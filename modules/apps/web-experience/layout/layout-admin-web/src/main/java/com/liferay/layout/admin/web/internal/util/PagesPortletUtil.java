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

package com.liferay.layout.admin.web.internal.util;

import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;
import com.liferay.layout.util.comparator.LayoutCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class PagesPortletUtil {

	public static JSONObject getActionsJSONObject(
			Layout layout, PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String portletId = PortletProviderUtil.getPortletId(
			Layout.class.getName(), PortletProvider.Action.EDIT);

		PortletURL redirectURL = PortalUtil.getControlPanelPortletURL(
			portletRequest, LayoutAdminPortletKeys.GROUP_PAGES,
			PortletRequest.RENDER_PHASE);

		redirectURL.setParameter(
			"groupId", String.valueOf(layout.getGroupId()));
		redirectURL.setParameter("millerColumns", String.valueOf(Boolean.TRUE));
		redirectURL.setParameter(
			"privateLayout", String.valueOf(layout.isPrivateLayout()));

		if (LayoutPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), layout,
				ActionKeys.ADD_LAYOUT)) {

			JSONObject addChildPageJSONObject =
				JSONFactoryUtil.createJSONObject();

			addChildPageJSONObject.put(
				"label",
				LanguageUtil.get(themeDisplay.getLocale(), "add-child-page"));

			PortletURL addLayoutURL = PortalUtil.getControlPanelPortletURL(
				portletRequest, portletId, PortletRequest.RENDER_PHASE);

			addLayoutURL.setParameter("mvcPath", "/add_layout.jsp");
			addLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			addLayoutURL.setParameter(
				"privateLayout", String.valueOf(layout.isPrivateLayout()));

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getLayoutId()));

			addLayoutURL.setParameter("redirect", redirectURL.toString());
			addLayoutURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));

			addChildPageJSONObject.put("url", addLayoutURL.toString());

			jsonObject.put("add", addChildPageJSONObject);
		}

		if (LayoutPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), layout,
				ActionKeys.DELETE)) {

			JSONObject deletePageJSONObject =
				JSONFactoryUtil.createJSONObject();

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				portletRequest);

			deletePageJSONObject.put(
				"confirmMessage",
				LanguageUtil.get(
					request,
					"are-you-sure-you-want-to-delete-the-selected-page"));

			deletePageJSONObject.put(
				"label", LanguageUtil.get(themeDisplay.getLocale(), "delete"));

			PortletURL deleteLayoutURL = PortalUtil.getControlPanelPortletURL(
				portletRequest, portletId, PortletRequest.ACTION_PHASE);

			deleteLayoutURL.setParameter(
				ActionRequest.ACTION_NAME, "/layout/delete_layout");
			deleteLayoutURL.setParameter("mvcPath", "/edit_layout.jsp");

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getParentLayoutId()));

			deleteLayoutURL.setParameter("redirect", redirectURL.toString());
			deleteLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			deleteLayoutURL.setParameter(
				"layoutId", String.valueOf(layout.getLayoutId()));
			deleteLayoutURL.setParameter(
				"privateLayout", String.valueOf(layout.isPrivateLayout()));

			deletePageJSONObject.put("url", deleteLayoutURL.toString());

			jsonObject.put("delete", deletePageJSONObject);
		}

		if (LayoutPermissionUtil.contains(
				themeDisplay.getPermissionChecker(), layout,
				ActionKeys.UPDATE)) {

			JSONObject configurePageJSONObject =
				JSONFactoryUtil.createJSONObject();

			configurePageJSONObject.put(
				"label",
				LanguageUtil.get(themeDisplay.getLocale(), "configure"));

			PortletURL editLayoutURL = PortalUtil.getControlPanelPortletURL(
				portletRequest, portletId, PortletRequest.RENDER_PHASE);

			editLayoutURL.setParameter(
				"groupId", String.valueOf(layout.getGroupId()));
			editLayoutURL.setParameter(
				"selPlid", String.valueOf(layout.getPlid()));

			redirectURL.setParameter(
				"selectedLayoutId", String.valueOf(layout.getLayoutId()));

			editLayoutURL.setParameter("redirect", redirectURL.toString());
			editLayoutURL.setParameter(
				"privateLayout", String.valueOf(layout.isPrivateLayout()));

			configurePageJSONObject.put("url", editLayoutURL.toString());

			jsonObject.put("configure", configurePageJSONObject);
		}

		return jsonObject;
	}

	public static OrderByComparator<Layout> getLayoutOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<Layout> orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new LayoutCreateDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static JSONArray getLayoutsJSONArray(
			long groupId, boolean privateLayout, long parentLayoutId,
			long selectedLayoutId, OrderByComparator orderByComparator,
			PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, privateLayout, parentLayoutId, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, orderByComparator);

		for (Layout layout : layouts) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONObject actionsJSONObject = getActionsJSONObject(
				layout, portletRequest);

			if (actionsJSONObject.length() > 0) {
				jsonObject.put("actions", actionsJSONObject);
			}

			jsonObject.put("active", layout.getLayoutId() == selectedLayoutId);

			int childLayoutsCount = LayoutLocalServiceUtil.getLayoutsCount(
				themeDisplay.getScopeGroup(), privateLayout,
				layout.getLayoutId());

			jsonObject.put("hasChild", childLayoutsCount > 0);

			jsonObject.put("icon", "page");
			jsonObject.put("layoutId", layout.getLayoutId());
			jsonObject.put("parentLayoutId", layout.getParentLayoutId());
			jsonObject.put("selected", jsonObject.getBoolean("active"));
			jsonObject.put("title", layout.getName(themeDisplay.getLocale()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

}