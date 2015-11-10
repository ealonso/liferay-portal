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

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectUser");

long teamId = ParamUtil.getLong(request, "teamId");

Team team = TeamServiceUtil.fetchTeam(teamId);

Group group = GroupLocalServiceUtil.getGroup(team.getGroupId());

if (group != null) {
	group = StagingUtil.getLiveGroup(group.getGroupId());
}
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcPath" value="/select_user.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="eventName" value="<%= eventName %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL.toString() %>" cssClass="form-search" method="get" name="selectUserFm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<liferay-ui:search-container
		rowChecker="<%= new UserTeamChecker(renderResponse, team) %>"
		searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
		var="userSearchContainer"
	>

		<%
		UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

		LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

		userParams.put("inherit", Boolean.TRUE);
		userParams.put("usersGroups", group.getGroupId());
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
			cssClass="selectable"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>
			<liferay-ui:search-container-column-text
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				name="screen-name"
				property="screenName"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>

	<aui:button-row>
		<aui:button cssClass="btn-lg btn-primary" id="done" value="done" />

		<aui:button cssClass="btn-lg" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	var openingLiferay = Util.getOpener().Liferay;

	var form = $(document.<portlet:namespace />selectUserFm);

	$('#<portlet:namespace />done').on(
		'click',
		function(event) {
			var result = {
				userIds: Util.listCheckedExcept(form, '<portlet:namespace />allRowIds')
			};

			openingLiferay.fire('<%= HtmlUtil.escapeJS(eventName) %>', result);

			Util.getWindow().hide();
		}
	);
</aui:script>