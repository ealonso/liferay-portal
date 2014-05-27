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

Group group = GroupLocalServiceUtil.fetchGroup(groupId);
%>

<div id="<portlet:namespace />displayPageItemContainer">

</div>

<aui:script use="aui-io-plugin-deprecated,aui-io-request,aui-tabview,aui-tree">
	var Lang = A.Lang;

	var Util = Liferay.Util;

	var TPL_TAB_CONTENT = '<div id="<portlet:namespace />{tabId}">' +
		'<div id="<portlet:namespace />{tabContentId}"></div>' +
	'</div>';

	var TPL_TAB_VIEW = '<div id="<portlet:namespace />{pagesTabViewId}"></div>' +
		'<div class="alert alert-block selected-page-message" id="<portlet:namespace />selectedPageMessage">' +
			'<%= UnicodeLanguageUtil.get(pageContext, "there-is-no-selected-page") %>' +
		'</div>';

	var displayPageItemContainer = A.one('#<portlet:namespace />displayPageItemContainer');

	var pagesTabViewId = A.guid();
	var privatePagesTabContentId = A.guid();
	var privatePagesTabId = A.guid();
	var publicPagesTabContentId = A.guid();
	var publicPagesTabId = A.guid();

	var privatePagesTabNode;
	var publicPagesTabNode;
	var selectedNodeMessage;
	var tabView;
	var treeViewPrivate;
	var treeViewPublic;

	var treePrivatePagesContainerId = '<portlet:namespace />treeContainerPrivatePagesOutput';
	var treePublicPagesContainerId = '<portlet:namespace />treeContainerPublicPagesOutput';

	var bindTreeUI = function(treeInstance) {
		treeInstance.after(
			'lastSelectedChange',
			function(event) {
				setSelectedPage(event.newVal);
			}
		);
	};

	var getChosenPagePath = function(node) {
		var buffer = [];

		if (A.instanceOf(node, A.TreeNode)) {
			var labelText = Util.escapeHTML(node.get('labelEl').text());

			buffer.push(labelText);

			node.eachParent(
				function(treeNode) {
					var labelEl = treeNode.get('labelEl');

					if (labelEl) {
						labelText = Util.escapeHTML(labelEl.text());

						buffer.unshift(labelText);
					}
				}
			);
		}

		return buffer.join(' > ');
	}

	var bodyContent = Lang.sub(
		TPL_TAB_VIEW,
		{
			pagesTabViewId: pagesTabViewId
		}
	);

	displayPageItemContainer.html(bodyContent);

	selectedNodeMessage = A.one('#<portlet:namespace />selectedPageMessage');

	var tabs = [];

	<c:if test="<%= group.getPublicLayoutsPageCount() > 0 %>">
		tabs.push(
			{
				label: '<%= UnicodeLanguageUtil.get(pageContext, "public-pages") %>',
				content: Lang.sub(
					TPL_TAB_CONTENT,
					{
						tabContentId: publicPagesTabContentId,
						tabId: publicPagesTabId
					}
				)
			}
		);
	</c:if>

	<c:if test="<%= group.getPrivateLayoutsPageCount() > 0 %>">
		tabs.push(
			{
				label: '<%= UnicodeLanguageUtil.get(pageContext, "private-pages") %>',
				content: Lang.sub(
					TPL_TAB_CONTENT,
					{
						tabContentId: privatePagesTabContentId,
						tabId: privatePagesTabId
					}
				)
			}
		);
	</c:if>

	tabView = new A.TabView(
		{
			children: tabs,
			contentBox: '#<portlet:namespace />' + pagesTabViewId
		}
	);

	tabView.render();

	tabView.after(
		'activeTabChange',
		function() {
			loadPages();
		}
	);

	<c:if test="<%= group.getPublicLayoutsPageCount() > 0 %>">
		publicPagesTabNode = A.one('#<portlet:namespace />' + publicPagesTabContentId);

		publicPagesTabNode.plug(A.Plugin.ParseContent);
	</c:if>

	<c:if test="<%= group.getPrivateLayoutsPageCount() > 0 %>">
		privatePagesTabNode = A.one('#<portlet:namespace />' + privatePagesTabContentId);

		privatePagesTabNode.plug(A.Plugin.ParseContent);
	</c:if>

	var isPublicPagesTabSelected = function() {
		var result = <%= group.getPublicLayoutsPageCount() > 0 %>;

		if (tabView.size() >= 2) {
			var index = tabView.indexOf(tabView.get('selection'));

			result = (index == 0);
		}

		return result;
	};

	var loadPages = function() {
		var url;

		var publicPages = isPublicPagesTabSelected();

		if (publicPages && !treeViewPublic) {
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" var="treeUrlPublicPages">
				<portlet:param name="struts_action" value="/document_selector/select_pages" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= ActionKeys.VIEW_TREE %>" />
				<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getSiteGroupId()) %>" />
				<portlet:param name="treeId" value="treeContainerPublicPages" />
				<portlet:param name="checkContentDisplayPage" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="draggableTree" value="<%= Boolean.FALSE.toString() %>" />
				<portlet:param name="expandFirstNode" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="saveState" value="<%= Boolean.FALSE.toString() %>" />
			</liferay-portlet:resourceURL>

			url = '<%= treeUrlPublicPages %>';
		}
		else if (!treeViewPrivate) {
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" var="treeUrlPrivatePages">
				<portlet:param name="struts_action" value="/document_selector/select_pages" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= ActionKeys.VIEW_TREE %>" />
				<portlet:param name="tabs1" value="private-pages" />
				<portlet:param name="groupId" value="<%= String.valueOf(themeDisplay.getSiteGroupId()) %>" />
				<portlet:param name="treeId" value="treeContainerPrivatePages" />
				<portlet:param name="checkContentDisplayPage" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="expandFirstNode" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="saveState" value="<%= Boolean.FALSE.toString() %>" />
			</liferay-portlet:resourceURL>

			url = '<%= treeUrlPrivatePages %>';
		}

		if (url) {
			A.io.request(
				url,
				{
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							onPagesLoad(response, publicPages);
						}
					}
				}
			);
		}
		else {
			var treeInstance = treeViewPrivate;

			if (publicPages) {
				treeInstance = treeViewPublic;
			}

			setSelectedPage(treeInstance.get('lastSelected'));
		}
	}

	var onPagesLoad = function(response, publicPages) {
		var treeContainerId;
		var treeWrapper;

		if (publicPages) {
			treeContainerId = treePublicPagesContainerId;
			treeWrapper = publicPagesTabNode;
		}
		else {
			treeContainerId = treePrivatePagesContainerId;
			treeWrapper = privatePagesTabNode;
		}

		if (treeWrapper) {
			treeWrapper.setContent(response);

			var treeContainer = A.one('#' + treeContainerId);

			var processTreeTask = A.debounce(
				function() {
					treeViewInstance = treeContainer.getData('treeInstance');

					if (treeViewInstance) {
						if (publicPages) {
							treeViewPublic = treeViewInstance;
						}
						else {
							treeViewPrivate = treeViewInstance;
						}

						bindTreeUI(treeViewInstance);

						treeContainer.swallowEvent('click', true);

						setSelectedPage(treeViewInstance.get('lastSelected'));
					}
					else {
						processTreeTask();
					}
				},
				100
			);

			processTreeTask();
		}
	};

	var setSelectedPage = function(lastSelectedNode) {
		var disabled = true;

		var messageText = '<%= UnicodeLanguageUtil.get(pageContext, "there-is-no-selected-page") %>';
		var messageType = 'alert';

		if (lastSelectedNode) {
			var labelEl = lastSelectedNode.get('labelEl');

			var link = labelEl.one('a');

			var text = getChosenPagePath(lastSelectedNode);

			if (link && !link.hasClass('layout-page-invalid')) {
				disabled = false;

				messageText = text;
				messageType = 'info';
			}
			else if (text) {
				messageText = Lang.sub('<%= UnicodeLanguageUtil.get(pageContext, "x-is-not-a-content-display-page") %>', ['"' + text + '"']);
			}
		}
	};
</aui:script>