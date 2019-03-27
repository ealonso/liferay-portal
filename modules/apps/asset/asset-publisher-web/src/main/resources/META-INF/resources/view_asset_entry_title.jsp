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
AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute("view.jsp-assetRenderer");

boolean print = GetterUtil.getBoolean(request.getAttribute("view.jsp-print"));

String redirect = (String)request.getAttribute("view.jsp-redirect");

String viewURL = (String)request.getAttribute("view.jsp-viewURL");
%>

<span class="asset-anchor lfr-asset-anchor" id="<%= assetEntry.getEntryId() %>"></span>

<c:if test="<%= assetPublisherDisplayContext.isShowAssetTitle() %>">
	<div class="mb-4">
		<h4 class="component-title">
			<c:if test="<%= Validator.isNotNull(redirect) %>">
				<liferay-ui:icon
					cssClass="header-back-to"
					icon="angle-left"
					markupView="lexicon"
					url="<%= redirect %>"
				/>
			</c:if>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(viewURL) && assetPublisherDisplayContext.isShowContextLink() %>">
					<a class="asset-title d-inline" href="<%= viewURL %>">
						<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>
					</a>
				</c:when>
				<c:otherwise>
					<span class="asset-title d-inline">
						<%= HtmlUtil.escape(assetRenderer.getTitle(locale)) %>
					</span>
				</c:otherwise>
			</c:choose>

			<c:if test="<%= !print %>">
				<span class="d-inline-flex">
					<liferay-util:include page="/asset_actions.jsp" servletContext="<%= application %>" />
				</span>
			</c:if>
		</h4>
	</div>
</c:if>