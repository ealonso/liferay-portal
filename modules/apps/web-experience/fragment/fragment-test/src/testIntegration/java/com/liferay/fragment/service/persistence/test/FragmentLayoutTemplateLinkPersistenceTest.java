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

package com.liferay.fragment.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.fragment.exception.NoSuchLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentLayoutTemplateLink;
import com.liferay.fragment.service.FragmentLayoutTemplateLinkLocalServiceUtil;
import com.liferay.fragment.service.persistence.FragmentLayoutTemplateLinkPersistence;
import com.liferay.fragment.service.persistence.FragmentLayoutTemplateLinkUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class FragmentLayoutTemplateLinkPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.fragment.service"));

	@Before
	public void setUp() {
		_persistence = FragmentLayoutTemplateLinkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FragmentLayoutTemplateLink> iterator = _fragmentLayoutTemplateLinks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = _persistence.create(pk);

		Assert.assertNotNull(fragmentLayoutTemplateLink);

		Assert.assertEquals(fragmentLayoutTemplateLink.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		_persistence.remove(newFragmentLayoutTemplateLink);

		FragmentLayoutTemplateLink existingFragmentLayoutTemplateLink = _persistence.fetchByPrimaryKey(newFragmentLayoutTemplateLink.getPrimaryKey());

		Assert.assertNull(existingFragmentLayoutTemplateLink);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFragmentLayoutTemplateLink();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = _persistence.create(pk);

		newFragmentLayoutTemplateLink.setGroupId(RandomTestUtil.nextLong());

		newFragmentLayoutTemplateLink.setFragmentEntryId(RandomTestUtil.nextLong());

		newFragmentLayoutTemplateLink.setLayoutPageTemplateEntryId(RandomTestUtil.nextLong());

		_fragmentLayoutTemplateLinks.add(_persistence.update(
				newFragmentLayoutTemplateLink));

		FragmentLayoutTemplateLink existingFragmentLayoutTemplateLink = _persistence.findByPrimaryKey(newFragmentLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId(),
			newFragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId());
		Assert.assertEquals(existingFragmentLayoutTemplateLink.getGroupId(),
			newFragmentLayoutTemplateLink.getGroupId());
		Assert.assertEquals(existingFragmentLayoutTemplateLink.getFragmentEntryId(),
			newFragmentLayoutTemplateLink.getFragmentEntryId());
		Assert.assertEquals(existingFragmentLayoutTemplateLink.getLayoutPageTemplateEntryId(),
			newFragmentLayoutTemplateLink.getLayoutPageTemplateEntryId());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_F() throws Exception {
		_persistence.countByG_F(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_F(0L, 0L);
	}

	@Test
	public void testCountByG_L() throws Exception {
		_persistence.countByG_L(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_L(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		FragmentLayoutTemplateLink existingFragmentLayoutTemplateLink = _persistence.findByPrimaryKey(newFragmentLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentLayoutTemplateLink,
			newFragmentLayoutTemplateLink);
	}

	@Test(expected = NoSuchLayoutTemplateLinkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FragmentLayoutTemplateLink> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FragmentLayoutTemplateLink",
			"fragmentLayoutTemplateLinkId", true, "groupId", true,
			"fragmentEntryId", true, "layoutPageTemplateEntryId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		FragmentLayoutTemplateLink existingFragmentLayoutTemplateLink = _persistence.fetchByPrimaryKey(newFragmentLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentLayoutTemplateLink,
			newFragmentLayoutTemplateLink);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentLayoutTemplateLink missingFragmentLayoutTemplateLink = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFragmentLayoutTemplateLink);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink1 = addFragmentLayoutTemplateLink();
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink2 = addFragmentLayoutTemplateLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentLayoutTemplateLink1.getPrimaryKey());
		primaryKeys.add(newFragmentLayoutTemplateLink2.getPrimaryKey());

		Map<Serializable, FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fragmentLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentLayoutTemplateLink1,
			fragmentLayoutTemplateLinks.get(
				newFragmentLayoutTemplateLink1.getPrimaryKey()));
		Assert.assertEquals(newFragmentLayoutTemplateLink2,
			fragmentLayoutTemplateLinks.get(
				newFragmentLayoutTemplateLink2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fragmentLayoutTemplateLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentLayoutTemplateLink.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fragmentLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentLayoutTemplateLink,
			fragmentLayoutTemplateLinks.get(
				newFragmentLayoutTemplateLink.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fragmentLayoutTemplateLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentLayoutTemplateLink.getPrimaryKey());

		Map<Serializable, FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fragmentLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentLayoutTemplateLink,
			fragmentLayoutTemplateLinks.get(
				newFragmentLayoutTemplateLink.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FragmentLayoutTemplateLinkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FragmentLayoutTemplateLink>() {
				@Override
				public void performAction(
					FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
					Assert.assertNotNull(fragmentLayoutTemplateLink);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"fragmentLayoutTemplateLinkId",
				newFragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId()));

		List<FragmentLayoutTemplateLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FragmentLayoutTemplateLink existingFragmentLayoutTemplateLink = result.get(0);

		Assert.assertEquals(existingFragmentLayoutTemplateLink,
			newFragmentLayoutTemplateLink);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"fragmentLayoutTemplateLinkId", RandomTestUtil.nextLong()));

		List<FragmentLayoutTemplateLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FragmentLayoutTemplateLink newFragmentLayoutTemplateLink = addFragmentLayoutTemplateLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fragmentLayoutTemplateLinkId"));

		Object newFragmentLayoutTemplateLinkId = newFragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"fragmentLayoutTemplateLinkId",
				new Object[] { newFragmentLayoutTemplateLinkId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFragmentLayoutTemplateLinkId = result.get(0);

		Assert.assertEquals(existingFragmentLayoutTemplateLinkId,
			newFragmentLayoutTemplateLinkId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fragmentLayoutTemplateLinkId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"fragmentLayoutTemplateLinkId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FragmentLayoutTemplateLink addFragmentLayoutTemplateLink()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentLayoutTemplateLink fragmentLayoutTemplateLink = _persistence.create(pk);

		fragmentLayoutTemplateLink.setGroupId(RandomTestUtil.nextLong());

		fragmentLayoutTemplateLink.setFragmentEntryId(RandomTestUtil.nextLong());

		fragmentLayoutTemplateLink.setLayoutPageTemplateEntryId(RandomTestUtil.nextLong());

		_fragmentLayoutTemplateLinks.add(_persistence.update(
				fragmentLayoutTemplateLink));

		return fragmentLayoutTemplateLink;
	}

	private List<FragmentLayoutTemplateLink> _fragmentLayoutTemplateLinks = new ArrayList<FragmentLayoutTemplateLink>();
	private FragmentLayoutTemplateLinkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}