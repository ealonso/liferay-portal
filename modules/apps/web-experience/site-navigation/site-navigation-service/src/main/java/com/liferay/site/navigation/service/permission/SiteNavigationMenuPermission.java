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

package com.liferay.site.navigation.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = {"model.class.name=com.liferay.site.navigation.model.SiteNavigationMenu"},
	service = BaseModelPermissionChecker.class
)
public class SiteNavigationMenuPermission
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long siteNavigationMenuId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, siteNavigationMenuId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, SiteNavigationMenu.class.getName(),
				siteNavigationMenuId, actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker,
			SiteNavigationMenu siteNavigationMenu, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, siteNavigationMenu, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, SiteNavigationMenu.class.getName(),
				siteNavigationMenu.getSiteNavigationMenuId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long siteNavigationMenuId,
			String actionId)
		throws PortalException {

		Map<Object, Object> permissionChecksMap =
			permissionChecker.getPermissionChecksMap();

		PermissionCacheKey permissionCacheKey = new PermissionCacheKey(
			siteNavigationMenuId, actionId);

		Boolean contains = (Boolean)permissionChecksMap.get(permissionCacheKey);

		if (contains == null) {
			SiteNavigationMenuCacheKey siteNavigationMenuCacheKey =
				new SiteNavigationMenuCacheKey(siteNavigationMenuId);

			SiteNavigationMenu siteNavigationMenu =
				(SiteNavigationMenu)permissionChecksMap.get(
					siteNavigationMenuCacheKey);

			if (siteNavigationMenu == null) {
				siteNavigationMenu =
					_siteNavigationMenuLocalService.getSiteNavigationMenu(
						siteNavigationMenuId);

				permissionChecksMap.put(
					siteNavigationMenuCacheKey, siteNavigationMenu);
			}

			contains = _contains(
				permissionChecker, siteNavigationMenu, actionId);

			permissionChecksMap.put(permissionCacheKey, contains);
		}

		return contains;
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		SiteNavigationMenu siteNavigationMenu, String actionId) {

		Map<Object, Object> permissionChecksMap =
			permissionChecker.getPermissionChecksMap();

		PermissionCacheKey permissionCacheKey = new PermissionCacheKey(
			siteNavigationMenu.getSiteNavigationMenuId(), actionId);

		Boolean contains = (Boolean)permissionChecksMap.get(permissionCacheKey);

		if (contains == null) {
			contains = _contains(
				permissionChecker, siteNavigationMenu, actionId);

			permissionChecksMap.put(permissionCacheKey, contains);
		}

		return contains;
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setSiteNavigationMenuLocalService(
		SiteNavigationMenuLocalService siteNavigationMenuLocalService) {

		_siteNavigationMenuLocalService = siteNavigationMenuLocalService;
	}

	private static boolean _contains(
		PermissionChecker permissionChecker,
		SiteNavigationMenu siteNavigationMenu, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				siteNavigationMenu.getCompanyId(),
				SiteNavigationMenu.class.getName(),
				siteNavigationMenu.getSiteNavigationMenuId(),
				siteNavigationMenu.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			siteNavigationMenu.getGroupId(), SiteNavigationMenu.class.getName(),
			siteNavigationMenu.getSiteNavigationMenuId(), actionId);
	}

	private static SiteNavigationMenuLocalService
		_siteNavigationMenuLocalService;

	private static class PermissionCacheKey {

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof PermissionCacheKey)) {
				return false;
			}

			PermissionCacheKey permissionCacheKey = (PermissionCacheKey)obj;

			if ((_siteNavigationMenuId ==
					permissionCacheKey._siteNavigationMenuId) &&
				Objects.equals(_actionId, permissionCacheKey._actionId)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hash = HashUtil.hash(0, _siteNavigationMenuId);

			return HashUtil.hash(hash, _actionId);
		}

		private PermissionCacheKey(long siteNavigationMenuId, String actionId) {
			_siteNavigationMenuId = siteNavigationMenuId;
			_actionId = actionId;
		}

		private final String _actionId;
		private final long _siteNavigationMenuId;

	}

	private static class SiteNavigationMenuCacheKey {

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof SiteNavigationMenuCacheKey)) {
				return false;
			}

			SiteNavigationMenuCacheKey siteNavigationMenuCacheKey =
				(SiteNavigationMenuCacheKey)obj;

			if (_siteNavigationMenuId ==
					siteNavigationMenuCacheKey._siteNavigationMenuId) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return (int)_siteNavigationMenuId;
		}

		private SiteNavigationMenuCacheKey(long siteNavigationMenuId) {
			_siteNavigationMenuId = siteNavigationMenuId;
		}

		private final long _siteNavigationMenuId;

	}

}