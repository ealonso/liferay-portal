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
AssetDisplayTemplate assetDisplayTemplate = (AssetDisplayTemplate)request.getAttribute(AssetDisplayLayoutTypeControllerWebKeys.ASSET_DISPLAY_TEMPLATE);

Map<String, Object> contextObjects = (Map<String, Object>)request.getAttribute(AssetDisplayLayoutTypeControllerWebKeys.CONTEXT_OBJECTS);

DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute(WebKeys.TEMPLATE);

List<AssetEntry> entries = (List<AssetEntry>)request.getAttribute(AssetDisplayLayoutTypeControllerWebKeys.ENTRIES);
%>

<c:choose>
	<c:when test="<%= ddmTemplate != null %>">

		<%
		String displayStyle = "ddmTemplate_" + ddmTemplate.getTemplateKey();
		%>

		<liferay-ddm:template-renderer
			className="<%= AssetDisplayTemplate.class.getName() %>"
			contextObjects="<%= contextObjects %>"
			displayStyle="<%= displayStyle %>"
			displayStyleGroupId="<%= ddmTemplate.getGroupId() %>"
			entries="<%= entries %>"
		/>
	</c:when>
	<c:otherwise>
		<div class="alert alert-warning">
			<liferay-ui:message arguments="<%= assetDisplayTemplate.getAssetTypeName(locale) %>" key="there-is-no-appropriate-template-for-asset-type-x" />
		</div>
	</c:otherwise>
</c:choose>