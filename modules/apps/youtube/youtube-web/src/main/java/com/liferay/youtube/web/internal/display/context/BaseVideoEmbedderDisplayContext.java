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

package com.liferay.youtube.web.internal.display.context;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class BaseVideoEmbedderDisplayContext implements VideoEmbedderDisplayContext {

	public BaseVideoEmbedderDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		_request = request;
		_portletPreferences = portletPreferences;
	}

	@Override
	public String getEmbedURL() {
		StringBundler sb = new StringBundler(13);

		sb.append(HttpUtil.getProtocol(_request));
		sb.append("stub for now");

		return sb.toString();
	}

	@Override
	public String getHeight() {
		if (_height != null) {
			return _height;
		}

		if (isCustomSize()) {
			_height = _portletPreferences.getValue("height", "360");
		}
		else {
			String presetSize = getPresetSize();

			String[] dimensions = presetSize.split("x");

			_height = dimensions[1];
		}

		return _height;
	}

	public String getId() {
		if (_id != null) {
			return _id;
		}

		String url = getURL();

		_id = url.replaceAll("^.*?v=([a-zA-Z0-9_-]+).*$", "$1");

		return _id;
	}

	@Override
	public String getPresetSize() {
		if (_presetSize != null) {
			return _presetSize;
		}

		_presetSize = _portletPreferences.getValue("presetSize", "480x360");

		return _presetSize;
	}

	@Override
	public String getURL() {
		if (_url != null) {
			return _url;
		}

		_url = _portletPreferences.getValue("url", StringPool.BLANK);

		return _url;
	}

	@Override
	public String getWidth() {
		if (_width != null) {
			return _width;
		}

		if (isCustomSize()) {
			_width = _portletPreferences.getValue("width", "480");
		}
		else {
			String presetSize = getPresetSize();

			String[] dimensions = presetSize.split("x");

			_width = dimensions[0];
		}

		return _width;
	}

	@Override
	public boolean isCustomSize() {
		String presetSize = getPresetSize();

		if (Objects.equals(presetSize, "custom")) {
			return true;
		}

		return false;
	}

	protected String _height;
	protected String _id;
	protected final PortletPreferences _portletPreferences;
	protected String _presetSize;
	protected final HttpServletRequest _request;
	protected String _url;
	protected String _width;

}
