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

package com.liferay.asset.list.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetListEntryAssetEntryRelService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetListEntryAssetEntryRelService
 * @generated
 */
@ProviderType
public class AssetListEntryAssetEntryRelServiceWrapper
	implements AssetListEntryAssetEntryRelService,
		ServiceWrapper<AssetListEntryAssetEntryRelService> {
	public AssetListEntryAssetEntryRelServiceWrapper(
		AssetListEntryAssetEntryRelService assetListEntryAssetEntryRelService) {
		_assetListEntryAssetEntryRelService = assetListEntryAssetEntryRelService;
	}

	@Override
	public com.liferay.asset.list.model.AssetListEntryAssetEntryRel addAssetListEntryAssetEntryRel(
		long assetListEntryId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetListEntryAssetEntryRelService.addAssetListEntryAssetEntryRel(assetListEntryId,
			assetEntryId);
	}

	@Override
	public com.liferay.asset.list.model.AssetListEntryAssetEntryRel deleteAssetListEntryAssetEntryRel(
		long assetListEntryId, int position)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetListEntryAssetEntryRelService.deleteAssetListEntryAssetEntryRel(assetListEntryId,
			position);
	}

	@Override
	public java.util.List<com.liferay.asset.list.model.AssetListEntryAssetEntryRel> getAssetListEntryAssetEntryRels(
		long assetListEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetListEntryAssetEntryRelService.getAssetListEntryAssetEntryRels(assetListEntryId,
			start, end);
	}

	@Override
	public int getAssetListEntryAssetEntryRelsCount(long assetListEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetListEntryAssetEntryRelService.getAssetListEntryAssetEntryRelsCount(assetListEntryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetListEntryAssetEntryRelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.asset.list.model.AssetListEntryAssetEntryRel moveAssetEntry(
		long assetListEntryId, int position, int newPosition)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _assetListEntryAssetEntryRelService.moveAssetEntry(assetListEntryId,
			position, newPosition);
	}

	@Override
	public AssetListEntryAssetEntryRelService getWrappedService() {
		return _assetListEntryAssetEntryRelService;
	}

	@Override
	public void setWrappedService(
		AssetListEntryAssetEntryRelService assetListEntryAssetEntryRelService) {
		_assetListEntryAssetEntryRelService = assetListEntryAssetEntryRelService;
	}

	private AssetListEntryAssetEntryRelService _assetListEntryAssetEntryRelService;
}