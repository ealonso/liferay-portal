/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.item;

import com.liferay.info.localized.InfoLocalizedValue;

import java.util.Locale;

/**
 * @author Eudaldo alonso
 */
public class InfoItemRelationship {

	public static Builder builder() {
		return new Builder();
	}

	public String getClassName() {
		return _builder._className;
	}

	public String getLabel(Locale locale) {
		return _builder._labelInfoLocalizedValue.getValue(locale);
	}

	public static class Builder {

		public InfoItemRelationship build() {
			return new InfoItemRelationship(this);
		}

		public Builder className(String className) {
			_className = className;

			return this;
		}

		public Builder labelInfoLocalizedValue(
			InfoLocalizedValue<String> labelInfoLocalizedValue) {

			_labelInfoLocalizedValue = labelInfoLocalizedValue;

			return this;
		}

		private String _className;
		private InfoLocalizedValue<String> _labelInfoLocalizedValue;

	}

	private InfoItemRelationship(Builder builder) {
		_builder = builder;
	}

	private final Builder _builder;

}