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
 * This class is a wrapper for {@link FragmentEntryLayoutTemplateLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLayoutTemplateLink
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkWrapper
	implements FragmentEntryLayoutTemplateLink,
		ModelWrapper<FragmentEntryLayoutTemplateLink> {
	public FragmentEntryLayoutTemplateLinkWrapper(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		_fragmentEntryLayoutTemplateLink = fragmentEntryLayoutTemplateLink;
	}

	@Override
	public Class<?> getModelClass() {
		return FragmentEntryLayoutTemplateLink.class;
	}

	@Override
	public String getModelClassName() {
		return FragmentEntryLayoutTemplateLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("fragmentEntryLayoutTemplateLinkId",
			getFragmentEntryLayoutTemplateLinkId());
		attributes.put("groupId", getGroupId());
		attributes.put("fragmentEntryId", getFragmentEntryId());
		attributes.put("layoutPageTemplateEntryId",
			getLayoutPageTemplateEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long fragmentEntryLayoutTemplateLinkId = (Long)attributes.get(
				"fragmentEntryLayoutTemplateLinkId");

		if (fragmentEntryLayoutTemplateLinkId != null) {
			setFragmentEntryLayoutTemplateLinkId(fragmentEntryLayoutTemplateLinkId);
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
		return new FragmentEntryLayoutTemplateLinkWrapper((FragmentEntryLayoutTemplateLink)_fragmentEntryLayoutTemplateLink.clone());
	}

	@Override
	public int compareTo(
		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink) {
		return _fragmentEntryLayoutTemplateLink.compareTo(fragmentEntryLayoutTemplateLink);
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _fragmentEntryLayoutTemplateLink.getExpandoBridge();
	}

	/**
	* Returns the fragment entry ID of this fragment entry layout template link.
	*
	* @return the fragment entry ID of this fragment entry layout template link
	*/
	@Override
	public long getFragmentEntryId() {
		return _fragmentEntryLayoutTemplateLink.getFragmentEntryId();
	}

	/**
	* Returns the fragment entry layout template link ID of this fragment entry layout template link.
	*
	* @return the fragment entry layout template link ID of this fragment entry layout template link
	*/
	@Override
	public long getFragmentEntryLayoutTemplateLinkId() {
		return _fragmentEntryLayoutTemplateLink.getFragmentEntryLayoutTemplateLinkId();
	}

	/**
	* Returns the group ID of this fragment entry layout template link.
	*
	* @return the group ID of this fragment entry layout template link
	*/
	@Override
	public long getGroupId() {
		return _fragmentEntryLayoutTemplateLink.getGroupId();
	}

	/**
	* Returns the layout page template entry ID of this fragment entry layout template link.
	*
	* @return the layout page template entry ID of this fragment entry layout template link
	*/
	@Override
	public long getLayoutPageTemplateEntryId() {
		return _fragmentEntryLayoutTemplateLink.getLayoutPageTemplateEntryId();
	}

	/**
	* Returns the primary key of this fragment entry layout template link.
	*
	* @return the primary key of this fragment entry layout template link
	*/
	@Override
	public long getPrimaryKey() {
		return _fragmentEntryLayoutTemplateLink.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _fragmentEntryLayoutTemplateLink.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _fragmentEntryLayoutTemplateLink.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _fragmentEntryLayoutTemplateLink.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _fragmentEntryLayoutTemplateLink.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _fragmentEntryLayoutTemplateLink.isNew();
	}

	@Override
	public void persist() {
		_fragmentEntryLayoutTemplateLink.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_fragmentEntryLayoutTemplateLink.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_fragmentEntryLayoutTemplateLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_fragmentEntryLayoutTemplateLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_fragmentEntryLayoutTemplateLink.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fragment entry ID of this fragment entry layout template link.
	*
	* @param fragmentEntryId the fragment entry ID of this fragment entry layout template link
	*/
	@Override
	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryLayoutTemplateLink.setFragmentEntryId(fragmentEntryId);
	}

	/**
	* Sets the fragment entry layout template link ID of this fragment entry layout template link.
	*
	* @param fragmentEntryLayoutTemplateLinkId the fragment entry layout template link ID of this fragment entry layout template link
	*/
	@Override
	public void setFragmentEntryLayoutTemplateLinkId(
		long fragmentEntryLayoutTemplateLinkId) {
		_fragmentEntryLayoutTemplateLink.setFragmentEntryLayoutTemplateLinkId(fragmentEntryLayoutTemplateLinkId);
	}

	/**
	* Sets the group ID of this fragment entry layout template link.
	*
	* @param groupId the group ID of this fragment entry layout template link
	*/
	@Override
	public void setGroupId(long groupId) {
		_fragmentEntryLayoutTemplateLink.setGroupId(groupId);
	}

	/**
	* Sets the layout page template entry ID of this fragment entry layout template link.
	*
	* @param layoutPageTemplateEntryId the layout page template entry ID of this fragment entry layout template link
	*/
	@Override
	public void setLayoutPageTemplateEntryId(long layoutPageTemplateEntryId) {
		_fragmentEntryLayoutTemplateLink.setLayoutPageTemplateEntryId(layoutPageTemplateEntryId);
	}

	@Override
	public void setNew(boolean n) {
		_fragmentEntryLayoutTemplateLink.setNew(n);
	}

	/**
	* Sets the primary key of this fragment entry layout template link.
	*
	* @param primaryKey the primary key of this fragment entry layout template link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_fragmentEntryLayoutTemplateLink.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_fragmentEntryLayoutTemplateLink.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<FragmentEntryLayoutTemplateLink> toCacheModel() {
		return _fragmentEntryLayoutTemplateLink.toCacheModel();
	}

	@Override
	public FragmentEntryLayoutTemplateLink toEscapedModel() {
		return new FragmentEntryLayoutTemplateLinkWrapper(_fragmentEntryLayoutTemplateLink.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _fragmentEntryLayoutTemplateLink.toString();
	}

	@Override
	public FragmentEntryLayoutTemplateLink toUnescapedModel() {
		return new FragmentEntryLayoutTemplateLinkWrapper(_fragmentEntryLayoutTemplateLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _fragmentEntryLayoutTemplateLink.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FragmentEntryLayoutTemplateLinkWrapper)) {
			return false;
		}

		FragmentEntryLayoutTemplateLinkWrapper fragmentEntryLayoutTemplateLinkWrapper =
			(FragmentEntryLayoutTemplateLinkWrapper)obj;

		if (Objects.equals(_fragmentEntryLayoutTemplateLink,
					fragmentEntryLayoutTemplateLinkWrapper._fragmentEntryLayoutTemplateLink)) {
			return true;
		}

		return false;
	}

	@Override
	public FragmentEntryLayoutTemplateLink getWrappedModel() {
		return _fragmentEntryLayoutTemplateLink;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _fragmentEntryLayoutTemplateLink.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _fragmentEntryLayoutTemplateLink.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_fragmentEntryLayoutTemplateLink.resetOriginalValues();
	}

	private final FragmentEntryLayoutTemplateLink _fragmentEntryLayoutTemplateLink;
}