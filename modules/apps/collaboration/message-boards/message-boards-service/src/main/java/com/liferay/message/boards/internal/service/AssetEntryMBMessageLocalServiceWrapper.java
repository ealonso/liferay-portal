/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.message.boards.internal.service;

import com.liferay.asset.model.AssetEntry;
import com.liferay.asset.model.AssetLinkConstants;
import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.asset.service.AssetLinkLocalService;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBMessageConstants;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.social.SocialActivityManagerUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.messageboards.social.MBActivityKeys;
import com.liferay.social.kernel.model.SocialActivityConstants;
import com.liferay.trash.service.TrashVersionLocalService;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryMBMessageLocalServiceWrapper
	extends MBMessageLocalServiceWrapper {

	public AssetEntryMBMessageLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryMBMessageLocalServiceWrapper(
		MBMessageLocalService mbMessageLocalService) {

		super(mbMessageLocalService);
	}

	@Override
	public MBMessage deleteMessage(MBMessage message) throws PortalException {
		_assetEntryLocalService.deleteEntry(
			message.getWorkflowClassName(), message.getMessageId());

		return super.deleteMessage(message);
	}

	@Override
	public void updateAsset(
			long userId, MBMessage message, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException {

		super.updateAsset(
			userId, message, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);

		updateAsset(
			userId, message, assetCategoryIds, assetTagNames, assetLinkEntryIds,
			true);
	}

	@Override
	public void updateAsset(
			long userId, MBMessage message, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds,
			boolean assetEntryVisible)
		throws PortalException {

		boolean visible = false;
		Date publishDate = null;

		if (assetEntryVisible && message.isApproved() &&
			((message.getClassNameId() == 0) ||
			 (message.getParentMessageId() != 0))) {

			visible = true;
			publishDate = message.getModifiedDate();
		}

		AssetEntry assetEntry = _assetEntryLocalService.updateEntry(
			userId, message.getGroupId(), message.getCreateDate(),
			message.getModifiedDate(), message.getWorkflowClassName(),
			message.getMessageId(), message.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, visible, null, null, publishDate, null,
			ContentTypes.TEXT_HTML, message.getSubject(), null, null, null,
			null, 0, 0, message.getPriority());

		_assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Override
	public MBMessage updateMessage(
			long userId, long messageId, String subject, String body,
			List<ObjectValuePair<String, InputStream>> inputStreamOVPs,
			List<String> existingFiles, double priority, boolean allowPingbacks,
			ServiceContext serviceContext)
		throws PortalException {

		MBMessage message = super.updateMessage(
			userId, messageId, subject, body, inputStreamOVPs, existingFiles,
			priority, allowPingbacks, serviceContext);

		if ((serviceContext.getWorkflowAction() ==
				WorkflowConstants.ACTION_SAVE_DRAFT) &&
			!message.isDraft() && !message.isPending()) {

			_assetEntryLocalService.updateVisible(
				message.getWorkflowClassName(), message.getMessageId(), false);
		}

		updateAsset(
			userId, message, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());

		return message;
	}

	@Override
	public MBMessage updateStatus(
			long userId, long messageId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		MBMessage message = getMessage(messageId);

		int oldStatus = message.getStatus();

		message = super.updateStatus(
			userId, messageId, status, serviceContext, workflowContext);

		User user = _userLocalService.getUser(userId);
		Date now = new Date();

		if (status == WorkflowConstants.STATUS_APPROVED) {
			if (oldStatus != WorkflowConstants.STATUS_APPROVED) {

				// Asset

				if (serviceContext.isAssetEntryVisible() &&
					((message.getClassNameId() == 0) ||
					 (message.getParentMessageId() != 0))) {

					Date publishDate = null;

					AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
						message.getWorkflowClassName(), message.getMessageId());

					if ((assetEntry != null) &&
						(assetEntry.getPublishDate() != null)) {

						publishDate = assetEntry.getPublishDate();
					}
					else {
						publishDate = now;

						serviceContext.setCommand(Constants.ADD);
					}

					_assetEntryLocalService.updateEntry(
						message.getWorkflowClassName(), message.getMessageId(),
						publishDate, null, true, true);
				}

				if (serviceContext.isCommandAdd()) {

					// Social

					JSONObject extraDataJSONObject =
						JSONFactoryUtil.createJSONObject();

					String title = message.getSubject();

					if (message.isDiscussion()) {
						title = HtmlUtil.stripHtml(title);
					}

					extraDataJSONObject.put("title", title);

					if (!message.isDiscussion()) {
						if (!message.isAnonymous() && !user.isDefaultUser()) {
							long receiverUserId = 0;

							MBMessage parentMessage = fetchMBMessage(
								message.getParentMessageId());

							if (parentMessage != null) {
								receiverUserId = parentMessage.getUserId();
							}

							SocialActivityManagerUtil.addActivity(
								message.getUserId(), message,
								MBActivityKeys.ADD_MESSAGE,
								extraDataJSONObject.toString(), receiverUserId);

							if ((parentMessage != null) &&
								(receiverUserId != message.getUserId())) {

								SocialActivityManagerUtil.addActivity(
									message.getUserId(), parentMessage,
									MBActivityKeys.REPLY_MESSAGE,
									extraDataJSONObject.toString(), 0);
							}
						}
					}
					else {
						String className = (String)serviceContext.getAttribute(
							"className");
						long classPK = ParamUtil.getLong(
							serviceContext, "classPK");
						long parentMessageId = message.getParentMessageId();

						if (parentMessageId !=
								MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) {

							AssetEntry assetEntry =
								_assetEntryLocalService.fetchEntry(
									className, classPK);

							if (assetEntry != null) {
								extraDataJSONObject.put(
									"messageId", message.getMessageId());

								SocialActivityManagerUtil.addActivity(
									message.getUserId(), assetEntry,
									SocialActivityConstants.TYPE_ADD_COMMENT,
									extraDataJSONObject.toString(),
									assetEntry.getUserId());
							}
						}
					}
				}
			}
		}
		else if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
			_assetEntryLocalService.updateVisible(
				message.getWorkflowClassName(), message.getMessageId(), false);
		}

		return message;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private TrashVersionLocalService _trashVersionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}