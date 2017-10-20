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

package com.liferay.layout.internal.service;

import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class AssetEntryLayoutLocalServiceWrapper
	extends LayoutLocalServiceWrapper {

	public AssetEntryLayoutLocalServiceWrapper() {
		super(null);
	}

	public AssetEntryLayoutLocalServiceWrapper(
		LayoutLocalService layoutLocalService) {

		super(layoutLocalService);
	}

	@Override
	public Layout addLayout(
			long userId, long groupId, boolean privateLayout,
			long parentLayoutId, Map<Locale, String> nameMap,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> keywordsMap, Map<Locale, String> robotsMap,
			String type, String typeSettings, boolean hidden,
			Map<Locale, String> friendlyURLMap, ServiceContext serviceContext)
		throws PortalException {

		Layout layout = super.addLayout(
			userId, groupId, privateLayout, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, typeSettings, hidden,
			friendlyURLMap, serviceContext);

		updateAsset(
			userId, layout, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return layout;
	}

	@Override
	public void deleteLayout(
			Layout layout, boolean updateLayoutSet,
			ServiceContext serviceContext)
		throws PortalException {

		super.deleteLayout(layout, updateLayoutSet, serviceContext);

		_assetEntryLocalService.deleteEntry(
			Layout.class.getName(), layout.getPlid());
	}

	@Override
	public void updateAsset(
			long userId, Layout layout, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

		super.updateAsset(userId, layout, assetCategoryIds, assetTagNames);

		_assetEntryLocalService.updateEntry(
			userId, layout.getGroupId(), layout.getCreateDate(),
			layout.getModifiedDate(), Layout.class.getName(), layout.getPlid(),
			layout.getUuid(), 0, assetCategoryIds, assetTagNames, true, false,
			null, null, null, null, ContentTypes.TEXT_HTML,
			layout.getName(LocaleUtil.getDefault()), null, null, null, null, 0,
			0, null);
	}

	@Override
	public Layout updateLayout(
			long groupId, boolean privateLayout, long layoutId,
			long parentLayoutId, Map<Locale, String> nameMap,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			Map<Locale, String> keywordsMap, Map<Locale, String> robotsMap,
			String type, boolean hidden, Map<Locale, String> friendlyURLMap,
			boolean iconImage, byte[] iconBytes, ServiceContext serviceContext)
		throws PortalException {

		Layout layout = super.updateLayout(
			groupId, privateLayout, layoutId, parentLayoutId, nameMap, titleMap,
			descriptionMap, keywordsMap, robotsMap, type, hidden,
			friendlyURLMap, iconImage, iconBytes, serviceContext);

		updateAsset(
			serviceContext.getUserId(), layout,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return layout;
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}