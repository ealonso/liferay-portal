/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.analytics.reports.journal.internal.info.item;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.comparator.ArticleVersionComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author David Arques
 */
@RunWith(MockitoJUnitRunner.class)
public class JournalArticleAnalyticsReportsInfoItemTest {

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			_journalArticleAnalyticsReportsInfoItem,
			"_journalArticleLocalService", _journalArticleLocalService);

		ReflectionTestUtil.setFieldValue(
			_journalArticleAnalyticsReportsInfoItem, "_userLocalService",
			_userLocalService);
	}

	@Test
	public void testGetAuthorNameWithExistingUser() {
		String articleId = RandomTestUtil.randomString();
		long groupId = RandomTestUtil.randomLong();
		long userId = RandomTestUtil.randomLong();

		JournalArticle journalArticle = _createJournalArticle(
			articleId, groupId, userId);

		Mockito.when(
			_journalArticleLocalService.getArticles(
				Matchers.eq(groupId), Matchers.eq(articleId), Matchers.eq(0),
				Matchers.eq(1), Mockito.isA(ArticleVersionComparator.class))
		).thenReturn(
			Collections.singletonList(journalArticle)
		);

		User user = Mockito.mock(User.class);

		Mockito.doReturn(
			RandomTestUtil.randomString()
		).when(
			user
		).getFullName();

		Mockito.doReturn(
			user
		).when(
			_userLocalService
		).fetchUser(
			userId
		);

		Assert.assertEquals(
			user.getFullName(),
			_journalArticleAnalyticsReportsInfoItem.getAuthorName(
				journalArticle));
	}

	@Test
	public void testGetAuthorNameWithNonexistingUser() {
		String articleId = RandomTestUtil.randomString();
		long groupId = RandomTestUtil.randomLong();
		long userId = RandomTestUtil.randomLong();

		JournalArticle journalArticle = _createJournalArticle(
			articleId, groupId, userId);

		Mockito.when(
			_journalArticleLocalService.getArticles(
				Matchers.eq(groupId), Matchers.eq(articleId), Matchers.eq(0),
				Matchers.eq(1), Mockito.isA(ArticleVersionComparator.class))
		).thenReturn(
			Collections.singletonList(journalArticle)
		);

		Mockito.doReturn(
			null
		).when(
			_userLocalService
		).fetchUser(
			userId
		);

		Assert.assertEquals(
			StringPool.BLANK,
			_journalArticleAnalyticsReportsInfoItem.getAuthorName(
				journalArticle));
	}

	@Test
	public void testGetPublishDate() {
		JournalArticle journalArticle = Mockito.mock(JournalArticle.class);

		Date displayDate = RandomTestUtil.nextDate();

		Mockito.doReturn(
			displayDate
		).when(
			journalArticle
		).getDisplayDate();

		Assert.assertEquals(
			journalArticle.getDisplayDate(),
			_journalArticleAnalyticsReportsInfoItem.getPublishDate(
				journalArticle));
	}

	@Test
	public void testGetTitle() {
		JournalArticle journalArticle = Mockito.mock(JournalArticle.class);

		String title = RandomTestUtil.randomString();

		Locale locale = LocaleUtil.getDefault();

		Mockito.doReturn(
			title
		).when(
			journalArticle
		).getTitle(
			locale
		);

		Assert.assertEquals(
			journalArticle.getTitle(locale),
			_journalArticleAnalyticsReportsInfoItem.getTitle(
				journalArticle, locale));
	}

	private JournalArticle _createJournalArticle(
		String articleId, long groupId, long userId) {

		JournalArticle journalArticle = Mockito.mock(JournalArticle.class);

		Mockito.doReturn(
			articleId
		).when(
			journalArticle
		).getArticleId();

		Mockito.doReturn(
			groupId
		).when(
			journalArticle
		).getGroupId();

		Mockito.doReturn(
			userId
		).when(
			journalArticle
		).getUserId();

		return journalArticle;
	}

	private final JournalArticleAnalyticsReportsInfoItem
		_journalArticleAnalyticsReportsInfoItem =
			new JournalArticleAnalyticsReportsInfoItem();

	@Mock
	private JournalArticleLocalService _journalArticleLocalService;

	@Mock
	private UserLocalService _userLocalService;

}