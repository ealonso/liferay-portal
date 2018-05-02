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

package com.liferay.asset.entry.rel.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AssetEntryClassNameRelSoap implements Serializable {
	public static AssetEntryClassNameRelSoap toSoapModel(
		AssetEntryClassNameRel model) {
		AssetEntryClassNameRelSoap soapModel = new AssetEntryClassNameRelSoap();

		soapModel.setAssetEntryClassNameRelId(model.getAssetEntryClassNameRelId());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static AssetEntryClassNameRelSoap[] toSoapModels(
		AssetEntryClassNameRel[] models) {
		AssetEntryClassNameRelSoap[] soapModels = new AssetEntryClassNameRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryClassNameRelSoap[][] toSoapModels(
		AssetEntryClassNameRel[][] models) {
		AssetEntryClassNameRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetEntryClassNameRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntryClassNameRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryClassNameRelSoap[] toSoapModels(
		List<AssetEntryClassNameRel> models) {
		List<AssetEntryClassNameRelSoap> soapModels = new ArrayList<AssetEntryClassNameRelSoap>(models.size());

		for (AssetEntryClassNameRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntryClassNameRelSoap[soapModels.size()]);
	}

	public AssetEntryClassNameRelSoap() {
	}

	public long getPrimaryKey() {
		return _assetEntryClassNameRelId;
	}

	public void setPrimaryKey(long pk) {
		setAssetEntryClassNameRelId(pk);
	}

	public long getAssetEntryClassNameRelId() {
		return _assetEntryClassNameRelId;
	}

	public void setAssetEntryClassNameRelId(long assetEntryClassNameRelId) {
		_assetEntryClassNameRelId = assetEntryClassNameRelId;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _assetEntryClassNameRelId;
	private long _assetEntryId;
	private long _classNameId;
	private long _classPK;
}