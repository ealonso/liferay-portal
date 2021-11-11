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

package com.liferay.layout.content.page.editor.web.internal.portlet.action.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalService;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionResponse;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;

import java.util.Collections;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
public class UpdateSegmentsExperiencePriorityMVCActionCommandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), TestPropsValues.getUserId());

		ServiceContextThreadLocal.pushServiceContext(_serviceContext);

		_layout = _addLayout();
	}

	@After
	public void tearDown() {
		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testUpdateSegmentsExperiencePriority() throws Exception {
		SegmentsExperience segmentsExperienceAVariationA =
			_addSegmentsExperience(0, "Experience A Variation A");
		SegmentsExperience segmentsExperienceAVariationB =
			_addSegmentsExperience(1, "Experience A Variation B");
		SegmentsExperience segmentsExperienceBVariationA =
			_addSegmentsExperience(0, "Experience B Variation A");

		_addSegmentsExperience(0, "Experience C Variation A");

		SegmentsExperience segmentsExperienceBVariationB =
			_addSegmentsExperience(1, "Experience B Variation B");

		_addSegmentsExperience(0, "Experience D Variation A");

		_increaseSegmentsExperiencePriority(
			segmentsExperienceBVariationA.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-2, segmentsExperienceBVariationA.getSegmentsExperienceId());

		_increaseSegmentsExperiencePriority(
			segmentsExperienceAVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			0, segmentsExperienceAVariationB.getSegmentsExperienceId());

		_increaseSegmentsExperiencePriority(
			segmentsExperienceAVariationA.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-2, segmentsExperienceAVariationA.getSegmentsExperienceId());

		_increaseSegmentsExperiencePriority(
			segmentsExperienceBVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			0, segmentsExperienceBVariationB.getSegmentsExperienceId());

		_decreaseSegmentsExperiencePriority(
			segmentsExperienceAVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			0, segmentsExperienceAVariationB.getSegmentsExperienceId());

		_decreaseSegmentsExperiencePriority(
			segmentsExperienceAVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-2, segmentsExperienceAVariationB.getSegmentsExperienceId());

		_decreaseSegmentsExperiencePriority(
			segmentsExperienceAVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-6, segmentsExperienceAVariationB.getSegmentsExperienceId());

		_decreaseSegmentsExperiencePriority(
			segmentsExperienceBVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-2, segmentsExperienceBVariationB.getSegmentsExperienceId());

		_decreaseSegmentsExperiencePriority(
			segmentsExperienceBVariationB.getSegmentsExperienceId());

		_assertSegmentsExperiencePriority(
			-7, segmentsExperienceBVariationB.getSegmentsExperienceId());
	}

	private Layout _addLayout() throws Exception {
		Layout layout = _layoutLocalService.addLayout(
			TestPropsValues.getUserId(), _group.getGroupId(), false,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			StringPool.BLANK, LayoutConstants.TYPE_CONTENT, false,
			StringPool.BLANK, _serviceContext);

		_layoutPageTemplateStructureLocalService.addLayoutPageTemplateStructure(
			TestPropsValues.getUserId(), _group.getGroupId(), layout.getPlid(),
			_getDefaultData(), _serviceContext);

		Layout draftLayout = layout.fetchDraftLayout();

		_layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				addLayoutPageTemplateStructure(
					TestPropsValues.getUserId(), _group.getGroupId(),
					draftLayout.getPlid(), _getDefaultData(), _serviceContext);

		return layout;
	}

	private SegmentsExperience _addSegmentsExperience(
			long layoutSetBranchId, String name)
		throws Exception {

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.addSegmentsExperience(
				SegmentsEntryConstants.ID_DEFAULT,
				_classNameLocalService.getClassNameId(Layout.class.getName()),
				_layout.getPlid(),
				Collections.singletonMap(LocaleUtil.getSiteDefault(), name),
				true, layoutSetBranchId, new UnicodeProperties(true),
				_serviceContext);

		_layoutPageTemplateStructureRelLocalService.
			addLayoutPageTemplateStructureRel(
				TestPropsValues.getUserId(), _group.getGroupId(),
				_layoutPageTemplateStructure.getLayoutPageTemplateStructureId(),
				segmentsExperience.getSegmentsExperienceId(), _getDefaultData(),
				_serviceContext);

		return segmentsExperience;
	}

	private void _assertSegmentsExperiencePriority(
		int priority, long segmentsExperienceId) {

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.fetchSegmentsExperience(
				segmentsExperienceId);

		Assert.assertEquals(priority, segmentsExperience.getPriority());
	}

	private void _decreaseSegmentsExperiencePriority(long segmentsExperienceId)
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			_getMockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.addParameter(
			"segmentsExperienceId", String.valueOf(segmentsExperienceId));
		mockLiferayPortletActionRequest.addParameter("direction", "down");

		ReflectionTestUtil.invoke(
			_mvcActionCommand, "doTransactionalCommand",
			new Class<?>[] {ActionRequest.class, ActionResponse.class},
			mockLiferayPortletActionRequest,
			new MockLiferayPortletActionResponse());
	}

	private String _getDefaultData() {
		LayoutStructure layoutStructure = new LayoutStructure();

		layoutStructure.addRootLayoutStructureItem();

		return layoutStructure.toString();
	}

	private MockLiferayPortletActionRequest
			_getMockLiferayPortletActionRequest()
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			new MockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay());

		return mockLiferayPortletActionRequest;
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		Layout draftLayout = _layout.fetchDraftLayout();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(_group.getCompanyId()));
		themeDisplay.setLayout(draftLayout);
		themeDisplay.setLayoutSet(draftLayout.getLayoutSet());
		themeDisplay.setLocale(LocaleUtil.US);
		themeDisplay.setPlid(draftLayout.getPlid());
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	private void _increaseSegmentsExperiencePriority(long segmentsExperienceId)
		throws Exception {

		MockLiferayPortletActionRequest mockLiferayPortletActionRequest =
			_getMockLiferayPortletActionRequest();

		mockLiferayPortletActionRequest.addParameter(
			"segmentsExperienceId", String.valueOf(segmentsExperienceId));
		mockLiferayPortletActionRequest.addParameter("direction", "up");

		ReflectionTestUtil.invoke(
			_mvcActionCommand, "doTransactionalCommand",
			new Class<?>[] {ActionRequest.class, ActionResponse.class},
			mockLiferayPortletActionRequest,
			new MockLiferayPortletActionResponse());
	}

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private Layout _layout;

	@Inject
	private LayoutLocalService _layoutLocalService;

	private LayoutPageTemplateStructure _layoutPageTemplateStructure;

	@Inject
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Inject
	private LayoutPageTemplateStructureRelLocalService
		_layoutPageTemplateStructureRelLocalService;

	@Inject(
		filter = "mvc.command.name=/layout_content_page_editor/update_segments_experience_priority"
	)
	private MVCActionCommand _mvcActionCommand;

	@Inject
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	private ServiceContext _serviceContext;

}