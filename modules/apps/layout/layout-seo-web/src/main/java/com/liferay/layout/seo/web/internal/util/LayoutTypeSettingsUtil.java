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

package com.liferay.layout.seo.web.internal.util;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Alejandro Tardín
 */
public class LayoutTypeSettingsUtil {

	public static Layout updateTypeSettings(
			Layout layout, LayoutService layoutService,
			UnicodeProperties typeSettingsUnicodeProperties)
		throws Exception {

		UnicodeProperties layoutTypeSettingsUnicodeProperties =
			layout.getTypeSettingsProperties();

		if (layout.isTypePortlet()) {
			layoutTypeSettingsUnicodeProperties.putAll(
				typeSettingsUnicodeProperties);

			boolean layoutCustomizable = GetterUtil.getBoolean(
				layoutTypeSettingsUnicodeProperties.get(
					LayoutConstants.CUSTOMIZABLE_LAYOUT));

			if (!layoutCustomizable) {
				LayoutTypePortlet layoutTypePortlet =
					(LayoutTypePortlet)layout.getLayoutType();

				layoutTypePortlet.removeCustomization(
					layoutTypeSettingsUnicodeProperties);
			}

			return layoutService.updateLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(),
				layoutTypeSettingsUnicodeProperties.toString());
		}

		layoutTypeSettingsUnicodeProperties.putAll(
			typeSettingsUnicodeProperties);

		layoutTypeSettingsUnicodeProperties.putAll(
			layout.getTypeSettingsProperties());

		return layoutService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layoutTypeSettingsUnicodeProperties.toString());
	}

}