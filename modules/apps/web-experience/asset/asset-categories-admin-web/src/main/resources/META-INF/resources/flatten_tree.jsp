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
portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(assetCategoriesDisplayContext.getCategoriesRedirect(true));

AssetCategory category = assetCategoriesDisplayContext.getCategory();
AssetVocabulary vocabulary = assetCategoriesDisplayContext.getVocabulary();

String title = category != null ? category.getTitle(locale) : vocabulary.getTitle(locale);

renderResponse.setTitle(title);

AssetCategoryUtil.addPortletBreadcrumbEntry(vocabulary, category, request, renderResponse);
%>

<div class="container-fluid-1280">
	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showParentGroups="<%= false %>"
	/>

	<liferay-ui:search-container
		id="assetCategories"
		iteratorURL="<%= currentURLObj %>"
		searchContainer="<%= assetCategoriesDisplayContext.getCategoriesTreeSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.asset.kernel.model.AssetCategory"
			keyProperty="categoryId"
			modelVar="curCategory"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/flatten_tree.jsp" />
				<portlet:param name="categoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
				<portlet:param name="vocabularyId" value="<%= String.valueOf(curCategory.getVocabularyId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				href="<%= rowURL %>"
				name="path"
				value="<%= HtmlUtil.escape(curCategory.getPath(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="category"
				value="<%= HtmlUtil.escape(curCategory.getTitle(locale)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="create-date"
				property="createDate"
			/>

			<liferay-ui:search-container-column-jsp
				path="/category_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= assetCategoriesDisplayContext.getDisplayStyle() %>" markupView="lexicon" />
	</liferay-ui:search-container>
</div>