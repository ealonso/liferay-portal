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

package com.liferay.layout.admin.web.internal.control.menu;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.TOOLS,
		"product.navigation.control.menu.entry.order:Integer=400"
	},
	service = {
		LayoutEditModeProductNavigationControlMenuEntry.class,
		ProductNavigationControlMenuEntry.class
	}
)
public class LayoutEditModeProductNavigationControlMenuEntry
	extends BaseJSPProductNavigationControlMenuEntry {

	@Override
	public String getBodyJspPath() {
		return "/control/menu/edit_mode.jsp";
	}

	@Override
	public String getIconJspPath() {
		return StringPool.BLANK;
	}

	@Override
	public boolean includeBody(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		long selPlid = ParamUtil.getLong(request, "p_r_p_selPlid");

		Layout selLayout = _layoutLocalService.fetchLayout(selPlid);

		request.setAttribute(WebKeys.SEL_LAYOUT, selLayout);

		return super.includeBody(request, response);
	}

	@Override
	public boolean isShow(HttpServletRequest request) throws PortalException {
		String layoutMode = ParamUtil.getString(
			request, "p_l_mode", Constants.VIEW);

		return layoutMode.equals(Constants.EDIT);
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutEditModeProductNavigationControlMenuEntry.class);

	@Reference
	private LayoutLocalService _layoutLocalService;

}