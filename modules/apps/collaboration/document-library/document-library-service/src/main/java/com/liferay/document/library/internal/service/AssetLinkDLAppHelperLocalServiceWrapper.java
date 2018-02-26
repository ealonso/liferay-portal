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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.link.model.AssetLink;
import com.liferay.asset.link.model.AssetLinkConstants;
import com.liferay.asset.link.service.AssetLinkLocalService;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLAppHelperLocalService;
import com.liferay.document.library.kernel.service.DLAppHelperLocalServiceWrapper;
import com.liferay.document.library.kernel.util.DLAppHelperThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.repository.liferayrepository.model.LiferayFileEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetLinkDLAppHelperLocalServiceWrapper
	extends DLAppHelperLocalServiceWrapper {

	public AssetLinkDLAppHelperLocalServiceWrapper() {
		super(null);
	}

	public AssetLinkDLAppHelperLocalServiceWrapper(
		DLAppHelperLocalService dlAppHelperLocalService) {

		super(dlAppHelperLocalService);
	}

	@Override
	public void checkAssetEntry(
			long userId, FileEntry fileEntry, FileVersion fileVersion)
		throws PortalException {

		super.checkAssetEntry(userId, fileEntry, fileVersion);

		AssetEntry fileVersionAssetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(),
			fileVersion.getFileVersionId());

		if ((fileVersionAssetEntry != null) || fileVersion.isApproved() ||
			Objects.equals(
				fileVersion.getVersion(),
				DLFileEntryConstants.VERSION_DEFAULT)) {

			return;
		}

		AssetEntry fileEntryAssetEntry = _assetEntryLocalService.fetchEntry(
			DLFileEntryConstants.getClassName(), fileEntry.getFileEntryId());

		List<AssetLink> assetLinks = _assetLinkLocalService.getDirectLinks(
			fileEntryAssetEntry.getEntryId(), false);

		long[] assetLinkIds = ListUtil.toLongArray(
			assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

		_assetLinkLocalService.updateLinks(
			userId, fileVersionAssetEntry.getEntryId(), assetLinkIds,
			AssetLinkConstants.TYPE_RELATED);
	}

	@Override
	public AssetEntry updateAsset(
			long userId, FileEntry fileEntry, FileVersion fileVersion,
			long assetClassPK)
		throws PortalException {

		AssetEntry assetEntry = super.updateAsset(
			userId, fileEntry, fileVersion, assetClassPK);

		long[] assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
			DLFileEntryConstants.getClassName(), assetClassPK);
		String[] assetTagNames = _assetTagLocalService.getTagNames(
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
	public AssetEntry updateAsset(
			long userId, FileEntry fileEntry, FileVersion fileVersion,
			long[] assetCategoryIds, String[] assetTagNames,
			long[] assetLinkEntryIds)
		throws PortalException {

		AssetEntry assetEntry = super.updateAsset(
			userId, fileEntry, fileVersion, assetCategoryIds, assetTagNames,
			assetLinkEntryIds);

		boolean addDraftAssetEntry = false;

		if (fileEntry instanceof LiferayFileEntry) {
			DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();

			if (!dlFileVersion.isApproved()) {
				String version = dlFileVersion.getVersion();

				if (!version.equals(DLFileEntryConstants.VERSION_DEFAULT)) {
					addDraftAssetEntry = true;
				}
			}
		}

		if (addDraftAssetEntry && (assetLinkEntryIds == null)) {
			AssetEntry previousAssetEntry = _assetEntryLocalService.getEntry(
				DLFileEntryConstants.getClassName(),
				fileEntry.getFileEntryId());

			List<AssetLink> assetLinks = _assetLinkLocalService.getDirectLinks(
				previousAssetEntry.getEntryId(),
				AssetLinkConstants.TYPE_RELATED, false);

			assetLinkEntryIds = ListUtil.toLongArray(
				assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);
		}

		_assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);

		return assetEntry;
	}

	@Override
	public AssetEntry updateAsset(
			long userId, Folder folder, long[] assetCategoryIds,
			String[] assetTagNames, long[] assetLinkEntryIds)
		throws PortalException {

		AssetEntry assetEntry = super.updateAsset(
			userId, folder, assetCategoryIds, assetTagNames, assetLinkEntryIds);

		_assetLinkLocalService.updateLinks(
			userId, assetEntry.getEntryId(), assetLinkEntryIds,
			AssetLinkConstants.TYPE_RELATED);

		return assetEntry;
	}

	@Override
	public void updateStatus(
			long userId, FileEntry fileEntry, FileVersion latestFileVersion,
			int oldStatus, int newStatus, ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		if (DLAppHelperThreadLocal.isEnabled() &&
			(newStatus == WorkflowConstants.STATUS_APPROVED)) {

			String latestFileVersionVersion = latestFileVersion.getVersion();

			if (latestFileVersionVersion.equals(fileEntry.getVersion())) {
				if (!latestFileVersionVersion.equals(
						DLFileEntryConstants.VERSION_DEFAULT)) {

					AssetEntry draftAssetEntry =
						_assetEntryLocalService.fetchEntry(
							DLFileEntryConstants.getClassName(),
							latestFileVersion.getPrimaryKey());

					if (draftAssetEntry != null) {
						List<AssetLink> assetLinks =
							_assetLinkLocalService.getDirectLinks(
								draftAssetEntry.getEntryId(),
								AssetLinkConstants.TYPE_RELATED, false);

						long[] assetLinkEntryIds = ListUtil.toLongArray(
							assetLinks, AssetLink.ENTRY_ID2_ACCESSOR);

						AssetEntry assetEntry =
							_assetEntryLocalService.fetchEntry(
								DLFileEntryConstants.getClassName(),
								fileEntry.getFileEntryId());

						_assetLinkLocalService.updateLinks(
							userId, assetEntry.getEntryId(), assetLinkEntryIds,
							AssetLinkConstants.TYPE_RELATED);
					}
				}
			}
		}

		super.updateStatus(
			userId, fileEntry, latestFileVersion, oldStatus, newStatus,
			serviceContext, workflowContext);
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

}