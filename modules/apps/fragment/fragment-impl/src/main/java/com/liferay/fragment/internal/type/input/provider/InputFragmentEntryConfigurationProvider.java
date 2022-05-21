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

package com.liferay.fragment.internal.type.input.provider;

import com.liferay.fragment.constants.FragmentConfigurationFieldDataType;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.entry.processor.helper.FragmentEntryProcessorHelper;
import com.liferay.fragment.internal.type.input.templateparser.InputFragmentEntryTemplateNode;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.fragment.util.configuration.provider.FragmentEntryConfigurationProvider;
import com.liferay.info.constants.InfoDisplayWebKeys;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(service = FragmentEntryConfigurationProvider.class)
public class InputFragmentEntryConfigurationProvider
	implements FragmentEntryConfigurationProvider {

	@Override
	public List<FragmentConfigurationField> getFragmentConfigurationFields() {
		return ListUtil.fromArray(_FRAGMENT_CONFIGURATION_FIELDS);
	}

	@Override
	public Map<String, Object> getTemplateNode(
			String editableValues,
			FragmentEntryProcessorContext fragmentEntryProcessorContext)
		throws JSONException {

		HttpServletRequest httpServletRequest =
			fragmentEntryProcessorContext.getHttpServletRequest();

		InfoField infoField = null;

		InfoForm infoForm = (InfoForm)httpServletRequest.getAttribute(
			InfoDisplayWebKeys.INFO_FORM);

		if (infoForm != null) {
			String fieldName = GetterUtil.getString(
				_fragmentEntryConfigurationParser.getConfigurationFieldValue(
					editableValues, "inputFieldId",
					FragmentConfigurationFieldDataType.STRING));

			infoField = infoForm.getInfoField(fieldName);
		}

		JSONObject configurationJSONObject =
			_fragmentEntryConfigurationParser.getConfigurationJSONObject(
				getFragmentConfigurationFields(), editableValues,
				fragmentEntryProcessorContext.getLocale());

		if (infoField == null) {
			return new InputFragmentEntryTemplateNode(
				configurationJSONObject, getType(), StringPool.BLANK);
		}

		if (Validator.isNull(configurationJSONObject.get("label"))) {
			configurationJSONObject.put(
				"label",
				infoField.getLabel(fragmentEntryProcessorContext.getLocale()));
		}

		configurationJSONObject.put("name", infoField.getName());

		if (infoField.isRequired()) {
			configurationJSONObject.put("required", true);
		}

		Object value = StringPool.BLANK;

		Object infoItem = httpServletRequest.getAttribute(
			InfoDisplayWebKeys.INFO_ITEM);

		InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
			(InfoItemFieldValuesProvider)httpServletRequest.getAttribute(
				InfoDisplayWebKeys.INFO_ITEM_FIELD_VALUES_PROVIDER);

		if ((infoItem != null) && (infoItemFieldValuesProvider != null)) {
			value = _fragmentEntryProcessorHelper.getMappedInfoItemFieldValue(
				infoField.getUniqueId(), infoItemFieldValuesProvider,
				fragmentEntryProcessorContext.getLocale(), infoItem);
		}

		InputFragmentEntryTemplateNode inputFragmentEntryTemplateNode =
			new InputFragmentEntryTemplateNode(
				configurationJSONObject, getType(), value);

		if (infoField.getInfoFieldType() == SelectInfoFieldType.INSTANCE) {
			Optional<List<SelectInfoFieldType.Option>> optionsOptional =
				infoField.getAttributeOptional(SelectInfoFieldType.OPTIONS);

			List<SelectInfoFieldType.Option> options = optionsOptional.orElse(
				new ArrayList<>());

			for (SelectInfoFieldType.Option option : options) {
				inputFragmentEntryTemplateNode.addOption(
					option.getLabel(fragmentEntryProcessorContext.getLocale()),
					option.getValue());
			}
		}

		return inputFragmentEntryTemplateNode;
	}

	@Override
	public String getType() {
		return FragmentConstants.getTypeLabel(FragmentConstants.TYPE_INPUT);
	}

	private static FragmentConfigurationField _getFragmentConfigurationField(
		String label, String name, String type, boolean localizable,
		Object defaultValue, JSONObject typeOptionsJSONObject) {

		JSONObject jsonObject = JSONUtil.put(
			"defaultValue", defaultValue
		).put(
			"label", label
		).put(
			"name", name
		).put(
			"type", type
		);

		if (localizable) {
			jsonObject.put("localizable", localizable);
		}

		if (typeOptionsJSONObject != null) {
			jsonObject.put("typeOptions", typeOptionsJSONObject);
		}

		return new FragmentConfigurationField(jsonObject);
	}

	private static final FragmentConfigurationField[]
		_FRAGMENT_CONFIGURATION_FIELDS = {
			_getFragmentConfigurationField(
				"mark-as-required", "required", "checkbox", false, false, null),
			_getFragmentConfigurationField(
				"show-label", "showLabel", "checkbox", false, true,
				JSONUtil.put("displayType", "toggle")),
			_getFragmentConfigurationField(
				"label", "label", "text", true, StringPool.BLANK, null),
			_getFragmentConfigurationField(
				"show-help-text", "showHelpText", "checkbox", false, true,
				JSONUtil.put("displayType", "toggle")),
			_getFragmentConfigurationField(
				"help-text", "helpText", "checkbox", true, StringPool.BLANK,
				null)
		};

	@Reference
	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;

	@Reference
	private FragmentEntryProcessorHelper _fragmentEntryProcessorHelper;

}