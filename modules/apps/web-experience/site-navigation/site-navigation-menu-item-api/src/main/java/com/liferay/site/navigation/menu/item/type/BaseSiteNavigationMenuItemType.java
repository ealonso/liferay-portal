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

package com.liferay.site.navigation.menu.item.type;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.site.navigation.exception.InvalidSiteNavigationMenuItemSettingsException;
import com.liferay.site.navigation.menu.item.layout.constants.SiteNavigationMenuItemTypeConstants;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;

/**
 * @author Pavel Savinov
 */
public abstract class BaseSiteNavigationMenuItemType
	implements SiteNavigationMenuItemType {

	@Override
	public void validate(String typeSettings)
		throws InvalidSiteNavigationMenuItemSettingsException {

		UnicodeProperties properties = new UnicodeProperties(true);

		properties.fastLoad(typeSettings);

		if (!properties.containsKey(
				SiteNavigationMenuItemTypeConstants.NAME_FIELD)) {

			return;
		}

		String name = properties.get(
			SiteNavigationMenuItemTypeConstants.NAME_FIELD);

		if (name.length() >
				SiteNavigationMenuItemTypeConstants.
					SITE_NAVIGATION_MENU_ITEM_NAME_MAX_LENGTH) {

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			throw new InvalidSiteNavigationMenuItemSettingsException(
				LanguageUtil.format(
					serviceContext.getRequest(),
					"maximum-name-length-x-exceeded",
					SiteNavigationMenuItemTypeConstants.
						SITE_NAVIGATION_MENU_ITEM_NAME_MAX_LENGTH));
		}
	}

}