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

package com.liferay.layout.admin.web.internal.servlet.taglib.util;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletRequest;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Diego Hu
 */
public class CustomizationSettingsActionDropdownItemsProvider {

	public CustomizationSettingsActionDropdownItemsProvider(
		RenderRequest renderRequest) {

		_httpServletRequest = PortalUtil.getHttpServletRequest(
			renderRequest);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getActionDropdownItems() throws Exception {
		LayoutTypePortlet layoutTypePortlet =
			_themeDisplay.getLayoutTypePortlet();

		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.putData("action", "toggleCustomizedViewMessage");
				dropdownItem.putData(
					"toggleCustomizedViewMessageURL",
					PortletURLBuilder.create(
						PortletURLFactoryUtil.create(
							_httpServletRequest,
							LayoutAdminPortletKeys.GROUP_PAGES,
							PortletRequest.ACTION_PHASE)
					).setActionName(
						"/layout_admin/toggle_customized_view"
					).setParameter(
						"customized_view",
						!layoutTypePortlet.isCustomizedView()
					).buildString());
				dropdownItem.setLabel(_getCustomizedViewMessage());
			}
		).build();
	}

	private String _getCustomizedViewMessage() {
		LayoutTypePortlet layoutTypePortlet =
			_themeDisplay.getLayoutTypePortlet();

		if (!layoutTypePortlet.isCustomizedView()) {
			return LanguageUtil.get(
				_httpServletRequest, "view-my-customized-page");
		}
		else if (layoutTypePortlet.isDefaultUpdated()) {
			return LanguageUtil.get(
				_httpServletRequest,
				"the-defaults-for-the-current-page-have-been-updated-click-" +
					"here-to-see-them");
		}

		return LanguageUtil.get(
			_httpServletRequest, "view-page-without-my-customizations");
	}

	private final HttpServletRequest _httpServletRequest;
	private final ThemeDisplay _themeDisplay;

}