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

package com.liferay.asset.list.internal.list.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.petra.string.StringPool;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public class MostViewedAssetsInfoListProvider
	implements InfoListProvider<AssetEntry> {

	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext) {

		return Collections.emptyList();
	}

	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		Sort sort) {

		return Collections.emptyList();
	}

	public int getInfoListCount(
		InfoListProviderContext infoListProviderContext) {

		return 0;
	}

	public String getLabel(Locale locale) {
		return StringPool.BLANK;
	}


}