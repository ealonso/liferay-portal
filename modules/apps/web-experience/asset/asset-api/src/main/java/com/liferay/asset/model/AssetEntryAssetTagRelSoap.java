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

package com.liferay.asset.model;

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
public class AssetEntryAssetTagRelSoap implements Serializable {
	public static AssetEntryAssetTagRelSoap toSoapModel(
		AssetEntryAssetTagRel model) {
		AssetEntryAssetTagRelSoap soapModel = new AssetEntryAssetTagRelSoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setAssetTagId(model.getAssetTagId());

		return soapModel;
	}

	public static AssetEntryAssetTagRelSoap[] toSoapModels(
		AssetEntryAssetTagRel[] models) {
		AssetEntryAssetTagRelSoap[] soapModels = new AssetEntryAssetTagRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryAssetTagRelSoap[][] toSoapModels(
		AssetEntryAssetTagRel[][] models) {
		AssetEntryAssetTagRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetEntryAssetTagRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntryAssetTagRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryAssetTagRelSoap[] toSoapModels(
		List<AssetEntryAssetTagRel> models) {
		List<AssetEntryAssetTagRelSoap> soapModels = new ArrayList<AssetEntryAssetTagRelSoap>(models.size());

		for (AssetEntryAssetTagRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntryAssetTagRelSoap[soapModels.size()]);
	}

	public AssetEntryAssetTagRelSoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public long getAssetTagId() {
		return _assetTagId;
	}

	public void setAssetTagId(long assetTagId) {
		_assetTagId = assetTagId;
	}

	private long _entryId;
	private long _companyId;
	private long _assetEntryId;
	private long _assetTagId;
}