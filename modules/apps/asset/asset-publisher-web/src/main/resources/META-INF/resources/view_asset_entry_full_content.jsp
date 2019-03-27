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
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	redirect = ParamUtil.getString(PortalUtil.getOriginalServletRequest(request), "redirect");
}

redirect = PortalUtil.escapeRedirect(redirect);

boolean showBackURL = GetterUtil.getBoolean(request.getAttribute("view.jsp-showBackURL"));

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/view.jsp");

	redirect = portletURL.toString();
}

AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRendererFactory<?> assetRendererFactory = (AssetRendererFactory<?>)request.getAttribute("view.jsp-assetRendererFactory");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");

assetPublisherDisplayContext.setLayoutAssetEntry(assetEntry);

assetEntry = assetPublisherDisplayContext.incrementViewCounter(assetEntry);

String fullContentRedirect = currentURL;

if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(assetEntry.getCompanyId(), assetEntry.getGroupId(), assetEntry.getClassName())) {
	fullContentRedirect = redirect;
}

request.setAttribute("view.jsp-fullContentRedirect", fullContentRedirect);

if (showBackURL) {
	request.setAttribute("view.jsp-redirect", redirect);
}
%>

<div class="asset-full-content clearfix mb-5 <%= assetPublisherDisplayContext.isDefaultAssetPublisher() ? "default-asset-publisher" : StringPool.BLANK %> <%= assetPublisherDisplayContext.isShowAssetTitle() ? "show-asset-title" : "no-title" %>">
	<liferay-util:include page="/view_asset_entry_title.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/view_asset_entry_author.jsp" servletContext="<%= application %>" />

	<div class="asset-content mb-4">
		<liferay-asset:asset-display
			assetEntry="<%= assetEntry %>"
			assetRenderer="<%= assetRenderer %>"
			assetRendererFactory="<%= assetRendererFactory %>"
			showExtraInfo="<%= assetPublisherDisplayContext.isShowExtraInfo() %>"
		/>
	</div>

	<liferay-util:include page="/view_asset_entry_metadata.jsp" servletContext="<%= application %>" />
</div>