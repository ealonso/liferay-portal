<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

<div class="asset-content">

	<%
	String redirect = ParamUtil.getString(request, "redirect");

	long trashEntryId = ParamUtil.getLong(request, "trashEntryId");

	String className = ParamUtil.getString(request, "className");
	long classPK = ParamUtil.getLong(request, "classPK");

	TrashEntry entry = null;

	if (trashEntryId > 0) {
		entry = TrashEntryLocalServiceUtil.getEntry(trashEntryId);
	}
	else if (Validator.isNotNull(className) && (classPK > 0)) {
		entry = TrashEntryLocalServiceUtil.fetchEntry(className, classPK);
	}

	if (entry != null) {
		className = entry.getClassName();
		classPK = entry.getClassPK();
	}

	TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(className);

	TrashRenderer trashRenderer = trashHandler.getTrashRenderer(classPK);

	String path = trashRenderer.render(renderRequest, renderResponse, AssetRenderer.TEMPLATE_FULL_CONTENT);

	boolean showActions = (entry != null) && (entry.getRootEntry() == null);
	%>

	<liferay-ui:header
		backURL="<%= redirect %>"
		localizeTitle="<%= false %>"
		title="<%= trashRenderer.getTitle(locale) %>"
	/>

	<aui:layout>
		<aui:column columnWidth="100" cssClass="context-pane" last="<%= true %>">
			<c:if test="<%= showActions %>">
				<liferay-ui:app-view-toolbar>
					<aui:button-row cssClass="edit-toolbar" id='<%= renderResponse.getNamespace() + "entryToolbar" %>' />
				</liferay-ui:app-view-toolbar>
			</c:if>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(path) %>">
					<liferay-util:include page="<%= path %>" portletId="<%= trashRenderer.getPortletId() %>">
						<liferay-util:param name="showHeader" value="<%= Boolean.FALSE.toString() %>" />
					</liferay-util:include>
				</c:when>
				<c:otherwise>
					<%= trashRenderer.getSummary(locale) %>
				</c:otherwise>
			</c:choose>

			<c:if test="<%= trashRenderer instanceof AssetRenderer %>">

				<%
				AssetRenderer assetRenderer = (AssetRenderer)trashRenderer;
				%>

				<c:if test="<%= !assetRenderer.getAssetRendererFactoryClassName().equals(DLFileEntryAssetRendererFactory.CLASS_NAME) %>">
					<div class="asset-ratings">
						<liferay-ui:ratings
							className="<%= className %>"
							classPK="<%= classPK %>"
						/>
					</div>

					<%
					AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(className, classPK);
					%>

					<div class="asset-related-assets">
						<liferay-ui:asset-links
							assetEntryId="<%= assetEntry.getEntryId() %>"
						/>
					</div>

					<c:if test="<%= Validator.isNotNull(assetRenderer.getDiscussionPath()) %>">
						<portlet:actionURL var="discussionURL">
							<portlet:param name="struts_action" value="/trash/edit_discussion" />
						</portlet:actionURL>

						<div class="asset-discussion">
							<liferay-ui:discussion
								className="<%= className %>"
								classPK="<%= classPK %>"
								formAction="<%= discussionURL %>"
								formName='<%= "fm" + classPK %>'
								redirect="<%= currentURL %>"
								subject="<%= trashRenderer.getTitle(locale) %>"
								userId="<%= assetEntry.getUserId() %>"
							/>
						</div>
					</c:if>
				</c:if>
			</c:if>
		</aui:column>
	</aui:layout>
</div>

<c:if test="<%= showActions %>">
	<aui:script use="aui-base,aui-toolbar">
		var buttonRow = A.one('#<portlet:namespace />entryToolbar');

		var entryToolbarChildren = [];

		<portlet:actionURL var="deleteEntryURL">
			<portlet:param name="struts_action" value="/trash/edit_entry" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="trashEntryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
		</portlet:actionURL>

		entryToolbarChildren.push(
			{
				handler: function(event) {
					if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
						Liferay.fire('<portlet:namespace />checkEntry', {trashEntryId: <%= entry.getEntryId() %>, uri: '<%= deleteEntryURL.toString() %>'});
					}
				},
				icon: 'delete',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "delete") %>'
			}
		);

		<portlet:actionURL var="restoreEntryURL">
			<portlet:param name="struts_action" value="/trash/edit_entry" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="trashEntryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
		</portlet:actionURL>

		entryToolbarChildren.push(
			{
				handler: function(event) {
					Liferay.fire('<portlet:namespace />checkEntry', {trashEntryId: <%= entry.getEntryId() %>, uri: '<%= restoreEntryURL.toString() %>'});
				},
				icon: 'undo',
				label: '<%= UnicodeLanguageUtil.get(pageContext, "restore") %>'
			}
		);

		var entryToolbar = new A.Toolbar(
			{
				activeState: false,
				boundingBox: buttonRow,
				children: entryToolbarChildren
			}
		).render();

		buttonRow.setData('entryToolbar', entryToolbar);
	</aui:script>
</c:if>

<aui:script use="liferay-restore-entry">
	new Liferay.RestoreEntry(
		{
			checkEntryURL: '<portlet:actionURL><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.CHECK %>" /><portlet:param name="struts_action" value="/trash/edit_entry" /></portlet:actionURL>',
			namespace: '<portlet:namespace />',
			restoreEntryURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/trash/restore_entry" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>'
		}
	);
</aui:script>
