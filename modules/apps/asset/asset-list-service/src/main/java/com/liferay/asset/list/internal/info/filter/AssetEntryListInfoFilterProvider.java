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

package com.liferay.asset.list.internal.info.filter;

import com.liferay.asset.list.info.filter.AssetEntryListInfoFilter;
import com.liferay.info.collection.filter.type.CategoriesInfoCollectionFilterType;
import com.liferay.info.collection.filter.type.InfoCollectionFilterType;
import com.liferay.info.collection.filter.type.KeywordsInfoCollectionFilterType;
import com.liferay.info.filter.InfoFilterProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = InfoFilterProvider.class)
public class AssetEntryListInfoFilterProvider
	implements InfoFilterProvider<AssetEntryListInfoFilter> {

	@Override
	public AssetEntryListInfoFilter create(Map<String, String[]> values) {
		AssetEntryListInfoFilter assetEntryListInfoFilter =
			new AssetEntryListInfoFilter();

		assetEntryListInfoFilter.setAssetCategoryIds(
			_getAssetCategoryIds(values));
		assetEntryListInfoFilter.setKeywords(_getKeywords(values));

		return assetEntryListInfoFilter;
	}

	@Override
	public List<InfoCollectionFilterType>
		getSupportedInfoCollectionFilterTypes() {

		return _supportedInfoCollectionFilterTypes;
	}

	private long[][] _getAssetCategoryIds(Map<String, String[]> values) {
		Set<long[]> assetCategoryIdsSet = new HashSet<>();

		for (Map.Entry<String, String[]> entry : values.entrySet()) {
			if (!StringUtil.startsWith(
					entry.getKey(),
					CategoriesInfoCollectionFilterType.FILTER_TYPE +
						StringPool.UNDERLINE)) {

				continue;
			}

			assetCategoryIdsSet.add(
				ArrayUtil.filter(
					GetterUtil.getLongValues(entry.getValue()),
					categoryId -> categoryId != 0));
		}

		return assetCategoryIdsSet.toArray(
			new long[assetCategoryIdsSet.size()][]);
	}

	private String _getKeywords(Map<String, String[]> values) {
		Set<String> keywordsSet = new HashSet<>();

		for (Map.Entry<String, String[]> entry : values.entrySet()) {
			if (!StringUtil.startsWith(
					entry.getKey(),
					KeywordsInfoCollectionFilterType.FILTER_TYPE +
						StringPool.UNDERLINE)) {

				continue;
			}

			Collections.addAll(keywordsSet, entry.getValue());
		}

		return StringUtil.merge(keywordsSet, StringPool.SPACE);
	}

	private static final List<InfoCollectionFilterType>
		_supportedInfoCollectionFilterTypes = Arrays.asList(
			new CategoriesInfoCollectionFilterType(),
			new KeywordsInfoCollectionFilterType());

}