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

<%@ include file="/html/portlet/trash/init.jsp" %>

<liferay-ui:restore-entry />

<portlet:actionURL var="selectContainerURL">
	<portlet:param name="struts_action" value="/trash/edit_entry" />
</portlet:actionURL>

<aui:form action="<%= selectContainerURL.toString() %>" method="post" name="selectContainerForm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.MOVE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="className" type="hidden" value="" />
	<aui:input name="classPK" type="hidden" value="" />
	<aui:input name="containerModelId" type="hidden" value="" />
</aui:form>

<aui:script use="aui-dialog-iframe-deprecated,liferay-util-window">
	A.getBody().delegate(
		'click',
		function(event) {
			var link = event.currentTarget.one('a');

			<portlet:namespace />restoreDialog(link.attr('data-uri'));
		},
		'.trash-restore-link'
	);

	Liferay.provide(
		window,
		'<portlet:namespace />restoreDialog',
		function(uri) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true,
						modal: true,
						width: 1024
					},
					eventName: '<portlet:namespace />selectFolder',
					id: '<portlet:namespace />selectFolder',
					title: '<liferay-ui:message key="warning" />',
					uri: uri
				},
				function(event) {
					document.<portlet:namespace />selectContainerForm.<portlet:namespace />className.value = event.classname;
					document.<portlet:namespace />selectContainerForm.<portlet:namespace />classPK.value = event.classpk;
					document.<portlet:namespace />selectContainerForm.<portlet:namespace />containerModelId.value = event.containermodelid;

					submitForm(document.<portlet:namespace />selectContainerForm);
				}
			);
		},
		['aui-base']
	);
</aui:script>