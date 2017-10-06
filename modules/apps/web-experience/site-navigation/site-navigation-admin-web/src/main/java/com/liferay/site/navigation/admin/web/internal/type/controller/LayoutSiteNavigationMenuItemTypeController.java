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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.admin.web.internal.constants.SiteNavigationAdminPortletKeys;
import com.liferay.site.navigation.constants.SiteNavigationMenuItemTypeControllerConstants;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.type.controller.SiteNavigationMenuItemTypeController;
import com.liferay.site.navigation.type.controller.impl.BaseSiteNavigationMenuItemTypeControllerImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;

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
	property = {"site.navigation.menu.item.type=" + SiteNavigationMenuItemTypeControllerConstants.LAYOUT},
	service = SiteNavigationMenuItemTypeController.class
)
public class LayoutSiteNavigationMenuItemTypeController
	extends BaseSiteNavigationMenuItemTypeControllerImpl {

	@Override
	public JSONObject getEditContext(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		JSONObject context = JSONFactoryUtil.createJSONObject();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		LiferayPortletURL loadMoreURL = PortletURLFactoryUtil.create(
			request, SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN,
			themeDisplay.getPlid(), ResourceRequest.RESOURCE_PHASE);

		boolean privateLayout = false;

		loadMoreURL.setResourceID("/navigation_menu/get_layouts");
		loadMoreURL.setParameter(
			"groupId", String.valueOf(themeDisplay.getScopeGroupId()));
		loadMoreURL.setParameter(
			"privateLayout", String.valueOf(privateLayout));

		context.put("followURLOnTitleClick", false);
		context.put("itemSelectorSaveEvent", "selectPage");
		context.put("loadMoreURL", loadMoreURL.toString());
		context.put("multiSelection", false);
		context.put("namespace", portletDisplay.getNamespace());
		context.put(
			"nodes",
			_getLayoutsJSONArray(
				themeDisplay, themeDisplay.getScopeGroupId(), privateLayout,
				0));
		context.put("pathThemeImages", themeDisplay.getPathThemeImages());

		jsonObject.put("context", context);
		jsonObject.put("displayStyle", "tree");

		return jsonObject;
	}

	@Override
	public String getIcon() {
		return "page";
	}

	public String getLayoutBreadcrumb(
		ThemeDisplay themeDisplay, Layout layout) {

		Locale locale = themeDisplay.getLocale();

		List<Layout> ancestors = null;

		try {
			ancestors = layout.getAncestors();
		}
		catch (PortalException pe) {
			_log.error(pe, pe.getCause());

			return layout.getName();
		}

		StringBundler sb = new StringBundler(4 * ancestors.size());

		Collections.reverse(ancestors);

		for (Layout ancestor : ancestors) {
			sb.append(HtmlUtil.escape(ancestor.getName(locale)));

			sb.append(StringPool.SPACE);
			sb.append(StringPool.SLASH);
			sb.append(StringPool.SPACE);
		}

		sb.append(HtmlUtil.escape(layout.getName(locale)));

		return sb.toString();
	}

	@Override
	public String getURL(
			HttpServletRequest request, HttpServletResponse response,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		UnicodeProperties properties = getTypeSettingsProperties(
			siteNavigationMenuItem);

		long plid = GetterUtil.getLong(properties.getProperty("plid"));

		Layout layout = _layoutLocalService.getLayout(plid);

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.site.navigation.admin.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private JSONArray _getLayoutsJSONArray(
		ThemeDisplay themeDisplay, long groupId, boolean privateLayout,
		long parentLayoutId) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			groupId, privateLayout, parentLayoutId, false, 0, 10);

		List<JSONObject> layoutJSONObjects = new ArrayList<>();

		for (Layout layout : layouts) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("icon", "page");
			jsonObject.put("id", layout.getPlid());
			jsonObject.put("layoutId", layout.getLayoutId());
			jsonObject.put("name", getLayoutBreadcrumb(themeDisplay, layout));
			jsonObject.put("value", layout.getName(themeDisplay.getLocale()));

			JSONArray children = _getLayoutsJSONArray(
				themeDisplay, groupId, privateLayout, layout.getLayoutId());

			layoutJSONObjects.add(jsonObject);

			Iterator iterator = children.iterator();

			while (iterator.hasNext()) {
				Object child = iterator.next();

				try {
					layoutJSONObjects.add(
						JSONFactoryUtil.createJSONObject(child.toString()));
				}
				catch (Exception e) {
				}
			}
		}

		Comparator<JSONObject> comparator = (object1, object2) -> {
			String name1 = object1.getString("name");
			String name2 = object2.getString("name");

			return name1.compareTo(name2);
		};

		Collections.sort(layoutJSONObjects, comparator);

		for (JSONObject object : layoutJSONObjects) {
			jsonArray.put(object);
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutSiteNavigationMenuItemTypeController.class);

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}