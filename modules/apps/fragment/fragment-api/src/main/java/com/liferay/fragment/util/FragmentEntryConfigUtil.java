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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsConstants;

import java.util.List;

/**
 * @author Rubén Pulido
 */
public class FragmentEntryConfigUtil {

	public static JSONObject getConfigurationDefaultValuesJSONObject(
		String configuration) {

		JSONObject defaultValuesJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray fieldSetsJSONArray = _getFieldSetsJSONArray(configuration);

		if (fieldSetsJSONArray == null) {
			return null;
		}

		for (int i = 0; i < fieldSetsJSONArray.length(); i++) {
			JSONObject configurationFieldSetJSONObject =
				fieldSetsJSONArray.getJSONObject(i);

			JSONObject defaultValuesFieldSetJSONObject =
				JSONFactoryUtil.createJSONObject();

			JSONArray configurationFieldSetFieldsJSONArray =
				configurationFieldSetJSONObject.getJSONArray("fields");

			for (int j = 0; j < configurationFieldSetFieldsJSONArray.length();
				 j++) {

				JSONObject configurationFieldSetFieldJSONObject =
					configurationFieldSetFieldsJSONArray.getJSONObject(j);

				Object fieldDefaultValue = _getFieldValue(
					configurationFieldSetFieldJSONObject.getString("dataType"),
					configurationFieldSetFieldJSONObject.getString(
						"defaultValue"));

				defaultValuesFieldSetJSONObject.put(
					configurationFieldSetFieldJSONObject.getString("name"),
					fieldDefaultValue);
			}

			defaultValuesJSONObject.put(
				configurationFieldSetJSONObject.getString("name"),
				defaultValuesFieldSetJSONObject);
		}

		return defaultValuesJSONObject;
	}

	public static JSONObject getConfigurationValuesJSONObject(
		String configuration, String editableValues, long segmentsExperienceId,
		boolean includeDefault) {

		try {
			JSONFactoryUtil.createJSONObject(configuration);
		}
		catch (JSONException jsone) {
			_log.error(
				"Unable to parse configuration JSON object: " + configuration,
				jsone);

			return null;
		}

		JSONObject configurationDefaultValuesJSONObject =
			getConfigurationDefaultValuesJSONObject(configuration);

		if (configurationDefaultValuesJSONObject == null) {
			configurationDefaultValuesJSONObject =
				JSONFactoryUtil.createJSONObject();
		}

		JSONObject configurationDataTypesJSONObject =
			_getConfigurationDataTypesJSONObject(configuration);

		JSONObject configurationValidValuesJSONObject =
			_getConfigurationValidValuesJSONObject(configuration);

		JSONObject editableValuesJSONObject = _getEditableValuesJSONObject(
			editableValues);

		JSONObject freemarkerFragmentEntryProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME);

		if (Validator.isNull(
				freemarkerFragmentEntryProcessorJSONObject.getJSONObject(
					SegmentsConstants.SEGMENTS_EXPERIENCE_ID_PREFIX +
						segmentsExperienceId))) {

			freemarkerFragmentEntryProcessorJSONObject.put(
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_PREFIX +
					segmentsExperienceId,
				JSONFactoryUtil.createJSONObject());
		}

		JSONObject configurationValuesJSONObject =
			freemarkerFragmentEntryProcessorJSONObject.getJSONObject(
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_PREFIX +
					segmentsExperienceId);

		return _getConfigurationValuesJSONObject(
			configurationDefaultValuesJSONObject,
			configurationDataTypesJSONObject,
			configurationValidValuesJSONObject, configurationValuesJSONObject,
			includeDefault);
	}

	public static JSONObject getEditableValuesJSONObject(
		String configuration, String editableValues, boolean includeDefault) {

		try {
			JSONFactoryUtil.createJSONObject(configuration);
		}
		catch (JSONException jsone) {
			_log.error(
				"Unable to parse configuration JSON object: " + configuration,
				jsone);

			return null;
		}

		JSONObject outputEditableValuesJSONObject =
			JSONFactoryUtil.createJSONObject();

		JSONObject configurationDefaultValuesJSONObject =
			getConfigurationDefaultValuesJSONObject(configuration);

		if (configurationDefaultValuesJSONObject == null) {
			configurationDefaultValuesJSONObject =
				JSONFactoryUtil.createJSONObject();
		}

		JSONObject configurationDataTypesJSONObject =
			_getConfigurationDataTypesJSONObject(configuration);

		JSONObject configurationValidValuesJSONObject =
			_getConfigurationValidValuesJSONObject(configuration);

		JSONObject editableValuesJSONObject = _getEditableValuesJSONObject(
			editableValues);

		for (String processorKey : editableValuesJSONObject.keySet()) {
			if (processorKey.equals(
					_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME)) {

				outputEditableValuesJSONObject.put(
					processorKey, JSONFactoryUtil.createJSONObject());
			}
			else {
				outputEditableValuesJSONObject.put(
					processorKey, editableValuesJSONObject.get(processorKey));
			}
		}

		JSONObject outputFreemarkerFragmentEntryProcessorJSONObject =
			JSONFactoryUtil.createJSONObject();

		JSONObject freemarkerFragmentEntryProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME);

		if (Validator.isNull(
				freemarkerFragmentEntryProcessorJSONObject.getJSONObject(
					SegmentsConstants.SEGMENTS_EXPERIENCE_ID_PREFIX +
						SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT))) {

			freemarkerFragmentEntryProcessorJSONObject.put(
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_PREFIX +
					SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT,
				JSONFactoryUtil.createJSONObject());
		}

		for (String segmentsExperienceKey :
				freemarkerFragmentEntryProcessorJSONObject.keySet()) {

			if (Validator.isNull(
					freemarkerFragmentEntryProcessorJSONObject.getJSONObject(
						segmentsExperienceKey))) {

				freemarkerFragmentEntryProcessorJSONObject.put(
					segmentsExperienceKey, JSONFactoryUtil.createJSONObject());
			}

			JSONObject configurationValuesJSONObject =
				freemarkerFragmentEntryProcessorJSONObject.getJSONObject(
					segmentsExperienceKey);

			outputFreemarkerFragmentEntryProcessorJSONObject.put(
				segmentsExperienceKey,
				_getConfigurationValuesJSONObject(
					configurationDefaultValuesJSONObject,
					configurationDataTypesJSONObject,
					configurationValidValuesJSONObject,
					configurationValuesJSONObject, includeDefault));
		}

		outputEditableValuesJSONObject.put(
			_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME,
			outputFreemarkerFragmentEntryProcessorJSONObject);

		return outputEditableValuesJSONObject;
	}

	private static JSONObject _getConfigurationDataTypesJSONObject(
		String configuration) {

		JSONObject dataTypesJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray fieldSetsJSONArray = _getFieldSetsJSONArray(configuration);

		if (fieldSetsJSONArray == null) {
			return null;
		}

		for (int i = 0; i < fieldSetsJSONArray.length(); i++) {
			JSONObject configurationFieldSetJSONObject =
				fieldSetsJSONArray.getJSONObject(i);

			JSONObject dataTypesFieldSetJSONObject =
				JSONFactoryUtil.createJSONObject();

			JSONArray configurationFieldSetFieldsJSONArray =
				configurationFieldSetJSONObject.getJSONArray("fields");

			for (int j = 0; j < configurationFieldSetFieldsJSONArray.length();
				 j++) {

				JSONObject configurationFieldSetFieldJSONObject =
					configurationFieldSetFieldsJSONArray.getJSONObject(j);

				String fieldDataType =
					configurationFieldSetFieldJSONObject.getString("dataType");

				dataTypesFieldSetJSONObject.put(
					configurationFieldSetFieldJSONObject.getString("name"),
					fieldDataType);
			}

			dataTypesJSONObject.put(
				configurationFieldSetJSONObject.getString("name"),
				dataTypesFieldSetJSONObject);
		}

		return dataTypesJSONObject;
	}

	private static JSONObject _getConfigurationValidValuesJSONObject(
		String configuration) {

		JSONObject dataTypesJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray fieldSetsJSONArray = _getFieldSetsJSONArray(configuration);

		if (fieldSetsJSONArray == null) {
			return null;
		}

		for (int i = 0; i < fieldSetsJSONArray.length(); i++) {
			JSONObject configurationFieldSetJSONObject =
				fieldSetsJSONArray.getJSONObject(i);

			JSONObject validValuesFieldSetJSONObject =
				JSONFactoryUtil.createJSONObject();

			JSONArray configurationFieldSetFieldsJSONArray =
				configurationFieldSetJSONObject.getJSONArray("fields");

			for (int j = 0; j < configurationFieldSetFieldsJSONArray.length();
				 j++) {

				JSONObject configurationFieldSetFieldJSONObject =
					configurationFieldSetFieldsJSONArray.getJSONObject(j);

				JSONObject fieldTypeOptionsJSONObject =
					configurationFieldSetFieldJSONObject.getJSONObject(
						"typeOptions");

				validValuesFieldSetJSONObject.put(
					configurationFieldSetFieldJSONObject.getString("name"),
					fieldTypeOptionsJSONObject.getJSONArray("validValues"));
			}

			dataTypesJSONObject.put(
				configurationFieldSetJSONObject.getString("name"),
				validValuesFieldSetJSONObject);
		}

		return dataTypesJSONObject;
	}

	private static JSONObject _getConfigurationValuesJSONObject(
		JSONObject configurationDefaultValuesJSONObject,
		JSONObject configurationDataTypesJSONObject,
		JSONObject configurationValidValuesJSONObject,
		JSONObject configurationValuesJSONObject, boolean includeDefault) {

		JSONObject outputConfigurationValuesJSONObject =
			JSONFactoryUtil.createJSONObject();

		for (String fieldSetName :
				configurationDefaultValuesJSONObject.keySet()) {

			JSONObject outputFieldSetValuesJSONObject =
				JSONFactoryUtil.createJSONObject();

			outputConfigurationValuesJSONObject.put(
				fieldSetName, outputFieldSetValuesJSONObject);

			if (Validator.isNull(
					configurationValuesJSONObject.get(fieldSetName))) {

				configurationValuesJSONObject.put(
					fieldSetName, JSONFactoryUtil.createJSONObject());
			}

			JSONObject defaultValuesFieldSetJSONObject =
				configurationDefaultValuesJSONObject.getJSONObject(
					fieldSetName);

			JSONObject fieldSetValuesJSONObject =
				configurationValuesJSONObject.getJSONObject(fieldSetName);

			for (String fieldKey : defaultValuesFieldSetJSONObject.keySet()) {
				Object value = fieldSetValuesJSONObject.get(fieldKey);

				JSONObject configurationDataTypesFieldSetJSONObject =
					configurationDataTypesJSONObject.getJSONObject(
						fieldSetName);

				JSONObject configurationValidValuesFieldSetJSONObject =
					configurationValidValuesJSONObject.getJSONObject(
						fieldSetName);

				JSONArray configurationValidValuesFieldSetFieldJSONArray =
					configurationValidValuesFieldSetJSONObject.getJSONArray(
						fieldKey);

				List<String> configurationValidValuesFieldSetFieldValues =
					JSONUtil.toStringList(
						configurationValidValuesFieldSetFieldJSONArray);

				if (Validator.isNull(fieldSetValuesJSONObject.get(fieldKey)) ||
					!_hasDataType(
						configurationDataTypesFieldSetJSONObject.getString(
							fieldKey),
						value) ||
					!configurationValidValuesFieldSetFieldValues.contains(
						String.valueOf(value)) ||
					value.equals(
						defaultValuesFieldSetJSONObject.get(fieldKey))) {

					if (includeDefault) {
						outputFieldSetValuesJSONObject.put(
							fieldKey,
							defaultValuesFieldSetJSONObject.get(fieldKey));
					}
				}
				else {
					outputFieldSetValuesJSONObject.put(fieldKey, value);
				}
			}
		}

		return outputConfigurationValuesJSONObject;
	}

	private static JSONObject _getEditableValuesJSONObject(
		String editableValues) {

		JSONObject editableValuesJSONObject =
			JSONFactoryUtil.createJSONObject();

		try {
			editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
				editableValues);
		}
		catch (JSONException jsone) {
			_log.error(jsone, jsone);
		}

		if (Validator.isNull(
				editableValuesJSONObject.getJSONObject(
					_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME))) {

			editableValuesJSONObject.put(
				_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME,
				JSONFactoryUtil.createJSONObject());
		}

		return editableValuesJSONObject;
	}

	private static JSONArray _getFieldSetsJSONArray(String configuration) {
		JSONObject configurationJSONObject = JSONFactoryUtil.createJSONObject();

		try {
			configurationJSONObject = JSONFactoryUtil.createJSONObject(
				configuration);
		}
		catch (JSONException jsone) {
			_log.error(
				"Unable to parse configuration JSON object: " + configuration,
				jsone);
		}

		return configurationJSONObject.getJSONArray("fieldSets");
	}

	private static Object _getFieldValue(String dataType, String value) {
		if (dataType.equals("double")) {
			return GetterUtil.getDouble(value);
		}
		else if (dataType.equals("int")) {
			return GetterUtil.getInteger(value);
		}
		else if (dataType.equals("string")) {
			return value;
		}

		return null;
	}

	private static boolean _hasDataType(String dataType, Object value) {
		if (dataType.equals("double") && (value instanceof Double)) {
			return true;
		}

		if (dataType.equals("int") && (value instanceof Integer)) {
			return true;
		}

		if (dataType.equals("string") && (value instanceof String)) {
			return true;
		}

		return false;
	}

	private static final String
		_FREE_MARKER_FRAGMENT_ENTRY_PROCESSOR_CLASS_NAME =
			"com.liferay.fragment.entry.processor.freemarker." +
				"FreeMarkerFragmentEntryProcessor";

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryConfigUtil.class);

}