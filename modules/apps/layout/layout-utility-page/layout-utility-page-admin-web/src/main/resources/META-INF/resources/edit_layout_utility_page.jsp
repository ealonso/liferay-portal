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

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	redirect = portletURL.toString();
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(layoutUtilityPageDisplayContext.getTitle());
%>

<portlet:actionURL name="/layout_utility_pages/edit_layout_utility_page_entry" var="editLayoutUtilityPageURL" />

<liferay-frontend:edit-form
	action="<%= editLayoutUtilityPageURL %>"
	method="post"
	name="fm"
>
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-frontend:edit-form-body>
		<aui:model-context bean="<%= layoutUtilityPageDisplayContext.getLayoutUtilityPageEntry() %>" model="<%= LayoutUtilityPageEntry.class %>" />

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<aui:input name="layoutUtilityPageEntryId" type="hidden" value="<%= layoutUtilityPageDisplayContext.getLayoutUtilityPageEntryId() %>" />

				<aui:input autoFocus="<%= true %>" name="name" placeholder="name" />

				<aui:select inlineField="<%= true %>" label="choose-a-type" name="type">
					<aui:option label="<%= LanguageUtil.get(request, LayoutUtilityPageConstants.TYPE_NONE_LABEL) %>" value="0" />
					<aui:option label="<%= LanguageUtil.get(request, LayoutUtilityPageConstants.TYPE_404_LABEL) %>" value="1" />
				</aui:select>

				<aui:input label="layout" name="layoutName" type="resource" value="<%= layoutUtilityPageDisplayContext.getLayoutName() %>" />
				<aui:input name="layoutUuid" type="hidden" value="<%= layoutUtilityPageDisplayContext.getLayoutName() %>" />

				<aui:button name="selectLayoutButton" value="select" />
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script sandbox="<%= true %>">
	var selectLayoutButton = document.getElementById(
		'<portlet:namespace />selectLayoutButton'
	);

	selectLayoutButton.addEventListener('click', (event) => {
		event.preventDefault();

		Liferay.Util.openSelectionModal({
			onSelect: function (selectedItem) {
				var layoutName = document.getElementById(
					'<portlet:namespace />layoutName'
				);
				var layoutUuid = document.getElementById(
					'<portlet:namespace />layoutUuid'
				);

				if (selectedItem && layoutName && layoutUuid) {
					layoutName.value = selectedItem.name;
					layoutUuid.value = selectedItem.id;
				}
			},
			selectEventName:
				'<%= layoutUtilityPageDisplayContext.getEventName() %>',
			title: '<liferay-ui:message key="select-layout" />',
			url: '<%= layoutUtilityPageDisplayContext.getItemSelectorURL() %>',
		});
	});
</aui:script>