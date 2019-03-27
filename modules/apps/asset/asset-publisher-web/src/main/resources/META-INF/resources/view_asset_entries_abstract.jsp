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
AssetEntryResult assetEntryResult = (AssetEntryResult)request.getAttribute("view.jsp-assetEntryResult");

for (AssetEntry assetEntry : assetEntryResult.getAssetEntries()) {
	AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassNameId(assetEntry.getClassNameId());

	if (assetRendererFactory == null) {
		continue;
	}

	AssetRenderer<?> assetRenderer = null;

	try {
		assetRenderer = assetRendererFactory.getAssetRenderer(assetEntry.getClassPK());
	}
	catch (Exception e) {
		if (_log.isWarnEnabled()) {
			_log.warn(e, e);
		}
	}

	if ((assetRenderer == null) || !assetRenderer.isDisplayable()) {
		continue;
	}

	request.setAttribute("view.jsp-assetEntry", assetEntry);
	request.setAttribute("view.jsp-assetRenderer", assetRenderer);
	request.setAttribute("view.jsp-assetRendererFactory", assetRendererFactory);

	try {
		String viewURL = assetPublisherHelper.getAssetViewURL(liferayPortletRequest, liferayPortletResponse, assetRenderer, assetEntry, assetPublisherDisplayContext.isAssetLinkBehaviorViewInPortlet());
%>

		<div class="asset-abstract mb-5 <%= assetPublisherWebUtil.isDefaultAssetPublisher(layout, portletDisplay.getId(), assetPublisherDisplayContext.getPortletResource()) ? "default-asset-publisher" : StringPool.BLANK %>">
			<span class="asset-anchor lfr-asset-anchor" id="<%= assetEntry.getEntryId() %>"></span>

			<div class="mb-4">
				<h4 class="component-title">
					<c:choose>
						<c:when test="<%= assetPublisherDisplayContext.isShowContextLink() %>">
							<a class="asset-title d-inline" href="<%= viewURL %>">
								<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>
							</a>
						</c:when>
						<c:otherwise>
							<span class="asset-title d-inline-flex">
								<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>
							</span>
						</c:otherwise>
					</c:choose>

					<span class="d-inline-flex">
						<liferay-util:include page="/asset_actions.jsp" servletContext="<%= application %>" />
					</span>
				</h4>
			</div>

			<liferay-util:include page="/view_asset_entry_author.jsp" servletContext="<%= application %>" />

			<div class="asset-content mb-4">
				<liferay-asset:asset-display
					abstractLength="<%= assetPublisherDisplayContext.getAbstractLength() %>"
					assetEntry="<%= assetEntry %>"
					assetRenderer="<%= assetRenderer %>"
					assetRendererFactory="<%= assetRendererFactory %>"
					template="<%= AssetRenderer.TEMPLATE_ABSTRACT %>"
					viewURL="<%= viewURL %>"
				/>
			</div>

			<liferay-util:include page="/view_asset_entry_metadata.jsp" servletContext="<%= application %>" />
		</div>

<%
	}
	catch (Exception e) {
		_log.error(e.getMessage());
	}
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_asset_publisher_web.view_asset_entries_abstract_jsp");
%>