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

package com.liferay.fragment.model;

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
 * This class is a wrapper for {@link FragmentLayoutTemplateLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentLayoutTemplateLink
 * @generated
 */
@ProviderType
public class FragmentLayoutTemplateLinkWrapper
	implements FragmentLayoutTemplateLink,
		ModelWrapper<FragmentLayoutTemplateLink> {
	public FragmentLayoutTemplateLinkWrapper(
		FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		_fragmentLayoutTemplateLink = fragmentLayoutTemplateLink;
	}

	@Override
	public Class<?> getModelClass() {
		return FragmentLayoutTemplateLink.class;
	}

	@Override
	public String getModelClassName() {
		return FragmentLayoutTemplateLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fragmentLayoutTemplateLinkId",
			getFragmentLayoutTemplateLinkId());
		attributes.put("groupId", getGroupId());
		attributes.put("fragmentEntryId", getFragmentEntryId());
		attributes.put("layoutPageTemplateEntryId",
			getLayoutPageTemplateEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fragmentLayoutTemplateLinkId = (Long)attributes.get(
				"fragmentLayoutTemplateLinkId");

		if (fragmentLayoutTemplateLinkId != null) {
			setFragmentLayoutTemplateLinkId(fragmentLayoutTemplateLinkId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long fragmentEntryId = (Long)attributes.get("fragmentEntryId");

		if (fragmentEntryId != null) {
			setFragmentEntryId(fragmentEntryId);
		}

		Long layoutPageTemplateEntryId = (Long)attributes.get(
				"layoutPageTemplateEntryId");

		if (layoutPageTemplateEntryId != null) {
			setLayoutPageTemplateEntryId(layoutPageTemplateEntryId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FragmentLayoutTemplateLinkWrapper((FragmentLayoutTemplateLink)_fragmentLayoutTemplateLink.clone());
	}

	@Override
	public int compareTo(FragmentLayoutTemplateLink fragmentLayoutTemplateLink) {
		return _fragmentLayoutTemplateLink.compareTo(fragmentLayoutTemplateLink);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fragmentLayoutTemplateLink.getExpandoBridge();
	}

	/**
	* Returns the fragment entry ID of this fragment layout template link.
	*
	* @return the fragment entry ID of this fragment layout template link
	*/
	@Override
	public long getFragmentEntryId() {
		return _fragmentLayoutTemplateLink.getFragmentEntryId();
	}

	/**
	* Returns the fragment layout template link ID of this fragment layout template link.
	*
	* @return the fragment layout template link ID of this fragment layout template link
	*/
	@Override
	public long getFragmentLayoutTemplateLinkId() {
		return _fragmentLayoutTemplateLink.getFragmentLayoutTemplateLinkId();
	}

	/**
	* Returns the group ID of this fragment layout template link.
	*
	* @return the group ID of this fragment layout template link
	*/
	@Override
	public long getGroupId() {
		return _fragmentLayoutTemplateLink.getGroupId();
	}

	/**
	* Returns the layout page template entry ID of this fragment layout template link.
	*
	* @return the layout page template entry ID of this fragment layout template link
	*/
	@Override
	public long getLayoutPageTemplateEntryId() {
		return _fragmentLayoutTemplateLink.getLayoutPageTemplateEntryId();
	}

	/**
	* Returns the primary key of this fragment layout template link.
	*
	* @return the primary key of this fragment layout template link
	*/
	@Override
	public long getPrimaryKey() {
		return _fragmentLayoutTemplateLink.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fragmentLayoutTemplateLink.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _fragmentLayoutTemplateLink.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fragmentLayoutTemplateLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fragmentLayoutTemplateLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fragmentLayoutTemplateLink.isNew();
	}

	@Override
	public void persist() {
		_fragmentLayoutTemplateLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fragmentLayoutTemplateLink.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fragmentLayoutTemplateLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fragmentLayoutTemplateLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fragmentLayoutTemplateLink.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fragment entry ID of this fragment layout template link.
	*
	* @param fragmentEntryId the fragment entry ID of this fragment layout template link
	*/
	@Override
	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentLayoutTemplateLink.setFragmentEntryId(fragmentEntryId);
	}

	/**
	* Sets the fragment layout template link ID of this fragment layout template link.
	*
	* @param fragmentLayoutTemplateLinkId the fragment layout template link ID of this fragment layout template link
	*/
	@Override
	public void setFragmentLayoutTemplateLinkId(
		long fragmentLayoutTemplateLinkId) {
		_fragmentLayoutTemplateLink.setFragmentLayoutTemplateLinkId(fragmentLayoutTemplateLinkId);
	}

	/**
	* Sets the group ID of this fragment layout template link.
	*
	* @param groupId the group ID of this fragment layout template link
	*/
	@Override
	public void setGroupId(long groupId) {
		_fragmentLayoutTemplateLink.setGroupId(groupId);
	}

	/**
	* Sets the layout page template entry ID of this fragment layout template link.
	*
	* @param layoutPageTemplateEntryId the layout page template entry ID of this fragment layout template link
	*/
	@Override
	public void setLayoutPageTemplateEntryId(long layoutPageTemplateEntryId) {
		_fragmentLayoutTemplateLink.setLayoutPageTemplateEntryId(layoutPageTemplateEntryId);
	}

	@Override
	public void setNew(boolean n) {
		_fragmentLayoutTemplateLink.setNew(n);
	}

	/**
	* Sets the primary key of this fragment layout template link.
	*
	* @param primaryKey the primary key of this fragment layout template link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fragmentLayoutTemplateLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fragmentLayoutTemplateLink.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FragmentLayoutTemplateLink> toCacheModel() {
		return _fragmentLayoutTemplateLink.toCacheModel();
	}

	@Override
	public FragmentLayoutTemplateLink toEscapedModel() {
		return new FragmentLayoutTemplateLinkWrapper(_fragmentLayoutTemplateLink.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fragmentLayoutTemplateLink.toString();
	}

	@Override
	public FragmentLayoutTemplateLink toUnescapedModel() {
		return new FragmentLayoutTemplateLinkWrapper(_fragmentLayoutTemplateLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _fragmentLayoutTemplateLink.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FragmentLayoutTemplateLinkWrapper)) {
			return false;
		}

		FragmentLayoutTemplateLinkWrapper fragmentLayoutTemplateLinkWrapper = (FragmentLayoutTemplateLinkWrapper)obj;

		if (Objects.equals(_fragmentLayoutTemplateLink,
					fragmentLayoutTemplateLinkWrapper._fragmentLayoutTemplateLink)) {
			return true;
		}

		return false;
	}

	@Override
	public FragmentLayoutTemplateLink getWrappedModel() {
		return _fragmentLayoutTemplateLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fragmentLayoutTemplateLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fragmentLayoutTemplateLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fragmentLayoutTemplateLink.resetOriginalValues();
	}

	private final FragmentLayoutTemplateLink _fragmentLayoutTemplateLink;
}