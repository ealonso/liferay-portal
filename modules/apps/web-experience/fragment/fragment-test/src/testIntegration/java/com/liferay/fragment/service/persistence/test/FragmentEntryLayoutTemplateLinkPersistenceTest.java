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

import com.liferay.fragment.exception.NoSuchEntryLayoutTemplateLinkException;
import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;
import com.liferay.fragment.service.FragmentEntryLayoutTemplateLinkLocalServiceUtil;
import com.liferay.fragment.service.persistence.FragmentEntryLayoutTemplateLinkPersistence;
import com.liferay.fragment.service.persistence.FragmentEntryLayoutTemplateLinkUtil;

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
public class FragmentEntryLayoutTemplateLinkPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.fragment.service"));

	@Before
	public void setUp() {
		_persistence = FragmentEntryLayoutTemplateLinkUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FragmentEntryLayoutTemplateLink> iterator = _fragmentEntryLayoutTemplateLinks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = _persistence.create(pk);

		Assert.assertNotNull(fragmentEntryLayoutTemplateLink);

		Assert.assertEquals(fragmentEntryLayoutTemplateLink.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		_persistence.remove(newFragmentEntryLayoutTemplateLink);

		FragmentEntryLayoutTemplateLink existingFragmentEntryLayoutTemplateLink = _persistence.fetchByPrimaryKey(newFragmentEntryLayoutTemplateLink.getPrimaryKey());

		Assert.assertNull(existingFragmentEntryLayoutTemplateLink);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFragmentEntryLayoutTemplateLink();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = _persistence.create(pk);

		newFragmentEntryLayoutTemplateLink.setGroupId(RandomTestUtil.nextLong());

		newFragmentEntryLayoutTemplateLink.setFragmentEntryId(RandomTestUtil.nextLong());

		newFragmentEntryLayoutTemplateLink.setLayoutPageTemplateEntryId(RandomTestUtil.nextLong());

		_fragmentEntryLayoutTemplateLinks.add(_persistence.update(
				newFragmentEntryLayoutTemplateLink));

		FragmentEntryLayoutTemplateLink existingFragmentEntryLayoutTemplateLink = _persistence.findByPrimaryKey(newFragmentEntryLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId(),
			newFragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId());
		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink.getGroupId(),
			newFragmentEntryLayoutTemplateLink.getGroupId());
		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink.getFragmentEntryId(),
			newFragmentEntryLayoutTemplateLink.getFragmentEntryId());
		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink.getLayoutPageTemplateEntryId(),
			newFragmentEntryLayoutTemplateLink.getLayoutPageTemplateEntryId());
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
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		FragmentEntryLayoutTemplateLink existingFragmentEntryLayoutTemplateLink = _persistence.findByPrimaryKey(newFragmentEntryLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink,
			newFragmentEntryLayoutTemplateLink);
	}

	@Test(expected = NoSuchEntryLayoutTemplateLinkException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<FragmentEntryLayoutTemplateLink> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("FragmentEntryLayoutTemplateLink",
			"fragmentEntryLayoutTemplateLinkId", true, "groupId", true,
			"fragmentEntryId", true, "layoutPageTemplateEntryId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		FragmentEntryLayoutTemplateLink existingFragmentEntryLayoutTemplateLink = _persistence.fetchByPrimaryKey(newFragmentEntryLayoutTemplateLink.getPrimaryKey());

		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink,
			newFragmentEntryLayoutTemplateLink);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentEntryLayoutTemplateLink missingFragmentEntryLayoutTemplateLink = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFragmentEntryLayoutTemplateLink);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink1 = addFragmentEntryLayoutTemplateLink();
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink2 = addFragmentEntryLayoutTemplateLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentEntryLayoutTemplateLink1.getPrimaryKey());
		primaryKeys.add(newFragmentEntryLayoutTemplateLink2.getPrimaryKey());

		Map<Serializable, FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fragmentEntryLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentEntryLayoutTemplateLink1,
			fragmentEntryLayoutTemplateLinks.get(
				newFragmentEntryLayoutTemplateLink1.getPrimaryKey()));
		Assert.assertEquals(newFragmentEntryLayoutTemplateLink2,
			fragmentEntryLayoutTemplateLinks.get(
				newFragmentEntryLayoutTemplateLink2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fragmentEntryLayoutTemplateLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentEntryLayoutTemplateLink.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fragmentEntryLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentEntryLayoutTemplateLink,
			fragmentEntryLayoutTemplateLinks.get(
				newFragmentEntryLayoutTemplateLink.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fragmentEntryLayoutTemplateLinks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFragmentEntryLayoutTemplateLink.getPrimaryKey());

		Map<Serializable, FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fragmentEntryLayoutTemplateLinks.size());
		Assert.assertEquals(newFragmentEntryLayoutTemplateLink,
			fragmentEntryLayoutTemplateLinks.get(
				newFragmentEntryLayoutTemplateLink.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = FragmentEntryLayoutTemplateLinkLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<FragmentEntryLayoutTemplateLink>() {
				@Override
				public void performAction(
					FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
					Assert.assertNotNull(fragmentEntryLayoutTemplateLink);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentEntryLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"fragmentEntryLayoutTemplateLinkId",
				newFragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId()));

		List<FragmentEntryLayoutTemplateLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		FragmentEntryLayoutTemplateLink existingFragmentEntryLayoutTemplateLink = result.get(0);

		Assert.assertEquals(existingFragmentEntryLayoutTemplateLink,
			newFragmentEntryLayoutTemplateLink);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentEntryLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"fragmentEntryLayoutTemplateLinkId", RandomTestUtil.nextLong()));

		List<FragmentEntryLayoutTemplateLink> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		FragmentEntryLayoutTemplateLink newFragmentEntryLayoutTemplateLink = addFragmentEntryLayoutTemplateLink();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentEntryLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fragmentEntryLayoutTemplateLinkId"));

		Object newFragmentEntryLayoutTemplateLinkId = newFragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"fragmentEntryLayoutTemplateLinkId",
				new Object[] { newFragmentEntryLayoutTemplateLinkId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFragmentEntryLayoutTemplateLinkId = result.get(0);

		Assert.assertEquals(existingFragmentEntryLayoutTemplateLinkId,
			newFragmentEntryLayoutTemplateLinkId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(FragmentEntryLayoutTemplateLink.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"fragmentEntryLayoutTemplateLinkId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"fragmentEntryLayoutTemplateLinkId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected FragmentEntryLayoutTemplateLink addFragmentEntryLayoutTemplateLink()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink = _persistence.create(pk);

		fragmentEntryLayoutTemplateLink.setGroupId(RandomTestUtil.nextLong());

		fragmentEntryLayoutTemplateLink.setFragmentEntryId(RandomTestUtil.nextLong());

		fragmentEntryLayoutTemplateLink.setLayoutPageTemplateEntryId(RandomTestUtil.nextLong());

		_fragmentEntryLayoutTemplateLinks.add(_persistence.update(
				fragmentEntryLayoutTemplateLink));

		return fragmentEntryLayoutTemplateLink;
	}

	private List<FragmentEntryLayoutTemplateLink> _fragmentEntryLayoutTemplateLinks =
		new ArrayList<FragmentEntryLayoutTemplateLink>();
	private FragmentEntryLayoutTemplateLinkPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}