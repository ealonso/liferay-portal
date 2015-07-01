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

<%@ include file="/html/taglib/portlet/icon_options/init.jsp" %>

<ul class="dropdown-menu dropdown-menu-right">
	<%
	List<PortletConfigurationIconFactory> portletConfigurationIconFactories = ListUtil.copy(PortletConfigurationIconTracker.getPortletConfigurationIcons());

	portletConfigurationIconFactories = ListUtil.sort(portletConfigurationIconFactories, new PropertyComparator("weight", false, false));

	for (PortletConfigurationIconFactory portletConfigurationIconFactory : portletConfigurationIconFactories) {
		PortletConfigurationIcon portletConfigurationIcon = portletConfigurationIconFactory.create(request);
	%>

		<c:if test="<%= portletConfigurationIcon.isShow() %>">
			<li><a href="<%= portletConfigurationIcon.getURL() %>"><%= portletConfigurationIcon.getMessage() %></a></li>
		</c:if>
	<%
	}
	%>
</ul>