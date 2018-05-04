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

package com.liferay.asset.entry.rel.service.impl;

import com.liferay.asset.entry.rel.exception.NoSuchEntryClassNameRelException;
import com.liferay.asset.entry.rel.model.AssetEntryClassNameRel;
import com.liferay.asset.entry.rel.service.base.AssetEntryClassNameRelLocalServiceBaseImpl;

/**
 * @author Jürgen Kappler
 */
public class AssetEntryClassNameRelLocalServiceImpl
	extends AssetEntryClassNameRelLocalServiceBaseImpl {

	@Override
	public AssetEntryClassNameRel addAssetEntryClassNameRel(
		long assetEntryId, long classNameId, long classPK) {

		long assetEntryClassNameRelId = counterLocalService.increment();

		AssetEntryClassNameRel assetEntryClassNameRel =
			assetEntryClassNameRelPersistence.create(assetEntryClassNameRelId);

		assetEntryClassNameRel.setAssetEntryId(assetEntryId);
		assetEntryClassNameRel.setClassNameId(classNameId);
		assetEntryClassNameRel.setClassPK(classPK);

		assetEntryClassNameRelPersistence.update(assetEntryClassNameRel);

		return assetEntryClassNameRel;
	}

	@Override
	public void deleteAssetEntryClassNameRel(
			long assetEntryId, long classNameId)
		throws NoSuchEntryClassNameRelException {

		assetEntryClassNameRelPersistence.removeByA_C(
			assetEntryId, classNameId);
	}

	@Override
	public void deleteAssetEntryClassNameRelByAssetEntryId(long assetEntryId) {
		assetEntryClassNameRelPersistence.removeByAssetEntry(assetEntryId);
	}

	@Override
	public AssetEntryClassNameRel fetchAssetEntryClassNameRel(
		long assetEntryId, long classNameId) {

		return assetEntryClassNameRelPersistence.fetchByA_C(
			assetEntryId, classNameId);
	}

}