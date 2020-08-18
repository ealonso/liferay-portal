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
List<NavigationStep> navigationSteps = (List<NavigationStep>)request.getAttribute("multi_step_nav_small.jsp-navigationSteps");
%>

<ol class="multi-step-indicator-label-top multi-step-nav multi-step-nav-collapse-sm">

	<%
	boolean activeReached = false;

	for (int i = 0; i < navigationSteps.size(); i++) {
		NavigationStep navigationStep = navigationSteps.get(i);

		if (navigationStep.isActive()) {
			activeReached = true;
		}
	%>

		<li class="<%= navigationStep.isActive() ? "active" : StringPool.BLANK %> <%= activeReached ? StringPool.BLANK : "multi-step-item-done" %> multi-step-item multi-step-item-expand">
			<c:choose>
				<c:when test="<%= i != (navigationSteps.size() - 1) %>">
					<div class="multi-step-divider"></div>
				</c:when>
			</c:choose>

			<div class="multi-step-indicator">
				<div class="multi-step-indicator-label"><%= navigationStep.getLabel() %></div>

				<a class="multi-step-icon" href="<%= navigationStep.getUrl() %>"></a>
			</div>
		</li>

	<%
	}
	%>

</ol>