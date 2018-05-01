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

package com.liferay.asset.entry.rel.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.entry.rel.model.AssetEntryRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AssetEntryRel in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRel
 * @generated
 */
@ProviderType
public class AssetEntryRelCacheModel implements CacheModel<AssetEntryRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryRelCacheModel)) {
			return false;
		}

		AssetEntryRelCacheModel assetEntryRelCacheModel = (AssetEntryRelCacheModel)obj;

		if (assetEntryRelId == assetEntryRelCacheModel.assetEntryRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetEntryRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{assetEntryRelId=");
		sb.append(assetEntryRelId);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetEntryRel toEntityModel() {
		AssetEntryRelImpl assetEntryRelImpl = new AssetEntryRelImpl();

		assetEntryRelImpl.setAssetEntryRelId(assetEntryRelId);
		assetEntryRelImpl.setAssetEntryId(assetEntryId);
		assetEntryRelImpl.setClassNameId(classNameId);
		assetEntryRelImpl.setClassPK(classPK);

		assetEntryRelImpl.resetOriginalValues();

		return assetEntryRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		assetEntryRelId = objectInput.readLong();

		assetEntryId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(assetEntryRelId);

		objectOutput.writeLong(assetEntryId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);
	}

	public long assetEntryRelId;
	public long assetEntryId;
	public long classNameId;
	public long classPK;
}