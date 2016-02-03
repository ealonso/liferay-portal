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
		<legend><liferay-ui:message key="current-theme" /></legend>

		<%@ include file="/look_and_feel_themes_theme_details.jspf" %>
	</div>

	<div class="float-container lfr-available-themes" id="<%= device %>availableThemes">
		<legend>
			<span class="header-title">
				<liferay-ui:message arguments="<%= themes.size() - 1 %>" key="available-themes-x" translateArguments="<%= false %>" />
			</span>

			<c:if test="<%= permissionChecker.isOmniadmin() && PortletLocalServiceUtil.hasPortlet(themeDisplay.getCompanyId(), PortletKeys.MARKETPLACE_STORE) && PrefsPropsUtil.getBoolean(PropsKeys.AUTO_DEPLOY_ENABLED, PropsValues.AUTO_DEPLOY_ENABLED) %>">

				<%
				PortletURL marketplaceURL = PortalUtil.getControlPanelPortletURL(request, PortletKeys.MARKETPLACE_STORE, PortletRequest.RENDER_PHASE);
				%>

				<aui:button-row>
					<aui:button cssClass="btn-lg manage-layout-set-branches-link" href="<%= marketplaceURL.toString() %>" id="installMore" value="install-more" />
				</aui:button-row>
			</c:if>
		</legend>

		<c:if test="<%= themes.size() > 1 %>">
			<ul class="lfr-theme-list list-unstyled">

				<%
				for (int i = 0; i < themes.size(); i++) {
					Theme curTheme = themes.get(i);

					if (!selTheme.getThemeId().equals(curTheme.getThemeId())) {
				%>

						<li>
							<div class="theme-entry">
								<img alt="" class="modify-link theme-thumbnail" onclick="<portlet:namespace /><%= device %>selectTheme('ThemeId<%= i %>', true);" src="<%= themeDisplay.getCDNBaseURL() %><%= HtmlUtil.escapeAttribute(curTheme.getStaticResourcePath()) %><%= HtmlUtil.escapeAttribute(curTheme.getImagesPath()) %>/thumbnail.png" title="<%= HtmlUtil.escapeAttribute(curTheme.getName()) %>" />

								<aui:input cssClass="theme-title" id='<%= device + "ThemeId" + i %>' label="<%= HtmlUtil.escape(curTheme.getName()) %>" name='<%= device + "ThemeId" %>' type="radio" value="<%= curTheme.getThemeId() %>" />
							</div>
						</li>

				<%
					}
				}
				%>

			</ul>
		</c:if>
	</div>
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