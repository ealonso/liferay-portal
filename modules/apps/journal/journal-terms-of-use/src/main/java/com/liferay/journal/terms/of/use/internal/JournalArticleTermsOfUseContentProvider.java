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

package com.liferay.journal.terms.of.use.internal;

import com.liferay.journal.configuration.JournalServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.terms.of.use.TermsOfUseContentProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import java.util.Map;

/**
 * @author Eduardo García
 */
@Component(
	configurationPid = "com.liferay.journal.configuration.JournalServiceConfiguration",
	immediate = true, service = TermsOfUseContentProvider.class)
public class JournalArticleTermsOfUseContentProvider
	implements TermsOfUseContentProvider {

	@Override
	public void includeConfig(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(_JSP_PATH_CONFIGURATION);

		httpServletRequest.setAttribute(
			JournalServiceConfiguration.class.getName(),
			_journalServiceConfiguration);

		requestDispatcher.include(httpServletRequest, httpServletResponse);
	}

	@Override
	public void includeView(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(_JSP_PATH_VIEW);

		httpServletRequest.setAttribute(
			JournalServiceConfiguration.class.getName(),
			_journalServiceConfiguration);

		requestDispatcher.include(httpServletRequest, httpServletResponse);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.terms.of.use)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_journalServiceConfiguration = ConfigurableUtil.createConfigurable(
			JournalServiceConfiguration.class, properties);
	}

	private static final String _JSP_PATH_CONFIGURATION = "/configuration.jsp";

	private static final String _JSP_PATH_VIEW = "/view.jsp";

	private ServletContext _servletContext;

	private JournalServiceConfiguration _journalServiceConfiguration;

}