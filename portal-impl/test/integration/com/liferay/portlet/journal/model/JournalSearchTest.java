/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.journal.model;

import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portlet.documentlibrary.util.JournalSearcher;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.util.JournalTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.util.Locale;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class JournalSearchTest {

	@Before
	public void setUp() throws Exception {
		FinderCacheUtil.clearCache();
	}

	@Test
	public void testFindJournalArticlesCount() throws Exception {
		Group group = GroupTestUtil.addGroup();

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			group.getGroupId());

		int articlesCount = searchJournalCount(
			group.getGroupId(), searchContext);

		Assert.assertEquals(0, articlesCount);
	}

	@Test
	public void testFindJournalArticlesByFolder() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setFolderIds(new long[]{folder.getFolderId()});

		Document[] documents = searchJournal(searchContext);

		Assert.assertEquals(1, documents.length);
	}

	@Test
	public void testFindJournalArticlesByUrlTitle() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);

		article.setUrlTitle("testUrlTitle");
		
		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setAttribute("urlTitle", "testUrlTitle");

		Document[] documents = searchJournal(searchContext);

		Assert.assertEquals(1, documents.length);
	}

	@Test
	public void testFindJournalArticleByArticleId() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setAttribute("articleId", article.getArticleId());

		Document[] documents = searchJournal(searchContext);

		Assert.assertEquals(1, documents.length);
	}

	@Test
	public void testFindJournalArticlesByTitle() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setAttribute(
			"title", article.getTitle(LocaleUtil.getDefault()));

		Document[] documents = searchJournal(searchContext);

		Assert.assertEquals(1, documents.length);
	}

	@Test
	public void testFindExpiredJournalArticles() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);
		article.setStatus(WorkflowConstants.STATUS_EXPIRED);

		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setAttribute("articleId", article.getArticleId());

		Document[] documents = searchJournal(searchContext);

		Assert.assertTrue(article.isExpired());
		Assert.assertEquals(0, documents.length);
	}

	@Test
	public void testFindScheduledArticles() throws Exception {
		Group group = GroupTestUtil.addGroup();
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), ServiceTestUtil.randomString());

		JournalArticle article = addJournalArticle(group, folder);
		article.setStatus(WorkflowConstants.STATUS_SCHEDULED);

		JournalArticleLocalServiceUtil.updateJournalArticle(article);

		SearchContext searchContext = ServiceTestUtil.getSearchContext(
			article.getGroupId());

		searchContext.setAttribute("articleId", article.getArticleId());

		Document[] documents = searchJournal(searchContext);

		Assert.assertTrue(article.isScheduled());
		Assert.assertEquals(0, documents.length);
	}

	protected JournalArticle addJournalArticle(
			Group group, JournalFolder folder)
		throws Exception {

		Locale locale = LocaleUtil.getDefault();

		String xmlContent = getArticleContent(ServiceTestUtil.randomString(50));

		return JournalTestUtil.addArticle(
			group.getGroupId(), folder.getGroupId(), "TestTitle", xmlContent,
			locale, true, true);
    }

	protected int searchJournalCount(
			long groupId, SearchContext searchContext)
		throws Exception {

		Indexer indexer = JournalSearcher.getInstance();

		searchContext.setGroupIds(new long[]{groupId});

		Hits results = indexer.search(searchContext);

		return results.getLength();
	}

	protected Document[] searchJournal(SearchContext searchContext)
		throws Exception {

		Indexer indexer = JournalSearcher.getInstance();

		Hits results = indexer.search(searchContext);

		return results.getDocs();
	}

	protected static String getArticleContent(String content) {
		StringBundler sb = new StringBundler();

	    String localeId = LocaleUtil.getDefault().toString();

		sb.append("<?xml version=\"1.0\"?><root available-locales=");
		sb.append("\"" + localeId + "\" ");
		sb.append("default-locale=\"" + localeId + "\">");
		sb.append("<static-content language-id=\"" + localeId + "\">");
		sb.append("<![CDATA[<p>");
		sb.append(content);
		sb.append("</p>]]>");
		sb.append("</static-content></root>");

		return sb.toString();
	}

}
