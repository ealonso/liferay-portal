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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingSoap implements Serializable {
	public static LayoutPageTemplateSettingSoap toSoapModel(
		LayoutPageTemplateSetting model) {
		LayoutPageTemplateSettingSoap soapModel = new LayoutPageTemplateSettingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setLayoutPageTemplateSettingId(model.getLayoutPageTemplateSettingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setSettings(model.getSettings());

		return soapModel;
	}

	public static LayoutPageTemplateSettingSoap[] toSoapModels(
		LayoutPageTemplateSetting[] models) {
		LayoutPageTemplateSettingSoap[] soapModels = new LayoutPageTemplateSettingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutPageTemplateSettingSoap[][] toSoapModels(
		LayoutPageTemplateSetting[][] models) {
		LayoutPageTemplateSettingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutPageTemplateSettingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutPageTemplateSettingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutPageTemplateSettingSoap[] toSoapModels(
		List<LayoutPageTemplateSetting> models) {
		List<LayoutPageTemplateSettingSoap> soapModels = new ArrayList<LayoutPageTemplateSettingSoap>(models.size());

		for (LayoutPageTemplateSetting model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LayoutPageTemplateSettingSoap[soapModels.size()]);
	}

	public LayoutPageTemplateSettingSoap() {
	}

	public long getPrimaryKey() {
		return _layoutPageTemplateSettingId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutPageTemplateSettingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getLayoutPageTemplateSettingId() {
		return _layoutPageTemplateSettingId;
	}

	public void setLayoutPageTemplateSettingId(long layoutPageTemplateSettingId) {
		_layoutPageTemplateSettingId = layoutPageTemplateSettingId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getSettings() {
		return _settings;
	}

	public void setSettings(String settings) {
		_settings = settings;
	}

	private String _uuid;
	private long _layoutPageTemplateSettingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _settings;
}