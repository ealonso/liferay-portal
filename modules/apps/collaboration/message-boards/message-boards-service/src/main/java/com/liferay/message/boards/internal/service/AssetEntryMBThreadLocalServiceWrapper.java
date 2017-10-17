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
import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBMessageLocalService;
import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.model.TrashVersion;
import com.liferay.trash.service.TrashVersionLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryMBThreadLocalServiceWrapper
	extends MBThreadLocalServiceWrapper {

	public AssetEntryMBThreadLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryMBThreadLocalServiceWrapper(
		MBThreadLocalService mbThreadLocalService) {

		super(mbThreadLocalService);
	}

	@Override
	public MBThread addThread(
			long categoryId, MBMessage message, ServiceContext serviceContext)
		throws PortalException {

		MBThread thread = super.addThread(categoryId, message, serviceContext);

		if (categoryId >= 0) {
			_assetEntryLocalService.updateEntry(
				message.getUserId(), message.getGroupId(),
				thread.getStatusDate(), thread.getLastPostDate(),
				MBThread.class.getName(), thread.getThreadId(),
				thread.getUuid(), 0, new long[0], new String[0], true, false,
				null, null, thread.getStatusDate(), null, null,
				String.valueOf(thread.getRootMessageId()), null, null, null,
				null, 0, 0, serviceContext.getAssetPriority());
		}

		return thread;
	}

	@Override
	public void deleteThread(MBThread thread) throws PortalException {
		MBMessage rootMessage = _mbMessageLocalService.getMessage(
			thread.getRootMessageId());

		List<MBMessage> messages = _mbMessageLocalService.getThreadMessages(
			thread.getThreadId(), WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {
			_assetEntryLocalService.deleteEntry(
				message.getWorkflowClassName(), message.getMessageId());
		}

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			MBThread.class.getName(), thread.getThreadId());

		if (assetEntry != null) {
			assetEntry.setTitle(rootMessage.getSubject());

			_assetEntryLocalService.updateAssetEntry(assetEntry);
		}

		super.deleteThread(thread);
	}

	@Override
	public void moveDependentsToTrash(
			long groupId, long threadId, long trashEntryId)
		throws PortalException {

		List<MBMessage> messages = _mbMessageLocalService.getThreadMessages(
			threadId, WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {
			if (message.isDiscussion()) {
				continue;
			}

			int oldStatus = message.getStatus();

			if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
				_assetEntryLocalService.updateVisible(
					MBMessage.class.getName(), message.getMessageId(), false);
			}
		}

		super.moveDependentsToTrash(groupId, threadId, trashEntryId);
	}

	@Override
	public void restoreDependentsFromTrash(long groupId, long threadId)
		throws PortalException {

		List<MBMessage> messages = _mbMessageLocalService.getThreadMessages(
			threadId, WorkflowConstants.STATUS_ANY);

		for (MBMessage message : messages) {

			// Message

			if (message.isDiscussion()) {
				continue;
			}

			TrashVersion trashVersion = _trashVersionLocalService.fetchVersion(
				MBMessage.class.getName(), message.getMessageId());

			int oldStatus = WorkflowConstants.STATUS_APPROVED;

			if (trashVersion != null) {
				oldStatus = trashVersion.getStatus();
			}

			if (oldStatus == WorkflowConstants.STATUS_APPROVED) {
				_assetEntryLocalService.updateVisible(
					MBMessage.class.getName(), message.getMessageId(), true);
			}
		}

		super.restoreDependentsFromTrash(groupId, threadId);
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private MBMessageLocalService _mbMessageLocalService;

	@Reference
	private TrashVersionLocalService _trashVersionLocalService;

}