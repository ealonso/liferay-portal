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

package com.liferay.modern.site.building.page.web.internal.util;

import com.liferay.modern.site.building.page.web.constants.PagesPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class PagesPortletUtil {

	public static JSONObject getActionsJSONObject(
			Layout layout, HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		boolean privateLayout = layout.isPrivateLayout();

		String portletId = PortletProviderUtil.getPortletId(
			Layout.class.getName(), PortletProvider.Action.EDIT);

		PortletURL redirectURL = PortalUtil.getControlPanelPortletURL(
			request, PagesPortletKeys.PAGES, PortletRequest.RENDER_PHASE);

		redirectURL.setParameter(
			"groupId", String.valueOf(layout.getGroupId()));
		redirectURL.setParameter(
			"privateLayout", String.valueOf(layout.isPrivateLayout()));

		if (LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.ADD_LAYOUT)) {

			JSONObject addChildPageJSONObject =
				JSONFactoryUtil.createJSONObject();

			addChildPageJSONObject.put(
				"label",
				LanguageUtil.get(themeDisplay.getLocale(), "add-child-page"));

			PortletURL addLayoutURL = PortalUtil.getControlPanelPortletURL(
				request, portletId, PortletRequest.RENDER_PHASE);

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getLayoutId()));

			addLayoutURL.setParameter("mvcPath", "/add_layout.jsp");
			addLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			addLayoutURL.setParameter(
				"privateLayout", String.valueOf(privateLayout));
			addLayoutURL.setParameter("redirect", redirectURL.toString());
			addLayoutURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));

			addChildPageJSONObject.put("url", addLayoutURL.toString());

			jsonObject.put("add", addChildPageJSONObject);
		}

		if (LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.DELETE)) {

			JSONObject deletePageJSONObject =
				JSONFactoryUtil.createJSONObject();

			deletePageJSONObject.put(
				"confirmMessage",
				LanguageUtil.get(
					request,
					"are-you-sure-you-want-to-delete-the-selected-page"));

			deletePageJSONObject.put(
				"label", LanguageUtil.get(themeDisplay.getLocale(), "delete"));

			PortletURL deleteLayoutURL = PortalUtil.getControlPanelPortletURL(
				request, portletId, PortletRequest.ACTION_PHASE);

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getParentLayoutId()));

			deleteLayoutURL.setParameter(
				ActionRequest.ACTION_NAME, "deleteLayout");
			deleteLayoutURL.setParameter("mvcPath", "/edit_layout.jsp");
			deleteLayoutURL.setParameter("redirect", redirectURL.toString());
			deleteLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			deleteLayoutURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));
			deleteLayoutURL.setParameter(
				"privateLayout", String.valueOf(privateLayout));

			deletePageJSONObject.put("url", deleteLayoutURL.toString());

			jsonObject.put("delete", deletePageJSONObject);
		}

		if (LayoutPermissionUtil.contains(
				permissionChecker, layout, ActionKeys.UPDATE)) {

			JSONObject configurePageJSONObject =
				JSONFactoryUtil.createJSONObject();

			configurePageJSONObject.put(
				"label",
				LanguageUtil.get(themeDisplay.getLocale(), "configure"));

			PortletURL editLayoutURL = PortalUtil.getControlPanelPortletURL(
				request, portletId, PortletRequest.RENDER_PHASE);

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getLayoutId()));

			editLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			editLayoutURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));
			editLayoutURL.setParameter("redirect", redirectURL.toString());
			editLayoutURL.setParameter(
				"privateLayout", String.valueOf(privateLayout));

			configurePageJSONObject.put("url", editLayoutURL.toString());

			jsonObject.put("configure", configurePageJSONObject);
		}

		return jsonObject;
	}

}