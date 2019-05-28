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

package com.liferay.asset.categories.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.exception.AssetCategoryNameException;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 * @author Rubén Pulido
 */
@RunWith(Arquillian.class)
@Sync
public class AssetCategoryLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_assetVocabulary = _assetVocabularyLocalService.addDefaultVocabulary(
			_group.getGroupId());
	}

	@Test
	public void testAddCategoryWithNoLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		String title = RandomTestUtil.randomString(20);

		AssetCategory assetCategory = _addCategory(title);

		Map<Locale, String> titleMap = assetCategory.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Test
	public void testAddCategoryWithNonsiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		expectedException.expect(AssetCategoryNameException.class);
		expectedException.expectMessage(
			"Category name cannot be null for category 0 and vocabulary " +
				_assetVocabulary.getVocabularyId());

		_addCategory(RandomTestUtil.randomString(20), LocaleUtil.CHINA);
	}

	@Test
	public void testAddCategoryWithSiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		String title = RandomTestUtil.randomString(20);

		AssetCategory assetCategory = _addCategory(title, locale);

		Map<Locale, String> titleMap = assetCategory.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Test
	public void testUpdateCategoryWithSiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		AssetCategory assetCategory = _addCategory(
			RandomTestUtil.randomString(20), locale);

		String title = RandomTestUtil.randomString(20);

		AssetCategory updatedAssetCategory = _updateCategory(
			assetCategory.getCategoryId(), title, locale);

		Map<Locale, String> titleMap = updatedAssetCategory.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private AssetCategory _addCategory(String title) throws Exception {
		return _assetCategoryLocalService.addCategory(
			TestPropsValues.getUserId(), _group.getGroupId(), title,
			_assetVocabulary.getVocabularyId(), new ServiceContext());
	}

	private AssetCategory _addCategory(String title, Locale locale)
		throws Exception {

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(locale, title);

		return _assetCategoryLocalService.addCategory(
			TestPropsValues.getUserId(), _group.getGroupId(), 0, titleMap,
			titleMap, _assetVocabulary.getVocabularyId(), null,
			new ServiceContext());
	}

	private AssetCategory _updateCategory(
			long categoryId, String title, Locale locale)
		throws Exception {

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(locale, title);

		return _assetCategoryLocalService.updateCategory(
			TestPropsValues.getUserId(), categoryId, 0, titleMap, titleMap,
			_assetVocabulary.getVocabularyId(), null, new ServiceContext());
	}

	@Inject
	private AssetCategoryLocalService _assetCategoryLocalService;

	private AssetVocabulary _assetVocabulary;

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@DeleteAfterTestRun
	private Group _group;

}