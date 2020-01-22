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

<%@ include file="/dynamic_include/init.jsp" %>

<div class="control-menu-level-0"></div>

<div class="change-tracking-indicator">
	<react:component
		data="<%= changeTrackingIndicatorDisplayContext.getReactData() %>"
		module="dynamic_include/ChangeTrackingIndictator"
	/>
</div>

<aui:script use="liferay-portlet-url">
	var onDestroyPortlet = function() {
		Liferay.detach('destroyPortlet', onDestroyPortlet);
		Liferay.detach(
			'<%= changeTrackingIndicatorDisplayContext.getEventName() %>',
			onSelectChangeList
		);
	};

	Liferay.on('destroyPortlet', onDestroyPortlet);

	var onSelectChangeList = function() {
		Liferay.Util.selectEntity(
			{
				dialog: {
					constrain: true,
					height: 580,
					modal: true,
					width: 900
				},
				id:
					'<%= changeTrackingIndicatorDisplayContext.getNamespace() + "selectChangeList" %>',
				title: '<liferay-ui:message key="select-a-publication" />',
				uri:
					'<%= changeTrackingIndicatorDisplayContext.getSelectChangeListURL() %>'
			},
			function(event) {
				var checkoutURL = Liferay.PortletURL.createURL(
					'<%= changeTrackingIndicatorDisplayContext.getCheckoutURL() %>'
				);

				checkoutURL.setParameter('ctCollectionId', event.ctcollectionid);

				Liferay.Util.navigate(checkoutURL);
			}
		);
	};

	Liferay.on(
		'<%= changeTrackingIndicatorDisplayContext.getEventName() %>',
		onSelectChangeList
	);
</aui:script>