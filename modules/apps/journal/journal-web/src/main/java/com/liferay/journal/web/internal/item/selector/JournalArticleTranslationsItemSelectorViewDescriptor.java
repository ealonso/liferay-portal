
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

package com.liferay.journal.web.internal.item.selector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.TableItemView;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.web.internal.display.context.JournalDisplayContext;
import com.liferay.journal.web.internal.portlet.action.ActionUtil;
import com.liferay.journal.web.internal.util.JournalArticleTranslation;
import com.liferay.journal.web.internal.util.JournalArticleTranslationRowChecker;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Barbara Cabrera
 */
public class JournalArticleTranslationsItemSelectorViewDescriptor
	implements ItemSelectorViewDescriptor<JournalArticleTranslation> {

	public JournalArticleTranslationsItemSelectorViewDescriptor(
		JournalArticleTranslationsItemSelectorCriterion
			journalArticleTranslationsItemSelectorCriterion,
		HttpServletRequest httpServletRequest, PortletURL portletURL){

		ThemeDisplay themeDisplay =
			(ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		_article = JournalArticleLocalServiceUtil.fetchLatestArticle(themeDisplay.getScopeGroupId(), journalArticleTranslationsItemSelectorCriterion.getArticleId(),
			WorkflowConstants.STATUS_ANY);
		_httpServletRequest = httpServletRequest;
		_journalArticleTranslationsItemSelectorCriterion = journalArticleTranslationsItemSelectorCriterion;
		_portletRequest = (PortletRequest)_httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
		_portletResponse = (PortletResponse) _httpServletRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);
		_portletURL = portletURL;

	}

	public TableItemView getTableItemView(JournalArticleTranslation journalArticleTranslation) {
		return new JournalArticleTranslationsItemView(journalArticleTranslation);
	}

	@Override
	public String getDefaultDisplayStyle() {
		return "list";
	}

	public String[] getDisplayViews() {
		return new String[] {"list"};
	}

	@Override
	public boolean isMultipleSelection() {
		return true;
	}

	@Override
	public ItemDescriptor getItemDescriptor(
		JournalArticleTranslation journalArticleTranslation) {

		return new JournalArticleTranslationsItemDescriptor(
			journalArticleTranslation, _httpServletRequest);
	}

	@Override
	public ItemSelectorReturnType getItemSelectorReturnType() {
		return new UUIDItemSelectorReturnType();
	}

	@Override
	public SearchContainer<JournalArticleTranslation> getSearchContainer()
		throws PortalException {

		if (_articleTranslationsSearchContainer != null) {
			return _articleTranslationsSearchContainer;
		}

		SearchContainer<JournalArticleTranslation>
			articleTranslationsSearchContainer = new SearchContainer<>(
			_portletRequest, _portletURL, null, null);

		articleTranslationsSearchContainer.setId("articleTranslations");

		List<JournalArticleTranslation> articleTranslations = new ArrayList<>();

		JournalArticle article = getArticle();

		String keywords = getKeywords();

		for (String languageId : article.getAvailableLanguageIds()) {
			JournalArticleTranslation articleTranslation =
				new JournalArticleTranslation(
					StringUtil.equalsIgnoreCase(
						article.getDefaultLanguageId(), languageId),
					LocaleUtil.fromLanguageId(languageId));

			if (Validator.isNotNull(keywords) &&
				!StringUtil.containsIgnoreCase(
					LocaleUtil.getLongDisplayName(
						articleTranslation.getLocale(), Collections.emptySet()),
					keywords, StringPool.BLANK)) {

				continue;
			}

			articleTranslations.add(articleTranslation);
		}

		articleTranslationsSearchContainer.setResultsAndTotal(
			articleTranslations);
		articleTranslationsSearchContainer.setRowChecker(
			new JournalArticleTranslationRowChecker(_portletResponse));

		_articleTranslationsSearchContainer =
			articleTranslationsSearchContainer;

		return _articleTranslationsSearchContainer;
	}

	public JournalArticle getArticle() throws PortalException {
		if (_article != null) {
			return _article;
		}

		_article = ActionUtil.getArticle(_httpServletRequest);

		return _article;
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isShowBreadcrumb() {
		return false;
	}

	private JournalArticle _article;

	private JournalArticleTranslationsItemSelectorCriterion _journalArticleTranslationsItemSelectorCriterion;
	private HttpServletRequest _httpServletRequest;
	private PortletRequest _portletRequest;
	private PortletResponse _portletResponse;
	private PortletURL _portletURL;
	private SearchContainer<JournalArticleTranslation>
		_articleTranslationsSearchContainer;
	private String _keywords;

}

