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

package com.liferay.asset.categories.admin.web.internal.servlet.taglib.ui;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.frontend.taglib.servlet.taglib.BaseJSPScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = {"screen.navigation.entry.order:Integer=20"},
	service = ScreenNavigationEntry.class
)
public class Permissions2ScreenNavigationEntry
	extends BaseJSPScreenNavigationEntry<AssetCategory> {

	@Override
	public String getEntryKey() {
		return "permissions";
	}

	@Override
	public String getCategoryKey() {
		return "advanced";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "permissions");
	}

	@Override
	public String getScreenNavigationKey() {
		return "edit.category.screen";
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.categories.admin.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	@Override
	protected String getJspPath() {
		return "/screen_category/principal.jsp";
	}

}