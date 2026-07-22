/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.internal.search;

import com.liferay.portal.kernel.dao.db.DBManager;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Eudaldo Alonso
 */
public class UserBulkReindexerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		_dbManager = Mockito.mock(DBManager.class);

		Mockito.when(
			_dbManager.getDBMaxParameters()
		).thenReturn(
			65400
		);

		DBManagerUtil.setDBManager(_dbManager);
	}

	@After
	public void tearDown() {
		DBManagerUtil.setDBManager(null);
	}

	@Test
	public void testReindexByDBInMaxParameters() {
		_assertReindex(3, 1, 1);
		_assertReindex(3, 3, 1);
		_assertReindex(3, 4, 2);
		_assertReindex(3, 10, 4);
	}

	private void _assertReindex(int dbInMaxParameters, int size, int expected) {
		Mockito.when(
			_dbManager.getDBInMaxParameters()
		).thenReturn(
			dbInMaxParameters
		);

		UserBulkReindexer userBulkReindexer = new UserBulkReindexer();

		UserLocalService userLocalService = Mockito.mock(
			UserLocalService.class);

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			Mockito.mock(IndexableActionableDynamicQuery.class);

		Mockito.when(
			userLocalService.getIndexableActionableDynamicQuery()
		).thenReturn(
			indexableActionableDynamicQuery
		);

		ReflectionTestUtil.setFieldValue(
			userBulkReindexer, "userLocalService", userLocalService);

		userBulkReindexer.reindex(
			RandomTestUtil.randomLong(), _createClassPKs(size));

		Mockito.verify(
			indexableActionableDynamicQuery, Mockito.times(expected)
		).performActions();
	}

	private List<Long> _createClassPKs(int size) {
		List<Long> classPKs = new ArrayList<>(size);

		for (long classPK = 1; classPK <= size; classPK++) {
			classPKs.add(classPK);
		}

		return classPKs;
	}

	private DBManager _dbManager;

}