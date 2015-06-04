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

package com.liferay.portlet.myaccount;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.portlet.usersadmin.UsersAdminPortlet;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

/**
 * @author Pei-Jung Lan
 */
public class MyAccountPortlet extends UsersAdminPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if ((renderRequest.getRemoteUser() == null) ||
			!renderRequest.getWindowState().equals(WindowState.MAXIMIZED)) {

			super.render(renderRequest, renderResponse);

			return;
		}

		try {
			User user = PortalUtil.getUser(renderRequest);

			RenderRequestImpl renderRequestImpl =
				(RenderRequestImpl)renderRequest;

			DynamicServletRequest dynamicRequest =
				(DynamicServletRequest)
					renderRequestImpl.getHttpServletRequest();

			dynamicRequest.setParameter(
				"p_u_i_d", String.valueOf(user.getUserId()));

			include(
				"/html/portlet/my_account/edit_user.jsp", renderRequest,
				renderResponse);
		}
		catch (PortalException pe) {
			super.render(renderRequest, renderResponse);
		}
	}

}