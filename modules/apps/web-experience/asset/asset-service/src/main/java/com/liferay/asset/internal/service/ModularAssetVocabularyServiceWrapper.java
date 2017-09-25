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

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyDisplay;
import com.liferay.asset.kernel.service.AssetVocabularyServiceWrapper;
import com.liferay.asset.service.AssetVocabularyService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetVocabularyServiceWrapper
	extends AssetVocabularyServiceWrapper {

	public ModularAssetVocabularyServiceWrapper() {
		super(null);
	}

	public ModularAssetVocabularyServiceWrapper(
		com.liferay.asset.kernel.service.AssetVocabularyService
			assetVocabularyService) {

		super(assetVocabularyService);
	}

	@Override
	public AssetVocabulary addVocabulary(
			long groupId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.addVocabulary(
				groupId, title, titleMap, descriptionMap, settings,
				serviceContext));
	}

	@Override
	public AssetVocabulary addVocabulary(
			long groupId, String title, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.addVocabulary(
				groupId, title, serviceContext));
	}

	@Override
	public List<AssetVocabulary> deleteVocabularies(
			long[] vocabularyIds, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.deleteVocabularies(
				vocabularyIds, serviceContext));
	}

	@Override
	public void deleteVocabulary(long vocabularyId) throws PortalException {
		_assetVocabularyService.deleteVocabulary(vocabularyId);
	}

	@Override
	public AssetVocabulary fetchVocabulary(long vocabularyId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.fetchVocabulary(vocabularyId));
	}

	/**
	 * @deprecated As of 1.1.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public List<AssetVocabulary> getCompanyVocabularies(long companyId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getCompanyVocabularies(companyId));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupsVocabularies(groupIds));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupsVocabularies(groupIds, className));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className, long classTypePK) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupsVocabularies(
				groupIds, className, classTypePK));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long groupId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(groupId));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, boolean createDefaultVocabulary)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(
				groupId, createDefaultVocabulary));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, boolean createDefaultVocabulary, int start, int end,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(
				groupId, createDefaultVocabulary, start, end,
				ModelAdapterUtil.adapt(AssetVocabulary.class, obc)));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
		long groupId, int start, int end,
		OrderByComparator<AssetVocabulary> obc) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(
				groupId, start, end,
				ModelAdapterUtil.adapt(AssetVocabulary.class, obc)));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> obc) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(
				groupId, name, start, end,
				ModelAdapterUtil.adapt(AssetVocabulary.class, obc)));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getGroupVocabularies(groupIds));
	}

	@Override
	public int getGroupVocabulariesCount(long groupId) {
		return _assetVocabularyService.getGroupVocabulariesCount(groupId);
	}

	@Override
	public int getGroupVocabulariesCount(long groupId, String name) {
		return _assetVocabularyService.getGroupVocabulariesCount(groupId, name);
	}

	@Override
	public int getGroupVocabulariesCount(long[] groupIds) {
		return _assetVocabularyService.getGroupVocabulariesCount(groupIds);
	}

	@Override
	public AssetVocabularyDisplay getGroupVocabulariesDisplay(
			long groupId, String name, int start, int end,
			boolean addDefaultVocabulary,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabularyDisplay.class,
			_assetVocabularyService.getGroupVocabulariesDisplay(
				groupId, name, start, end, addDefaultVocabulary,
				ModelAdapterUtil.adapt(AssetVocabulary.class, obc)));
	}

	@Override
	public AssetVocabularyDisplay getGroupVocabulariesDisplay(
			long groupId, String name, int start, int end,
			OrderByComparator<AssetVocabulary> obc)
		throws PortalException {

		return getGroupVocabulariesDisplay(
			groupId, name, start, end, false, obc);
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link
	 *             com.liferay.portlet.asset.util.AssetUtil#filterVocabularyIds(PermissionChecker, long[])}
	 */
	@Deprecated
	@Override
	public List<AssetVocabulary> getVocabularies(long[] vocabularyIds)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getVocabularies(vocabularyIds));
	}

	@Override
	public AssetVocabulary getVocabulary(long vocabularyId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.getVocabulary(vocabularyId));
	}

	@Override
	public AssetVocabularyDisplay searchVocabulariesDisplay(
			long groupId, String title, boolean addDefaultVocabulary, int start,
			int end)
		throws PortalException {

		return searchVocabulariesDisplay(
			groupId, title, addDefaultVocabulary, start, end, null);
	}

	@Override
	public AssetVocabularyDisplay searchVocabulariesDisplay(
			long groupId, String title, boolean addDefaultVocabulary, int start,
			int end, Sort sort)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabularyDisplay.class,
			_assetVocabularyService.searchVocabulariesDisplay(
				groupId, title, addDefaultVocabulary, start, end, sort));
	}

	@Override
	public AssetVocabulary updateVocabulary(
			long vocabularyId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyService.updateVocabulary(
				vocabularyId, title, titleMap, descriptionMap, settings,
				serviceContext));
	}

	@Reference
	private AssetVocabularyService _assetVocabularyService;

}