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

package com.liferay.site.navigation.item.selector.web.internal.display.context;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.constants.SiteNavigationAdminPortletKeys;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuServiceUtil;
import com.liferay.site.navigation.util.comparator.SiteNavigationMenuPortletUtil;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pavel Savinov
 */
public class SiteNavigationItemSelectorViewDisplayContext {

	public SiteNavigationItemSelectorViewDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			HttpServletRequest request, PortletURL portletURL,
			String itemSelectedEventName)
		throws PortalException {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_request = request;
		_portletURL = portletURL;
		_itemSelectedEventName = itemSelectedEventName;

		_portletPreferences =
			PortletPreferencesFactoryUtil.getPortletPreferences(
				request, SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN);
	}

	public String getDisplayStyle() {
		if (_displayStyle != null) {
			return _displayStyle;
		}

		String[] displayViews = getDisplayViews();

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(_request);

		_displayStyle = ParamUtil.getString(_request, "displayStyle");

		if (Validator.isNull(_displayStyle)) {
			_displayStyle = portalPreferences.getValue(
				SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN,
				"display-style", "list");
		}
		else if (ArrayUtil.contains(displayViews, _displayStyle)) {
			portalPreferences.setValue(
				SiteNavigationAdminPortletKeys.SITE_NAVIGATION_ADMIN,
				"display-style", _displayStyle);
		}

		if (!ArrayUtil.contains(displayViews, _displayStyle)) {
			_displayStyle = displayViews[0];
		}

		return _displayStyle;
	}

	public String[] getDisplayViews() {
		if (_displayViews == null) {
			_displayViews = StringUtil.split(
				PrefsParamUtil.getString(
					_portletPreferences, _request, "displayViews",
					"list,icon,descriptive"));
		}

		return _displayViews;
	}

	public String getOrderByCol() throws Exception {
		if (_orderByCol != null) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(_request, "orderByCol");

		if (Validator.isNull(_orderByCol)) {
			_orderByCol = _portletPreferences.getValue(
				"order-by-col", "create-date");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portletPreferences.setValue("order-by-col", _orderByCol);
			}
		}

		return _orderByCol;
	}

	public String getOrderByType() throws Exception {
		if (_orderByType != null) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(_request, "orderByType");

		if (Validator.isNull(_orderByType)) {
			_orderByType = _portletPreferences.getValue("order-by-type", "asc");
		}
		else {
			boolean saveOrderBy = ParamUtil.getBoolean(_request, "saveOrderBy");

			if (saveOrderBy) {
				_portletPreferences.setValue("order-by-type", _orderByType);
			}
		}

		return _orderByType;
	}

	public String[] getOrderColumns() {
		String[] orderColumns = {"create-date", "name"};

		return orderColumns;
	}

	public PortletURL getPortletURL() throws Exception {
		String displayStyle = ParamUtil.getString(_request, "displayStyle");

		if (Validator.isNotNull(displayStyle)) {
			_portletURL.setParameter("displayStyle", getDisplayStyle());
		}

		String orderByCol = getOrderByCol();

		if (Validator.isNotNull(orderByCol)) {
			_portletURL.setParameter("orderByCol", orderByCol);
		}

		String orderByType = getOrderByType();

		if (Validator.isNotNull(orderByType)) {
			_portletURL.setParameter("orderByType", orderByType);
		}

		return _portletURL;
	}

	private String _displayStyle;
	private String[] _displayViews;
	private final String _itemSelectedEventName;
	private String _keywords;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
	private final PortletPreferences _portletPreferences;
	private final PortletURL _portletURL;
	private final HttpServletRequest _request;

}