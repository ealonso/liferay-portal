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

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.util.configuration.FragmentConfigurationField;
import com.liferay.fragment.util.configuration.provider.FragmentEntryConfigurationProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;

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

}