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

package com.liferay.layout.content.page.editor.web.internal.util;

import com.liferay.info.exception.NoSuchFormVariationException;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.field.InfoFieldSetEntry;
import com.liferay.info.field.type.BooleanInfoFieldType;
import com.liferay.info.field.type.InfoFieldType;
import com.liferay.info.field.type.NumberInfoFieldType;
import com.liferay.info.field.type.OptionInfoFieldType;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.layout.util.structure.FormStyledLayoutStructureItem;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Jürgen Kappler
 */
public class InfoFormUtil {

	public static JSONObject getConfigurationJSONObject(
		InfoForm infoForm, Locale locale) {

		JSONArray defaultFieldSetFieldsJSONArray =
			JSONFactoryUtil.createJSONArray();

		JSONArray fieldSetsJSONArray = JSONUtil.put(
			JSONUtil.put("fields", defaultFieldSetFieldsJSONArray));

		if (infoForm == null) {
			return JSONUtil.put("fieldSets", fieldSetsJSONArray);
		}

		for (InfoFieldSetEntry infoFieldSetEntry :
				infoForm.getInfoFieldSetEntries()) {

			if (infoFieldSetEntry instanceof InfoField) {
				InfoField<?> infoField = (InfoField<?>)infoFieldSetEntry;

				InfoFieldType infoFieldType = infoField.getInfoFieldType();

				if (!_isValidInfoFieldType(infoFieldType)) {
					continue;
				}

				defaultFieldSetFieldsJSONArray.put(
					_getFieldSetFieldJSONObject(
						infoField, infoFieldType, locale));
			}
			else if (infoFieldSetEntry instanceof InfoFieldSet) {
				InfoFieldSet infoFieldSet = (InfoFieldSet)infoFieldSetEntry;

				for (InfoField<?> infoField : infoFieldSet.getAllInfoFields()) {
					InfoFieldType infoFieldType = infoField.getInfoFieldType();

					if (!_isValidInfoFieldType(infoFieldType)) {
						continue;
					}

					defaultFieldSetFieldsJSONArray.put(
						_getFieldSetFieldJSONObject(
							infoField, infoFieldType, locale));
				}
			}
		}

		return JSONUtil.put("fieldSets", fieldSetsJSONArray);
	}

	public static InfoForm getInfoForm(
		FormStyledLayoutStructureItem formStyledLayoutStructureItem,
		InfoItemServiceTracker infoItemServiceTracker) {

		if (formStyledLayoutStructureItem == null) {
			return null;
		}

		InfoItemFormProvider<Object> infoItemFormProvider =
			infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFormProvider.class,
				PortalUtil.getClassName(
					formStyledLayoutStructureItem.getClassNameId()));

		if (infoItemFormProvider != null) {
			try {
				return infoItemFormProvider.getInfoForm(
					String.valueOf(
						formStyledLayoutStructureItem.getClassTypeId()));
			}
			catch (NoSuchFormVariationException noSuchFormVariationException) {
				return null;
			}
		}

		return null;
	}

	private static String _getDataType(InfoFieldType infoFieldType) {
		if (infoFieldType instanceof BooleanInfoFieldType) {
			return "object";
		}
		else if (infoFieldType instanceof NumberInfoFieldType) {
			return "double";
		}

		return "string";
	}

	private static JSONObject _getFieldSetFieldJSONObject(
		InfoField infoField, InfoFieldType infoFieldType, Locale locale) {

		JSONObject jsonObject = JSONUtil.put(
			"dataType", _getDataType(infoFieldType)
		).put(
			"label", infoField.getLabel(locale)
		).put(
			"name", infoField.getName()
		).put(
			"type", _getType(infoFieldType)
		);

		if (infoFieldType instanceof SelectInfoFieldType) {
			Optional<List<OptionInfoFieldType.Options>> optionsOptional =
				infoField.getAttributeOptional(SelectInfoFieldType.OPTIONS);

			List<OptionInfoFieldType.Options> options = optionsOptional.orElse(
				Collections.emptyList());

			try {
				jsonObject.put(
					"typeOptions",
					JSONUtil.put(
						"multiSelect",
						() -> {
							Optional<Boolean> multipleOptional =
								infoField.getAttributeOptional(
									SelectInfoFieldType.MULTIPLE);

							return multipleOptional.orElse(false);
						}
					).put(
						"validValues",
						JSONUtil.toJSONArray(
							options,
							option -> JSONUtil.put(
								"label", String.valueOf(option.getLabel(locale))
							).put(
								"value", String.valueOf(option.getValue())
							))
					));
			}
			catch (Exception exception) {
				if (_log.isDebugEnabled()) {
					_log.debug(exception);
				}
			}
		}

		return jsonObject;
	}

	private static String _getType(InfoFieldType infoFieldType) {
		if (infoFieldType instanceof BooleanInfoFieldType) {
			return "checkbox";
		}
		else if (infoFieldType instanceof SelectInfoFieldType) {
			return "select";
		}

		return "text";
	}

	private static boolean _isValidInfoFieldType(InfoFieldType infoFieldType) {
		if (infoFieldType instanceof SelectInfoFieldType ||
			infoFieldType instanceof TextInfoFieldType) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(InfoFormUtil.class);

}