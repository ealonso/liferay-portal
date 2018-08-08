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

package com.liferay.layout.page.template.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.layout.page.template.exception.NoSuchPageTemplateSettingException;
import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;
import com.liferay.layout.page.template.service.LayoutPageTemplateSettingLocalServiceUtil;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateSettingPersistence;
import com.liferay.layout.page.template.service.persistence.LayoutPageTemplateSettingUtil;

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
import com.liferay.portal.kernel.util.Time;
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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class LayoutPageTemplateSettingPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.layout.page.template.service"));

	@Before
	public void setUp() {
		_persistence = LayoutPageTemplateSettingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LayoutPageTemplateSetting> iterator = _layoutPageTemplateSettings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPageTemplateSetting layoutPageTemplateSetting = _persistence.create(pk);

		Assert.assertNotNull(layoutPageTemplateSetting);

		Assert.assertEquals(layoutPageTemplateSetting.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		_persistence.remove(newLayoutPageTemplateSetting);

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = _persistence.fetchByPrimaryKey(newLayoutPageTemplateSetting.getPrimaryKey());

		Assert.assertNull(existingLayoutPageTemplateSetting);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutPageTemplateSetting();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPageTemplateSetting newLayoutPageTemplateSetting = _persistence.create(pk);

		newLayoutPageTemplateSetting.setUuid(RandomTestUtil.randomString());

		newLayoutPageTemplateSetting.setGroupId(RandomTestUtil.nextLong());

		newLayoutPageTemplateSetting.setCompanyId(RandomTestUtil.nextLong());

		newLayoutPageTemplateSetting.setUserId(RandomTestUtil.nextLong());

		newLayoutPageTemplateSetting.setUserName(RandomTestUtil.randomString());

		newLayoutPageTemplateSetting.setCreateDate(RandomTestUtil.nextDate());

		newLayoutPageTemplateSetting.setModifiedDate(RandomTestUtil.nextDate());

		newLayoutPageTemplateSetting.setClassNameId(RandomTestUtil.nextLong());

		newLayoutPageTemplateSetting.setClassPK(RandomTestUtil.nextLong());

		newLayoutPageTemplateSetting.setSettings(RandomTestUtil.randomString());

		_layoutPageTemplateSettings.add(_persistence.update(
				newLayoutPageTemplateSetting));

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = _persistence.findByPrimaryKey(newLayoutPageTemplateSetting.getPrimaryKey());

		Assert.assertEquals(existingLayoutPageTemplateSetting.getUuid(),
			newLayoutPageTemplateSetting.getUuid());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getLayoutPageTemplateSettingId(),
			newLayoutPageTemplateSetting.getLayoutPageTemplateSettingId());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getGroupId(),
			newLayoutPageTemplateSetting.getGroupId());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getCompanyId(),
			newLayoutPageTemplateSetting.getCompanyId());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getUserId(),
			newLayoutPageTemplateSetting.getUserId());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getUserName(),
			newLayoutPageTemplateSetting.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutPageTemplateSetting.getCreateDate()),
			Time.getShortTimestamp(newLayoutPageTemplateSetting.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutPageTemplateSetting.getModifiedDate()),
			Time.getShortTimestamp(
				newLayoutPageTemplateSetting.getModifiedDate()));
		Assert.assertEquals(existingLayoutPageTemplateSetting.getClassNameId(),
			newLayoutPageTemplateSetting.getClassNameId());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getClassPK(),
			newLayoutPageTemplateSetting.getClassPK());
		Assert.assertEquals(existingLayoutPageTemplateSetting.getSettings(),
			newLayoutPageTemplateSetting.getSettings());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_C_C() throws Exception {
		_persistence.countByG_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_C_C(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = _persistence.findByPrimaryKey(newLayoutPageTemplateSetting.getPrimaryKey());

		Assert.assertEquals(existingLayoutPageTemplateSetting,
			newLayoutPageTemplateSetting);
	}

	@Test(expected = NoSuchPageTemplateSettingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LayoutPageTemplateSetting> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LayoutPageTemplateSetting",
			"uuid", true, "layoutPageTemplateSettingId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "classNameId", true, "classPK", true,
			"settings", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = _persistence.fetchByPrimaryKey(newLayoutPageTemplateSetting.getPrimaryKey());

		Assert.assertEquals(existingLayoutPageTemplateSetting,
			newLayoutPageTemplateSetting);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPageTemplateSetting missingLayoutPageTemplateSetting = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutPageTemplateSetting);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting1 = addLayoutPageTemplateSetting();
		LayoutPageTemplateSetting newLayoutPageTemplateSetting2 = addLayoutPageTemplateSetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutPageTemplateSetting1.getPrimaryKey());
		primaryKeys.add(newLayoutPageTemplateSetting2.getPrimaryKey());

		Map<Serializable, LayoutPageTemplateSetting> layoutPageTemplateSettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, layoutPageTemplateSettings.size());
		Assert.assertEquals(newLayoutPageTemplateSetting1,
			layoutPageTemplateSettings.get(
				newLayoutPageTemplateSetting1.getPrimaryKey()));
		Assert.assertEquals(newLayoutPageTemplateSetting2,
			layoutPageTemplateSettings.get(
				newLayoutPageTemplateSetting2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LayoutPageTemplateSetting> layoutPageTemplateSettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutPageTemplateSettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutPageTemplateSetting.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LayoutPageTemplateSetting> layoutPageTemplateSettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutPageTemplateSettings.size());
		Assert.assertEquals(newLayoutPageTemplateSetting,
			layoutPageTemplateSettings.get(
				newLayoutPageTemplateSetting.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LayoutPageTemplateSetting> layoutPageTemplateSettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(layoutPageTemplateSettings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLayoutPageTemplateSetting.getPrimaryKey());

		Map<Serializable, LayoutPageTemplateSetting> layoutPageTemplateSettings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, layoutPageTemplateSettings.size());
		Assert.assertEquals(newLayoutPageTemplateSetting,
			layoutPageTemplateSettings.get(
				newLayoutPageTemplateSetting.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LayoutPageTemplateSettingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LayoutPageTemplateSetting>() {
				@Override
				public void performAction(
					LayoutPageTemplateSetting layoutPageTemplateSetting) {
					Assert.assertNotNull(layoutPageTemplateSetting);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPageTemplateSetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"layoutPageTemplateSettingId",
				newLayoutPageTemplateSetting.getLayoutPageTemplateSettingId()));

		List<LayoutPageTemplateSetting> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = result.get(0);

		Assert.assertEquals(existingLayoutPageTemplateSetting,
			newLayoutPageTemplateSetting);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPageTemplateSetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"layoutPageTemplateSettingId", RandomTestUtil.nextLong()));

		List<LayoutPageTemplateSetting> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPageTemplateSetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutPageTemplateSettingId"));

		Object newLayoutPageTemplateSettingId = newLayoutPageTemplateSetting.getLayoutPageTemplateSettingId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"layoutPageTemplateSettingId",
				new Object[] { newLayoutPageTemplateSettingId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutPageTemplateSettingId = result.get(0);

		Assert.assertEquals(existingLayoutPageTemplateSettingId,
			newLayoutPageTemplateSettingId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutPageTemplateSetting.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"layoutPageTemplateSettingId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"layoutPageTemplateSettingId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LayoutPageTemplateSetting newLayoutPageTemplateSetting = addLayoutPageTemplateSetting();

		_persistence.clearCache();

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting = _persistence.findByPrimaryKey(newLayoutPageTemplateSetting.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingLayoutPageTemplateSetting.getUuid(),
				ReflectionTestUtil.invoke(existingLayoutPageTemplateSetting,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingLayoutPageTemplateSetting.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingLayoutPageTemplateSetting,
				"getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingLayoutPageTemplateSetting.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingLayoutPageTemplateSetting,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingLayoutPageTemplateSetting.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingLayoutPageTemplateSetting,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingLayoutPageTemplateSetting.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingLayoutPageTemplateSetting,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected LayoutPageTemplateSetting addLayoutPageTemplateSetting()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutPageTemplateSetting layoutPageTemplateSetting = _persistence.create(pk);

		layoutPageTemplateSetting.setUuid(RandomTestUtil.randomString());

		layoutPageTemplateSetting.setGroupId(RandomTestUtil.nextLong());

		layoutPageTemplateSetting.setCompanyId(RandomTestUtil.nextLong());

		layoutPageTemplateSetting.setUserId(RandomTestUtil.nextLong());

		layoutPageTemplateSetting.setUserName(RandomTestUtil.randomString());

		layoutPageTemplateSetting.setCreateDate(RandomTestUtil.nextDate());

		layoutPageTemplateSetting.setModifiedDate(RandomTestUtil.nextDate());

		layoutPageTemplateSetting.setClassNameId(RandomTestUtil.nextLong());

		layoutPageTemplateSetting.setClassPK(RandomTestUtil.nextLong());

		layoutPageTemplateSetting.setSettings(RandomTestUtil.randomString());

		_layoutPageTemplateSettings.add(_persistence.update(
				layoutPageTemplateSetting));

		return layoutPageTemplateSetting;
	}

	private List<LayoutPageTemplateSetting> _layoutPageTemplateSettings = new ArrayList<LayoutPageTemplateSetting>();
	private LayoutPageTemplateSettingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}