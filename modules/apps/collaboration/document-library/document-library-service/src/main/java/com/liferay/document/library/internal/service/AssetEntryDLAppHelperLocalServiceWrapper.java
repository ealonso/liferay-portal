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

import com.liferay.asset.model.AssetCategory;
import com.liferay.asset.model.AssetEntry;
import com.liferay.asset.model.AssetLink;
import com.liferay.asset.model.AssetLinkConstants;
import com.liferay.asset.service.AssetCategoryLocalService;
import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.asset.service.AssetLinkLocalService;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileShortcut;
import com.liferay.document.library.kernel.model.DLFileShortcutConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceWrapper;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileShortcutLocalService;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.document.library.kernel.util.DLAppHelperThreadLocal;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryDLAppHelperLocalServiceWrapper
	extends DLAppHelperLocalServiceWrapper {

	public AssetEntryDLAppHelperLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryDLAppHelperLocalServiceWrapper(
		DLAppHelperLocalService dlAppHelperLocalService) {

		super(dlAppHelperLocalService);
	}

	@Override
	public void addFolder(
			long userId, Folder folder, ServiceContext serviceContext)
		throws PortalException {

		super.addFolder(userId, folder, serviceContext);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		updateAsset(
			userId, folder, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());
	}

	@Override
	public void cancelCheckOut(
			long userId, FileEntry fileEntry, FileVersion sourceFileVersion,
			FileVersion destinationFileVersion, FileVersion draftFileVersion,
			ServiceContext serviceContext)
		throws PortalException {

		super.cancelCheckOut(
			userId, fileEntry, sourceFileVersion, destinationFileVersion,
			draftFileVersion, serviceContext);

		if (draftFileVersion == null) {
			return;
		}

		AssetEntry draftAssetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(),
			draftFileVersion.getPrimaryKey());

		if (draftAssetEntry != null) {
			_assetEntryLocalService.deleteEntry(draftAssetEntry);
		}
	}

	@Override
	public void checkAssetEntry(
			long userId, FileEntry fileEntry, FileVersion fileVersion)
		throws PortalException {

		super.checkAssetEntry(userId, fileEntry, fileVersion);

		AssetEntry fileEntryAssetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId());

		long[] assetCategoryIds = new long[0];
		String[] assetTagNames = new String[0];

		long fileEntryTypeId = getFileEntryTypeId(fileEntry);

		if (fileEntryAssetEntry == null) {
			fileEntryAssetEntry = _assetEntryLocalService.updateEntry(
				userId, fileEntry.getGroupId(), fileEntry.getCreateDate(),
				fileEntry.getModifiedDate(),
				DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId(),
				fileEntry.getUuid(), fileEntryTypeId, assetCategoryIds,
				assetTagNames, true, false, null, null, null, null,
				fileEntry.getMimeType(), fileEntry.getTitle(),
				fileEntry.getDescription(), null, null, null, 0, 0, null);
		}

		AssetEntry fileVersionAssetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(),
			fileVersion.getFileVersionId());

		if ((fileVersionAssetEntry == null) && !fileVersion.isApproved() &&
			!fileVersion.getVersion().equals(
				DLFileEntryConstants.VERSION_DEFAULT)) {

			assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
				DLFileEntryConstants.getClassName(),
				fileEntry.getFileEntryId());
			assetTagNames = _assetTagLocalService.getTagNames(
				DLFileEntryConstants.getClassName(),
				fileEntry.getFileEntryId());

			fileVersionAssetEntry = _assetEntryLocalService.updateEntry(
				userId, fileEntry.getGroupId(), fileEntry.getCreateDate(),
				fileEntry.getModifiedDate(),
				DLFileEntryConstants.getClassName(),
				fileVersion.getFileVersionId(), fileEntry.getUuid(),
				fileEntryTypeId, assetCategoryIds, assetTagNames, true, false,
				null, null, null, null, fileEntry.getMimeType(),
				fileEntry.getTitle(), fileEntry.getDescription(), null, null,
				null, 0, 0, null);

			List<AssetLink> assetLinks = _assetLinkLocalService.getDirectLinks(
				fileEntryAssetEntry.getEntryId(), false);

			long[] assetLinkIds = ListUtil.toLongArray(
				assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

			_assetLinkLocalService.updateLinks(
				userId, fileVersionAssetEntry.getEntryId(), assetLinkIds,
				AssetLinkConstants.TYPE_RELATED);
		}
	}

	@Override
	public void deleteFileEntry(FileEntry fileEntry) throws PortalException {
		super.deleteFileEntry(fileEntry);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		_assetEntryLocalService.deleteEntry(
			DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId());
	}

	@Override
	public void deleteFolder(Folder folder) throws PortalException {
		super.deleteFolder(folder);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		_assetEntryLocalService.deleteEntry(
			DLFolderConstants.getClassName(), folder.getFolderId());
	}

	@Override
	public void getFileAsStream(
		long userId, FileEntry fileEntry, boolean incrementCounter) {

		super.getFileAsStream(userId, fileEntry, incrementCounter);

		if (!incrementCounter) {
			return;
		}

		_assetEntryLocalService.incrementViewCounter(
			userId, DLFileEntryConstants.getClassName(),
			fileEntry.getFileEntryId(), 1);

		List<DLFileShortcut> fileShortcuts =
			_dlFileShortcutLocalService.getFileShortcuts(
				fileEntry.getFileEntryId());

		for (DLFileShortcut fileShortcut : fileShortcuts) {
			_assetEntryLocalService.incrementViewCounter(
				userId, DLFileShortcutConstants.getClassName(),
				fileShortcut.getFileShortcutId(), 1);
		}
	}

	@Override
	public void moveDependentsToTrash(DLFolder dlFolder)
		throws PortalException {

		super.moveDependentsToTrash(dlFolder);

		trashOrRestoreFolder(dlFolder, true);
	}

	@Override
	public void restoreDependentsFromTrash(DLFolder dlFolder)
		throws PortalException {

		super.restoreDependentsFromTrash(dlFolder);

		trashOrRestoreFolder(dlFolder, false);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry updateAsset(
			long userId, FileEntry fileEntry, FileVersion fileVersion,
			long assetClassPK)
		throws PortalException {

		super.updateAsset(userId, fileEntry, fileVersion, assetClassPK);

		long[] assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
			DLFileEntryConstants.getClassName(), assetClassPK);
		String[] assetTagNames = _assetTagLocalService.getTagNames(
			DLFileEntryConstants.getClassName(), assetClassPK);

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(), assetClassPK);

		List<AssetLink> assetLinks = null;

		if (assetEntry != null) {
			assetLinks = _assetLinkLocalService.getDirectLinks(
				assetEntry.getEntryId(), false);
		}

		long[] assetLinkIds = ListUtil.toLongArray(
			assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

		return updateAsset(
			userId, fileEntry, fileVersion, assetCategoryIds, assetTagNames,
			assetLinkIds);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry updateAsset(
			long userId, FileEntry fileEntry, FileVersion fileVersion,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds)
		throws PortalException {

		super.updateAsset(
			userId, fileEntry, fileVersion, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);

		AssetEntry assetEntry = null;

		boolean visible = false;

		boolean addDraftAssetEntry = false;

		if (fileEntry instanceof LiferayFileEntry) {
			DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

			if (dlFileVersion.isApproved()) {
				visible = true;
			}
			else {
				String version = dlFileVersion.getVersion();

				if (!version.equals(DLFileEntryConstants.VERSION_DEFAULT)) {
					addDraftAssetEntry = true;
				}
			}
		}
		else {
			visible = true;
		}

		long fileEntryTypeId = getFileEntryTypeId(fileEntry);

		if (addDraftAssetEntry) {
			if (assetCategoryIds == null) {
				assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
					DLFileEntryConstants.getClassName(),
					fileEntry.getFileEntryId());
			}

			if (assetTagNames == null) {
				assetTagNames = _assetTagLocalService.getTagNames(
					DLFileEntryConstants.getClassName(),
					fileEntry.getFileEntryId());
			}

			if (assetLinkEntryIds == null) {
				AssetEntry previousAssetEntry =
					_assetEntryLocalService.getEntry(
						DLFileEntryConstants.getClassName(),
						fileEntry.getFileEntryId());

				List<AssetLink> assetLinks =
					_assetLinkLocalService.getDirectLinks(
						previousAssetEntry.getEntryId(),
						AssetLinkConstants.TYPE_RELATED, false);

				assetLinkEntryIds = ListUtil.toLongArray(
					assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);
			}

			assetEntry = _assetEntryLocalService.updateEntry(
				userId, fileEntry.getGroupId(), fileEntry.getCreateDate(),
				fileEntry.getModifiedDate(),
				DLFileEntryConstants.getClassName(),
				fileVersion.getFileVersionId(), fileEntry.getUuid(),
				fileEntryTypeId, assetCategoryIds, assetTagNames, true, false,
				null, null, null, null, fileEntry.getMimeType(),
				fileEntry.getTitle(), fileEntry.getDescription(), null, null,
				null, 0, 0, null);
		}
		else {
			Date publishDate = null;

			if (visible) {
				publishDate = fileEntry.getCreateDate();
			}

			assetEntry = _assetEntryLocalService.updateEntry(
				userId, fileEntry.getGroupId(), fileEntry.getCreateDate(),
				fileEntry.getModifiedDate(),
				DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId(),
				fileEntry.getUuid(), fileEntryTypeId, assetCategoryIds,
				assetTagNames, true, visible, null, null, publishDate, null,
				fileEntry.getMimeType(), fileEntry.getTitle(),
				fileEntry.getDescription(), null, null, null, 0, 0, null);

			List<DLFileShortcut> dlFileShortcuts =
				_dlFileShortcutLocalService.getFileShortcuts(
					fileEntry.getFileEntryId());

			for (DLFileShortcut dlFileShortcut : dlFileShortcuts) {
				_assetEntryLocalService.updateEntry(
					userId, dlFileShortcut.getGroupId(),
					dlFileShortcut.getCreateDate(),
					dlFileShortcut.getModifiedDate(),
					DLFileShortcutConstants.getClassName(),
					dlFileShortcut.getFileShortcutId(),
					dlFileShortcut.getUuid(), fileEntryTypeId, assetCategoryIds,
					assetTagNames, true, true, null, null,
					dlFileShortcut.getCreateDate(), null,
					fileEntry.getMimeType(), fileEntry.getTitle(),
					fileEntry.getDescription(), null, null, null, 0, 0, null);
			}
		}

		_assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);

		return ModelAdapterUtil.adapt(
			com.liferay.asset.kernel.model.AssetEntry.class, assetEntry);
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry updateAsset(
			long userId, Folder folder, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException {

		super.updateAsset(
			userId, folder, assetCategoryIds, assetTagNames, assetLinkEntryIds);

		AssetEntry assetEntry = null;

		boolean visible = false;

		if (folder instanceof LiferayFolder) {
			DLFolder dlFolder = (DLFolder)folder.getModel();

			if (dlFolder.isApproved() && !dlFolder.isHidden() &&
				!dlFolder.isInHiddenFolder()) {

				visible = true;
			}
		}
		else {
			visible = true;
		}

		Date publishDate = null;

		if (visible) {
			publishDate = folder.getCreateDate();
		}

		assetEntry = _assetEntryLocalService.updateEntry(
			userId, folder.getGroupId(), folder.getCreateDate(),
			folder.getModifiedDate(), DLFolderConstants.getClassName(),
			folder.getFolderId(), folder.getUuid(), 0, assetCategoryIds,
			assetTagNames, true, visible, null, null, publishDate, null, null,
			folder.getName(), folder.getDescription(), null, null, null, 0, 0,
			null);

		_assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);

		return ModelAdapterUtil.adapt(
			com.liferay.asset.kernel.model.AssetEntry.class, assetEntry);
	}

	@Override
	public void updateFileEntry(
			long userId, FileEntry fileEntry, FileVersion sourceFileVersion,
			FileVersion destinationFileVersion, long assetClassPK)
		throws PortalException {

		super.updateFileEntry(
			userId, fileEntry, sourceFileVersion, destinationFileVersion,
			assetClassPK);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		boolean updateAsset = true;

		if (fileEntry instanceof LiferayFileEntry &&
			fileEntry.getVersion().equals(
				destinationFileVersion.getVersion())) {

			updateAsset = false;
		}

		if (updateAsset) {
			updateAsset(
				userId, fileEntry, destinationFileVersion, assetClassPK);
		}
	}

	@Override
	public void updateFileEntry(
			long userId, FileEntry fileEntry, FileVersion sourceFileVersion,
			FileVersion destinationFileVersion, ServiceContext serviceContext)
		throws PortalException {

		super.updateFileEntry(
			userId, fileEntry, sourceFileVersion, destinationFileVersion,
			serviceContext);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		if (Objects.equals(serviceContext.getCommand(), Constants.REVERT)) {
			List<AssetCategory> assetCategories =
				_assetCategoryLocalService.getCategories(
					DLFileEntryConstants.getClassName(),
					fileEntry.getFileEntryId());

			List<Long> assetCategoryIds = ListUtil.toList(
				assetCategories, AssetCategory.CATEGORY_ID_ACCESSOR);

			serviceContext.setAssetCategoryIds(
				ArrayUtil.toLongArray(assetCategoryIds));
		}

		updateAsset(
			userId, fileEntry, destinationFileVersion,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());
	}

	@Override
	public void updateFolder(
			long userId, Folder folder, ServiceContext serviceContext)
		throws PortalException {

		super.updateFolder(userId, folder, serviceContext);

		updateAsset(
			userId, folder, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames(),
			serviceContext.getAssetLinkEntryIds());
	}

	@Override
	public void updateStatus(
			long userId, FileEntry fileEntry, FileVersion latestFileVersion,
			int oldStatus, int newStatus, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		super.updateStatus(
			userId, fileEntry, latestFileVersion, oldStatus, newStatus,
			serviceContext, workflowContext);

		if (!DLAppHelperThreadLocal.isEnabled()) {
			return;
		}

		if (newStatus == WorkflowConstants.STATUS_APPROVED) {
			String latestFileVersionVersion = latestFileVersion.getVersion();

			if (latestFileVersionVersion.equals(fileEntry.getVersion())) {
				if (!latestFileVersionVersion.equals(
						DLFileEntryConstants.VERSION_DEFAULT)) {

					AssetEntry draftAssetEntry =
						_assetEntryLocalService.fetchEntry(
							DLFileEntryConstants.getClassName(),
							latestFileVersion.getPrimaryKey());

					if (draftAssetEntry != null) {
						long fileEntryTypeId = getFileEntryTypeId(fileEntry);

						long[] assetCategoryIds =
							draftAssetEntry.getCategoryIds();
						String[] assetTagNames = draftAssetEntry.getTagNames();

						List<AssetLink> assetLinks =
							_assetLinkLocalService.getDirectLinks(
								draftAssetEntry.getEntryId(),
								AssetLinkConstants.TYPE_RELATED, false);

						long[] assetLinkEntryIds = ListUtil.toLongArray(
							assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

						AssetEntry assetEntry =
							_assetEntryLocalService.updateEntry(
								userId, fileEntry.getGroupId(),
								fileEntry.getCreateDate(),
								fileEntry.getModifiedDate(),
								DLFileEntryConstants.getClassName(),
								fileEntry.getFileEntryId(), fileEntry.getUuid(),
								fileEntryTypeId, assetCategoryIds,
								assetTagNames, true, true, null, null,
								fileEntry.getCreateDate(), null,
								draftAssetEntry.getMimeType(),
								fileEntry.getTitle(),
								fileEntry.getDescription(), null, null, null, 0,
								0, null);

						_assetLinkLocalService.updateLinks(
							userId, assetEntry.getEntryId(), assetLinkEntryIds,
							AssetLinkConstants.TYPE_RELATED);

						_assetEntryLocalService.deleteEntry(draftAssetEntry);
					}
				}

				AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
					DLFileEntryConstants.getClassName(),
					fileEntry.getFileEntryId());

				if (assetEntry != null) {
					_assetEntryLocalService.updateVisible(
						DLFileEntryConstants.getClassName(),
						fileEntry.getFileEntryId(), true);
				}
			}
		}
		else {
			boolean visible = false;

			if (newStatus != WorkflowConstants.STATUS_IN_TRASH) {
				List<DLFileVersion> approvedFileVersions =
					_dlFileVersionLocalService.getFileVersions(
						fileEntry.getFileEntryId(),
						WorkflowConstants.STATUS_APPROVED);

				if (!approvedFileVersions.isEmpty()) {
					visible = true;
				}
			}

			_assetEntryLocalService.updateVisible(
				DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId(),
				visible);
		}
	}

	protected long getFileEntryTypeId(FileEntry fileEntry) {
		if (fileEntry instanceof LiferayFileEntry) {
			DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

			return dlFileEntry.getFileEntryTypeId();
		}

		return 0;
	}

	protected void trashOrRestoreFolder(DLFolder dlFolder, boolean moveToTrash)
		throws PortalException {

		long dlFileEntryClassNameId = _classNameLocalService.getClassNameId(
			DLFileEntry.class);

		List<DLFileEntry> dlFileEntries =
			_dlFileEntryLocalService.getFileEntriesByClassNameIdAndTreePath(
				dlFileEntryClassNameId, dlFolder.getTreePath());

		for (DLFileEntry dlFileEntry : dlFileEntries) {
			_assetEntryLocalService.updateVisible(
				DLFileEntry.class.getName(), dlFileEntry.getFileEntryId(),
				!moveToTrash);
		}

		long dlFolderClassNameId = _classNameLocalService.getClassNameId(
			DLFolder.class);

		List<DLFolder> dlFolders = _dlFolderLocalService.getFolders(
			dlFolderClassNameId, dlFolder.getTreePath());

		for (DLFolder curDLFolder : dlFolders) {
			_assetEntryLocalService.updateVisible(
				DLFolder.class.getName(), curDLFolder.getFolderId(),
				!moveToTrash);
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileShortcutLocalService _dlFileShortcutLocalService;

	@Reference
	private DLFileVersionLocalService _dlFileVersionLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

}