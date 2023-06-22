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

package com.liferay.asset.tags.item.selector.web.internal;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.item.selector.TableItemView;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.taglib.search.TextSearchEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Stefan Tanasie
 */
public class AssetTagsTableItemView implements TableItemView {

	public AssetTagsTableItemView(
		AssetTag assetTag, ThemeDisplay themeDisplay) {

		_assetTag = assetTag;
		_themeDisplay = themeDisplay;
	}

	@Override
	public List<String> getHeaderNames() {
		return ListUtil.fromArray("name", "site");
	}

	@Override
	public List<SearchEntry> getSearchEntries(Locale locale) {
		List<SearchEntry> searchEntries = new ArrayList<>();

		TextSearchEntry nameTextSearchEntry = new TextSearchEntry();

		nameTextSearchEntry.setName(HtmlUtil.escape(_assetTag.getName()));
		nameTextSearchEntry.setCssClass(
			"entry entry-selector table-cell-expand table-cell-minw-200");
		nameTextSearchEntry.setData(
			HashMapBuilder.<String, Object>put(
				"entityid", _assetTag.getTagId()
			).put(
				"entityname", _assetTag.getName()
			).build());

		searchEntries.add(nameTextSearchEntry);

		TextSearchEntry scopeTextSearchEntry = new TextSearchEntry();

		scopeTextSearchEntry.setName(
			LanguageUtil.get(
				locale, HtmlUtil.escape(_getGroupDescriptiveName())));
		scopeTextSearchEntry.setCssClass(
			"table-cell-expand-smaller table-cell-minw-150");

		searchEntries.add(scopeTextSearchEntry);

		return searchEntries;
	}

	private String _getGroupDescriptiveName() {
		try {
			Group group = GroupLocalServiceUtil.fetchGroup(
				_assetTag.getGroupId());

			return group.getDescriptiveName(_themeDisplay.getLocale());
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetTagsTableItemView.class);

	private final AssetTag _assetTag;
	private final ThemeDisplay _themeDisplay;

}