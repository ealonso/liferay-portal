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

package com.liferay.site.navigation.site.map.web.internal.display.context;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portal.util.LayoutListUtil;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.item.selector.criterion.SiteNavigationMenuItemSelectorCriterion;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;
import com.liferay.site.navigation.site.map.web.configuration.SiteNavigationSiteMapPortletInstanceConfiguration;
import com.liferay.site.navigation.site.map.web.internal.constants.SiteNavigationSiteMapWebKeys;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juergen Kappler
 */
public class SiteNavigationSiteMapDisplayContext {

	public SiteNavigationSiteMapDisplayContext(HttpServletRequest request)
		throws ConfigurationException {

		_request = request;

		_themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = _themeDisplay.getPortletDisplay();

		_siteNavigationSiteMapPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				SiteNavigationSiteMapPortletInstanceConfiguration.class);
	}

	public String buildSiteMap() throws Exception {
		StringBundler sb = new StringBundler();

		_buildSiteMap(
			_themeDisplay.getLayout(), getRootItems(),
			_siteNavigationSiteMapPortletInstanceConfiguration.displayDepth(),
			1, _themeDisplay, sb);

		return sb.toString();
	}

	public Long getDisplayStyleGroupId() {
		if (_displayStyleGroupId != null) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_siteNavigationSiteMapPortletInstanceConfiguration.
				displayStyleGroupId();

		Group displayStyleGroup = GroupLocalServiceUtil.fetchGroup(
			_displayStyleGroupId);

		if (displayStyleGroup == null) {
			_displayStyleGroupId = _themeDisplay.getSiteGroupId();
		}

		return _displayStyleGroupId;
	}

	public List<LayoutDescription> getLayoutDescriptions() {
		Layout layout = _themeDisplay.getLayout();

		String rootNodeName = StringPool.BLANK;

		return LayoutListUtil.getLayoutDescriptions(
			layout.getGroupId(), layout.isPrivateLayout(), rootNodeName,
			_themeDisplay.getLocale());
	}

	public long getRootItemId() {
		if (_rootItemId != null) {
			return _rootItemId;
		}

		_rootItemId =
			_siteNavigationSiteMapPortletInstanceConfiguration.rootItemId();

		return _rootItemId;
	}

	public List<SiteNavigationMenuItem> getRootItems() {
		SiteNavigationMenu primarySiteNavigationMenu =
			SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
				getSelectSiteNavigationMenuId());

		if (primarySiteNavigationMenu == null) {
			return Collections.emptyList();
		}

		return
			SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItems(
				primarySiteNavigationMenu.getSiteNavigationMenuId(),
				getRootItemId());
	}

	public long getSelectSiteNavigationMenuId() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		int siteNavigationMenuType = getSiteNavigationMenuType();

		long siteNavigationMenuId = getSiteNavigationMenuId();

		if ((siteNavigationMenuType == -1) && (siteNavigationMenuId <= 0)) {
			SiteNavigationMenu siteNavigationMenu =
				SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
					themeDisplay.getScopeGroupId(),
					_getDefaultSelectSiteNavigationMenuType());

			if (siteNavigationMenu != null) {
				return siteNavigationMenu.getSiteNavigationMenuId();
			}

			return 0;
		}

		if (siteNavigationMenuType > 0) {
			SiteNavigationMenu siteNavigationMenu =
				SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
					themeDisplay.getScopeGroupId(), siteNavigationMenuType);

			if (siteNavigationMenu != null) {
				return siteNavigationMenu.getSiteNavigationMenuId();
			}

			return 0;
		}

		return siteNavigationMenuId;
	}

	public int getSelectSiteNavigationMenuType() {
		int selectSiteNavigationMenuType = getSiteNavigationMenuType();

		if (selectSiteNavigationMenuType > 0) {
			return selectSiteNavigationMenuType;
		}

		return _getDefaultSelectSiteNavigationMenuType();
	}

	public SiteNavigationMenu getSiteNavigationMenu() {
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
			_siteNavigationSiteMapPortletInstanceConfiguration.
				siteNavigationMenuId());

		return _siteNavigationMenuId;
	}

	public String getSiteNavigationMenuItemSelectorURL() {
		String eventName = getSiteNavigationMenuEventName();

		ItemSelector itemSelector = (ItemSelector)_request.getAttribute(
			SiteNavigationSiteMapWebKeys.ITEM_SELECTOR);

		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
			new ArrayList<>();

		desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

		SiteNavigationMenuItemSelectorCriterion
			siteNavigationMenuItemSelectorCriterion =
				new SiteNavigationMenuItemSelectorCriterion();

		siteNavigationMenuItemSelectorCriterion.
			setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);

		PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(
			RequestBackedPortletURLFactoryUtil.create(_request), eventName,
			siteNavigationMenuItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	public int getSiteNavigationMenuType() {
		if (_navigationMenuType != null) {
			return _navigationMenuType;
		}

		int siteNavigationMenuType =
			_siteNavigationSiteMapPortletInstanceConfiguration.
				siteNavigationMenuType();

		_navigationMenuType = ParamUtil.getInteger(
			_request, "siteNavigationMenuType", siteNavigationMenuType);

		return _navigationMenuType;
	}

	public String getSiteNavigationMenuTypeLabel() {
		int type = getSiteNavigationMenuType();

		String typeKey = "select";

		if (type == SiteNavigationConstants.TYPE_PRIMARY) {
			typeKey = "primary-navigation";
		}
		else if (type == SiteNavigationConstants.TYPE_PRIVATE) {
			typeKey = "private-navigation";
		}
		else if (type == SiteNavigationConstants.TYPE_SECONDARY) {
			typeKey = "secondary-navigation";
		}
		else if (type == SiteNavigationConstants.TYPE_SOCIAL) {
			typeKey = "social-navigation";
		}

		return LanguageUtil.get(_request, typeKey);
	}

	public SiteNavigationSiteMapPortletInstanceConfiguration
		getSiteNavigationSiteMapPortletInstanceConfiguration() {

		return _siteNavigationSiteMapPortletInstanceConfiguration;
	}

	public boolean isSiteNavigationMenuSelected() {
		if ((_siteNavigationSiteMapPortletInstanceConfiguration.
				siteNavigationMenuId() > 0) &&
			(_siteNavigationSiteMapPortletInstanceConfiguration.
				siteNavigationMenuType() == -1)) {

			return true;
		}

		return false;
	}

	private void _buildSiteMap(
			Layout layout, List<SiteNavigationMenuItem> siteNavigationMenuItems,
			int displayDepth, int curDepth, ThemeDisplay themeDisplay,
			StringBundler sb)
		throws Exception {

		if (siteNavigationMenuItems.isEmpty()) {
			return;
		}

		SiteNavigationMenuItem rootItem =
			SiteNavigationMenuItemLocalServiceUtil.fetchSiteNavigationMenuItem(
				getRootItemId());

		if (rootItem != null) {
			SiteNavigationMenuItemType siteNavigationMenuItemType =
				rootItem.getSiteNavigationMenuItemType();

			if (!siteNavigationMenuItemType.hasPermission(
					_themeDisplay.getPermissionChecker(), rootItem)) {

				return;
			}
		}

		sb.append("<ul>");

		for (SiteNavigationMenuItem siteNavigationMenuItem :
				siteNavigationMenuItems) {

			SiteNavigationMenuItemType siteNavigationMenuItemType =
				siteNavigationMenuItem.getSiteNavigationMenuItemType();

			if (siteNavigationMenuItemType.hasPermission(
					_themeDisplay.getPermissionChecker(),
					siteNavigationMenuItem)) {

				sb.append("<li>");

				String cssClass = StringPool.BLANK;

				Layout siteNavigationMenuItemLayout = _fetchLayout(
					siteNavigationMenuItem);

				if ((siteNavigationMenuItemLayout != null) &&
					(siteNavigationMenuItemLayout.getPlid() ==
						layout.getPlid())) {

					cssClass = "current";
				}

				_buildSiteNavigationMenuItemView(
					siteNavigationMenuItem, cssClass, themeDisplay, sb);

				if ((displayDepth == 0) || (displayDepth > curDepth)) {
					_buildSiteMap(
						layout, siteNavigationMenuItem.getChildren(),
						displayDepth, curDepth + 1, themeDisplay, sb);
				}

				sb.append("</li>");
			}
		}

		sb.append("</ul>");
	}

	private void _buildSiteNavigationMenuItemView(
			SiteNavigationMenuItem siteNavigationMenuItem, String cssClass,
			ThemeDisplay themeDisplay, StringBundler sb)
		throws Exception {

		SiteNavigationMenuItemType siteNavigationMenuItemType =
			siteNavigationMenuItem.getSiteNavigationMenuItemType();

		String url = siteNavigationMenuItemType.getRegularURL(
			_request, siteNavigationMenuItem);

		sb.append("<a");

		if (siteNavigationMenuItemType.isBrowsable(siteNavigationMenuItem)) {
			sb.append(" href=\"");
			sb.append(url);
			sb.append("\" ");
		}

		if (Validator.isNotNull(cssClass)) {
			sb.append(" class=\"");
			sb.append(cssClass);
			sb.append("\" ");
		}

		sb.append("> ");

		String title = siteNavigationMenuItemType.getTitle(
			siteNavigationMenuItem, themeDisplay.getLocale());

		sb.append(title);

		sb.append("</a>");
	}

	private Layout _fetchLayout(SiteNavigationMenuItem siteNavigationMenuItem) {
		UnicodeProperties properties = new UnicodeProperties();

		properties.fastLoad(siteNavigationMenuItem.getTypeSettings());

		String layoutUuid = properties.get("layoutUuid");

		boolean privateLayout = GetterUtil.getBoolean(
			properties.get("privateLayout"));

		if (Validator.isNull(layoutUuid)) {
			return null;
		}

		return LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
			layoutUuid, siteNavigationMenuItem.getGroupId(), privateLayout);
	}

	private int _getDefaultSelectSiteNavigationMenuType() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		if (layout.isPrivateLayout()) {
			SiteNavigationMenu siteNavigationMenu =
				SiteNavigationMenuLocalServiceUtil.fetchSiteNavigationMenu(
					themeDisplay.getScopeGroupId(),
					SiteNavigationConstants.TYPE_PRIVATE);

			if (siteNavigationMenu != null) {
				return SiteNavigationConstants.TYPE_PRIVATE;
			}
		}

		return SiteNavigationConstants.TYPE_PRIMARY;
	}

	private Long _displayStyleGroupId;
	private Integer _navigationMenuType;
	private final HttpServletRequest _request;
	private Long _rootItemId;
	private SiteNavigationMenu _siteNavigationMenu;
	private Long _siteNavigationMenuId;
	private final SiteNavigationSiteMapPortletInstanceConfiguration
		_siteNavigationSiteMapPortletInstanceConfiguration;
	private final ThemeDisplay _themeDisplay;

}