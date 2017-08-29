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

import com.liferay.asset.display.template.model.AssetDisplayTemplate;
import com.liferay.asset.display.template.service.AssetDisplayTemplateLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.dynamic.data.mapping.kernel.DDMTemplate;
import com.liferay.dynamic.data.mapping.kernel.DDMTemplateManager;
import com.liferay.layout.type.controller.asset.display.constants.AssetDisplayLayoutTypeControllerConstants;
import com.liferay.layout.type.controller.asset.display.internal.constants.AssetDisplayLayoutTypeControllerPortletKeys;
import com.liferay.layout.type.controller.asset.display.internal.constants.AssetDisplayLayoutTypeControllerWebKeys;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypeController;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.impl.BaseLayoutTypeControllerImpl;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.RenderRequestFactory;
import com.liferay.portlet.RenderRequestImpl;
import com.liferay.portlet.RenderResponseFactory;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;

import javax.servlet.ServletContext;
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
	protected void addAttributes(HttpServletRequest request) {
		Map<String, Object> contextObjects = new HashMap<>();

		long assetDisplayTemplateId = ParamUtil.getLong(
			request, "assetDisplayTemplateId");

		AssetEntry assetEntry = (AssetEntry)request.getAttribute(
			AssetDisplayLayoutTypeControllerWebKeys.ASSET_ENTRY);

		AssetDisplayTemplate assetDisplayTemplate =
			_assetDisplayTemplateLocalService.fetchAssetDisplayTemplate(
				assetDisplayTemplateId);

		if (assetDisplayTemplate != null) {
			long classNameId = _portal.getClassNameId(
				AssetDisplayTemplate.class);

			DDMTemplate ddmTemplate = _ddmTemplateManager.fetchTemplate(
				assetDisplayTemplate.getGroupId(), classNameId,
				String.valueOf(assetDisplayTemplateId));

			request.setAttribute(WebKeys.TEMPLATE, ddmTemplate);

			List<AssetEntry> entries = new ArrayList<>();

			entries.add(assetEntry);

			request.setAttribute(
				AssetDisplayLayoutTypeControllerWebKeys.ENTRIES, entries);

			contextObjects.put("assetEntry", assetEntry);
		}

		request.setAttribute(
			AssetDisplayLayoutTypeControllerWebKeys.CONTEXT_OBJECTS,
			contextObjects);

		super.addAttributes(request);
	}

	@Override
	protected ServletResponse createServletResponse(
		HttpServletResponse response, UnsyncStringWriter unsyncStringWriter) {

		return new PipingServletResponse(response, unsyncStringWriter);
	}

	@Override
	protected String getEditPage() {
		return _EDIT_PAGE;
	}

	@Override
	protected String getViewPage() {
		return _VIEW_PAGE;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.layout.type.controller.asset.display)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	private static final String _EDIT_PAGE = "/layout/edit/asset_display.jsp";

	private static final String _URL =
		"${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}" +
			"&p_v_l_s_g_id=${liferay:pvlsgid}&assetDisplayTemplateId=" +
				"${assetDisplayTemplateId}";

	private static final String _VIEW_PAGE = "/layout/view/asset_display.jsp";

	@Reference
	private AssetDisplayTemplateLocalService _assetDisplayTemplateLocalService;

	@Reference
	private DDMTemplateManager _ddmTemplateManager;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

}