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
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectUsersRoles");

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

User selUser = siteMembershipsDisplayContext.getSelUser();

PortletURL portletURL = siteMembershipsDisplayContext.getPortletURL();

portletURL.setParameter("mvcPath", "/users_roles.jsp");
portletURL.setParameter("p_u_i_d", String.valueOf(selUser.getUserId()));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(siteMembershipsDisplayContext.getRedirect());
renderResponse.setTitle(LanguageUtil.get(request, "edit-site-roles-for-user") + ": " + HtmlUtil.escape(selUser.getFullName()));
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar
	checkBoxContainerId="userGroupRolesSearchContainer"
	includeCheckBox="<%= true %>"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:membership-policy-error />

	<liferay-ui:search-container
		id="userGroupRoles"
		rowChecker="<%= new UserGroupRoleRoleChecker(renderResponse, siteMembershipsDisplayContext.getSelUser(), siteMembershipsDisplayContext.getGroup()) %>"
		searchContainer="<%= new RoleSearch(renderRequest, portletURL) %>"
	>

		<liferay-ui:search-container-results>

			<%
			RoleSearchTerms searchTerms = (RoleSearchTerms)searchContainer.getSearchTerms();

			List<Role> roles = RoleLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), new Integer[] {RoleConstants.TYPE_SITE}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, searchContainer.getOrderByComparator());

			roles = UsersAdminUtil.filterGroupRoles(permissionChecker, siteMembershipsDisplayContext.getGroupId(), roles);

			total = roles.size();

			searchContainer.setTotal(total);

			results = ListUtil.subList(roles, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Role"
			cssClass="selected"
			keyProperty="roleId"
			modelVar="role"
		>
			<liferay-ui:search-container-column-text
				name="title"
			>
				<liferay-ui:icon
					iconCssClass="<%= RolesAdminUtil.getIconCssClass(role) %>"
					label="<%= true %>"
					message="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
				/>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="type"
				value="<%= LanguageUtil.get(request, role.getTypeLabel()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				value="<%= HtmlUtil.escape(role.getDescription(locale)) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	var A = AUI();
	var AArray = A.Array;

	var <portlet:namespace />addRoleIds = [];
	var <portlet:namespace />removeRoleIds = [];

	$('input[name="<portlet:namespace />rowIds"]').on(
		'change',
		function(event) {
			var target = event.target;

			if (target.checked) {
				<portlet:namespace />addRoleIds.push(target.value);

				AArray.removeItem(<portlet:namespace />removeRoleIds, target.value);
			}
			else {
				AArray.removeItem(<portlet:namespace />addRoleIds, target.value);

				<portlet:namespace />removeRoleIds.push(target.value);
			}

			var event = {};

			if ((<portlet:namespace />addRoleIds.length > 0) || (<portlet:namespace />removeRoleIds.length > 0)) {
				event = {
					data: {
						addRoleIds: <portlet:namespace />addRoleIds.join(','),
						removeRoleIds: <portlet:namespace />removeRoleIds.join(','),
						selUserId: <%= siteMembershipsDisplayContext.getUserId() %>
					}
				};
			}

			Liferay.Util.getOpener().Liferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', event);
		}
	);
</aui:script>