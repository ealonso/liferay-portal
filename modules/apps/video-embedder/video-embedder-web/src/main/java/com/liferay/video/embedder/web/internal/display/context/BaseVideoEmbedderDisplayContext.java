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

package com.liferay.video.embedder.web.internal.display.context;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Objects;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class BaseVideoEmbedderDisplayContext
	implements VideoEmbedderDisplayContext {

	public BaseVideoEmbedderDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		this.request = request;
		this.portletPreferences = portletPreferences;
	}

	@Override
	public String getEmbedURL() {
		StringBundler sb = new StringBundler(13);

		sb.append(HttpUtil.getProtocol(request));
		sb.append("stub for now");

		return sb.toString();
	}

	@Override
	public String getHeight() {
		if (height != null) {
			return height;
		}

		if (isCustomSize()) {
			height = portletPreferences.getValue("height", "360");
		}
		else {
			String presetSize = getPresetSize();

			String[] dimensions = presetSize.split("x");

			height = dimensions[1];
		}

		return height;
	}

	public String getId() {
		if (id != null) {
			return id;
		}

		String url = getURL();

		id = url.replaceAll("^.*?v=([a-zA-Z0-9_-]+).*$", "$1");

		return id;
	}

	@Override
	public String getPresetSize() {
		if (presetSize != null) {
			return presetSize;
		}

		presetSize = portletPreferences.getValue("presetSize", "480x360");

		return presetSize;
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

		if (isCustomSize()) {
			width = portletPreferences.getValue("width", "480");
		}
		else {
			String presetSize = getPresetSize();

			String[] dimensions = presetSize.split("x");

			width = dimensions[0];
		}

		return width;
	}

	@Override
	public boolean isCustomSize() {
		String presetSize = getPresetSize();

		if (Objects.equals(presetSize, "custom")) {
			return true;
		}

		return false;
	}

	protected String height;
	protected String id;
	protected final PortletPreferences portletPreferences;
	protected String presetSize;
	protected final HttpServletRequest request;
	protected String url;
	protected String width;

}