<%@ page import="com.liferay.layout.admin.web.internal.servlet.taglib.ui.NavigationStep" %>

<%@ page import="java.util.Arrays" %>

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

	request.setAttribute("multi_step_nav_small.jsp-navigationSteps", Arrays.asList(new NavigationStep(false, "Select Template", "www.example.com"), new NavigationStep(true, "Select Collection", "www.example.com"), new NavigationStep(false, "Configure Page", "www.example.com")));
%>

<div class="lfr-search-container-wrapper" id="<portlet:namespace/>collectionProviders">

	<div style="padding-top: 30px; height: 66px; width: 100%; display: flex; justify-content: center; align-items: center; background: white;">
		<liferay-util:include page="/multi_step_nav_small.jsp" servletContext="<%= application %>" />
	</div>

	<liferay-ui:search-container
		id="entries"
		searchContainer="<%= selectLayoutCollectionDisplayContext.getCollectionProvidersSearchContainer() %>"
		var="collectionsSearch"
	>
		<liferay-ui:search-container-row
			className="com.liferay.info.list.provider.InfoListProvider"
			cssClass="entry"
			modelVar="infoListProvider"
		>

			<%
			row.setCssClass("entry-card entry-display-style lfr-asset-item " + row.getCssClass());
			%>

			<liferay-ui:search-container-column-text>
				<clay:vertical-card
					verticalCard="<%= new CollectionProvidersVerticalCard(selectLayoutCollectionDisplayContext.getSelGroupId(), infoListProvider, renderRequest, renderResponse) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="icon"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>