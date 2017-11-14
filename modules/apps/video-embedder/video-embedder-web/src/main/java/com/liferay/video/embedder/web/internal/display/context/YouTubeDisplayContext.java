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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 * @author arthurchan35
 */
public class YouTubeDisplayContext extends BaseVideoEmbedderDisplayContext {

	public YouTubeDisplayContext(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		super(request, portletPreferences);
	}

	@Override
	public String getEmbedURL() {
		StringBundler sb = new StringBundler(13);

		sb.append(super.getEmbedURL());

		sb.append("?wmode=transparent");

		if (isAutoPlay()) {
			sb.append("&amp;autoplay=1");
		}

		if (isClosedCaptioning()) {
			sb.append("&amp;cc_load_policy=1");
		}

		if (!isEnableKeyboardControls()) {
			sb.append("&amp;disablekb=1");
		}

		if (isAnnotations()) {
			sb.append("&amp;iv_load_policy=1");
		}
		else {
			sb.append("&amp;iv_load_policy=3");
		}

		if (isLoop()) {
			sb.append("&amp;loop=1&amp;playlist=");
			sb.append(getId());
		}

		if (Validator.isNotNull(getStartTime())) {
			sb.append("&amp;start=");
			sb.append(getStartTime());
		}

		return sb.toString();
	}

	public String getImageURL() {
		StringBundler sb = new StringBundler(4);

		sb.append(HttpUtil.getProtocol(request));
		sb.append("://img.youtube.com/vi/");
		sb.append(getId());
		sb.append("/0.jpg");

		return sb.toString();
	}

	public String getStartTime() {
		if (_startTime != null) {
			return _startTime;
		}

		_startTime = portletPreferences.getValue("startTime", StringPool.BLANK);

		return _startTime;
	}

	public String getWatchURL() {
		return HttpUtil.getProtocol(request) + "://www.youtube.com/watch?v=" +
			getId();
	}

	public boolean isAnnotations() {
		if (_annotations != null) {
			return _annotations;
		}

		_annotations = GetterUtil.getBoolean(
			portletPreferences.getValue("annotations", "true"));

		return _annotations;
	}

	public boolean isAutoPlay() {
		if (_autoPlay != null) {
			return _autoPlay;
		}

		_autoPlay = GetterUtil.getBoolean(
			portletPreferences.getValue("autoplay", "false"));

		return _autoPlay;
	}

	public boolean isClosedCaptioning() {
		if (_closedCaptioning != null) {
			return _closedCaptioning;
		}

		_closedCaptioning = GetterUtil.getBoolean(
			portletPreferences.getValue("closedCaptioning", "false"));

		return _closedCaptioning;
	}

	public boolean isEnableKeyboardControls() {
		if (_enableKeyboardControls != null) {
			return _enableKeyboardControls;
		}

		_enableKeyboardControls = GetterUtil.getBoolean(
			portletPreferences.getValue("enableKeyboardControls", "true"));

		return _enableKeyboardControls;
	}

	public boolean isLoop() {
		if (_loop != null) {
			return _loop;
		}

		_loop = GetterUtil.getBoolean(
			portletPreferences.getValue("loop", "false"));

		return _loop;
	}

	public boolean isShowThumbnail() {
		if (_showThumbnail != null) {
			return _showThumbnail;
		}

		_showThumbnail = GetterUtil.getBoolean(
			portletPreferences.getValue("showThumbnail", "false"));

		return _showThumbnail;
	}

	private Boolean _annotations;
	private Boolean _autoPlay;
	private Boolean _closedCaptioning;
	private Boolean _enableKeyboardControls;
	private Boolean _loop;
	private Boolean _showThumbnail;
	private String _startTime;

}