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

package com.liferay.portlet.asset.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.asset.service.base.AssetCategoryServiceBaseImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, moving, and
 * updating asset categories. Its methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Alvaro del Castillo
 * @author Eduardo Lundgren
 * @author Bruno Farache
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.categories.model.impl.AssetCategoryServiceImpl}
 */
@Deprecated
public class AssetCategoryServiceImpl extends AssetCategoryServiceBaseImpl {

	@Override
	public AssetCategory addCategory(
			long groupId, long parentCategoryId, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, long vocabularyId,
			String[] categoryProperties, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategory addCategory(
			long groupId, String title, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public void deleteCategories(long[] categoryIds) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, Replaced by {@link #deleteCategories(long[])}
	 */
	@Deprecated
	@Override
	public List<AssetCategory> deleteCategories(
			long[] categoryIds, ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public void deleteCategory(long categoryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategory fetchCategory(long categoryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getCategories(String className, long classPK)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategory getCategory(long categoryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public String getCategoryPath(long categoryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getChildCategories(long parentCategoryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getChildCategories(
			long parentCategoryId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
			long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
			long parentCategoryId, long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long groupId, long parentCategoryId, long vocabularyId, int start,
		int end, OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long groupId, String name, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public int getVocabularyCategoriesCount(long groupId, long vocabularyId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public int getVocabularyCategoriesCount(
		long groupId, long parentCategory, long vocabularyId) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public int getVocabularyCategoriesCount(
		long groupId, String name, long vocabularyId) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay getVocabularyCategoriesDisplay(
			long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay getVocabularyCategoriesDisplay(
			long groupId, String name, long vocabularyId, int start, int end,
			OrderByComparator<AssetCategory> obc)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyRootCategories(
		long groupId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public int getVocabularyRootCategoriesCount(
		long groupId, long vocabularyId) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategory moveCategory(
			long categoryId, long parentCategoryId, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public List<AssetCategory> search(
		long groupId, String keywords, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public JSONArray search(
			long groupId, String name, String[] categoryProperties, int start,
			int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public JSONArray search(
			long[] groupIds, String name, long[] vocabularyIds, int start,
			int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long vocabularyId, int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long parentCategoryId,
			long vocabularyId, int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long groupId, String title, long vocabularyId,
			long parentCategoryId, int start, int end, Sort sort)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] vocabularyIds, int start,
			int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] parentCategoryIds,
			long[] vocabularyIds, int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategoryDisplay searchCategoriesDisplay(
			long[] groupIds, String title, long[] vocabularyIds,
			long[] parentCategoryIds, int start, int end, Sort sort)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

	@Override
	public AssetCategory updateCategory(
			long categoryId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryServiceImpl");
	}

}