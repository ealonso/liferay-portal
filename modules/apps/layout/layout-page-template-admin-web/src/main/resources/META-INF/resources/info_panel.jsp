<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<LayoutPageTemplateCollection> layoutPageTemplateCollections = (List<LayoutPageTemplateCollection>)request.getAttribute(LayoutPageTemplateAdminWebKeys.LAYOUT_PAGE_TEMPLATE_COLLECTIONS);
List<LayoutPageTemplateEntry> layoutPageTemplateEntries = (List<LayoutPageTemplateEntry>)request.getAttribute(LayoutPageTemplateAdminWebKeys.LAYOUT_PAGE_TEMPLATE_ENTRIES);

if (ListUtil.isEmpty(layoutPageTemplateCollections) && ListUtil.isEmpty(layoutPageTemplateEntries)) {
	layoutPageTemplateCollections = new ArrayList<>();

	layoutPageTemplateCollections.add(null);
}
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(layoutPageTemplateCollections) && ListUtil.isEmpty(layoutPageTemplateEntries) && (layoutPageTemplateCollections.size() == 1) %>">

		<%
		LayoutPageTemplateCollection layoutPageTemplateCollection = layoutPageTemplateCollections.get(0);
		%>

		<div class="sidebar-header">
			<clay:content-row
				cssClass="sidebar-section"
			>
				<clay:content-col
					expand="<%= true %>"
				>
					<h1 class="component-title">
						<%= (layoutPageTemplateCollection != null) ? HtmlUtil.escape(layoutPageTemplateCollection.getName()) : LanguageUtil.get(request, "home") %>
					</h1>

					<h2 class="component-subtitle">
						<liferay-ui:message key="folder" />
					</h2>
				</clay:content-col>
			</clay:content-row>
		</div>

		<div class="sidebar-body">
			<p class="sidebar-dt">
				<liferay-ui:message key="num-of-items" />
			</p>

			<c:if test="<%= layoutPageTemplateCollection == null %>">
				<p class="sidebar-dd text-secondary">
					<%= LayoutPageTemplateEntryServiceUtil.getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(scopeGroupId, LayoutPageTemplateConstants.PARENT_LAYOUT_PAGE_TEMPLATE_COLLECTION_ID_DEFAULT, LayoutPageTemplateEntryTypeConstants.TYPE_DISPLAY_PAGE) %>
				</p>
			</c:if>

			<c:if test="<%= layoutPageTemplateCollection != null %>">
				<p class="sidebar-dd text-secondary">
					<%= LayoutPageTemplateEntryServiceUtil.getLayoutPageCollectionsAndLayoutPageTemplateEntriesCount(layoutPageTemplateCollection.getGroupId(), layoutPageTemplateCollection.getLayoutPageTemplateCollectionId(), layoutPageTemplateCollection.getType()) %>
				</p>

				<p class="sidebar-dt">
					<liferay-ui:message key="location" />
				</p>

				<p class="sidebar-dd text-secondary">
					<clay:icon
						symbol="folder"
					/>

					<%
					List<String> paths = TransformUtil.transform(layoutPageTemplateCollection.getAncestors(), curLayoutPageTemplateCollection -> HtmlUtil.escape(curLayoutPageTemplateCollection.getName()));

					paths.add(LanguageUtil.get(request, "home"));

					Collections.reverse(paths);
					%>

					<%= StringUtil.merge(paths, " > ") %>
				</p>

				<p class="sidebar-dt">
					<liferay-ui:message key="created" />
				</p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getCreateDate().toString()) %>
				</p>

				<p class="sidebar-dt">
					<liferay-ui:message key="modified" />
				</p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getModifiedDate().toString()) %>
				</p>

				<p class="sidebar-dt">
					<liferay-ui:message key="description" />
				</p>

				<p class="sidebar-dd text-secondary">
					<%= HtmlUtil.escape(layoutPageTemplateCollection.getDescription()) %>
				</p>
			</c:if>
		</div>
	</c:when>
	<c:when test="<%= ListUtil.isEmpty(layoutPageTemplateCollections) && ListUtil.isNotEmpty(layoutPageTemplateEntries) && (layoutPageTemplateEntries.size() == 1) %>">
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<clay:content-row
				cssClass="sidebar-section"
			>
				<clay:content-col
					expand="<%= true %>"
				>
					<h1 class="component-title"><liferay-ui:message arguments="<%= layoutPageTemplateCollections.size() + layoutPageTemplateEntries.size() %>" key="x-items-are-selected" /></h1>
				</clay:content-col>
			</clay:content-row>
		</div>
	</c:otherwise>
</c:choose>