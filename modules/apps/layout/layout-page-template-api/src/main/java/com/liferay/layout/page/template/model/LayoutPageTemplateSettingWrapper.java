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

package com.liferay.layout.page.template.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LayoutPageTemplateSetting}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSetting
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingWrapper
	implements LayoutPageTemplateSetting,
		ModelWrapper<LayoutPageTemplateSetting> {
	public LayoutPageTemplateSettingWrapper(
		LayoutPageTemplateSetting layoutPageTemplateSetting) {
		_layoutPageTemplateSetting = layoutPageTemplateSetting;
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPageTemplateSetting.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPageTemplateSetting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("layoutPageTemplateSettingId",
			getLayoutPageTemplateSettingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("settings", getSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long layoutPageTemplateSettingId = (Long)attributes.get(
				"layoutPageTemplateSettingId");

		if (layoutPageTemplateSettingId != null) {
			setLayoutPageTemplateSettingId(layoutPageTemplateSettingId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String settings = (String)attributes.get("settings");

		if (settings != null) {
			setSettings(settings);
		}
	}

	@Override
	public Object clone() {
		return new LayoutPageTemplateSettingWrapper((LayoutPageTemplateSetting)_layoutPageTemplateSetting.clone());
	}

	@Override
	public int compareTo(LayoutPageTemplateSetting layoutPageTemplateSetting) {
		return _layoutPageTemplateSetting.compareTo(layoutPageTemplateSetting);
	}

	/**
	* Returns the fully qualified class name of this layout page template setting.
	*
	* @return the fully qualified class name of this layout page template setting
	*/
	@Override
	public String getClassName() {
		return _layoutPageTemplateSetting.getClassName();
	}

	/**
	* Returns the class name ID of this layout page template setting.
	*
	* @return the class name ID of this layout page template setting
	*/
	@Override
	public long getClassNameId() {
		return _layoutPageTemplateSetting.getClassNameId();
	}

	/**
	* Returns the class pk of this layout page template setting.
	*
	* @return the class pk of this layout page template setting
	*/
	@Override
	public long getClassPK() {
		return _layoutPageTemplateSetting.getClassPK();
	}

	/**
	* Returns the company ID of this layout page template setting.
	*
	* @return the company ID of this layout page template setting
	*/
	@Override
	public long getCompanyId() {
		return _layoutPageTemplateSetting.getCompanyId();
	}

	/**
	* Returns the create date of this layout page template setting.
	*
	* @return the create date of this layout page template setting
	*/
	@Override
	public Date getCreateDate() {
		return _layoutPageTemplateSetting.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _layoutPageTemplateSetting.getExpandoBridge();
	}

	/**
	* Returns the group ID of this layout page template setting.
	*
	* @return the group ID of this layout page template setting
	*/
	@Override
	public long getGroupId() {
		return _layoutPageTemplateSetting.getGroupId();
	}

	/**
	* Returns the layout page template setting ID of this layout page template setting.
	*
	* @return the layout page template setting ID of this layout page template setting
	*/
	@Override
	public long getLayoutPageTemplateSettingId() {
		return _layoutPageTemplateSetting.getLayoutPageTemplateSettingId();
	}

	/**
	* Returns the modified date of this layout page template setting.
	*
	* @return the modified date of this layout page template setting
	*/
	@Override
	public Date getModifiedDate() {
		return _layoutPageTemplateSetting.getModifiedDate();
	}

	/**
	* Returns the primary key of this layout page template setting.
	*
	* @return the primary key of this layout page template setting
	*/
	@Override
	public long getPrimaryKey() {
		return _layoutPageTemplateSetting.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPageTemplateSetting.getPrimaryKeyObj();
	}

	/**
	* Returns the settings of this layout page template setting.
	*
	* @return the settings of this layout page template setting
	*/
	@Override
	public String getSettings() {
		return _layoutPageTemplateSetting.getSettings();
	}

	/**
	* Returns the user ID of this layout page template setting.
	*
	* @return the user ID of this layout page template setting
	*/
	@Override
	public long getUserId() {
		return _layoutPageTemplateSetting.getUserId();
	}

	/**
	* Returns the user name of this layout page template setting.
	*
	* @return the user name of this layout page template setting
	*/
	@Override
	public String getUserName() {
		return _layoutPageTemplateSetting.getUserName();
	}

	/**
	* Returns the user uuid of this layout page template setting.
	*
	* @return the user uuid of this layout page template setting
	*/
	@Override
	public String getUserUuid() {
		return _layoutPageTemplateSetting.getUserUuid();
	}

	/**
	* Returns the uuid of this layout page template setting.
	*
	* @return the uuid of this layout page template setting
	*/
	@Override
	public String getUuid() {
		return _layoutPageTemplateSetting.getUuid();
	}

	@Override
	public int hashCode() {
		return _layoutPageTemplateSetting.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _layoutPageTemplateSetting.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _layoutPageTemplateSetting.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _layoutPageTemplateSetting.isNew();
	}

	@Override
	public void persist() {
		_layoutPageTemplateSetting.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_layoutPageTemplateSetting.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_layoutPageTemplateSetting.setClassName(className);
	}

	/**
	* Sets the class name ID of this layout page template setting.
	*
	* @param classNameId the class name ID of this layout page template setting
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_layoutPageTemplateSetting.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this layout page template setting.
	*
	* @param classPK the class pk of this layout page template setting
	*/
	@Override
	public void setClassPK(long classPK) {
		_layoutPageTemplateSetting.setClassPK(classPK);
	}

	/**
	* Sets the company ID of this layout page template setting.
	*
	* @param companyId the company ID of this layout page template setting
	*/
	@Override
	public void setCompanyId(long companyId) {
		_layoutPageTemplateSetting.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this layout page template setting.
	*
	* @param createDate the create date of this layout page template setting
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_layoutPageTemplateSetting.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_layoutPageTemplateSetting.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_layoutPageTemplateSetting.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_layoutPageTemplateSetting.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this layout page template setting.
	*
	* @param groupId the group ID of this layout page template setting
	*/
	@Override
	public void setGroupId(long groupId) {
		_layoutPageTemplateSetting.setGroupId(groupId);
	}

	/**
	* Sets the layout page template setting ID of this layout page template setting.
	*
	* @param layoutPageTemplateSettingId the layout page template setting ID of this layout page template setting
	*/
	@Override
	public void setLayoutPageTemplateSettingId(long layoutPageTemplateSettingId) {
		_layoutPageTemplateSetting.setLayoutPageTemplateSettingId(layoutPageTemplateSettingId);
	}

	/**
	* Sets the modified date of this layout page template setting.
	*
	* @param modifiedDate the modified date of this layout page template setting
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_layoutPageTemplateSetting.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_layoutPageTemplateSetting.setNew(n);
	}

	/**
	* Sets the primary key of this layout page template setting.
	*
	* @param primaryKey the primary key of this layout page template setting
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_layoutPageTemplateSetting.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_layoutPageTemplateSetting.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the settings of this layout page template setting.
	*
	* @param settings the settings of this layout page template setting
	*/
	@Override
	public void setSettings(String settings) {
		_layoutPageTemplateSetting.setSettings(settings);
	}

	/**
	* Sets the user ID of this layout page template setting.
	*
	* @param userId the user ID of this layout page template setting
	*/
	@Override
	public void setUserId(long userId) {
		_layoutPageTemplateSetting.setUserId(userId);
	}

	/**
	* Sets the user name of this layout page template setting.
	*
	* @param userName the user name of this layout page template setting
	*/
	@Override
	public void setUserName(String userName) {
		_layoutPageTemplateSetting.setUserName(userName);
	}

	/**
	* Sets the user uuid of this layout page template setting.
	*
	* @param userUuid the user uuid of this layout page template setting
	*/
	@Override
	public void setUserUuid(String userUuid) {
		_layoutPageTemplateSetting.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this layout page template setting.
	*
	* @param uuid the uuid of this layout page template setting
	*/
	@Override
	public void setUuid(String uuid) {
		_layoutPageTemplateSetting.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LayoutPageTemplateSetting> toCacheModel() {
		return _layoutPageTemplateSetting.toCacheModel();
	}

	@Override
	public LayoutPageTemplateSetting toEscapedModel() {
		return new LayoutPageTemplateSettingWrapper(_layoutPageTemplateSetting.toEscapedModel());
	}

	@Override
	public String toString() {
		return _layoutPageTemplateSetting.toString();
	}

	@Override
	public LayoutPageTemplateSetting toUnescapedModel() {
		return new LayoutPageTemplateSettingWrapper(_layoutPageTemplateSetting.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _layoutPageTemplateSetting.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutPageTemplateSettingWrapper)) {
			return false;
		}

		LayoutPageTemplateSettingWrapper layoutPageTemplateSettingWrapper = (LayoutPageTemplateSettingWrapper)obj;

		if (Objects.equals(_layoutPageTemplateSetting,
					layoutPageTemplateSettingWrapper._layoutPageTemplateSetting)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _layoutPageTemplateSetting.getStagedModelType();
	}

	@Override
	public LayoutPageTemplateSetting getWrappedModel() {
		return _layoutPageTemplateSetting;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _layoutPageTemplateSetting.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _layoutPageTemplateSetting.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_layoutPageTemplateSetting.resetOriginalValues();
	}

	private final LayoutPageTemplateSetting _layoutPageTemplateSetting;
}