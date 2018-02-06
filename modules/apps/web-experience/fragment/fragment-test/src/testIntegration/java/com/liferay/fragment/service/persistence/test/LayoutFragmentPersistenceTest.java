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

import com.liferay.fragment.exception.NoSuchLayoutFragmentException;
import com.liferay.fragment.model.LayoutFragment;
import com.liferay.fragment.service.LayoutFragmentLocalServiceUtil;
import com.liferay.fragment.service.persistence.LayoutFragmentPersistence;
import com.liferay.fragment.service.persistence.LayoutFragmentUtil;

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
public class LayoutFragmentPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.fragment.service"));

	@Before
	public void setUp() {
		_persistence = LayoutFragmentUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LayoutFragment> iterator = _layoutFragments.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutFragment layoutFragment = _persistence.create(pk);

		Assert.assertNotNull(layoutFragment);

		Assert.assertEquals(layoutFragment.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		_persistence.remove(newLayoutFragment);

		LayoutFragment existingLayoutFragment = _persistence.fetchByPrimaryKey(newLayoutFragment.getPrimaryKey());

		Assert.assertNull(existingLayoutFragment);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutFragment();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutFragment newLayoutFragment = _persistence.create(pk);

		newLayoutFragment.setGroupId(RandomTestUtil.nextLong());

		newLayoutFragment.setPlid(RandomTestUtil.nextLong());

		newLayoutFragment.setFragmentEntryId(RandomTestUtil.nextLong());

		newLayoutFragment.setCss(RandomTestUtil.randomString());

		newLayoutFragment.setHtml(RandomTestUtil.randomString());

		newLayoutFragment.setJs(RandomTestUtil.randomString());

		newLayoutFragment.setEditableValues(RandomTestUtil.randomString());

		newLayoutFragment.setPosition(RandomTestUtil.nextInt());

		_layoutFragments.add(_persistence.update(newLayoutFragment));

		LayoutFragment existingLayoutFragment = _persistence.findByPrimaryKey(newLayoutFragment.getPrimaryKey());

		Assert.assertEquals(existingLayoutFragment.getLayoutFragmentId(),
			newLayoutFragment.getLayoutFragmentId());
		Assert.assertEquals(existingLayoutFragment.getGroupId(),
			newLayoutFragment.getGroupId());
		Assert.assertEquals(existingLayoutFragment.getPlid(),
			newLayoutFragment.getPlid());
		Assert.assertEquals(existingLayoutFragment.getFragmentEntryId(),
			newLayoutFragment.getFragmentEntryId());
		Assert.assertEquals(existingLayoutFragment.getCss(),
			newLayoutFragment.getCss());
		Assert.assertEquals(existingLayoutFragment.getHtml(),
			newLayoutFragment.getHtml());
		Assert.assertEquals(existingLayoutFragment.getJs(),
			newLayoutFragment.getJs());
		Assert.assertEquals(existingLayoutFragment.getEditableValues(),
			newLayoutFragment.getEditableValues());
		Assert.assertEquals(existingLayoutFragment.getPosition(),
			newLayoutFragment.getPosition());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_P() throws Exception {
		_persistence.countByG_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_P(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		LayoutFragment existingLayoutFragment = _persistence.findByPrimaryKey(newLayoutFragment.getPrimaryKey());

		Assert.assertEquals(existingLayoutFragment, newLayoutFragment);
	}

	@Test(expected = NoSuchLayoutFragmentException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LayoutFragment> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LayoutFragment",
			"layoutFragmentId", true, "groupId", true, "plid", true,
			"fragmentEntryId", true, "css", true, "html", true, "js", true,
			"editableValues", true, "position", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		LayoutFragment existingLayoutFragment = _persistence.fetchByPrimaryKey(newLayoutFragment.getPrimaryKey());

		Assert.assertEquals(existingLayoutFragment, newLayoutFragment);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutFragment missingLayoutFragment = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutFragment);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LayoutFragment newLayoutFragment1 = addLayoutFragment();
		LayoutFragment newLayoutFragment2 = addLayoutFragment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutFragment1.getPrimaryKey());
		primaryKeys.add(newLayoutFragment2.getPrimaryKey());

		Map<Serializable, LayoutFragment> layoutFragments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, layoutFragments.size());
		Assert.assertEquals(newLayoutFragment1,
			layoutFragments.get(newLayoutFragment1.getPrimaryKey()));
		Assert.assertEquals(newLayoutFragment2,
			layoutFragments.get(newLayoutFragment2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LayoutFragment> layoutFragments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutFragments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutFragment.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LayoutFragment> layoutFragments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutFragments.size());
		Assert.assertEquals(newLayoutFragment,
			layoutFragments.get(newLayoutFragment.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LayoutFragment> layoutFragments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutFragments.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutFragment.getPrimaryKey());

		Map<Serializable, LayoutFragment> layoutFragments = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutFragments.size());
		Assert.assertEquals(newLayoutFragment,
			layoutFragments.get(newLayoutFragment.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LayoutFragmentLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LayoutFragment>() {
				@Override
				public void performAction(LayoutFragment layoutFragment) {
					Assert.assertNotNull(layoutFragment);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutFragment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutFragmentId",
				newLayoutFragment.getLayoutFragmentId()));

		List<LayoutFragment> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutFragment existingLayoutFragment = result.get(0);

		Assert.assertEquals(existingLayoutFragment, newLayoutFragment);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutFragment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutFragmentId",
				RandomTestUtil.nextLong()));

		List<LayoutFragment> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutFragment newLayoutFragment = addLayoutFragment();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutFragment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutFragmentId"));

		Object newLayoutFragmentId = newLayoutFragment.getLayoutFragmentId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutFragmentId",
				new Object[] { newLayoutFragmentId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutFragmentId = result.get(0);

		Assert.assertEquals(existingLayoutFragmentId, newLayoutFragmentId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutFragment.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutFragmentId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutFragmentId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LayoutFragment addLayoutFragment() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutFragment layoutFragment = _persistence.create(pk);

		layoutFragment.setGroupId(RandomTestUtil.nextLong());

		layoutFragment.setPlid(RandomTestUtil.nextLong());

		layoutFragment.setFragmentEntryId(RandomTestUtil.nextLong());

		layoutFragment.setCss(RandomTestUtil.randomString());

		layoutFragment.setHtml(RandomTestUtil.randomString());

		layoutFragment.setJs(RandomTestUtil.randomString());

		layoutFragment.setEditableValues(RandomTestUtil.randomString());

		layoutFragment.setPosition(RandomTestUtil.nextInt());

		_layoutFragments.add(_persistence.update(layoutFragment));

		return layoutFragment;
	}

	private List<LayoutFragment> _layoutFragments = new ArrayList<LayoutFragment>();
	private LayoutFragmentPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}