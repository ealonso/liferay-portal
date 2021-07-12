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

package com.liferay.portal.search.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchPermissionChecker;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.UserBag;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.configuration.SearchPermissionCheckerConfiguration;
import com.liferay.portal.search.spi.model.permission.SearchPermissionFilterContributor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author André de Oliveira
 */
public class SearchPermissionCheckerImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		Mockito.doReturn(
			_indexer
		).when(
			_indexerRegistry
		).getIndexer(
			Mockito.anyString()
		);

		_searchPermissionChecker = createSearchPermissionChecker();
	}

	@Test
	public void testNullInput() {
		Assert.assertNull(
			_searchPermissionChecker.getPermissionBooleanFilter(
				0, null, 0, null, null, null));
	}

	@Test
	public void testPermissionFilterMultipleResourcePermissionName()
		throws Exception {

		long userId = RandomTestUtil.randomLong();

		whenIndexerIsPermissionAware(true);
		whenPermissionCheckerGetUser(_user);
		whenPermissionCheckerGetUserBag(_userBag);
		whenUserGetUserId(userId);

		ReflectionTestUtil.getAndSetFieldValue(
			_searchPermissionChecker, "_searchPermissionFilterContributors",
			Arrays.asList(_searchPermissionFilterContributor));

		SearchContext searchContext = new SearchContext();
		String resourceName1 = StringBundler.concat(
			"com.liferay.asset.kernel.model.AssetCategory-",
			"com.liferay.dynamic.data.mapping.model.DDMTemplate");
		String resourceName2 = StringBundler.concat(
			"com.liferay.dynamic.data.mapping.model.DDMTemplate-",
			"com.liferay.journal.model.JournalArticle");

		searchContext.setAttribute(
			"resourcePermissionNames",
			new String[] {resourceName1, resourceName2});

		BooleanFilter booleanFilter = null;

		BooleanFilter permissionBooleanFilter =
			_searchPermissionChecker.getPermissionBooleanFilter(
				0, null, userId, null, booleanFilter, searchContext);

		Assert.assertNotNull(permissionBooleanFilter);

		List<BooleanClause<Filter>> shouldBooleanClauses =
			permissionBooleanFilter.getShouldBooleanClauses();

		Assert.assertEquals(
			shouldBooleanClauses.toString(), 2, shouldBooleanClauses.size());

		Mockito.verify(
			_searchPermissionFilterContributor
		).contribute(
			Matchers.any(BooleanFilter.class), Matchers.eq((long)0),
			Matchers.eq(null), Matchers.eq(userId),
			Matchers.eq(_permissionChecker), Matchers.eq(resourceName1)
		);

		Mockito.verify(
			_searchPermissionFilterContributor
		).contribute(
			Matchers.any(BooleanFilter.class), Matchers.eq((long)0),
			Matchers.eq(null), Matchers.eq(userId),
			Matchers.eq(_permissionChecker), Matchers.eq(resourceName2)
		);

		Mockito.verifyNoMoreInteractions(_searchPermissionFilterContributor);
	}

	@Test
	public void testPermissionFilterSingleResourcePermissionName()
		throws Exception {

		long userId = RandomTestUtil.randomLong();

		whenIndexerIsPermissionAware(true);
		whenPermissionCheckerGetUser(_user);
		whenPermissionCheckerGetUserBag(_userBag);
		whenUserGetUserId(userId);

		ReflectionTestUtil.getAndSetFieldValue(
			_searchPermissionChecker, "_searchPermissionFilterContributors",
			Arrays.asList(_searchPermissionFilterContributor));

		SearchContext searchContext = new SearchContext();
		String resourceName = StringBundler.concat(
			"com.liferay.asset.kernel.model.AssetCategory-",
			"com.liferay.dynamic.data.mapping.model.DDMTemplate");

		searchContext.setAttribute("resourcePermissionName", resourceName);

		BooleanFilter booleanFilter = null;

		BooleanFilter permissionBooleanFilter =
			_searchPermissionChecker.getPermissionBooleanFilter(
				0, null, userId, null, booleanFilter, searchContext);

		Assert.assertNotNull(permissionBooleanFilter);

		List<BooleanClause<Filter>> shouldBooleanClauses =
			permissionBooleanFilter.getShouldBooleanClauses();

		Assert.assertEquals(
			shouldBooleanClauses.toString(), 1, shouldBooleanClauses.size());

		Mockito.verify(
			_searchPermissionFilterContributor
		).contribute(
			permissionBooleanFilter, 0, null, userId, _permissionChecker,
			resourceName
		);

		Mockito.verifyNoMoreInteractions(_searchPermissionFilterContributor);
	}

	@Test
	public void testPermissionFilterTakesOverNullInputFilter()
		throws Exception {

		long userId = RandomTestUtil.randomLong();

		whenIndexerIsPermissionAware(true);
		whenPermissionCheckerGetUser(_user);
		whenPermissionCheckerGetUserBag(_userBag);
		whenUserGetUserId(userId);

		BooleanFilter booleanFilter = null;

		BooleanFilter permissionBooleanFilter =
			_searchPermissionChecker.getPermissionBooleanFilter(
				0, null, userId, null, booleanFilter, new SearchContext());

		Assert.assertNotNull(permissionBooleanFilter);
	}

	protected SearchPermissionCheckerImpl createSearchPermissionChecker() {
		return new SearchPermissionCheckerImpl() {
			{
				indexerRegistry = _indexerRegistry;
				permissionChecker = _permissionChecker;
				resourcePermissionLocalService =
					_resourcePermissionLocalService;
				roleLocalService = _roleLocalService;
				searchPermissionCheckerConfiguration =
					_searchPermissionCheckerConfiguration;
				userLocalService = _userLocalService;
			}
		};
	}

	protected boolean whenIndexerIsPermissionAware(boolean permissionAware) {
		return Mockito.doReturn(
			permissionAware
		).when(
			_indexer
		).isPermissionAware();
	}

	protected User whenPermissionCheckerGetUser(User user) {
		return Mockito.doReturn(
			user
		).when(
			_permissionChecker
		).getUser();
	}

	protected void whenPermissionCheckerGetUserBag(UserBag userBag)
		throws Exception {

		Mockito.doReturn(
			userBag
		).when(
			_permissionChecker
		).getUserBag();
	}

	protected long whenUserGetUserId(long userId) {
		return Mockito.doReturn(
			userId
		).when(
			_user
		).getUserId();
	}

	@Mock
	private Indexer<?> _indexer;

	@Mock
	private IndexerRegistry _indexerRegistry;

	@Mock
	private PermissionChecker _permissionChecker;

	@Mock
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Mock
	private RoleLocalService _roleLocalService;

	private SearchPermissionChecker _searchPermissionChecker;

	@Mock
	private SearchPermissionCheckerConfiguration
		_searchPermissionCheckerConfiguration;

	@Mock
	private SearchPermissionFilterContributor
		_searchPermissionFilterContributor;

	@Mock
	private User _user;

	@Mock
	private UserBag _userBag;

	@Mock
	private UserLocalService _userLocalService;

}