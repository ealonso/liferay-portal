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

package com.liferay.journal.internal.search;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleResource;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.journal.util.JournalContent;
import com.liferay.journal.util.JournalConverter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.search.batch.BatchIndexingHelper;
import com.liferay.portal.search.index.IndexStatusManager;
import com.liferay.portal.search.model.uid.UIDFactory;
import com.liferay.trash.TrashHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Harry Mark
 * @author Bruno Farache
 * @author Raymond Augé
 * @author Hugo Huijser
 * @author Tibor Lipusz
 */
@Component(immediate = true, service = Indexer.class)
public class JournalArticleIndexer extends BaseIndexer<JournalArticle> {

	public static final String CLASS_NAME = JournalArticle.class.getName();

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, String entryClassName,
			long entryClassPK, String actionId)
		throws Exception {

		return _journalArticleModelResourcePermission.contains(
			permissionChecker, entryClassPK, ActionKeys.VIEW);
	}

	@Override
	public boolean isVisible(long classPK, int status) throws Exception {
		List<JournalArticle> articles =
			_journalArticleLocalService.getArticlesByResourcePrimKey(classPK);

		for (JournalArticle article : articles) {
			if (isVisible(article.getStatus(), status)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {
	}

	@Override
	protected void doDelete(JournalArticle journalArticle) throws Exception {
		_deleteDocument(journalArticle);

		_reindexEveryVersionOfResourcePrimKey(
			journalArticle.getResourcePrimKey());
	}

	@Override
	protected Document doGetDocument(JournalArticle journalArticle)
		throws Exception {

		return null;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("display-date")) {
			return Field.DISPLAY_DATE;
		}
		else if (orderByCol.equals("id")) {
			return Field.ENTRY_CLASS_PK;
		}
		else if (orderByCol.equals("modified-date")) {
			return Field.MODIFIED_DATE;
		}
		else if (orderByCol.equals("title")) {
			return Field.TITLE;
		}

		return orderByCol;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Locale defaultLocale = LocaleUtil.fromLanguageId(
			document.get("defaultLanguageId"));

		Locale snippetLocale = getSnippetLocale(document, locale);

		String localizedTitleName = Field.getLocalizedName(locale, Field.TITLE);

		if ((snippetLocale == null) &&
			(document.getField(localizedTitleName) == null)) {

			snippetLocale = defaultLocale;
		}
		else {
			snippetLocale = locale;
		}

		String title = document.get(
			snippetLocale, Field.SNIPPET + StringPool.UNDERLINE + Field.TITLE,
			Field.TITLE);

		if (Validator.isBlank(title) && !snippetLocale.equals(defaultLocale)) {
			title = document.get(
				defaultLocale,
				Field.SNIPPET + StringPool.UNDERLINE + Field.TITLE,
				Field.TITLE);
		}

		String content = _getDDMContentSummary(
			document, snippetLocale, portletRequest, portletResponse);

		if (Validator.isBlank(content) &&
			!snippetLocale.equals(defaultLocale)) {

			content = _getDDMContentSummary(
				document, defaultLocale, portletRequest, portletResponse);
		}

		Summary summary = new Summary(snippetLocale, title, content);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(JournalArticle article) throws Exception {
		if (_portal.getClassNameId(DDMStructure.class) ==
				article.getClassNameId()) {

			_deleteDocument(article);

			return;
		}

		_reindexEveryVersionOfResourcePrimKey(article.getResourcePrimKey());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		JournalArticle journalArticle =
			_journalArticleLocalService.fetchJournalArticle(classPK);

		if (journalArticle != null) {
			_reindexEveryVersionOfResourcePrimKey(
				journalArticle.getResourcePrimKey());

			return;
		}

		long resourcePrimKey = classPK;

		_reindexEveryVersionOfResourcePrimKey(resourcePrimKey);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		_reindexArticles(companyId);
	}

	protected boolean isIndexAllArticleVersions() {
		JournalServiceConfiguration journalServiceConfiguration = null;

		try {
			journalServiceConfiguration =
				_configurationProvider.getCompanyConfiguration(
					JournalServiceConfiguration.class,
					CompanyThreadLocal.getCompanyId());

			return journalServiceConfiguration.indexAllArticleVersionsEnabled();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {

		_ddmStructureLocalService = ddmStructureLocalService;
	}

	@Reference(unbind = "-")
	protected void setFieldsToDDMFormValuesConverter(
		FieldsToDDMFormValuesConverter fieldsToDDMFormValuesConverter) {

		_fieldsToDDMFormValuesConverter = fieldsToDDMFormValuesConverter;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleLocalService(
		JournalArticleLocalService journalArticleLocalService) {

		_journalArticleLocalService = journalArticleLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleResourceLocalService(
		JournalArticleResourceLocalService journalArticleResourceLocalService) {

		_journalArticleResourceLocalService =
			journalArticleResourceLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalContent(JournalContent journalContent) {
		_journalContent = journalContent;
	}

	@Reference(unbind = "-")
	protected void setJournalConverter(JournalConverter journalConverter) {
		_journalConverter = journalConverter;
	}

	@Reference
	protected UIDFactory uidFactory;

	private void _deleteDocument(JournalArticle article) throws Exception {
		if ((article.getCtCollectionId() == 0) &&
			!CTCollectionThreadLocal.isProductionMode()) {

			return;
		}

		deleteDocument(
			article.getCompanyId(), "UID=" + uidFactory.getUID(article));
	}

	private JournalArticle _fetchLatestIndexableArticleVersion(
		long resourcePrimKey) {

		JournalArticle latestIndexableArticle =
			_journalArticleLocalService.fetchLatestArticle(
				resourcePrimKey,
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH
				});

		if (latestIndexableArticle == null) {
			latestIndexableArticle =
				_journalArticleLocalService.fetchLatestArticle(resourcePrimKey);
		}

		return latestIndexableArticle;
	}

	private String _getDDMContentSummary(
		Document document, Locale snippetLocale, PortletRequest portletRequest,
		PortletResponse portletResponse) {

		String content = StringPool.BLANK;

		if ((portletRequest == null) || (portletResponse == null)) {
			return content;
		}

		try {
			content = document.get(
				snippetLocale,
				Field.SNIPPET + StringPool.UNDERLINE + Field.DESCRIPTION,
				Field.DESCRIPTION);

			if (!Validator.isBlank(content)) {
				return content;
			}

			content = document.get(
				snippetLocale,
				Field.SNIPPET + StringPool.UNDERLINE + Field.CONTENT,
				Field.CONTENT);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		return content;
	}

	private void _reindexArticles(long companyId) throws Exception {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery;

		if (isIndexAllArticleVersions()) {
			indexableActionableDynamicQuery =
				_journalArticleLocalService.
					getIndexableActionableDynamicQuery();

			indexableActionableDynamicQuery.setAddCriteriaMethod(
				dynamicQuery -> {
					Property property = PropertyFactoryUtil.forName(
						"classNameId");

					dynamicQuery.add(
						property.ne(
							_portal.getClassNameId(DDMStructure.class)));
				});
			indexableActionableDynamicQuery.setInterval(
				_batchIndexingHelper.getBulkSize(
					JournalArticle.class.getName()));
			indexableActionableDynamicQuery.setPerformActionMethod(
				(JournalArticle article) -> {
					try {
						indexableActionableDynamicQuery.addDocuments(
							getDocument(article));
					}
					catch (PortalException portalException) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index journal article " +
									article.getId(),
								portalException);
						}
					}
				});
		}
		else {
			indexableActionableDynamicQuery =
				_journalArticleResourceLocalService.
					getIndexableActionableDynamicQuery();

			indexableActionableDynamicQuery.setInterval(
				_batchIndexingHelper.getBulkSize(
					JournalArticleResource.class.getName()));

			indexableActionableDynamicQuery.setPerformActionMethod(
				(JournalArticleResource articleResource) -> {
					JournalArticle latestIndexableArticle =
						_fetchLatestIndexableArticleVersion(
							articleResource.getResourcePrimKey());

					if (latestIndexableArticle == null) {
						return;
					}

					try {
						indexableActionableDynamicQuery.addDocuments(
							getDocument(latestIndexableArticle));
					}
					catch (PortalException portalException) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"Unable to index journal article " +
									latestIndexableArticle.getId(),
								portalException);
						}
					}
				});
		}

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private void _reindexEveryVersionOfResourcePrimKey(long resourcePrimKey)
		throws Exception {

		List<JournalArticle> journalArticles =
			_journalArticleLocalService.getArticlesByResourcePrimKey(
				resourcePrimKey);

		if (ListUtil.isEmpty(journalArticles)) {
			return;
		}

		JournalArticle article = journalArticles.get(0);

		if (_portal.getClassNameId(DDMStructure.class) ==
				article.getClassNameId()) {

			_deleteDocument(article);

			return;
		}

		if (isIndexAllArticleVersions()) {
			List<Document> documents = new ArrayList<>(journalArticles.size());

			if (CTCollectionThreadLocal.isProductionMode()) {
				for (JournalArticle journalArticle : journalArticles) {
					documents.add(getDocument(journalArticle));
				}
			}
			else {
				for (JournalArticle journalArticle : journalArticles) {
					if (journalArticle.getCtCollectionId() != 0) {
						documents.add(getDocument(journalArticle));
					}
				}
			}

			_indexWriterHelper.updateDocuments(
				getSearchEngineId(), article.getCompanyId(), documents,
				isCommitImmediately());
		}
		else {
			JournalArticle latestIndexableArticle =
				_fetchLatestIndexableArticleVersion(resourcePrimKey);

			for (JournalArticle journalArticle : journalArticles) {
				if (journalArticle.getId() == latestIndexableArticle.getId()) {
					_indexWriterHelper.updateDocument(
						getSearchEngineId(), article.getCompanyId(),
						getDocument(journalArticle), isCommitImmediately());
				}
				else {
					_deleteDocument(journalArticle);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleIndexer.class);

	@Reference
	private BatchIndexingHelper _batchIndexingHelper;

	private ConfigurationProvider _configurationProvider;
	private DDMStructureLocalService _ddmStructureLocalService;
	private FieldsToDDMFormValuesConverter _fieldsToDDMFormValuesConverter;

	@Reference
	private Html _html;

	@Reference
	private IndexerRegistry _indexerRegistry;

	@Reference
	private IndexStatusManager _indexStatusManager;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

	private JournalArticleLocalService _journalArticleLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalArticle)"
	)
	private ModelResourcePermission<JournalArticle>
		_journalArticleModelResourcePermission;

	private JournalArticleResourceLocalService
		_journalArticleResourceLocalService;
	private JournalContent _journalContent;
	private JournalConverter _journalConverter;

	@Reference
	private Portal _portal;

	@Reference
	private TrashHelper _trashHelper;

}