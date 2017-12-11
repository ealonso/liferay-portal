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

package com.liferay.layout.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LayoutFragment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragment
 * @generated
 */
@ProviderType
public class LayoutFragmentWrapper implements LayoutFragment,
	ModelWrapper<LayoutFragment> {
	public LayoutFragmentWrapper(LayoutFragment layoutFragment) {
		_layoutFragment = layoutFragment;
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutFragment.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutFragment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("layoutFragmentId", getLayoutFragmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("plid", getPlid());
		attributes.put("fragmentEntryId", getFragmentEntryId());
		attributes.put("css", getCss());
		attributes.put("html", getHtml());
		attributes.put("js", getJs());
		attributes.put("position", getPosition());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long layoutFragmentId = (Long)attributes.get("layoutFragmentId");

		if (layoutFragmentId != null) {
			setLayoutFragmentId(layoutFragmentId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long plid = (Long)attributes.get("plid");

		if (plid != null) {
			setPlid(plid);
		}

		Long fragmentEntryId = (Long)attributes.get("fragmentEntryId");

		if (fragmentEntryId != null) {
			setFragmentEntryId(fragmentEntryId);
		}

		String css = (String)attributes.get("css");

		if (css != null) {
			setCss(css);
		}

		String html = (String)attributes.get("html");

		if (html != null) {
			setHtml(html);
		}

		String js = (String)attributes.get("js");

		if (js != null) {
			setJs(js);
		}

		Integer position = (Integer)attributes.get("position");

		if (position != null) {
			setPosition(position);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new LayoutFragmentWrapper((LayoutFragment)_layoutFragment.clone());
	}

	@Override
	public int compareTo(LayoutFragment layoutFragment) {
		return _layoutFragment.compareTo(layoutFragment);
	}

	/**
	* Returns the company ID of this layout fragment.
	*
	* @return the company ID of this layout fragment
	*/
	@Override
	public long getCompanyId() {
		return _layoutFragment.getCompanyId();
	}

	/**
	* Returns the create date of this layout fragment.
	*
	* @return the create date of this layout fragment
	*/
	@Override
	public Date getCreateDate() {
		return _layoutFragment.getCreateDate();
	}

	/**
	* Returns the css of this layout fragment.
	*
	* @return the css of this layout fragment
	*/
	@Override
	public java.lang.String getCss() {
		return _layoutFragment.getCss();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _layoutFragment.getExpandoBridge();
	}

	/**
	* Returns the fragment entry ID of this layout fragment.
	*
	* @return the fragment entry ID of this layout fragment
	*/
	@Override
	public long getFragmentEntryId() {
		return _layoutFragment.getFragmentEntryId();
	}

	/**
	* Returns the group ID of this layout fragment.
	*
	* @return the group ID of this layout fragment
	*/
	@Override
	public long getGroupId() {
		return _layoutFragment.getGroupId();
	}

	/**
	* Returns the html of this layout fragment.
	*
	* @return the html of this layout fragment
	*/
	@Override
	public java.lang.String getHtml() {
		return _layoutFragment.getHtml();
	}

	/**
	* Returns the js of this layout fragment.
	*
	* @return the js of this layout fragment
	*/
	@Override
	public java.lang.String getJs() {
		return _layoutFragment.getJs();
	}

	/**
	* Returns the layout fragment ID of this layout fragment.
	*
	* @return the layout fragment ID of this layout fragment
	*/
	@Override
	public long getLayoutFragmentId() {
		return _layoutFragment.getLayoutFragmentId();
	}

	/**
	* Returns the modified date of this layout fragment.
	*
	* @return the modified date of this layout fragment
	*/
	@Override
	public Date getModifiedDate() {
		return _layoutFragment.getModifiedDate();
	}

	/**
	* Returns the plid of this layout fragment.
	*
	* @return the plid of this layout fragment
	*/
	@Override
	public long getPlid() {
		return _layoutFragment.getPlid();
	}

	/**
	* Returns the position of this layout fragment.
	*
	* @return the position of this layout fragment
	*/
	@Override
	public int getPosition() {
		return _layoutFragment.getPosition();
	}

	/**
	* Returns the primary key of this layout fragment.
	*
	* @return the primary key of this layout fragment
	*/
	@Override
	public long getPrimaryKey() {
		return _layoutFragment.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutFragment.getPrimaryKeyObj();
	}

	/**
	* Returns the type settings of this layout fragment.
	*
	* @return the type settings of this layout fragment
	*/
	@Override
	public java.lang.String getTypeSettings() {
		return _layoutFragment.getTypeSettings();
	}

	/**
	* Returns the user ID of this layout fragment.
	*
	* @return the user ID of this layout fragment
	*/
	@Override
	public long getUserId() {
		return _layoutFragment.getUserId();
	}

	/**
	* Returns the user name of this layout fragment.
	*
	* @return the user name of this layout fragment
	*/
	@Override
	public java.lang.String getUserName() {
		return _layoutFragment.getUserName();
	}

	/**
	* Returns the user uuid of this layout fragment.
	*
	* @return the user uuid of this layout fragment
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _layoutFragment.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _layoutFragment.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _layoutFragment.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _layoutFragment.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _layoutFragment.isNew();
	}

	@Override
	public void persist() {
		_layoutFragment.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_layoutFragment.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this layout fragment.
	*
	* @param companyId the company ID of this layout fragment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_layoutFragment.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this layout fragment.
	*
	* @param createDate the create date of this layout fragment
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_layoutFragment.setCreateDate(createDate);
	}

	/**
	* Sets the css of this layout fragment.
	*
	* @param css the css of this layout fragment
	*/
	@Override
	public void setCss(java.lang.String css) {
		_layoutFragment.setCss(css);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_layoutFragment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_layoutFragment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_layoutFragment.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fragment entry ID of this layout fragment.
	*
	* @param fragmentEntryId the fragment entry ID of this layout fragment
	*/
	@Override
	public void setFragmentEntryId(long fragmentEntryId) {
		_layoutFragment.setFragmentEntryId(fragmentEntryId);
	}

	/**
	* Sets the group ID of this layout fragment.
	*
	* @param groupId the group ID of this layout fragment
	*/
	@Override
	public void setGroupId(long groupId) {
		_layoutFragment.setGroupId(groupId);
	}

	/**
	* Sets the html of this layout fragment.
	*
	* @param html the html of this layout fragment
	*/
	@Override
	public void setHtml(java.lang.String html) {
		_layoutFragment.setHtml(html);
	}

	/**
	* Sets the js of this layout fragment.
	*
	* @param js the js of this layout fragment
	*/
	@Override
	public void setJs(java.lang.String js) {
		_layoutFragment.setJs(js);
	}

	/**
	* Sets the layout fragment ID of this layout fragment.
	*
	* @param layoutFragmentId the layout fragment ID of this layout fragment
	*/
	@Override
	public void setLayoutFragmentId(long layoutFragmentId) {
		_layoutFragment.setLayoutFragmentId(layoutFragmentId);
	}

	/**
	* Sets the modified date of this layout fragment.
	*
	* @param modifiedDate the modified date of this layout fragment
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_layoutFragment.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_layoutFragment.setNew(n);
	}

	/**
	* Sets the plid of this layout fragment.
	*
	* @param plid the plid of this layout fragment
	*/
	@Override
	public void setPlid(long plid) {
		_layoutFragment.setPlid(plid);
	}

	/**
	* Sets the position of this layout fragment.
	*
	* @param position the position of this layout fragment
	*/
	@Override
	public void setPosition(int position) {
		_layoutFragment.setPosition(position);
	}

	/**
	* Sets the primary key of this layout fragment.
	*
	* @param primaryKey the primary key of this layout fragment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_layoutFragment.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_layoutFragment.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type settings of this layout fragment.
	*
	* @param typeSettings the type settings of this layout fragment
	*/
	@Override
	public void setTypeSettings(java.lang.String typeSettings) {
		_layoutFragment.setTypeSettings(typeSettings);
	}

	/**
	* Sets the user ID of this layout fragment.
	*
	* @param userId the user ID of this layout fragment
	*/
	@Override
	public void setUserId(long userId) {
		_layoutFragment.setUserId(userId);
	}

	/**
	* Sets the user name of this layout fragment.
	*
	* @param userName the user name of this layout fragment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_layoutFragment.setUserName(userName);
	}

	/**
	* Sets the user uuid of this layout fragment.
	*
	* @param userUuid the user uuid of this layout fragment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_layoutFragment.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LayoutFragment> toCacheModel() {
		return _layoutFragment.toCacheModel();
	}

	@Override
	public LayoutFragment toEscapedModel() {
		return new LayoutFragmentWrapper(_layoutFragment.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _layoutFragment.toString();
	}

	@Override
	public LayoutFragment toUnescapedModel() {
		return new LayoutFragmentWrapper(_layoutFragment.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _layoutFragment.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutFragmentWrapper)) {
			return false;
		}

		LayoutFragmentWrapper layoutFragmentWrapper = (LayoutFragmentWrapper)obj;

		if (Objects.equals(_layoutFragment,
					layoutFragmentWrapper._layoutFragment)) {
			return true;
		}

		return false;
	}

	@Override
	public LayoutFragment getWrappedModel() {
		return _layoutFragment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _layoutFragment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _layoutFragment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_layoutFragment.resetOriginalValues();
	}

	private final LayoutFragment _layoutFragment;
}