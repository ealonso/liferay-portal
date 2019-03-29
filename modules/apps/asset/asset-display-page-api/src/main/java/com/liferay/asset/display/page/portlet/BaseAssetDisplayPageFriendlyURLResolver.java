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

package com.liferay.asset.display.page.portlet;

import com.liferay.asset.display.contributor.AssetDisplayContributor;
import com.liferay.asset.display.contributor.AssetDisplayContributorTracker;
import com.liferay.asset.display.contributor.constants.AssetDisplayWebKeys;
import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.util.AssetHelper;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURLComposite;
import com.liferay.portal.kernel.portlet.FriendlyURLResolver;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
public abstract class BaseAssetDisplayPageFriendlyURLResolver
	implements FriendlyURLResolver {

	@Override
	public String getActualURL(
			long companyId, long groupId, boolean privateLayout,
			String mainPath, String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		HttpServletRequest request = (HttpServletRequest)requestContext.get(
			"request");

		request.setAttribute(
			AssetDisplayWebKeys.ASSET_DISPLAY_CONTRIBUTOR,
			_getAssetDisplayContributor(groupId, friendlyURL));

		AssetEntry assetEntry = _getAssetEntry(groupId, friendlyURL);

		request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);

		request.setAttribute(
			AssetDisplayWebKeys.VERSION_CLASS_PK,
			_getVersionClassPK(friendlyURL));

		Locale locale = portal.getLocale(request);

		portal.setPageTitle(assetEntry.getTitle(locale), request);
		portal.setPageDescription(assetEntry.getDescription(locale), request);

		portal.setPageKeywords(
			assetHelper.getAssetKeywords(
				assetEntry.getClassName(), assetEntry.getClassPK()),
			request);

		Layout layout = _getAssetEntryLayout(assetEntry);

		return portal.getLayoutActualURL(layout, mainPath);
	}

	public abstract String getAssetURLSeparator();

	@Override
	public LayoutFriendlyURLComposite getLayoutFriendlyURLComposite(
			long companyId, long groupId, boolean privateLayout,
			String friendlyURL, Map<String, String[]> params,
			Map<String, Object> requestContext)
		throws PortalException {

		Layout layout = _getAssetEntryLayout(
			_getAssetEntry(groupId, friendlyURL));

		return new LayoutFriendlyURLComposite(layout, friendlyURL);
	}

	@Override
	public String getURLSeparator() {
		return getAssetURLSeparator();
	}

	@Reference
	protected AssetDisplayContributorTracker assetDisplayContributorTracker;

	@Reference
	protected AssetDisplayPageEntryLocalService
		assetDisplayPageEntryLocalService;

	@Reference
	protected AssetEntryService assetEntryService;

	@Reference
	protected AssetHelper assetHelper;

	@Reference
	protected LayoutLocalService layoutLocalService;

	@Reference
	protected LayoutPageTemplateEntryService layoutPageTemplateEntryService;

	@Reference
	protected Portal portal;

	private AssetDisplayContributor _getAssetDisplayContributor(
			long groupId, String friendlyURL)
		throws PortalException {

		String assetURLSeparator = _getAssetURLSeparator(friendlyURL);

		AssetDisplayContributor assetDisplayContributor = null;

		if (Validator.isNotNull(assetURLSeparator)) {
			assetDisplayContributor =
				assetDisplayContributorTracker.
					getAssetDisplayContributorByAssetURLSeparator(
						assetURLSeparator);
		}
		else {
			AssetEntry assetEntry = _getAssetEntry(groupId, friendlyURL);

			assetDisplayContributor =
				assetDisplayContributorTracker.getAssetDisplayContributor(
					assetEntry.getClassName());
		}

		if (assetDisplayContributor == null) {
			throw new PortalException(
				"Display page is not available for " + assetURLSeparator);
		}

		return assetDisplayContributor;
	}

	private AssetEntry _getAssetEntry(long groupId, String friendlyURL)
		throws PortalException {

		long assetEntryId = _getAssetEntryId(friendlyURL);

		if (assetEntryId > 0) {
			return assetEntryService.getEntry(assetEntryId);
		}

		AssetDisplayContributor assetDisplayContributor =
			_getAssetDisplayContributor(groupId, friendlyURL);

		String className = assetDisplayContributor.getClassName();

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.
				getAssetRendererFactoryByClassNameId(
					portal.getClassNameId(className));

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			groupId, _getUrlTitle(friendlyURL));

		return assetRendererFactory.getAssetEntry(
			className, assetRenderer.getClassPK());
	}

	private long _getAssetEntryId(String friendlyURL) {
		List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

		String assetEntryId = paths.get(1);

		if (Validator.isNumber(assetEntryId)) {
			return GetterUtil.getLong(assetEntryId);
		}

		return 0;
	}

	private Layout _getAssetEntryLayout(AssetEntry assetEntry) {
		AssetDisplayPageEntry assetDisplayPageEntry =
			assetDisplayPageEntryLocalService.fetchAssetDisplayPageEntry(
				assetEntry.getGroupId(), assetEntry.getClassNameId(),
				assetEntry.getClassPK());

		if (assetDisplayPageEntry == null) {
			return null;
		}

		if (assetDisplayPageEntry.getType() !=
				AssetDisplayPageConstants.TYPE_DEFAULT) {

			return layoutLocalService.fetchLayout(
				assetDisplayPageEntry.getPlid());
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry =
			layoutPageTemplateEntryService.fetchDefaultLayoutPageTemplateEntry(
				assetEntry.getGroupId(), assetEntry.getClassNameId(),
				assetEntry.getClassTypeId());

		if (layoutPageTemplateEntry != null) {
			return layoutLocalService.fetchLayout(
				layoutPageTemplateEntry.getPlid());
		}

		return null;
	}

	private String _getAssetURLSeparator(String friendlyURL) {
		List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

		String assetURLSeparator = paths.get(1);

		if (Validator.isNumber(assetURLSeparator)) {
			return StringPool.BLANK;
		}

		return assetURLSeparator;
	}

	private String _getUrlTitle(String friendlyURL) {
		List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

		if (paths.size() < 3) {
			return StringPool.BLANK;
		}

		if (Validator.isNumber(paths.get(1))) {
			return StringPool.BLANK;
		}

		return paths.get(2);
	}

	private long _getVersionClassPK(String friendlyURL) {
		List<String> paths = StringUtil.split(friendlyURL, CharPool.SLASH);

		if (Validator.isNumber(paths.get(1))) {
			if (paths.size() < 3) {
				return 0;
			}

			return GetterUtil.getLong(paths.get(2));
		}

		if (paths.size() < 4) {
			return 0;
		}

		return GetterUtil.getLong(paths.get(3));
	}

}