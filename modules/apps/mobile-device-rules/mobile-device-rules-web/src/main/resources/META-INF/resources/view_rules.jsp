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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL");

if (Validator.isNull(redirect) && Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/view.jsp");
	portletURL.setParameter("groupId", String.valueOf(groupId));

	backURL = portletURL.toString();
}

long ruleGroupId = ParamUtil.getLong(request, "ruleGroupId");

MDRRuleGroup ruleGroup = MDRRuleGroupLocalServiceUtil.getRuleGroup(ruleGroupId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view_rules.jsp");
portletURL.setParameter("ruleGroupId", String.valueOf(ruleGroupId));
portletURL.setParameter("groupId", String.valueOf(groupId));
portletURL.setParameter("redirect", redirect);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);

renderResponse.setTitle(LanguageUtil.format(request, "classification-rules-for-x", ruleGroup.getName(locale), false));
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="classification-rules" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="no-classification-rules-are-configured-for-this-device-family"
		iteratorURL="<%= portletURL %>"
		total="<%= MDRRuleLocalServiceUtil.getRulesCount(ruleGroupId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= MDRRuleLocalServiceUtil.getRules(ruleGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.mobile.device.rules.model.MDRRule"
			escapedModel="<%= true %>"
			keyProperty="ruleId"
			modelVar="rule"
		>
			<liferay-portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="ruleId" value="<%= String.valueOf(rule.getRuleId()) %>" />
			</liferay-portlet:renderURL>

			<%@ include file="/rule_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" type="more" />
	</liferay-ui:search-container>
</div>

<liferay-portlet:renderURL var="addURL">
	<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	<portlet:param name="ruleGroupId" value="<%= String.valueOf(ruleGroupId) %>" />
</liferay-portlet:renderURL>

<liferay-frontend:add-menu>
	<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add-classification-rule") %>' url="<%= addURL.toString() %>" />
</liferay-frontend:add-menu>