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

package com.liferay.document.library.internal.service;

import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryDLFileShortcutLocalServiceWrapper
	extends DLFileShortcutLocalServiceWrapper {

	public AssetEntryDLFileShortcutLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryDLFileShortcutLocalServiceWrapper(
		DLFileShortcutLocalService dlFileShortcutLocalService) {

		super(dlFileShortcutLocalService);
	}

	@Override
	public DLFileShortcut addFileShortcut(
			long userId, long groupId, long repositoryId, long folderId,
			long toFileEntryId, ServiceContext serviceContext)
		throws PortalException {

		DLFileShortcut fileShortcut = super.addFileShortcut(
			userId, groupId, repositoryId, folderId, toFileEntryId,
			serviceContext);

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(toFileEntryId);

		copyAssetTags(fileEntry, serviceContext);

		updateAsset(
			userId, fileShortcut, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return fileShortcut;
	}

	@Override
	public void deleteFileShortcut(DLFileShortcut fileShortcut)
		throws PortalException {

		super.deleteFileShortcut(fileShortcut);

		_assetEntryLocalService.deleteEntry(
			DLFileShortcutConstants.getClassName(),
			fileShortcut.getFileShortcutId());
	}

	@Override
	public void updateAsset(
			long userId, DLFileShortcut fileShortcut, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		super.updateAsset(
			userId, fileShortcut, assetCategoryIds, assetTagNames);

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(
			fileShortcut.getToFileEntryId());

		_assetEntryLocalService.updateEntry(
			userId, fileShortcut.getGroupId(), fileShortcut.getCreateDate(),
			fileShortcut.getModifiedDate(),
			DLFileShortcutConstants.getClassName(),
			fileShortcut.getFileShortcutId(), fileShortcut.getUuid(), 0,
			assetCategoryIds, assetTagNames, true, false, null, null,
			fileShortcut.getCreateDate(), null, fileEntry.getMimeType(),
			fileEntry.getTitle(), fileEntry.getDescription(), null, null, null,
			0, 0, null);
	}

	@Override
	public DLFileShortcut updateFileShortcut(
			long userId, long fileShortcutId, long repositoryId, long folderId,
			long toFileEntryId, ServiceContext serviceContext)
		throws PortalException {

		DLFileShortcut fileShortcut = super.updateFileShortcut(
			userId, fileShortcutId, repositoryId, folderId, toFileEntryId,
			serviceContext);

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(toFileEntryId);

		copyAssetTags(fileEntry, serviceContext);

		updateAsset(
			userId, fileShortcut, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return fileShortcut;
	}

	protected void copyAssetTags(
			FileEntry fileEntry, ServiceContext serviceContext)
		throws PortalException {

		String[] assetTagNames = _assetTagLocalService.getTagNames(
			FileEntry.class.getName(), fileEntry.getFileEntryId());

		_assetTagLocalService.checkTags(
			serviceContext.getUserId(), serviceContext.getScopeGroupId(),
			assetTagNames);

		serviceContext.setAssetTagNames(assetTagNames);
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

}