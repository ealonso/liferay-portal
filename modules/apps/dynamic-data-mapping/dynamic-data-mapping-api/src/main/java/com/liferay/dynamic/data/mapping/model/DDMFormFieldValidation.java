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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.petra.lang.HashUtil;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class DDMFormFieldValidation implements Serializable {

	public DDMFormFieldValidation() {
	}

	public DDMFormFieldValidation(
		DDMFormFieldValidation ddmFormFieldValidation) {

		_ddmFormFieldValidationExpression =
			ddmFormFieldValidation._ddmFormFieldValidationExpression;
		_errorMessageLocalizedValue =
			ddmFormFieldValidation._errorMessageLocalizedValue;
		_parameterLocalizedValue =
			ddmFormFieldValidation._parameterLocalizedValue;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormFieldValidation)) {
			return false;
		}

		DDMFormFieldValidation ddmFormFieldValidation =
			(DDMFormFieldValidation)object;

		if (Objects.equals(
				_ddmFormFieldValidationExpression,
				ddmFormFieldValidation._ddmFormFieldValidationExpression) &&
			Objects.equals(
				_errorMessageLocalizedValue,
				ddmFormFieldValidation._errorMessageLocalizedValue) &&
			Objects.equals(
				_parameterLocalizedValue,
				ddmFormFieldValidation._parameterLocalizedValue)) {

			return true;
		}

		return false;
	}

	public DDMFormFieldValidationExpression
		getDDMFormFieldValidationExpression() {

		return _ddmFormFieldValidationExpression;
	}

	public LocalizedValue getErrorMessageLocalizedValue() {
		return _errorMessageLocalizedValue;
	}

	public LocalizedValue getParameterLocalizedValue() {
		return _parameterLocalizedValue;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _ddmFormFieldValidationExpression);

		hash = hash + HashUtil.hash(hash, _errorMessageLocalizedValue);

		return HashUtil.hash(hash, _parameterLocalizedValue);
	}

	public void setDDMFormFieldValidationExpression(
		DDMFormFieldValidationExpression ddmFormFieldValidationExpression) {

		_ddmFormFieldValidationExpression = ddmFormFieldValidationExpression;
	}

	public void setErrorMessageLocalizedValue(
		LocalizedValue errorMessageLocalizedValue) {

		_errorMessageLocalizedValue = errorMessageLocalizedValue;
	}

	public void setParameterLocalizedValue(
		LocalizedValue parameterLocalizedValue) {

		_parameterLocalizedValue = parameterLocalizedValue;
	}

	private DDMFormFieldValidationExpression _ddmFormFieldValidationExpression =
		new DDMFormFieldValidationExpression();
	private LocalizedValue _errorMessageLocalizedValue;
	private LocalizedValue _parameterLocalizedValue;

}