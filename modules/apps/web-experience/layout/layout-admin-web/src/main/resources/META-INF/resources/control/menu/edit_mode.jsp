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

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.Portlet" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%
Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
%>

<link href="<%= PortalUtil.getStaticResourceURL(request, application.getContextPath() + "/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />

<%
PortletURL editLayoutURL = PortalUtil.getControlPanelPortletURL(request, PortletProviderUtil.getPortletId(Layout.class.getName(), PortletProvider.Action.EDIT), PortletRequest.RENDER_PHASE);

editLayoutURL.setParameter("mvcRenderCommandName", "/layout/edit_layout");
editLayoutURL.setParameter("backURL", PortalUtil.getCurrentURL(request));
editLayoutURL.setParameter("groupId", String.valueOf(layout.getGroupId()));
editLayoutURL.setParameter("selPlid", String.valueOf(layout.getPlid()));
editLayoutURL.setParameter("privateLayout", String.valueOf(layout.isPrivateLayout()));

String path = ParamUtil.getString(request, portletDisplay.getNamespace() + "mvcRenderCommandName");

String activeTab = "content";

if (Objects.equals(path, "/layout/edit_layout")) {
	activeTab = "properties";
}

Layout selLayout = (Layout)request.getAttribute(WebKeys.SEL_LAYOUT);

if (selLayout == null) {
	selLayout = layout;
}
%>

<div class="layout-edit-mode">
	<div class="container-fluid-1280">
		<liferay-ui:tabs
			names="content,properties"
			type="tabs nav-tabs-default"
			urls='<%= new String[] {HttpUtil.addParameter(PortalUtil.getLayoutFullURL(selLayout, themeDisplay), "p_p_edit", Boolean.TRUE.toString()), HttpUtil.addParameter(editLayoutURL.toString(), "p_p_edit", Boolean.TRUE.toString())} %>'
			value="<%= activeTab %>"
		/>
	</div>
</div>