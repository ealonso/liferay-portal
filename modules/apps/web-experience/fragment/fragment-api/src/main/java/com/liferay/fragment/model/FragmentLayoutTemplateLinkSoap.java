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
public class FragmentLayoutTemplateLinkSoap implements Serializable {
	public static FragmentLayoutTemplateLinkSoap toSoapModel(
		FragmentLayoutTemplateLink model) {
		FragmentLayoutTemplateLinkSoap soapModel = new FragmentLayoutTemplateLinkSoap();

		soapModel.setFragmentLayoutTemplateLinkId(model.getFragmentLayoutTemplateLinkId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setFragmentEntryId(model.getFragmentEntryId());
		soapModel.setLayoutPageTemplateEntryId(model.getLayoutPageTemplateEntryId());

		return soapModel;
	}

	public static FragmentLayoutTemplateLinkSoap[] toSoapModels(
		FragmentLayoutTemplateLink[] models) {
		FragmentLayoutTemplateLinkSoap[] soapModels = new FragmentLayoutTemplateLinkSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FragmentLayoutTemplateLinkSoap[][] toSoapModels(
		FragmentLayoutTemplateLink[][] models) {
		FragmentLayoutTemplateLinkSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FragmentLayoutTemplateLinkSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FragmentLayoutTemplateLinkSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FragmentLayoutTemplateLinkSoap[] toSoapModels(
		List<FragmentLayoutTemplateLink> models) {
		List<FragmentLayoutTemplateLinkSoap> soapModels = new ArrayList<FragmentLayoutTemplateLinkSoap>(models.size());

		for (FragmentLayoutTemplateLink model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FragmentLayoutTemplateLinkSoap[soapModels.size()]);
	}

	public FragmentLayoutTemplateLinkSoap() {
	}

	public long getPrimaryKey() {
		return _fragmentLayoutTemplateLinkId;
	}

	public void setPrimaryKey(long pk) {
		setFragmentLayoutTemplateLinkId(pk);
	}

	public long getFragmentLayoutTemplateLinkId() {
		return _fragmentLayoutTemplateLinkId;
	}

	public void setFragmentLayoutTemplateLinkId(
		long fragmentLayoutTemplateLinkId) {
		_fragmentLayoutTemplateLinkId = fragmentLayoutTemplateLinkId;
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

	private long _fragmentLayoutTemplateLinkId;
	private long _groupId;
	private long _fragmentEntryId;
	private long _layoutPageTemplateEntryId;
}