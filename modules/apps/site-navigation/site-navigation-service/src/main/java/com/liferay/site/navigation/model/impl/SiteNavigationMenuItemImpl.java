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

package com.liferay.site.navigation.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SiteNavigationMenuItemImpl extends SiteNavigationMenuItemBaseImpl {

	public SiteNavigationMenuItemImpl() {
	}

	public List<SiteNavigationMenuItem> getChildren() {
		return SiteNavigationMenuItemLocalServiceUtil.
			getSiteNavigationMenuItems(
				getSiteNavigationMenuId(), getSiteNavigationMenuItemId());
	}

	public SiteNavigationMenuItemType getSiteNavigationMenuItemType() {
		return SiteNavigationMenuItemLocalServiceUtil.
			getSiteNavigationMenuItemType(this);
	}

}