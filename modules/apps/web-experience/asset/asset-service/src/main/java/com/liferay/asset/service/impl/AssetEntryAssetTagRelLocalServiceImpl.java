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
import com.liferay.asset.model.AssetEntryAssetTagRel;
import com.liferay.asset.service.base.AssetEntryAssetTagRelLocalServiceBaseImpl;
import com.liferay.asset.tags.model.AssetTag;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryAssetTagRelLocalServiceImpl
	extends AssetEntryAssetTagRelLocalServiceBaseImpl {

	@Override
	public void addAssetEntryAssetTagRel(
		AssetEntry assetEntry, List<AssetTag> assetTags) {

		for (AssetTag assetTag : assetTags) {
			addAssetEntryAssetTagRel(
				assetEntry.getCompanyId(), assetEntry.getEntryId(),
				assetTag.getTagId());
		}
	}

	@Override
	public void addAssetEntryAssetTagRel(long assetEntryId, long assetTagId) {
		AssetEntry assetEntry = assetEntryLocalService.fetchEntry(assetEntryId);

		addAssetEntryAssetTagRel(
			assetEntry.getCompanyId(), assetEntry.getEntryId(), assetTagId);
	}

	@Override
	public AssetEntryAssetTagRel addAssetEntryAssetTagRel(
		long companyId, long assetEntryId, long assetTagId) {

		long entryId = counterLocalService.increment();

		AssetEntryAssetTagRel assetEntryAssetTagRel =
			assetEntryAssetTagRelPersistence.create(entryId);

		assetEntryAssetTagRel.setCompanyId(companyId);
		assetEntryAssetTagRel.setAssetEntryId(assetEntryId);
		assetEntryAssetTagRel.setAssetTagId(assetTagId);

		return assetEntryAssetTagRel;
	}

	@Override
	public long[] getAssetEntryIdsByAssetTagId(long assetTagId) {
		List<AssetEntryAssetTagRel> assetEntryAssetTagRels =
			assetEntryAssetTagRelPersistence.findByAssetTagId(assetTagId);

		return ListUtil.toLongArray(
			assetEntryAssetTagRels,
			AssetEntryAssetTagRel.ASSET_ENTRY_ID_ACCESSOR);
	}

	@Override
	public long[] getAssetTagIdsByAssetEntryId(long assetEntryId) {
		List<AssetEntryAssetTagRel> assetEntryAssetTagRels =
			assetEntryAssetTagRelPersistence.findByAssetEntryId(assetEntryId);

		return ListUtil.toLongArray(
			assetEntryAssetTagRels,
			AssetEntryAssetTagRel.ASSET_TAG_ID_ACCESSOR);
	}

}