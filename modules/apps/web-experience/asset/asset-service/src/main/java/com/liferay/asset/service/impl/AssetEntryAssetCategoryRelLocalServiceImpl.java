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

package com.liferay.asset.service.impl;

import com.liferay.asset.model.AssetEntry;
import com.liferay.asset.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.service.base.AssetEntryAssetCategoryRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryAssetCategoryRelLocalServiceImpl
	extends AssetEntryAssetCategoryRelLocalServiceBaseImpl {

	@Override
	public void addAssetEntryAssetCategoryRel(
		AssetEntry assetEntry, long assetCategoryId) {

		addAssetEntryAssetCategoryRel(
			assetEntry.getCompanyId(), assetEntry.getEntryId(),
			assetCategoryId);
	}

	@Override
	public void addAssetEntryAssetCategoryRel(
		AssetEntry assetEntry, long[] assetCategoryIds) {

		for (long assetCategoryId : assetCategoryIds) {
			addAssetEntryAssetCategoryRel(
				assetEntry.getCompanyId(), assetEntry.getEntryId(),
				assetCategoryId);
		}
	}

	@Override
	public void addAssetEntryAssetCategoryRel(
		long assetEntryId, long assetCategoryId) {

		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(assetEntryId);

		addAssetEntryAssetCategoryRel(
			assetEntry.getCompanyId(), assetEntry.getEntryId(),
			assetCategoryId);
	}

	@Override
	public AssetEntryAssetCategoryRel addAssetEntryAssetCategoryRel(
		long companyId, long assetEntryId, long assetCategoryId) {

		long entryId = counterLocalService.increment();

		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel =
			assetEntryAssetCategoryRelPersistence.create(entryId);

		assetEntryAssetCategoryRel.setCompanyId(companyId);
		assetEntryAssetCategoryRel.setAssetEntryId(assetEntryId);
		assetEntryAssetCategoryRel.setAssetCategoryId(assetCategoryId);

		return assetEntryAssetCategoryRel;
	}

	@Override
	public long[] getAssetCategoryIdsByAssetEntryId(long assetEntryId) {
		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			assetEntryAssetCategoryRelPersistence.findByAssetEntryId(
				assetEntryId);

		return ListUtil.toLongArray(
			assetEntryAssetCategoryRels,
			AssetEntryAssetCategoryRel.ASSET_CATEGORY_ID_ACCESSOR);
	}

	@Override
	public long[] getAssetEntryIdsByAssetCategoryId(long assetCategoryId) {
		List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
			assetEntryAssetCategoryRelPersistence.findByAssetCategoryId(
				assetCategoryId);

		return ListUtil.toLongArray(
			assetEntryAssetCategoryRels,
			AssetEntryAssetCategoryRel.ASSET_ENTRY_ID_ACCESSOR);
	}

}