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

<%@ include file="/render_fragment_layout/init.jsp" %>

<%
Map<String, Object> fieldValues = (Map<String, Object>)request.getAttribute("liferay-layout:render-fragment-layout:fieldValues");
LayoutStructure layoutStructure = (LayoutStructure)request.getAttribute("liferay-layout:render-fragment-layout:layoutStructure");
String mode = (String)request.getAttribute("liferay-layout:render-fragment-layout:mode");
long previewClassNameId = (long)request.getAttribute("liferay-layout:render-fragment-layout:previewClassNameId");
long previewClassPK = (long)request.getAttribute("liferay-layout:render-fragment-layout:previewClassPK");
int previewType = (int)request.getAttribute("liferay-layout:render-fragment-layout:previewType");
long[] segmentsExperienceIds = (long[])request.getAttribute("liferay-layout:render-fragment-layout:segmentsExperienceIds");

RenderFragmentLayoutDisplayContext renderFragmentLayoutDisplayContext = (RenderFragmentLayoutDisplayContext)request.getAttribute("render_layout_data.jsp-renderFragmentLayoutDisplayContext");

String itemId = (String)request.getAttribute("render_layout_data.jsp-itemId");

LayoutStructureItem layoutStructureItem = layoutStructure.getLayoutStructureItem(itemId);

for (String childLayoutStructureItemId : layoutStructureItem.getChildrenItemIds()) {
	LayoutStructureItem childLayoutStructureItem = layoutStructure.getLayoutStructureItem(childLayoutStructureItemId);

	JSONObject childLayoutStructureItemConfigJSONObject = childLayoutStructureItem.getItemConfigJSONObject();
%>

	<c:choose>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_COLUMN) %>">

			<%
			String size = childLayoutStructureItemConfigJSONObject.getString("size");
			%>

			<div class="<%= Validator.isNotNull(size) ? "col-md-" + size : StringPool.BLANK %>">

				<%
				request.setAttribute("render_layout_data.jsp-itemId", childLayoutStructureItemId);
				%>

				<liferay-util:include page="/render_fragment_layout/render_layout_data.jsp" servletContext="<%= application %>" />
			</div>
		</c:when>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_CONTAINER) %>">

			<%
			String backgroundColorCssClass = childLayoutStructureItemConfigJSONObject.getString("backgroundColorCssClass");
			String backgroundImage = renderFragmentLayoutDisplayContext.getBackgroundImage(childLayoutStructureItemConfigJSONObject);
			String containerType = childLayoutStructureItemConfigJSONObject.getString("containerType", "fluid");
			long paddingBottom = childLayoutStructureItemConfigJSONObject.getLong("paddingBottom", 3);
			long paddingHorizontal = childLayoutStructureItemConfigJSONObject.getLong("paddingHorizontal", 3);
			long paddingTop = childLayoutStructureItemConfigJSONObject.getLong("paddingTop", 3);

			StringBundler cssClasses = new StringBundler();

			if (Validator.isNotNull(backgroundColorCssClass)) {
				cssClasses.append("bg-");
				cssClasses.append(backgroundColorCssClass);
			}

			if (Objects.equals(containerType, "fluid")) {
				cssClasses.append(" container-fluid");
			}
			else {
				cssClasses.append(" container");
			}

			if (paddingBottom != -1L) {
				cssClasses.append(" pb-");
				cssClasses.append(paddingBottom);
			}

			if (paddingHorizontal != -1L) {
				cssClasses.append(" px-");
				cssClasses.append(paddingHorizontal);
			}

			if (paddingTop != -1L) {
				cssClasses.append(" pt-");
				cssClasses.append(paddingTop);
			}
			%>

			<div class="<%= cssClasses.toString() %>" style="<%= Validator.isNotNull(backgroundImage) ? "background-image: url(" + backgroundImage + "); background-position: 50% 50%; background-repeat: no-repeat; background-size: cover;" : "" %>">

				<%
				request.setAttribute("render_layout_data.jsp-itemId", childLayoutStructureItemId);
				%>

				<liferay-util:include page="/render_fragment_layout/render_layout_data.jsp" servletContext="<%= application %>" />
			</div>
		</c:when>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_FRAGMENT) %>">

			<%
			long fragmentEntryLinkId = childLayoutStructureItemConfigJSONObject.getLong("fragmentEntryLinkId");

			if (fragmentEntryLinkId > 0) {
				FragmentEntryLink fragmentEntryLink = FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(fragmentEntryLinkId);

				if (fragmentEntryLink != null) {
					FragmentRendererController fragmentRendererController = (FragmentRendererController)request.getAttribute(FragmentActionKeys.FRAGMENT_RENDERER_CONTROLLER);

					DefaultFragmentRendererContext defaultFragmentRendererContext = new DefaultFragmentRendererContext(fragmentEntryLink);

					defaultFragmentRendererContext.setFieldValues(fieldValues);
					defaultFragmentRendererContext.setLocale(locale);
					defaultFragmentRendererContext.setMode(mode);
					defaultFragmentRendererContext.setPreviewClassNameId(previewClassNameId);
					defaultFragmentRendererContext.setPreviewClassPK(previewClassPK);
					defaultFragmentRendererContext.setPreviewType(previewType);
					defaultFragmentRendererContext.setSegmentsExperienceIds(segmentsExperienceIds);
			%>

					<%= fragmentRendererController.render(defaultFragmentRendererContext, request, response) %>

			<%
				}
			}
			%>

		</c:when>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_ROW) %>">

			<%
			boolean gutters = childLayoutStructureItemConfigJSONObject.getBoolean("gutters", true);
			%>

			<div class="row <%= !gutters ? "no-gutters" : StringPool.BLANK %>">

				<%
				request.setAttribute("render_layout_data.jsp-itemId", childLayoutStructureItemId);
				%>

				<liferay-util:include page="/render_fragment_layout/render_layout_data.jsp" servletContext="<%= application %>" />
			</div>
		</c:when>
	</c:choose>

<%
}
%>