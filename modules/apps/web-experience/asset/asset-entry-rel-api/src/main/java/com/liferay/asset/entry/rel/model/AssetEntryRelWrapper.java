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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntryRel}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryRel
 * @generated
 */
@ProviderType
public class AssetEntryRelWrapper implements AssetEntryRel,
	ModelWrapper<AssetEntryRel> {
	public AssetEntryRelWrapper(AssetEntryRel assetEntryRel) {
		_assetEntryRel = assetEntryRel;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntryRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntryRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntryRelId", getAssetEntryRelId());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntryRelId = (Long)attributes.get("assetEntryRelId");

		if (assetEntryRelId != null) {
			setAssetEntryRelId(assetEntryRelId);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	@Override
	public Object clone() {
		return new AssetEntryRelWrapper((AssetEntryRel)_assetEntryRel.clone());
	}

	@Override
	public int compareTo(AssetEntryRel assetEntryRel) {
		return _assetEntryRel.compareTo(assetEntryRel);
	}

	/**
	* Returns the asset entry ID of this asset entry rel.
	*
	* @return the asset entry ID of this asset entry rel
	*/
	@Override
	public long getAssetEntryId() {
		return _assetEntryRel.getAssetEntryId();
	}

	/**
	* Returns the asset entry rel ID of this asset entry rel.
	*
	* @return the asset entry rel ID of this asset entry rel
	*/
	@Override
	public long getAssetEntryRelId() {
		return _assetEntryRel.getAssetEntryRelId();
	}

	/**
	* Returns the fully qualified class name of this asset entry rel.
	*
	* @return the fully qualified class name of this asset entry rel
	*/
	@Override
	public String getClassName() {
		return _assetEntryRel.getClassName();
	}

	/**
	* Returns the class name ID of this asset entry rel.
	*
	* @return the class name ID of this asset entry rel
	*/
	@Override
	public long getClassNameId() {
		return _assetEntryRel.getClassNameId();
	}

	/**
	* Returns the class pk of this asset entry rel.
	*
	* @return the class pk of this asset entry rel
	*/
	@Override
	public long getClassPK() {
		return _assetEntryRel.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntryRel.getExpandoBridge();
	}

	/**
	* Returns the primary key of this asset entry rel.
	*
	* @return the primary key of this asset entry rel
	*/
	@Override
	public long getPrimaryKey() {
		return _assetEntryRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntryRel.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _assetEntryRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntryRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntryRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntryRel.isNew();
	}

	@Override
	public void persist() {
		_assetEntryRel.persist();
	}

	/**
	* Sets the asset entry ID of this asset entry rel.
	*
	* @param assetEntryId the asset entry ID of this asset entry rel
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntryRel.setAssetEntryId(assetEntryId);
	}

	/**
	* Sets the asset entry rel ID of this asset entry rel.
	*
	* @param assetEntryRelId the asset entry rel ID of this asset entry rel
	*/
	@Override
	public void setAssetEntryRelId(long assetEntryRelId) {
		_assetEntryRel.setAssetEntryRelId(assetEntryRelId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntryRel.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_assetEntryRel.setClassName(className);
	}

	/**
	* Sets the class name ID of this asset entry rel.
	*
	* @param classNameId the class name ID of this asset entry rel
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetEntryRel.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this asset entry rel.
	*
	* @param classPK the class pk of this asset entry rel
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetEntryRel.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetEntryRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntryRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntryRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntryRel.setNew(n);
	}

	/**
	* Sets the primary key of this asset entry rel.
	*
	* @param primaryKey the primary key of this asset entry rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntryRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntryRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntryRel> toCacheModel() {
		return _assetEntryRel.toCacheModel();
	}

	@Override
	public AssetEntryRel toEscapedModel() {
		return new AssetEntryRelWrapper(_assetEntryRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _assetEntryRel.toString();
	}

	@Override
	public AssetEntryRel toUnescapedModel() {
		return new AssetEntryRelWrapper(_assetEntryRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _assetEntryRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryRelWrapper)) {
			return false;
		}

		AssetEntryRelWrapper assetEntryRelWrapper = (AssetEntryRelWrapper)obj;

		if (Objects.equals(_assetEntryRel, assetEntryRelWrapper._assetEntryRel)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetEntryRel getWrappedModel() {
		return _assetEntryRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntryRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntryRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntryRel.resetOriginalValues();
	}

	private final AssetEntryRel _assetEntryRel;
}