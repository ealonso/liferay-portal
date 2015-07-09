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

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Eudaldo Alonso
 */
@OSGiBeanProperties(
	property = {"model.class.name=com.liferay.portal.model.LayoutSet"}
)
public class LayoutSetPermissionImpl
	implements BaseModelPermissionChecker, LayoutSetPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker, long layoutSetId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, layoutSetId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LayoutSet.class.getName(), layoutSetId,
				actionId);
		}
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Layout layout, String actionId)
		throws PortalException {

		return contains(permissionChecker, layout.getLayoutSet(), actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, LayoutSet layoutSet,
		String actionId) {

		return permissionChecker.hasPermission(
			layoutSet.getGroupId(), LayoutSet.class.getName(),
			layoutSet.getLayoutSetId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long layoutSetId,
			String actionId)
		throws PortalException {

		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
			layoutSetId);

		return contains(permissionChecker, layoutSet, actionId);
	}

	@Override
	public boolean isViewableByGuest(Layout layout) throws PortalException {
		return hasGuestViewPermission(layout.getLayoutSet());
	}

	@Override
	public boolean isViewableByGuest(LayoutSet layoutSet)
		throws PortalException {

		return hasGuestViewPermission(layoutSet);
	}

	protected static boolean hasGuestViewPermission(LayoutSet layoutSet)
		throws PortalException {

		Role role = RoleLocalServiceUtil.getRole(
			layoutSet.getCompanyId(), RoleConstants.GUEST);

		return ResourcePermissionLocalServiceUtil.hasResourcePermission(
			layoutSet.getCompanyId(), LayoutSet.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(layoutSet.getLayoutSetId()), role.getRoleId(),
			ActionKeys.VIEW);
	}

}