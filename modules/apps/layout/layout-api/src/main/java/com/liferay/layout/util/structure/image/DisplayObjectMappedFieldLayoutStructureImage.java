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
public class DisplayObjectMappedFieldLayoutStructureImage
	extends LayoutStructureImage {

	public String getMappedField() {
		return _mappedField;
	}

	public void setMappedField(String mappedField) {
		_mappedField = mappedField;
	}

	@Override
	public JSONObject toJSONObject() {
		return JSONUtil.put("mappedField", _mappedField);
	}

	@Override
	public void updateItemConfig(JSONObject configJSONObject) {
		if (configJSONObject.has("mappedField")) {
			setMappedField(configJSONObject.getString("mappedField"));
		}
	}

	private String _mappedField;

}