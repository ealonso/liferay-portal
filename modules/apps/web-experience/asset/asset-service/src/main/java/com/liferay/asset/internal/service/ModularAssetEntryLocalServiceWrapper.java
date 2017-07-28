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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceWrapper;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.service.AssetEntryLocalService;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetEntryLocalServiceWrapper
	extends AssetEntryLocalServiceWrapper {

	public ModularAssetEntryLocalServiceWrapper() {
		super(null);
	}

	public ModularAssetEntryLocalServiceWrapper(
		com.liferay.asset.
			kernel.service.AssetEntryLocalService assetEntryLocalService) {

		super(assetEntryLocalService);
	}

	@Override
	public void deleteEntry(AssetEntry entry) throws PortalException {
		_assetEntryLocalService.deleteEntry(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetEntry.class, entry));
	}

	@Override
	public void deleteEntry(long entryId) throws PortalException {
		_assetEntryLocalService.deleteEntry(entryId);
	}

	@Override
	public void deleteEntry(String className, long classPK)
		throws PortalException {

		_assetEntryLocalService.deleteEntry(className, classPK);
	}

	@Override
	public void deleteGroupEntries(long groupId) throws PortalException {
		_assetEntryLocalService.deleteGroupEntries(groupId);
	}

	@Override
	public AssetEntry fetchEntry(long entryId) {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.fetchEntry(entryId));
	}

	@Override
	public AssetEntry fetchEntry(long groupId, String classUuid) {
		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.fetchEntry(groupId, classUuid));
	}

	@Override
	public AssetEntry fetchEntry(String className, long classPK) {
		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.fetchEntry(className, classPK));
	}

	@Override
	public List<AssetEntry> getAncestorEntries(long entryId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getAncestorEntries(entryId));
	}

	@Override
	public List<AssetEntry> getChildEntries(long entryId)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.getChildEntries(entryId));
	}

	@Override
	public List<AssetEntry> getCompanyEntries(
		long companyId, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getCompanyEntries(companyId, start, end));
	}

	@Override
	public int getCompanyEntriesCount(long companyId) {
		return _assetEntryLocalService.getCompanyEntriesCount(companyId);
	}

	@Override
	public List<AssetEntry> getEntries(AssetEntryQuery entryQuery) {
		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getEntries(
				ModelAdapterUtil.adapt(
					com.liferay.asset.service.persistence.AssetEntryQuery.class,
					entryQuery)));
	}

	@Override
	public List<AssetEntry> getEntries(
		long[] groupIds, long[] classNameIds, String keywords, String userName,
		String title, String description, Boolean listable,
		boolean advancedSearch, boolean andOperator, int start, int end,
		String orderByCol1, String orderByCol2, String orderByType1,
		String orderByType2) {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getEntries(
				groupIds, classNameIds, keywords, userName, title, description,
				listable, advancedSearch, andOperator, start, end, orderByCol1,
				orderByCol2, orderByType1, orderByType2));
	}

	@Override
	public int getEntriesCount(AssetEntryQuery entryQuery) {
		return _assetEntryLocalService.getEntriesCount(
			ModelAdapterUtil.adapt(
				com.liferay.asset.service.persistence.AssetEntryQuery.class,
				entryQuery));
	}

	@Override
	public int getEntriesCount(
		long[] groupIds, long[] classNameIds, String keywords, String userName,
		String title, String description, Boolean listable,
		boolean advancedSearch, boolean andOperator) {

		return _assetEntryLocalService.getEntriesCount(
			groupIds, classNameIds, keywords, userName, title, description,
			listable, advancedSearch, andOperator);
	}

	@Override
	public AssetEntry getEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.getEntry(entryId));
	}

	@Override
	public AssetEntry getEntry(long groupId, String classUuid)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getEntry(groupId, classUuid));
	}

	@Override
	public AssetEntry getEntry(String className, long classPK)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getEntry(className, classPK));
	}

	@Override
	public List<AssetEntry> getGroupEntries(long groupId) {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.getGroupEntries(groupId));
	}

	@Override
	public AssetEntry getNextEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.getNextEntry(entryId));
	}

	@Override
	public AssetEntry getParentEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryLocalService.getParentEntry(entryId));
	}

	@Override
	public AssetEntry getPreviousEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getPreviousEntry(entryId));
	}

	@Override
	public List<AssetEntry> getTopViewedEntries(
		String className, boolean asc, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getTopViewedEntries(
				className, asc, start, end));
	}

	@Override
	public List<AssetEntry> getTopViewedEntries(
		String[] className, boolean asc, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.getTopViewedEntries(
				className, asc, start, end));
	}

	@Override
	public void incrementViewCounter(long userId, AssetEntry assetEntry)
		throws PortalException {

		_assetEntryLocalService.incrementViewCounter(
			userId,
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetEntry.class, assetEntry));
	}

	@Override
	public AssetEntry incrementViewCounter(
			long userId, String className, long classPK)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.incrementViewCounter(
				userId, className, classPK));
	}

	@Override
	public void incrementViewCounter(
		long userId, String className, long classPK, int increment) {

		_assetEntryLocalService.incrementViewCounter(
			userId, className, classPK, increment);
	}

	@Override
	public void reindex(List<AssetEntry> entries) throws PortalException {
		_assetEntryLocalService.reindex(
			ModelAdapterUtil.adapt(
				com.liferay.asset.model.AssetEntry.class, entries));
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable, int status,
		int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, keywords,
			showNonindexable, status, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, keywords,
			showNonindexable, statuses, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses, int start, int end, Sort sort) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, keywords,
			showNonindexable, statuses, start, end, sort);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, int status, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, keywords,
			status, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int status, boolean andSearch, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, userName,
			title, description, assetCategoryIds, assetTagNames,
			showNonindexable, status, andSearch, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, userName,
			title, description, assetCategoryIds, assetTagNames,
			showNonindexable, statuses, andSearch, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, int status,
		boolean andSearch, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, classTypeId, userName,
			title, description, assetCategoryIds, assetTagNames, status,
			andSearch, start, end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		String keywords, int status, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, keywords, status, start,
			end);
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, int status,
		boolean andSearch, int start, int end) {

		return _assetEntryLocalService.search(
			companyId, groupIds, userId, className, userName, title,
			description, assetCategoryIds, assetTagNames, status, andSearch,
			start, end);
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses) {

		return _assetEntryLocalService.searchCount(
			companyId, groupIds, userId, className, classTypeId, keywords,
			showNonindexable, statuses);
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showInvisible,
		boolean showNonindexable, int[] statuses, boolean andSearch) {

		return _assetEntryLocalService.searchCount(
			companyId, groupIds, userId, className, classTypeId, userName,
			title, description, assetCategoryIds, assetTagNames, showInvisible,
			showNonindexable, statuses, andSearch);
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch) {

		return _assetEntryLocalService.searchCount(
			companyId, groupIds, userId, className, classTypeId, userName,
			title, description, assetCategoryIds, assetTagNames,
			showNonindexable, statuses, andSearch);
	}

	@Override
	public AssetEntry updateEntry(
			long userId, long groupId, Date createDate, Date modifiedDate,
			String className, long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean listable,
			boolean visible, Date startDate, Date endDate, Date publishDate,
			Date expirationDate, String mimeType, String title,
			String description, String summary, String url, String layoutUuid,
			int height, int width, Double priority)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				userId, groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, listable,
				visible, startDate, endDate, publishDate, expirationDate,
				mimeType, title, description, summary, url, layoutUuid, height,
				width, priority));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(long, long,
	 *             Date, Date, String, long, String, long, long[], String[],
	 *             boolean, boolean, Date, Date, Date, Date, String, String,
	 *             String, String, String, String, int, int, Double)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			long userId, long groupId, Date createDate, Date modifiedDate,
			String className, long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean listable,
			boolean visible, Date startDate, Date endDate, Date expirationDate,
			String mimeType, String title, String description, String summary,
			String url, String layoutUuid, int height, int width,
			Double priority)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				userId, groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, listable,
				visible, startDate, endDate, expirationDate, mimeType, title,
				description, summary, url, layoutUuid, height, width,
				priority));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(long, long,
	 *             Date, Date, String, long, String, long, long[], String[],
	 *             boolean, boolean, Date, Date, Date, Date, String, String,
	 *             String, String, String, String, int, int, Double)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			long userId, long groupId, Date createDate, Date modifiedDate,
			String className, long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean visible,
			Date startDate, Date endDate, Date expirationDate, String mimeType,
			String title, String description, String summary, String url,
			String layoutUuid, int height, int width, Integer priority,
			boolean sync)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				userId, groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, visible,
				startDate, endDate, expirationDate, mimeType, title,
				description, summary, url, layoutUuid, height, width, priority,
				sync));
	}

	@Override
	public AssetEntry updateEntry(
			long userId, long groupId, String className, long classPK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				userId, groupId, className, classPK, categoryIds, tagNames));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(String, long,
	 *             Date, Date, boolean, boolean)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate, boolean visible)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				className, classPK, publishDate, visible));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(String, long,
	 *             Date, Date, boolean, boolean)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate,
			Date expirationDate, boolean visible)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				className, classPK, publishDate, expirationDate, visible));
	}

	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate,
			Date expirationDate, boolean listable, boolean visible)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateEntry(
				className, classPK, publishDate, expirationDate, listable,
				visible));
	}

	@Override
	public AssetEntry updateVisible(AssetEntry entry, boolean visible)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateVisible(
				ModelAdapterUtil.adapt(
					com.liferay.asset.model.AssetEntry.class, entry),
				visible));
	}

	@Override
	public AssetEntry updateVisible(
			String className, long classPK, boolean visible)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryLocalService.updateVisible(className, classPK, visible));
	}

	@Override
	public void validate(
			long groupId, String className, long classPK, long classTypePK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		_assetEntryLocalService.validate(
			groupId, className, classPK, classTypePK, categoryIds, tagNames);
	}

	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		_assetEntryLocalService.validate(
			groupId, className, classTypePK, categoryIds, tagNames);
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #validate(long, String, long,
	 *             long[], String[])}
	 */
	@Deprecated
	@Override
	public void validate(
			long groupId, String className, long[] categoryIds,
			String[] tagNames)
		throws PortalException {

		_assetEntryLocalService.validate(
			groupId, className, categoryIds, tagNames);
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}