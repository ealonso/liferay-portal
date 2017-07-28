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

package com.liferay.asset.internal.service;

import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceWrapper;
import com.liferay.asset.service.AssetLinkLocalService;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
public class ModularAssetLinkLocalServiceWrapper
	extends AssetLinkLocalServiceWrapper {

	public ModularAssetLinkLocalServiceWrapper() {
		super(null);
	}

	public ModularAssetLinkLocalServiceWrapper(
		com.liferay.
			asset.kernel.service.AssetLinkLocalService assetLinkLocalService) {

		super(assetLinkLocalService);
	}

	@Override
	public AssetLink addLink(
			long userId, long entryId1, long entryId2, int type, int weight)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.addLink(
				userId, entryId1, entryId2, type, weight));
	}

	@Override
	public AssetLink deleteAssetLink(AssetLink assetLink) {
		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.deleteAssetLink(
				ModelAdapterUtil.adapt(
					com.liferay.asset.model.AssetLink.class, assetLink)));
	}

	@Override
	public AssetLink deleteAssetLink(long linkId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetLink.class, _assetLinkLocalService.deleteAssetLink(linkId));
	}

	@Override
	public void deleteGroupLinks(long groupId) {
		_assetLinkLocalService.deleteGroupLinks(groupId);
	}

	@Override
	public void deleteLink(AssetLink link) {
		_assetLinkLocalService.deleteLink(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetLink.class, link));
	}

	@Override
	public void deleteLink(long linkId) throws PortalException {
		_assetLinkLocalService.deleteLink(linkId);
	}

	@Override
	public void deleteLinks(long entryId) {
		_assetLinkLocalService.deleteLinks(entryId);
	}

	@Override
	public void deleteLinks(long entryId1, long entryId2) {
		_assetLinkLocalService.deleteLinks(entryId1, entryId2);
	}

	@Override
	public List<AssetLink> getDirectLinks(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetLink.class, _assetLinkLocalService.getDirectLinks(entryId));
	}

	@Override
	public List<AssetLink> getDirectLinks(
		long entryId, boolean excludeInvisibleLinks) {

		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.getDirectLinks(
				entryId, excludeInvisibleLinks));
	}

	@Override
	public List<AssetLink> getDirectLinks(long entryId, int typeId) {
		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.getDirectLinks(entryId, typeId));
	}

	@Override
	public List<AssetLink> getDirectLinks(
		long entryId, int typeId, boolean excludeInvisibleLinks) {

		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.getDirectLinks(
				entryId, typeId, excludeInvisibleLinks));
	}

	@Override
	public ExportActionableDynamicQuery getExportActionbleDynamicQuery(
		final PortletDataContext portletDataContext) {

		return _assetLinkLocalService.getExportActionbleDynamicQuery(
			portletDataContext);
	}

	@Override
	public List<AssetLink> getLinks(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetLink.class, _assetLinkLocalService.getLinks(entryId));
	}

	@Override
	public List<AssetLink> getLinks(long entryId, int typeId) {
		return ModelAdapterUtil.adapt(
			AssetLink.class, _assetLinkLocalService.getLinks(entryId, typeId));
	}

	@Override
	public List<AssetLink> getReverseLinks(long entryId, int typeId) {
		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.getReverseLinks(entryId, typeId));
	}

	@Override
	public AssetLink updateLink(
			long userId, long entryId1, long entryId2, int typeId, int weight)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetLink.class,
			_assetLinkLocalService.updateLink(
				userId, entryId1, entryId2, typeId, weight));
	}

	@Override
	public void updateLinks(
			long userId, long entryId, long[] linkEntryIds, int typeId)
		throws PortalException {

		_assetLinkLocalService.updateLinks(
			userId, entryId, linkEntryIds, typeId);
	}

	@Reference
	private AssetLinkLocalService _assetLinkLocalService;

}