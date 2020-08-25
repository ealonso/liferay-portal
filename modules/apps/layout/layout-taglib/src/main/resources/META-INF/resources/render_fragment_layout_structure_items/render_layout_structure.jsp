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

<%@ include file="/render_fragment_layout_structure_items/init.jsp" %>

<%
RenderFragmentLayoutStructureItemsDisplayContext renderFragmentLayoutStructureItemsDisplayContext = (RenderFragmentLayoutStructureItemsDisplayContext)request.getAttribute(RenderFragmentLayoutStructureItemsDisplayContext.class.getName());

LayoutStructure layoutStructure = renderFragmentLayoutStructureItemsDisplayContext.getLayoutStructure();

List<String> childrenItemIds = (List<String>)request.getAttribute("render_fragment_layout_structure_items.jsp-childrenItemIds");

for (String childrenItemId : childrenItemIds) {
	LayoutStructureItem layoutStructureItem = layoutStructure.getLayoutStructureItem(childrenItemId);

	request.setAttribute("render_fragment_layout_structure_items.jsp-childrenItemIds", layoutStructureItem.getChildrenItemIds());
%>

	<c:choose>
		<c:when test="<%= layoutStructureItem instanceof CollectionStyledLayoutStructureItem %>">

			<%
			CollectionStyledLayoutStructureItem collectionStyledLayoutStructureItem = (CollectionStyledLayoutStructureItem)layoutStructureItem;

			InfoListRenderer<Object> infoListRenderer = (InfoListRenderer<Object>)renderFragmentLayoutStructureItemsDisplayContext.getInfoListRenderer(collectionStyledLayoutStructureItem);
			%>

			<div class="<%= renderFragmentLayoutStructureItemsDisplayContext.getCssClass(collectionStyledLayoutStructureItem) %>" style="<%= renderFragmentLayoutStructureItemsDisplayContext.getStyle(collectionStyledLayoutStructureItem) %>">
				<c:choose>
					<c:when test="<%= infoListRenderer != null %>">

						<%
						infoListRenderer.render(renderFragmentLayoutStructureItemsDisplayContext.getCollection(collectionStyledLayoutStructureItem), renderFragmentLayoutStructureItemsDisplayContext.getInfoListRendererContext(collectionStyledLayoutStructureItem.getListItemStyle(), collectionStyledLayoutStructureItem.getTemplateKey()));
						%>

					</c:when>
					<c:otherwise>
						<clay:row>

							<%
							LayoutDisplayPageProvider<?> currentLayoutDisplayPageProvider = (LayoutDisplayPageProvider<?>)request.getAttribute(LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_PROVIDER);

							try {
								request.setAttribute(LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_PROVIDER, renderFragmentLayoutStructureItemsDisplayContext.getCollectionLayoutDisplayPageProvider(collectionStyledLayoutStructureItem));

								for (Object collectionObject : renderFragmentLayoutStructureItemsDisplayContext.getCollection(collectionStyledLayoutStructureItem)) {
									request.setAttribute(InfoDisplayWebKeys.INFO_LIST_DISPLAY_OBJECT, collectionObject);
							%>

									<clay:col
										md="<%= String.valueOf(12 / collectionStyledLayoutStructureItem.getNumberOfColumns()) %>"
									>
										<liferay-util:include page="/render_fragment_layout_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />
									</clay:col>

							<%
								}
							}
							finally {
								request.removeAttribute(InfoDisplayWebKeys.INFO_LIST_DISPLAY_OBJECT);

								request.setAttribute(LayoutDisplayPageWebKeys.LAYOUT_DISPLAY_PAGE_PROVIDER, currentLayoutDisplayPageProvider);
							}
							%>

						</clay:row>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:when test="<%= layoutStructureItem instanceof ColumnLayoutStructureItem %>">

			<%
			ColumnLayoutStructureItem columnLayoutStructureItem = (ColumnLayoutStructureItem)layoutStructureItem;
			%>

			<clay:col
				cssClass="<%= ResponsiveLayoutStructureUtil.getColumnCssClass(columnLayoutStructureItem) %>"
			>
				<liferay-util:include page="/render_fragment_layout_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />
			</clay:col>
		</c:when>
		<c:when test="<%= layoutStructureItem instanceof ContainerStyledLayoutStructureItem %>">

			<%
			ContainerStyledLayoutStructureItem containerStyledLayoutStructureItem = (ContainerStyledLayoutStructureItem)layoutStructureItem;

			String containerLinkHref = renderFragmentLayoutStructureItemsDisplayContext.getContainerLinkHref(containerStyledLayoutStructureItem, request.getAttribute(InfoDisplayWebKeys.INFO_LIST_DISPLAY_OBJECT));
			%>

			<div class="<%= renderFragmentLayoutStructureItemsDisplayContext.getCssClass(containerStyledLayoutStructureItem) %>" style="<%= renderFragmentLayoutStructureItemsDisplayContext.getStyle(containerStyledLayoutStructureItem) %>">
				<c:if test="<%= Validator.isNotNull(containerLinkHref) %>">
					<a href="<%= containerLinkHref %>" style="color: inherit; text-decoration: none;" target="<%= renderFragmentLayoutStructureItemsDisplayContext.getContainerLinkTarget(containerStyledLayoutStructureItem) %>">
				</c:if>

				<liferay-util:include page="/render_fragment_layout_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />

				<c:if test="<%= Validator.isNotNull(containerLinkHref) %>">
					</a>
				</c:if>
			</div>
		</c:when>
		<c:when test="<%= layoutStructureItem instanceof FragmentStyledLayoutStructureItem %>">

			<%
			FragmentStyledLayoutStructureItem fragmentStyledLayoutStructureItem = (FragmentStyledLayoutStructureItem)layoutStructureItem;

			if (fragmentStyledLayoutStructureItem.getFragmentEntryLinkId() <= 0) {
				continue;
			}

			FragmentEntryLink fragmentEntryLink = FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(fragmentStyledLayoutStructureItem.getFragmentEntryLinkId());

			if (fragmentEntryLink == null) {
				continue;
			}

			FragmentRendererController fragmentRendererController = (FragmentRendererController)request.getAttribute(FragmentActionKeys.FRAGMENT_RENDERER_CONTROLLER);

			DefaultFragmentRendererContext defaultFragmentRendererContext = renderFragmentLayoutStructureItemsDisplayContext.getDefaultFragmentRendererContext(fragmentEntryLink, fragmentStyledLayoutStructureItem.getItemId());
			%>

			<div class="<%= renderFragmentLayoutStructureItemsDisplayContext.getCssClass(fragmentStyledLayoutStructureItem) %>" style="<%= renderFragmentLayoutStructureItemsDisplayContext.getStyle(fragmentStyledLayoutStructureItem) %>">
				<%= fragmentRendererController.render(defaultFragmentRendererContext, request, response) %>
			</div>
		</c:when>
		<c:when test="<%= layoutStructureItem instanceof RowStyledLayoutStructureItem %>">

			<%
			RowStyledLayoutStructureItem rowStyledLayoutStructureItem = (RowStyledLayoutStructureItem)layoutStructureItem;

			LayoutStructureItem parentLayoutStructureItem = layoutStructure.getLayoutStructureItem(rowStyledLayoutStructureItem.getParentItemId());

			boolean includeContainer = false;

			if (parentLayoutStructureItem instanceof RootLayoutStructureItem) {
				LayoutStructureItem rootParentLayoutStructureItem = layoutStructure.getLayoutStructureItem(parentLayoutStructureItem.getParentItemId());

				if (rootParentLayoutStructureItem == null) {
					includeContainer = true;
				}
				else if (rootParentLayoutStructureItem instanceof DropZoneLayoutStructureItem) {
					LayoutStructureItem dropZoneParentLayoutStructureItem = layoutStructure.getLayoutStructureItem(rootParentLayoutStructureItem.getParentItemId());

					if (dropZoneParentLayoutStructureItem instanceof RootLayoutStructureItem) {
						includeContainer = true;
					}
				}
			}
			%>

			<div class="<%= renderFragmentLayoutStructureItemsDisplayContext.getCssClass(rowStyledLayoutStructureItem) %>" style="<%= renderFragmentLayoutStructureItemsDisplayContext.getStyle(rowStyledLayoutStructureItem) %>">
				<c:choose>
					<c:when test="<%= includeContainer %>">
						<clay:container
							cssClass="overflow-hidden p-0"
							fluid="<%= true %>"
						>
							<clay:row
								cssClass="<%= ResponsiveLayoutStructureUtil.getRowCssClass(rowStyledLayoutStructureItem) %>"
							>
								<liferay-util:include page="/render_fragment_layout_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />
							</clay:row>
						</clay:container>
					</c:when>
					<c:otherwise>
						<clay:row
							cssClass="<%= ResponsiveLayoutStructureUtil.getRowCssClass(rowStyledLayoutStructureItem) %>"
						>
							<liferay-util:include page="/render_fragment_layout_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />
						</clay:row>
					</c:otherwise>
				</c:choose>
			</div>
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/render_fragment_layout_structure_items_structure_items/render_layout_structure.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>

<%
}
%>