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
String portletResource = ParamUtil.getString(request, "portletResource");

Portlet selPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletResource);
%>

<portlet:renderURL var="redirectURL">
	<portlet:param name="mvcRenderCommandName" value="/navigation_menu/add_site_navigation_menu_item_redirect" />
	<portlet:param name="siteNavigationMenuId" value="<%= String.valueOf(siteNavigationAdminDisplayContext.getSiteNavigationMenuId()) %>" />
</portlet:renderURL>

<aui:script>
	Liferay.fire(
		'closeWindow',
		{
			id: '_<%= HtmlUtil.escapeJS(selPortlet.getPortletId()) %>_addMenuItem',
			redirect: '<%= redirectURL %>'
		}
	);
</aui:script>