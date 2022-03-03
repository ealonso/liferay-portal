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
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalService;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;

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
public class LayoutTypeContentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddLayoutTypeContent() throws Exception {
		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					_group.getGroupId(), layout.getPlid());

		Assert.assertNotNull(layoutPageTemplateStructure);

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.fetchSegmentsExperience(
				_group.getGroupId(), SegmentsExperienceConstants.KEY_DEFAULT,
				PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		Assert.assertNotNull(segmentsExperience);

		LayoutPageTemplateStructureRel layoutPageTemplateStructureRel =
			_layoutPageTemplateStructureRelLocalService.
				fetchLayoutPageTemplateStructureRel(
					layoutPageTemplateStructure.
						getLayoutPageTemplateStructureId(),
					segmentsExperience.getSegmentsExperienceId());

		Assert.assertNotNull(layoutPageTemplateStructureRel);
	}

	@Test
	public void testDeleteLayoutTypeContent() throws Exception {
		Layout layout = LayoutTestUtil.addTypeContentLayout(_group);

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					_group.getGroupId(), layout.getPlid());

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.fetchSegmentsExperience(
				_group.getGroupId(), SegmentsExperienceConstants.KEY_DEFAULT,
				PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		_layoutLocalService.deleteLayout(layout.getPlid());

		LayoutPageTemplateStructure deletedLayoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					_group.getGroupId(), layout.getPlid());

		Assert.assertNull(deletedLayoutPageTemplateStructure);

		int segmentsExperiencesCount =
			_segmentsExperienceLocalService.getSegmentsExperiencesCount(
				_group.getGroupId(), PortalUtil.getClassNameId(Layout.class),
				layout.getPlid());

		Assert.assertEquals(0, segmentsExperiencesCount);

		LayoutPageTemplateStructureRel deletedLayoutPageTemplateStructureRel =
			_layoutPageTemplateStructureRelLocalService.
				fetchLayoutPageTemplateStructureRel(
					layoutPageTemplateStructure.
						getLayoutPageTemplateStructureId(),
					segmentsExperience.getSegmentsExperienceId());

		Assert.assertNull(deletedLayoutPageTemplateStructureRel);
	}

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private LayoutLocalService _layoutLocalService;

	@Inject
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

	@Inject
	private LayoutPageTemplateStructureRelLocalService
		_layoutPageTemplateStructureRelLocalService;

	@Inject
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

}