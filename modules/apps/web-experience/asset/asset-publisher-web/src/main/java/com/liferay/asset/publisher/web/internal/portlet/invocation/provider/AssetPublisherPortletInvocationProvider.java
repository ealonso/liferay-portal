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

package com.liferay.asset.publisher.web.internal.portlet.invocation.provider;

import com.liferay.asset.constants.AssetWebKeys;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.constants.AssetPublisherWebKeys;
import com.liferay.asset.publisher.web.configuration.AssetPublisherPortletInstanceConfiguration;
import com.liferay.asset.publisher.web.configuration.AssetPublisherWebConfiguration;
import com.liferay.asset.publisher.web.internal.action.AssetEntryActionRegistry;
import com.liferay.asset.publisher.web.internal.util.AssetPublisherWebUtil;
import com.liferay.asset.publisher.web.util.AssetPublisherCustomizer;
import com.liferay.asset.publisher.web.util.AssetPublisherCustomizerRegistry;
import com.liferay.asset.util.AssetHelper;
import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.invocation.provider.PortletInvocationProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.DirectRequestDispatcherFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.servlet.PipingServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;
import javax.portlet.PortletURL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true)
public class AssetPublisherPortletInvocationProvider implements
	PortletInvocationProvider {

	@Override
	public PortletURL getConfigurationPortletURL() throws PortalException {
		return null;
	}

	@Override
	public String getFragmentInvocationAlias() {
		return "asset-publisher";
	}

	@Override
	public String[] getRequiredAttributes() {
		return new String[0];
	}

	@Override
	public String render(
		HttpServletRequest request, HttpServletResponse response,
		JSONObject contextJSONObject) throws FragmentEntryContentException {

		try {
			request.setAttribute(
				AssetPublisherWebKeys.ASSET_ENTRY_ACTION_REGISTRY,
				assetEntryActionRegistry);

			request.setAttribute(AssetWebKeys.ASSET_HELPER, assetHelper);

			request.setAttribute(
				AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_UTIL,
				assetPublisherWebUtil);

			String rootPortletId = PortletIdCodec.decodePortletName(
				portal.getPortletId(request));

			AssetPublisherCustomizer assetPublisherCustomizer =
				assetPublisherCustomizerRegistry.getAssetPublisherCustomizer(
					rootPortletId);

			request.setAttribute(
				AssetPublisherWebKeys.ASSET_PUBLISHER_CUSTOMIZER,
				assetPublisherCustomizer);

			request.setAttribute(
				AssetPublisherWebKeys.ASSET_PUBLISHER_WEB_CONFIGURATION,
				assetPublisherWebConfiguration);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			AssetPublisherPortletInstanceConfiguration
				assetPublisherPortletInstanceConfiguration =
				portletDisplay.getPortletInstanceConfiguration(
					AssetPublisherPortletInstanceConfiguration.class);

			request.setAttribute(
				AssetPublisherWebKeys.
					ASSET_PUBLISHER_PORTLET_INSTANCE_CONFIGURATION,
				assetPublisherPortletInstanceConfiguration);

			request.setAttribute(
				WebKeys.SINGLE_PAGE_APPLICATION_CLEAR_CACHE, Boolean.TRUE);

			RequestDispatcher requestDispatcher =
				DirectRequestDispatcherFactoryUtil.getRequestDispatcher(
					servletContext, "/view.jsp");

			BufferCacheServletResponse bufferResponse =
				new BufferCacheServletResponse(response);

			requestDispatcher.include(request, bufferResponse);

			return bufferResponse.getString();
		}
		catch (Exception e) {
			throw new FragmentEntryContentException(e);
		}
	}

	@Reference
	protected AssetEntryActionRegistry assetEntryActionRegistry;

	@Reference
	protected AssetHelper assetHelper;

	@Reference
	protected AssetPublisherCustomizerRegistry assetPublisherCustomizerRegistry;

	protected AssetPublisherWebConfiguration assetPublisherWebConfiguration;

	@Reference
	protected AssetPublisherWebUtil assetPublisherWebUtil;

	@Reference
	protected Portal portal;

	@Reference(target = "(javax.portlet.name=" + AssetPublisherPortletKeys.ASSET_PUBLISHER + ")")
	protected Portlet assetPublisherPortlet;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.asset.publisher.web)")
	protected ServletContext servletContext;
}
