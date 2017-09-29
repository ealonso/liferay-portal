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
import com.liferay.asset.kernel.model.AssetCategoryDisplay;
import com.liferay.asset.kernel.service.AssetCategoryServiceWrapper;
import com.liferay.asset.service.AssetCategoryService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
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
public class ModularAssetCategoryServiceWrapper
	extends AssetCategoryServiceWrapper {

	public ModularAssetCategoryServiceWrapper() {
		super(null);
	}

	public ModularAssetCategoryServiceWrapper(
		com.liferay.asset.kernel.service.AssetCategoryService
			assetCategoryService) {

		super(assetCategoryService);
	}

	@Override
	public AssetCategory addCategory(
			long groupId, long parentCategoryId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long vocabularyId,
			String[] categoryProperties, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.addCategory(
				groupId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext));
	}

	@Override
	public AssetCategory addCategory(
			long groupId, String title, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.addCategory(
				groupId, title, vocabularyId, serviceContext));
	}

	@Override
	public void deleteCategories(long[] categoryIds) throws PortalException {
		_assetCategoryService.deleteCategories(categoryIds);
	}

	/**
	 * @deprecated As of 1.1.0, Replaced by {@link #deleteCategories(long[])}
	 */
	@Deprecated
	@Override
	public List<AssetCategory> deleteCategories(
			long[] categoryIds, ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.deleteCategories(
				categoryIds, serviceContext));
	}

	@Override
	public void deleteCategory(long categoryId) throws PortalException {
		_assetCategoryService.deleteCategory(categoryId);
	}

	@Override
	public AssetCategory fetchCategory(long categoryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.fetchCategory(categoryId));
	}

	@Override
	public List<AssetCategory> getCategories(String className, long classPK)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getCategories(className, classPK));
	}

	@Override
	public AssetCategory getCategory(long categoryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetCategory.class, _assetCategoryService.getCategory(categoryId));
	}

	@Override
	public String getCategoryPath(long categoryId) throws PortalException {
		return _assetCategoryService.getCategoryPath(categoryId);
	}

	@Override
	public List<AssetCategory> getChildCategories(long parentCategoryId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getChildCategories(parentCategoryId));
	}

	@Override
	public List<AssetCategory> getChildCategories(
			long parentCategoryId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getChildCategories(
				parentCategoryId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
			long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getVocabularyCategories(
				vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
			long parentCategoryId, long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getVocabularyCategories(
				parentCategoryId, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long groupId, long parentCategoryId, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getVocabularyCategories(
				groupId, parentCategoryId, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long groupId, String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getVocabularyCategories(
				groupId, name, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public int getVocabularyCategoriesCount(long groupId, long vocabularyId) {
		return _assetCategoryService.getVocabularyCategoriesCount(
			groupId, vocabularyId);
	}

	@Override
	public int getVocabularyCategoriesCount(
		long groupId, long parentCategory, long vocabularyId) {

		return _assetCategoryService.getVocabularyCategoriesCount(
			groupId, parentCategory, vocabularyId);
	}

	@Override
	public int getVocabularyCategoriesCount(
		long groupId, String name, long vocabularyId) {

		return _assetCategoryService.getVocabularyCategoriesCount(
			groupId, name, vocabularyId);
	}

	@Override
	public AssetCategoryDisplay getVocabularyCategoriesDisplay(
			long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.getVocabularyCategoriesDisplay(
				vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public AssetCategoryDisplay getVocabularyCategoriesDisplay(
			long groupId, String name, long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.getVocabularyCategoriesDisplay(
				groupId, name, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public List<AssetCategory> getVocabularyRootCategories(
		long groupId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.getVocabularyRootCategories(
				groupId, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public int getVocabularyRootCategoriesCount(
		long groupId, long vocabularyId) {

		return _assetCategoryService.getVocabularyCategoriesCount(
			groupId, vocabularyId);
	}

	@Override
	public AssetCategory moveCategory(
			long categoryId, long parentCategoryId, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.moveCategory(
				categoryId, parentCategoryId, vocabularyId, serviceContext));
	}

	@Override
	public List<AssetCategory> search(
		long groupId, String keywords, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.search(
				groupId, keywords, vocabularyId, start, end,
				ModelAdapterUtil.adapt(AssetCategory.class, obc)));
	}

	@Override
	public JSONArray search(
			long groupId, String name, String[] categoryProperties, int start,
			int end)
		throws PortalException {

		return _assetCategoryService.search(
			groupId, name, categoryProperties, start, end);
	}

	@Override
	public JSONArray search(
			long[] groupIds, String name, long[] vocabularyIds, int start,
			int end)
		throws PortalException {

		return _assetCategoryService.search(
			groupIds, name, vocabularyIds, start, end);
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long vocabularyId, int start, int end)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupId, title, vocabularyId, start, end));
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long parentCategoryId,
			long vocabularyId, int start, int end)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupId, title, parentCategoryId, vocabularyId, start, end));
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long vocabularyId,
			long parentCategoryId, int start, int end, Sort sort)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupId, title, vocabularyId, parentCategoryId, start, end,
				sort));
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] vocabularyIds, int start,
			int end)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupIds, title, vocabularyIds, start, end));
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] parentCategoryIds,
			long[] vocabularyIds, int start, int end)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupIds, title, parentCategoryIds, vocabularyIds, start, end));
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] vocabularyIds,
			long[] parentCategoryIds, int start, int end, Sort sort)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategoryDisplay.class,
			_assetCategoryService.searchCategoriesDisplay(
				groupIds, title, parentCategoryIds, vocabularyIds, start, end,
				sort));
	}

	@Override
	public AssetCategory updateCategory(
			long categoryId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetCategory.class,
			_assetCategoryService.updateCategory(
				categoryId, parentCategoryId, titleMap, descriptionMap,
				vocabularyId, categoryProperties, serviceContext));
	}

	@Reference
	private AssetCategoryService _assetCategoryService;

}