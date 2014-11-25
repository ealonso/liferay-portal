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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.servlet.JSPSupportServlet;
import com.liferay.portlet.portletdisplaytemplate.util.PortletDisplayTemplateConstants;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Raymond Augé
 */
public interface TemplateTaglibSupportProvider {

	public void addTaglibSupport(
			Map<String, Object> contextObjects, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception;

	public void addTaglibSupport(
			Template template, String servletContextName,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception;

}