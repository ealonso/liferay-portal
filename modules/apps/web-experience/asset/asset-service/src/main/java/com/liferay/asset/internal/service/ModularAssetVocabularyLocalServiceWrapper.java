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
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceWrapper;
import com.liferay.asset.service.AssetVocabularyLocalService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
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
public class ModularAssetVocabularyLocalServiceWrapper
	extends AssetVocabularyLocalServiceWrapper {

	public ModularAssetVocabularyLocalServiceWrapper() {
		super(null);
	}

	public ModularAssetVocabularyLocalServiceWrapper(
		com.liferay.asset.kernel.service.AssetVocabularyLocalService
			assetVocabularyLocalService) {

		super(assetVocabularyLocalService);
	}

	@Override
	public AssetVocabulary addDefaultVocabulary(long groupId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.addDefaultVocabulary(groupId));
	}

	@Override
	public AssetVocabulary addVocabulary(
			long userId, long groupId, String title,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			String settings, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.addVocabulary(
				userId, groupId, title, titleMap, descriptionMap, settings,
				serviceContext));
	}

	@Override
	public AssetVocabulary addVocabulary(
			long userId, long groupId, String title,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.addVocabulary(
				userId, groupId, title, serviceContext));
	}

	@Override
	public void addVocabularyResources(
			AssetVocabulary vocabulary, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		_assetVocabularyLocalService.addVocabularyResources(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetVocabulary.class, vocabulary),
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addVocabularyResources(
			AssetVocabulary vocabulary, ModelPermissions modelPermissions)
		throws PortalException {

		_assetVocabularyLocalService.addVocabularyResources(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetVocabulary.class, vocabulary),
			modelPermissions);
	}

	@Override
	public void deleteVocabularies(long groupId) throws PortalException {
		_assetVocabularyLocalService.deleteVocabularies(groupId);
	}

	@Override
	public AssetVocabulary deleteVocabulary(AssetVocabulary vocabulary)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.deleteVocabulary(
				ModelAdapterUtil.adapt(
					com.liferay.asset.model.AssetVocabulary.class,
					vocabulary)));
	}

	@Override
	public void deleteVocabulary(long vocabularyId) throws PortalException {
		_assetVocabularyLocalService.deleteVocabulary(vocabularyId);
	}

	@Override
	public AssetVocabulary fetchGroupVocabulary(long groupId, String name)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.fetchGroupVocabulary(groupId, name));
	}

	@Override
	public List<AssetVocabulary> getCompanyVocabularies(long companyId) {
		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getCompanyVocabularies(companyId));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupsVocabularies(groupIds));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupsVocabularies(
				groupIds, className));
	}

	@Override
	public List<AssetVocabulary> getGroupsVocabularies(
		long[] groupIds, String className, long classTypePK) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupsVocabularies(
				groupIds, className, classTypePK));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long groupId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupVocabularies(groupId));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
			long groupId, boolean addDefaultVocabulary)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupVocabularies(
				groupId, addDefaultVocabulary));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(
		long groupId, String name, int start, int end,
		OrderByComparator<AssetVocabulary> obc) {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupVocabularies(
				groupId, name, start, end,
				ModelAdapterUtil.adapt(AssetVocabulary.class, obc)));
	}

	@Override
	public List<AssetVocabulary> getGroupVocabularies(long[] groupIds) {
		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupVocabularies(groupIds));
	}

	@Override
	public int getGroupVocabulariesCount(long[] groupIds) {
		return _assetVocabularyLocalService.getGroupVocabulariesCount(groupIds);
	}

	@Override
	public AssetVocabulary getGroupVocabulary(long groupId, String name)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getGroupVocabulary(groupId, name));
	}

	@Override
	public List<AssetVocabulary> getVocabularies(Hits hits)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getVocabularies(hits));
	}

	@Override
	public List<AssetVocabulary> getVocabularies(long[] vocabularyIds)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getVocabularies(vocabularyIds));
	}

	@Override
	public AssetVocabulary getVocabulary(long vocabularyId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.getVocabulary(vocabularyId));
	}

	@Override
	public BaseModelSearchResult<AssetVocabulary> searchVocabularies(
			long companyId, long groupId, String title, int start, int end)
		throws PortalException {

		return searchVocabularies(companyId, groupId, title, start, end, null);
	}

	@Override
	public BaseModelSearchResult<AssetVocabulary> searchVocabularies(
			long companyId, long groupId, String title, int start, int end,
			Sort sort)
		throws PortalException {

		BaseModelSearchResult
			<com.liferay.asset.model.AssetVocabulary>
				baseModelSearchResult =
					_assetVocabularyLocalService.searchVocabularies(
						companyId, groupId, title, start, end, sort);

		return new BaseModelSearchResult<>(
			ModelAdapterUtil.adapt(
				AssetVocabulary.class, baseModelSearchResult.getBaseModels()),
			baseModelSearchResult.getLength());
	}

	@Override
	public AssetVocabulary updateVocabulary(
			long vocabularyId, String title, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String settings,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetVocabulary.class,
			_assetVocabularyLocalService.updateVocabulary(
				vocabularyId, title, titleMap, descriptionMap, settings,
				serviceContext));
	}

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

}