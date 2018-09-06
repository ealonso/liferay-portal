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

package com.liferay.layout.page.template.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing LayoutPageTemplateSetting in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateSetting
 * @generated
 */
@ProviderType
public class LayoutPageTemplateSettingCacheModel implements CacheModel<LayoutPageTemplateSetting>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutPageTemplateSettingCacheModel)) {
			return false;
		}

		LayoutPageTemplateSettingCacheModel layoutPageTemplateSettingCacheModel = (LayoutPageTemplateSettingCacheModel)obj;

		if (layoutPageTemplateSettingId == layoutPageTemplateSettingCacheModel.layoutPageTemplateSettingId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, layoutPageTemplateSettingId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", layoutPageTemplateSettingId=");
		sb.append(layoutPageTemplateSettingId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", settings=");
		sb.append(settings);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutPageTemplateSetting toEntityModel() {
		LayoutPageTemplateSettingImpl layoutPageTemplateSettingImpl = new LayoutPageTemplateSettingImpl();

		if (uuid == null) {
			layoutPageTemplateSettingImpl.setUuid("");
		}
		else {
			layoutPageTemplateSettingImpl.setUuid(uuid);
		}

		layoutPageTemplateSettingImpl.setLayoutPageTemplateSettingId(layoutPageTemplateSettingId);
		layoutPageTemplateSettingImpl.setGroupId(groupId);
		layoutPageTemplateSettingImpl.setCompanyId(companyId);
		layoutPageTemplateSettingImpl.setUserId(userId);

		if (userName == null) {
			layoutPageTemplateSettingImpl.setUserName("");
		}
		else {
			layoutPageTemplateSettingImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			layoutPageTemplateSettingImpl.setCreateDate(null);
		}
		else {
			layoutPageTemplateSettingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			layoutPageTemplateSettingImpl.setModifiedDate(null);
		}
		else {
			layoutPageTemplateSettingImpl.setModifiedDate(new Date(modifiedDate));
		}

		layoutPageTemplateSettingImpl.setClassNameId(classNameId);
		layoutPageTemplateSettingImpl.setClassPK(classPK);

		if (settings == null) {
			layoutPageTemplateSettingImpl.setSettings("");
		}
		else {
			layoutPageTemplateSettingImpl.setSettings(settings);
		}

		layoutPageTemplateSettingImpl.resetOriginalValues();

		return layoutPageTemplateSettingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		layoutPageTemplateSettingId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		settings = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(layoutPageTemplateSettingId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (settings == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(settings);
		}
	}

	public String uuid;
	public long layoutPageTemplateSettingId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String settings;
}