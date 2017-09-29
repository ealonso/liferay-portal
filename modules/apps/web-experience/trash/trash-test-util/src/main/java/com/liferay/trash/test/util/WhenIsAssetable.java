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

package com.liferay.trash.test.util;

import com.liferay.asset.model.AssetEntry;
import com.liferay.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.petra.model.adapter.util.ModelAdapterUtil;
import com.liferay.portal.kernel.model.ClassedModel;

/**
 * @author Cristina González
 */
public interface WhenIsAssetable {

	/**
	 * @deprecated As of 1.1.0, replaced by {@link #fetchEntry(ClassedModel)}
	 */
	@Deprecated
	public default com.liferay.asset.kernel.model.AssetEntry fetchAssetEntry(
			ClassedModel classedModel)
		throws Exception {

		return ModelAdapterUtil.adapt(
			com.liferay.asset.kernel.model.AssetEntry.class,
			fetchEntry(classedModel));
	}

	public default AssetEntry fetchEntry(ClassedModel classedModel)
		throws Exception {

		Class<?> modelClass = classedModel.getModelClass();

		return AssetEntryLocalServiceUtil.fetchEntry(
			modelClass.getName(), (Long)classedModel.getPrimaryKeyObj());
	}

	public boolean isAssetEntryVisible(ClassedModel classedModel, long classPK)
		throws Exception;

}