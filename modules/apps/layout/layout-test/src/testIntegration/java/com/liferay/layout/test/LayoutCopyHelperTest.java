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

package com.liferay.layout.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.layout.util.LayoutCopyHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Collections;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pavel Savinov
 */
@RunWith(Arquillian.class)
public class LayoutCopyHelperTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testCopyLayoutFragmentEntryLinks() throws Exception {
		Layout sourceLayout = addLayout(_group.getGroupId(), StringPool.BLANK);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		_fragmentEntryLinkLocalService.addFragmentEntryLink(
			sourceLayout.getUserId(), sourceLayout.getGroupId(), 0,
			_portal.getClassNameId(Layout.class), sourceLayout.getPlid(),
			StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, 0, serviceContext);

		Layout targetLayout = addLayout(_group.getGroupId(), StringPool.BLANK);

		Assert.assertTrue(
			ListUtil.isNotEmpty(
				_fragmentEntryLinkLocalService.getFragmentEntryLinks(
					_group.getGroupId(), _portal.getClassNameId(Layout.class),
					sourceLayout.getPlid())));

		Assert.assertFalse(
			ListUtil.isNotEmpty(
				_fragmentEntryLinkLocalService.getFragmentEntryLinks(
					_group.getGroupId(), _portal.getClassNameId(Layout.class),
					targetLayout.getPlid())));

		_layoutCopyHelper.copyLayout(sourceLayout, targetLayout);

		Assert.assertTrue(
			ListUtil.isNotEmpty(
				_fragmentEntryLinkLocalService.getFragmentEntryLinks(
					_group.getGroupId(), _portal.getClassNameId(Layout.class),
					targetLayout.getPlid())));
	}

	@Test
	public void testCopyLayoutLookAndFeel() throws Exception {
		Layout sourceLayout = addLayout(_group.getGroupId(), StringPool.BLANK);

		sourceLayout.setThemeId("l1-theme");
		sourceLayout.setCss("l1-css");

		LayoutLocalServiceUtil.updateLayout(sourceLayout);

		Layout targetLayout = addLayout(_group.getGroupId(), StringPool.BLANK);

		Assert.assertNotEquals(
			sourceLayout.getThemeId(), targetLayout.getThemeId());

		Assert.assertNotEquals(sourceLayout.getCss(), targetLayout.getCss());

		targetLayout = _layoutCopyHelper.copyLayout(sourceLayout, targetLayout);

		Assert.assertEquals(
			sourceLayout.getThemeId(), targetLayout.getThemeId());

		Assert.assertEquals(sourceLayout.getCss(), targetLayout.getCss());
	}

	@Test
	public void testCopyLayoutPortletPreferences() throws Exception {
		Layout sourceLayout = addLayout(
			_group.getGroupId(), "column-1=portlet-id");

		PortletPreferences sourcePortletPreferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				sourceLayout, "portlet-id",
				"<portlet-preferences><layout1/></portlet-preferences>");

		Layout targetLayout = addLayout(_group.getGroupId(), StringPool.BLANK);

		PortletPreferences targetPortletPreferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				targetLayout, "portlet-id");

		Assert.assertNotEquals(
			PortletPreferencesFactoryUtil.toXML(targetPortletPreferences),
			PortletPreferencesFactoryUtil.toXML(sourcePortletPreferences));

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		_layoutCopyHelper.copyLayout(sourceLayout, targetLayout);

		targetPortletPreferences =
			PortletPreferencesFactoryUtil.getLayoutPortletSetup(
				targetLayout, "portlet-id");

		Assert.assertEquals(
			PortletPreferencesFactoryUtil.toXML(sourcePortletPreferences),
			PortletPreferencesFactoryUtil.toXML(targetPortletPreferences));
	}

	protected Layout addLayout(long groupId, String typeSettings)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return _layoutLocalService.addLayout(
			TestPropsValues.getUserId(), groupId, false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			LayoutConstants.TYPE_PORTLET, typeSettings, false,
			Collections.emptyMap(), serviceContext);
	}

	@Inject
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutCopyHelper _layoutCopyHelper;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private Portal _portal;

}