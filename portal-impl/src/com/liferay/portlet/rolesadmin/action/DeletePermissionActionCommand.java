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

package com.liferay.portlet.rolesadmin.action;

import com.liferay.portal.RolePermissionsException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.ResourceBlockLocalServiceUtil;
import com.liferay.portal.service.ResourceBlockServiceUtil;
import com.liferay.portal.service.ResourcePermissionServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Connor McKay
 */
public class DeletePermissionActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ActionRequest actionRequest = (ActionRequest)portletRequest;
		ActionResponse actionResponse = (ActionResponse)portletResponse;

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long roleId = ParamUtil.getLong(actionRequest, "roleId");
		String name = ParamUtil.getString(actionRequest, "name");
		int scope = ParamUtil.getInteger(actionRequest, "scope");
		String primKey = ParamUtil.getString(actionRequest, "primKey");
		String actionId = ParamUtil.getString(actionRequest, "actionId");

		Role role = RoleLocalServiceUtil.getRole(roleId);

		String roleName = role.getName();

		if (roleName.equals(RoleConstants.ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.ORGANIZATION_OWNER) ||
			roleName.equals(RoleConstants.OWNER) ||
			roleName.equals(RoleConstants.SITE_ADMINISTRATOR) ||
			roleName.equals(RoleConstants.SITE_OWNER)) {

			throw new RolePermissionsException(roleName);
		}

		if (ResourceBlockLocalServiceUtil.isSupported(name)) {
			if (scope == ResourceConstants.SCOPE_GROUP) {
				ResourceBlockServiceUtil.removeGroupScopePermission(
					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
					GetterUtil.getLong(primKey), name, roleId, actionId);
			}
			else {
				ResourceBlockServiceUtil.removeCompanyScopePermission(
					themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
					name, roleId, actionId);
			}
		}
		else {
			ResourcePermissionServiceUtil.removeResourcePermission(
				themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(),
				name, scope, primKey, roleId, actionId);
		}

		// Send redirect

		SessionMessages.add(actionRequest, "permissionDeleted");

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

}