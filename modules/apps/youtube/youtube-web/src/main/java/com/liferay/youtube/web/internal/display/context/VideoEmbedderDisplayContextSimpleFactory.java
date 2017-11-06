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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.youtube.web.configuration.VideoEmbedderConfiguration;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author arthurchan35
 */
public class VideoEmbedderDisplayContextSimpleFactory {

	public static VideoEmbedderDisplayContext create(
		HttpServletRequest request, PortletPreferences portletPreferences) {

		String url = portletPreferences.getValue("url", StringPool.BLANK);

		if (url.contains(VideoEmbedderConfiguration.YOUTUBE) ||
			url.contains(VideoEmbedderConfiguration.YOUTUBE_2)) {

			return new YouTubeDisplayContext(request, portletPreferences);
		}

		return new BaseVideoEmbedderDisplayContext(request, portletPreferences);
	}

}