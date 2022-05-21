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

package com.liferay.fragment.internal.type.input.templateparser;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class InputFragmentEntryTemplateNode
	extends LinkedHashMap<String, Object> {

	public InputFragmentEntryTemplateNode(
		JSONObject configurationJSONObject, String fragmentEntryType,
		Object value) {

		_fragmentEntryType = fragmentEntryType;
		_value = value;

		_helpText = configurationJSONObject.getString("helpText");
		_label = configurationJSONObject.getString("label");
		_name = configurationJSONObject.getString("name");
		_required = configurationJSONObject.getBoolean("required");
		_showHelpText = configurationJSONObject.getBoolean("showHelpText");
		_showLabel = configurationJSONObject.getBoolean("showLabel");
		_type = configurationJSONObject.getString("type");

		JSONObject typeOptionsJSONObject =
			configurationJSONObject.getJSONObject("typeOptions");

		if ((typeOptionsJSONObject != null) &&
			(typeOptionsJSONObject.length() > 0)) {

			for (String key : typeOptionsJSONObject.keySet()) {
				_typeOptions.put(key, typeOptionsJSONObject.getString(key));
			}
		}

		for (String key : configurationJSONObject.keySet()) {
			put(key, configurationJSONObject.get(key));
		}

		put("options", _options);
	}

	public void addOption(String label, String value) {
		_options.add(new Option(label, value));
	}

	public String getHelpText() {
		return _helpText;
	}

	public String getInputLabel() {
		return _label;
	}

	public String getInputName() {
		return _name;
	}

	public List<InputFragmentEntryTemplateNode.Option> getOptions() {
		return _options;
	}

	public String getType() {
		return _type;
	}

	public Map<String, String> getTypeOptions() {
		return _typeOptions;
	}

	public Object getValue() {
		return _value;
	}

	public boolean isRequired() {
		return _required;
	}

	public boolean isShowHelpText() {
		return _showHelpText;
	}

	public boolean isShowLabel() {
		return _showLabel;
	}

	public static class Option {

		public Option(String label, String value) {
			_label = label;
			_value = value;
		}

		public String getLabel() {
			return _label;
		}

		public String getValue() {
			return _value;
		}

		private final String _label;
		private final String _value;

	}

	private final String _fragmentEntryType;
	private final String _helpText;
	private final String _label;
	private final String _name;
	private final List<InputFragmentEntryTemplateNode.Option> _options =
		new ArrayList<>();
	private final boolean _required;
	private final boolean _showHelpText;
	private final boolean _showLabel;
	private final String _type;
	private final Map<String, String> _typeOptions = new LinkedHashMap<>();
	private final Object _value;

}