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

package com.liferay.analytics.reports.blogs.internal.info.item;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author David Arques
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogsAnalyticsReportsInfoItemTest {

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			_blogsAnalyticsReportsInfoItem, "_userLocalService",
			_userLocalService);
	}

	@Test
	public void testGetAuthorNameWithExistingUser() {
		User user = Mockito.mock(User.class);

		Mockito.doReturn(
			RandomTestUtil.randomString()
		).when(
			user
		).getFullName();

		long userId = RandomTestUtil.randomLong();

		Mockito.doReturn(
			user
		).when(
			_userLocalService
		).fetchUser(
			userId
		);

		BlogsEntry blogsEntry = Mockito.mock(BlogsEntry.class);

		Mockito.doReturn(
			userId
		).when(
			blogsEntry
		).getUserId();

		Assert.assertEquals(
			user.getFullName(),
			_blogsAnalyticsReportsInfoItem.getAuthorName(blogsEntry));
	}

	@Test
	public void testGetAuthorNameWithNonexistingUser() {
		long userId = RandomTestUtil.randomLong();

		Mockito.doReturn(
			null
		).when(
			_userLocalService
		).fetchUser(
			userId
		);

		BlogsEntry blogsEntry = Mockito.mock(BlogsEntry.class);

		Mockito.doReturn(
			userId
		).when(
			blogsEntry
		).getUserId();

		Assert.assertEquals(
			StringPool.BLANK,
			_blogsAnalyticsReportsInfoItem.getAuthorName(blogsEntry));
	}

	@Test
	public void testGetPublishDate() {
		BlogsEntry blogsEntry = Mockito.mock(BlogsEntry.class);

		Date displayDate = RandomTestUtil.nextDate();

		Mockito.doReturn(
			displayDate
		).when(
			blogsEntry
		).getDisplayDate();

		Assert.assertEquals(
			blogsEntry.getDisplayDate(),
			_blogsAnalyticsReportsInfoItem.getPublishDate(blogsEntry));
	}

	@Test
	public void testGetTitle() {
		BlogsEntry blogsEntry = Mockito.mock(BlogsEntry.class);

		String title = RandomTestUtil.randomString();

		Mockito.doReturn(
			title
		).when(
			blogsEntry
		).getTitle();

		Assert.assertEquals(
			blogsEntry.getTitle(),
			_blogsAnalyticsReportsInfoItem.getTitle(
				blogsEntry, LocaleUtil.getDefault()));
	}

	private final BlogsAnalyticsReportsInfoItem _blogsAnalyticsReportsInfoItem =
		new BlogsAnalyticsReportsInfoItem();

	@Mock
	private UserLocalService _userLocalService;

}