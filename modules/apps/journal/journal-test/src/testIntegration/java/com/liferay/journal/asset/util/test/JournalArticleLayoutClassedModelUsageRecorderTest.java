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

package com.liferay.journal.asset.util.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.journal.constants.JournalFolderConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.test.util.JournalTestUtil;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.layout.util.LayoutClassedModelUsageRecorder;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import javax.portlet.PortletPreferences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eudaldo Alonso
 */
@RunWith(Arquillian.class)
public class JournalArticleLayoutClassedModelUsageRecorderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_article = JournalTestUtil.addArticle(
			_group.getGroupId(),
			JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		AssetEntry assetEntry = _assetEntryLocalService.getEntry(
			JournalArticle.class.getName(), _article.getResourcePrimKey());

		for (int i = 1; i <= _LAYOUTS_AND_ASSET_PUBLISHERS_COUNT; i++) {
			_addAssetPublisherWithPreferences(assetEntry);
		}

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(_group.getGroupId()));
	}

	@After
	public void tearDown() {
		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testCheckJournalArticleLayoutClassedModelUsages()
		throws Exception {

		long classNameId = _portal.getClassNameId(
			JournalArticle.class.getName());

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			_journalArticleLayoutClassedModelUsageRecorder.record(
				classNameId, _article.getResourcePrimKey());
		}

		Assert.assertEquals(
			_LAYOUTS_AND_ASSET_PUBLISHERS_COUNT,
			_layoutClassedModelUsageLocalService.
				getLayoutClassedModelUsagesCount(
					classNameId, _article.getResourcePrimKey()));
	}

	private void _addAssetPublisherWithPreferences(AssetEntry assetEntry)
		throws Exception {

		Layout layout = LayoutTestUtil.addTypePortletLayout(
			_group.getGroupId());

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		String portletId = layoutTypePortlet.addPortletId(
			TestPropsValues.getUserId(),
			AssetPublisherPortletKeys.ASSET_PUBLISHER, "column-1", -1);

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		PortletPreferences layoutPortletPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				layout, portletId, null);

		layoutPortletPreferences.setValue("selectionStyle", "manual");
		layoutPortletPreferences.setValue(
			"assetLinkBehavior", "showFullContent");
		layoutPortletPreferences.setValue("displayStyle", "full-content");
		layoutPortletPreferences.setValue("showAvailableLocales", "true");
		layoutPortletPreferences.setValues(
			"assetEntryXml", _getAssetEntryXml(assetEntry));

		layoutPortletPreferences.store();
	}

	private String _getAssetEntryXml(AssetEntry assetEntry) {
		StringBundler sb = new StringBundler(6);

		sb.append("<?xml version=\"1.0\"?><asset-entry>");
		sb.append("<asset-entry-type>");
		sb.append(assetEntry.getClassName());
		sb.append("</asset-entry-type><asset-entry-uuid>");
		sb.append(assetEntry.getClassUuid());
		sb.append("</asset-entry-uuid></asset-entry>");

		return sb.toString();
	}

	private static final int _LAYOUTS_AND_ASSET_PUBLISHERS_COUNT = 15000;

	private JournalArticle _article;

	@Inject
	private AssetEntryLocalService _assetEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject(
		filter = "model.class.name=com.liferay.journal.model.JournalArticle"
	)
	private LayoutClassedModelUsageRecorder
		_journalArticleLayoutClassedModelUsageRecorder;

	@Inject
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private Portal _portal;

}