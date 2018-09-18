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

package com.liferay.asset.list.service.impl;

import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.asset.list.service.base.AssetListEntryAssetEntryRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

/**
 * @author Pavel Savinov
 * @see AssetListEntryAssetEntryRelServiceBaseImpl
 * @see com.liferay.asset.list.service.AssetListEntryAssetEntryRelServiceUtil
 */
public class AssetListEntryAssetEntryRelServiceImpl
	extends AssetListEntryAssetEntryRelServiceBaseImpl {

	@Override
	public AssetListEntryAssetEntryRel addAssetListEntryAssetEntryRel(
			long assetListEntryId, long assetEntryId)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryLocalService.getAssetListEntry(assetListEntryId);

		_assetListEntryModelResourcePermission.check(
			getPermissionChecker(), assetListEntry, ActionKeys.UPDATE);

		return assetListEntryAssetEntryRelLocalService.
			addAssetListEntryAssetEntryRel(assetListEntryId, assetEntryId);
	}

	@Override
	public AssetListEntryAssetEntryRel deleteAssetListEntryAssetEntryRel(
			long assetListEntryId, int position)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryLocalService.getAssetListEntry(assetListEntryId);

		_assetListEntryModelResourcePermission.check(
			getPermissionChecker(), assetListEntry, ActionKeys.UPDATE);

		return assetListEntryAssetEntryRelLocalService.
			deleteAssetListEntryAssetEntryRel(assetListEntryId, position);
	}

	@Override
	public List<AssetListEntryAssetEntryRel> getAssetListEntryAssetEntryRels(
			long assetListEntryId, int start, int end)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryLocalService.fetchAssetListEntry(assetListEntryId);

		if (assetListEntry != null) {
			_assetListEntryModelResourcePermission.check(
				getPermissionChecker(), assetListEntry, ActionKeys.VIEW);
		}

		return assetListEntryAssetEntryRelLocalService.
			getAssetListEntryAssetEntryRels(assetListEntryId, start, end);
	}

	@Override
	public int getAssetListEntryAssetEntryRelsCount(long assetListEntryId)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryLocalService.fetchAssetListEntry(assetListEntryId);

		if (assetListEntry != null) {
			_assetListEntryModelResourcePermission.check(
				getPermissionChecker(), assetListEntry, ActionKeys.VIEW);
		}

		return assetListEntryAssetEntryRelLocalService.
			getAssetListEntryAssetEntryRelsCount(assetListEntryId);
	}

	@Override
	public AssetListEntryAssetEntryRel moveAssetEntry(
			long assetListEntryId, int position, int newPosition)
		throws PortalException {

		AssetListEntry assetListEntry =
			assetListEntryLocalService.getAssetListEntry(assetListEntryId);

		_assetListEntryModelResourcePermission.check(
			getPermissionChecker(), assetListEntry, ActionKeys.UPDATE);

		return assetListEntryAssetEntryRelLocalService.moveAssetEntry(
			assetListEntryId, position, newPosition);
	}

	private static volatile ModelResourcePermission<AssetListEntry>
		_assetListEntryModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				AssetListEntryServiceImpl.class,
				"_assetListEntryModelResourcePermission", AssetListEntry.class);

}