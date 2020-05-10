<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-application-list" uri="http://liferay.com/tld/application-list" %>
<%@ page import="com.liferay.application.list.PanelCategory" %>
<%@ page import="java.util.List" %>
<%@ page
        import="com.liferay.application.list.constants.ApplicationListWebKeys" %>
<%@ page import="com.liferay.application.list.PanelApp" %>
<%@ page import="com.liferay.application.list.PanelAppRegistry" %>
<%@ page import="java.util.Objects" %><%--
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

<%@ include file="/global_menu/init.jsp" %>

<%
GlobalAppsDisplayContext globalAppsDisplayContext = new GlobalAppsDisplayContext(request, renderRequest, renderResponse);

System.out.println(globalAppsDisplayContext);
%>

<div class="control-menu-icon lfr-portal-tooltip">
	<clay:icon monospaced="<%= true %>" symbol="grid" />

	<react:component
		data="<%= globalAppsDisplayContext.getGlobalMenuComponentData() %>"
		module="global_menu/GlobalMenu"
	/>
</div>