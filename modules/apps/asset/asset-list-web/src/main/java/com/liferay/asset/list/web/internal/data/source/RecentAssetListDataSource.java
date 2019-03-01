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

package com.liferay.asset.list.web.internal.data.source;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.asset.list.provider.AssetListProvider;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = AssetListProvider.class)
public class RecentAssetListDataSource implements AssetListProvider {

	@Override
	public List<AssetEntry> getAssetEntries(PortletRequest portletRequest) {
		return Collections.emptyList();
	}

	@Override
	public List<AssetEntry> getAssetEntries(
		PortletRequest portletRequest, int start, int end) {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetTag tag = _assetTagLocalService.fetchTag(
			themeDisplay.getScopeGroupId(), "madrid");

		return AssetEntryLocalServiceUtil.getAssetTagAssetEntries(
			tag.getTagId());
	}

	@Override
	public int getAssetEntriesCount(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AssetTag tag = _assetTagLocalService.fetchTag(
			themeDisplay.getScopeGroupId(), "madrid");

		return AssetEntryLocalServiceUtil.getAssetTagAssetEntriesCount(
			tag.getTagId());
	}

	@Override
	public String getLabel(Locale locale) {
		return "recent";
	}

	@Reference
	private AssetTagLocalService _assetTagLocalService;

}