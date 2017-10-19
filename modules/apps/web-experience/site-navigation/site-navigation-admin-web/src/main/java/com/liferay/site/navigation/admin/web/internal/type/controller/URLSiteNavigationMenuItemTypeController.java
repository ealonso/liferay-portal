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

package com.liferay.site.navigation.admin.web.internal.type.controller;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import com.liferay.site.navigation.type.controller.SiteNavigationMenuItemTypeController;
import com.liferay.site.navigation.type.controller.SiteNavigationMenuItemTypeControllerTracker;
import com.liferay.site.navigation.type.controller.impl.BaseSiteNavigationMenuItemTypeControllerImpl;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true, property = {"site.navigation.menu.item.type=url"},
	service = SiteNavigationMenuItemTypeController.class
)
public class URLSiteNavigationMenuItemTypeController
	extends BaseSiteNavigationMenuItemTypeControllerImpl {

	@Override
	public JSONObject getEditContext(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		JSONObject context = JSONFactoryUtil.createJSONObject();

		context.put("fields", _getFieldsJSONArray(themeDisplay));
		context.put("icon", getIcon());
		context.put("namespace", portletDisplay.getNamespace());
		context.put("type", getType());

		jsonObject.put("context", context);

		jsonObject.put("displayStyle", "fieldset");

		return jsonObject;
	}

	@Override
	public String getIcon() {
		return "link";
	}

	@Override
	public String getType() {
		return "url";
	}

	@Override
	public String getURL(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		UnicodeProperties properties = getTypeSettingsProperties(
			siteNavigationMenuItem);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(
			properties.getProperty("value"));

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		return jsonObject.getString("value");
	}

	@Override
	public JSONObject getViewContext(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		JSONObject jsonObject = _getSiteNavigationMenuItemJSONObject(
			request, response, siteNavigationMenuItem);

		return jsonObject;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.site.navigation.admin.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private JSONArray _getFieldsJSONArray(ThemeDisplay themeDisplay) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		ResourceBundle siteNavigationMenuItemTypeResourceBundle =
			ResourceBundleUtil.getBundle(
				"content.Language", themeDisplay.getLocale(), getClass());

		jsonObject.put("description", "http://www.liferay.com");
		jsonObject.put(
			"label",
			LanguageUtil.get(siteNavigationMenuItemTypeResourceBundle, "url"));
		jsonObject.put("name", "url");
		jsonObject.put("value", "");

		jsonArray.put(jsonObject);

		return jsonArray;
	}

	private JSONObject _getSiteNavigationMenuItemJSONObject(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		ResourceBundle siteNavigationMenuItemTypeResourceBundle =
			ResourceBundleUtil.getBundle(
				"content.Language", themeDisplay.getLocale(), getClass());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		UnicodeProperties properties = getTypeSettingsProperties(
			siteNavigationMenuItem);

		JSONArray value = JSONFactoryUtil.createJSONArray(
			properties.getProperty("value"));

		jsonObject.put(
			"children",
			_getSiteNavigationMenuItemsJSONArray(
				request, response, siteNavigationMenuItem));

		jsonObject.put("icon", getIcon());
		jsonObject.put("id", properties.getProperty("id"));
		jsonObject.put("name", properties.getProperty("name"));
		jsonObject.put("type", getType());
		jsonObject.put(
			"typeLabel",
			LanguageUtil.get(
				siteNavigationMenuItemTypeResourceBundle,
				"site.navigation.menu.item.types." + getType()));
		jsonObject.put("value", value);

		return jsonObject;
	}

	private JSONArray _getSiteNavigationMenuItemsJSONArray(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<SiteNavigationMenuItem> siteNavigationMenuItems =
			_siteNavigationMenuItemLocalService.
				getSiteNavigationMenuItemsByParent(
					siteNavigationMenuItem.getSiteNavigationMenuItemId());

		for (SiteNavigationMenuItem childSiteNavigationMenuItem :
				siteNavigationMenuItems) {

			SiteNavigationMenuItemTypeController
				siteNavigationMenuItemTypeController =
					_siteNavigationMenuItemTypeControllerTracker.
						getSiteNavigationMenuItemTypeController(
							childSiteNavigationMenuItem.getType());

			jsonArray.put(
				siteNavigationMenuItemTypeController.getViewContext(
					request, response, childSiteNavigationMenuItem));
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		URLSiteNavigationMenuItemTypeController.class);

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private SiteNavigationMenuItemLocalService
		_siteNavigationMenuItemLocalService;

	@Reference
	private SiteNavigationMenuItemTypeControllerTracker
		_siteNavigationMenuItemTypeControllerTracker;

}