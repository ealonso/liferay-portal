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

package com.liferay.modern.site.building.page.web.internal.display.context;

import com.liferay.modern.site.building.page.web.constants.PagesPortletKeys;
import com.liferay.modern.site.building.page.web.internal.util.PagesPortletUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class PagesDisplayContext {

	public PagesDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest request) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_request = request;
	}

	public JSONArray getBreadcrumbEntriesJSONArray() throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("index", 0);
		jsonObject.put("layoutId", -1);
		jsonObject.put("parentLayoutId", 0);
		jsonObject.put("title", LanguageUtil.get(_request, "home"));

		jsonArray.put(jsonObject);

		Layout selectedLayout = LayoutLocalServiceUtil.fetchLayout(
			getGroupId(), isPrivateLayout(), getSelectedLayoutId());

		if (selectedLayout == null) {
			return jsonArray;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Layout> ancestors = selectedLayout.getAncestors();

		Collections.reverse(ancestors);

		for (int i = 0; i < ancestors.size(); i++) {
			Layout ancestor = ancestors.get(i);

			JSONObject ancestorJSONObject = JSONFactoryUtil.createJSONObject();

			ancestorJSONObject.put("index", i);
			ancestorJSONObject.put("layoutId", ancestor.getLayoutId());
			ancestorJSONObject.put(
				"parentLayoutId", ancestor.getParentLayoutId());
			ancestorJSONObject.put(
				"title", ancestor.getName(themeDisplay.getLocale()));

			jsonArray.put(ancestorJSONObject);
		}

		return jsonArray;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		_displayStyle = portalPreferences.getValue(
			PagesPortletKeys.PAGES, "display-style", "icon");

		return _displayStyle;
	}

	public long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_groupId = ParamUtil.getLong(
			_request, "groupId", themeDisplay.getScopeGroupId());

		return _groupId;
	}

	public JSONArray getLayoutsJSONArray(boolean children) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		long parentLayoutId = getSelectedLayoutId();

		if (!children && (parentLayoutId > 0)) {
			Layout selectedLayout = LayoutLocalServiceUtil.getLayout(
				getGroupId(), isPrivateLayout(), getSelectedLayoutId());

			parentLayoutId = selectedLayout.getParentLayoutId();
		}
		else if (children && (parentLayoutId == 0)) {
			return jsonArray;
		}

		OrderByComparator orderByComparator =
			PagesPortletUtil.getLayoutOrderByComparator(
				getOrderByCol(), getOrderByType());

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			themeDisplay.getScopeGroupId(), isPrivateLayout(),parentLayoutId,
			true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);

		for (Layout layout : layouts) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONObject actionsJSONObject =
				PagesPortletUtil.getActionsJSONObject(layout, _request);

			if (actionsJSONObject.length() > 0) {
				jsonObject.put("actions", actionsJSONObject);
			}

			jsonObject.put(
				"active", layout.getLayoutId() == getSelectedLayoutId());
			jsonObject.put("icon", "page");
			jsonObject.put("layoutId", layout.getLayoutId());
			jsonObject.put("parentLayoutId", layout.getParentLayoutId());
			jsonObject.put("selected", jsonObject.getBoolean("active"));
			jsonObject.put("title", layout.getName(themeDisplay.getLocale()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(
			_request, "navigation", "public-pages");

		return _navigation;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_request, "orderByCol", "create-date");

		return _orderByCol;
	}

	public JSONObject getOrderByJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("orderByCol", getOrderByCol());
		jsonObject.put("orderByType", getOrderByType());

		return jsonObject;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType", "asc");

		return _orderByType;
	}

	public String[] getOrderColumns() {
		return new String[] {"create-date"};
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public long getSelectedLayoutId() {
		if (_selectedLayoutId != null) {
			return _selectedLayoutId;
		}

		_selectedLayoutId = ParamUtil.getLong(_request, "selectedLayoutId", 0);

		return _selectedLayoutId;
	}

	public boolean isPrivateLayout() {
		boolean privateLayout = ParamUtil.getBoolean(_request, "privateLayout");

		if (privateLayout) {
			return true;
		}

		if (Objects.equals(getNavigation(), "private-pages")) {
			return true;
		}

		return false;
	}

	private String _displayStyle;
	private Long _groupId;
	private String _navigation;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;
	private Long _selectedLayoutId;

}