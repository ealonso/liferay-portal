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

package com.liferay.asset.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.exception.VocabularyNameException;
import com.liferay.asset.kernel.model.AssetVocabulary;
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
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
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
public class AssetVocabularyLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddDefaultVocabulary() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		AssetVocabulary assetVocabulary = _addDefaultVocabulary();

		Map<Locale, String> titleMap = assetVocabulary.getTitleMap();

		Assert.assertNotNull(titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Test
	public void testAddVocabularyWithNoLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		String title = RandomTestUtil.randomString(20);

		AssetVocabulary assetVocabulary = _addVocabulary(title);

		Map<Locale, String> titleMap = assetVocabulary.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Test
	public void testAddVocabularyWithNonsiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		expectedException.expect(VocabularyNameException.class);
		expectedException.expectMessage(
			"Category vocabulary name cannot be null for group " +
				_group.getGroupId());

		_addVocabulary(RandomTestUtil.randomString(20), LocaleUtil.CHINA);
	}

	@Test
	public void testAddVocabularyWithSiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		String title = RandomTestUtil.randomString(20);

		AssetVocabulary assetVocabulary = _addVocabulary(title, locale);

		Map<Locale, String> titleMap = assetVocabulary.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Test
	public void testUpdateVocabularyWithSiteDefaultLocale() throws Exception {
		Locale locale = LocaleUtil.SPAIN;

		GroupTestUtil.updateDisplaySettings(
			_group.getGroupId(), Collections.singletonList(locale), locale);

		AssetVocabulary assetVocabulary = _addVocabulary(
			RandomTestUtil.randomString(20), locale);

		String title = RandomTestUtil.randomString(20);

		AssetVocabulary updatedAssetVocabulary = _updateVocabulary(
			assetVocabulary.getVocabularyId(), title, locale);

		Map<Locale, String> titleMap = updatedAssetVocabulary.getTitleMap();

		Assert.assertEquals(title, titleMap.get(locale));
		Assert.assertNull(titleMap.get(LocaleUtil.US));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private AssetVocabulary _addDefaultVocabulary() throws Exception {
		return _assetVocabularyLocalService.addDefaultVocabulary(
			_group.getGroupId());
	}

	private AssetVocabulary _addVocabulary(String title) throws Exception {
		return _assetVocabularyLocalService.addVocabulary(
			TestPropsValues.getUserId(), _group.getGroupId(), title,
			new ServiceContext());
	}

	private AssetVocabulary _addVocabulary(String title, Locale locale)
		throws Exception {

		Map<Locale, String> titleMap = HashMapBuilder.put(
			locale, title
		).build();

		return _assetVocabularyLocalService.addVocabulary(
			TestPropsValues.getUserId(), _group.getGroupId(), null, titleMap,
			titleMap, null, new ServiceContext());
	}

	private AssetVocabulary _updateVocabulary(
			long vocabularyId, String title, Locale locale)
		throws Exception {

		Map<Locale, String> titleMap = HashMapBuilder.put(
			locale, title
		).build();

		return _assetVocabularyLocalService.updateVocabulary(
			vocabularyId, null, titleMap, titleMap, null, new ServiceContext());
	}

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@DeleteAfterTestRun
	private Group _group;

}