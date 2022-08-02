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
LayoutUtilityPageActionDropdownItemsProvider layoutUtilityPageActionDropdownItemsProvider = new LayoutUtilityPageActionDropdownItemsProvider(request, renderResponse);
%>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new LayoutUtilityPageManagementToolbarDisplayContext(request, layoutUtilityPageDisplayContext, liferayPortletRequest, liferayPortletResponse) %>"
	propsTransformer="js/ManagementToolbarPropsTransformer"
/>

<portlet:actionURL name="/layout_utility_pages/delete_layout_utility_page_entry" var="deleteLayoutUtilityPageURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteLayoutUtilityPageURL %>" cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:breadcrumb
		showLayout="<%= false %>"
	/>

	<liferay-ui:search-container
		id="entries"
		searchContainer="<%= layoutUtilityPageDisplayContext.getLayoutUtilityPageEntrySearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.layout.utility.page.model.LayoutUtilityPageEntry"
			keyProperty="layoutUtilityPageEntryId"
			modelVar="layoutUtilityPageEntry"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/edit_layout_utility_page.jsp" />
				<portlet:param name="layoutUtilityPageEntryId" value="<%= String.valueOf(layoutUtilityPageEntry.getLayoutUtilityPageEntryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand table-title"
				href="<%= rowURL %>"
				name="name"
				value="<%= HtmlUtil.escape(layoutUtilityPageEntry.getName()) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="type"
				value="<%= LanguageUtil.get(request, LayoutUtilityPageConstants.getTypeLabel(layoutUtilityPageEntry.getType())) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-expand"
				name="default"
				value="<%= layoutUtilityPageEntry.isDefaultLayoutUtilityPageEntry() ? StringPool.STAR : StringPool.BLANK %>"
			/>

			<liferay-ui:search-container-column-text>
				<clay:dropdown-actions
					dropdownItems="<%= layoutUtilityPageActionDropdownItemsProvider.getActionDropdownItems(layoutUtilityPageEntry) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>