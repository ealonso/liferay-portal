package com.liferay.youtube.web.internal.display.context;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.youtube.web.configuration.VideoEmbedderConfiguration;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

public class VideoEmbedderDisplayContextSimpleFactory {

	public static VideoEmbedderDisplayContext create(
		HttpServletRequest request, PortletPreferences portletPreferences,
		VideoEmbedderConfiguration configuration) {

		String url = portletPreferences.getValue("url", StringPool.BLANK);

		if ((url.contains(VideoEmbedderConfiguration.YOUTUBE)) ||
			(url.contains(VideoEmbedderConfiguration.YOUTUBE_2))) {

			return new YouTubeDisplayContext(
				request, portletPreferences, configuration);
		}

		return new BaseVideoEmbedderDisplayContext(
			request, portletPreferences, configuration);
}

}