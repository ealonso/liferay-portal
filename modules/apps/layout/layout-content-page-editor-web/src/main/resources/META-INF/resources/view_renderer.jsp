<%--
/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutPageTemplateStructure layoutPageTemplateStructure = LayoutPageTemplateStructureLocalServiceUtil.fetchLayoutPageTemplateStructure(themeDisplay.getScopeGroupId(), themeDisplay.getPlid());

String data = layoutPageTemplateStructure.getDefaultSegmentsExperienceData();
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(data) %>">
		<liferay-layout:render-layout-structure
			layoutStructure="<%= LayoutStructure.of(data) %>"
		/>
	</c:when>
	<c:otherwise>
		<clay:alert
			displayType="info"
			message="Empty content"
		/>
	</c:otherwise>
</c:choose>