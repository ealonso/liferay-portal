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

import com.liferay.application.list.display.context.logic.PanelCategoryHelper;
import com.liferay.frontend.taglib.clay.internal.servlet.ServletContextUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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

		PanelCategoryHelper panelCategoryHelper = new PanelCategoryHelper(
			ServletContextUtil.getPanelAppRegistry(),
			ServletContextUtil.getPanelCategoryRegistry());

		if (panelCategoryHelper.isGlobalMenuApp(ppid)) {
			return true;
		}

		return false;
	}

}