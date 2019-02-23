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

<%@ include file="/select_asset_display_page/init.jsp" %>

<aui:input id="pagesContainerInput" ignoreRequestValue="<%= true %>" name="layoutUuid" type="hidden" value="<%= selectAssetDisplayPageDisplayContext.getLayoutUuid() %>" />

<aui:input id="assetDisplayPageIdInput" ignoreRequestValue="<%= true %>" name="assetDisplayPageId" type="hidden" value="<%= selectAssetDisplayPageDisplayContext.getAssetDisplayPageId() %>" />

<liferay-frontend:fieldset
	id='<%= renderResponse.getNamespace() + "eventsContainer" %>'
>
	<aui:select label="" name="displayPageType" value="<%= selectAssetDisplayPageDisplayContext.getAssetDisplayPageType() %>">
		<aui:option label="default-display-page" value="<%= AssetDisplayPageConstants.TYPE_DEFAULT %>" />
		<aui:option label="specific-display-page" value="<%= AssetDisplayPageConstants.TYPE_SPECIFIC %>" />
		<aui:option label="no-display-page" value="<%= AssetDisplayPageConstants.TYPE_NONE %>" />
	</aui:select>

	<div class="<%= !selectAssetDisplayPageDisplayContext.isAssetDisplayPageTypeNone() ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />displayPageNameContainer">

		<%
		String displayPageName = selectAssetDisplayPageDisplayContext.getAssetDisplayPageName();

		if (Validator.isNull(displayPageName) && selectAssetDisplayPageDisplayContext.isAssetDisplayPageTypeDefault()) {
			displayPageName = LanguageUtil.get(resourceBundle, "no-default-display-page");
		}
		else if (Validator.isNull(displayPageName) && selectAssetDisplayPageDisplayContext.isAssetDisplayPageTypeSpecific()) {
			displayPageName = LanguageUtil.get(resourceBundle, "no-display-page-selected");
		}
		%>

		<aui:input disabled="<%= true %>" label="" name="displayPageNameInput" value="<%= displayPageName %>" />
	</div>

	<div class="<%= selectAssetDisplayPageDisplayContext.isAssetDisplayPageTypeSpecific() ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />displayPageContainer">
		<aui:button cssClass='<%= selectAssetDisplayPageDisplayContext.isAssetDisplayPageTypeSpecific() ? StringPool.BLANK : "hide" %>' name="chooseDisplayPage" value="choose" />

		<c:if test="<%= selectAssetDisplayPageDisplayContext.isURLViewInContext() %>">

			<%
			Layout defaultDisplayLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(selectAssetDisplayPageDisplayContext.getLayoutUuid(), themeDisplay.getScopeGroupId(), false);

			if (defaultDisplayLayout == null) {
				defaultDisplayLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(selectAssetDisplayPageDisplayContext.getLayoutUuid(), themeDisplay.getScopeGroupId(), true);
			}
			%>

			<c:if test="<%= selectAssetDisplayPageDisplayContext.isShowViewInContextLink() %>">
				<aui:a href="<%= selectAssetDisplayPageDisplayContext.getURLViewInContext() %>" target="blank">
					<liferay-ui:message arguments="<%= HtmlUtil.escape(defaultDisplayLayout.getName(locale)) %>" key="view-content-in-x" translateArguments="<%= false %>" />
				</aui:a>
			</c:if>
		</c:if>
	</div>
</liferay-frontend:fieldset>

<aui:script use="liferay-item-selector-dialog">
	var assetDisplayPageIdInput = document.getElementById('<portlet:namespace />assetDisplayPageIdInput');
	var chooseDisplayPageButton = document.getElementById('<portlet:namespace />chooseDisplayPage');
	var displayPageNameInput = document.getElementById('<portlet:namespace />displayPageNameInput');
	var pagesContainerInput = document.getElementById('<portlet:namespace />pagesContainerInput');

	if (assetDisplayPageIdInput && chooseDisplayPageButton && displayPageItemRemove && displayPageNameInput && pagesContainerInput) {
		chooseDisplayPageButton.addEventListener(
			'click',
			function(event) {
				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: '<%= selectAssetDisplayPageDisplayContext.getEventName() %>',
						on: {
							selectedItemChange: function(event) {
								var selectedItem = event.newVal;

								assetDisplayPageIdInput.value = '';

								pagesContainerInput.value = '';

								if (selectedItem) {
									if (selectedItem.type === 'asset-display-page') {
										assetDisplayPageIdInput.value = selectedItem.id;
									}
									else {
										pagesContainerInput.value = selectedItem.id;
									}

									displayPageNameInput.value = selectedItem.name;
								}
							}
						},
						'strings.add': '<liferay-ui:message key="done" />',
						title: '<liferay-ui:message key="select-page" />',
						url: '<%= selectAssetDisplayPageDisplayContext.getAssetDisplayPageItemSelectorURL() %>'
					}
				);

				itemSelectorDialog.open();
			}
		);
	}

	var displayPageContainer = document.getElementById('<portlet:namespace />displayPageContainer');
	var displayPageNameContainer = document.getElementById('<portlet:namespace />displayPageNameContainer');
	var eventsContainer = document.getElementById('<portlet:namespace />eventsContainer');

	if (displayPageContainer && eventsContainer) {
		eventsContainer.addEventListener(
			'change',
			function(event) {
				var target = event.target;

				<%
				String defaultDisplayPageName = selectAssetDisplayPageDisplayContext.getDefaultAssetDisplayPageName();

				if (Validator.isNull(defaultDisplayPageName)) {
					defaultDisplayPageName = LanguageUtil.get(resourceBundle, "no-default-display-page");
				}
				%>

				var defaultDisplayPageName = '<%= defaultDisplayPageName %>';

				if (target && (target.value === '<%= AssetDisplayPageConstants.TYPE_SPECIFIC %>' || target.value === '<%= AssetDisplayPageConstants.TYPE_DEFAULT %>')) {
					displayPageNameContainer.classList.remove('hide');

					if (target.value === '<%= AssetDisplayPageConstants.TYPE_DEFAULT %>') {
						assetDisplayPageIdInput.value = '<%= selectAssetDisplayPageDisplayContext.getDefaultAssetDisplayPageId() %>';
						displayPageNameInput.value = defaultDisplayPageName;
					}
					else {
						displayPageContainer.classList.remove('hide');

						assetDisplayPageIdInput.value = '0';
						displayPageNameInput.value = '<%= LanguageUtil.get(resourceBundle, "no-display-page-selected") %>';
					}
				}
				else {
					displayPageContainer.classList.add('hide');
					displayPageNameContainer.classList.add('hide');
				}
			}
		);
	}
</aui:script>