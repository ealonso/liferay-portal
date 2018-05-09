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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %>

<%@ page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%
String layoutMode = ParamUtil.getString(request, "p_l_mode", Constants.VIEW);

String redirect = HttpUtil.setParameter(themeDisplay.getURLCurrent(), "p_l_mode", Objects.equals(layoutMode, Constants.EDIT) ? Constants.VIEW : Constants.EDIT);

if (layoutMode.equals(Constants.EDIT)) {
	long selPlid = ParamUtil.getLong(request, "p_r_p_selPlid");

	if (selPlid > 0) {
		Layout selLayout = LayoutLocalServiceUtil.getLayout(ParamUtil.getLong(request, "p_r_p_selPlid"));

		redirect = PortalUtil.getLayoutFullURL(selLayout, themeDisplay);
	}
}
else {
	long layoutPageTemplateEntryId = GetterUtil.getLong(layout.getTypeSettingsProperty("layoutPageTemplateEntryId"));

	if (layoutPageTemplateEntryId > 0) {
		PortletURL editLayoutURL = PortalUtil.getControlPanelPortletURL(request, PortletProviderUtil.getPortletId(Layout.class.getName(), PortletProvider.Action.EDIT), PortletRequest.RENDER_PHASE);

		editLayoutURL.setParameter("mvcPath", "/edit_content_layout.jsp");
		editLayoutURL.setParameter("backURL", themeDisplay.getURLCurrent());
		editLayoutURL.setParameter("groupId", String.valueOf(themeDisplay.getScopeGroupId()));
		editLayoutURL.setParameter("selPlid", String.valueOf(layout.getPlid()));
		editLayoutURL.setParameter("privateLayout", String.valueOf(layout.isPrivateLayout()));

		redirect = HttpUtil.setParameter(editLayoutURL.toString(), "p_l_mode", Constants.EDIT);
	}
}
%>

<label class="align-text-top toggle-switch">
	<input <%= Objects.equals(layoutMode, Constants.EDIT) ? "checked=\"checked\"" : StringPool.BLANK %> class="toggle-switch-check" id="<portlet:namespace />mode" type="checkbox" />

	<span aria-hidden="true" class="toggle-switch-bar">
		<span class="toggle-switch-handle" data-label-off="" data-label-on="">
			<span class="button-icon button-icon-on toggle-switch-icon">
				<svg aria-hidden="true" class="lexicon-icon lexicon-icon-unlock">
					<use xlink:href="<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg#cog" %>"></use>
				</svg>
			</span>
			<span class="button-icon button-icon-off toggle-switch-icon">
				<svg aria-hidden="true" class="lexicon-icon lexicon-icon-lock">
					<use xlink:href="<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg#view" %>"></use>
				</svg>
			</span>
		</span>
	</span>
</label>

<aui:script>
	$('#<portlet:namespace />mode').on(
		'change',
		function(event) {
			if (Liferay.SPA) {
				Liferay.SPA.app.navigate('<%= redirect %>');
			}
			else {
				location.href='<%= redirect %>'
			}
		}
	);
</aui:script>