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
String tabs2 = siteMembershipsDisplayContext.getTabs2();
Group group = siteMembershipsDisplayContext.getGroup();

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String orderByCol = ParamUtil.getString(request, "orderByCol", "first-name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL viewUsersURL = renderResponse.createRenderURL();

viewUsersURL.setParameter("mvcPath", "/view.jsp");
viewUsersURL.setParameter("tabs1", "users");
viewUsersURL.setParameter("tabs2", tabs2);
viewUsersURL.setParameter("redirect", currentURL);
viewUsersURL.setParameter("groupId", String.valueOf(siteMembershipsDisplayContext.getGroupId()));

SiteMembershipChecker siteMembershipChecker = null;

if (!tabs2.equals("current")) {
	siteMembershipChecker = new SiteMembershipChecker(renderResponse, siteMembershipsDisplayContext.getGroup());
}

String emptyResultsMessage = UserSearch.EMPTY_RESULTS_MESSAGE;

if (tabs2.equals("current")) {
	emptyResultsMessage ="no-user-was-found-that-is-a-direct-member-of-this-site";
}

SearchContainer searchContainer = new UserSearch(renderRequest, viewUsersURL);

searchContainer.setEmptyResultsMessage(emptyResultsMessage);
%>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= siteMembershipsDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"first-name", "screen-name"} %>'
			portletURL="<%= siteMembershipsDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= siteMembershipsDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<liferay-util:include page="/info_message.jsp" servletContext="<%= application %>" />

<aui:form action="<%= siteMembershipsDisplayContext.getPortletURL() %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="tabs1" type="hidden" value="users" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="assignmentsRedirect" type="hidden" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(siteMembershipsDisplayContext.getGroupId()) %>" />
	<aui:input name="p_u_i_d" type="hidden" />
	<aui:input name="addUserIds" type="hidden" />
	<aui:input name="removeUserIds" type="hidden" />
	<aui:input name="addRoleIds" type="hidden" />
	<aui:input name="removeRoleIds" type="hidden" />

	<liferay-ui:membership-policy-error />

	<liferay-ui:search-container
		rowChecker="<%= siteMembershipChecker %>"
		searchContainer="<%= searchContainer %>"
		var="userSearchContainer"
	>

		<%
		UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

		LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

		if (tabs2.equals("current")) {
			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", Long.valueOf(group.getGroupId()));
		}
		else if (group.isLimitedToParentSiteMembers()) {
			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", Long.valueOf(group.getParentGroupId()));
		}
		%>

		<liferay-ui:search-container-results>

			<%
			total = UserLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams);

			userSearchContainer.setTotal(total);

			results = UserLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getStatus(), userParams, userSearchContainer.getStart(), userSearchContainer.getEnd(), userSearchContainer.getOrderByComparator());

			userSearchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>

			<%@ include file="/user_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-util:buffer var="formButton">
			<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
				<c:choose>
					<c:when test='<%= tabs2.equals("current") %>'>

						<%
						viewUsersURL.setParameter("tabs2", "available");
						viewUsersURL.setParameter("redirect", currentURL);
						%>

						<liferay-frontend:add-menu>
							<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "assign-users") %>' url="<%= viewUsersURL.toString() %>" />
						</liferay-frontend:add-menu>

						<%
						viewUsersURL.setParameter("tabs2", "current");
						%>

					</c:when>
					<c:otherwise>

						<%
						PortletURL portletURL = siteMembershipsDisplayContext.getPortletURL();

						portletURL.setParameter("tabs2", "current");
						portletURL.setParameter("cur", String.valueOf(siteMembershipsDisplayContext.getCur()));

						String taglibOnClick = renderResponse.getNamespace() + "updateGroupUsers('" + portletURL.toString() + "');";
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

<aui:script use="liferay-item-selector-dialog">
	var Util = Liferay.Util;

	var form = $(document.<portlet:namespace />fm);

	<c:if test='<%= tabs2.equals("current") %>'>
		form.on(
			'click',
			'.assign-site-roles a',
			function(event) {
				event.preventDefault();

				var currentTarget = $(event.currentTarget);

				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: '<portlet:namespace />selectUsersRoles',
						on: {
							selectedItemChange: function(event) {
								var selectedItem = event.newVal;

								if (selectedItem) {
									form.fm('addRoleIds').val(selectedItem.addRoleIds);
									form.fm('removeRoleIds').val(selectedItem.removeRoleIds);
									form.fm('p_u_i_d').val(selectedItem.selUserId);

									submitForm(form, '<portlet:actionURL name="editUserGroupRole" />');
								}
							}
						},
						title: '<liferay-ui:message key="assign-site-roles" />',
						url: currentTarget.data('href')
					}
				);

				itemSelectorDialog.open();
			}
		);
	</c:if>

	function <portlet:namespace />updateGroupUsers(assignmentsRedirect) {
		form.fm('assignmentsRedirect').val(assignmentsRedirect);
		form.fm('addUserIds').val(Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
		form.fm('removeUserIds').val(Util.listUncheckedExcept(form, '<portlet:namespace />allRowIds'));

		submitForm(form, '<portlet:actionURL name="editGroupUsers" />');
	}
</aui:script>