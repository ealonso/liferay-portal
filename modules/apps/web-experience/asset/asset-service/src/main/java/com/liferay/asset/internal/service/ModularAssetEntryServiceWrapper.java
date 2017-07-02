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
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.asset.kernel.service.AssetEntryServiceWrapper;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class ModularAssetEntryServiceWrapper extends AssetEntryServiceWrapper {

	public ModularAssetEntryServiceWrapper() {
		super(null);
	}

	public ModularAssetEntryServiceWrapper(
		AssetEntryService assetEntryService) {

		super(assetEntryService);
	}

	@Override
	public AssetEntry fetchEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryService.fetchEntry(entryId));
	}

	@Override
	public List<AssetEntry> getCompanyEntries(
		long companyId, int start, int end) {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryService.getCompanyEntries(companyId, start, end));
	}

	@Override
	public int getCompanyEntriesCount(long companyId) {
		return _assetEntryService.getCompanyEntriesCount(companyId);
	}

	@Override
	public List<AssetEntry> getEntries(AssetEntryQuery entryQuery)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryService.getEntries(entryQuery));
	}

	@Override
	public int getEntriesCount(AssetEntryQuery entryQuery)
		throws PortalException {

		return _assetEntryService.getEntriesCount(entryQuery);
	}

	@Override
	public AssetEntry getEntry(long entryId) throws PortalException {
		return ModelAdapterUtil.adapt(
			AssetEntry.class, _assetEntryService.getEntry(entryId));
	}

	@Override
	public void incrementViewCounter(AssetEntry assetEntry)
		throws PortalException {

		_assetEntryService.incrementViewCounter(assetEntry);
	}

	@Override
	public AssetEntry incrementViewCounter(String className, long classPK)
		throws PortalException {

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryService.incrementViewCounter(className, classPK));
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

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryService.updateEntry(
				groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, listable,
				visible, startDate, endDate, publishDate, expirationDate,
				mimeType, title, description, summary, url, layoutUuid, height,
				width, priority));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(long, Date,
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

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryService.updateEntry(
				groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, listable,
				visible, startDate, endDate, expirationDate, mimeType, title,
				description, summary, url, layoutUuid, height, width,
				priority));
	}

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #updateEntry(long, Date,
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

		return ModelAdapterUtil.adapt(
			AssetEntry.class,
			_assetEntryService.updateEntry(
				groupId, createDate, modifiedDate, className, classPK,
				classUuid, classTypeId, categoryIds, tagNames, visible,
				startDate, endDate, expirationDate, mimeType, title,
				description, summary, url, layoutUuid, height, width, priority,
				sync));
	}

	@Reference
	private AssetEntryService _assetEntryService;

}