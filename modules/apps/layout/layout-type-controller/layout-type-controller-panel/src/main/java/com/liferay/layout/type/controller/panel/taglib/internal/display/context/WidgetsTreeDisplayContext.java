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

package com.liferay.layout.type.controller.panel.taglib.internal.display.context;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.model.PortletCategory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletConfigFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.PortletCategoryComparator;
import com.liferay.portal.kernel.util.comparator.PortletTitleComparator;
import com.liferay.portal.util.PortletCategoryUtil;
import com.liferay.portal.util.WebAppPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.portlet.PortletConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Diego Hu
 */
public class WidgetsTreeDisplayContext {

	public WidgetsTreeDisplayContext(
		HttpServletRequest httpServletRequest,
		LayoutTypePortlet layoutTypePortlet, User user) {

		_httpServletRequest = httpServletRequest;
		_layoutTypePortlet = layoutTypePortlet;
		_user = user;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getData() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"items", getPortletsJSONArray(_httpServletRequest, _themeDisplay)
		).put(
			"selectedPortlets", _selectedPortletsjsonArray
		).build();
	}

	public JSONArray getPortletsJSONArray(
			HttpServletRequest httpServletRequest, ThemeDisplay themeDisplay)
		throws Exception {

		PortletCategory rootPortletCategory = (PortletCategory)WebAppPool.get(
			themeDisplay.getCompanyId(), WebKeys.PORTLET_CATEGORY);

		PortletCategory portletCategory =
			PortletCategoryUtil.getRelevantPortletCategory(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getCompanyId(), themeDisplay.getLayout(),
				rootPortletCategory, themeDisplay.getLayoutTypePortlet());

		Map<String, JSONObject> portletCategoryJSONObjectsMap =
			_getPortletCategoryJSONObjectsMap(
				httpServletRequest, portletCategory, themeDisplay);

		return JSONUtil.toJSONArray(
			new ArrayList<>(portletCategoryJSONObjectsMap.values()),
			portletCategoryJSONObject -> portletCategoryJSONObject);
	}

	private Set<String> _getLayoutDecodedPortletNames(
		ThemeDisplay themeDisplay) {

		Set<String> layoutDecodedPortletNames = new HashSet<>();

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		for (Portlet layoutPortlet : layoutTypePortlet.getPortlets()) {
			String decodedPortletName = PortletIdCodec.decodePortletName(
				layoutPortlet.getPortletId());

			layoutDecodedPortletNames.add(decodedPortletName);
		}

		return layoutDecodedPortletNames;
	}

	private Map<String, JSONObject> _getPortletCategoryJSONObjectsMap(
			HttpServletRequest httpServletRequest,
			PortletCategory portletCategory, ThemeDisplay themeDisplay)
		throws Exception {

		Map<String, JSONObject> portletCategoryJSONObjectsMap =
			new LinkedHashMap<>();

		Set<String> layoutDecodedPortletNames = _getLayoutDecodedPortletNames(
			themeDisplay);

		List<PortletCategory> portletCategories = ListUtil.fromCollection(
			portletCategory.getCategories());

		portletCategories = ListUtil.sort(
			portletCategories,
			new PortletCategoryComparator(themeDisplay.getLocale()));

		for (PortletCategory currentPortletCategory : portletCategories) {
			if (currentPortletCategory.isHidden()) {
				continue;
			}

			String portletCategoryKey = StringUtil.replace(
				currentPortletCategory.getPath(), new String[] {"/", "."},
				new String[] {"-", "-"});

			Map<String, JSONObject> childPortletCategoryJSONObjectsMap =
				_getPortletCategoryJSONObjectsMap(
					httpServletRequest, currentPortletCategory, themeDisplay);

			JSONArray childPortletCategoriesJSONArray = JSONUtil.toJSONArray(
				childPortletCategoryJSONObjectsMap.values(),
				portletCategoryJSONObject -> portletCategoryJSONObject);

			JSONArray portletsJSONArray = _getPortletsJSONArray(
				httpServletRequest, layoutDecodedPortletNames,
				currentPortletCategory, themeDisplay);

			if ((childPortletCategoriesJSONArray.length() > 0) ||
				(portletsJSONArray.length() > 0)) {

				portletCategoryJSONObjectsMap.put(
					portletCategoryKey,
					JSONUtil.put(
						"categories", childPortletCategoriesJSONArray
					).put(
						"children", portletsJSONArray
					).put(
						"id", portletCategoryKey
					).put(
						"name",
						_getPortletCategoryTitle(
							httpServletRequest, currentPortletCategory,
							themeDisplay)
					).put(
						"path", portletCategoryKey
					));
			}
		}

		return portletCategoryJSONObjectsMap;
	}

	private String _getPortletCategoryTitle(
		HttpServletRequest httpServletRequest, PortletCategory portletCategory,
		ThemeDisplay themeDisplay) {

		for (String portletId :
				PortletCategoryUtil.getFirstChildPortletIds(portletCategory)) {

			Portlet portlet = _portletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletId);

			if (portlet == null) {
				continue;
			}

			PortletApp portletApp = portlet.getPortletApp();

			if (!portletApp.isWARFile()) {
				continue;
			}

			PortletConfig portletConfig = PortletConfigFactoryUtil.create(
				portlet, httpServletRequest.getServletContext());

			ResourceBundle portletResourceBundle =
				portletConfig.getResourceBundle(themeDisplay.getLocale());

			String title = ResourceBundleUtil.getString(
				portletResourceBundle, portletCategory.getName());

			if (Validator.isNotNull(title)) {
				return title;
			}
		}

		return _languageUtil.get(httpServletRequest, portletCategory.getName());
	}

	private List<Portlet> _getPortlets(
		PortletCategory portletCategory, ThemeDisplay themeDisplay) {

		List<Portlet> portlets = new ArrayList<>();

		Set<String> portletIds = portletCategory.getPortletIds();

		for (String portletId : portletIds) {
			Portlet portlet = _portletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletId);

			if (portlet == null) {
				continue;
			}

			if (portlet.isSystem()) {
			}
			else if (!portlet.isActive()) {
			}
			else if (portlet.isInstanceable()) {
			}
			else if (!portlet.isInstanceable() &&
					 _layoutTypePortlet.hasPortletId(portlet.getPortletId())) {

				portlets.add(portlet);
			}
			else if (!portlet.hasAddPortletPermission(_user.getUserId())) {
			}
			else {
				portlets.add(portlet);
			}
		}

		return portlets;
	}

	private JSONArray _getPortletsJSONArray(
			HttpServletRequest httpServletRequest,
			Set<String> layoutDecodedPortletNames,
			PortletCategory portletCategory, ThemeDisplay themeDisplay)
		throws Exception {

		JSONArray jsonArray = _jsonFactoryUtil.createJSONArray();

		HttpSession httpSession = httpServletRequest.getSession();

		ServletContext servletContext = httpSession.getServletContext();

		List<Portlet> portlets = _getPortlets(portletCategory, themeDisplay);

		portlets = ListUtil.sort(
			portlets,
			new PortletTitleComparator(
				servletContext, themeDisplay.getLocale()));

		for (Portlet portlet : portlets) {
			_getSelectedPortlets(portlet);

			jsonArray.put(
				JSONUtil.put(
					"id", portlet.getPortletId()
				).put(
					"name",
					_portalUtil.getPortletTitle(
						portlet, servletContext, themeDisplay.getLocale())
				).put(
					"used",
					() -> {
						Layout layout = themeDisplay.getLayout();

						if (!layout.isTypePortlet() ||
							portlet.isInstanceable()) {

							return false;
						}

						LayoutTypePortlet layoutTypePortlet =
							themeDisplay.getLayoutTypePortlet();

						if (layoutDecodedPortletNames.contains(
								portlet.getPortletId()) ||
							layoutTypePortlet.hasPortletId(
								portlet.getPortletId())) {

							return true;
						}

						return false;
					}
				));
		}

		return jsonArray;
	}

	private void _getSelectedPortlets(Portlet portlet) {
		Layout selLayout = (Layout)_httpServletRequest.getAttribute(
			WebKeys.SEL_LAYOUT);

		List<String> panelSelectedPortlets = new ArrayList<>();

		if (selLayout != null) {
			UnicodeProperties typeSettingsUnicodeProperties =
				selLayout.getTypeSettingsProperties();

			panelSelectedPortlets = Arrays.asList(
				StringUtil.split(
					typeSettingsUnicodeProperties.getProperty(
						"panelSelectedPortlets", StringPool.BLANK)));
		}

		for (String currentPortlet : panelSelectedPortlets) {
			String portletId = portlet.getPortletId();

			if (portletId.equals(currentPortlet)) {
				_selectedPortletsjsonArray.put(portletId);
			}
		}
	}

	private final HttpServletRequest _httpServletRequest;
	private JSONFactoryUtil _jsonFactoryUtil;
	private LanguageUtil _languageUtil;
	private final LayoutTypePortlet _layoutTypePortlet;
	private PortalUtil _portalUtil;
	private PortletLocalServiceUtil _portletLocalServiceUtil;
	private final JSONArray _selectedPortletsjsonArray =
		_jsonFactoryUtil.createJSONArray();
	private final ThemeDisplay _themeDisplay;
	private final User _user;

}