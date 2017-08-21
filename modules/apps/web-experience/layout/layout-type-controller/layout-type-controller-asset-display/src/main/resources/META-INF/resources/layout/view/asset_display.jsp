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

<%@ include file="/layout/view/init.jsp" %>

<%
Map<String, Object> contextObjects = (Map<String, Object>)request.getAttribute(AssetDisplayLayoutTypeControllerWebKeys.CONTEXT_OBJECTS);
List<AssetEntry> entries = (List<AssetEntry>)request.getAttribute(AssetDisplayLayoutTypeControllerWebKeys.ENTRIES);
DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute(WebKeys.TEMPLATE);

String displayStyle = "ddmTemplate_" + ddmTemplate.getTemplateKey();
%>

<liferay-ddm:template-renderer className="<%= AssetDisplayTemplate.class.getName() %>" contextObjects="<%= contextObjects %>" displayStyle="<%= displayStyle %>" displayStyleGroupId="<%= ddmTemplate.getGroupId() %>" entries="<%= entries %>">
</liferay-ddm:template-renderer>