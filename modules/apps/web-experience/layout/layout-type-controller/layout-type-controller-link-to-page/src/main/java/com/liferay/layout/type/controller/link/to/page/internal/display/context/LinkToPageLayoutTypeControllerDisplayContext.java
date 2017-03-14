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

package com.liferay.layout.type.controller.link.to.page.internal.display.context;

import com.liferay.layout.type.controller.link.to.page.internal.constants.LinkToPageLayoutTypeControllerPortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class LinkToPageLayoutTypeControllerDisplayContext {

	public LinkToPageLayoutTypeControllerDisplayContext(
		HttpServletRequest request,
		LiferayPortletResponse liferayPortletResponse) {

		_request = request;
		_liferayPortletResponse = liferayPortletResponse;

		_setSelectedLayout();
	}

	public String getItemSelectorURL() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			_request,
			LinkToPageLayoutTypeControllerPortletKeys.
				LINK_TO_PAGE_LAYOUT_TYPE_CONTROLLER_PORTLET,
			themeDisplay.getLayout(), PortletRequest.RENDER_PHASE);

		String eventName =
			_liferayPortletResponse.getNamespace() + "selectLayout";

		portletURL.setParameter("mvcPath", "/layout/edit/select_page.jsp");
		portletURL.setParameter("layoutUuid", getLinkToLayoutUuid());
		portletURL.setParameter("eventName", eventName);
		portletURL.setParameter(
			"privateLayout", ParamUtil.getString(_request, "privateLayout"));

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	public String getLayoutBreadcrumb(Layout layout) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		List<Layout> ancestors = layout.getAncestors();

		StringBundler sb = new StringBundler(4 * ancestors.size() + 5);

		if (layout.isPrivateLayout()) {
			sb.append(LanguageUtil.get(_request, "private-pages"));
		}
		else {
			sb.append(LanguageUtil.get(_request, "public-pages"));
		}

		sb.append(StringPool.SPACE);
		sb.append(StringPool.GREATER_THAN);
		sb.append(StringPool.SPACE);

		Collections.reverse(ancestors);

		for (Layout ancestor : ancestors) {
			sb.append(HtmlUtil.escape(ancestor.getName(locale)));
			sb.append(StringPool.SPACE);
			sb.append(StringPool.GREATER_THAN);
			sb.append(StringPool.SPACE);
		}

		sb.append(HtmlUtil.escape(layout.getName(locale)));

		return sb.toString();
	}

	public JSONObject getLayoutsJSON() throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("private", _getLayoutsJSONObject(true));
		jsonObject.put("public", _getLayoutsJSONObject(false));

		return jsonObject;
	}

	public String getLinkToLayoutName() throws Exception {
		if (_selectedLayout != null) {
			return getLayoutBreadcrumb(_selectedLayout);
		}

		return StringPool.BLANK;
	}

	public String getLinkToLayoutUuid() {
		if (_selectedLayout != null) {
			return _selectedLayout.getUuid();
		}

		return ParamUtil.getString(_request, "layoutUuid");
	}

	private JSONArray _getLayoutsJSONArray(
			long groupId, boolean privateLayout, long parentLayoutId,
			String selectedLayoutUuid)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(
			groupId, privateLayout, parentLayoutId);

		for (Layout layout : layouts) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("icon", "page");
			jsonObject.put("id", layout.getUuid());
			jsonObject.put("name", layout.getName(themeDisplay.getLocale()));
			jsonObject.put("value", getLayoutBreadcrumb(layout));

			if (layout.getUuid().equals(selectedLayoutUuid)) {
				jsonObject.put("selected", true);
			}

			JSONArray childrenJSONArray = _getLayoutsJSONArray(
				groupId, privateLayout, layout.getLayoutId(),
				selectedLayoutUuid);

			if (childrenJSONArray.length() > 0) {
				jsonObject.put("children", childrenJSONArray);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private JSONObject _getLayoutsJSONObject(boolean privateLayout)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = _getLayoutsJSONArray(
			themeDisplay.getScopeGroupId(), privateLayout, 0,
			getLinkToLayoutUuid());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("children", jsonArray);
		jsonObject.put("disabled", true);
		jsonObject.put("expanded", true);
		jsonObject.put("icon", "home");
		jsonObject.put("name", themeDisplay.getScopeGroupName());

		return jsonObject;
	}

	private void _setSelectedLayout() {
		Layout layout = (Layout)_request.getAttribute(WebKeys.SEL_LAYOUT);

		if (layout != null) {
			long linkToLayoutId = GetterUtil.getLong(
				layout.getTypeSettingsProperty("linkToLayoutId"));

			_selectedLayout = LayoutLocalServiceUtil.fetchLayout(
				layout.getGroupId(), layout.isPrivateLayout(), linkToLayoutId);
		}
	}

	private final LiferayPortletResponse _liferayPortletResponse;
	private final HttpServletRequest _request;
	private Layout _selectedLayout;

}