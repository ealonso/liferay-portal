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

package com.liferay.fragment.web.internal.configuration.display;

import com.liferay.configuration.admin.display.ConfigurationScreen;
import com.liferay.fragment.configuration.FragmentServiceConfigurationProvider;
import com.liferay.fragment.web.internal.display.context.FragmentServiceConfigurationDisplayContext;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;

import java.util.Locale;

import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ConfigurationScreen.class)
public class FragmentServiceConfigurationScreen implements ConfigurationScreen {

	@Override
	public String getCategoryKey() {
		return "page-fragments";
	}

	@Override
	public String getKey() {
		return "fragments-service";
	}

	@Override
	public String getName(Locale locale) {
		return _language.get(locale, "fragment-configuration-name");
	}

	@Override
	public String getScope() {
		return "company";
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(
					"/configuration/fragment_service_configuration.jsp");

			httpServletRequest.setAttribute(
				FragmentServiceConfigurationDisplayContext.class.getName(),
				new FragmentServiceConfigurationDisplayContext(
					_fragmentServiceConfigurationProvider, httpServletRequest,
					_portal.getLiferayPortletResponse(
						(PortletResponse)httpServletRequest.getAttribute(
							JavaConstants.JAVAX_PORTLET_RESPONSE))));

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new IOException(
				"Unable to render /configuration" +
					"/fragment_service_configuration.jsp",
				exception);
		}
	}

	@Reference
	private FragmentServiceConfigurationProvider
		_fragmentServiceConfigurationProvider;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.fragment.web)",
		unbind = "-"
	)
	private ServletContext _servletContext;

}