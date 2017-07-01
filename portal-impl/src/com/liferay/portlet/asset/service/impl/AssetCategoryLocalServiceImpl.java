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
import com.liferay.portal.kernel.cache.thread.local.ThreadLocalCachable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.asset.service.base.AssetCategoryLocalServiceBaseImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service for accessing, adding, deleting, merging, moving,
 * and updating asset categories.
 *
 * @author Brian Wing Shun Chan
 * @author Alvaro del Castillo
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.categories.service.impl.AssetCategoryLocalServiceImpl}
 */
@Deprecated
public class AssetCategoryLocalServiceImpl
	extends AssetCategoryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetCategory addCategory(
			long userId, long groupId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory addCategory(
			long userId, long groupId, String title, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void addCategoryResources(
			AssetCategory category, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void addCategoryResources(
			AssetCategory category, ModelPermissions modelPermissions)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void deleteCategories(List<AssetCategory> categories)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void deleteCategories(long[] categoryIds) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory deleteCategory(AssetCategory category)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public AssetCategory deleteCategory(
			AssetCategory category, boolean skipRebuildTree)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory deleteCategory(long categoryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void deleteVocabularyCategories(long vocabularyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory fetchCategory(long categoryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory fetchCategory(
		long groupId, long parentCategoryId, String name, long vocabularyId) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getCategories() {
		return assetCategoryPersistence.findAll();
	}

	@Override
	public List<AssetCategory> getCategories(Hits hits) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	@ThreadLocalCachable
	public List<AssetCategory> getCategories(long classNameId, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getCategories(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory getCategory(long categoryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public AssetCategory getCategory(String uuid, long groupId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public long[] getCategoryIds(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public String[] getCategoryNames() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public String[] getCategoryNames(long classNameId, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public String[] getCategoryNames(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getChildCategories(long parentCategoryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getChildCategories(
		long parentCategoryId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public int getChildCategoriesCount(long parentCategoryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getEntryCategories(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<Long> getSubcategoryIds(long parentCategoryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyCategories(
		long parentCategoryId, long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public int getVocabularyCategoriesCount(long vocabularyId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> getVocabularyRootCategories(
		long vocabularyId, int start, int end,
		OrderByComparator<AssetCategory> obc) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public int getVocabularyRootCategoriesCount(long vocabularyId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetCategory mergeCategories(long fromCategoryId, long toCategoryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetCategory moveCategory(
			long categoryId, long parentCategoryId, long vocabularyId,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public void rebuildTree(long groupId, boolean force) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public List<AssetCategory> search(
		long groupId, String name, String[] categoryProperties, int start,
		int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long groupIds, String title, long vocabularyId,
			int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title, long[] vocabularyIds,
			int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title,
			long[] parentCategoryIds, long[] vocabularyIds, int start, int end)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Override
	public BaseModelSearchResult<AssetCategory> searchCategories(
			long companyId, long[] groupIds, String title, long[] vocabularyIds,
			long[] parentCategoryIds, int start, int end, Sort sort)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetCategory updateCategory(
			long userId, long categoryId, long parentCategoryId,
			Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
			long vocabularyId, String[] categoryProperties,
			ServiceContext serviceContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected SearchContext buildSearchContext(
		long companyId, long[] groupIds, String title, long[] parentCategoryIds,
		long[] vocabularyIds, int start, int end, Sort sort) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected long[] getCategoryIds(List<AssetCategory> categories) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected String[] getCategoryNames(List<AssetCategory> categories) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected BaseModelSearchResult<AssetCategory> searchCategories(
			SearchContext searchContext)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected void updateChildrenVocabularyId(
		AssetCategory category, long vocabularyId) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

	protected void validate(
			long categoryId, long parentCategoryId, String name,
			long vocabularyId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.service.impl." +
					"AssetCategoryLocalServiceImpl");
	}

}