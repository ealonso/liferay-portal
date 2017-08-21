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

package com.liferay.asset.publisher.web.util;

import com.liferay.asset.display.template.model.AssetDisplayTemplate;
import com.liferay.asset.display.template.service.AssetDisplayTemplateLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Objects;

import javax.portlet.PortletURL;

/**
 * Provides utility methods to be used from Asset Publisher display templates.
 * This class is injected in the context of Asset Publisher display templates.
 *
 * @author Juan Fernández
 */
public class AssetPublisherHelper {

	public static String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry) {

		return getAssetViewURL(
			liferayPortletRequest, liferayPortletResponse, assetEntry, false);
	}

	public static String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse, AssetEntry assetEntry,
		boolean viewInContext) {

		AssetRenderer<?> assetRenderer = assetEntry.getAssetRenderer();

		return getAssetViewURL(
			liferayPortletRequest, liferayPortletResponse, assetRenderer,
			assetEntry, viewInContext);
	}

	public static String getAssetViewURL(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		AssetRenderer<?> assetRenderer, AssetEntry assetEntry,
		boolean viewInContext) {

		AssetRendererFactory<?> assetRendererFactory =
			assetRenderer.getAssetRendererFactory();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (viewInContext) {
			AssetDisplayTemplate assetDisplayTemplate =
				AssetDisplayTemplateLocalServiceUtil.fetchAssetDisplayTemplate(
					themeDisplay.getScopeGroupId(),
					assetRendererFactory.getClassNameId());

			if (assetDisplayTemplate != null) {
				try {
					Layout layout = LayoutLocalServiceUtil.getLayout(
						assetDisplayTemplate.getPlid());

					StringBundler sb = new StringBundler(5);

					sb.append(
						PortalUtil.getGroupFriendlyURL(
							layout.getLayoutSet(), themeDisplay));
					sb.append("/=");
					sb.append(layout.getFriendlyURL(themeDisplay.getLocale()));
					sb.append(StringPool.SLASH);
					sb.append(assetEntry.getEntryId());

					return sb.toString();
				}
				catch (PortalException pe) {
					_log.error(pe, pe);
				}
			}
		}

		PortletURL viewFullContentURL =
			liferayPortletResponse.createRenderURL();

		viewFullContentURL.setParameter("mvcPath", "/view_content.jsp");
		viewFullContentURL.setParameter(
			"assetEntryId", String.valueOf(assetEntry.getEntryId()));

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		int cur = ParamUtil.getInteger(liferayPortletRequest, "cur");
		int delta = ParamUtil.getInteger(liferayPortletRequest, "delta");
		boolean resetCur = ParamUtil.getBoolean(
			liferayPortletRequest, "resetCur");

		redirectURL.setParameter("cur", String.valueOf(cur));

		if (delta > 0) {
			redirectURL.setParameter("delta", String.valueOf(delta));
		}

		redirectURL.setParameter("resetCur", String.valueOf(resetCur));
		redirectURL.setParameter(
			"assetEntryId", String.valueOf(assetEntry.getEntryId()));

		viewFullContentURL.setParameter("redirect", redirectURL.toString());

		viewFullContentURL.setParameter("type", assetRendererFactory.getType());

		if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
			if (assetRenderer.getGroupId() != themeDisplay.getScopeGroupId()) {
				viewFullContentURL.setParameter(
					"groupId", String.valueOf(assetRenderer.getGroupId()));
			}

			viewFullContentURL.setParameter(
				"urlTitle", assetRenderer.getUrlTitle());
		}

		String viewURL = null;

		if (viewInContext) {
			try {
				String noSuchEntryRedirect = viewFullContentURL.toString();

				viewURL = assetRenderer.getURLViewInContext(
					liferayPortletRequest, liferayPortletResponse,
					noSuchEntryRedirect);

				if (Validator.isNotNull(viewURL) &&
					!Objects.equals(viewURL, noSuchEntryRedirect)) {

					viewURL = HttpUtil.setParameter(
						viewURL, "redirect",
						PortalUtil.getCurrentURL(liferayPortletRequest));
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (Validator.isNull(viewURL)) {
			viewURL = viewFullContentURL.toString();
		}

		return viewURL;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetPublisherHelper.class);

}