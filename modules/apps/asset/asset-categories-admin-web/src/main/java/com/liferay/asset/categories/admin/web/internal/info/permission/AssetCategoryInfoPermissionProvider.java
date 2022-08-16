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

package com.liferay.asset.categories.admin.web.internal.info.permission;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.info.permission.provider.InfoPermissionProvider;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = InfoPermissionProvider.class)
public class AssetCategoryInfoPermissionProvider
	implements InfoPermissionProvider<AssetCategory> {

	@Override
	public boolean hasPermission(
		String actionId, long groupId, PermissionChecker permissionChecker) {

		return _portletResourcePermission.contains(
			permissionChecker, groupId, actionId);
	}

	@Reference(target = "(resource.name=com.liferay.asset.categories")
	private PortletResourcePermission _portletResourcePermission;

}