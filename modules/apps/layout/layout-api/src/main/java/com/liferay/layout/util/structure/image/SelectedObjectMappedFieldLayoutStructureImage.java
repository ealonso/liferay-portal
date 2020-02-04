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

package com.liferay.layout.util.structure.image;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

/**
 * @author Eudaldo Alonso
 */
public class SelectedObjectMappedFieldLayoutStructureImage
	extends LayoutStructureImage {

	public long getClassNameId() {
		return _classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public String getFieldId() {
		return _fieldId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setFieldId(String fieldId) {
		_fieldId = fieldId;
	}

	@Override
	public JSONObject toJSONObject() {
		return JSONUtil.put(
			"classNameId", _classNameId
		).put(
			"classPK", _classPK
		).put(
			"fieldId", _fieldId
		);
	}

	@Override
	public void updateItemConfig(JSONObject configJSONObject) {
		if (configJSONObject.has("classPK")) {
			setClassPK(configJSONObject.getLong("classPK"));
		}

		if (configJSONObject.has("classNameId")) {
			setClassNameId(configJSONObject.getLong("classNameId"));
		}

		if (configJSONObject.has("fieldId")) {
			setFieldId(configJSONObject.getString("fieldId"));
		}
	}

	private long _classNameId;
	private long _classPK;
	private String _fieldId;

}