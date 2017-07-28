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

package com.liferay.asset.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.asset.exception.NoSuchEntryAssetTagRelException;
import com.liferay.asset.model.AssetEntryAssetTagRel;
import com.liferay.asset.service.AssetEntryAssetTagRelLocalServiceUtil;
import com.liferay.asset.service.persistence.AssetEntryAssetTagRelPersistence;
import com.liferay.asset.service.persistence.AssetEntryAssetTagRelUtil;

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
public class AssetEntryAssetTagRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.asset.service"));

	@Before
	public void setUp() {
		_persistence = AssetEntryAssetTagRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AssetEntryAssetTagRel> iterator = _assetEntryAssetTagRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryAssetTagRel assetEntryAssetTagRel = _persistence.create(pk);

		Assert.assertNotNull(assetEntryAssetTagRel);

		Assert.assertEquals(assetEntryAssetTagRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		_persistence.remove(newAssetEntryAssetTagRel);

		AssetEntryAssetTagRel existingAssetEntryAssetTagRel = _persistence.fetchByPrimaryKey(newAssetEntryAssetTagRel.getPrimaryKey());

		Assert.assertNull(existingAssetEntryAssetTagRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAssetEntryAssetTagRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryAssetTagRel newAssetEntryAssetTagRel = _persistence.create(pk);

		newAssetEntryAssetTagRel.setCompanyId(RandomTestUtil.nextLong());

		newAssetEntryAssetTagRel.setAssetEntryId(RandomTestUtil.nextLong());

		newAssetEntryAssetTagRel.setAssetTagId(RandomTestUtil.nextLong());

		_assetEntryAssetTagRels.add(_persistence.update(
				newAssetEntryAssetTagRel));

		AssetEntryAssetTagRel existingAssetEntryAssetTagRel = _persistence.findByPrimaryKey(newAssetEntryAssetTagRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryAssetTagRel.getEntryId(),
			newAssetEntryAssetTagRel.getEntryId());
		Assert.assertEquals(existingAssetEntryAssetTagRel.getCompanyId(),
			newAssetEntryAssetTagRel.getCompanyId());
		Assert.assertEquals(existingAssetEntryAssetTagRel.getAssetEntryId(),
			newAssetEntryAssetTagRel.getAssetEntryId());
		Assert.assertEquals(existingAssetEntryAssetTagRel.getAssetTagId(),
			newAssetEntryAssetTagRel.getAssetTagId());
	}

	@Test
	public void testCountByAssetEntryId() throws Exception {
		_persistence.countByAssetEntryId(RandomTestUtil.nextLong());

		_persistence.countByAssetEntryId(0L);
	}

	@Test
	public void testCountByAssetTagId() throws Exception {
		_persistence.countByAssetTagId(RandomTestUtil.nextLong());

		_persistence.countByAssetTagId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		AssetEntryAssetTagRel existingAssetEntryAssetTagRel = _persistence.findByPrimaryKey(newAssetEntryAssetTagRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryAssetTagRel,
			newAssetEntryAssetTagRel);
	}

	@Test(expected = NoSuchEntryAssetTagRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<AssetEntryAssetTagRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("AssetEntryAssetTagRel",
			"entryId", true, "companyId", true, "assetEntryId", true,
			"assetTagId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		AssetEntryAssetTagRel existingAssetEntryAssetTagRel = _persistence.fetchByPrimaryKey(newAssetEntryAssetTagRel.getPrimaryKey());

		Assert.assertEquals(existingAssetEntryAssetTagRel,
			newAssetEntryAssetTagRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryAssetTagRel missingAssetEntryAssetTagRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAssetEntryAssetTagRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel1 = addAssetEntryAssetTagRel();
		AssetEntryAssetTagRel newAssetEntryAssetTagRel2 = addAssetEntryAssetTagRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryAssetTagRel1.getPrimaryKey());
		primaryKeys.add(newAssetEntryAssetTagRel2.getPrimaryKey());

		Map<Serializable, AssetEntryAssetTagRel> assetEntryAssetTagRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, assetEntryAssetTagRels.size());
		Assert.assertEquals(newAssetEntryAssetTagRel1,
			assetEntryAssetTagRels.get(
				newAssetEntryAssetTagRel1.getPrimaryKey()));
		Assert.assertEquals(newAssetEntryAssetTagRel2,
			assetEntryAssetTagRels.get(
				newAssetEntryAssetTagRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AssetEntryAssetTagRel> assetEntryAssetTagRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryAssetTagRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryAssetTagRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AssetEntryAssetTagRel> assetEntryAssetTagRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryAssetTagRels.size());
		Assert.assertEquals(newAssetEntryAssetTagRel,
			assetEntryAssetTagRels.get(newAssetEntryAssetTagRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AssetEntryAssetTagRel> assetEntryAssetTagRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(assetEntryAssetTagRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAssetEntryAssetTagRel.getPrimaryKey());

		Map<Serializable, AssetEntryAssetTagRel> assetEntryAssetTagRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, assetEntryAssetTagRels.size());
		Assert.assertEquals(newAssetEntryAssetTagRel,
			assetEntryAssetTagRels.get(newAssetEntryAssetTagRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = AssetEntryAssetTagRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<AssetEntryAssetTagRel>() {
				@Override
				public void performAction(
					AssetEntryAssetTagRel assetEntryAssetTagRel) {
					Assert.assertNotNull(assetEntryAssetTagRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryAssetTagRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				newAssetEntryAssetTagRel.getEntryId()));

		List<AssetEntryAssetTagRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AssetEntryAssetTagRel existingAssetEntryAssetTagRel = result.get(0);

		Assert.assertEquals(existingAssetEntryAssetTagRel,
			newAssetEntryAssetTagRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryAssetTagRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entryId",
				RandomTestUtil.nextLong()));

		List<AssetEntryAssetTagRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		AssetEntryAssetTagRel newAssetEntryAssetTagRel = addAssetEntryAssetTagRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryAssetTagRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		Object newEntryId = newAssetEntryAssetTagRel.getEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { newEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntryId = result.get(0);

		Assert.assertEquals(existingEntryId, newEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(AssetEntryAssetTagRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("entryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected AssetEntryAssetTagRel addAssetEntryAssetTagRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		AssetEntryAssetTagRel assetEntryAssetTagRel = _persistence.create(pk);

		assetEntryAssetTagRel.setCompanyId(RandomTestUtil.nextLong());

		assetEntryAssetTagRel.setAssetEntryId(RandomTestUtil.nextLong());

		assetEntryAssetTagRel.setAssetTagId(RandomTestUtil.nextLong());

		_assetEntryAssetTagRels.add(_persistence.update(assetEntryAssetTagRel));

		return assetEntryAssetTagRel;
	}

	private List<AssetEntryAssetTagRel> _assetEntryAssetTagRels = new ArrayList<AssetEntryAssetTagRel>();
	private AssetEntryAssetTagRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}