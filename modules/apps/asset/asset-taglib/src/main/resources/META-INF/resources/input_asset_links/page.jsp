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

<%@ include file="/input_asset_links/init.jsp" %>

<liferay-util:buffer
	var="removeLinkIcon"
>
	<liferay-ui:icon
		icon="times-circle"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	compactEmptyResultsMessage="<%= true %>"
	emptyResultsMessage="none"
	headerNames="title,null"
	total="<%= inputAssetLinksDisplayContext.getAssetLinksCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= inputAssetLinksDisplayContext.getAssetLinks() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.asset.kernel.model.AssetLink"
		keyProperty="entryId2"
		modelVar="assetLink"
	>

		<%
		AssetEntry assetLinkEntry = inputAssetLinksDisplayContext.getAssetLinkEntry(assetLink);
		%>

		<liferay-ui:search-container-column-text>
			<h5>
				<%= HtmlUtil.escape(assetLinkEntry.getTitle(locale)) %>
			</h5>

			<div class="text-secondary">
				<%= inputAssetLinksDisplayContext.getAssetType(assetLinkEntry) %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="text-right"
		>
			<a class="modify-link" data-rowId="<%= assetLinkEntry.getEntryId() %>" href="javascript:;"><%= removeLinkIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>

<c:if test="<%= stagingGroupHelper.isLiveGroup(themeDisplay.getScopeGroupId()) %>">
	<span>
		<liferay-ui:message key="related-assets-for-staged-asset-types-can-be-managed-on-the-staging-site" />
	</span>
</c:if>

<aui:button onClick='<%= renderResponse.getNamespace() + "selectAssetEntries();" %>' value="select" />

<aui:input name="assetLinkEntryIds" type="hidden" />

<aui:script use="aui-base,escape,liferay-item-selector-dialog,liferay-search-container">
	function <portlet:namespace />selectAssetEntries() {
		var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />assetLinksSearchContainer');

		var searchContainerData = searchContainer.getData();

		if (searchContainerData) {
			searchContainerData = searchContainerData.split(',');
		}
		else {
			searchContainerData = [];
		}

		var itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: '<%= inputAssetLinksDisplayContext.getEventName() %>',
				id: '<%= inputAssetLinksDisplayContext.getEventName() %>selectAssetEntries',
				on: {
					selectedItemChange: function(event) {
						var assetEntryIds = event.newVal;

						if (assetEntryIds) {
							assetEntryIds.forEach(
								function(assetEntry) {
									var entityId = assetEntry.entityid;

									if (searchContainerData.indexOf(entityId) == -1) {
										var entryLink = '<div class="text-right"><a class="modify-link" data-rowId="' + entityId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeLinkIcon) %></a></div>';

										var entryHtml = '<h5>' + A.Escape.html(assetEntry.assettitle) + '</h5><div class="text-secondary">' + A.Escape.html(assetEntry.assettype) + '</div>';

										searchContainer.addRow([entryHtml, entryLink], entityId);

										searchContainer.updateDataStore();
									}
								}
							);
						}
					}
				},
				selectedData: searchContainerData,
				title: '<liferay-ui:message key="select-asset-entries" />',
				url: '<%= inputAssetLinksDisplayContext.getAssetBrowserPortletURL() %>'
			}
		);

		itemSelectorDialog.open();
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />assetLinksSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>