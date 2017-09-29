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
import com.liferay.asset.kernel.service.AssetTagLocalServiceWrapper;
import com.liferay.asset.service.AssetTagLocalService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetTagLocalServiceWrapper
	extends AssetTagLocalServiceWrapper {

	public ModularAssetTagLocalServiceWrapper() {
		super(null);
	}

	public ModularAssetTagLocalServiceWrapper(
		com.liferay.asset.kernel.service.AssetTagLocalService
			assetTagLocalService) {

		super(assetTagLocalService);
	}

	@Override
	public AssetTag addTag(
			long userId, long groupId, String name,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.addTag(
				userId, groupId, name, serviceContext));
	}

	@Override
	public List<AssetTag> checkTags(long userId, Group group, String[] names)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.checkTags(userId, group, names));
	}

	@Override
	public List<AssetTag> checkTags(long userId, long groupId, String[] names)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.checkTags(userId, groupId, names));
	}

	@Override
	public AssetTag decrementAssetCount(long tagId, long classNameId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.decrementAssetCount(tagId, classNameId));
	}

	@Override
	public void deleteGroupTags(long groupId) throws PortalException {
		_assetTagLocalService.deleteGroupTags(groupId);
	}

	@Override
	public void deleteTag(AssetTag tag) throws PortalException {
		_assetTagLocalService.deleteTag(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetTag.class, tag));
	}

	@Override
	public void deleteTag(long tagId) throws PortalException {
		_assetTagLocalService.deleteTag(tagId);
	}

	@Override
	public AssetTag fetchTag(long groupId, String name) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.fetchTag(groupId, name));
	}

	@Override
	public List<AssetTag> getEntryTags(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getEntryTags(entryId));
	}

	@Override
	public List<AssetTag> getGroupsTags(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getGroupsTags(groupIds));
	}

	@Override
	public List<AssetTag> getGroupTags(long groupId) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getGroupTags(groupId));
	}

	@Override
	public List<AssetTag> getGroupTags(long groupId, int start, int end) {
		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getGroupTags(groupId, start, end));
	}

	@Override
	public int getGroupTagsCount(long groupId) {
		return _assetTagLocalService.getGroupTagsCount(groupId);
	}

	@Override
	public List<AssetTag> getSocialActivityCounterOffsetTags(
		long groupId, String socialActivityCounterName, int startOffset,
		int endOffset) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getSocialActivityCounterOffsetTags(
				groupId, socialActivityCounterName, startOffset, endOffset));
	}

	@Override
	public List<AssetTag> getSocialActivityCounterPeriodTags(
		long groupId, String socialActivityCounterName, int startPeriod,
		int endPeriod) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getSocialActivityCounterOffsetTags(
				groupId, socialActivityCounterName, startPeriod, endPeriod));
	}

	@Override
	public AssetTag getTag(long tagId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getTag(tagId));
	}

	@Override
	public AssetTag getTag(long groupId, String name) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getTag(groupId, name));
	}

	@Override
	public long[] getTagIds(long groupId, String[] names) {
		return _assetTagLocalService.getTagIds(groupId, names);
	}

	@Override
	public long[] getTagIds(long[] groupIds, String name) {
		return _assetTagLocalService.getTagIds(groupIds, name);
	}

	@Override
	public long[] getTagIds(long[] groupIds, String[] names) {
		return _assetTagLocalService.getTagIds(groupIds, names);
	}

	@Override
	public String[] getTagNames() {
		return _assetTagLocalService.getTagNames();
	}

	@Override
	public String[] getTagNames(long classNameId, long classPK) {
		return _assetTagLocalService.getTagNames(classNameId, classPK);
	}

	@Override
	public String[] getTagNames(String className, long classPK) {
		return _assetTagLocalService.getTagNames(className, classPK);
	}

	@Override
	public List<AssetTag> getTags() {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getTags());
	}

	@Override
	public List<AssetTag> getTags(long classNameId, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getTags(classNameId, classPK));
	}

	@Override
	public List<AssetTag> getTags(long groupId, long classNameId, String name) {
		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getTags(groupId, classNameId, name));
	}

	@Override
	public List<AssetTag> getTags(
		long groupId, long classNameId, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.getTags(
				groupId, classNameId, name, start, end));
	}

	@Override
	public List<AssetTag> getTags(String className, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetTag.class, _assetTagLocalService.getTags(className, classPK));
	}

	@Override
	public int getTagsSize(long groupId, long classNameId, String name) {
		return _assetTagLocalService.getTagsSize(groupId, classNameId, name);
	}

	@Override
	public boolean hasTag(long groupId, String name) {
		return _assetTagLocalService.hasTag(groupId, name);
	}

	@Override
	public AssetTag incrementAssetCount(long tagId, long classNameId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.incrementAssetCount(tagId, classNameId));
	}

	@Override
	public void mergeTags(long fromTagId, long toTagId) throws PortalException {
		_assetTagLocalService.mergeTags(fromTagId, toTagId);
	}

	@Override
	public List<AssetTag> search(
		long groupId, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.search(groupId, name, start, end));
	}

	@Override
	public List<AssetTag> search(
		long[] groupIds, String name, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.search(groupIds, name, start, end));
	}

	@Override
	public AssetTag updateTag(
			long userId, long tagId, String name, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetTag.class,
			_assetTagLocalService.updateTag(
				userId, tagId, name, serviceContext));
	}

	@Reference
	private AssetTagLocalService _assetTagLocalService;

}