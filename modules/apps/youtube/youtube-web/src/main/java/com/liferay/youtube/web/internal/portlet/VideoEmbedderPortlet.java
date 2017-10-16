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

package com.liferay.youtube.web.internal.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.youtube.web.internal.constants.YouTubePortletKeys;
import com.liferay.youtube.web.configuration.VideoEmbedderConfiguration;

import java.io.IOException;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Fellwock
 * @author arthurchan35
 */
@Component(
	configurationPid = "com.liferay.youtube.web.configuration.VideoEmbedderConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=videoembedder-portlet",
		"com.liferay.portlet.display-category=category.entertainment",
		"com.liferay.portlet.icon=/icons/youtube.png",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Video Embedder",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + YouTubePortletKeys.YOUTUBE,
		"javax.portlet.portlet-info.keywords=Video",
		"javax.portlet.portlet-info.short-title=VideoEmbedder",
		"javax.portlet.portlet-info.title=VideoEmbedder",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)

public class VideoEmbedderPortlet extends MVCPortlet {

	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {
		request.setAttribute(VideoEmbedderConfiguration.class
			.getName(), _configuration);

		super.render(request, response);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration =
			(VideoEmbedderConfiguration)ConfigurableUtil.createConfigurable(
				VideoEmbedderConfiguration.class, properties);
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.youtube.web)(release.schema.version=1.0.0))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	private volatile VideoEmbedderConfiguration _configuration;
}
