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

package com.liferay.product.navigation.global.apps.web.internal.display.context;

import com.liferay.application.list.PanelApp;
import com.liferay.application.list.PanelAppRegistry;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.application.list.constants.ApplicationListWebKeys;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class GlobalAppsDisplayContext {

	public GlobalAppsDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_panelCategoryRegistry =
			(PanelCategoryRegistry)_renderRequest.getAttribute(
				ApplicationListWebKeys.PANEL_CATEGORY_REGISTRY);
		_panelAppRegistry =
			(PanelAppRegistry)_renderRequest.getAttribute(
				ApplicationListWebKeys.PANEL_APP_REGISTRY);
		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	private List<PanelCategory> _globalAppsPanelCategories;
	private List<PanelCategory> _selectedPanelCategories;

	private List<PanelCategory> _getGlobalAppsPanelCategories() {
		if (_globalAppsPanelCategories != null) {
			return _globalAppsPanelCategories;
		}

		List<PanelCategory> globalAppsPanelCategories =
			_panelCategoryRegistry.getChildPanelCategories(
				PanelCategoryKeys.GLOBAL_APPS,
				_themeDisplay.getPermissionChecker(),
				_themeDisplay.getScopeGroup());

		_globalAppsPanelCategories = ListUtil.filter(
			globalAppsPanelCategories,
			panelCategory -> {
				if (ListUtil.isEmpty(
						_getChildPanelCategories(panelCategory.getKey()))) {

					return false;
				}

				return true;
			});

		return _globalAppsPanelCategories;
	}

	private final PanelCategoryRegistry _panelCategoryRegistry;
	private final PanelAppRegistry _panelAppRegistry;

	public List<NavigationItem> getNavigationItems() {
		return new NavigationItemList() {
			{
				for (PanelCategory panelCategory :
						_getGlobalAppsPanelCategories()) {

					add(
						navigationItem -> {
							navigationItem.setActive(
								Objects.equals(
									panelCategory.getKey(), getKey()));
							navigationItem.setHref(
								_renderResponse.createRenderURL(), "key",
								panelCategory.getKey());
							navigationItem.setLabel(
								panelCategory.getLabel(
									_themeDisplay.getLocale()));
						});
				}
			}
		};
	}

	public String getKey() {
		if (_key != null) {
			return _key;
		}

		List<PanelCategory> panelCategories = _getGlobalAppsPanelCategories();

		PanelCategory panelCategory = panelCategories.get(0);

		_key = ParamUtil.getString(
			_renderRequest, "key", panelCategory.getKey());

		return _key;
	}

	private String _key;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

	public List<PanelCategory> getSelectedPanelCategories() {
		if (_selectedPanelCategories != null) {
			return _selectedPanelCategories;
		}

		_selectedPanelCategories = _getChildPanelCategories(getKey());

		return _selectedPanelCategories;
	}
	
	private List<PanelCategory> _getChildPanelCategories(String key) {
		List<PanelCategory> childPanelCategories =
			_panelCategoryRegistry.getChildPanelCategories(
				key, _themeDisplay.getPermissionChecker(),
				_themeDisplay.getScopeGroup());

		return ListUtil.filter(
			childPanelCategories,
			panelCategory -> {
				List<PanelApp> panelApps = _panelAppRegistry.getPanelApps(
					panelCategory.getKey());

				if (ListUtil.isEmpty(panelApps)) {
					return false;
				}

				return true;
			});
	}
	
}