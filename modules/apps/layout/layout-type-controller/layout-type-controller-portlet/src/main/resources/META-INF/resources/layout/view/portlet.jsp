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

<c:choose>
	<c:when test="<%= themeDisplay.isStatePopUp() || themeDisplay.isWidget() || layoutTypePortlet.hasStateMax() %>">

		<%
		String ppid = ParamUtil.getString(request, "p_p_id");

		String templateId = null;
		String templateContent = null;
		String langType = null;

		if (themeDisplay.isStatePopUp() || themeDisplay.isWidget()) {
			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "pop_up";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("pop_up", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("pop_up", true, theme.getThemeId());
		}
		else {
			ppid = StringUtil.split(layoutTypePortlet.getStateMax())[0];

			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("max", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("max", true, theme.getThemeId());
		}

		if (Validator.isNotNull(templateContent)) {
			RuntimePageUtil.processTemplate(request, response, ppid, new StringTemplateResource(templateId, templateContent), langType);
		}
		%>

	</c:when>
	<c:otherwise>
		<div class="layout-content portlet-layout" id="main-content" role="main">

			<%
			try {
				request.setAttribute(WebKeys.SHOW_PORTLET_TOPPER, Boolean.TRUE);

				PortletLayoutDisplayContext portletLayoutDisplayContext = new PortletLayoutDisplayContext(request, response);

				LayoutStructure layoutStructure = portletLayoutDisplayContext.getLayoutStructure();

				request.setAttribute("render_layout_data.jsp-itemId", layoutStructure.getMainItemId());

				request.setAttribute("render_layout_data.jsp-portletLayoutDisplayContext", portletLayoutDisplayContext);
			%>

				<%= portletLayoutDisplayContext.getPortletPaths() %>

				<liferay-util:include page="/layout/view/render_layout_data.jsp" servletContext="<%= application %>" />

			<%
			}
			finally {
				request.removeAttribute(WebKeys.SHOW_PORTLET_TOPPER);
			}
			%>

		</div>
	</c:otherwise>
</c:choose>

<liferay-ui:layout-common />