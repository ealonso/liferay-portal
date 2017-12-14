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

package com.liferay.html.preview.messaging;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.html.preview.constants.HtmlPreviewConstants;
import com.liferay.html.preview.exception.InvalidHtmlPreviewEntryMimeTypeException;
import com.liferay.html.preview.model.HtmlPreviewEntry;
import com.liferay.html.preview.processor.HtmlPreviewProcessor;
import com.liferay.html.preview.processor.HtmlPreviewProcessorTracker;
import com.liferay.html.preview.service.HtmlPreviewEntryLocalService;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;

import java.io.File;
import java.io.FileInputStream;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {"destination.name=" + HtmlPreviewConstants.DESTINATION_NAME},
	service = MessageListener.class
)
public class HtmlPreviewGeneratorMessageListener extends BaseMessageListener {

	@Activate
	protected void activate() {
		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				HtmlPreviewConstants.DESTINATION_NAME);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		_messageBus.addDestination(destination);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		Map<String, Object> payload = (Map<String, Object>)message.getPayload();

		long userId = GetterUtil.getLong(payload.get("userId"));
		long groupId = GetterUtil.getLong(payload.get("groupId"));
		long htmlPreviewEntryId = GetterUtil.getLong(
			payload.get("htmlPreviewEntryId"));
		String content = GetterUtil.getString(payload.get("content"));
		String mimeType = GetterUtil.getString(payload.get("mimeType"));

		HtmlPreviewProcessor htmlPreviewProcessor =
			_htmlPreviewProcessorTracker.getHtmlPreviewProcessor(mimeType);

		if (htmlPreviewProcessor == null) {
			throw new InvalidHtmlPreviewEntryMimeTypeException(
				"No HTML preview processor available for MIME type " +
					mimeType);
		}

		File file = htmlPreviewProcessor.generateHtmlPreview(content);

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				groupId, HtmlPreviewEntry.class.getName());

		ServiceContext serviceContext = new ServiceContext();

		if (repository != null) {
			FileEntry fileEntry =
				PortletFileRepositoryUtil.fetchPortletFileEntry(
					groupId, repository.getDlFolderId(),
					String.valueOf(htmlPreviewEntryId));

			if (fileEntry != null) {
				PortletFileRepositoryUtil.deletePortletFileEntry(
					groupId, repository.getDlFolderId(),
					String.valueOf(htmlPreviewEntryId));
			}
		}
		else {
			synchronized (HtmlPreviewGeneratorMessageListener.class) {
				repository = PortletFileRepositoryUtil.addPortletRepository(
					groupId, HtmlPreviewEntry.class.getName(), serviceContext);
			}
		}

		DLFileEntry dlFileEntry = _dlFileEntryLocalService.addFileEntry(
			userId, groupId, repository.getRepositoryId(),
			repository.getDlFolderId(), file.getName(), mimeType,
			file.getName(), "", "", -1, null, null, new FileInputStream(file),
			file.length(), serviceContext);

		FileEntry fileEntry = new LiferayFileEntry(dlFileEntry);

		HtmlPreviewEntry htmlPreviewEntry =
			_htmlPreviewEntryLocalService.fetchHtmlPreviewEntry(
				htmlPreviewEntryId);

		if (htmlPreviewEntry != null) {
			htmlPreviewEntry.setFileEntryId(fileEntry.getFileEntryId());

			_htmlPreviewEntryLocalService.updateHtmlPreviewEntry(
				htmlPreviewEntry);
		}
	}

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private HtmlPreviewEntryLocalService _htmlPreviewEntryLocalService;

	@Reference
	private HtmlPreviewProcessorTracker _htmlPreviewProcessorTracker;

	@Reference
	private MessageBus _messageBus;

}