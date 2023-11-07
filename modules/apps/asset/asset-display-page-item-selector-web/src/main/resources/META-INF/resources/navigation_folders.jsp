<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetDisplayPagesItemSelectorViewDisplayContext assetDisplayPagesItemSelectorViewDisplayContext = (AssetDisplayPagesItemSelectorViewDisplayContext)request.getAttribute("ASSET_DISPLAY_PAGES_ITEM_SELECTOR_DISPLAY_CONTEXT");
%>

<c:if test='<%= FeatureFlagManagerUtil.isEnabled("LPS-189856") %>'>
	<clay:container-fluid
		cssClass="container-view sidenav-content"
	>
<%--		<liferay-site-navigation:breadcrumb--%>
<%--		breadcrumbEntries="<%= assetDisplayPagesItemSelectorViewDisplayContext.getLayoutPageTemplateBreadcrumbEntries() %>"--%>

		<liferay-ui:search-container
			id="displayPages"
			searchContainer="<%= assetDisplayPagesItemSelectorViewDisplayContext.getAssetDisplayPageSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="Object"
				modelVar="object"
			>

				<%
				LayoutPageTemplateCollection curLayoutPageTemplateCollection = null;
				LayoutPageTemplateEntry curLayoutPageTemplateEntry = null;

				Object result = row.getObject();

				if (result instanceof LayoutPageTemplateEntry) {
					curLayoutPageTemplateEntry = (LayoutPageTemplateEntry)result;
				}
				else {
					curLayoutPageTemplateCollection = (LayoutPageTemplateCollection)result;
				}
				%>

				<c:choose>
					<c:when test="<%= curLayoutPageTemplateCollection != null %>">
						<liferay-ui:search-container-column-text
							colspan="<%= 2 %>"
						>
							<clay:horizontal-card
								horizontalCard="<%= new DisplayPageTemplateCollectionHorizontalCard (curLayoutPageTemplateCollection, renderRequest, renderResponse, searchContainer.getRowChecker()) %>"
								propsTransformer="js/propsTransformers/LayoutPageTemplateCollectionPropsTransformer"
							/>
						</liferay-ui:search-container-column-text>
					</c:when>
					<c:when test="<%= curLayoutPageTemplateEntry != null %>">
						<liferay-ui:search-container-column-text>
							<clay:vertical-card
								propsTransformer="js/propsTransformers/DisplayPageDropdownPropsTransformer"
								verticalCard="<%= new DisplayPageVerticalCard(curLayoutPageTemplateEntry, renderRequest, renderResponse, searchContainer.getRowChecker()) %>"
							/>
						</liferay-ui:search-container-column-text>
					</c:when>
				</c:choose>
			</liferay-ui:search-container-row>
		</liferay-ui:search-container>

	</clay:container-fluid>
</c:if>