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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portal.util.LayoutListUtil;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalServiceUtil;
import com.liferay.site.navigation.site.map.web.configuration.SiteNavigationSiteMapPortletInstanceConfiguration;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;

import java.util.Collections;
import java.util.List;

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
			SiteNavigationMenuLocalServiceUtil.fetchPrimarySiteNavigationMenu(
				_themeDisplay.getScopeGroupId());

		if (primarySiteNavigationMenu == null) {
			return Collections.emptyList();
		}

		return
			SiteNavigationMenuItemLocalServiceUtil.getSiteNavigationMenuItems(
				primarySiteNavigationMenu.getSiteNavigationMenuId(),
				getRootItemId());
	}

	public SiteNavigationSiteMapPortletInstanceConfiguration
		getSiteNavigationSiteMapPortletInstanceConfiguration() {

		return _siteNavigationSiteMapPortletInstanceConfiguration;
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

	private Long _displayStyleGroupId;
	private final HttpServletRequest _request;
	private Long _rootItemId;
	private final SiteNavigationSiteMapPortletInstanceConfiguration
		_siteNavigationSiteMapPortletInstanceConfiguration;
	private final ThemeDisplay _themeDisplay;

}