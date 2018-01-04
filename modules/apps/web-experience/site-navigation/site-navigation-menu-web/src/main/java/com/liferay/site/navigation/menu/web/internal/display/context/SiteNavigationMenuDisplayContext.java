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

package com.liferay.site.navigation.menu.web.internal.display.context;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import com.liferay.site.navigation.item.selector.criterion.SiteNavigationItemSelectorCriterion;
import com.liferay.site.navigation.menu.web.configuration.SiteNavigationMenuPortletInstanceConfiguration;
import com.liferay.site.navigation.menu.web.internal.constants.SiteNavigationMenuWebKeys;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juergen Kappler
 */
public class SiteNavigationMenuDisplayContext {

	public SiteNavigationMenuDisplayContext(HttpServletRequest request)
		throws ConfigurationException {

		_request = request;

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_siteNavigationMenuPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				SiteNavigationMenuPortletInstanceConfiguration.class);

		_siteNavigationMenuItemTypeRegistry =
			(SiteNavigationMenuItemTypeRegistry)_request.getAttribute(
				SiteNavigationMenuWebKeys.
					SITE_NAVIGATION_MENU_ITEM_TYPE_REGISTRY);
	}

	public String getDDMTemplateKey() {
		if (_ddmTemplateKey != null) {
			return _ddmTemplateKey;
		}

		String displayStyle = getDisplayStyle();

		if (displayStyle != null) {
			PortletDisplayTemplate portletDisplayTemplate =
				(PortletDisplayTemplate)_request.getAttribute(
					WebKeys.PORTLET_DISPLAY_TEMPLATE);

			_ddmTemplateKey = portletDisplayTemplate.getDDMTemplateKey(
				displayStyle);
		}

		return _ddmTemplateKey;
	}

	public int getDisplayDepth() {
		if (_displayDepth != -1) {
			return _displayDepth;
		}

		_displayDepth = ParamUtil.getInteger(
			_request, "displayDepth",
			_siteNavigationMenuPortletInstanceConfiguration.displayDepth());

		return _displayDepth;
	}

	public String getDisplayStyle() {
		if (_displayStyle != null) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(
			_request, "displayStyle",
			_siteNavigationMenuPortletInstanceConfiguration.displayStyle());

		return _displayStyle;
	}

	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId != 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId = ParamUtil.getLong(
			_request, "displayStyleGroupId",
			_siteNavigationMenuPortletInstanceConfiguration.
				displayStyleGroupId());

		if (_displayStyleGroupId <= 0) {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			_displayStyleGroupId = themeDisplay.getSiteGroupId();
		}

		return _displayStyleGroupId;
	}

	public String getEventName() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getNamespace() + "selectLayout";
	}

	public String getExpandedLevels() {
		if (_expandedLevels != null) {
			return _expandedLevels;
		}

		_expandedLevels = ParamUtil.getString(
			_request, "expandedLevels",
			_siteNavigationMenuPortletInstanceConfiguration.expandedLevels());

		return _expandedLevels;
	}

	public String getItemSelectorURL() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		ItemSelector itemSelector = (ItemSelector)_request.getAttribute(
			SiteNavigationMenuWebKeys.ITEM_SELECTOR);

		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		Layout layout = themeDisplay.getLayout();

		layoutItemSelectorCriterion.setCheckDisplayPage(false);
		layoutItemSelectorCriterion.setEnableCurrentPage(true);
		layoutItemSelectorCriterion.setShowPrivatePages(
			layout.isPrivateLayout());
		layoutItemSelectorCriterion.setShowPublicPages(layout.isPublicLayout());

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
			new ArrayList<>();

		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			desiredItemSelectorReturnTypes);

		PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_request), getEventName(),
			layoutItemSelectorCriterion);

		itemSelectorURL.setParameter(
			"selectedItemId", String.valueOf(getRootItemId()));

		return itemSelectorURL.toString();
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

	public String getRootItemEventName() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getNamespace() + "selectRootItem";
	}

	public long getRootItemId() {
		if (_rootItemId != null) {
			return _rootItemId;
		}

		_rootItemId = ParamUtil.getLong(
			_request, "rootItemId",
			_siteNavigationMenuPortletInstanceConfiguration.rootItemId());

		return _rootItemId;
	}

	public int getRootItemLevel() {
		if (_rootItemLevel != null) {
			return _rootItemLevel;
		}

		_rootItemLevel = ParamUtil.getInteger(
			_request, "rootItemLevel",
			_siteNavigationMenuPortletInstanceConfiguration.rootItemLevel());

		return _rootItemLevel;
	}

	public String getRootItemSelectorURL() throws PortalException {
		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			_request, SiteNavigationMenu.class.getName(),
			PortletProvider.Action.VIEW);

		portletURL.setParameter(
			"siteNavigationMenuId", String.valueOf(getSiteNavigationMenuId()));
		portletURL.setParameter("eventName", getRootItemEventName());

		return portletURL.toString();
	}

	public String getRootItemType() {
		if (_rootItemType != null) {
			return _rootItemType;
		}

		_rootItemType = ParamUtil.getString(
			_request, "rootItemType",
			_siteNavigationMenuPortletInstanceConfiguration.rootItemType());

		return _rootItemType;
	}

	public SiteNavigationMenu getSiteNavigationMenu() throws PortalException {
		if (_siteNavigationMenu != null) {
			return _siteNavigationMenu;
		}

		_siteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				getSiteNavigationMenuId());

		return _siteNavigationMenu;
	}

	public String getSiteNavigationMenuEventName() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getNamespace() + "selectSiteNavigationMenu";
	}

	public long getSiteNavigationMenuId() {
		if (_siteNavigationMenuId != null) {
			return _siteNavigationMenuId;
		}

		_siteNavigationMenuId = ParamUtil.getLong(
			_request, "siteNavigationMenuId",
			_siteNavigationMenuPortletInstanceConfiguration.
				siteNavigationMenuId());

		return _siteNavigationMenuId;
	}

	public String getSiteNavigationMenuItemSelectorURL() {
		String eventName = getSiteNavigationMenuEventName();

		ItemSelector itemSelector = (ItemSelector)_request.getAttribute(
			SiteNavigationMenuWebKeys.ITEM_SELECTOR);

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
			new ArrayList<>();

		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

		SiteNavigationItemSelectorCriterion
			siteNavigationItemSelectorCriterion =
				new SiteNavigationItemSelectorCriterion();

		siteNavigationItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			desiredItemSelectorReturnTypes);

		PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_request), eventName,
			siteNavigationItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public SiteNavigationMenuItemTypeRegistry
		getSiteNavigationMenuItemTypeRegistry() {

		return _siteNavigationMenuItemTypeRegistry;
	}

	public boolean isLayoutsMenu() {
		if (_menuType != null) {
			return _menuType.equals("layouts");
		}

		_menuType = ParamUtil.getString(
			_request, "menuType",
			_siteNavigationMenuPortletInstanceConfiguration.menuType());

		return _menuType.equals("layouts");
	}

	public boolean isPreview() {
		if (_preview != null) {
			return _preview;
		}

		_preview = ParamUtil.getBoolean(
			_request, "preview",
			_siteNavigationMenuPortletInstanceConfiguration.preview());

		return _preview;
	}

	private String _ddmTemplateKey;
	private int _displayDepth = -1;
	private String _displayStyle;
	private long _displayStyleGroupId;
	private String _expandedLevels;
	private String _menuType;
	private Boolean _preview;
	private final HttpServletRequest _request;
	private Long _rootItemId;
	private Integer _rootItemLevel;
	private String _rootItemType;
	private SiteNavigationMenu _siteNavigationMenu;
	private Long _siteNavigationMenuId;
	private final SiteNavigationMenuItemTypeRegistry
		_siteNavigationMenuItemTypeRegistry;
	private final SiteNavigationMenuPortletInstanceConfiguration
		_siteNavigationMenuPortletInstanceConfiguration;

}