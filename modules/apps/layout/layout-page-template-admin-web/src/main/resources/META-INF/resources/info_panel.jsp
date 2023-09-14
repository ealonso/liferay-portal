<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
DisplayPageDisplayContext displayPageDisplayContext = new DisplayPageDisplayContext(request, renderRequest, renderResponse);

String[] selectedDPTsAndDPTCollectionIDs = (String[])request.getAttribute(LayoutWebKeys.SELECTED_LPT_AND_LPTCOLLECTION_IDS);

boolean multipleSelection = false;

long layoutPageTemplateCollectionId = displayPageDisplayContext.getLayoutPageTemplateCollectionId();

LayoutPageTemplateCollection layoutPageTemplateCollection = displayPageDisplayContext.getLayoutPageTemplateCollection();

int totalItemsSelected = 0;

if (selectedDPTsAndDPTCollectionIDs != null) {
	totalItemsSelected = selectedDPTsAndDPTCollectionIDs.length;

	if (totalItemsSelected > 1) {
		multipleSelection = true;
	}
	else if (totalItemsSelected == 1) {
		layoutPageTemplateCollection = displayPageDisplayContext.getLayoutPageTemplateCollection(GetterUtil.getLong(selectedDPTsAndDPTCollectionIDs[0]));
	}
	else {
		layoutPageTemplateCollection = displayPageDisplayContext.getLayoutPageTemplateCollection();
	}
}
%>

<c:choose>
	<c:when test="<%= multipleSelection %>">
		<div class="sidebar-header">
			<clay:content-row
				cssClass="sidebar-section"
			>
				<clay:content-col
					expand="<%= true %>"
				>
					<h1 class="component-title"><liferay-ui:message arguments="<%= totalItemsSelected %>" key="x-items-are-selected" /></h1>
				</clay:content-col>
			</clay:content-row>
		</div>
	</c:when>
	<c:when test="<%= !multipleSelection %>">
		<div class="sidebar-header">
			<clay:content-row
				cssClass="sidebar-section"
			>
				<clay:content-col
					expand="<%= true %>"
				>
					<h1 class="component-title"><%= (layoutPageTemplateCollection != null) ? HtmlUtil.escape(layoutPageTemplateCollection.getName()) : LanguageUtil.get(request, "home") %></h1>

					<h2 class="component-subtitle">
						<liferay-ui:message key="folder" />
					</h2>
				</clay:content-col>
			</clay:content-row>
		</div>

		<div class="sidebar-body">
			<p class="sidebar-dt"><liferay-ui:message key="num-of-items" /></p>

			<c:if test="<%= layoutPageTemplateCollectionId == LayoutPageTemplateConstants.PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT %>">
				<p class="sidebar-dd text-secondary">
					<%= LayoutPageTemplateEntryServiceUtil.getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(0, LayoutPageTemplateConstants.PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT, 1) %>
				</p>
			</c:if>

			<c:if test="<%= layoutPageTemplateCollection != null %>">
				<p class="sidebar-dd text-secondary">
					<%= LayoutPageTemplateEntryServiceUtil.getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(layoutPageTemplateCollection.getGroupId(), layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(), layoutPageTemplateCollection.getType()) %>
				</p>

				<p class="sidebar-dt"><liferay-ui:message key="location" /></p>

				<p class="sidebar-dd text-secondary">
					<clay:icon
						symbol="folder"
					/>

					<%=
					HtmlUtil.escape(StringUtil.merge(
						TransformUtil.transform(
							displayPageDisplayContext.getLayoutPageTemplateBreadcrumbEntries(),
							BreadcrumbEntry::getTitle)
						, " > ")) %>
				</p>

				<p class="sidebar-dt"><liferay-ui:message key="created" /></p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getCreateDate().toString()) %>
				</p>

				<p class="sidebar-dt"><liferay-ui:message key="modified" /></p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getModifiedDate().toString()) %>
				</p>

				<p class="sidebar-dt"><liferay-ui:message key="description" /></p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getDescription()) %>
				</p>
			</c:if>
		</div>
	</c:when>
</c:choose>