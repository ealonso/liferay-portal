<%@ page import="com.liferay.portlet.wiki.model.WikiPage" %>
<%@ page import="com.liferay.portlet.wiki.model.WikiPageResource" %>
<%@ page import="com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.wiki.service.WikiPageResourceLocalServiceUtil" %>
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

<%@ include file="/html/portlet/document_selector/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");

DocumentSelector documentSelector = (DocumentSelector)request.getAttribute("DOCUMENT_SELECTOR");

int entryStart = ParamUtil.getInteger(request, "entryStart");
int entryEnd = ParamUtil.getInteger(request, "entryEnd", PropsValues.SEARCH_CONTAINER_PAGE_DEFAULT_DELTA);

String keywords = ParamUtil.getString(request, "keywords");

String eventName = ParamUtil.getString(request, "eventName");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/document_selector/view");
portletURL.setParameter("eventName", eventName);
portletURL.setParameter("groupId", String.valueOf(groupId));
%>

<aui:form method="post" name="selectDocumentFm">

	<%
	FileEntrySearch fileEntrySearchContainer = new FileEntrySearch(renderRequest, portletURL);

	FileEntryDisplayTerms displayTerms = (FileEntryDisplayTerms)fileEntrySearchContainer.getDisplayTerms();

	displayTerms.setSelectedGroupId(groupId);
	%>

	<aui:nav-bar>
		<aui:nav>
			<aui:nav-item
				dropdown="<%= true %>"
				iconCssClass="icon-plus"
				label="add"
			>
				<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) %>">
					<liferay-portlet:renderURL portletName="<%= PortletKeys.DOCUMENT_LIBRARY %>" var="editFileEntryURL">
						<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="backURL" value="<%= currentURL %>" />
						<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
						<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					</liferay-portlet:renderURL>

					<%
					AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(DLFileEntry.class.getName());
					%>

					<aui:nav-item
						href="<%= editFileEntryURL %>"
						iconCssClass="<%= assetRendererFactory.getIconCssClass() %>"
						label="basic-document"
					/>
				</c:if>
			</aui:nav-item>
		</aui:nav>

		<aui:nav-bar-search cssClass="pull-right"
			file="/html/portlet/document_selector/search.jsp"
			searchContainer="<%= fileEntrySearchContainer %>"
		/>
	</aui:nav-bar>

	<%
	PortletURL iteratorURL = renderResponse.createRenderURL();

	iteratorURL.setParameter("struts_action", "/document_selector/view");
	iteratorURL.setParameter("eventName", eventName);
	iteratorURL.setParameter("groupId", String.valueOf(groupId));
	%>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-documents-in-this-folder"
		iteratorURL="<%= iteratorURL %>"
	>

		<%
		List<FileEntry> attachments = new ArrayList<FileEntry>();

		long wikiPageResourcePrimKey = ParamUtil.getLong(request, "wikiPageResourcePrimKey");

		WikiPageResource pageResource = WikiPageResourceLocalServiceUtil.fetchWikiPageResource(wikiPageResourcePrimKey);

		if (pageResource != null) {
			WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(pageResource.getNodeId(), pageResource.getTitle());

			attachments = wikiPage.getAttachmentsFileEntries(searchContainer.getStart(), searchContainer.getEnd());
		}

		Map<String, String> params = new HashMap<String, String>();

		HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(request);

		for (String param : httpServletRequest.getParameterMap().keySet()) {
			String[] values = httpServletRequest.getParameterValues(param);

			params.put(param, StringUtil.merge(values));
		}
		%>

		<liferay-ui:search-container-results
			results="<%= attachments %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.repository.model.FileEntry"
			keyProperty="fileEntryId"
			modelVar="fileEntry"
		>
			<liferay-ui:search-container-column-text
				name="document"
			>
				<img align="left" alt="" src="<%= DLUtil.getThumbnailSrc(fileEntry, null, themeDisplay) %>" style="<%= DLUtil.getThumbnailStyle() %>" />
				<%= HtmlUtil.escape(fileEntry.getTitle()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="size"
				value="<%= TextFormatter.formatStorageSize(fileEntry.getSize(), locale) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>(params);

				data.put("groupid", fileEntry.getGroupId());
				data.put("title", fileEntry.getTitle());
				data.put("url", DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK, false, false));
				data.put("uuid", fileEntry.getUuid());
				data.put("version", fileEntry.getVersion());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectDocumentFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>