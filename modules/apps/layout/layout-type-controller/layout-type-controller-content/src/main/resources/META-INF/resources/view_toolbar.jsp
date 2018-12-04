<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
FragmentsEditorDisplayContext fragmentsEditorDisplayContext = new FragmentsEditorDisplayContext(request, renderResponse);
String moduleName = (String)request.getAttribute(LayoutAdminWebKeys.RESOLVED_MODULE_NAME);
System.out.println(fragmentsEditorDisplayContext.getModuleName());
System.out.println(moduleName);
%>

<soy:component-renderer
	componentId='<%= PortalUtil.getPortletNamespace(ContentLayoutPortletKeys.CONTENT_PAGE_EDITOR_PORTLET) + "toolbar" %>'
	context="<%= fragmentsEditorDisplayContext.getFragmentsEditorToolbarContext() %>"
	module="layout-admin-web@3.0.0/js/fragments_editor/components/toolbar/FragmentsEditorToolbar.es"
	templateNamespace="com.liferay.layout.admin.web.FragmentsEditorToolbar.render"
/>