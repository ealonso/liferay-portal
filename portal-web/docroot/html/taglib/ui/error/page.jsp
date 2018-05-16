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

<%@ include file="/html/taglib/init.jsp" %>

<%
String key = (String)request.getAttribute("liferay-ui:error:key");
String message = (String)request.getAttribute("liferay-ui:error:message");
String rowBreak = (String)request.getAttribute("liferay-ui:error:rowBreak");
String targetNode = GetterUtil.getString((String)request.getAttribute("liferay-ui:error:targetNode"));
boolean toast = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:error:toast"));
boolean translateMessage = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:error:translateMessage"));

String bodyContentString = StringPool.BLANK;

Object bodyContent = request.getAttribute("liferay-ui:error:bodyContent");

if (bodyContent != null) {
	bodyContentString = bodyContent.toString();
}

boolean showAlert = false;

String alertIcon = "exclamation-full";
String alertMessage = bodyContentString;
String alertStyle = "danger";
String alertTitle = LanguageUtil.get(resourceBundle, "error");
%>

<c:choose>
	<c:when test="<%= (key != null) && Validator.isNull(message) %>">
		<c:if test="<%= SessionErrors.contains(portletRequest, key) %>">
			<c:if test="<%= Validator.isNotNull(bodyContentString) %>">

				<%
				showAlert = true;
				%>

			</c:if>
		</c:if>
	</c:when>
	<c:when test='<%= SessionErrors.contains(portletRequest, "warning") %>'>
		<liferay-util:buffer
			var="alertMessageContent"
		>
			<c:choose>
				<c:when test="<%= message != null %>">
					<liferay-ui:message key="<%= message %>" localizeKey="<%= translateMessage %>" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key='<%= (String)SessionErrors.get(portletRequest, "warning") %>' localizeKey="<%= translateMessage %>" />
				</c:otherwise>
			</c:choose>
		</liferay-util:buffer>

		<%
		alertIcon = "warning-full";
		alertMessage = alertMessageContent;
		alertStyle = "warning";
		alertTitle = LanguageUtil.get(resourceBundle, "warning");
		showAlert = true;
		%>

	</c:when>
	<c:when test="<%= key == null %>">

		<%
		alertMessage = LanguageUtil.get(resourceBundle, "your-request-failed-to-complete");
		showAlert = true;
		%>

	</c:when>
	<c:otherwise>
		<c:if test="<%= SessionErrors.contains(portletRequest, key) %>">

			<%
			alertMessage = translateMessage ? LanguageUtil.get(resourceBundle, message) : message;
			showAlert = true;
			%>

		</c:if>
	</c:otherwise>
</c:choose>

<c:if test="<%= showAlert %>">
	<c:choose>
		<c:when test="<%= toast %>">
			<aui:script require="metal-dom/src/all/dom as dom,clay-alert@2.0.2/lib/ClayToast as ClayToast">
				var alertContainer = document.getElementById('alertContainer');

				if (!alertContainer) {
					alertContainer = document.createElement('div');
					alertContainer.id = 'alertContainer';

					dom.addClasses(alertContainer, 'alert-notifications alert-notifications-fixed');
					dom.enterDocument(alertContainer);
				}

				new ClayToast.default(
					{
						destroyOnHide: true,
						message: '<%= alertMessage %>',
						spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg',
						style: '<%= alertStyle %>',
						title: '<%= alertTitle %>'
					},
					alertContainer
				);
			</aui:script>
		</c:when>
		<c:otherwise>
			<div class="alert alert-<%= alertStyle %>" role="alert">
				<span class="alert-indicator">
					<svg aria-hidden="true" class="lexicon-icon lexicon-icon-<%= alertIcon %>">
						<use xlink:href="<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg#<%= alertIcon %>"></use>
					</svg>
				</span>

				<strong class="lead"><%= alertTitle %>:</strong><%= alertMessage %>
			</div>

			<%= rowBreak %>
		</c:otherwise>
	</c:choose>
</c:if>