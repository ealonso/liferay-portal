/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.display.context;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.TabsItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.TabsItemListBuilder;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.journal.web.internal.servlet.taglib.util.JournalArticleActionDropdownItemsProvider;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.trash.TrashHelper;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Clara Izquierdo
 */
public class JournalVersionTabDisplayContext {

	public JournalVersionTabDisplayContext(
		AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider,
		JournalArticle journalArticle,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		TrashHelper trashHelper) {

		_assetDisplayPageFriendlyURLProvider =
			assetDisplayPageFriendlyURLProvider;

		_article = journalArticle;

		_httpServletRequest = PortalUtil.getHttpServletRequest(
			liferayPortletRequest);

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_trashHelper = trashHelper;
	}

	public List<JournalArticle> getAllJournalArticlesVersions() {
		return JournalArticleServiceUtil.getArticlesByArticleId(
			_article.getGroupId(), _article.getArticleId(), QueryUtil.ALL_POS,
			10, new ArticleVersionComparator());
	}

	public List<DropdownItem> getArticleHistoryActionDropdownItems(
			JournalArticle article)
		throws Exception {

		JournalArticleActionDropdownItemsProvider
			articleActionDropdownItemsProvider =
				new JournalArticleActionDropdownItemsProvider(
					article, _liferayPortletRequest, _liferayPortletResponse,
					_assetDisplayPageFriendlyURLProvider, _trashHelper);

		return articleActionDropdownItemsProvider.
			getArticleVersionTabActionDropdownItems();
	}

	public List<TabsItem> getInfoPanelTabsItems() {
		return TabsItemListBuilder.add(
			tabsItem -> {
				tabsItem.setActive(true);
				tabsItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "details"));
			}
		).add(
			tabsItem -> tabsItem.setLabel(
				LanguageUtil.get(_httpServletRequest, "versions"))
		).build();
	}

	public int getTotalJournalArticlesVersions() {
		return JournalArticleServiceUtil.getArticlesCountByArticleId(
			_article.getGroupId(), _article.getArticleId());
	}

	private final JournalArticle _article;
	private final AssetDisplayPageFriendlyURLProvider
		_assetDisplayPageFriendlyURLProvider;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final TrashHelper _trashHelper;

}