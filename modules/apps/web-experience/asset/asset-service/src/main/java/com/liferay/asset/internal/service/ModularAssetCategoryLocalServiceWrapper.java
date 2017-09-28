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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceWrapper;
import com.liferay.asset.model.AssetCategoryConstants;
import com.liferay.asset.service.AssetCategoryLocalService;
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
public class ModularAssetCategoryLocalServiceWrapper
	extends AssetCategoryLocalServiceWrapper {

	public ModularAssetCategoryLocalServiceWrapper() {
		super(null);
	}

	public ModularAssetCategoryLocalServiceWrapper(
		com.liferay.asset.kernel.service.AssetCategoryLocalService
			assetCategoryLocalService) {

		super(assetCategoryLocalService);
	}

	@Override
	public AssetCategory addCategory(
			long userId, long groupId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.addCategory(
				userId, groupId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext));
	}

	@Override
	public AssetCategory addCategory(
			long userId, long groupId, String title, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.addCategory(
				userId, groupId, title, vocabularyId, serviceContext));
	}

	@Override
	public void addCategoryResources(
			AssetCategory category, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		_assetCategoryLocalService.addCategoryResources(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetCategory.class, category),
			addGroupPermissions, addGuestPermissions);
	}

	@Override
	public void addCategoryResources(
			AssetCategory category, ModelPermissions modelPermissions)
		throws PortalException {

		_assetCategoryLocalService.addCategoryResources(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetCategory.class, category),
			modelPermissions);
	}

	@Override
	public void deleteCategories(List<AssetCategory> categories)
		throws PortalException {

		_assetCategoryLocalService.deleteCategories(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetCategory.class, categories));
	}

	@Override
	public void deleteCategories(long[] categoryIds) throws PortalException {
		_assetCategoryLocalService.deleteCategories(categoryIds);
	}

	@Override
	public AssetCategory deleteCategory(AssetCategory category)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.deleteAssetCategory(
				ModelAdapterUtil.adapt(
					com.liferay.asset.model.AssetCategory.class, category)));
	}

	@Override
	public AssetCategory deleteCategory(
			AssetCategory category, boolean skipRebuildTree)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.deleteCategory(
				ModelAdapterUtil.adapt(
					com.liferay.asset.model.AssetCategory.class, category),
				skipRebuildTree));
	}

	@Override
	public AssetCategory deleteCategory(long categoryId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.deleteCategory(categoryId));
	}

	@Override
	public void deleteVocabularyCategories(long vocabularyId)
		throws PortalException {

		_assetCategoryLocalService.deleteVocabularyCategories(vocabularyId);
	}

	@Override
	public AssetCategory fetchCategory(long categoryId) {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.fetchCategory(categoryId));
	}

	@Override
	public AssetCategory fetchCategory(
		long groupId, long parentCategoryId, String name, long vocabularyId) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.fetchCategory(
				groupId, parentCategoryId, name, vocabularyId));
	}

	@Override
	public List<AssetCategory> getCategories() {
		return ModelAdapterUtil.adapt(
			AssetCategory.class, _assetCategoryLocalService.getCategories());
	}

	@Override
	public List<AssetCategory> getCategories(Hits hits) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getCategories(hits));
	}

	@Override
	public List<AssetCategory> getCategories(long classNameId, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getCategories(classNameId, classPK));
	}

	@Override
	public List<AssetCategory> getCategories(String className, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getCategories(className, classPK));
	}

	@Override
	public AssetCategory getCategory(long categoryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getCategory(categoryId));
	}

	@Override
	public AssetCategory getCategory(String uuid, long groupId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getCategory(uuid, groupId));
	}

	@Override
	public long[] getCategoryIds(String className, long classPK) {
		return _assetCategoryLocalService.getCategoryIds(className, classPK);
	}

	@Override
	public String[] getCategoryNames() {
		return _assetCategoryLocalService.getCategoryNames();
	}

	@Override
	public String[] getCategoryNames(long classNameId, long classPK) {
		return _assetCategoryLocalService.getCategoryNames(
			classNameId, classPK);
	}

	@Override
	public String[] getCategoryNames(String className, long classPK) {
		return _assetCategoryLocalService.getCategoryNames(className, classPK);
	}

	@Override
	public List<AssetCategory> getChildCategories(long parentCategoryId) {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getChildCategories(parentCategoryId));
	}

	@Override
	public List<AssetCategory> getChildCategories(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getChildCategories(
				parentCategoryId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public int getChildCategoriesCount(long parentCategoryId) {
		return _assetCategoryLocalService.getChildCategoriesCount(
			parentCategoryId);
	}

	@Override
	public List<AssetCategory> getEntryCategories(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getEntryCategories(entryId));
	}

	@Override
	public List<Long> getSubcategoryIds(long parentCategoryId) {
		return _assetCategoryLocalService.getSubcategoryIds(parentCategoryId);
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getVocabularyCategories(
				vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.getVocabularyCategories(
				parentCategoryId, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public int getVocabularyCategoriesCount(long vocabularyId) {
		return _assetCategoryLocalService.getVocabularyCategoriesCount(
			vocabularyId);
	}

	@Override
	public List<AssetCategory> getVocabularyRootCategories(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return getVocabularyCategories(
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, vocabularyId,
			start, end, obc);
	}

	@Override
	public int getVocabularyRootCategoriesCount(long vocabularyId) {
		return _assetCategoryLocalService.getVocabularyRootCategoriesCount(
			vocabularyId);
	}

	@Override
	public AssetCategory mergeCategories(long fromCategoryId, long toCategoryId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.mergeCategories(
				fromCategoryId, toCategoryId));
	}

	@Override
	public AssetCategory moveCategory(
			long categoryId, long parentCategoryId, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.moveCategory(
				categoryId, parentCategoryId, vocabularyId, serviceContext));
	}

	@Override
	public void rebuildTree(long groupId, boolean force) {
		_assetCategoryLocalService.rebuildTree(groupId, force);
	}

	@Override
	public List<AssetCategory> search(
		long groupId, String name, String[] categoryProperties, int start,
		int end) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.search(
				groupId, name, categoryProperties, start, end));
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long groupIds, String title, long vocabularyId,
			int start, int end)
		throws PortalException {

		return searchCategories(
			companyId, new long[] {groupIds}, title, new long[] {vocabularyId},
			start, end);
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title, long[] vocabularyIds,
			int start, int end)
		throws PortalException {

		BaseModelSearchResult
			<com.liferay.asset.model.AssetCategory>
				baseModelSearchResult =
					_assetCategoryLocalService.searchCategories(
						companyId, groupIds, title, vocabularyIds, start, end);

		return new BaseModelSearchResult<>(
			ModelAdapterUtil.adapt(
				AssetCategory.class, baseModelSearchResult.getBaseModels()),
			baseModelSearchResult.getLength());
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title,
			long[] parentCategoryIds, long[] vocabularyIds, int start, int end)
		throws PortalException {

		BaseModelSearchResult
			<com.liferay.asset.model.AssetCategory>
				baseModelSearchResult =
					_assetCategoryLocalService.searchCategories(
						companyId, groupIds, title, parentCategoryIds,
						vocabularyIds, start, end);

		return new BaseModelSearchResult<>(
			ModelAdapterUtil.adapt(
				AssetCategory.class, baseModelSearchResult.getBaseModels()),
			baseModelSearchResult.getLength());
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title, long[] vocabularyIds,
			long[] parentCategoryIds, int start, int end, Sort sort)
		throws PortalException {

		BaseModelSearchResult
			<com.liferay.asset.model.AssetCategory>
				baseModelSearchResult =
					_assetCategoryLocalService.searchCategories(
						companyId, groupIds, title, vocabularyIds,
						parentCategoryIds, start, end, sort);

		return new BaseModelSearchResult<>(
			ModelAdapterUtil.adapt(
				AssetCategory.class, baseModelSearchResult.getBaseModels()),
			baseModelSearchResult.getLength());
	}

	@Override
	public AssetCategory updateCategory(
			long userId, long categoryId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryLocalService.updateCategory(
				userId, categoryId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext));
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

}