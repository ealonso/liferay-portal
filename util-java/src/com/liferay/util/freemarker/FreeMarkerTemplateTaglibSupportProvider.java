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

package com.liferay.util.freemarker;

import com.liferay.portal.kernel.servlet.JSPSupportServlet;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateTaglibSupportProvider;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.ServletContextHashModel;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public class FreeMarkerTemplateTaglibSupportProvider
	implements TemplateTaglibSupportProvider {

	@Override
	public void addTaglibSupport(
			Map<String, Object> contextObjects, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		// FreeMarker servlet application

		GenericServlet genericServlet = new JSPSupportServlet(
			request.getServletContext());

		ServletContextHashModel servletContextHashModel =
			new ServletContextHashModel(
				genericServlet, ObjectWrapper.DEFAULT_WRAPPER);

		contextObjects.put(
			PortletDisplayTemplateConstants.FREEMARKER_SERVLET_APPLICATION,
			servletContextHashModel);

		// FreeMarker servlet request

		HttpRequestHashModel requestHashModel = new HttpRequestHashModel(
			request, response, ObjectWrapper.DEFAULT_WRAPPER);

		contextObjects.put(
			PortletDisplayTemplateConstants.FREEMARKER_SERVLET_REQUEST,
			requestHashModel);

		// Taglib Liferay hash

		TemplateHashModel taglibLiferayHash =
			FreeMarkerTaglibFactoryUtil.createTaglibFactory(
				request.getServletContext());

		contextObjects.put(
			PortletDisplayTemplateConstants.TAGLIB_LIFERAY_HASH,
			taglibLiferayHash);
	}

	@Override
	public void addTaglibSupport(
			Template template, String servletContextName,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		template.prepare(request);

		template.put(
			"fullTemplatesPath", servletContextName.concat(
				TemplateConstants.SERVLET_SEPARATOR));

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		GenericServlet genericServlet = new JSPSupportServlet(servletContext);

		ServletContextHashModel servletContextHashModel =
			new ServletContextHashModel(
				genericServlet, ObjectWrapper.DEFAULT_WRAPPER);

		template.put("Application", servletContextHashModel);

		TemplateHashModel taglibsFactory =
			FreeMarkerTaglibFactoryUtil.createTaglibFactory(servletContext);

		template.put("PortletJspTagLibs", taglibsFactory);

		HttpServletRequestWrapper httpServletRequestWrapper =
			new HttpServletRequestWrapper(
				PortalUtil.getHttpServletRequest(portletRequest));
		HttpServletResponseWrapper httpServletResponseWrapper =
			new HttpServletResponseWrapper(
				PortalUtil.getHttpServletResponse(portletResponse));

		HttpRequestHashModel httpRequestHashModel =
			new HttpRequestHashModel(
				httpServletRequestWrapper, httpServletResponseWrapper,
				ObjectWrapper.DEFAULT_WRAPPER);

		template.put("Request", httpRequestHashModel);
	}

}