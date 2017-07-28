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
String ppid = ParamUtil.getString(request, "p_p_id");

String layoutTemplateId = GetterUtil.getString(selLayout.getTypeSettingsProperty("layout-template-id"), "1_column");
String velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + layoutTemplateId;
String velocityTemplateContent = LayoutTemplateLocalServiceUtil.getContent(layoutTemplateId, false, theme.getThemeId());

if (themeDisplay.isStatePopUp() || themeDisplay.isWidget()) {
	velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "pop_up";
	velocityTemplateContent = LayoutTemplateLocalServiceUtil.getContent("pop_up", true, theme.getThemeId());
}
else if (layoutTypePortlet.hasStateMax()) {
	ppid = StringUtil.split(layoutTypePortlet.getStateMax())[0];

	velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
	velocityTemplateContent = LayoutTemplateLocalServiceUtil.getContent("max", true, theme.getThemeId());
}

if (Validator.isNotNull(velocityTemplateContent)) {
	RuntimePageUtil.processTemplate(request, response, ppid, new StringTemplateResource(velocityTemplateId, velocityTemplateContent));
}
%>