/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.layout.seo.service.LayoutSEOEntryCustomMetaTagLocalService;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;
import java.util.List;

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
public class LayoutSEOEntryCustomMetaTagLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddAndDeleteLayoutSEOEntryCustomMetaTags()
		throws Exception {

		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		LayoutSEOEntry layoutSEOEntry =
			_layoutSEOEntryLocalService.updateLayoutSEOEntry(
				TestPropsValues.getUserId(), _group.getGroupId(), false,
				layout.getLayoutId(), false,
				Collections.singletonMap(LocaleUtil.US, "http://example.com"),
				true, Collections.singletonMap(LocaleUtil.US, "description"),
				Collections.singletonMap(LocaleUtil.US, "image alt"), 12345,
				true, Collections.singletonMap(LocaleUtil.US, "title"),
				ServiceContextTestUtil.getServiceContext(_group.getGroupId()));

		_layoutSEOEntryCustomMetaTagLocalService.addLayoutSEOEntryCustomMetaTag(
			layout.getGroupId(), layoutSEOEntry.getLayoutSEOEntryId(),
			"property1",
			Collections.singletonMap(LocaleUtil.getSiteDefault(), "content1"));
		_layoutSEOEntryCustomMetaTagLocalService.addLayoutSEOEntryCustomMetaTag(
			layout.getGroupId(), layoutSEOEntry.getLayoutSEOEntryId(),
			"property2",
			Collections.singletonMap(LocaleUtil.getSiteDefault(), "content2"));

		_assertCustomMetaTags(layoutSEOEntry);

		_layoutSEOEntryCustomMetaTagLocalService.
			deleteLayoutSEOEntryCustomMetaTags(
				layoutSEOEntry.getGroupId(),
				layoutSEOEntry.getLayoutSEOEntryId());

		List<LayoutSEOEntryCustomMetaTag> layoutSEOEntryCustomMetaTags =
			_layoutSEOEntryCustomMetaTagLocalService.
				getLayoutSEOEntryCustomMetaTags(
					layoutSEOEntry.getGroupId(),
					layoutSEOEntry.getLayoutSEOEntryId());

		Assert.assertTrue(layoutSEOEntryCustomMetaTags.isEmpty());
	}

	private void _assertCustomMetaTags(LayoutSEOEntry layoutSEOEntry) {
		List<LayoutSEOEntryCustomMetaTag> layoutSEOEntryCustomMetaTags =
			_layoutSEOEntryCustomMetaTagLocalService.
				getLayoutSEOEntryCustomMetaTags(
					layoutSEOEntry.getGroupId(),
					layoutSEOEntry.getLayoutSEOEntryId());

		Assert.assertFalse(layoutSEOEntryCustomMetaTags.isEmpty());
		Assert.assertEquals(
			layoutSEOEntryCustomMetaTags.toString(), 2,
			layoutSEOEntryCustomMetaTags.size());

		LayoutSEOEntryCustomMetaTag firstLayoutSEOEntryCustomMetaTag =
			layoutSEOEntryCustomMetaTags.get(0);

		Assert.assertEquals(
			"property1", firstLayoutSEOEntryCustomMetaTag.getProperty());
		Assert.assertEquals(
			"content1",
			firstLayoutSEOEntryCustomMetaTag.getContent(
				LocaleUtil.getSiteDefault()));

		LayoutSEOEntryCustomMetaTag secondLayoutSEOEntryCustomMetaTag =
			layoutSEOEntryCustomMetaTags.get(1);

		Assert.assertEquals(
			"property2", secondLayoutSEOEntryCustomMetaTag.getProperty());
		Assert.assertEquals(
			"content2",
			secondLayoutSEOEntryCustomMetaTag.getContent(
				LocaleUtil.getSiteDefault()));
	}

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutSEOEntryCustomMetaTagLocalService
		_layoutSEOEntryCustomMetaTagLocalService;

	@Inject
	private LayoutSEOEntryLocalService _layoutSEOEntryLocalService;

}