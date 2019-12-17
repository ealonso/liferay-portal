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

<%@ include file="/dynamic_include/init.jsp" %>

<div class="control-menu-level-0"></div>

<div class="change-tracking-indicator">
	<a class="change-tracking-indicator-link" href="<%= changeTrackingIndicatorDisplayContext.getChangeTrackingURL() %>">
		<svg class="<%= changeTrackingIndicatorDisplayContext.getIconClass() %> lexicon-icon" focusable="false" role="presentation">
			<use xlink:href="<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg#<%= changeTrackingIndicatorDisplayContext.getIconName() %>"></use>
		</svg>

		<span><%= changeTrackingIndicatorDisplayContext.getTitle() %></span>
	</a>
</div>