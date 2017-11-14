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
			<liferay-ui:error key="embeddedVideoURLInvalid" message="the-url-is-invalid" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input label="video-url" name="preferences--url--" placeholder="https://youtu.be/3_VCoBfrt9c" value="<%= displayContext.getURL() %>" />

					<aui:select inlineField="<%= true %>" label="preset-frame-aspect-ratio" name="preferences--presetRatio--" onChange='<%= renderResponse.getNamespace() + "updateFrameSize(this.value);" %>' value="<%= displayContext.getPresetRatio() %>">
						<aui:option label="custom" selected='<%= Objects.equals(displayContext.getPresetRatio(), "custom") %>' value="custom" />
						<aui:option label="old-4-3" selected='<%= Objects.equals(displayContext.getPresetRatio(), "4:3") %>' value="4:3" />
						<aui:option label="old-5-4" selected='<%= Objects.equals(displayContext.getPresetRatio(), "5:4") %>' value="5:4" />
						<aui:option label="standard-16-9" selected='<%= Objects.equals(displayContext.getPresetRatio(), "16:9") %>' value="16:9" />
						<aui:option label="special-16-10" selected='<%= Objects.equals(displayContext.getPresetRatio(), "16:10") %>' value="16:10" />
						<aui:option label="special-15-9" selected='<%= Objects.equals(displayContext.getPresetRatio(), "15:9") %>' value="15:9" />
					</aui:select>

					<aui:input disabled="<%= !displayContext.isCustomRatio() %>" inlineField="<%= true %>" label="aspect-ratio-width" name="preferences--width--" value="<%= displayContext.getWidth() %>">
						<aui:validator name="digits" />
					</aui:input>

					<aui:input disabled="<%= !displayContext.isCustomRatio() %>" inlineField="<%= true %>" label="aspect-ratio-height" name="preferences--height--" value="<%= displayContext.getHeight() %>">
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
			var dimensions = value.split(':');

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