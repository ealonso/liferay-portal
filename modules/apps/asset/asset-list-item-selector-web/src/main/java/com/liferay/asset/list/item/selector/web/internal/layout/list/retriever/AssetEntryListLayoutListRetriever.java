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

package com.liferay.asset.list.item.selector.web.internal.layout.list.retriever;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.list.asset.entry.provider.AssetListAssetEntryProvider;
import com.liferay.asset.list.constants.AssetListEntryTypeConstants;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.model.AssetListEntrySegmentsEntryRel;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.asset.list.service.AssetListEntrySegmentsEntryRelLocalService;
import com.liferay.info.pagination.Pagination;
import com.liferay.item.selector.criteria.InfoListItemSelectorReturnType;
import com.liferay.layout.list.retriever.ClassedModelListObjectReference;
import com.liferay.layout.list.retriever.LayoutListRetriever;
import com.liferay.layout.list.retriever.LayoutListRetrieverContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = LayoutListRetriever.class)
public class AssetEntryListLayoutListRetriever
	implements LayoutListRetriever
		<InfoListItemSelectorReturnType, ClassedModelListObjectReference> {

	@Override
	public List getList(
		ClassedModelListObjectReference classedModelListObjectReference,
		LayoutListRetrieverContext layoutListRetrieverContext) {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.fetchAssetListEntry(
				classedModelListObjectReference.getClassPK());

		if (assetListEntry == null) {
			return Collections.emptyList();
		}

		Optional<long[]> segmentsExperienceIdsOptional =
			layoutListRetrieverContext.getSegmentsExperienceIdsOptional();

		long[] segmentsExperienceIds = segmentsExperienceIdsOptional.orElse(
			new long[] {0});

		Optional<Pagination> paginationOptional =
			layoutListRetrieverContext.getPaginationOptional();

		Pagination pagination = paginationOptional.orElse(
			Pagination.of(QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		List<AssetEntry> assetEntries =
			_assetListAssetEntryProvider.getAssetEntries(
				assetListEntry, segmentsExperienceIds[0], pagination.getStart(),
				pagination.getEnd());

		if (Objects.equals(
				AssetEntry.class.getName(), _getClassName(assetListEntry))) {

			return assetEntries;
		}

		return _toAssetObjects(assetEntries);
	}

	@Override
	public int getListCount(
		ClassedModelListObjectReference classedModelListObjectReference,
		LayoutListRetrieverContext layoutListRetrieverContext) {

		AssetListEntry assetListEntry =
			_assetListEntryLocalService.fetchAssetListEntry(
				classedModelListObjectReference.getClassPK());

		if (assetListEntry == null) {
			return 0;
		}

		Optional<long[]> segmentsExperienceIdsOptional =
			layoutListRetrieverContext.getSegmentsExperienceIdsOptional();

		long[] segmentsExperienceIds = segmentsExperienceIdsOptional.orElse(
			new long[] {0});

		return _assetListAssetEntryProvider.getAssetEntriesCount(
			assetListEntry, segmentsExperienceIds[0]);
	}

	private String _getClassName(AssetListEntry assetListEntry) {
		if (assetListEntry.getType() ==
				AssetListEntryTypeConstants.TYPE_MANUAL) {

			return AssetEntry.class.getName();
		}

		try {
			String className = StringPool.BLANK;

			List<AssetListEntrySegmentsEntryRel>
				assetListEntrySegmentsEntryRels =
					_assetListEntrySegmentsEntryRelLocalService.
						getAssetListEntrySegmentsEntryRels(
							assetListEntry.getAssetListEntryId(),
							QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (AssetListEntrySegmentsEntryRel assetListEntrySegmentsEntryRel :
					assetListEntrySegmentsEntryRels) {

				UnicodeProperties unicodeProperties = new UnicodeProperties();

				unicodeProperties.load(
					assetListEntrySegmentsEntryRel.getTypeSettings());

				long defaultClassNameId = GetterUtil.getLong(
					unicodeProperties.getProperty("anyAssetType", null));

				if (defaultClassNameId <= 0) {
					return AssetEntry.class.getName();
				}

				if (Validator.isNull(className)) {
					className = _portal.getClassName(defaultClassNameId);
				}
				else if (!Objects.equals(
							className,
							_portal.getClassName(defaultClassNameId))) {

					return AssetEntry.class.getName();
				}
			}

			return className;
		}
		catch (IOException ioException) {
			if (_log.isDebugEnabled()) {
				_log.debug(ioException, ioException);
			}
		}

		return AssetEntry.class.getName();
	}

	private List _toAssetObjects(List<AssetEntry> assetEntries) {
		List list = new ArrayList(assetEntries.size());

		for (AssetEntry assetEntry : assetEntries) {
			AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

			list.add(assetRenderer.getAssetObject());
		}

		return list;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryListLayoutListRetriever.class);

	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@Reference
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Reference
	private AssetListEntrySegmentsEntryRelLocalService
		_assetListEntrySegmentsEntryRelLocalService;

	@Reference
	private Portal _portal;

}