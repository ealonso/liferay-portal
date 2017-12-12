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

package com.liferay.fragment.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing FragmentEntryLayoutTemplateLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see FragmentEntryLayoutTemplateLink
 * @generated
 */
@ProviderType
public class FragmentEntryLayoutTemplateLinkCacheModel implements CacheModel<FragmentEntryLayoutTemplateLink>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FragmentEntryLayoutTemplateLinkCacheModel)) {
			return false;
		}

		FragmentEntryLayoutTemplateLinkCacheModel fragmentEntryLayoutTemplateLinkCacheModel =
			(FragmentEntryLayoutTemplateLinkCacheModel)obj;

		if (fragmentEntryLayoutTemplateLinkId == fragmentEntryLayoutTemplateLinkCacheModel.fragmentEntryLayoutTemplateLinkId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, fragmentEntryLayoutTemplateLinkId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{fragmentEntryLayoutTemplateLinkId=");
		sb.append(fragmentEntryLayoutTemplateLinkId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", fragmentEntryId=");
		sb.append(fragmentEntryId);
		sb.append(", layoutPageTemplateEntryId=");
		sb.append(layoutPageTemplateEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FragmentEntryLayoutTemplateLink toEntityModel() {
		FragmentEntryLayoutTemplateLinkImpl fragmentEntryLayoutTemplateLinkImpl = new FragmentEntryLayoutTemplateLinkImpl();

		fragmentEntryLayoutTemplateLinkImpl.setFragmentEntryLayoutTemplateLinkId(fragmentEntryLayoutTemplateLinkId);
		fragmentEntryLayoutTemplateLinkImpl.setGroupId(groupId);
		fragmentEntryLayoutTemplateLinkImpl.setFragmentEntryId(fragmentEntryId);
		fragmentEntryLayoutTemplateLinkImpl.setLayoutPageTemplateEntryId(layoutPageTemplateEntryId);

		fragmentEntryLayoutTemplateLinkImpl.resetOriginalValues();

		return fragmentEntryLayoutTemplateLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		fragmentEntryLayoutTemplateLinkId = objectInput.readLong();

		groupId = objectInput.readLong();

		fragmentEntryId = objectInput.readLong();

		layoutPageTemplateEntryId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(fragmentEntryLayoutTemplateLinkId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(fragmentEntryId);

		objectOutput.writeLong(layoutPageTemplateEntryId);
	}

	public long fragmentEntryLayoutTemplateLinkId;
	public long groupId;
	public long fragmentEntryId;
	public long layoutPageTemplateEntryId;
}