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

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL viewOrganizationsURL = renderResponse.createRenderURL();

viewOrganizationsURL.setParameter("mvcPath", "/view.jsp");
viewOrganizationsURL.setParameter("tabs1", "organizations");
viewOrganizationsURL.setParameter("tabs2", tabs2);
viewOrganizationsURL.setParameter("redirect", currentURL);
viewOrganizationsURL.setParameter("groupId", String.valueOf(siteMembershipsDisplayContext.getGroupId()));

OrganizationGroupChecker organizationGroupChecker = null;

if (!tabs2.equals("current")) {
	organizationGroupChecker = new OrganizationGroupChecker(renderResponse, siteMembershipsDisplayContext.getGroup());
}

String emptyResultsMessage = OrganizationSearch.EMPTY_RESULTS_MESSAGE;

if (tabs2.equals("current")) {
	emptyResultsMessage ="no-organization-was-found-that-is-a-member-of-this-site";
}

SearchContainer searchContainer = new OrganizationSearch(renderRequest, viewOrganizationsURL);

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
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= siteMembershipsDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= siteMembershipsDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="<%= displayStyle %>"
			/>
		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<liferay-util:include page="/info_message.jsp" servletContext="<%= application %>" />

<aui:form action="<%= siteMembershipsDisplayContext.getPortletURL() %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="tabs1" type="hidden" value="organizations" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="assignmentsRedirect" type="hidden" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(siteMembershipsDisplayContext.getGroupId()) %>" />
	<aui:input name="addOrganizationIds" type="hidden" />
	<aui:input name="removeOrganizationIds" type="hidden" />

	<liferay-ui:search-container
		rowChecker="<%= organizationGroupChecker %>"
		searchContainer="<%= searchContainer %>"
		var="organizationSearchContainer"
	>

		<%
		OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)organizationSearchContainer.getSearchTerms();

		long parentOrganizationId = OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;

		LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

		if (tabs2.equals("current")) {
			organizationParams.put("groupOrganization", Long.valueOf(siteMembershipsDisplayContext.getGroupId()));
			organizationParams.put("organizationsGroups", Long.valueOf(siteMembershipsDisplayContext.getGroupId()));
		}
		%>

		<liferay-ui:search-container-results>

			<%
			total = OrganizationLocalServiceUtil.searchCount(company.getCompanyId(), parentOrganizationId, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams);

			organizationSearchContainer.setTotal(total);

			results = OrganizationLocalServiceUtil.search(company.getCompanyId(), parentOrganizationId, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams, organizationSearchContainer.getStart(), organizationSearchContainer.getEnd(), organizationSearchContainer.getOrderByComparator());

			organizationSearchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>
			<liferay-ui:search-container-row-parameter
				name="group"
				value="<%= siteMembershipsDisplayContext.getGroup() %>"
			/>

			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= true %>"
			>

				<%
				Group group = siteMembershipsDisplayContext.getGroup();
				%>

				<%= organization.getName() %>

				<c:if test="<%= group.getOrganizationId() == organization.getOrganizationId() %>">
					<liferay-ui:icon-help message='<%= LanguageUtil.format(request, "this-site-belongs-to-x-which-is-an-organization-of-type-x", new String[] {organization.getName(), LanguageUtil.get(request, organization.getType())}, false) + StringPool.SPACE + LanguageUtil.format(request, "all-users-of-x-are-automatically-members-of-the-site", organization.getName(), false) %>' />
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="parent-organization"
				value="<%= HtmlUtil.escape(organization.getParentOrganizationName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				orderable="<%= true %>"
				value="<%= LanguageUtil.get(request, organization.getType()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="city"
				value="<%= HtmlUtil.escape(organization.getAddress().getCity()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="region"
				value="<%= UsersAdmin.ORGANIZATION_REGION_NAME_ACCESSOR.get(organization) %>"
			/>

			<liferay-ui:search-container-column-text
				name="country"
				value="<%= UsersAdmin.ORGANIZATION_COUNTRY_NAME_ACCESSOR.get(organization) %>"
			/>

			<c:if test='<%= tabs2.equals("current") %>'>
				<liferay-ui:search-container-column-jsp
					cssClass="list-group-item-field"
					path="/organization_action.jsp"
				/>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-util:buffer var="formButton">
			<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, siteMembershipsDisplayContext.getGroupId(), ActionKeys.ASSIGN_MEMBERS) %>">
				<c:choose>
					<c:when test='<%= tabs2.equals("current") %>'>

						<%
						viewOrganizationsURL.setParameter("tabs2", "available");
						%>

						<liferay-frontend:add-menu>
							<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "assign-organizations") %>' url="<%= viewOrganizationsURL.toString() %>" />
						</liferay-frontend:add-menu>

						<%
						viewOrganizationsURL.setParameter("tabs2", "current");
						%>

					</c:when>
					<c:otherwise>

						<%
						PortletURL portletURL = siteMembershipsDisplayContext.getPortletURL();

						portletURL.setParameter("tabs2", "current");
						portletURL.setParameter("cur", String.valueOf(siteMembershipsDisplayContext.getCur()));

						String taglibOnClick = renderResponse.getNamespace() + "updateGroupOrganizations('" + portletURL.toString() + "');";
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
	function <portlet:namespace />updateGroupOrganizations(assignmentsRedirect) {
		var Util = Liferay.Util;

		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('assignmentsRedirect').val(assignmentsRedirect);
		form.fm('addOrganizationIds').val(Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
		form.fm('removeOrganizationIds').val(Util.listUncheckedExcept(form, '<portlet:namespace />allRowIds'));

		submitForm(form, '<portlet:actionURL name="editGroupOrganizations" />');
	}
</aui:script>