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
import com.liferay.youtube.web.configuration.VideoEmbedderConfiguration;
import java.util.HashMap;
import java.util.Map;

import java.util.Objects;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 * @author arthurchan35
 */
public class BaseVideoEmbedderDisplayContext implements VideoEmbedderDisplayContext {

	public BaseVideoEmbedderDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences, VideoEmbedderConfiguration configuration) {

		_request = request;
		_portletPreferences = portletPreferences;
		_id = StringPool.BLANK;
		_siteName = StringPool.BLANK;

		_init(configuration);
	}

	@Override
	public String getEmbedURL() {
		getURL();
		if (_url == null) {
			return "";
		}

		StringBundler sb = new StringBundler(13);

		sb.append(HttpUtil.getProtocol(_request));
		sb.append("://");
		sb.append(_getIFramePrefix(_getSiteName()));
		sb.append(getId());

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
		if (Validator.isNotNull(_id)) {
			return _id;
		}
		String regex = _getVideoPattern(_getSiteName());

		_id = getURL().replaceAll(regex, "$1");

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

	private String _getIFramePrefix(String siteName) {
		return _systemSettings.get(siteName)[0];
	}

	private String _getSiteName() {
		if (Validator.isNotNull(_siteName)) {
			return _siteName;
		}

		for (String key : _systemSettings.keySet()) {
			if (_url.contains(key)) {
				return key;
			}
		}

		return StringPool.BLANK;
	}

	private String _getVideoPattern(String siteName) {
		return _systemSettings.get(siteName)[1];
	}

	private void _init(VideoEmbedderConfiguration configuration) {

		//When this class is instantiated for configuration page, this configuration is not passed in and will be null
		if (configuration == null) {
			return;
		}

		String[] values = configuration.iframeURLs();

		for (String val : values) {

			String[] parts = val.split(VideoEmbedderConfiguration.DLM);

			if (parts.length != 3) {
				throw new IllegalArgumentException("Invalid configuration format, check system settings");
			}

			String[] copy = new String[2];

			copy[0] = parts[0];
			copy[1] = parts[1];

			if (_systemSettings == null) {
				_systemSettings = new HashMap<String, String[]>();
			}

			_systemSettings.put(parts[2], copy);
		}
	}


	protected String _height;
	protected String _id;
	protected final PortletPreferences _portletPreferences;
	protected String _presetSize;
	protected final HttpServletRequest _request;
	protected String _url;
	protected String _width;
	protected Map<String, String[]> _systemSettings;
	protected String _siteName;
}
