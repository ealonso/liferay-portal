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

package com.liferay.asset.display.contributor;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Jürgen Kappler
 */
public interface AssetDisplayContributor {

	public Set<AssetDisplayField> getAssetEntryFields(Locale locale);

	public List<ObjectValuePair> getAssetSubtypes(long groupId, Locale locale);

	public String getClassName();

	public String getLabel(Locale locale);

	public Map<String, Object> getParameterMap(
			AssetEntry assetEntry, Locale locale)
		throws PortalException;

}