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
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of 7.0.0, replaced by {@link
 *             com.liferay.asset.categories.model.impl.AssetCategoryImpl}
 */
@Deprecated
public class AssetCategoryImpl extends AssetCategoryBaseImpl {

	@Override
	public List<AssetCategory> getAncestors() throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public AssetCategory getParentCategory() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public String getPath(Locale locale) throws PortalException {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public String getPath(Locale locale, boolean reverse)
		throws PortalException {

		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public String getTitle(String languageId) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

	@Override
	public boolean isRootCategory() {
		throw new UnsupportedOperationException(
			"This class is deprecate and replaced by " +
				"com.liferay.asset.categories.model.impl.AssetCategoryImpl");
	}

}