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

package com.liferay.info.field.type;

import com.liferay.info.localized.InfoLocalizedValue;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public interface OptionInfoFieldType extends InfoFieldType {

	public class Options {

		public Options(InfoLocalizedValue<String> label, String value) {
			_label = label;
			_value = value;
		}

		public String getLabel(Locale locale) {
			return _label.getValue(locale);
		}

		public String getValue() {
			return _value;
		}

		private final InfoLocalizedValue<String> _label;
		private final String _value;

	}

}