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

package com.liferay.portlet.configuration.icon.configuration;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.configuration.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLImpl;

import javax.portlet.PortletRequest;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ConfigurationPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public ConfigurationPortletConfigurationIcon(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getCssClass() {
		return "portlet-configuration portlet-configuration-icon";
	}

	@Override
	public String getImage() {
		return "../aui/wrench";
	}

	@Override
	public String getMessage() {
		return "configuration";
	}

	@Override
	public String getMethod() {
		return "get";
	}

	@Override
	public String getOnClick() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		StringBuilder sb = new StringBuilder(11);

		sb.append("Liferay.Portlet.openWindow('#p_p_id_");
		sb.append(portletDisplay.getId());
		sb.append("_', '");
		sb.append(portletDisplay.getId());
		sb.append("', '");
		sb.append(HtmlUtil.escapeJS(getURL()));
		sb.append("', '");
		sb.append(portletDisplay.getNamespace());
		sb.append("', '");
		sb.append(LanguageUtil.get(themeDisplay.getLocale(), "configuration"));
		sb.append("'); return false;");

		return sb.toString();
	}

	@Override
	public String getURL() {
		Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

		long plid = themeDisplay.getPlid();

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletURLImpl urlConfiguration = new PortletURLImpl(
			request, PortletKeys.PORTLET_CONFIGURATION, plid,
			PortletRequest.RENDER_PHASE);

		try {
			urlConfiguration.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException wse) {
			if (_log.isDebugEnabled()) {
				_log.debug(wse, wse);
			}
		}

		urlConfiguration.setEscapeXml(false);

		Layout layout = themeDisplay.getLayout();
		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (portlet.getConfigurationActionInstance() != null) {
			urlConfiguration.setParameter(
				"mvcPath",
				"/html/portlet/portlet_configuration/edit_configuration.jsp");

			String settingsScope = (String)request.getAttribute(
				WebKeys.SETTINGS_SCOPE);

			if (Validator.isNotNull(settingsScope)) {
				urlConfiguration.setParameter("settingsScope", settingsScope);
			}
		}
		else {
			try {
				if (PortletPermissionUtil.contains(
						permissionChecker, layout, portletDisplay.getId(),
						ActionKeys.PERMISSIONS)) {

					urlConfiguration.setParameter(
						"mvcPath",
						"/html/portlet/portlet_configuration/" +
							"edit_permissions.jsp");
				}
				else {
					urlConfiguration.setParameter(
						"mvcPath",
						"/html/portlet/portlet_configuration/edit_sharing.jsp");
				}
			}
			catch (PortalException pe) {
				if (_log.isDebugEnabled()) {
					_log.debug(pe, pe);
				}
			}
		}

		String currentURL = PortalUtil.getCurrentURL(request);

		urlConfiguration.setParameter("redirect", currentURL);
		urlConfiguration.setParameter("returnToFullPageURL", currentURL);
		urlConfiguration.setParameter(
			"portletResource", portletDisplay.getId());
		urlConfiguration.setParameter(
			"resourcePrimKey",
			PortletPermissionUtil.getPrimaryKey(plid, portlet.getPortletId()));

		return urlConfiguration.toString() + "&" +
			PortalUtil.getPortletNamespace(PortletKeys.PORTLET_CONFIGURATION);
	}

	@Override
	public boolean isShow() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.isShowConfigurationIcon();
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConfigurationPortletConfigurationIcon.class);

}