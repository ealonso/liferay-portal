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

package com.liferay.layout.type.controller.asset.display.internal.controller;

import com.liferay.layout.type.controller.asset.display.constants.AssetDisplayLayoutTypeControllerConstants;
import com.liferay.layout.type.controller.asset.display.internal.constants.AssetDisplayLayoutTypeControllerPortletKeys;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.BaseLayoutTypeControllerImpl;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portlet.RenderRequestFactory;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.portlet.RenderResponseFactory;
import com.liferay.taglib.servlet.PipingServletResponse;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {"layout.type=" + AssetDisplayLayoutTypeControllerConstants.LAYOUT_TYPE_ASSET_DISPLAY},
	service = LayoutTypeController.class
)
public class AssetDisplayLayoutTypeController
	extends BaseLayoutTypeControllerImpl {

	@Override
	public String getType() {
		return LayoutConstants.TYPE_PORTLET;
	}

	@Override
	public String getURL() {
		return _URL;
	}

	@Override
	public boolean includeLayoutContent(
			HttpServletRequest request, HttpServletResponse response,
			Layout layout)
		throws Exception {

		Portlet portlet = _portletLocalService.getPortletById(
			AssetDisplayLayoutTypeControllerPortletKeys.
				ASSET_DISPLAY_LAYOUT_TYPE_CONTROLLER);

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getPortletPreferences(
				request, portlet.getPortletId());

		PortletRequest portletRequest = RenderRequestFactory.create(
			request, portlet, null, null, WindowState.NORMAL, PortletMode.VIEW,
			portletPreferences);

		PortletResponse portletResponse = RenderResponseFactory.create(
			(RenderRequestImpl)portletRequest, response);

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST, portletRequest);

		request.setAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE, portletResponse);

		request.setAttribute(
			PortletRequest.LIFECYCLE_PHASE, PortletRequest.RENDER_PHASE);

		return super.includeLayoutContent(request, response, layout);
	}

	@Override
	public boolean isBrowsable() {
		return true;
	}

	@Override
	public boolean isFirstPageable() {
		return true;
	}

	@Override
	public boolean isFullPageDisplayable() {
		return true;
	}

	@Override
	public boolean isParentable() {
		return true;
	}

	@Override
	public boolean isSitemapable() {
		return false;
	}

	@Override
	public boolean isURLFriendliable() {
		return true;
	}

	@Override
	protected ServletResponse createServletResponse(
		HttpServletResponse response, UnsyncStringWriter unsyncStringWriter) {

		return new PipingServletResponse(response, unsyncStringWriter);
	}

	@Override
	protected String getEditPage() {
		return null;
	}

	@Override
	protected String getViewPage() {
		return null;
	}

	private static final String _URL =
		"${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}" +
			"&p_v_l_s_g_id=${liferay:pvlsgid}&assetDisplayTemplateId=" +
				"${assetDisplayTemplateId}";

	@Reference
	private PortletLocalService _portletLocalService;

}