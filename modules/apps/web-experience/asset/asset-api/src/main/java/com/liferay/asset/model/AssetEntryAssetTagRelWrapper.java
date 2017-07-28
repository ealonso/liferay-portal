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
 * This class is a wrapper for {@link AssetEntryAssetTagRel}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetTagRel
 * @generated
 */
@ProviderType
public class AssetEntryAssetTagRelWrapper implements AssetEntryAssetTagRel,
	ModelWrapper<AssetEntryAssetTagRel> {
	public AssetEntryAssetTagRelWrapper(
		AssetEntryAssetTagRel assetEntryAssetTagRel) {
		_assetEntryAssetTagRel = assetEntryAssetTagRel;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntryAssetTagRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntryAssetTagRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("assetTagId", getAssetTagId());

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

		Long assetTagId = (Long)attributes.get("assetTagId");

		if (assetTagId != null) {
			setAssetTagId(assetTagId);
		}
	}

	@Override
	public AssetEntryAssetTagRel toEscapedModel() {
		return new AssetEntryAssetTagRelWrapper(_assetEntryAssetTagRel.toEscapedModel());
	}

	@Override
	public AssetEntryAssetTagRel toUnescapedModel() {
		return new AssetEntryAssetTagRelWrapper(_assetEntryAssetTagRel.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntryAssetTagRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntryAssetTagRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntryAssetTagRel.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntryAssetTagRel.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntryAssetTagRel> toCacheModel() {
		return _assetEntryAssetTagRel.toCacheModel();
	}

	@Override
	public int compareTo(AssetEntryAssetTagRel assetEntryAssetTagRel) {
		return _assetEntryAssetTagRel.compareTo(assetEntryAssetTagRel);
	}

	@Override
	public int hashCode() {
		return _assetEntryAssetTagRel.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntryAssetTagRel.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AssetEntryAssetTagRelWrapper((AssetEntryAssetTagRel)_assetEntryAssetTagRel.clone());
	}

	@Override
	public java.lang.String toString() {
		return _assetEntryAssetTagRel.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetEntryAssetTagRel.toXmlString();
	}

	/**
	* Returns the asset entry ID of this asset entry asset tag rel.
	*
	* @return the asset entry ID of this asset entry asset tag rel
	*/
	@Override
	public long getAssetEntryId() {
		return _assetEntryAssetTagRel.getAssetEntryId();
	}

	/**
	* Returns the asset tag ID of this asset entry asset tag rel.
	*
	* @return the asset tag ID of this asset entry asset tag rel
	*/
	@Override
	public long getAssetTagId() {
		return _assetEntryAssetTagRel.getAssetTagId();
	}

	/**
	* Returns the company ID of this asset entry asset tag rel.
	*
	* @return the company ID of this asset entry asset tag rel
	*/
	@Override
	public long getCompanyId() {
		return _assetEntryAssetTagRel.getCompanyId();
	}

	/**
	* Returns the entry ID of this asset entry asset tag rel.
	*
	* @return the entry ID of this asset entry asset tag rel
	*/
	@Override
	public long getEntryId() {
		return _assetEntryAssetTagRel.getEntryId();
	}

	/**
	* Returns the primary key of this asset entry asset tag rel.
	*
	* @return the primary key of this asset entry asset tag rel
	*/
	@Override
	public long getPrimaryKey() {
		return _assetEntryAssetTagRel.getPrimaryKey();
	}

	@Override
	public void persist() {
		_assetEntryAssetTagRel.persist();
	}

	/**
	* Sets the asset entry ID of this asset entry asset tag rel.
	*
	* @param assetEntryId the asset entry ID of this asset entry asset tag rel
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntryAssetTagRel.setAssetEntryId(assetEntryId);
	}

	/**
	* Sets the asset tag ID of this asset entry asset tag rel.
	*
	* @param assetTagId the asset tag ID of this asset entry asset tag rel
	*/
	@Override
	public void setAssetTagId(long assetTagId) {
		_assetEntryAssetTagRel.setAssetTagId(assetTagId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntryAssetTagRel.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this asset entry asset tag rel.
	*
	* @param companyId the company ID of this asset entry asset tag rel
	*/
	@Override
	public void setCompanyId(long companyId) {
		_assetEntryAssetTagRel.setCompanyId(companyId);
	}

	/**
	* Sets the entry ID of this asset entry asset tag rel.
	*
	* @param entryId the entry ID of this asset entry asset tag rel
	*/
	@Override
	public void setEntryId(long entryId) {
		_assetEntryAssetTagRel.setEntryId(entryId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntryAssetTagRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetEntryAssetTagRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntryAssetTagRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntryAssetTagRel.setNew(n);
	}

	/**
	* Sets the primary key of this asset entry asset tag rel.
	*
	* @param primaryKey the primary key of this asset entry asset tag rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntryAssetTagRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntryAssetTagRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryAssetTagRelWrapper)) {
			return false;
		}

		AssetEntryAssetTagRelWrapper assetEntryAssetTagRelWrapper = (AssetEntryAssetTagRelWrapper)obj;

		if (Objects.equals(_assetEntryAssetTagRel,
					assetEntryAssetTagRelWrapper._assetEntryAssetTagRel)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetEntryAssetTagRel getWrappedModel() {
		return _assetEntryAssetTagRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntryAssetTagRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntryAssetTagRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntryAssetTagRel.resetOriginalValues();
	}

	private final AssetEntryAssetTagRel _assetEntryAssetTagRel;
}