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
renderResponse.setTitle(LanguageUtil.get(request, "pages"));
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="pages" />
	</aui:nav>
</aui:nav-bar>

<liferay-portlet:resourceURL id="getLayouts" var="getLayoutsURL">
	<liferay-portlet:param name="groupId" value="<%= String.valueOf(layoutsAdminDisplayContext.getGroupId()) %>" />
	<liferay-portlet:param name="privateLayout" value="<%= String.valueOf(layoutsAdminDisplayContext.isPrivateLayout()) %>" />
</liferay-portlet:resourceURL>

<%
Map<String, Object> context = new HashMap<>();

context.put("layoutBlocks", layoutsAdminDisplayContext.getLayoutBlocksJSONArray());
context.put("orderBy", layoutsAdminDisplayContext.getOrderByJSONObject());
context.put("pathThemeImages", themeDisplay.getPathThemeImages());
context.put("portletNamespace", renderResponse.getNamespace());
context.put("portletURL", layoutsAdminDisplayContext.getPortletURL().toString());
context.put("searchContainerId", "pages");
%>

<liferay-frontend:management-bar
	disabled="<%= false %>"
	includeCheckBox="<%= true %>"
	searchContainerId="pages"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon"} %>'
			portletURL="<%= layoutsAdminDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="<%= layoutsAdminDisplayContext.getDisplayStyle() %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"public-pages", "private-pages"} %>'
			portletURL="<%= layoutsAdminDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= layoutsAdminDisplayContext.getOrderByCol() %>"
			orderByType="<%= layoutsAdminDisplayContext.getOrderByType() %>"
			orderColumns="<%= layoutsAdminDisplayContext.getOrderColumns() %>"
			portletURL="<%= layoutsAdminDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button href="javascript:;" icon="trash" id="deleteSelectedPages" label="delete" />
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="/pages/delete_layout" var="deleteLayoutURL">
	<portlet:param name="groupId" value="<%= String.valueOf(layoutsAdminDisplayContext.getGroupId()) %>" />
	<portlet:param name="privateLayout" value="<%= String.valueOf(layoutsAdminDisplayContext.isPrivateLayout()) %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteLayoutURL %>" name="fm">
	<soy:template-renderer
		context="<%= context %>"
		module="layout-admin-web/js/PageList"
		templateNamespace="PageList.render"
	/>
</aui:form>

<liferay-frontend:add-menu>
	<liferay-portlet:renderURL portletName="<%= PortletProviderUtil.getPortletId(Layout.class.getName(), PortletProvider.Action.EDIT) %>" var="addLayoutURL">
		<portlet:param name="mvcPath" value="/add_layout.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
	</liferay-portlet:renderURL>

	<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "page") %>' type="<%= AddMenuKeys.AddMenuType.PRIMARY %>" url="<%= addLayoutURL.toString() %>" />
</liferay-frontend:add-menu>

<aui:script sandbox="<%= true %>">
	$('#<portlet:namespace />deleteSelectedPages').on(
		'click',
		function() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
				submitForm($(document.<portlet:namespace />fm));
			}
		}
	);
</aui:script>