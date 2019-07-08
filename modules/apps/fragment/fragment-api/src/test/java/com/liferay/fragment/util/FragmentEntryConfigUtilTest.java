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

package com.liferay.fragment.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.util.FileImpl;
import com.liferay.segments.constants.SegmentsConstants;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Rubén Pulido
 */
public class FragmentEntryConfigUtilTest {

	@BeforeClass
	public static void setUpClass() {
		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Test
	public void testGetConfigurationDefaultValuesJSONObject() throws Exception {
		JSONObject configurationDefaultValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationDefaultValuesJSONObject(
				_getFileContent("configuration.json"));

		JSONObject expectedConfigurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		Assert.assertEquals(
			expectedConfigurationDefaultValuesJSONObject.toJSONString(),
			configurationDefaultValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectBlank() throws Exception {
		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"), StringPool.BLANK,
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectConfigBlank()
		throws Exception {

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				StringPool.BLANK,
				_getFileContent("editable-values-default.json"),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals("{}", configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectConfigNull()
		throws Exception {

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				null, _getFileContent("editable-values-default.json"),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals("{}", configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectDefault() throws Exception {
		String fileName = "editable-values-default.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectEmtpy() throws Exception {
		String fileName = "editable-values-empty.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldInvalidDataType()
		throws Exception {

		String fileName = "editable-values-field-invalid-data-type.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldInvalidName()
		throws Exception {

		String fileName = "editable-values-field-invalid-name.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldInvalidOption()
		throws Exception {

		String fileName = "editable-values-field-invalid-option.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldMissingNondefaultInvalidOptionNullInvalidName()
		throws Exception {

		String fileName =
			"editable-values-field-missing-non-default-invalid-option-null-" +
				"invalid-name.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent(
					"expected-configuration-values-field-missing-non-default-" +
						"invalid-option-null-invalid-name.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldNondefault()
		throws Exception {

		String fileName = "editable-values-field-non-default.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent(
					"expected-configuration-values-field-non-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldNull()
		throws Exception {

		String fileName = "editable-values-field-null.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldSegmentExperienceId0Empty()
		throws Exception {

		String fileName = "editable-values-segments-experience-id-0-empty.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldSegmentExperienceIdInvalid()
		throws Exception {

		String fileName = "editable-values-default.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName), 10, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldSetEmpty()
		throws Exception {

		String fileName = "editable-values-fieldSet-empty.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldSetInvalidJSON()
		throws Exception {

		String fileName = "editable-values-fieldSet-invalid-json.json.txt";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFieldSetInvalidName()
		throws Exception {

		String fileName = "editable-values-fieldSet-invalid-name.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectFreeMarkerFragmentEntryProcessorEmpty()
		throws Exception {

		String fileName =
			"editable-values-FreeMarkerFragmentEntryProcessor-empty.json";

		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(fileName),
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetConfigurationValuesJSONObjectNull() throws Exception {
		JSONObject configurationDefaultValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-configuration-values-default.json"));

		JSONObject configurationValuesJSONObject =
			FragmentEntryConfigUtil.getConfigurationValuesJSONObject(
				_getFileContent("configuration.json"), null,
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT, true);

		Assert.assertEquals(
			configurationDefaultValuesJSONObject.toJSONString(),
			configurationValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetEditableValuesJSONObjectFieldMissingNondefaultInvalidOptionNullInvalidName()
		throws Exception {

		JSONObject editableValuesJSONObject =
			FragmentEntryConfigUtil.getEditableValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(
					"editable-values-field-missing-non-default-invalid-" +
						"option-null-invalid-name.json"),
				true);

		JSONObject expectedEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent(
					"expected-editable-values-field-missing-non-default-" +
						"invalid-option-null-invalid-name.json"));

		Assert.assertEquals(
			expectedEditableValuesJSONObject.toJSONString(),
			editableValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetEditableValuesJSONObjectFieldSegmentExperienceId0And1Empty()
		throws Exception {

		JSONObject editableValuesJSONObject =
			FragmentEntryConfigUtil.getEditableValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(
					"editable-values-segments-experience-id-0-1-empty.json"),
				true);

		JSONObject expectedEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent(
					"expected-editable-values-segments-experience-id-0-1-" +
						"default.json"));

		Assert.assertEquals(
			expectedEditableValuesJSONObject.toJSONString(),
			editableValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetEditableValuesJSONObjectFieldSegmentExperienceId0Empty()
		throws Exception {

		JSONObject editableValuesJSONObject =
			FragmentEntryConfigUtil.getEditableValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent(
					"editable-values-segments-experience-id-0-empty.json"),
				true);

		JSONObject expectedEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-editable-values-default.json"));

		Assert.assertEquals(
			expectedEditableValuesJSONObject.toJSONString(),
			editableValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetEditableValuesJSONObjectNondefault() throws Exception {
		JSONObject editableValuesJSONObject =
			FragmentEntryConfigUtil.getEditableValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent("editable-values-field-non-default.json"),
				true);

		JSONObject expectedEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent("expected-editable-values-non-default.json"));

		Assert.assertEquals(
			expectedEditableValuesJSONObject.toJSONString(),
			editableValuesJSONObject.toJSONString());
	}

	@Test
	public void testGetEditableValuesJSONObjectOtherProcessors()
		throws Exception {

		JSONObject editableValuesJSONObject =
			FragmentEntryConfigUtil.getEditableValuesJSONObject(
				_getFileContent("configuration.json"),
				_getFileContent("editable-values-other-processors.json"), true);

		JSONObject expectedEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject(
				_getFileContent(
					"expected-editable-values-other-processors.json"));

		Assert.assertEquals(
			expectedEditableValuesJSONObject.toJSONString(),
			editableValuesJSONObject.toJSONString());
	}

	private String _getFileContent(String fileName) throws Exception {
		return new String(
			FileUtil.getBytes(getClass(), "dependencies/" + fileName));
	}

}