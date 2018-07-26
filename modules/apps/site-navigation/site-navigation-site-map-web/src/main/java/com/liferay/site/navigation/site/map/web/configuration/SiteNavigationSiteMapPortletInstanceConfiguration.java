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

package com.liferay.site.navigation.site.map.web.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Juergen Kappler
 */
@ExtendedObjectClassDefinition(
	category = "navigation",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "com.liferay.site.navigation.site.map.web.configuration.SiteNavigationSiteMapPortletInstanceConfiguration",
	localization = "content/Language",
	name = "site-navigation-site-map-portlet-instance-configuration-name"
)
public interface SiteNavigationSiteMapPortletInstanceConfiguration {

	@Meta.AD(deflt = "0", name = "display-style-group-id", required = false)
	public long displayStyleGroupId();

	@Meta.AD(name = "display-style", required = false)
	public String displayStyle();

	@Meta.AD(deflt = "0", name = "root-item-id", required = false)
	public long rootItemId();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(name = "root-layout-uuid", required = false)
	public String rootLayoutUuid();

	@Meta.AD(deflt = "0", name = "display-depth", required = false)
	public int displayDepth();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(deflt = "false", name = "include-root-in-tree", required = false)
	public boolean includeRootInTree();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(deflt = "false", name = "show-current-page", required = false)
	public boolean showCurrentPage();

	@Meta.AD(name = "site-navigation-menu-id", required = false)
	public long siteNavigationMenuId();

	@Meta.AD(deflt = "1", name = "site-navigation-menu-type", required = false)
	public int siteNavigationMenuType();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(deflt = "false", name = "use-html-title", required = false)
	public boolean useHtmlTitle();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Meta.AD(deflt = "false", name = "show-hidden-pages", required = false)
	public boolean showHiddenPages();

}