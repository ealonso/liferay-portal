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
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Eudaldo Alonso
 */
public class LayoutSetPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long layoutSetId,
			String actionId)
		throws PortalException {

		getLayoutSetPermission().check(
			permissionChecker, layoutSetId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Layout layout, String actionId)
		throws PortalException {

		return getLayoutSetPermission().contains(
			permissionChecker, layout.getLayoutSet(), actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LayoutSet layoutSet,
		String actionId) {

		return getLayoutSetPermission().contains(
			permissionChecker, layoutSet, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long layoutSetId,
			String actionId)
		throws PortalException {

		return getLayoutSetPermission().contains(
			permissionChecker, layoutSetId, actionId);
	}

	public static LayoutSetPermission getLayoutSetPermission() {
		PortalRuntimePermission.checkGetBeanProperty(
			LayoutSetPermissionUtil.class);

		return _layoutSetPermission;
	}

	public static boolean isViewableByGuest(Layout layout)
		throws PortalException {

		return getLayoutSetPermission().isViewableByGuest(layout);
	}

	public static boolean isViewableByGuest(LayoutSet layoutSet)
		throws PortalException {

		return getLayoutSetPermission().isViewableByGuest(layoutSet);
	}

	public void setLayoutSetPermission(
		LayoutSetPermission layoutSetPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_layoutSetPermission = layoutSetPermission;
	}

	private static LayoutSetPermission _layoutSetPermission;

}