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

package com.liferay.html.preview.service.impl;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.html.preview.model.HtmlPreviewEntry;
import com.liferay.html.preview.service.base.HtmlPreviewEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;

import java.io.File;

import java.util.Date;

/**
 * @author Pavel Savinov
 */
public class HtmlPreviewEntryLocalServiceImpl
	extends HtmlPreviewEntryLocalServiceBaseImpl {

	@Override
	public HtmlPreviewEntry addHtmlPreviewEntry(
			long userId, long groupId, long classNameId, long classPK,
			File htmlPreviewFile, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long htmlPreviewEntryId = counterLocalService.increment();

		HtmlPreviewEntry htmlPreviewEntry = htmlPreviewEntryPersistence.create(
			htmlPreviewEntryId);

		htmlPreviewEntry.setGroupId(groupId);
		htmlPreviewEntry.setCompanyId(user.getCompanyId());
		htmlPreviewEntry.setUserId(user.getUserId());
		htmlPreviewEntry.setUserName(user.getFullName());
		htmlPreviewEntry.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		htmlPreviewEntry.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		htmlPreviewEntry.setClassNameId(classNameId);
		htmlPreviewEntry.setClassPK(classPK);

		FileEntry fileEntry = _getFileEntry(
			htmlPreviewEntry.getUserId(), htmlPreviewEntry.getGroupId(),
			htmlPreviewEntryId, htmlPreviewFile);

		if (fileEntry != null) {
			htmlPreviewEntry.setFileEntryId(fileEntry.getFileEntryId());
		}

		htmlPreviewEntryPersistence.update(htmlPreviewEntry);

		return htmlPreviewEntry;
	}

	@Override
	public HtmlPreviewEntry deleteHtmlPreviewEntry(
			HtmlPreviewEntry htmlPreviewEntry)
		throws PortalException {

		htmlPreviewEntryPersistence.remove(htmlPreviewEntry);

		if (htmlPreviewEntry.getFileEntryId() > 0) {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				htmlPreviewEntry.getFileEntryId());
		}

		return htmlPreviewEntry;
	}

	@Override
	public HtmlPreviewEntry deleteHtmlPreviewEntry(long htmlPreviewEntryId)
		throws PortalException {

		HtmlPreviewEntry htmlPreviewEntry =
			htmlPreviewEntryPersistence.fetchByPrimaryKey(htmlPreviewEntryId);

		return deleteHtmlPreviewEntry(htmlPreviewEntry);
	}

	@Override
	public HtmlPreviewEntry updateHtmlPreviewEntry(
			long htmlPreviewEntryId, File htmlPreviewFile,
			ServiceContext serviceContext)
		throws PortalException {

		HtmlPreviewEntry htmlPreviewEntry =
			htmlPreviewEntryPersistence.fetchByPrimaryKey(htmlPreviewEntryId);

		htmlPreviewEntry.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));

		FileEntry fileEntry = _getFileEntry(
			htmlPreviewEntry.getUserId(), htmlPreviewEntry.getGroupId(),
			htmlPreviewEntryId, htmlPreviewFile);

		if (fileEntry != null) {
			htmlPreviewEntry.setFileEntryId(fileEntry.getFileEntryId());
		}

		htmlPreviewEntryPersistence.update(htmlPreviewEntry);

		return htmlPreviewEntry;
	}

	private FileEntry _getFileEntry(
			long userId, long groupId, long htmlPreviewEntryId,
			File htmlPreviewFile)
		throws PortalException {

		if (htmlPreviewFile == null) {
			return null;
		}

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				groupId, HtmlPreviewEntry.class.getName());

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

		String extension = FileUtil.getExtension(htmlPreviewFile.getName());

		String mimeType = MimeTypesUtil.getExtensionContentType(
			extension);

		return PortletFileRepositoryUtil.addPortletFileEntry(
			groupId, userId, HtmlPreviewEntry.class.getName(),
			htmlPreviewEntryId, HtmlPreviewEntry.class.getName(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, htmlPreviewFile,
			String.valueOf(htmlPreviewEntryId), mimeType, false);
	}

}