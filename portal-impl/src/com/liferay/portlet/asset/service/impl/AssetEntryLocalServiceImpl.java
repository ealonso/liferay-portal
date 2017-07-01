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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.increment.BufferedIncrement;
import com.liferay.portal.kernel.increment.NumberIncrement;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portlet.asset.service.base.AssetEntryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service for accessing, deleting, updating, and validating
 * asset entries.
 *
 * @author Brian Wing Shun Chan
 * @author Bruno Farache
 * @author Zsolt Berentey
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.model.impl.AssetEntryLocalServiceImpl}
 */
@Deprecated
public class AssetEntryLocalServiceImpl extends AssetEntryLocalServiceBaseImpl {

	@Override
	public void deleteEntry(AssetEntry entry) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void deleteEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void deleteEntry(String className, long classPK)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void deleteGroupEntries(long groupId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void destroy() {
		super.destroy();

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry fetchEntry(long entryId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry fetchEntry(long groupId, String classUuid) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry fetchEntry(String className, long classPK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getAncestorEntries(long entryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getChildEntries(long entryId)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getCompanyEntries(
		long companyId, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public int getCompanyEntriesCount(long companyId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getEntries(AssetEntryQuery entryQuery) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getEntries(
		long[] groupIds, long[] classNameIds, String keywords, String userName,
		String title, String description, Boolean listable,
		boolean advancedSearch, boolean andOperator, int start, int end,
		String orderByCol1, String orderByCol2, String orderByType1,
		String orderByType2) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public int getEntriesCount(AssetEntryQuery entryQuery) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public int getEntriesCount(
		long[] groupIds, long[] classNameIds, String keywords, String userName,
		String title, String description, Boolean listable,
		boolean advancedSearch, boolean andOperator) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getEntry(long groupId, String classUuid)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getEntry(String className, long classPK)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getGroupEntries(long groupId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getNextEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getParentEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry getPreviousEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getTopViewedEntries(
		String className, boolean asc, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public List<AssetEntry> getTopViewedEntries(
		String[] className, boolean asc, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void incrementViewCounter(long userId, AssetEntry assetEntry)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetEntry incrementViewCounter(
			long userId, String className, long classPK)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@BufferedIncrement(
		configuration = "AssetEntry", incrementClass = NumberIncrement.class
	)
	@Override
	public void incrementViewCounter(
		long userId, String className, long classPK, int increment) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void reindex(List<AssetEntry> entries) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable, int status,
		int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses, int start, int end, Sort sort) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, int status, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int status, boolean andSearch, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, int status,
		boolean andSearch, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		String keywords, int status, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public Hits search(
		long companyId, long[] groupIds, long userId, String className,
		String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, int status,
		boolean andSearch, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String keywords, boolean showNonindexable,
		int[] statuses) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showInvisible,
		boolean showNonindexable, int[] statuses, boolean andSearch) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public long searchCount(
		long companyId, long[] groupIds, long userId, String className,
		long classTypeId, String userName, String title, String description,
		String assetCategoryIds, String assetTagNames, boolean showNonindexable,
		int[] statuses, boolean andSearch) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
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

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, long,
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

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, long,
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

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry updateEntry(
			long userId, long groupId, String className, long classPK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(String, long,
	 *             Date, Date, boolean, boolean)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate, boolean visible)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(String, long,
	 *             Date, Date, boolean, boolean)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate,
			Date expirationDate, boolean visible)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry updateEntry(
			String className, long classPK, Date publishDate,
			Date expirationDate, boolean listable, boolean visible)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry updateVisible(AssetEntry entry, boolean visible)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public AssetEntry updateVisible(
			String className, long classPK, boolean visible)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void validate(
			long groupId, String className, long classPK, long classTypePK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	@Override
	public void validate(
			long groupId, String className, long classTypePK,
			long[] categoryIds, String[] tagNames)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #validate(long, String, long,
	 *             long[], String[])}
	 */
	@Deprecated
	@Override
	public void validate(
			long groupId, String className, long[] categoryIds,
			String[] tagNames)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryLocalServiceImpl");
	}

}