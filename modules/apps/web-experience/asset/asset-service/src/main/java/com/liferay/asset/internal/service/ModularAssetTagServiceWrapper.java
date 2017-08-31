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

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetTagDisplay;
import com.liferay.asset.kernel.service.AssetTagServiceWrapper;
import com.liferay.asset.service.AssetTagService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorAdapter;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetTagServiceWrapper extends AssetTagServiceWrapper {

	public ModularAssetTagServiceWrapper() {
		super(null);
	}

	public ModularAssetTagServiceWrapper(
		com.liferay.asset.kernel.service.AssetTagService assetTagService) {

		super(assetTagService);
	}

	@Override
	public AssetTag addTag(
			long groupId, String name, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.addTag(groupId, name, serviceContext));
	}

	@Override
	public void deleteTag(long tagId) throws PortalException {
		_assetTagService.deleteTag(tagId);
	}

	@Override
	public void deleteTags(long[] tagIds) throws PortalException {
		_assetTagService.deleteTags(tagIds);
	}

	@Override
	public List<AssetTag> getGroupsTags(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagService.getGroupsTags(groupIds));
	}

	@Override
	public List<AssetTag> getGroupTags(long groupId) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagService.getGroupTags(groupId));
	}

	@Override
	public List<AssetTag> getGroupTags(
		long groupId, int start, int end, OrderByComparator<AssetTag> obc) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getGroupTags(
				groupId, start, end,
				new AssetTagEntryOrderByComparatorAdapter(obc)));
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		return _assetTagService.getGroupTagsCount(groupId);
	}

	@Override
	public AssetTagDisplay getGroupTagsDisplay(
		long groupId, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTagDisplay.class,
			_assetTagService.getGroupTagsDisplay(groupId, name, start, end));
	}

	@Override
	public AssetTag getTag(long tagId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagService.getTag(tagId));
	}

	@Override
	public List<AssetTag> getTags(long groupId, long classNameId, String name) {
		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getTags(groupId, classNameId, name));
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getTags(
				groupId, classNameId, name, start, end,
				new AssetTagEntryOrderByComparatorAdapter(obc)));
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getTags(groupId, name, start, end));
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.tags.service.impl.AssetTagServiceImpl");
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getTags(groupIds, name, start, end));
	}

	@Override
	public List<AssetTag> getTags(
		long[] groupIds, String name, int start, int end,
		OrderByComparator<AssetTag> obc) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.getTags(groupIds, name, start, end));
	}

	@Override
	public List<AssetTag> getTags(String className, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagService.getTags(className, classPK));
	}

	@Override
	public int getTagsCount(long groupId, String name) {
		return _assetTagService.getTagsCount(groupId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(
		long groupId, long classNameId, String name) {

		return _assetTagService.getVisibleAssetsTagsCount(
			groupId, classNameId, name);
	}

	@Override
	public int getVisibleAssetsTagsCount(long groupId, String name) {
		return _assetTagService.getVisibleAssetsTagsCount(groupId, name);
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
		_assetTagService.mergeTags(fromTagId, toTagId);
	}

	@Override
	public void mergeTags(long[] fromTagIds, long toTagId)
		throws PortalException {

		_assetTagService.mergeTags(fromTagIds, toTagId);
	}

	@Override
	public JSONArray search(long groupId, String name, int start, int end) {
		return _assetTagService.search(groupId, name, start, end);
	}

	@Override
	public JSONArray search(long[] groupIds, String name, int start, int end) {
		return _assetTagService.search(groupIds, name, start, end);
	}

	@Override
	public AssetTag updateTag(
			long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagService.updateTag(tagId, name, serviceContext));
	}

	@Reference
	private AssetTagService _assetTagService;

	private static class AssetTagEntryOrderByComparatorAdapter
		extends
			OrderByComparatorAdapter
				<com.liferay.asset.model.AssetTag, AssetTag> {

		public AssetTagEntryOrderByComparatorAdapter(
			OrderByComparator<AssetTag> orderByComparator) {

			super(orderByComparator);
		}

		@Override
		public AssetTag adapt(com.liferay.asset.model.AssetTag assetTag) {
			return ModelAdapterUtil.adapt(AssetTag.class, assetTag);
		}

	}

}