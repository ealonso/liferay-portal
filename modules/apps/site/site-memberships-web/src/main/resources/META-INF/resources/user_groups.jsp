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
String tabs2 = (String)request.getAttribute("edit_site_assignments.jsp-tabs2");

int cur = (Integer)request.getAttribute("edit_site_assignments.jsp-cur");

Group group = (Group)request.getAttribute("edit_site_assignments.jsp-group");

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_site_assignments.jsp-portletURL");

PortletURL viewUserGroupsURL = renderResponse.createRenderURL();

viewUserGroupsURL.setParameter("mvcPath", "/view.jsp");
viewUserGroupsURL.setParameter("tabs1", "user-groups");
viewUserGroupsURL.setParameter("tabs2", tabs2);
viewUserGroupsURL.setParameter("redirect", currentURL);
viewUserGroupsURL.setParameter("groupId", String.valueOf(group.getGroupId()));

UserGroupGroupChecker userGroupGroupChecker = null;

if (!tabs2.equals("current")) {
	userGroupGroupChecker = new UserGroupGroupChecker(renderResponse, group);
}

String emptyResultsMessage = UserGroupSearch.EMPTY_RESULTS_MESSAGE;

if (tabs2.equals("current")) {
	emptyResultsMessage ="no-user-group-was-found-that-is-a-member-of-this-site";
}

UserGroupSearch userGroupSearch = new UserGroupSearch(renderRequest, viewUserGroupsURL);

userGroupSearch.setEmptyResultsMessage(emptyResultsMessage);
%>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= PortletURLUtil.clone(viewUserGroupsURL, renderResponse) %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"name", "description"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
		/>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
				selectedDisplayStyle="<%= displayStyle %>"
			/>
		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<liferay-util:include page="/info_message.jsp" servletContext="<%= application %>" />

<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="tabs1" type="hidden" value="user-groups" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="assignmentsRedirect" type="hidden" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(group.getGroupId()) %>" />
	<aui:input name="addUserGroupIds" type="hidden" />
	<aui:input name="removeUserGroupIds" type="hidden" />

	<liferay-ui:search-container
		rowChecker="<%= userGroupGroupChecker %>"
		searchContainer="<%= userGroupSearch %>"
	>

		<%
		UserGroupDisplayTerms searchTerms = (UserGroupDisplayTerms)searchContainer.getSearchTerms();

		LinkedHashMap<String, Object> userGroupParams = new LinkedHashMap<String, Object>();

		if (tabs2.equals("current")) {
			userGroupParams.put("userGroupsGroups", Long.valueOf(group.getGroupId()));
		}
		%>

		<liferay-ui:search-container-results>

			<%
			total = UserGroupLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), userGroupParams);

			searchContainer.setTotal(total);

			results = UserGroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), userGroupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.UserGroup"
			escapedModel="<%= true %>"
			keyProperty="userGroupId"
			modelVar="userGroup"
		>
			<liferay-ui:search-container-row-parameter
				name="group"
				value="<%= group %>"
			/>

			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="description"
				orderable="<%= true %>"
				property="description"
			/>

			<c:if test='<%= tabs2.equals("current") %>'>

				<%
				List<UserGroupGroupRole> userGroupGroupRoles = UserGroupGroupRoleLocalServiceUtil.getUserGroupGroupRoles(userGroup.getUserGroupId(), group.getGroupId());
				%>

				<liferay-ui:search-container-column-text
					name="site-roles"
					value="<%= ListUtil.toString(userGroupGroupRoles, UsersAdmin.USER_GROUP_GROUP_ROLE_TITLE_ACCESSOR, StringPool.COMMA_AND_SPACE) %>"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="list-group-item-field"
					path="/user_group_action.jsp"
				/>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-util:buffer var="formButton">
			<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
				<c:choose>
					<c:when test='<%= tabs2.equals("current") %>'>

						<%
						viewUserGroupsURL.setParameter("tabs2", "available");
						%>

						<liferay-frontend:add-menu>
							<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "assign-user-groups") %>' url="<%= viewUserGroupsURL.toString() %>" />
						</liferay-frontend:add-menu>

						<%
						viewUserGroupsURL.setParameter("tabs2", "current");
						%>

					</c:when>
					<c:otherwise>

						<%
						portletURL.setParameter("tabs2", "current");
						portletURL.setParameter("cur", String.valueOf(cur));

						String taglibOnClick = renderResponse.getNamespace() + "updateGroupUserGroups('" + portletURL.toString() + "');";
						%>

						<aui:button-row>
							<aui:button onClick="<%= taglibOnClick %>" primary="<%= true %>" value="save" />
						</aui:button-row>
					</c:otherwise>
				</c:choose>
			</c:if>
		</liferay-util:buffer>

		<c:if test="<%= PropsValues.SEARCH_CONTAINER_SHOW_PAGINATION_TOP && (results.size() > PropsValues.SEARCH_CONTAINER_SHOW_PAGINATION_TOP_DELTA) %>">
			<%= formButton %>
		</c:if>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />

		<%= formButton %>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />updateGroupUserGroups(assignmentsRedirect) {
		var Util = Liferay.Util;

		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('assignmentsRedirect').val(assignmentsRedirect);
		form.fm('addUserGroupIds').val(Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
		form.fm('removeUserGroupIds').val(Util.listUncheckedExcept(form, '<portlet:namespace />allRowIds'));

		submitForm(form, '<portlet:actionURL name="editGroupUserGroups" />');
	}
</aui:script>