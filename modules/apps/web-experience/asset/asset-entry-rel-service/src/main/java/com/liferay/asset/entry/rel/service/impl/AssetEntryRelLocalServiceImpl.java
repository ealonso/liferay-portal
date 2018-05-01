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

import com.liferay.asset.entry.rel.exception.NoSuchEntryRelException;
import com.liferay.asset.entry.rel.model.AssetEntryRel;
import com.liferay.asset.entry.rel.service.base.AssetEntryRelLocalServiceBaseImpl;

/**
 * @author Jürgen Kappler
 */
public class AssetEntryRelLocalServiceImpl
	extends AssetEntryRelLocalServiceBaseImpl {

	@Override
	public AssetEntryRel addAssetEntryRel(
		long assetEntryId, long classNameId, long classPK) {

		long assetEntryRelId = counterLocalService.increment();

		AssetEntryRel assetEntryRel = assetEntryRelPersistence.create(
			assetEntryRelId);

		assetEntryRel.setAssetEntryId(assetEntryId);
		assetEntryRel.setClassNameId(classNameId);
		assetEntryRel.setClassPK(classPK);

		assetEntryRelPersistence.update(assetEntryRel);

		return assetEntryRel;
	}

	@Override
	public void deleteAssetEntryRel(long assetEntryId, long classNameId)
		throws NoSuchEntryRelException {

		assetEntryRelPersistence.removeByA_C(assetEntryId, classNameId);
	}

	@Override
	public void deleteAssetEntryRelByAssetEntryId(long assetEntryId) {
		assetEntryRelPersistence.removeByAssetEntry(assetEntryId);
	}

	@Override
	public AssetEntryRel fetchAssetEntryRel(
		long assetEntryId, long classNameId) {

		return assetEntryRelPersistence.fetchByA_C(assetEntryId, classNameId);
	}

}