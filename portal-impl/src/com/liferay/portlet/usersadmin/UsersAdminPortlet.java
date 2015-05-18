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

package com.liferay.portlet.usersadmin;

import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.CompanyMaxUsersException;
import com.liferay.portal.ContactBirthdayException;
import com.liferay.portal.ContactNameException;
import com.liferay.portal.DuplicateOrganizationException;
import com.liferay.portal.EmailAddressException;
import com.liferay.portal.GroupFriendlyURLException;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.NoSuchOrgLaborException;
import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.OrganizationNameException;
import com.liferay.portal.OrganizationParentException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.RequiredOrganizationException;
import com.liferay.portal.RequiredUserException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserFieldException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserReminderQueryException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.UserSmsException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.membershippolicy.MembershipPolicyException;
import com.liferay.portal.service.OrgLaborServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Pei-Jung Lan
 */
public class UsersAdminPortlet extends MVCPortlet {

	public void deactivateUsers(
		ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {

		try {
			long[] deleteUserIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteUserIds"), 0L);

			for (long deleteUserId : deleteUserIds) {
				int status = WorkflowConstants.STATUS_INACTIVE;

				UserServiceUtil.updateStatus(
					deleteUserId, status, new ServiceContext());
			}

			sendEditUserRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			String password1 = actionRequest.getParameter("password1");
			String password2 = actionRequest.getParameter("password2");

			boolean submittedPassword = false;

			if (e instanceof RequiredUserException ||
				!Validator.isBlank(password1) ||
				!Validator.isBlank(password2)) {

				handleEditUserException(
					actionRequest, actionResponse, submittedPassword);
			}
			else {
				throw e;
			}
		}
	}

	public void deleteOrgLabor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long orgLaborId = ParamUtil.getLong(actionRequest, "orgLaborId");

		OrgLaborServiceUtil.deleteOrgLabor(orgLaborId);
	}

	public void deleteRole(
		ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {

		try {
			User user = PortalUtil.getSelectedUser(actionRequest);

			long roleId = ParamUtil.getLong(actionRequest, "roleId");

			UserServiceUtil.deleteRoleUser(roleId, user.getUserId());

			sendEditUserRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			String password1 = actionRequest.getParameter("password1");
			String password2 = actionRequest.getParameter("password2");

			boolean submittedPassword = false;

			if (e instanceof RequiredUserException ||
				!Validator.isBlank(password1) ||
				!Validator.isBlank(password2)) {

				handleEditUserException(
					actionRequest, actionResponse, submittedPassword);
			}
			else {
				throw e;
			}
		}
	}

	public void deleteUsers(
		ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {

		try {
			long[] deleteUserIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteUserIds"), 0L);

			for (long deleteUserId : deleteUserIds) {
				UserServiceUtil.deleteUser(deleteUserId);
			}

			sendEditUserRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			String password1 = actionRequest.getParameter("password1");
			String password2 = actionRequest.getParameter("password2");

			boolean submittedPassword = false;

			if (e instanceof RequiredUserException ||
				!Validator.isBlank(password1) ||
				!Validator.isBlank(password2)) {

				handleEditUserException(
					actionRequest, actionResponse, submittedPassword);
			}
			else {
				throw e;
			}
		}
	}

	public void restoreUsers(
		ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {

		try {
			long[] deleteUserIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "deleteUserIds"), 0L);

			for (long deleteUserId : deleteUserIds) {
				int status = WorkflowConstants.STATUS_APPROVED;

				UserServiceUtil.updateStatus(
					deleteUserId, status, new ServiceContext());
			}

			sendEditUserRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			String password1 = actionRequest.getParameter("password1");
			String password2 = actionRequest.getParameter("password2");

			boolean submittedPassword = false;

			if (e instanceof RequiredUserException ||
				!Validator.isBlank(password1) ||
				!Validator.isBlank(password2)) {

				handleEditUserException(
					actionRequest, actionResponse, submittedPassword);
			}
			else {
				throw e;
			}
		}
	}

	public void updateLockout(
		ActionRequest actionRequest,
		ActionResponse actionResponse) throws Exception {

		try {
			User user = PortalUtil.getSelectedUser(actionRequest);

			UserServiceUtil.updateLockoutById(user.getUserId(), false);

			ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (user != null) {
				redirect = HttpUtil.setParameter(
					redirect, actionResponse.getNamespace() + "p_u_i_d",
					user.getUserId());
			}

			Group scopeGroup = themeDisplay.getScopeGroup();

			if (scopeGroup.isUser() &&
				(UserLocalServiceUtil.fetchUserById(
					scopeGroup.getClassPK()) == null)) {

				redirect = HttpUtil.setParameter(redirect, "doAsGroupId", 0);
				redirect = HttpUtil.setParameter(redirect, "refererPlid", 0);
			}

			actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
		}
		catch (Exception e) {
			String password1 = actionRequest.getParameter("password1");
			String password2 = actionRequest.getParameter("password2");

			boolean submittedPassword = false;

			if (e instanceof RequiredUserException ||
				!Validator.isBlank(password1) ||
				!Validator.isBlank(password2)) {

				handleEditUserException(
					actionRequest, actionResponse, submittedPassword);
			}
			else {
				throw e;
			}
		}
	}

	public void updateOrganizationUserGroups(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(organizationId);

		long groupId = organization.getGroupId();

		long[] addUserGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserGroupIds"), 0L);
		long[] removeUserGroupIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserGroupIds"), 0L);

		UserGroupServiceUtil.addGroupUserGroups(groupId, addUserGroupIds);
		UserGroupServiceUtil.unsetGroupUserGroups(groupId, removeUserGroupIds);

		String redirect = ParamUtil.getString(
			actionRequest, "assignmentsRedirect");

		actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
	}

	public void updateOrganizationUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		UserServiceUtil.addOrganizationUsers(organizationId, addUserIds);
		UserServiceUtil.unsetOrganizationUsers(organizationId, removeUserIds);

		String redirect = ParamUtil.getString(
			actionRequest, "assignmentsRedirect");

		actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
	}

	public void updateOrgLabor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long orgLaborId = ParamUtil.getLong(actionRequest, "orgLaborId");

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");
		long typeId = ParamUtil.getLong(actionRequest, "typeId");

		int sunOpen = ParamUtil.getInteger(actionRequest, "sunOpen");
		int sunClose = ParamUtil.getInteger(actionRequest, "sunClose");

		int monOpen = ParamUtil.getInteger(actionRequest, "monOpen");
		int monClose = ParamUtil.getInteger(actionRequest, "monClose");

		int tueOpen = ParamUtil.getInteger(actionRequest, "tueOpen");
		int tueClose = ParamUtil.getInteger(actionRequest, "tueClose");

		int wedOpen = ParamUtil.getInteger(actionRequest, "wedOpen");
		int wedClose = ParamUtil.getInteger(actionRequest, "wedClose");

		int thuOpen = ParamUtil.getInteger(actionRequest, "thuOpen");
		int thuClose = ParamUtil.getInteger(actionRequest, "thuClose");

		int friOpen = ParamUtil.getInteger(actionRequest, "friOpen");
		int friClose = ParamUtil.getInteger(actionRequest, "friClose");

		int satOpen = ParamUtil.getInteger(actionRequest, "satOpen");
		int satClose = ParamUtil.getInteger(actionRequest, "satClose");

		if (orgLaborId <= 0) {

			// Add organization labor

			OrgLaborServiceUtil.addOrgLabor(
				organizationId, typeId, sunOpen, sunClose, monOpen, monClose,
				tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose,
				friOpen, friClose, satOpen, satClose);
		}
		else {

			// Update organization labor

			OrgLaborServiceUtil.updateOrgLabor(
				orgLaborId, typeId, sunOpen, sunClose, monOpen, monClose,
				tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose,
				friOpen, friClose, satOpen, satClose);
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchOrganizationException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchOrgLaborException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, NoSuchUserException.class.getName()) ||
			SessionErrors.contains(
				renderRequest, PrincipalException.class.getName())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	protected void handleEditUserException(
		ActionRequest actionRequest, ActionResponse actionResponse,
		boolean submittedPassword) throws Exception {

		String redirect = PortalUtil.escapeRedirect(
			ParamUtil.getString(actionRequest, "redirect"));

		if (submittedPassword) {
			User user = PortalUtil.getSelectedUser(actionRequest);

			redirect = HttpUtil.setParameter(
				redirect, actionResponse.getNamespace() + "p_u_i_d",
				user.getUserId());
		}

		if (Validator.isNotNull(redirect)) {
			actionResponse.sendRedirect(redirect);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof AddressCityException ||
			cause instanceof AddressStreetException ||
			cause instanceof AddressZipException ||
			cause instanceof CompanyMaxUsersException ||
			cause instanceof ContactBirthdayException ||
			cause instanceof ContactNameException ||
			cause instanceof DuplicateOrganizationException ||
			cause instanceof EmailAddressException ||
			cause instanceof GroupFriendlyURLException ||
			cause instanceof MembershipPolicyException ||
			cause instanceof NoSuchCountryException ||
			cause instanceof NoSuchListTypeException ||
			cause instanceof NoSuchOrgLaborException ||
			cause instanceof NoSuchRegionException ||
			cause instanceof NoSuchUserException ||
			cause instanceof OrganizationNameException ||
			cause instanceof OrganizationParentException ||
			cause instanceof PhoneNumberException ||
			cause instanceof RequiredOrganizationException ||
			cause instanceof RequiredUserException ||
			cause instanceof UserEmailAddressException ||
			cause instanceof UserFieldException ||
			cause instanceof UserIdException ||
			cause instanceof UserPasswordException ||
			cause instanceof UserReminderQueryException ||
			cause instanceof UserScreenNameException ||
			cause instanceof UserSmsException ||
			cause instanceof WebsiteURLException) {

			return true;
		}

		return false;
	}

	protected void sendEditUserRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		Group scopeGroup = themeDisplay.getScopeGroup();

		if (scopeGroup.isUser() &&
			(UserLocalServiceUtil.fetchUserById(
				scopeGroup.getClassPK()) == null)) {

			redirect = HttpUtil.setParameter(redirect, "doAsGroupId", 0);
			redirect = HttpUtil.setParameter(redirect, "refererPlid", 0);
		}

		actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
	}

}