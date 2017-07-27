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
public class AssetEntryAssetCategoryRelSoap implements Serializable {
	public static AssetEntryAssetCategoryRelSoap toSoapModel(
		AssetEntryAssetCategoryRel model) {
		AssetEntryAssetCategoryRelSoap soapModel = new AssetEntryAssetCategoryRelSoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setAssetCategoryId(model.getAssetCategoryId());

		return soapModel;
	}

	public static AssetEntryAssetCategoryRelSoap[] toSoapModels(
		AssetEntryAssetCategoryRel[] models) {
		AssetEntryAssetCategoryRelSoap[] soapModels = new AssetEntryAssetCategoryRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryAssetCategoryRelSoap[][] toSoapModels(
		AssetEntryAssetCategoryRel[][] models) {
		AssetEntryAssetCategoryRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetEntryAssetCategoryRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntryAssetCategoryRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryAssetCategoryRelSoap[] toSoapModels(
		List<AssetEntryAssetCategoryRel> models) {
		List<AssetEntryAssetCategoryRelSoap> soapModels = new ArrayList<AssetEntryAssetCategoryRelSoap>(models.size());

		for (AssetEntryAssetCategoryRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntryAssetCategoryRelSoap[soapModels.size()]);
	}

	public AssetEntryAssetCategoryRelSoap() {
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

	public long getAssetCategoryId() {
		return _assetCategoryId;
	}

	public void setAssetCategoryId(long assetCategoryId) {
		_assetCategoryId = assetCategoryId;
	}

	private long _entryId;
	private long _companyId;
	private long _assetEntryId;
	private long _assetCategoryId;
}