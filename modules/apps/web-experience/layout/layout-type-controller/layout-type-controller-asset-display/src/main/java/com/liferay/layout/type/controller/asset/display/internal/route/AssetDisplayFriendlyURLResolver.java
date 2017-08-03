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

package com.liferay.layout.type.controller.asset.display.internal.route;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.layout.type.controller.asset.display.internal.constants.AssetDisplayLayoutTypeControllerWebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURLComposite;
import com.liferay.portal.kernel.portlet.FriendlyURLResolver;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = FriendlyURLResolver.class)
public class AssetDisplayFriendlyURLResolver implements FriendlyURLResolver {

	public static final String FRIENDLY_URL_SEPARATOR = "/=";

	@Override
	public String getActualURL(
			long companyId, long groupId, boolean privateLayout,
			String mainPath, String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		String url = friendlyURL.substring(FRIENDLY_URL_SEPARATOR.length());

		long assetEntryId = GetterUtil.getLong(
			url.substring(url.lastIndexOf(StringPool.SLASH) + 1));

		AssetEntry assetEntry = _assetEntryLocalService.getAssetEntry(
			assetEntryId);

		HttpServletRequest request = (HttpServletRequest)requestContext.get(
			"request");

		request.setAttribute(
			AssetDisplayLayoutTypeControllerWebKeys.ASSET_ENTRY, assetEntry);

		String layoutFriendlyURL = url.substring(
			0, url.lastIndexOf(StringPool.SLASH));

		Layout layout = _layoutLocalService.getFriendlyURLLayout(
			groupId, privateLayout, layoutFriendlyURL);

		return _portal.getLayoutActualURL(layout);
	}

	@Override
	public LayoutFriendlyURLComposite getLayoutFriendlyURLComposite(
			long companyId, long groupId, boolean privateLayout,
			String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		String url = friendlyURL.substring(FRIENDLY_URL_SEPARATOR.length());

		String layoutFriendlyURL = url.substring(
			0, url.lastIndexOf(StringPool.SLASH));

		Layout layout = _layoutLocalService.getFriendlyURLLayout(
			groupId, privateLayout, layoutFriendlyURL);

		return new LayoutFriendlyURLComposite(layout, friendlyURL);
	}

	@Override
	public String getURLSeparator() {
		return FRIENDLY_URL_SEPARATOR;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private Http _http;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}