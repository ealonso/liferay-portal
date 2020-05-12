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

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.frontend.taglib.clay.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chema Balsas
 */
public class NavigationBarTag extends BaseClayTag {

	@Override
	public int doStartTag() {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		setComponentBaseName("ClayNavigationBar");
		setHydrate(true);
		setInverted(!_isGlobalMenuApp(themeDisplay.getPpid()));
		setModuleBaseName("navigation-bar");

		return super.doStartTag();
	}

	public void setInverted(Boolean inverted) {
		putValue("inverted", inverted);
	}

	public void setNavigationItems(List<NavigationItem> navigationItems) {
		putValue("items", navigationItems);
	}

	private boolean _isGlobalMenuApp(String ppid) {
		if (Validator.isNull(ppid)) {
			return false;
		}

		Portlet portlet = PortletLocalServiceUtil.getPortletById(ppid);

		if (portlet == null) {
			return false;
		}

		String controlPanelEntryCategory =
			portlet.getControlPanelEntryCategory();

		if (Validator.isNull(controlPanelEntryCategory)) {
			return false;
		}

		PanelCategoryRegistry panelCategoryRegistry =
			ServletContextUtil.getPanelCategoryRegistry();

		List<PanelCategory> panelCategories = new ArrayList<>();

		for (PanelCategory panelCategory :
				panelCategoryRegistry.getChildPanelCategories(
					PanelCategoryKeys.GLOBAL_MENU)) {

			panelCategories.addAll(
				panelCategoryRegistry.getChildPanelCategories(panelCategory));
		}

		if (ListUtil.isEmpty(panelCategories)) {
			return false;
		}

		if (panelCategories.contains(
				panelCategoryRegistry.getPanelCategory(
					controlPanelEntryCategory))) {

			return true;
		}

		return false;
	}

}