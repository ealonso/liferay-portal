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

package com.liferay.asset.browser.web.internal.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Pavel Savinov
 */
public class AssetEntryOrderByComparator extends OrderByComparator<AssetEntry> {

	public AssetEntryOrderByComparator(String orderByCol, String orderByType) {
		_orderByCol = orderByCol;
		_orderByType = orderByType;
	}

	@Override
	public int compare(AssetEntry assetEntry1, AssetEntry assetEntry2) {
		if (_orderByType.equals("desc")) {
			AssetEntry swapEntry = assetEntry1;
			assetEntry1 = assetEntry2;
			assetEntry2 = swapEntry;
		}

		if (_orderByCol.equals("title")) {
			return assetEntry1.getTitle().compareTo(assetEntry2.getTitle());
		}
		else if (_orderByCol.equals("modified-date")) {
			return assetEntry2.getModifiedDate().compareTo(
				assetEntry1.getModifiedDate());
		}

		return 0;
	}

	private final String _orderByCol;
	private final String _orderByType;

}