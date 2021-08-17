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
import com.liferay.info.filter.InfoFilterProvider;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, service = InfoFilterProvider.class
)
public class AssetEntryListInfoRequestItemProvider
	implements InfoFilterProvider<AssetEntryListInfoFilter> {

	@Override
	public AssetEntryListInfoFilter create(Map<String, String[]> values) {
		AssetEntryListInfoFilter assetEntryListInfoFilter =
			new AssetEntryListInfoFilter();

		assetEntryListInfoFilter.setAssetCategoryIds(
			_getAssetCategoryIds(values));

		return assetEntryListInfoFilter;
	}

	private long[][] _getAssetCategoryIds(
		Map<String, String[]> values) {

		Set<long[]> assetCategoryIdsSet = new HashSet<>();

		for (String key: values.keySet()) {
			if (!key.contains("categoryId")) {
				continue;
			}

			assetCategoryIdsSet.add(GetterUtil.getLongValues(values.get(key)));
		}

		return assetCategoryIdsSet.toArray(
			new long[assetCategoryIdsSet.size()][]);
	}

}