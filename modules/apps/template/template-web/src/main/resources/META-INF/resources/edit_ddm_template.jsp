<%@ page import="com.liferay.dynamic.data.mapping.exception.TemplateScriptException" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portlet.display.template.PortletDisplayTemplate" %>

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
long classNameId = ParamUtil.getLong(request, "classNameId");
long resourceClassNameId = ParamUtil.getLong(request, "resourceClassNameId", PortalUtil.getClassNameId(PortletDisplayTemplate.class));

DDMTemplate ddmTemplate = null;

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack("journalEditDDMTemplateDisplayContext.getRedirect()");

renderResponse.setTitle("add");
%>

<portlet:actionURL name="/template/add_ddm_template" var="addDDMTemplateURL">
	<portlet:param name="mvcPath" value="/edit_ddm_template.jsp" />
</portlet:actionURL>

<portlet:actionURL name="/template/update_ddm_template" var="updateDDMTemplateURL">
	<portlet:param name="mvcPath" value="/edit_ddm_template.jsp" />
</portlet:actionURL>

<aui:form action="<%= (ddmTemplate == null) ? addDDMTemplateURL : updateDDMTemplateURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="" />
	<aui:input name="ddmTemplateId" type="hidden" value="<%= 0 %>" />
	<aui:input name="groupId" type="hidden" value="<%= scopeGroupId %>" />
	<aui:input name="classPK" type="hidden" value="<%= 0 %>" />
	<aui:input name="classNameId" type="hidden" value="<%= classNameId %>" />
	<aui:input name="resourceClassNameId" type="hidden" value="<%= resourceClassNameId %>" />
	<aui:input name="saveAndContinue" type="hidden" value="<%= false %>" />

	<aui:model-context bean="<%= ddmTemplate %>" model="<%= DDMTemplate.class %>" />

	<nav class="component-tbar subnav-tbar-light tbar tbar-article">
		<clay:container-fluid>
			<ul class="tbar-nav">
				<li class="tbar-item tbar-item-expand">
					<aui:input cssClass="form-control-inline" defaultLanguageId="<%= (ddmTemplate == null) ? LocaleUtil.toLanguageId(LocaleUtil.getSiteDefault()): ddmTemplate.getDefaultLanguageId() %>" label="" name="name" placeholder='<%= LanguageUtil.format(request, "untitled-x", "template") %>' wrapperCssClass="article-content-title mb-0" />
				</li>
				<li class="tbar-item">
					<div class="journal-article-button-row tbar-section text-right">
						<aui:button cssClass="btn-secondary btn-sm mr-3" href="" type="cancel" />

						<%
						String taglibOnClickSaveAndContinue = "Liferay.fire('" + liferayPortletResponse.getNamespace() + "saveAndContinue');";
						%>

						<aui:button cssClass="btn-secondary btn-sm mr-3" onClick="<%= taglibOnClickSaveAndContinue %>" type="submit" value="save-and-continue" />

						<%
						String taglibOnClickSaveTemplate = "Liferay.fire('" + liferayPortletResponse.getNamespace() + "saveTemplate');";
						%>

						<aui:button cssClass="btn-sm mr-3" onClick="<%= taglibOnClickSaveTemplate %>" type="submit" value="save" />
					</div>
				</li>
			</ul>
		</clay:container-fluid>
	</nav>

	<liferay-ui:error exception="<%= TemplateNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= TemplateScriptException.class %>" message="please-enter-a-valid-script" />

	<div>
		<div id="<portlet:namespace />ddmTemplateEditor">
			<div class="inline-item my-5 p-5 w-100">
				<span aria-hidden="true" class="loading-animation"></span>
			</div>
		</div>
	</div>
</aui:form>