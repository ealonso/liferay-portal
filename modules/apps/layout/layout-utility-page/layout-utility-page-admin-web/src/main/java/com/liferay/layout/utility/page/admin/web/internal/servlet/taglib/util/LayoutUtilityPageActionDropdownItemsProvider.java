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

package com.liferay.layout.utility.page.admin.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class LayoutUtilityPageActionDropdownItemsProvider {

	public LayoutUtilityPageActionDropdownItemsProvider(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getActionDropdownItems(
		LayoutUtilityPageEntry layoutUtilityPageEntry) {

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						dropdownItem -> {
							dropdownItem.setHref(
								PortletURLBuilder.createRenderURL(
									_renderResponse
								).setMVCPath(
									"/edit_layout_utility_page.jsp"
								).setParameter(
									"layoutUtilityPageEntryId",
									layoutUtilityPageEntry.
										getLayoutUtilityPageEntryId()
								).buildString());
							dropdownItem.setIcon("pencil");
							dropdownItem.setLabel(
								LanguageUtil.get(_httpServletRequest, "edit"));
						}
					).add(
						() ->
							!layoutUtilityPageEntry.
								isDefaultLayoutUtilityPageEntry(),
						dropdownItem -> {
							dropdownItem.setHref(
								PortletURLBuilder.createActionURL(
									_renderResponse
								).setActionName(
									"/layout_utility_pages/" +
										"set_default_layout_utility_page_entry"
								).setParameter(
									"layoutUtilityPageEntryId",
									layoutUtilityPageEntry.
										getLayoutUtilityPageEntryId()
								).buildString());
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "set-as-default"));
						}
					).build());
				dropdownGroupItem.setSeparator(true);
			}
		).build();
	}

	private final HttpServletRequest _httpServletRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}