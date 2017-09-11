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

import com.liferay.modern.site.building.page.web.constants.MSBPagesPortletKeys;
import com.liferay.modern.site.building.page.web.internal.util.MSBPagesPortletUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class MSBPagesDisplayContext {

	public MSBPagesDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest request) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_request = request;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		_displayStyle = portalPreferences.getValue(
			MSBPagesPortletKeys.MSB_PAGES, "display-style", "icon");

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
			MSBPagesPortletUtil.getLayoutOrderByComparator(
				getOrderByCol(), getOrderByType());

		jsonArray = MSBPagesPortletUtil.getLayoutsJSONArray(
			themeDisplay.getScopeGroupId(), isPrivateLayout(), parentLayoutId,
			getSelectedLayoutId(), orderByComparator, _renderRequest);

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

	public JSONArray getNodeBlocksJSONArray() throws Exception {
		List<JSONArray> nodeBlocksList = new ArrayList<>();
		JSONArray nodeBlocksJSONArray = JSONFactoryUtil.createJSONArray();
		long selectedLayoutId = getSelectedLayoutId();

		Layout selectedLayout = LayoutLocalServiceUtil.fetchLayout(
			getGroupId(), isPrivateLayout(), selectedLayoutId);

		Locale locale = (
			(ThemeDisplay)_request.getAttribute(WebKeys.THEME_DISPLAY)
		).getLocale();

		while (selectedLayout != null) {
			JSONArray nodeBlockJSONArray = JSONFactoryUtil.createJSONArray();

			for (Layout childLayout : selectedLayout.getChildren()) {
				boolean layoutIsActive = false;

				if (childLayout.getLayoutId() == selectedLayoutId) {
					layoutIsActive = true;
				}

				nodeBlockJSONArray.put(
					jsonObjectFromLayout(childLayout, layoutIsActive, locale));
			}

			if (nodeBlockJSONArray.length() > 0) {
				nodeBlocksList.add(nodeBlockJSONArray);
			}

			selectedLayoutId = selectedLayout.getParentLayoutId();

			selectedLayout = LayoutLocalServiceUtil.fetchLayout(
				getGroupId(), isPrivateLayout(),
				selectedLayout.getParentLayoutId());
		}

		nodeBlocksList.add(getRootLayoutsJSONArray());

		Collections.reverse(nodeBlocksList);

		for (JSONArray nodeBlockJSONArray : nodeBlocksList) {
			nodeBlocksJSONArray.put(nodeBlockJSONArray);
		}

		return nodeBlocksJSONArray;
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

	public JSONArray getRootLayoutsJSONArray() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray;

		OrderByComparator orderByComparator =
			MSBPagesPortletUtil.getLayoutOrderByComparator(
				getOrderByCol(), getOrderByType());

		jsonArray = MSBPagesPortletUtil.getLayoutsJSONArray(
			themeDisplay.getScopeGroupId(), isPrivateLayout(), 0,
			getSelectedLayoutId(), orderByComparator, _renderRequest);

		return jsonArray;
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

	public JSONObject jsonObjectFromLayout(
		Layout layout, boolean active, Locale locale) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("actions", JSONFactoryUtil.createJSONObject());
		jsonObject.put("active", active);
		jsonObject.put("hasChild", layout.getChildren().size() > 0);
		jsonObject.put("icon", "");
		jsonObject.put("layoutId", layout.getLayoutId());
		jsonObject.put("selected", active);
		jsonObject.put("title", layout.getName(locale));

		return jsonObject;
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