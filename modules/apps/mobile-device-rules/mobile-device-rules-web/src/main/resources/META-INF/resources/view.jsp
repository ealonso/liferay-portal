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
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("groupId", String.valueOf(groupId));
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="device-families" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="deviceFamilies"
>

	<%
	PortletURL displayStyleURL = PortletURLUtil.clone(portletURL, renderResponse);
	%>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= displayStyleURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<%
	PortletURL iteratorURL = PortletURLUtil.clone(portletURL, renderResponse);

	iteratorURL.setParameter("displayStyle", displayStyle);
	%>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= iteratorURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button href="javascript:;" icon="trash" id="deleteSelectedDeviceFamilies" label="delete" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="/mobile_device_rules/edit_rule_group" var="editRuleGroupURL">
	<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
</portlet:actionURL>

<aui:form action="<%= editRuleGroupURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
	<aui:input name="ruleGroupIds" type="hidden" />

	<%
	RuleGroupSearch ruleGroupSearch = new RuleGroupSearch(liferayPortletRequest, portletURL);

	RowChecker rowChecker = new RuleGroupChecker(renderResponse);
	%>

	<liferay-ui:search-container
		id="deviceFamilies"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= ruleGroupSearch %>"
	>

		<%
		RuleGroupDisplayTerms displayTerms = (RuleGroupDisplayTerms)searchContainer.getDisplayTerms();
		RuleGroupSearchTerms searchTerms = (RuleGroupSearchTerms)searchContainer.getSearchTerms();

		if (displayTerms.getGroupId() == 0) {
			displayTerms.setGroupId(groupId);
			searchTerms.setGroupId(groupId);
		}
		%>

		<liferay-ui:search-container-results>

			<%
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("includeGlobalScope", Boolean.TRUE);

			total = MDRRuleGroupLocalServiceUtil.searchByKeywordsCount(searchTerms.getGroupId(), searchTerms.getKeywords(), params, searchTerms.isAndOperator());

			searchContainer.setTotal(total);

			results = MDRRuleGroupLocalServiceUtil.searchByKeywords(searchTerms.getGroupId(), searchTerms.getKeywords(), params, searchTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.mobile.device.rules.model.MDRRuleGroup"
			escapedModel="<%= true %>"
			keyProperty="ruleGroupId"
			modelVar="ruleGroup"
		>
			<%@ include file="/rule_group_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" type="more" />
	</liferay-ui:search-container>
</aui:form>

<c:if test="<%= MDRPermission.contains(permissionChecker, groupId, ActionKeys.ADD_RULE_GROUP) %>">
	<portlet:renderURL var="viewRulesURL">
		<portlet:param name="mvcRenderCommandName" value="/view.jsp" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<portlet:param name="className" value="<%= className %>" />
		<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</portlet:renderURL>

	<liferay-portlet:renderURL var="addRuleGroupURL">
		<portlet:param name="mvcRenderCommandName" value="/mobile_device_rules/edit_rule_group" />
		<portlet:param name="redirect" value="<%= viewRulesURL %>" />
		<portlet:param name="backURL" value="<%= viewRulesURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
	</liferay-portlet:renderURL>

	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add-device-family") %>' url="<%= addRuleGroupURL %>" />
	</liferay-frontend:add-menu>
</c:if>

<aui:script>
	$('#<portlet:namespace />deleteSelectedDeviceFamilies').on(
		'click',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
				submitForm($(document.<portlet:namespace />fm));
			}
		}
	);
</aui:script>