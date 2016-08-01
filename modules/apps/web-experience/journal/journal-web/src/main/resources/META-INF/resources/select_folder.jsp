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
JournalFolder folder = journalDisplayContext.getFolder();

String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

String folderName = LanguageUtil.get(request, "home");

if (folder != null) {
	folderName = folder.getName();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/select_folder.jsp");
portletURL.setParameter("eventName", eventName);

JournalPortletUtil.addPortletBreadcrumbEntries(folder, request, portletURL);
%>

<aui:form cssClass="container-fluid-1280" method="post" name="selectFolderFm">
	<liferay-ui:breadcrumb showCurrentGroup="<%= false %>" showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

	<aui:button-row cssClass="text-center">

		<%
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("folderid", String.valueOf(journalDisplayContext.getFolderId()));
		data.put("foldername", HtmlUtil.escape(folderName));
		%>

		<aui:button cssClass="selector-button" data="<%= data %>" value="choose-this-folder" />
	</aui:button-row>

	<%
	PortletURL iteratorURL = renderResponse.createRenderURL();

	iteratorURL.setParameter("mvcPath", "/select_folder.jsp");
	iteratorURL.setParameter("folderId", String.valueOf(journalDisplayContext.getFolderId()));
	iteratorURL.setParameter("eventName", eventName);
	%>

	<liferay-ui:search-container
		iteratorURL="<%= iteratorURL %>"
		total="<%= JournalFolderServiceUtil.getFoldersCount(scopeGroupId, journalDisplayContext.getFolderId()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= JournalFolderServiceUtil.getFolders(scopeGroupId, journalDisplayContext.getFolderId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalFolderModel"
			escapedModel="<%= true %>"
			keyProperty="folderId"
			modelVar="curFolder"
			rowVar="row"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcPath" value="/select_folder.jsp" />
				<portlet:param name="folderId" value="<%= String.valueOf(curFolder.getFolderId()) %>" />
				<portlet:param name="eventName" value="<%= eventName %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			/>

			<liferay-ui:search-container-column-text
				name="num-of-folders"
				value="<%= String.valueOf(JournalFolderServiceUtil.getFoldersCount(scopeGroupId, curFolder.getFolderId())) %>"
			/>

			<liferay-ui:search-container-column-text
				name="num-of-web-content-instances"
				value="<%= String.valueOf(JournalArticleServiceUtil.getFoldersAndArticlesCount(scopeGroupId, Arrays.asList(curFolder.getFolderId()))) %>"
			/>

			<c:if test="<%= rowURL != null %>">
				<liferay-ui:search-container-column-text>

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("folderid", curFolder.getFolderId());
					data.put("foldername", HtmlUtil.escape(curFolder.getName()));
					%>

					<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var selectFolderFm = A.one('#<portlet:namespace />selectFolderFm');

	selectFolderFm.delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			selectFolderFm.all('.selector-button').removeClass('selected');

			currentTarget.addClass('selected');

			var data = {
				folderId: currentTarget.attr('data-folderid'),
				folderName: currentTarget.attr('data-foldername')
			};

			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(eventName) %>',
				{
					data: data
				}
			);
		},
		'.selector-button'
	);
</aui:script>

<aui:script require="journal-web/js/TreeviewCards.es, metal-dom/src/dom">
	var TreeviewCards = journalWebJsTreeviewCardsEs.default;

	var DOM = metalDomSrcDom.default;

	new TreeviewCards(
		{
			nodes: [
				{
					children: [
						{
							children: [
								{
									icon: 'folder',
									id: 3,
									name: 'year: 2012'
								},
								{
									icon: 'folder',
									id: 4,
									name: 'director: Thomas Vinterberg'
								}
							],
							icon: 'folder',
							id: 2,
							name: 'The Hunt'
						},
						{
							children: [
								{
									icon: 'folder',
									id: 6,
									name: 'year: 2015'
								},
								{
									children: [
										{
											icon: 'folder',
											id: 8,
											name: 'year: 2012'
										},
										{
											icon: 'folder',
											id: 9,
											name: 'director: Thomas Vinterberg'
										}
									],
									icon: 'folder',
									id: 7,
									name: 'director: Damián Szifron'
								}
							],
							expanded: true,
							icon: 'folder',
							id: 5,
							name: 'Wild Tales'
						}
					],
					expanded: true,
					icon: 'folder',
					id: 1,
					name: 'Movies'
				}
			],
			onNodeClick: function(event) {
				var currentTarget = event.delegateTarget;

				this.element.querySelectorAll('.selectable').forEach(
					function(element) {
						DOM.removeClasses(element, 'selected');
					}
				);

				DOM.addClasses(currentTarget.querySelector('.selectable'), 'selected');

				var data = {
					folderId: currentTarget.getAttribute('data-treeitemid'),
					folderName: currentTarget.getAttribute('data-treeitemname')
				};

				Liferay.Util.getOpener().Liferay.fire(
					'<%= HtmlUtil.escapeJS(eventName) %>',
					{
						data: data
					}
				);
			},
			pathThemeImages: '<%= themeDisplay.getPathThemeImages() %>'
		},
		'[name="<portlet:namespace />selectFolderFm"]'
	);
</aui:script>