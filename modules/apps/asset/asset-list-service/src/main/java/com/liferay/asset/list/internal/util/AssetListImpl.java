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

package com.liferay.asset.list.internal.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntryAssetEntryRel;
import com.liferay.asset.list.provider.AssetListProvider;
import com.liferay.asset.list.provider.AssetListProviderTracker;
import com.liferay.asset.list.service.AssetListEntryAssetEntryRelLocalService;
import com.liferay.asset.list.util.AssetListUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = AssetListUtil.class)
public class AssetListImpl implements AssetListUtil {

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, HttpServletRequest request) {

		return getAssetEntries(
			assetListEntry, request, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<AssetEntry> getAssetEntries(
		AssetListEntry assetListEntry, HttpServletRequest request, int start,
		int end) {

		if (Objects.equals(
				assetListEntry.getType(),
				AssetListEntryTypeConstants.TYPE_ASSET_LIST_PROVIDER)) {

			return _getAssetListProviderAssetEntries(
				assetListEntry, request, start, end);
		}
		else if (Objects.equals(
					assetListEntry.getType(),
					AssetListEntryTypeConstants.TYPE_MANUAL)) {

			return _getManualAssetEntries(assetListEntry, start, end);
		}

		return _getDynamicAssetEntries(assetListEntry, start, end);
	}

	@Override
	public int getAssetEntriesCount(
		AssetListEntry assetListEntry, HttpServletRequest request) {

		if (Objects.equals(
				assetListEntry.getType(),
				AssetListEntryTypeConstants.TYPE_ASSET_LIST_PROVIDER)) {

			return _getAssetListProviderAssetEntriesCount(
				assetListEntry, request);
		}
		else if (Objects.equals(
					assetListEntry.getType(),
					AssetListEntryTypeConstants.TYPE_MANUAL)) {

			return _getManualAssetEntriesCount(assetListEntry);
		}

		return _getDynamicAssetEntriesCount(assetListEntry);
	}

	@Override
	public List<AssetEntry> getManualAssetEntries(
		AssetListEntry assetListEntry) {

		return _getManualAssetEntries(
			assetListEntry, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	private List<AssetEntry> _getAssetListProviderAssetEntries(
		AssetListEntry assetListEntry, HttpServletRequest request, int start,
		int end) {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.fastLoad(assetListEntry.getTypeSettings());

		AssetListProvider assetListProvider =
			_assetListProviderTracker.getAssetListProvider(
				typeSettingsProperties.get("assetListProviderClassName"));

		if (assetListProvider == null) {
			return Collections.emptyList();
		}

		return assetListProvider.getAssetEntries(
			assetListEntry.getGroupId(), request, start, end);
	}

	private int _getAssetListProviderAssetEntriesCount(
		AssetListEntry assetListEntry, HttpServletRequest request) {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties();

		typeSettingsProperties.fastLoad(assetListEntry.getTypeSettings());

		AssetListProvider assetListProvider =
			_assetListProviderTracker.getAssetListProvider(
				typeSettingsProperties.get("assetListProviderClassName"));

		return assetListProvider.getAssetEntriesCount(
			assetListEntry.getGroupId(), request);
	}

	private List<AssetEntry> _getDynamicAssetEntries(
		AssetListEntry assetListEntry, int start, int end) {

		AssetEntryQuery assetEntryQuery = assetListEntry.getAssetEntryQuery();

		assetEntryQuery.setEnd(end);
		assetEntryQuery.setStart(start);

		return _assetEntryLocalService.getEntries(assetEntryQuery);
	}

	private int _getDynamicAssetEntriesCount(AssetListEntry assetListEntry) {
		return _assetEntryLocalService.getEntriesCount(
			assetListEntry.getAssetEntryQuery());
	}

	private List<AssetEntry> _getManualAssetEntries(
		AssetListEntry assetListEntry, int start, int end) {

		List<AssetListEntryAssetEntryRel> assetListEntryAssetEntryRels =
			_assetListEntryAssetEntryRelLocalService.
				getAssetListEntryAssetEntryRels(
					assetListEntry.getAssetListEntryId(), start, end);

		Stream<AssetListEntryAssetEntryRel> stream =
			assetListEntryAssetEntryRels.stream();

		return stream.map(
			assetListEntryAssetEntryRel -> _assetEntryLocalService.fetchEntry(
				assetListEntryAssetEntryRel.getAssetEntryId())
		).collect(
			Collectors.toList()
		);
	}

	private int _getManualAssetEntriesCount(AssetListEntry assetListEntry) {
		return _assetListEntryAssetEntryRelLocalService.
			getAssetListEntryAssetEntryRelsCount(
				assetListEntry.getAssetListEntryId());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetListEntryAssetEntryRelLocalService
		_assetListEntryAssetEntryRelLocalService;

	@Reference
	private AssetListProviderTracker _assetListProviderTracker;

}