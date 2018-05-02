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

package com.liferay.asset.entry.rel.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException;
import com.liferay.asset.entry.rel.model.AssetEntryClassNameRel;
import com.liferay.asset.entry.rel.service.AssetEntryClassNameRelLocalServiceUtil;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryClassNameRelPersistence;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryClassNameRelUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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
public class AssetEntryClassNameRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.asset.entry.rel.service"));

	@Before
	public void setUp() {
		_persistence = AssetEntryClassNameRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetEntryClassNameRel> iterator = _assetEntryClassNameRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryClassNameRel assetEntryClassNameRel = _persistence.create(pk);

		Assert.assertNotNull(assetEntryClassNameRel);

		Assert.assertEquals(assetEntryClassNameRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		_persistence.remove(newAssetEntryClassNameRel);

		AssetEntryClassNameRel existingAssetEntryClassNameRel = _persistence.fetchByPrimaryKey(newAssetEntryClassNameRel.getPrimaryKey());

		Assert.assertNull(existingAssetEntryClassNameRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetEntryClassNameRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryClassNameRel newAssetEntryClassNameRel = _persistence.create(pk);

		newAssetEntryClassNameRel.setAssetEntryId(RandomTestUtil.nextLong());

		newAssetEntryClassNameRel.setClassNameId(RandomTestUtil.nextLong());

		newAssetEntryClassNameRel.setClassPK(RandomTestUtil.nextLong());

		_assetEntryClassNameRels.add(_persistence.update(
				newAssetEntryClassNameRel));

		AssetEntryClassNameRel existingAssetEntryClassNameRel = _persistence.findByPrimaryKey(newAssetEntryClassNameRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryClassNameRel.getAssetEntryClassNameRelId(),
			newAssetEntryClassNameRel.getAssetEntryClassNameRelId());
		Assert.assertEquals(existingAssetEntryClassNameRel.getAssetEntryId(),
			newAssetEntryClassNameRel.getAssetEntryId());
		Assert.assertEquals(existingAssetEntryClassNameRel.getClassNameId(),
			newAssetEntryClassNameRel.getClassNameId());
		Assert.assertEquals(existingAssetEntryClassNameRel.getClassPK(),
			newAssetEntryClassNameRel.getClassPK());
	}

	@Test
	public void testCountByAssetEntry() throws Exception {
		_persistence.countByAssetEntry(RandomTestUtil.nextLong());

		_persistence.countByAssetEntry(0L);
	}

	@Test
	public void testCountByA_C() throws Exception {
		_persistence.countByA_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByA_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		AssetEntryClassNameRel existingAssetEntryClassNameRel = _persistence.findByPrimaryKey(newAssetEntryClassNameRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryClassNameRel,
			newAssetEntryClassNameRel);
	}

	@Test(expected = NoSuchEntryClassNameRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AssetEntryClassNameRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetEntryClassNameRel",
			"assetEntryClassNameRelId", true, "assetEntryId", true,
			"classNameId", true, "classPK", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		AssetEntryClassNameRel existingAssetEntryClassNameRel = _persistence.fetchByPrimaryKey(newAssetEntryClassNameRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryClassNameRel,
			newAssetEntryClassNameRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryClassNameRel missingAssetEntryClassNameRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetEntryClassNameRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel1 = addAssetEntryClassNameRel();
		AssetEntryClassNameRel newAssetEntryClassNameRel2 = addAssetEntryClassNameRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryClassNameRel1.getPrimaryKey());
		primaryKeys.add(newAssetEntryClassNameRel2.getPrimaryKey());

		Map<Serializable, AssetEntryClassNameRel> assetEntryClassNameRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, assetEntryClassNameRels.size());
		Assert.assertEquals(newAssetEntryClassNameRel1,
			assetEntryClassNameRels.get(
				newAssetEntryClassNameRel1.getPrimaryKey()));
		Assert.assertEquals(newAssetEntryClassNameRel2,
			assetEntryClassNameRels.get(
				newAssetEntryClassNameRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AssetEntryClassNameRel> assetEntryClassNameRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryClassNameRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryClassNameRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AssetEntryClassNameRel> assetEntryClassNameRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryClassNameRels.size());
		Assert.assertEquals(newAssetEntryClassNameRel,
			assetEntryClassNameRels.get(
				newAssetEntryClassNameRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AssetEntryClassNameRel> assetEntryClassNameRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryClassNameRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryClassNameRel.getPrimaryKey());

		Map<Serializable, AssetEntryClassNameRel> assetEntryClassNameRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryClassNameRels.size());
		Assert.assertEquals(newAssetEntryClassNameRel,
			assetEntryClassNameRels.get(
				newAssetEntryClassNameRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AssetEntryClassNameRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AssetEntryClassNameRel>() {
				@Override
				public void performAction(
					AssetEntryClassNameRel assetEntryClassNameRel) {
					Assert.assertNotNull(assetEntryClassNameRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryClassNameRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"assetEntryClassNameRelId",
				newAssetEntryClassNameRel.getAssetEntryClassNameRelId()));

		List<AssetEntryClassNameRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetEntryClassNameRel existingAssetEntryClassNameRel = result.get(0);

		Assert.assertEquals(existingAssetEntryClassNameRel,
			newAssetEntryClassNameRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryClassNameRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"assetEntryClassNameRelId", RandomTestUtil.nextLong()));

		List<AssetEntryClassNameRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryClassNameRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetEntryClassNameRelId"));

		Object newAssetEntryClassNameRelId = newAssetEntryClassNameRel.getAssetEntryClassNameRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"assetEntryClassNameRelId",
				new Object[] { newAssetEntryClassNameRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAssetEntryClassNameRelId = result.get(0);

		Assert.assertEquals(existingAssetEntryClassNameRelId,
			newAssetEntryClassNameRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryClassNameRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetEntryClassNameRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"assetEntryClassNameRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AssetEntryClassNameRel newAssetEntryClassNameRel = addAssetEntryClassNameRel();

		_persistence.clearCache();

		AssetEntryClassNameRel existingAssetEntryClassNameRel = _persistence.findByPrimaryKey(newAssetEntryClassNameRel.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingAssetEntryClassNameRel.getAssetEntryId()),
			ReflectionTestUtil.<Long>invoke(existingAssetEntryClassNameRel,
				"getOriginalAssetEntryId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingAssetEntryClassNameRel.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingAssetEntryClassNameRel,
				"getOriginalClassNameId", new Class<?>[0]));
	}

	protected AssetEntryClassNameRel addAssetEntryClassNameRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryClassNameRel assetEntryClassNameRel = _persistence.create(pk);

		assetEntryClassNameRel.setAssetEntryId(RandomTestUtil.nextLong());

		assetEntryClassNameRel.setClassNameId(RandomTestUtil.nextLong());

		assetEntryClassNameRel.setClassPK(RandomTestUtil.nextLong());

		_assetEntryClassNameRels.add(_persistence.update(assetEntryClassNameRel));

		return assetEntryClassNameRel;
	}

	private List<AssetEntryClassNameRel> _assetEntryClassNameRels = new ArrayList<AssetEntryClassNameRel>();
	private AssetEntryClassNameRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}