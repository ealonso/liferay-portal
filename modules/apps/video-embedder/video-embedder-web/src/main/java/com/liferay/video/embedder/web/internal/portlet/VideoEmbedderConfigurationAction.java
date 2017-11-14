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

package com.liferay.video.embedder.web.internal.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.video.embedder.web.configuration.VideoEmbedderConfiguration;
import com.liferay.video.embedder.web.internal.constants.VideoEmbedderPortletKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Peter Fellwock
 */
@Component(
	configurationPid = "com.liferay.video.embedder.web.configuration.VideoEmbedderConfiguration",
	immediate = true,
	property = {"javax.portlet.name=" + VideoEmbedderPortletKeys.VideoEmbedder},
	service = ConfigurationAction.class
)
public class VideoEmbedderConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		if (!_isValidURL(actionRequest)) {
			SessionErrors.add(actionRequest, "embeddedVideoURLInvalid");
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			VideoEmbedderConfiguration.class, properties);
	}

	private boolean _isValidURL(ActionRequest actionRequest) {
		UnicodeProperties typeSettingsProperties =
			PropertiesParamUtil.getProperties(actionRequest, "preferences--");

		String url = typeSettingsProperties.getProperty("url");

		String[] values = _configuration.iframeURLs();

		for (String val : values) {
			String[] parts = val.split(VideoEmbedderConfiguration.DLM);

			if (url.contains(parts[2])) {
				String videoPattern = parts[1];

				String id = url.replaceAll(videoPattern, "$1");

				if (!Validator.isBlank(id)) {
					return true;
				}
			}
		}

		return false;
	}

	private volatile VideoEmbedderConfiguration _configuration;

}