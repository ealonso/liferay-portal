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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkSoap implements Serializable {
	public static FragmentEntryLayoutTemplateLinkSoap toSoapModel(
		FragmentEntryLayoutTemplateLink model) {
		FragmentEntryLayoutTemplateLinkSoap soapModel = new FragmentEntryLayoutTemplateLinkSoap();

		soapModel.setFragmentEntryLayoutTemplateLinkId(model.getFragmentEntryLayoutTemplateLinkId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setFragmentEntryId(model.getFragmentEntryId());
		soapModel.setLayoutPageTemplateEntryId(model.getLayoutPageTemplateEntryId());

		return soapModel;
	}

	public static FragmentEntryLayoutTemplateLinkSoap[] toSoapModels(
		FragmentEntryLayoutTemplateLink[] models) {
		FragmentEntryLayoutTemplateLinkSoap[] soapModels = new FragmentEntryLayoutTemplateLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FragmentEntryLayoutTemplateLinkSoap[][] toSoapModels(
		FragmentEntryLayoutTemplateLink[][] models) {
		FragmentEntryLayoutTemplateLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FragmentEntryLayoutTemplateLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FragmentEntryLayoutTemplateLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FragmentEntryLayoutTemplateLinkSoap[] toSoapModels(
		List<FragmentEntryLayoutTemplateLink> models) {
		List<FragmentEntryLayoutTemplateLinkSoap> soapModels = new ArrayList<FragmentEntryLayoutTemplateLinkSoap>(models.size());

		for (FragmentEntryLayoutTemplateLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FragmentEntryLayoutTemplateLinkSoap[soapModels.size()]);
	}

	public FragmentEntryLayoutTemplateLinkSoap() {
	}

	public long getPrimaryKey() {
		return _fragmentEntryLayoutTemplateLinkId;
	}

	public void setPrimaryKey(long pk) {
		setFragmentEntryLayoutTemplateLinkId(pk);
	}

	public long getFragmentEntryLayoutTemplateLinkId() {
		return _fragmentEntryLayoutTemplateLinkId;
	}

	public void setFragmentEntryLayoutTemplateLinkId(
		long fragmentEntryLayoutTemplateLinkId) {
		_fragmentEntryLayoutTemplateLinkId = fragmentEntryLayoutTemplateLinkId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getFragmentEntryId() {
		return _fragmentEntryId;
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public long getLayoutPageTemplateEntryId() {
		return _layoutPageTemplateEntryId;
	}

	public void setLayoutPageTemplateEntryId(long layoutPageTemplateEntryId) {
		_layoutPageTemplateEntryId = layoutPageTemplateEntryId;
	}

	private long _fragmentEntryLayoutTemplateLinkId;
	private long _groupId;
	private long _fragmentEntryId;
	private long _layoutPageTemplateEntryId;
}