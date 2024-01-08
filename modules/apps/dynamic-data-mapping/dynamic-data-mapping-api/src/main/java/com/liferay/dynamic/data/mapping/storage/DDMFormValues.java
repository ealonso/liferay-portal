/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.storage;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.petra.lang.HashUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Marcellus Tavares
 * @author Pablo Carvalho
 */
public class DDMFormValues implements Serializable {

	public DDMFormValues(DDMForm ddmForm) {
		_ddmForm = ddmForm;
	}

	public void addAvailableLocale(Locale locale) {
		_availableLocales.add(locale);
	}

	public void addDDMFormFieldValue(DDMFormFieldValue ddmFormFieldValue) {
		ddmFormFieldValue.setDDMFormValues(this);

		_ddmFormFieldValues.add(ddmFormFieldValue);

		List<DDMFormFieldValue> ddmFormFieldValues =
			_ddmFormFieldValuesMap.computeIfAbsent(
				ddmFormFieldValue.getName(), value -> new ArrayList<>());

		ddmFormFieldValues.add(ddmFormFieldValue);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DDMFormValues)) {
			return false;
		}

		DDMFormValues ddmFormValues = (DDMFormValues)object;

		if (Objects.equals(
				_availableLocales, ddmFormValues._availableLocales) &&
			Objects.equals(_defaultLocale, ddmFormValues._defaultLocale) &&
			Objects.equals(
				_ddmFormFieldValues, ddmFormValues._ddmFormFieldValues)) {

			return true;
		}

		return false;
	}

	public Set<Locale> getAvailableLocales() {
		return _availableLocales;
	}

	public DDMForm getDDMForm() {
		return _ddmForm;
	}

	public List<DDMFormFieldValue> getDDMFormFieldValues() {
		return _ddmFormFieldValues;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getDDMFormFieldValuesMap(boolean)}
	 */
	@Deprecated
	public Map<String, List<DDMFormFieldValue>> getDDMFormFieldValuesMap() {
		return getDDMFormFieldValuesMap(false);
	}

	public Map<String, List<DDMFormFieldValue>> getDDMFormFieldValuesMap(
		boolean includeNestedDDMFormFieldValues) {

		if (includeNestedDDMFormFieldValues) {
			return _getFullHierarchyDDMFormFieldValuesMap();
		}

		return _ddmFormFieldValuesMap;
	}

	public Map<String, List<DDMFormFieldValue>>
		getDDMFormFieldValuesReferencesMap(
			boolean includeNestedDDMFormFieldValues) {

		Map<String, List<DDMFormFieldValue>> ddmFormFieldValuesReferencesMap =
			new LinkedHashMap<>();

		for (DDMFormFieldValue ddmFormFieldValue : _ddmFormFieldValues) {
			List<DDMFormFieldValue> ddmFormFieldValues =
				ddmFormFieldValuesReferencesMap.get(
					ddmFormFieldValue.getFieldReference());

			if (ddmFormFieldValues == null) {
				ddmFormFieldValues = new ArrayList<>();

				ddmFormFieldValuesReferencesMap.put(
					ddmFormFieldValue.getFieldReference(), ddmFormFieldValues);
			}

			ddmFormFieldValues.add(ddmFormFieldValue);

			if (includeNestedDDMFormFieldValues) {
				ddmFormFieldValue.populateNestedDDMFormFieldValuesReferencesMap(
					ddmFormFieldValuesReferencesMap);
			}
		}

		return ddmFormFieldValuesReferencesMap;
	}

	public Locale getDefaultLocale() {
		return _defaultLocale;
	}

	@Override
	public int hashCode() {
		int hash = HashUtil.hash(0, _availableLocales);

		hash = HashUtil.hash(hash, _defaultLocale);

		return HashUtil.hash(hash, _ddmFormFieldValuesMap);
	}

	public void setAvailableLocales(Set<Locale> availableLocales) {
		_availableLocales = availableLocales;
	}

	public void setDDMFormFieldValues(
		List<DDMFormFieldValue> ddmFormFieldValues) {

		for (DDMFormFieldValue ddmFormFieldValue : ddmFormFieldValues) {
			ddmFormFieldValue.setDDMFormValues(this);

			List<DDMFormFieldValue> curDDMFormFieldValues =
				_ddmFormFieldValuesMap.computeIfAbsent(
					ddmFormFieldValue.getName(), value -> new ArrayList<>());

			curDDMFormFieldValues.add(ddmFormFieldValue);
		}

		_ddmFormFieldValues = ddmFormFieldValues;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		_defaultLocale = defaultLocale;
	}

	private Map<String, List<DDMFormFieldValue>>
		_getFullHierarchyDDMFormFieldValuesMap() {

		if (_fullHierarchyDDMFormFieldValuesMap != null) {
			return _fullHierarchyDDMFormFieldValuesMap;
		}

		_fullHierarchyDDMFormFieldValuesMap = new LinkedHashMap<>();

		for (DDMFormFieldValue ddmFormFieldValue : _ddmFormFieldValues) {
			List<DDMFormFieldValue> ddmFormFieldValues =
				_fullHierarchyDDMFormFieldValuesMap.computeIfAbsent(
					ddmFormFieldValue.getName(), value -> new ArrayList<>());

			ddmFormFieldValues.add(ddmFormFieldValue);

			ddmFormFieldValue.populateNestedDDMFormFieldValuesMap(
				_fullHierarchyDDMFormFieldValuesMap);
		}

		return _fullHierarchyDDMFormFieldValuesMap;
	}

	private Set<Locale> _availableLocales = new LinkedHashSet<>();
	private final DDMForm _ddmForm;
	private List<DDMFormFieldValue> _ddmFormFieldValues = new ArrayList<>();
	private final Map<String, List<DDMFormFieldValue>> _ddmFormFieldValuesMap =
		new LinkedHashMap<>();
	private Locale _defaultLocale;
	private Map<String, List<DDMFormFieldValue>>
		_fullHierarchyDDMFormFieldValuesMap;

}