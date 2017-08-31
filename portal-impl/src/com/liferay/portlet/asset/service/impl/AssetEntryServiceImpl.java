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
import com.liferay.portlet.asset.service.base.AssetEntryServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * Provides the remote service for accessing and updating asset entries. Its
 * methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Bruno Farache
 * @author Raymond Augé
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.model.impl.AssetEntryServiceImpl}
 */
@Deprecated
public class AssetEntryServiceImpl extends AssetEntryServiceBaseImpl {

	@Override
	public AssetEntry fetchEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public List<AssetEntry> getCompanyEntries(
		long companyId, int start, int end) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public int getCompanyEntriesCount(long companyId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public List<AssetEntry> getEntries(AssetEntryQuery entryQuery)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public int getEntriesCount(AssetEntryQuery entryQuery)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public AssetEntry getEntry(long entryId) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public void incrementViewCounter(AssetEntry assetEntry)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public AssetEntry incrementViewCounter(String className, long classPK)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	@Override
	public AssetEntry updateEntry(
			long groupId, Date createDate, Date modifiedDate, String className,
			long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean listable,
			boolean visible, Date startDate, Date endDate, Date publishDate,
			Date expirationDate, String mimeType, String title,
			String description, String summary, String url, String layoutUuid,
			int height, int width, Double priority)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, Date,
	 *             Date, String, long, String, long, long[], String[], boolean,
	 *             boolean, Date, Date, Date, Date, String, String, String,
	 *             String, String, String, int, int, Double)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			long groupId, Date createDate, Date modifiedDate, String className,
			long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean listable,
			boolean visible, Date startDate, Date endDate, Date expirationDate,
			String mimeType, String title, String description, String summary,
			String url, String layoutUuid, int height, int width,
			Double priority)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #updateEntry(long, Date,
	 *             Date, String, long, String, long, long[], String[], boolean,
	 *             boolean, Date, Date, Date, Date, String, String, String,
	 *             String, String, String, int, int, Double)}
	 */
	@Deprecated
	@Override
	public AssetEntry updateEntry(
			long groupId, Date createDate, Date modifiedDate, String className,
			long classPK, String classUuid, long classTypeId,
			long[] categoryIds, String[] tagNames, boolean visible,
			Date startDate, Date endDate, Date expirationDate, String mimeType,
			String title, String description, String summary, String url,
			String layoutUuid, int height, int width, Integer priority,
			boolean sync)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.service.impl.AssetEntryServiceImpl");
	}

}