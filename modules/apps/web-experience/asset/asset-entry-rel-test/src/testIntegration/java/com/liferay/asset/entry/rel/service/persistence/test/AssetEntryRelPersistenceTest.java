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

import com.liferay.asset.entry.rel.exception.NoSuchEntryRelException;
import com.liferay.asset.entry.rel.model.AssetEntryRel;
import com.liferay.asset.entry.rel.service.AssetEntryRelLocalServiceUtil;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryRelPersistence;
import com.liferay.asset.entry.rel.service.persistence.AssetEntryRelUtil;

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
public class AssetEntryRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.asset.entry.rel.service"));

	@Before
	public void setUp() {
		_persistence = AssetEntryRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetEntryRel> iterator = _assetEntryRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryRel assetEntryRel = _persistence.create(pk);

		Assert.assertNotNull(assetEntryRel);

		Assert.assertEquals(assetEntryRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		_persistence.remove(newAssetEntryRel);

		AssetEntryRel existingAssetEntryRel = _persistence.fetchByPrimaryKey(newAssetEntryRel.getPrimaryKey());

		Assert.assertNull(existingAssetEntryRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetEntryRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryRel newAssetEntryRel = _persistence.create(pk);

		newAssetEntryRel.setAssetEntryId(RandomTestUtil.nextLong());

		newAssetEntryRel.setClassNameId(RandomTestUtil.nextLong());

		newAssetEntryRel.setClassPK(RandomTestUtil.nextLong());

		_assetEntryRels.add(_persistence.update(newAssetEntryRel));

		AssetEntryRel existingAssetEntryRel = _persistence.findByPrimaryKey(newAssetEntryRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryRel.getAssetEntryRelId(),
			newAssetEntryRel.getAssetEntryRelId());
		Assert.assertEquals(existingAssetEntryRel.getAssetEntryId(),
			newAssetEntryRel.getAssetEntryId());
		Assert.assertEquals(existingAssetEntryRel.getClassNameId(),
			newAssetEntryRel.getClassNameId());
		Assert.assertEquals(existingAssetEntryRel.getClassPK(),
			newAssetEntryRel.getClassPK());
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
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		AssetEntryRel existingAssetEntryRel = _persistence.findByPrimaryKey(newAssetEntryRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryRel, newAssetEntryRel);
	}

	@Test(expected = NoSuchEntryRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AssetEntryRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetEntryRel",
			"assetEntryRelId", true, "assetEntryId", true, "classNameId", true,
			"classPK", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		AssetEntryRel existingAssetEntryRel = _persistence.fetchByPrimaryKey(newAssetEntryRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryRel, newAssetEntryRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryRel missingAssetEntryRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetEntryRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AssetEntryRel newAssetEntryRel1 = addAssetEntryRel();
		AssetEntryRel newAssetEntryRel2 = addAssetEntryRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryRel1.getPrimaryKey());
		primaryKeys.add(newAssetEntryRel2.getPrimaryKey());

		Map<Serializable, AssetEntryRel> assetEntryRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, assetEntryRels.size());
		Assert.assertEquals(newAssetEntryRel1,
			assetEntryRels.get(newAssetEntryRel1.getPrimaryKey()));
		Assert.assertEquals(newAssetEntryRel2,
			assetEntryRels.get(newAssetEntryRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AssetEntryRel> assetEntryRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AssetEntryRel> assetEntryRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryRels.size());
		Assert.assertEquals(newAssetEntryRel,
			assetEntryRels.get(newAssetEntryRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AssetEntryRel> assetEntryRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryRel.getPrimaryKey());

		Map<Serializable, AssetEntryRel> assetEntryRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryRels.size());
		Assert.assertEquals(newAssetEntryRel,
			assetEntryRels.get(newAssetEntryRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AssetEntryRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AssetEntryRel>() {
				@Override
				public void performAction(AssetEntryRel assetEntryRel) {
					Assert.assertNotNull(assetEntryRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("assetEntryRelId",
				newAssetEntryRel.getAssetEntryRelId()));

		List<AssetEntryRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetEntryRel existingAssetEntryRel = result.get(0);

		Assert.assertEquals(existingAssetEntryRel, newAssetEntryRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("assetEntryRelId",
				RandomTestUtil.nextLong()));

		List<AssetEntryRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetEntryRelId"));

		Object newAssetEntryRelId = newAssetEntryRel.getAssetEntryRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("assetEntryRelId",
				new Object[] { newAssetEntryRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAssetEntryRelId = result.get(0);

		Assert.assertEquals(existingAssetEntryRelId, newAssetEntryRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"assetEntryRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("assetEntryRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AssetEntryRel newAssetEntryRel = addAssetEntryRel();

		_persistence.clearCache();

		AssetEntryRel existingAssetEntryRel = _persistence.findByPrimaryKey(newAssetEntryRel.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingAssetEntryRel.getAssetEntryId()),
			ReflectionTestUtil.<Long>invoke(existingAssetEntryRel,
				"getOriginalAssetEntryId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingAssetEntryRel.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingAssetEntryRel,
				"getOriginalClassNameId", new Class<?>[0]));
	}

	protected AssetEntryRel addAssetEntryRel() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryRel assetEntryRel = _persistence.create(pk);

		assetEntryRel.setAssetEntryId(RandomTestUtil.nextLong());

		assetEntryRel.setClassNameId(RandomTestUtil.nextLong());

		assetEntryRel.setClassPK(RandomTestUtil.nextLong());

		_assetEntryRels.add(_persistence.update(assetEntryRel));

		return assetEntryRel;
	}

	private List<AssetEntryRel> _assetEntryRels = new ArrayList<AssetEntryRel>();
	private AssetEntryRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}