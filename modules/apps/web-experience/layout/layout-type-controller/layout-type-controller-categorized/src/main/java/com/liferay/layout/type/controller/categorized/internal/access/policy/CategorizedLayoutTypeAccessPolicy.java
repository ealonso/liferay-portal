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

package com.liferay.layout.type.controller.categorized.internal.access.policy;

import com.liferay.layout.type.controller.categorized.constants.CategorizedLayoutTypeControllerConstants;
import com.liferay.layout.type.controller.categorized.constants.CategorizedLayoutTypeControllerPortletKeys;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.DefaultLayoutTypeAccessPolicyImpl;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {"layout.type=" + CategorizedLayoutTypeControllerConstants.LAYOUT_TYPE_CATEGORIZED},
	service = LayoutTypeAccessPolicy.class
)
public class CategorizedLayoutTypeAccessPolicy
	extends DefaultLayoutTypeAccessPolicyImpl {

	@Override
	protected boolean isAccessAllowedToLayoutPortlet(
		HttpServletRequest request, Layout layout, Portlet portlet) {

		if (portlet.getPortletName().equals(
				CategorizedLayoutTypeControllerPortletKeys.
					LAYOUT_TYPE_CATEGORIZED_PORTLET)) {

			return true;
		}

		return super.isAccessAllowedToLayoutPortlet(request, layout, portlet);
	}

}