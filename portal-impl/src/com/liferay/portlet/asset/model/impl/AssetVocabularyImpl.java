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

package com.liferay.portlet.asset.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.asset.util.AssetVocabularySettingsHelper;

import java.util.List;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Juan Fernández
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.categories.model.impl.AssetVocabularyImpl}
 */
@Deprecated
public class AssetVocabularyImpl extends AssetVocabularyBaseImpl {

	@Override
	public List<AssetCategory> getCategories() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public int getCategoriesCount() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public long[] getRequiredClassNameIds() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public long[] getSelectedClassNameIds() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public long[] getSelectedClassTypePKs() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public String getSettings() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public UnicodeProperties getSettingsProperties() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public String getTitle(String languageId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public String getUnambiguousTitle(
			List<AssetVocabulary> vocabularies, long groupId,
			final Locale locale)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean hasMoreThanOneCategorySelected(final long[] categoryIds) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean isAssociatedToClassNameId(long classNameId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean isAssociatedToClassNameIdAndClassTypePK(
		long classNameId, long classTypePK) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean isMissingRequiredCategory(
		long classNameId, long classTypePK, final long[] categoryIds) {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean isMultiValued() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #isRequired(long, long)}
	 */
	@Deprecated
	@Override
	public boolean isRequired(long classNameId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public boolean isRequired(long classNameId, long classTypePK) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	@Override
	public void setSettings(String settings) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public void setSettingsProperties(UnicodeProperties settingsProperties) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

	protected AssetVocabularySettingsHelper getVocabularySettingsHelper() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetVocabularyImpl");
	}

}