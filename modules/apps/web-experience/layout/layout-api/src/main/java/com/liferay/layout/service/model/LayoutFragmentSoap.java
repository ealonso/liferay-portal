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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.layout.service.service.http.LayoutFragmentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.layout.service.service.http.LayoutFragmentServiceSoap
 * @generated
 */
@ProviderType
public class LayoutFragmentSoap implements Serializable {
	public static LayoutFragmentSoap toSoapModel(LayoutFragment model) {
		LayoutFragmentSoap soapModel = new LayoutFragmentSoap();

		soapModel.setLayoutFragmentId(model.getLayoutFragmentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPlid(model.getPlid());
		soapModel.setFragmentEntryId(model.getFragmentEntryId());
		soapModel.setCss(model.getCss());
		soapModel.setHtml(model.getHtml());
		soapModel.setJs(model.getJs());
		soapModel.setPosition(model.getPosition());
		soapModel.setTypeSettings(model.getTypeSettings());

		return soapModel;
	}

	public static LayoutFragmentSoap[] toSoapModels(LayoutFragment[] models) {
		LayoutFragmentSoap[] soapModels = new LayoutFragmentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LayoutFragmentSoap[][] toSoapModels(LayoutFragment[][] models) {
		LayoutFragmentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LayoutFragmentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LayoutFragmentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LayoutFragmentSoap[] toSoapModels(List<LayoutFragment> models) {
		List<LayoutFragmentSoap> soapModels = new ArrayList<LayoutFragmentSoap>(models.size());

		for (LayoutFragment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LayoutFragmentSoap[soapModels.size()]);
	}

	public LayoutFragmentSoap() {
	}

	public long getPrimaryKey() {
		return _layoutFragmentId;
	}

	public void setPrimaryKey(long pk) {
		setLayoutFragmentId(pk);
	}

	public long getLayoutFragmentId() {
		return _layoutFragmentId;
	}

	public void setLayoutFragmentId(long layoutFragmentId) {
		_layoutFragmentId = layoutFragmentId;
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

	public long getPlid() {
		return _plid;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public long getFragmentEntryId() {
		return _fragmentEntryId;
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public String getCss() {
		return _css;
	}

	public void setCss(String css) {
		_css = css;
	}

	public String getHtml() {
		return _html;
	}

	public void setHtml(String html) {
		_html = html;
	}

	public String getJs() {
		return _js;
	}

	public void setJs(String js) {
		_js = js;
	}

	public int getPosition() {
		return _position;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	private long _layoutFragmentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _plid;
	private long _fragmentEntryId;
	private String _css;
	private String _html;
	private String _js;
	private int _position;
	private String _typeSettings;
}