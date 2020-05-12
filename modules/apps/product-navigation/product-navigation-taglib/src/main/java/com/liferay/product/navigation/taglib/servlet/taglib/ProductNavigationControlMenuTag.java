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

package com.liferay.product.navigation.taglib.servlet.taglib;

import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 * @author Eudaldo Alonso
 */
public class ProductNavigationControlMenuTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void includePage(
			String page, HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		String layoutMode = ParamUtil.getString(
			getOriginalServletRequest(), "p_l_mode", Constants.VIEW);

		if (layoutMode.equals(Constants.PREVIEW)) {
			return;
		}

		super.includePage(page, httpServletResponse);
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return false;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		httpServletRequest.setAttribute(
			"liferay-product-navigation:control-menu:globalMenuApp",
			_isGlobalMenuApp(themeDisplay.getPpid()));
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

	private static final String _PAGE = "/control_menu/page.jsp";

}