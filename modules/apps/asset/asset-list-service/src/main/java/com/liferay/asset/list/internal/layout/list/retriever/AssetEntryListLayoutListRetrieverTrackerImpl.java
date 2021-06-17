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

package com.liferay.asset.list.internal.layout.list.retriever;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.list.asset.entry.provider.AssetListAssetEntryProvider;
import com.liferay.asset.list.info.filter.AssetEntryListInfoFilter;
import com.liferay.asset.list.layout.list.retriever.AssetEntryListLayoutListRetrieverTracker;
import com.liferay.asset.list.layout.list.retriever.AssetLayoutListRetriever;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.info.filter.InfoFilter;
import com.liferay.info.list.provider.CollectionQuery;
import com.liferay.info.list.provider.InfoItemListProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.petra.reflect.GenericUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true, service = AssetEntryListLayoutListRetrieverTracker.class
)
public class AssetEntryListLayoutListRetrieverTrackerImpl
	implements AssetEntryListLayoutListRetrieverTracker {

	@Override
	public void register(AssetListEntry assetListEntry) {
		AssetLayoutListRetriever<?> assetLayoutListRetriever =
			_assetLayoutListRetrievers.get(assetListEntry.getAssetEntryType());

		InfoItemListProvider<?, ?> infoItemListProvider = null;

		if (assetLayoutListRetriever != null) {
			infoItemListProvider = assetLayoutListRetriever.create(
				assetListEntry);
		}
		else {
			infoItemListProvider = new AssetEntryListInfoListProvider(
				assetListEntry);
		}

		_bundleContext.registerService(
			InfoItemListProvider.class, infoItemListProvider,
			new HashMapDictionary<>());
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	public void registerAssetLayoutListRetriever(
		AssetLayoutListRetriever<?> assetLayoutListRetriever) {

		_assetLayoutListRetrievers.put(
			GenericUtil.getGenericClassName(assetLayoutListRetriever),
			assetLayoutListRetriever);
	}

	public void unregisterAssetLayoutListRetriever(
		AssetLayoutListRetriever<?> assetLayoutListRetriever) {

		_assetLayoutListRetrievers.remove(
			GenericUtil.getGenericClassName(assetLayoutListRetriever));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		List<AssetListEntry> assetListEntries =
			_assetListEntryLocalService.getAssetListEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AssetListEntry assetListEntry : assetListEntries) {
			register(assetListEntry);
		}
	}

	@Deactivate
	protected void deactivate() {
		_bundleContext = null;
	}

	private final Map<String, AssetLayoutListRetriever<?>>
		_assetLayoutListRetrievers = new ConcurrentHashMap<>();

	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@Reference
	private AssetListEntryLocalService _assetListEntryLocalService;

	private BundleContext _bundleContext;

	private class AssetEntryListInfoListProvider
		implements InfoItemListProvider<AssetEntry, AssetEntryListInfoFilter> {

		public AssetEntryListInfoListProvider(AssetListEntry assetListEntry) {
			_assetListEntry = assetListEntry;
		}

		@Override
		public InfoPage<? extends AssetEntry> getInfoPage(
			CollectionQuery collectionQuery) {

			return InfoPage.of(
				_getList(collectionQuery), collectionQuery.getPagination(),
				_getListCount(collectionQuery));
		}

		@Override
		public String getLabel(Locale locale) {
			return _assetListEntry.getTitle();
		}

		private List<AssetEntry> _getList(CollectionQuery collectionQuery) {
			long[][] assetCategoryIds = new long[0][];

			InfoFilter infoFilter = collectionQuery.getInfoFilter();

			if (infoFilter instanceof AssetEntryListInfoFilter) {
				AssetEntryListInfoFilter assetEntryListInfoFilter =
					(AssetEntryListInfoFilter)infoFilter;

				assetCategoryIds =
					assetEntryListInfoFilter.getAssetCategoryIds();
			}

			Pagination pagination = collectionQuery.getPagination();

			return _assetListAssetEntryProvider.getAssetEntries(
				_assetListEntry, collectionQuery.getSegmentEntryIds(),
				assetCategoryIds, StringPool.BLANK, pagination.getStart(),
				pagination.getEnd());
		}

		private int _getListCount(CollectionQuery collectionQuery) {
			long[][] assetCategoryIds = new long[0][];

			InfoFilter infoFilter = collectionQuery.getInfoFilter();

			if (infoFilter instanceof AssetEntryListInfoFilter) {
				AssetEntryListInfoFilter assetEntryListInfoFilter =
					(AssetEntryListInfoFilter)infoFilter;

				assetCategoryIds =
					assetEntryListInfoFilter.getAssetCategoryIds();
			}

			return _assetListAssetEntryProvider.getAssetEntriesCount(
				_assetListEntry, collectionQuery.getSegmentEntryIds(),
				assetCategoryIds, StringPool.BLANK);
		}

		private final AssetListEntry _assetListEntry;

	}

}