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

<c:choose>
	<c:when test="<%= Validator.isNotNull(displayContext.getURL()) %>">
		<iframe allowfullscreen frameborder="0" src="<%= displayContext.getEmbedURL() %>" width="100%" wmode="Opaque" onload="resizeIFrame()"/></iframe>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>

<aui:script>
	var portlet = document.getElementById('portlet_<%= YouTubePortletKeys.YOUTUBE %>');
	var container = portlet.querySelector('.portlet-body');
	var frame = container.querySelector('iframe');

	function resizeIFrame() {
		frame.width = frame.height = 0;

		var width = container.offsetWidth;
		frame.width = width;
		var aspectRatioHeight = <%= displayContext.getHeight() %>;
		var aspectRatioWidth = <%= displayContext.getWidth() %>;
		frame.height = width * (aspectRatioHeight / aspectRatioWidth);
	}

	window.addEventListener('resize', resizeIFrame);
</aui:script>