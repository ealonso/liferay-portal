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

import com.liferay.fragment.model.LayoutFragment;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LayoutFragment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragment
 * @generated
 */
@ProviderType
public class LayoutFragmentCacheModel implements CacheModel<LayoutFragment>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutFragmentCacheModel)) {
			return false;
		}

		LayoutFragmentCacheModel layoutFragmentCacheModel = (LayoutFragmentCacheModel)obj;

		if (layoutFragmentId == layoutFragmentCacheModel.layoutFragmentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, layoutFragmentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{layoutFragmentId=");
		sb.append(layoutFragmentId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", plid=");
		sb.append(plid);
		sb.append(", fragmentEntryId=");
		sb.append(fragmentEntryId);
		sb.append(", css=");
		sb.append(css);
		sb.append(", html=");
		sb.append(html);
		sb.append(", js=");
		sb.append(js);
		sb.append(", editableValues=");
		sb.append(editableValues);
		sb.append(", position=");
		sb.append(position);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LayoutFragment toEntityModel() {
		LayoutFragmentImpl layoutFragmentImpl = new LayoutFragmentImpl();

		layoutFragmentImpl.setLayoutFragmentId(layoutFragmentId);
		layoutFragmentImpl.setGroupId(groupId);
		layoutFragmentImpl.setPlid(plid);
		layoutFragmentImpl.setFragmentEntryId(fragmentEntryId);

		if (css == null) {
			layoutFragmentImpl.setCss("");
		}
		else {
			layoutFragmentImpl.setCss(css);
		}

		if (html == null) {
			layoutFragmentImpl.setHtml("");
		}
		else {
			layoutFragmentImpl.setHtml(html);
		}

		if (js == null) {
			layoutFragmentImpl.setJs("");
		}
		else {
			layoutFragmentImpl.setJs(js);
		}

		if (editableValues == null) {
			layoutFragmentImpl.setEditableValues("");
		}
		else {
			layoutFragmentImpl.setEditableValues(editableValues);
		}

		layoutFragmentImpl.setPosition(position);

		layoutFragmentImpl.resetOriginalValues();

		return layoutFragmentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		layoutFragmentId = objectInput.readLong();

		groupId = objectInput.readLong();

		plid = objectInput.readLong();

		fragmentEntryId = objectInput.readLong();
		css = objectInput.readUTF();
		html = objectInput.readUTF();
		js = objectInput.readUTF();
		editableValues = objectInput.readUTF();

		position = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(layoutFragmentId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(plid);

		objectOutput.writeLong(fragmentEntryId);

		if (css == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(css);
		}

		if (html == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(html);
		}

		if (js == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(js);
		}

		if (editableValues == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(editableValues);
		}

		objectOutput.writeInt(position);
	}

	public long layoutFragmentId;
	public long groupId;
	public long plid;
	public long fragmentEntryId;
	public String css;
	public String html;
	public String js;
	public String editableValues;
	public int position;
}