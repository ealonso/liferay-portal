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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntryAssetCategoryRel}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetCategoryRel
 * @generated
 */
@ProviderType
public class AssetEntryAssetCategoryRelWrapper
	implements AssetEntryAssetCategoryRel,
		ModelWrapper<AssetEntryAssetCategoryRel> {
	public AssetEntryAssetCategoryRelWrapper(
		AssetEntryAssetCategoryRel assetEntryAssetCategoryRel) {
		_assetEntryAssetCategoryRel = assetEntryAssetCategoryRel;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntryAssetCategoryRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntryAssetCategoryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("assetCategoryId", getAssetCategoryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long assetCategoryId = (Long)attributes.get("assetCategoryId");

		if (assetCategoryId != null) {
			setAssetCategoryId(assetCategoryId);
		}
	}

	@Override
	public AssetEntryAssetCategoryRel toEscapedModel() {
		return new AssetEntryAssetCategoryRelWrapper(_assetEntryAssetCategoryRel.toEscapedModel());
	}

	@Override
	public AssetEntryAssetCategoryRel toUnescapedModel() {
		return new AssetEntryAssetCategoryRelWrapper(_assetEntryAssetCategoryRel.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntryAssetCategoryRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntryAssetCategoryRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntryAssetCategoryRel.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntryAssetCategoryRel.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntryAssetCategoryRel> toCacheModel() {
		return _assetEntryAssetCategoryRel.toCacheModel();
	}

	@Override
	public int compareTo(AssetEntryAssetCategoryRel assetEntryAssetCategoryRel) {
		return _assetEntryAssetCategoryRel.compareTo(assetEntryAssetCategoryRel);
	}

	@Override
	public int hashCode() {
		return _assetEntryAssetCategoryRel.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntryAssetCategoryRel.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssetEntryAssetCategoryRelWrapper((AssetEntryAssetCategoryRel)_assetEntryAssetCategoryRel.clone());
	}

	@Override
	public java.lang.String toString() {
		return _assetEntryAssetCategoryRel.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetEntryAssetCategoryRel.toXmlString();
	}

	/**
	* Returns the asset category ID of this asset entry asset category rel.
	*
	* @return the asset category ID of this asset entry asset category rel
	*/
	@Override
	public long getAssetCategoryId() {
		return _assetEntryAssetCategoryRel.getAssetCategoryId();
	}

	/**
	* Returns the asset entry ID of this asset entry asset category rel.
	*
	* @return the asset entry ID of this asset entry asset category rel
	*/
	@Override
	public long getAssetEntryId() {
		return _assetEntryAssetCategoryRel.getAssetEntryId();
	}

	/**
	* Returns the company ID of this asset entry asset category rel.
	*
	* @return the company ID of this asset entry asset category rel
	*/
	@Override
	public long getCompanyId() {
		return _assetEntryAssetCategoryRel.getCompanyId();
	}

	/**
	* Returns the entry ID of this asset entry asset category rel.
	*
	* @return the entry ID of this asset entry asset category rel
	*/
	@Override
	public long getEntryId() {
		return _assetEntryAssetCategoryRel.getEntryId();
	}

	/**
	* Returns the primary key of this asset entry asset category rel.
	*
	* @return the primary key of this asset entry asset category rel
	*/
	@Override
	public long getPrimaryKey() {
		return _assetEntryAssetCategoryRel.getPrimaryKey();
	}

	@Override
	public void persist() {
		_assetEntryAssetCategoryRel.persist();
	}

	/**
	* Sets the asset category ID of this asset entry asset category rel.
	*
	* @param assetCategoryId the asset category ID of this asset entry asset category rel
	*/
	@Override
	public void setAssetCategoryId(long assetCategoryId) {
		_assetEntryAssetCategoryRel.setAssetCategoryId(assetCategoryId);
	}

	/**
	* Sets the asset entry ID of this asset entry asset category rel.
	*
	* @param assetEntryId the asset entry ID of this asset entry asset category rel
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntryAssetCategoryRel.setAssetEntryId(assetEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntryAssetCategoryRel.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this asset entry asset category rel.
	*
	* @param companyId the company ID of this asset entry asset category rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_assetEntryAssetCategoryRel.setCompanyId(companyId);
	}

	/**
	* Sets the entry ID of this asset entry asset category rel.
	*
	* @param entryId the entry ID of this asset entry asset category rel
	*/
	@Override
	public void setEntryId(long entryId) {
		_assetEntryAssetCategoryRel.setEntryId(entryId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntryAssetCategoryRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetEntryAssetCategoryRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntryAssetCategoryRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntryAssetCategoryRel.setNew(n);
	}

	/**
	* Sets the primary key of this asset entry asset category rel.
	*
	* @param primaryKey the primary key of this asset entry asset category rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntryAssetCategoryRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntryAssetCategoryRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryAssetCategoryRelWrapper)) {
			return false;
		}

		AssetEntryAssetCategoryRelWrapper assetEntryAssetCategoryRelWrapper = (AssetEntryAssetCategoryRelWrapper)obj;

		if (Objects.equals(_assetEntryAssetCategoryRel,
					assetEntryAssetCategoryRelWrapper._assetEntryAssetCategoryRel)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetEntryAssetCategoryRel getWrappedModel() {
		return _assetEntryAssetCategoryRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntryAssetCategoryRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntryAssetCategoryRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntryAssetCategoryRel.resetOriginalValues();
	}

	private final AssetEntryAssetCategoryRel _assetEntryAssetCategoryRel;
}