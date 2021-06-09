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

package com.liferay.asset.internal.info.list.provider;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.util.AssetHelper;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;
import com.liferay.info.pagination.Pagination;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.resource.bundle.ResourceBundleLoader;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
public abstract class BaseAssetsInfoListProvider
	implements InfoListProvider<AssetEntry> {

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext) {

		return getInfoList(infoListProviderContext, null, null);
	}

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		com.liferay.info.sort.Sort sort) {

		try {
			Hits hits = assetHelper.search(
				_getSearchContext(infoListProviderContext),
				getAssetEntryQuery(infoListProviderContext, null),
				pagination.getStart(), pagination.getEnd());

			return assetHelper.getAssetEntries(hits);
		}
		catch (Exception exception) {
			_log.error("Unable to get asset entries", exception);
		}

		return Collections.emptyList();
	}

	@Override
	public int getInfoListCount(
		InfoListProviderContext infoListProviderContext) {

		try {
			Long count = assetHelper.searchCount(
				_getSearchContext(infoListProviderContext),
				getAssetEntryQuery(infoListProviderContext, null));

			return count.intValue();
		}
		catch (Exception exception) {
			_log.error("Unable to get asset entries count", exception);
		}

		return 0;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleLoader.loadResourceBundle(
			locale);

		return LanguageUtil.get(resourceBundle, getLabelKey());
	}

	protected AssetEntryQuery getAssetEntryQuery(
		InfoListProviderContext infoListProviderContext,
		Pagination pagination) {

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		Company company = infoListProviderContext.getCompany();

		long[] availableClassNameIds =
			AssetRendererFactoryRegistryUtil.getClassNameIds(
				company.getCompanyId(), true);

		availableClassNameIds = ArrayUtil.filter(
			availableClassNameIds,
			availableClassNameId -> {
				Indexer<?> indexer = IndexerRegistryUtil.getIndexer(
					portal.getClassName(availableClassNameId));

				if (indexer == null) {
					return false;
				}

				return true;
			});

		assetEntryQuery.setClassNameIds(availableClassNameIds);

		assetEntryQuery.setEnablePermissions(true);

		Optional<Group> groupOptional =
			infoListProviderContext.getGroupOptional();

		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();

			assetEntryQuery.setGroupIds(new long[] {group.getGroupId()});
		}

		if (pagination != null) {
			assetEntryQuery.setStart(pagination.getStart());
			assetEntryQuery.setEnd(pagination.getEnd());
		}

		assetEntryQuery.setOrderByCol1(getOrderByCol());
		assetEntryQuery.setOrderByType1("DESC");

		assetEntryQuery.setOrderByCol2(Field.CREATE_DATE);
		assetEntryQuery.setOrderByType2("DESC");

		return assetEntryQuery;
	}

	protected abstract String getLabelKey();

	protected String getOrderByCol() {
		return Field.MODIFIED_DATE;
	}

	@Reference
	protected AssetHelper assetHelper;

	@Reference
	protected Portal portal;

	@Reference(target = "(bundle.symbolic.name=com.liferay.asset.service)")
	protected ResourceBundleLoader resourceBundleLoader;

	private SearchContext _getSearchContext(
			InfoListProviderContext infoListProviderContext)
		throws Exception {

		Company company = infoListProviderContext.getCompany();

		long groupId = company.getGroupId();

		Optional<Group> groupOptional =
			infoListProviderContext.getGroupOptional();

		if (groupOptional.isPresent()) {
			Group group = groupOptional.get();

			groupId = group.getGroupId();
		}

		User user = infoListProviderContext.getUser();

		Optional<Layout> layoutOptional =
			infoListProviderContext.getLayoutOptional();

		SearchContext searchContext = SearchContextFactory.getInstance(
			new long[0], new String[0], new HashMap<>(), company.getCompanyId(),
			null, layoutOptional.orElse(null), null, groupId, null,
			user.getUserId());

		searchContext.setSorts(
			SortFactoryUtil.create(getOrderByCol(), Sort.LONG_TYPE, true),
			SortFactoryUtil.create(Field.CREATE_DATE, Sort.LONG_TYPE, true));

		return searchContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseAssetsInfoListProvider.class);

}