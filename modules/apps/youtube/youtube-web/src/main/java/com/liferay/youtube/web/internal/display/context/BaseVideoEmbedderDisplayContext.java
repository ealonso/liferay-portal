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
public class BaseVideoEmbedderDisplayContext
	implements VideoEmbedderDisplayContext {

	public BaseVideoEmbedderDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		this.request = request;
		this.portletPreferences = portletPreferences;

		id = StringPool.BLANK;
		siteName = StringPool.BLANK;

		_initSystemSettings();
	}

	@Override
	public String getEmbedURL() {
		getURL();

		if (url == null) {
			return "";
		}

		StringBundler sb = new StringBundler(13);

		sb.append(HttpUtil.getProtocol(request));
		sb.append("://");
		sb.append(_getIFramePrefix(_getSiteName()));
		sb.append(getId());

		return sb.toString();
	}

	@Override
	public String getHeight() {
		if (height != null) {
			return height;
		}

		if (isCustomRatio()) {
			height = portletPreferences.getValue("height", "16");
		}
		else {
			String presetRatio = getPresetRatio();

			String[] ratio = presetRatio.split(":");

			height = ratio[1];
		}

		return height;
	}

	@Override
	public String getPresetRatio() {
		if (presetRatio != null) {
			return presetRatio;
		}

		presetRatio = portletPreferences.getValue("presetRatio", "16:9");

		return presetRatio;
	}

	@Override
	public String getURL() {
		if (url != null) {
			return url;
		}

		url = portletPreferences.getValue("url", StringPool.BLANK);

		return url;
	}

	@Override
	public String getWidth() {
		if (width != null) {
			return width;
		}

		if (isCustomRatio()) {
			width = portletPreferences.getValue("width", "9");
		}
		else {
			String presetRatio = getPresetRatio();

			String[] ratio = presetRatio.split(":");

			width = ratio[0];
		}

		return width;
	}

	@Override
	public boolean isCustomRatio() {
		String presetRatio = getPresetRatio();

		if (Objects.equals(presetRatio, "custom")) {
			return true;
		}

		return false;
	}

	protected String getId() {
		if (Validator.isNotNull(id)) {
			return id;
		}

		String regex = _getVideoPattern(_getSiteName());

		id = getURL().replaceAll(regex, "$1");

		return id;
	}

	protected String height;
	protected String id;
	protected final PortletPreferences portletPreferences;
	protected String presetRatio;
	protected final HttpServletRequest request;
	protected String siteName;
	protected Map<String, String[]> systemSettings;
	protected String url;
	protected String width;

	private String _getIFramePrefix(String siteName) {
		return systemSettings.get(siteName)[0];
	}

	private String _getSiteName() {
		if (Validator.isNotNull(siteName)) {
			return siteName;
		}

		for (String key : systemSettings.keySet()) {
			if (url.contains(key)) {
				return key;
			}
		}

		return StringPool.BLANK;
	}

	private String _getVideoPattern(String siteName) {
		return systemSettings.get(siteName)[1];
	}

	private void _initSystemSettings() {

		//When this class is instantiated for configuration page,
		//this configuration is not passed in and will be null

		VideoEmbedderConfiguration videoEmbedderConfiguration =
			(VideoEmbedderConfiguration)request.getAttribute(
				VideoEmbedderConfiguration.class.getName());

		if (videoEmbedderConfiguration == null) {
			return;
		}

		String[] values = videoEmbedderConfiguration.iframeURLs();

		for (String val : values) {
			String[] parts = val.split(VideoEmbedderConfiguration.DLM);

			if (parts.length != 3) {
				throw new IllegalArgumentException(
					"Invalid configuration format, check system settings");
			}

			String[] copy = new String[2];

			copy[0] = parts[0];
			copy[1] = parts[1];

			if (systemSettings == null) {
				systemSettings = new HashMap<>();
			}

			systemSettings.put(parts[2], copy);
		}
	}

}