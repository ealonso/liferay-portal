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

package com.liferay.asset.categories.internal.search;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyConstants;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Localization;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.document.DocumentBuilder;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchRequest;
import com.liferay.portal.search.engine.adapter.search.CountSearchResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 */
@Component(
	immediate = true, service = AssetEntryAssetCategoriesBatchReindexer.class
)
public class AssetEntryAssetCategoriesBatchReindexer {

	public void reindex(AssetVocabulary assetVocabulary) {
		if (assetVocabulary == null) {
			return;
		}

		List<AssetCategory> assetCategories =
			_assetCategoryLocalService.getVocabularyCategories(
				assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Stream<AssetCategory> assetCategoriesStream = assetCategories.stream();

		List<Long> assetCategoryIds = assetCategoriesStream.map(
			AssetCategory::getCategoryId
		).collect(
			Collectors.toList()
		);

		Set<Long> assetEntryIds = _getAssetEntryIds(assetCategoryIds);

		for (long assetEntryId : assetEntryIds) {
			AssetEntry assetEntry = _assetEntryLocalService.fetchAssetEntry(
				assetEntryId);

			_reindex(
				assetEntry, assetCategories, assetCategoryIds,
				assetVocabulary.getVisibilityType());
		}
	}

	private long _getAssetEntryCount(String indexName, TermQuery termQuery) {
		CountSearchRequest countSearchRequest = new CountSearchRequest();

		countSearchRequest.setIndexNames(indexName);
		countSearchRequest.setQuery(termQuery);

		CountSearchResponse countSearchResponse = _searchEngineAdapter.execute(
			countSearchRequest);

		return countSearchResponse.getCount();
	}

	private Set<Long> _getAssetEntryIds(List<Long> assetCategoryIds) {
		Set<Long> assetEntryIds = new HashSet<>();

		for (long assetCategoryId : assetCategoryIds) {
			List<AssetEntryAssetCategoryRel> assetEntryAssetCategoryRels =
				_assetEntryAssetCategoryRelLocalService.
					getAssetEntryAssetCategoryRelsByAssetCategoryId(
						assetCategoryId);

			for (AssetEntryAssetCategoryRel assetEntryAssetCategoryRel :
					assetEntryAssetCategoryRels) {

				assetEntryIds.add(assetEntryAssetCategoryRel.getAssetEntryId());
			}
		}

		return assetEntryIds;
	}

	private Map<Long, Map<Locale, String>> _getCategoryIdLocaleTitleMap(
		List<AssetCategory> assetCategories) {

		Map<Long, Map<Locale, String>> categoryIdLocaleTitleMap =
			new HashMap<>();

		Stream<AssetCategory> stream = assetCategories.stream();

		stream.forEach(
			assetCategory -> categoryIdLocaleTitleMap.put(
				assetCategory.getCategoryId(), assetCategory.getTitleMap()));

		return categoryIdLocaleTitleMap;
	}

	private Localization _getLocalization() {

		// See LPS-72507 and LPS-76500

		if (_localization != null) {
			return _localization;
		}

		return LocalizationUtil.getLocalization();
	}

	private List<SearchHit> _getSearchHits(
		long assetEntryClassPK, String indexName,
		List<String> publicCategoryTitleFields) {

		TermQuery termQuery = _queries.term(
			Field.ENTRY_CLASS_PK, assetEntryClassPK);

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setFetchSource(true);
		searchSearchRequest.setIndexNames(indexName);

		List<String> sourceIncludes = ListUtil.concat(
			ListUtil.fromArray(
				Field.ASSET_CATEGORY_IDS, Field.ASSET_PUBLIC_CATEGORY_IDS,
				Field.UID),
			publicCategoryTitleFields);

		searchSearchRequest.setFetchSourceIncludes(
			sourceIncludes.toArray(new String[0]));

		searchSearchRequest.setQuery(termQuery);
		searchSearchRequest.setSize(
			Math.toIntExact(_getAssetEntryCount(indexName, termQuery)));

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		SearchHits searchHits = searchSearchResponse.getSearchHits();

		return searchHits.getSearchHits();
	}

	private void _reindex(
		AssetEntry assetEntry, List<AssetCategory> assetVocabularyCategories,
		List<Long> assetVocabularyCategoryIds, int visibilityType) {

		if (assetEntry == null) {
			return;
		}

		String indexName = _indexNameBuilder.getIndexName(
			assetEntry.getCompanyId());

		Set<Locale> locales = LanguageUtil.getAvailableLocales();

		Stream<Locale> localesStream = locales.stream();

		Localization localization = _getLocalization();

		Map<Locale, String> localePublicCategoryTitleFieldMap =
			localesStream.collect(
				Collectors.toMap(
					locale -> locale,
					locale -> localization.getLocalizedName(
						Field.ASSET_PUBLIC_CATEGORY_TITLES,
						LocaleUtil.toLanguageId(locale))));

		List<String> publicCategoryTitleFields = new ArrayList<>(
			localePublicCategoryTitleFieldMap.values());

		List<SearchHit> searchHits = _getSearchHits(
			assetEntry.getClassPK(), indexName, publicCategoryTitleFields);

		Map<Long, Map<Locale, String>> assetVocabularyCategoryIdLocaleTitleMap =
			_getCategoryIdLocaleTitleMap(assetVocabularyCategories);

		for (SearchHit searchHit : searchHits) {
			Map<Locale, List<String>> assetEntryLocalePublicCategoryTitlesMap =
				new HashMap<>();

			Document document = searchHit.getDocument();

			List<Long> assetEntryPublicCategoryIds = document.getLongs(
				Field.ASSET_PUBLIC_CATEGORY_IDS);

			Set<Long> updatedAssetEntryPublicCategoryIds = new HashSet<>(
				assetEntryPublicCategoryIds);

			for (Locale locale : locales) {
				List<String> assetEntryPublicCategoryTitles =
					document.getStrings(
						localePublicCategoryTitleFieldMap.get(locale));

				assetEntryLocalePublicCategoryTitlesMap.put(
					locale, assetEntryPublicCategoryTitles);
			}

			boolean reindex = false;

			if (visibilityType ==
					AssetVocabularyConstants.VISIBILITY_TYPE_PUBLIC) {

				List<Long> assetEntryCategoryIds = document.getLongs(
					Field.ASSET_CATEGORY_IDS);

				for (Long assetCategoryId : assetEntryCategoryIds) {
					if (assetVocabularyCategoryIds.contains(assetCategoryId) &&
						!updatedAssetEntryPublicCategoryIds.contains(
							assetCategoryId)) {

						updatedAssetEntryPublicCategoryIds.add(assetCategoryId);

						for (Locale locale : locales) {
							Map<Locale, String>
								assetVocabularyCategoryLocaleTitleMap =
									assetVocabularyCategoryIdLocaleTitleMap.get(
										assetCategoryId);

							String assetVocabularyCategoryTitle =
								assetVocabularyCategoryLocaleTitleMap.get(
									locale);

							List<String> assetEntryPublicCategoryTitles =
								assetEntryLocalePublicCategoryTitlesMap.get(
									locale);

							assetEntryPublicCategoryTitles.add(
								assetVocabularyCategoryTitle);
						}

						reindex = true;
					}
				}
			}
			else {
				for (Long assetPublicCategoryId : assetEntryPublicCategoryIds) {
					if (assetVocabularyCategoryIds.contains(
							assetPublicCategoryId) &&
						updatedAssetEntryPublicCategoryIds.contains(
							assetPublicCategoryId)) {

						updatedAssetEntryPublicCategoryIds.remove(
							assetPublicCategoryId);

						for (Locale locale : locales) {
							Map<Locale, String>
								assetVocabularyCategoryLocaleTitleMap =
									assetVocabularyCategoryIdLocaleTitleMap.get(
										assetPublicCategoryId);

							String assetVocabularyCategoryTitle =
								assetVocabularyCategoryLocaleTitleMap.get(
									locale);

							List<String> assetEntryPublicCategoryTitles =
								assetEntryLocalePublicCategoryTitlesMap.get(
									locale);

							assetEntryPublicCategoryTitles.remove(
								assetVocabularyCategoryTitle);
						}

						reindex = true;
					}
				}
			}

			if (reindex) {
				Stream<Long> stream = assetEntryPublicCategoryIds.stream();

				List<String> updatedAssetPublicCategoryIds = stream.map(
					assetCategoryId -> String.valueOf(assetCategoryId)
				).collect(
					Collectors.toList()
				);

				DocumentBuilder documentBuilder =
					_documentBuilderFactory.builder(
					).setStrings(
						Field.ASSET_PUBLIC_CATEGORY_IDS,
						updatedAssetPublicCategoryIds.toArray(new String[0])
					);

				for (Locale locale : locales) {
					List<String> publicCategoryTitles =
						assetEntryLocalePublicCategoryTitlesMap.get(locale);

					documentBuilder.setStrings(
						localePublicCategoryTitleFieldMap.get(locale),
						publicCategoryTitles.toArray(new String[0]));
				}

				UpdateDocumentRequest updateDocumentRequest =
					new UpdateDocumentRequest(
						indexName, document.getString(Field.UID),
						documentBuilder.build());

				updateDocumentRequest.setRefresh(true);

				_searchEngineAdapter.execute(updateDocumentRequest);
			}
		}
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetEntryAssetCategoryRelLocalService
		_assetEntryAssetCategoryRelLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private DocumentBuilderFactory _documentBuilderFactory;

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	private Localization _localization;

	@Reference
	private Queries _queries;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}