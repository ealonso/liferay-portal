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
String redirect = ParamUtil.getString(request, "redirect");

Class<?> clazz = displayContext.getClass();
String[] displayContextFullQualifiedClass = clazz.toString().split("\\.| ");
String displayContextClass = displayContextFullQualifiedClass[displayContextFullQualifiedClass.length - 1];
String advancedConfigPath = "/advancedConfigs/" + displayContextClass + ".jsp";
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input label="video-id" name="preferences--url--" value="<%= displayContext.getURL() %>" />

					<aui:select inlineField="<%= true %>" label="preset-frame-size" name="preferences--presetSize--" onChange='<%= renderResponse.getNamespace() + "updateFrameSize(this.value);" %>' value="<%= displayContext.getPresetSize() %>">
						<aui:option label="custom" selected='<%= Objects.equals(displayContext.getPresetSize(), "custom") %>' value="custom" />
						<aui:option label="standard-360-4-3" selected='<%= Objects.equals(displayContext.getPresetSize(), "480x360") %>' value="480x360" />
						<aui:option label="standard-360-16-9" selected='<%= Objects.equals(displayContext.getPresetSize(), "640x360") %>' value="640x360" />
						<aui:option label="enhanced-480-4-3" selected='<%= Objects.equals(displayContext.getPresetSize(), "640x480") %>' value="640x480" />
						<aui:option label="enhanced-480-16-9" selected='<%= Objects.equals(displayContext.getPresetSize(), "854x480") %>' value="854x480" />
						<aui:option label="hd-720-4-3" selected='<%= Objects.equals(displayContext.getPresetSize(), "960x720") %>' value="960x720" />
						<aui:option label="hd-720-16-9" selected='<%= Objects.equals(displayContext.getPresetSize(), "1280x720") %>' value="1280x720" />
						<aui:option label="full-hd-1080-4-3" selected='<%= Objects.equals(displayContext.getPresetSize(), "1440x1080") %>' value="1440x1080" />
						<aui:option label="full-hd-1080-16-9" selected='<%= Objects.equals(displayContext.getPresetSize(), "1920x1080") %>' value="1920x1080" />
					</aui:select>

					<aui:input disabled="<%= !displayContext.isCustomSize() %>" inlineField="<%= true %>" label="frame-width" name="preferences--width--" value="<%= displayContext.getWidth() %>">
						<aui:validator name="digits" />
					</aui:input>

					<aui:input disabled="<%= !displayContext.isCustomSize() %>" inlineField="<%= true %>" label="frame-height" name="preferences--height--" value="<%= displayContext.getHeight() %>">
						<aui:validator name="digits" />
					</aui:input>
				</aui:fieldset>

				<c:choose>
					<c:when test='<%= !displayContextClass.equals("BaseVideoEmbedderDisplayContext") %>'>
						<liferay-util:include page="<%= advancedConfigPath %>" servletContext="<%= application %>" />
					</c:when>
				</c:choose>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	var <portlet:namespace />customHeight;
	var <portlet:namespace />customWidth;

	function <portlet:namespace />updateFrameSize(value) {
		var Util = Liferay.Util;

		var heightNode = AUI.$('#<portlet:namespace />height');
		var widthNode = AUI.$('#<portlet:namespace />width');

		var useDefaults = value != 'custom';

		Util.toggleDisabled(heightNode, useDefaults);
		Util.toggleDisabled(widthNode, useDefaults);

		if (useDefaults) {
			var dimensions = value.split('x');

			heightNode.val(dimensions[1]);
			widthNode.val(dimensions[0]);
		}
		else {
			heightNode.on(
				'blur',
				function(event) {
					<portlet:namespace />customHeight = event.currentTarget.value;
				}
			);

			heightNode.val(<portlet:namespace />customHeight);

			widthNode.on(
				'blur',
				function(event) {
					<portlet:namespace />customWidth = event.currentTarget.value;
				}
			);

			widthNode.val(<portlet:namespace />customWidth);
		}
	}

</aui:script>