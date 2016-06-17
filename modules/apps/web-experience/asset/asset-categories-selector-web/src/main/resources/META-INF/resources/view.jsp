<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
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

long vocabularyId = ParamUtil.getLong(request, "vocabularyID");
	System.out.println(vocabularyId);
%>

<div id="list_categories" class="lfr-tags-selector-list lfr-categories-selector-list">
</div>

<liferay-portlet:resourceURL id="getCategories" var="resourceURL">
	<portlet:param name="vocabularyId" value="33106" />
</liferay-portlet:resourceURL>
<script>
	debugger;
	AUI().use(
			'aui-tree-view',
			function(A) {
				new A.TreeView(
						{
							boundingBox: '#list_categories',
							children: [
								{
									children: [
										{
											hasChildren: false,
											name: "Voc1",
											parentCategoryId: "0",
											titleCurrentValue: "Voc1",
											categoryId: "33113",
											childrenCount: 0,
											leaf: true,
											type: 'check',
											label: "Voc1"
										},
										{
											hasChildren: false,
											name: "Voc2",
											parentCategoryId: "0",
											titleCurrentValue: "Voc2",
											categoryId: "33114",
											childrenCount: 0,
											leaf: true,
											type: 'check',
											label: "Voc2"
										}
									],
									expanded: true,
									alwaysShowHitArea: true,
									id: "vocabulary33106",
									label: "Vocabulary2 (Liferay)",
									leaf: false,
									paginator: {
										offsetParam: "start",
										limit: 50,
										moreResultsLabel: "Load More Results",
										total: 2
									}
								}
							]
						}
				).render();
			}
	);
</script>

