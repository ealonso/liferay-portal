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
UserGroupRolesDisplayContext userGroupRolesDisplayContext = new UserGroupRolesDisplayContext(request, renderRequest, renderResponse);
%>

<clay:management-toolbar
	displayContext="<%= new UserGroupRolesManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, userGroupRolesDisplayContext) %>"
/>

<aui:form cssClass="container-fluid-1280 portlet-site-memberships-assign-site-roles" name="fm">
	<liferay-ui:search-container
		id="userGroupGroupRoleRole"
		searchContainer="<%= userGroupRolesDisplayContext.getRoleSearchSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Role"
			keyProperty="roleId"
			modelVar="role"
		>

			<%
			String displayStyle = userGroupRolesDisplayContext.getDisplayStyle();
			%>

			<%@ include file="/role_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= userGroupRolesDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />userGroupGroupRoleRole');

	searchContainer.on(
		'rowToggled',
		function(event) {
			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(userGroupRolesDisplayContext.getEventName()) %>',
				{
					data: event.elements.allSelectedElements.getDOMNodes()
				}
			);
		}
	);

	function updateSelectedRoleIds() {
		var selectedRoleIdsInput = $('[name=<portlet:namespace />selectedRoleIds]');

		var selectedRoleIds = selectedRoleIdsInput.val();

		var list;

		if (selectedRoleIds === '') {
			list = new A.ArrayList();
		}
		else {
			var array = selectedRoleIds.split('<%= StringPool.COMMA %>');

			list = new A.ArrayList(array);
		}

		var rowIds = $('input[name=<portlet:namespace />rowIds]:not(.hide)');

		rowIds.each(
			function(index, input) {
				var checked = input.checked;

				var value = input.value;

				if (checked) {
					if (list.indexOf(value) === -1) {
						list.add(value);
					}
				}
				else {
					list.remove(value);
				}
			}
		);

		var updatedSelectedRoleIds = '';

		list.each(
			function(item, index) {
				if (index > 0) {
					updatedSelectedRoleIds += '<%= StringPool.COMMA %>';
				}

				updatedSelectedRoleIds += item;
			}
		);

		selectedRoleIdsInput.val(updatedSelectedRoleIds);
	}

	var opener = Liferay.Util.getOpener();

	opener.Liferay.on('updateSelectedRoleIds', updateSelectedRoleIds);

	Liferay.on('submitForm', updateSelectedRoleIds);

	var modal = Liferay.Util.getWindow();

	function clearHandles(event) {
		opener.Liferay.detach('updateSelectedRoleIds', updateSelectedRoleIds);

		Liferay.detach('submitForm', updateSelectedRoleIds);

		modal.detach('visibleChange', clearHandles);

		Liferay.detach('destroyPortlet', clearHandles);
	}

	modal.after('visibleChange', clearHandles);

	Liferay.on('destroyPortlet', clearHandles);
</aui:script>