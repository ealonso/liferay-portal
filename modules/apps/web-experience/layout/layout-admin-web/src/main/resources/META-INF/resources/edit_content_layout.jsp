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

<portlet:actionURL name="/layout/render_layout_fragment" var="renderLayoutFragmentURL" />

<%
ContentLayoutDisplayContext contentLayoutDisplayContext = new ContentLayoutDisplayContext(request);

Map<String, Object> layoutPageTemplateEditorContext = new HashMap<>();

layoutPageTemplateEditorContext.put("fragments", contentLayoutDisplayContext.getLayoutFragmentsJSONArray());
layoutPageTemplateEditorContext.put("id", String.valueOf(contentLayoutDisplayContext.getSelPlid()));
layoutPageTemplateEditorContext.put("portletNamespace", renderResponse.getNamespace());
layoutPageTemplateEditorContext.put("renderFragmentEntryURL", renderLayoutFragmentURL);
layoutPageTemplateEditorContext.put("spritemap", themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");
%>

<soy:template-renderer
	context="<%= layoutPageTemplateEditorContext %>"
	module="layout-admin-web/js/ContentLayoutEditor.es"
	templateNamespace="ContentLayoutEditor.render"
/>