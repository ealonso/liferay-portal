<%--
/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="align-items-center d-flex">
	<c:choose>
		<c:when test="<%= FaroNavigationUtil.isLDPEnabled(themeDisplay.getScopeGroupId()) %>">
			<img alt="" class="mr-3" height="20" src="<%= PortalUtil.getStaticResourceURL(request, "/o/osb-faro-web/images/ldp_logo.svg") %>" />

			<span class="d-none d-sm-block font-weight-semi-bold text-5 text-dark text-nowrap">
				<liferay-ui:message key="liferay-data-platform" />
			</span>
		</c:when>
		<c:otherwise>
			<img alt="" class="mr-3" height="20" src="<%= PortalUtil.getStaticResourceURL(request, "/o/osb-faro-web/images/ac_logo.svg") %>" />

			<span class="d-none d-sm-block font-weight-semi-bold text-5 text-dark text-nowrap">
				<liferay-ui:message key="analytics-cloud" />
			</span>
		</c:otherwise>
	</c:choose>
</div>