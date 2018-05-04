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
 * This class is a wrapper for {@link AssetEntryClassNameRel}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryClassNameRel
 * @generated
 */
@ProviderType
public class AssetEntryClassNameRelWrapper implements AssetEntryClassNameRel,
	ModelWrapper<AssetEntryClassNameRel> {
	public AssetEntryClassNameRelWrapper(
		AssetEntryClassNameRel assetEntryClassNameRel) {
		_assetEntryClassNameRel = assetEntryClassNameRel;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntryClassNameRel.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntryClassNameRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntryClassNameRelId", getAssetEntryClassNameRelId());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntryClassNameRelId = (Long)attributes.get(
				"assetEntryClassNameRelId");

		if (assetEntryClassNameRelId != null) {
			setAssetEntryClassNameRelId(assetEntryClassNameRelId);
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
		return new AssetEntryClassNameRelWrapper((AssetEntryClassNameRel)_assetEntryClassNameRel.clone());
	}

	@Override
	public int compareTo(AssetEntryClassNameRel assetEntryClassNameRel) {
		return _assetEntryClassNameRel.compareTo(assetEntryClassNameRel);
	}

	/**
	* Returns the asset entry class name rel ID of this asset entry class name rel.
	*
	* @return the asset entry class name rel ID of this asset entry class name rel
	*/
	@Override
	public long getAssetEntryClassNameRelId() {
		return _assetEntryClassNameRel.getAssetEntryClassNameRelId();
	}

	/**
	* Returns the asset entry ID of this asset entry class name rel.
	*
	* @return the asset entry ID of this asset entry class name rel
	*/
	@Override
	public long getAssetEntryId() {
		return _assetEntryClassNameRel.getAssetEntryId();
	}

	/**
	* Returns the fully qualified class name of this asset entry class name rel.
	*
	* @return the fully qualified class name of this asset entry class name rel
	*/
	@Override
	public String getClassName() {
		return _assetEntryClassNameRel.getClassName();
	}

	/**
	* Returns the class name ID of this asset entry class name rel.
	*
	* @return the class name ID of this asset entry class name rel
	*/
	@Override
	public long getClassNameId() {
		return _assetEntryClassNameRel.getClassNameId();
	}

	/**
	* Returns the class pk of this asset entry class name rel.
	*
	* @return the class pk of this asset entry class name rel
	*/
	@Override
	public long getClassPK() {
		return _assetEntryClassNameRel.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntryClassNameRel.getExpandoBridge();
	}

	/**
	* Returns the primary key of this asset entry class name rel.
	*
	* @return the primary key of this asset entry class name rel
	*/
	@Override
	public long getPrimaryKey() {
		return _assetEntryClassNameRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntryClassNameRel.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _assetEntryClassNameRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntryClassNameRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntryClassNameRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntryClassNameRel.isNew();
	}

	@Override
	public void persist() {
		_assetEntryClassNameRel.persist();
	}

	/**
	* Sets the asset entry class name rel ID of this asset entry class name rel.
	*
	* @param assetEntryClassNameRelId the asset entry class name rel ID of this asset entry class name rel
	*/
	@Override
	public void setAssetEntryClassNameRelId(long assetEntryClassNameRelId) {
		_assetEntryClassNameRel.setAssetEntryClassNameRelId(assetEntryClassNameRelId);
	}

	/**
	* Sets the asset entry ID of this asset entry class name rel.
	*
	* @param assetEntryId the asset entry ID of this asset entry class name rel
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntryClassNameRel.setAssetEntryId(assetEntryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntryClassNameRel.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_assetEntryClassNameRel.setClassName(className);
	}

	/**
	* Sets the class name ID of this asset entry class name rel.
	*
	* @param classNameId the class name ID of this asset entry class name rel
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetEntryClassNameRel.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this asset entry class name rel.
	*
	* @param classPK the class pk of this asset entry class name rel
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetEntryClassNameRel.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_assetEntryClassNameRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntryClassNameRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntryClassNameRel.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntryClassNameRel.setNew(n);
	}

	/**
	* Sets the primary key of this asset entry class name rel.
	*
	* @param primaryKey the primary key of this asset entry class name rel
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntryClassNameRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntryClassNameRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntryClassNameRel> toCacheModel() {
		return _assetEntryClassNameRel.toCacheModel();
	}

	@Override
	public AssetEntryClassNameRel toEscapedModel() {
		return new AssetEntryClassNameRelWrapper(_assetEntryClassNameRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _assetEntryClassNameRel.toString();
	}

	@Override
	public AssetEntryClassNameRel toUnescapedModel() {
		return new AssetEntryClassNameRelWrapper(_assetEntryClassNameRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _assetEntryClassNameRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntryClassNameRelWrapper)) {
			return false;
		}

		AssetEntryClassNameRelWrapper assetEntryClassNameRelWrapper = (AssetEntryClassNameRelWrapper)obj;

		if (Objects.equals(_assetEntryClassNameRel,
					assetEntryClassNameRelWrapper._assetEntryClassNameRel)) {
			return true;
		}

		return false;
	}

	@Override
	public AssetEntryClassNameRel getWrappedModel() {
		return _assetEntryClassNameRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntryClassNameRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntryClassNameRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntryClassNameRel.resetOriginalValues();
	}

	private final AssetEntryClassNameRel _assetEntryClassNameRel;
}