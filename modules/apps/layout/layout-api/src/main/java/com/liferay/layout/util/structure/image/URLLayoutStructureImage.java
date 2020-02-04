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
public class URLLayoutStructureImage extends LayoutStructureImage {

	public String getTitle() {
		return _title;
	}

	public String getURL() {
		return _url;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setURL(String url) {
		_url = url;
	}

	@Override
	public JSONObject toJSONObject() {
		return JSONUtil.put(
			"title", _title
		).put(
			"url", _url
		);
	}

	@Override
	public void updateItemConfig(JSONObject configJSONObject) {
		if (configJSONObject.has("title")) {
			setTitle(configJSONObject.getString("title"));
		}

		if (configJSONObject.has("url")) {
			setURL(configJSONObject.getString("url"));
		}
	}

	private String _title;
	private String _url;

}