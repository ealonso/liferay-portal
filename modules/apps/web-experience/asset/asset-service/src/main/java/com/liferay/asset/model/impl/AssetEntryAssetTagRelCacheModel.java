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

package com.liferay.asset.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.model.AssetEntryAssetTagRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssetEntryAssetTagRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetTagRel
 * @generated
 */
@ProviderType
public class AssetEntryAssetTagRelCacheModel implements CacheModel<AssetEntryAssetTagRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryAssetTagRelCacheModel)) {
			return false;
		}

		AssetEntryAssetTagRelCacheModel assetEntryAssetTagRelCacheModel = (AssetEntryAssetTagRelCacheModel)obj;

		if (entryId == assetEntryAssetTagRelCacheModel.entryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{entryId=");
		sb.append(entryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", assetTagId=");
		sb.append(assetTagId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetEntryAssetTagRel toEntityModel() {
		AssetEntryAssetTagRelImpl assetEntryAssetTagRelImpl = new AssetEntryAssetTagRelImpl();

		assetEntryAssetTagRelImpl.setEntryId(entryId);
		assetEntryAssetTagRelImpl.setCompanyId(companyId);
		assetEntryAssetTagRelImpl.setAssetEntryId(assetEntryId);
		assetEntryAssetTagRelImpl.setAssetTagId(assetTagId);

		assetEntryAssetTagRelImpl.resetOriginalValues();

		return assetEntryAssetTagRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entryId = objectInput.readLong();

		companyId = objectInput.readLong();

		assetEntryId = objectInput.readLong();

		assetTagId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(entryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(assetEntryId);

		objectOutput.writeLong(assetTagId);
	}

	public long entryId;
	public long companyId;
	public long assetEntryId;
	public long assetTagId;
}