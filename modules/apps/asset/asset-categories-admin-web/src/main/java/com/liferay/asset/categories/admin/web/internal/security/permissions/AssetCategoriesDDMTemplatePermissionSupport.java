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

package com.liferay.asset.categories.admin.web.internal.security.permissions;

import com.liferay.asset.categories.admin.web.internal.constants.AssetCategoriesConstants;
import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;

import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.List;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.asset.kernel.model.AssetCategory",
	service = {
		AssetCategoriesDDMTemplatePermissionSupport.class,
		DDMTemplatePermissionSupport.class
	}
)
public class AssetCategoriesDDMTemplatePermissionSupport
	implements DDMTemplatePermissionSupport {

	@Override
	public String getResourceName(long classNameId) {
		return _RESOURCE_NAME;
	}

	@Activate
	protected void activate() {
		List<String> actions = ResourceActionsUtil.getModelResourceActions(_RESOURCE_NAME);
		if (actions.isEmpty()) {
			ResourceActionLocalServiceUtil.addResourceAction(_RESOURCE_NAME, ActionKeys.DELETE, 2);
			ResourceActionLocalServiceUtil.addResourceAction(_RESOURCE_NAME, ActionKeys.PERMISSIONS, 4);
			ResourceActionLocalServiceUtil.addResourceAction(_RESOURCE_NAME, ActionKeys.UPDATE, 8);
			ResourceActionLocalServiceUtil.addResourceAction(_RESOURCE_NAME, ActionKeys.VIEW, 1);
		}
	}

	private final static String _RESOURCE_NAME =
			"com.liferay.asset.kernel.model.AssetCategory-"
					+ "com.liferay.dynamic.data.mapping.model.DDMTemplate";



}