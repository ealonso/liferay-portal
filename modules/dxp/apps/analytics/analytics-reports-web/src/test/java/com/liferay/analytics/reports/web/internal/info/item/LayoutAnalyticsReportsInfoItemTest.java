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

package com.liferay.analytics.reports.web.internal.info.item;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Date;
import java.util.Locale;

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
public class LayoutAnalyticsReportsInfoItemTest {

	@Before
	public void setUp() {
		ReflectionTestUtil.setFieldValue(
			_layoutAnalyticsReportsInfoItem, "_userLocalService",
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

		Layout layout = Mockito.mock(Layout.class);

		Mockito.doReturn(
			userId
		).when(
			layout
		).getUserId();

		Assert.assertEquals(
			user.getFullName(),
			_layoutAnalyticsReportsInfoItem.getAuthorName(layout));
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

		Layout layout = Mockito.mock(Layout.class);

		Mockito.doReturn(
			userId
		).when(
			layout
		).getUserId();

		Assert.assertEquals(
			StringPool.BLANK,
			_layoutAnalyticsReportsInfoItem.getAuthorName(layout));
	}

	@Test
	public void testGetPublishDate() {
		Layout layout = Mockito.mock(Layout.class);

		Date displayDate = RandomTestUtil.nextDate();

		Mockito.doReturn(
			displayDate
		).when(
			layout
		).getPublishDate();

		Assert.assertEquals(
			layout.getPublishDate(),
			_layoutAnalyticsReportsInfoItem.getPublishDate(layout));
	}

	@Test
	public void testGetTitle() {
		Layout layout = Mockito.mock(Layout.class);

		String title = RandomTestUtil.randomString();

		Locale locale = LocaleUtil.getDefault();

		Mockito.doReturn(
			title
		).when(
			layout
		).getTitle(
			locale
		);

		Assert.assertEquals(
			layout.getTitle(locale),
			_layoutAnalyticsReportsInfoItem.getTitle(layout, locale));
	}

	private final LayoutAnalyticsReportsInfoItem
		_layoutAnalyticsReportsInfoItem = new LayoutAnalyticsReportsInfoItem();

	@Mock
	private UserLocalService _userLocalService;

}