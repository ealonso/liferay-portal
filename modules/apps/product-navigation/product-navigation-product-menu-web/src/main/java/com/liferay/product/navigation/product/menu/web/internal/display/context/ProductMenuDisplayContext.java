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

package com.liferay.product.navigation.product.menu.web.internal.display.context;

import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Julio Camarero
 */
public class ProductMenuDisplayContext {

	public ProductMenuDisplayContext(PortletRequest portletRequest) {
		_panelCategoryHelper = (PanelCategoryHelper)portletRequest.getAttribute(
			ApplicationListWebKeys.PANEL_CATEGORY_HELPER);
		_panelCategoryRegistry =
			(PanelCategoryRegistry)portletRequest.getAttribute(
				ApplicationListWebKeys.PANEL_CATEGORY_REGISTRY);
		_themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<PanelCategory> getChildPanelCategories() {
		if (_childPanelCategories != null) {
			return _childPanelCategories;
		}

		_childPanelCategories = _panelCategoryRegistry.getChildPanelCategories(
			PanelCategoryKeys.ROOT, _themeDisplay.getPermissionChecker(),
			_themeDisplay.getScopeGroup());

		return _childPanelCategories;
	}

	public int getNotificationsCount(PanelCategory panelCategory) {
		return _panelCategoryHelper.getNotificationsCount(
			panelCategory.getKey(), _themeDisplay.getPermissionChecker(),
			_themeDisplay.getScopeGroup(), _themeDisplay.getUser());
	}

	private List<PanelCategory> _childPanelCategories;
	private final PanelCategoryHelper _panelCategoryHelper;
	private final PanelCategoryRegistry _panelCategoryRegistry;
	private final ThemeDisplay _themeDisplay;

}