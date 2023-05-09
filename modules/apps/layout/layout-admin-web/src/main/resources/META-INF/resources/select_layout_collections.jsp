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
SelectLayoutCollectionDisplayContext selectLayoutCollectionDisplayContext = (SelectLayoutCollectionDisplayContext)request.getAttribute(LayoutAdminWebKeys.SELECT_LAYOUT_COLLECTION_DISPLAY_CONTEXT);

SelectCollectionManagementToolbarDisplayContext selectCollectionManagementToolbarDisplayContext = new SelectCollectionManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, selectLayoutCollectionDisplayContext);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(layoutsAdminDisplayContext.getBackURL());

renderResponse.setTitle(LanguageUtil.get(request, "select-collection"));
%>

<div class="sheet-row">
	<clay:tabs
		tabsItems="<%= selectLayoutCollectionDisplayContext.getTabsItems() %>"
	>
		<c:if test="<%= selectLayoutCollectionDisplayContext.isCollections() %>">
			<div>
				<clay:management-toolbar
					managementToolbarDisplayContext="<%= selectCollectionManagementToolbarDisplayContext %>"
					propsTransformer="js/SelectLayoutCollectionManagementToolbarPropsTransformer"
				/>

				<liferay-util:include page="/select_collections.jsp" servletContext="<%= application %>" />
			</div>
		</c:if>

		<div>
			<liferay-util:include page="/select_collection_providers.jsp" servletContext="<%= application %>" />
		</div>
	</clay:tabs>
</div>

<aui:script require="frontend-js-web/index as frontendJsWeb">
	var {delegate} = frontendJsWeb;

	var collections = document.getElementById('<portlet:namespace />collections');

	var selectLayoutMasterLayoutActionOptionQueryClickHandler = delegate(
		collections,
		'click',
		'.select-collection-action-option',
		(event) => {
			Liferay.Util.navigate(
				event.delegateTarget.dataset.selectLayoutMasterLayoutUrl
			);
		}
	);

	function handleDestroyPortlet() {
		selectLayoutMasterLayoutActionOptionQueryClickHandler.dispose();

		Liferay.detach('destroyPortlet', handleDestroyPortlet);
	}

	Liferay.on('destroyPortlet', handleDestroyPortlet);
</aui:script>