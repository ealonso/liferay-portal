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

package com.liferay.asset.categories.internal.search;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Rubén Pulido
 */
@Component(
	immediate = true, service = AssetEntryAssetCategoriesBatchReindexer.class
)
public class AssetEntryAssetCategoriesBatchReindexer {

	public void reindex(AssetVocabulary assetVocabulary)
		throws PortalException {

		if (assetVocabulary == null) {
			return;
		}

		List<AssetCategory> assetCategories =
			_assetCategoryLocalService.getVocabularyCategories(
				assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Stream<AssetCategory> assetCategoriesStream = assetCategories.stream();

		long[] assetCategoryIds = assetCategoriesStream.mapToLong(
			AssetCategory::getCategoryId
		).toArray();

		Set<AssetEntry> assetEntries = _getAssetEntriesByAssetCategoryIds(
			assetCategoryIds);

		_assetEntryLocalService.reindex(ListUtil.fromCollection(assetEntries));
	}

	private Set<AssetEntry> _getAssetEntriesByAssetCategoryIds(
		long[] assetCategoryIds) {

		Set<AssetEntry> assetEntries = new HashSet<>();

		for (long assetCategoryId : assetCategoryIds) {
			List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
				_assetEntryAssetCategoryRelLocalService.
					getAssetEntryAssetCategoryRelsByAssetCategoryId(
						assetCategoryId);

			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel :
					assetEntryAssetCategoryRels) {

				AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
					assetEntryAssetCategoryRel.getAssetEntryId());

				if (assetEntry != null) {
					assetEntries.add(assetEntry);
				}
			}
		}

		return assetEntries;
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryAssetCategoryRelLocalService
		_assetEntryAssetCategoryRelLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}