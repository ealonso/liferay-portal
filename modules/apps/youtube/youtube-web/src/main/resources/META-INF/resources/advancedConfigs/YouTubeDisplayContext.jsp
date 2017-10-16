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
YouTubeDisplayContext youTubeDisplayContext = (YouTubeDisplayContext)displayContext;
%>

<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="advanced-options">
	<aui:input name="preferences--showThumbnail--" type="toggle-switch" value="<%= youTubeDisplayContext.isShowThumbnail() %>" />

	<div class="<%= youTubeDisplayContext.isShowThumbnail() ? "hide" : StringPool.BLANK %>" id="<portlet:namespace />videoPreferences">
		<aui:input inlineField="<%= true %>" label="auto-play" name="preferences--autoplay--" type="toggle-switch" value="<%= youTubeDisplayContext.isAutoPlay() %>" />

		<aui:input inlineField="<%= true %>" name="preferences--loop--" type="toggle-switch" value="<%= youTubeDisplayContext.isLoop() %>" />

		<aui:input inlineField="<%= true %>" name="preferences--enableKeyboardControls--" type="toggle-switch" value="<%= youTubeDisplayContext.isEnableKeyboardControls() %>" />

		<aui:input inlineField="<%= true %>" name="preferences--annotations--" type="toggle-switch" value="<%= youTubeDisplayContext.isAnnotations() %>" />

		<aui:input inlineField="<%= true %>" name="preferences--closedCaptioning--" type="toggle-switch" value="<%= youTubeDisplayContext.isClosedCaptioning() %>" />

		<aui:input name="preferences--startTime--" value="<%= youTubeDisplayContext.getStartTime() %>">
			<aui:validator name="digits" />
		</aui:input>
	</div>
</aui:fieldset>