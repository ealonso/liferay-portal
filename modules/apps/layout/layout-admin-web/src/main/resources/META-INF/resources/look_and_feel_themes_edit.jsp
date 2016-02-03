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
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

List<Theme> themes = (List<Theme>)request.getAttribute("edit_pages.jsp-themes");
List<ColorScheme> colorSchemes = (List<ColorScheme>)request.getAttribute("edit_pages.jsp-colorSchemes");
Theme selTheme = (Theme)request.getAttribute("edit_pages.jsp-selTheme");
ColorScheme selColorScheme = (ColorScheme)request.getAttribute("edit_pages.jsp-selColorScheme");
String device = (String)request.getAttribute("edit_pages.jsp-device");

Map<String, ThemeSetting> configurableSettings = selTheme.getConfigurableSettings();
%>

<div class="lfr-theme-list">
	<div class="float-container lfr-current-theme" id="<%= device %>LookAndFeel">
		<h3><liferay-ui:message key="current-theme" /></h3>

		<aui:select label="" name='<%= device + "ThemeId" %>'>

			<%
			for (Theme curTheme : themes) {
			%>

				<aui:option selected="<%= selTheme.getThemeId().equals(curTheme.getThemeId()) %>" label="<%= HtmlUtil.escape(curTheme.getName()) %>" value="<%= curTheme.getThemeId() %>" />

			<%
			}
			%>

		</aui:select>

		<%
		for (Theme curTheme : themes) {
		%>

			<div class="theme-details-<%= device %> <%= selTheme.getThemeId().equals(curTheme.getThemeId()) ? StringPool.BLANK : "hide" %>" id="<%= device  + curTheme.getThemeId() %>">
				<%@ include file="/look_and_feel_themes_theme_details.jspf" %>
			</div>

		<%
		}
		%>
	</div>

	<c:if test="<%= permissionChecker.isOmniadmin() && PortletLocalServiceUtil.hasPortlet(themeDisplay.getCompanyId(), PortletKeys.MARKETPLACE_STORE) && PrefsPropsUtil.getBoolean(PropsKeys.AUTO_DEPLOY_ENABLED, PropsValues.AUTO_DEPLOY_ENABLED) %>">

		<%
		PortletURL marketplaceURL = PortalUtil.getControlPanelPortletURL(request, PortletKeys.MARKETPLACE_STORE, PortletRequest.RENDER_PHASE);
		%>

		<aui:button cssClass="btn-lg" href="<%= marketplaceURL.toString() %>" id="installMore" value="install-more" />
	</c:if>
</div>

<aui:script sandbox="<%= true %>">
	var colorSchemePanel = $('#<%= device %>layoutsAdminLookAndFeelColorsPanel');

	var toggleDisabled = function(disabled) {
		colorSchemePanel.find('input[name=<portlet:namespace /><%= device %>ColorSchemeId]').prop('disabled', disabled);
	};

	if (colorSchemePanel.length) {
		$('#<%= device %>availableThemes').find('input[name=<portlet:namespace /><%= device %>ThemeId]').on(
			'change',
			function() {
				toggleDisabled(true);
			}
		);

		$('#<%= device %>LookAndFeel').find('#<portlet:namespace /><%= device %>SelTheme').on(
			'change',
			function() {
				toggleDisabled(false);
			}
		);
	}

	$('#<portlet:namespace /><%= device %>ThemeId').on(
		'change',
		function(event) {
			$('#<%= device %>LookAndFeel').find('.theme-details-<%= device %>').addClass('hide');

			$('#<%= device%>' + $(event.currentTarget).val()).removeClass('hide');
		}
	);
</aui:script>

<aui:script>
	function <portlet:namespace /><%= device %>selectColorScheme(id) {
		var colorSchemeInput = AUI.$(id);

		if (!colorSchemeInput.prop('disabled')) {
			colorSchemeInput.prop('checked', true);
		}
	}

	function <portlet:namespace /><%= device %>selectTheme(themeId, colorSchemesDisabled) {
		var $ = AUI.$;

		$('#<portlet:namespace /><%= device %>' + themeId).prop('checked', true);

		$('#<%= device %>layoutsAdminLookAndFeelColorsPanel').find('input[name=<portlet:namespace /><%= device %>ColorSchemeId]').prop('disabled', colorSchemesDisabled);
	}
</aui:script>