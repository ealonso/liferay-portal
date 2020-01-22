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
String itemId = (String)request.getAttribute("render_layout_data.jsp-itemId");
PortletLayoutDisplayContext portletLayoutDisplayContext = (PortletLayoutDisplayContext)request.getAttribute("render_layout_data.jsp-portletLayoutDisplayContext");

LayoutStructure layoutStructure = portletLayoutDisplayContext.getLayoutStructure();

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

				<liferay-util:include page="/layout/view/render_layout_data.jsp" servletContext="<%= application %>" />
			</div>
		</c:when>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_CONTAINER) %>">

			<%
			String backgroundColorCssClass = childLayoutStructureItemConfigJSONObject.getString("backgroundColorCssClass");
			String backgroundImage = portletLayoutDisplayContext.getBackgroundImage(childLayoutStructureItemConfigJSONObject);
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

				<liferay-util:include page="/layout/view/render_layout_data.jsp" servletContext="<%= application %>" />
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

					defaultFragmentRendererContext.setLocale(locale);
			%>

					<%= fragmentRendererController.render(defaultFragmentRendererContext, request, response) %>

			<%
				}
			}
			%>

		</c:when>
		<c:when test="<%= Objects.equals(childLayoutStructureItem.getItemType(), LayoutDataItemTypeConstants.TYPE_ROW) %>">

			<%
			String themeId = theme.getThemeId();

			String layoutTemplateId = layoutTypePortlet.getLayoutTemplateId();

			if (Validator.isNull(layoutTemplateId)) {
				layoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
			}

			LayoutTemplate layoutTemplate = LayoutTemplateLocalServiceUtil.getLayoutTemplate(layoutTemplateId, false, theme.getThemeId());

			if (layoutTemplate != null) {
				themeId = layoutTemplate.getThemeId();
			}

			String templateId = themeId + LayoutTemplateConstants.CUSTOM_SEPARATOR + layoutTypePortlet.getLayoutTemplateId();
			String templateContent = LayoutTemplateLocalServiceUtil.getContent(layoutTypePortlet.getLayoutTemplateId(), false, theme.getThemeId());
			String langType = LayoutTemplateLocalServiceUtil.getLangType(layoutTypePortlet.getLayoutTemplateId(), false, theme.getThemeId());

			if (Validator.isNotNull(templateContent)) {
				RuntimePageUtil.processTemplate(request, response, new StringTemplateResource(templateId, templateContent), langType);
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

				<liferay-util:include page="/layout/view/render_layout_data.jsp" servletContext="<%= application %>" />
			</div>
		</c:when>
	</c:choose>

<%
}
%>