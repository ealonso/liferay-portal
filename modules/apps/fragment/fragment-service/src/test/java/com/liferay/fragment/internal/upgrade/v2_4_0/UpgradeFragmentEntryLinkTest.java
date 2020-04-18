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

package com.liferay.fragment.internal.upgrade.v2_4_0;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.util.FileImpl;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeFragmentEntryLinkTest {

	@Before
	public void setUp() {
		new FileUtil().setFile(new FileImpl());

		_upgradeFragmentEntryLink = new UpgradeFragmentEntryLink();

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Test
	public void testGetBackGroundImagesEditableValues() throws Exception {
		String editableValues = _read(
			"fragment_entry_link_background_images_editable_values.json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_background_images_editable_values_" +
						"segments_experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_background_images_editable_values_" +
						"segments_experience_1.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 1));
	}

	@Test
	public void testGetEditableEditableValues() throws Exception {
		String editableValues = _read(
			"fragment_entry_link_editable_editable_values.json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_editable_editable_values_segments_" +
						"experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_editable_editable_values_segments_" +
						"experience_1.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 1));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_editable_editable_values_segments_" +
						"experience_2.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 2));
	}

	@Test
	public void testGetEditableValuesWithoutDefaultExperience()
		throws Exception {

		String editableValues = _read(
			"fragment_entry_link_editable_values_without_default_experience." +
				"json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_editable_values_without_default_" +
						"experience_segments_experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_editable_values_without_default_" +
						"experience_segments_experience_2.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 2));
	}

	@Test
	public void testGetFreeMarkerEditableValues() throws Exception {
		String editableValues = _read(
			"fragment_entry_link_free_marker_editable_values.json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_free_marker_editable_values_" +
						"segments_experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_free_marker_editable_values_" +
						"segments_experience_1.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 1));

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_free_marker_editable_values_" +
						"segments_experience_2.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 2));
	}

	@Test
	public void testGetMappedEditableValues() throws Exception {
		String editableValues = _read(
			"fragment_entry_link_mapped_editable_values.json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_mapped_editable_values_segments_" +
						"experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));
	}

	@Test
	public void testGetPortletEditableValues() throws Exception {
		String editableValues = _read(
			"fragment_entry_link_portlet_editable_values.json");

		Assert.assertEquals(
			_toString(
				_read(
					"fragment_entry_link_portlet_editable_values_segments_" +
						"experience_0.json")),
			_upgradeFragmentEntryLink.getEditableValues(editableValues, 0));
	}

	@Test
	public void testGetSegmentsExperienceIds() throws Exception {
		Long[] segmentsExperienceIds =
			_upgradeFragmentEntryLink.getSegmentsExperienceIds(
				_read("fragment_entry_link_editable_editable_values.json"));

		Assert.assertEquals(
			Arrays.toString(segmentsExperienceIds), 3,
			segmentsExperienceIds.length);

		Arrays.sort(segmentsExperienceIds);

		Assert.assertArrayEquals(
			Arrays.toString(segmentsExperienceIds), new Long[] {0L, 1L, 2L},
			segmentsExperienceIds);
	}

	private String _read(String fileName) throws Exception {
		return new String(
			FileUtil.getBytes(getClass(), "dependencies/" + fileName));
	}

	private String _toString(String editableValues) throws Exception {
		JSONObject editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
			editableValues);

		return editableValuesJSONObject.toJSONString();
	}

	private UpgradeFragmentEntryLink _upgradeFragmentEntryLink;

}