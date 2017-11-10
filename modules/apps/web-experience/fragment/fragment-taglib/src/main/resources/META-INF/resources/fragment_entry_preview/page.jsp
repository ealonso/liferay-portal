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

<%@ include file="/fragment_entry_preview/init.jsp" %>

<%
String fragmentEntryName = GetterUtil.getString(request.getAttribute("liferay-fragment:fragment-entry-preview:fragmentEntryName"));
int height = GetterUtil.getInteger(request.getAttribute("liferay-fragment:fragment-entry-preview:height"), 100);
String previewURL = GetterUtil.getString(request.getAttribute("liferay-fragment:fragment-entry-preview:previewURL"));
int width = GetterUtil.getInteger(request.getAttribute("liferay-fragment:fragment-entry-preview:width"), 250);
%>

<img alt="<%= fragmentEntryName %>" height="<%= height %>" src="<%= previewURL %>" width="<%= width %>" />