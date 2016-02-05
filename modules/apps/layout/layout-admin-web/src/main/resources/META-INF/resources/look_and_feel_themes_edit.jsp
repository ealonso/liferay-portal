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

Theme selTheme = selLayoutSet.getTheme();
ColorScheme selColorScheme = selLayoutSet.getColorScheme();

if (selLayout != null) {
	selTheme = selLayout.getTheme();
	selColorScheme = selLayout.getColorScheme();
}

List<Theme> themes = ThemeLocalServiceUtil.getPageThemes(company.getCompanyId(), layoutsAdminDisplayContext.getLiveGroupId(), user.getUserId());

String colorSchemeId = selColorScheme.getColorSchemeId();
%>

<div class="lfr-theme-list">
	<div class="float-container lfr-current-theme" id="regularLookAndFeel">
		<aui:select label="current-theme" name="regularThemeId">

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

			<div class="theme-details-regular <%= selTheme.getThemeId().equals(curTheme.getThemeId()) ? StringPool.BLANK : "hide" %>" id="regular<%= curTheme.getThemeId() %>">
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
	$('#<portlet:namespace />regularThemeId').on(
		'change',
		function(event) {
			$('#regularLookAndFeel').find('.theme-details-regular').addClass('hide');

			$('#regular' + $(event.currentTarget).val()).removeClass('hide');
		}
	);
</aui:script>