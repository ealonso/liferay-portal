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

<%@ include file="/change_lists/init.jsp" %>

<%
SelectChangeListDisplayContext selectChangeListDisplayContext = new SelectChangeListDisplayContext(request, renderRequest, renderResponse);
%>

<clay:management-toolbar
	clearResultsURL="<%= selectChangeListDisplayContext.getClearResultsURL() %>"
	filterDropdownItems="<%= selectChangeListDisplayContext.getFilterDropdownItems() %>"
	itemsTotal="<%= selectChangeListDisplayContext.getItemsTotal() %>"
	searchActionURL="<%= selectChangeListDisplayContext.getSearchActionURL() %>"
	searchContainerId="<%= selectChangeListDisplayContext.getSearchContainerId() %>"
	sortingOrder="<%= selectChangeListDisplayContext.getOrderByType() %>"
	sortingURL="<%= selectChangeListDisplayContext.getSortingURL() %>"
/>

<c:if test="<%= selectChangeListDisplayContext.showEmptyResultsMessage() %>">
	<div class="contact-information-empty-results-message-wrapper">
		<liferay-ui:empty-result-message
			message="<%= selectChangeListDisplayContext.getEmptyResultsMessage() %>"
		/>
	</div>
</c:if>

<div class="container container-fluid-1280" id="<portlet:namespace />selectChangeListContainer">
	<div class="table-responsive">
		<table class="select-change-list-table table table-autofit">
			<tbody>

				<%
				for (CTCollection ctCollection : selectChangeListDisplayContext.getResults()) {
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("ctcollectionid", ctCollection.getCtCollectionId());
				%>

					<tr>
						<td>
							<liferay-ui:user-portrait
								userId="<%= ctCollection.getUserId() %>"
							/>
						</td>
						<td class="table-cell-expand">
							<c:choose>
								<c:when test="<%= ctCollection.getCtCollectionId() == selectChangeListDisplayContext.getCtCollectionId() %>">
									<div class="change-list-name font-italic">
										<%= ctCollection.getName() %>
									</div>

									<div class="change-list-description font-italic">
										<%= ctCollection.getDescription() %>
									</div>
								</c:when>
								<c:otherwise>
									<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
										<div class="change-list-name">
											<%= ctCollection.getName() %>
										</div>

										<div class="change-list-description">
											<%= ctCollection.getDescription() %>
										</div>
									</aui:a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>

				<%
				}
				%>

			</tbody>
		</table>
	</div>

	<liferay-ui:search-paginator
		markupView="lexicon"
		searchContainer="<%= selectChangeListDisplayContext.getSearchContainer() %>"
	/>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler(
		'#<portlet:namespace />selectChangeListContainer',
		'<%= selectChangeListDisplayContext.getEventName() %>'
	);
</aui:script>