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

String backURL = ParamUtil.getString(request, "backURL", redirect);

Group selGroup = (Group)request.getAttribute(WebKeys.GROUP);

long liveGroupId = layoutsAdminDisplayContext.getLiveGroupId();
boolean privateLayout = layoutsAdminDisplayContext.isPrivateLayout();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

LayoutLookAndFeelDisplayContext layoutLookAndFeelDisplayContext = new LayoutLookAndFeelDisplayContext(request, layoutsAdminDisplayContext, liferayPortletResponse);

PortletURL redirectURL = layoutsAdminDisplayContext.getRedirectURL();

if (selGroup.isLayoutSetPrototype()) {
	privateLayout = true;
}

if (Validator.isNotNull(backURL)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);
}

renderResponse.setTitle(selGroup.getLayoutRootNodeName(privateLayout, locale));
%>

<portlet:actionURL name="/layout_admin/edit_layout_set" var="editLayoutSetURL">
	<portlet:param name="mvcRenderCommandName" value="/layout_admin/edit_layout_set" />
</portlet:actionURL>

<div class="container-fluid container-fluid-max-xl container-form-lg">
	<div class="sheet-lg">
		<h2><liferay-ui:message key="design" /></h2>

		<aui:form
			action="<%= editLayoutSetURL %>"
			enctype="multipart/form-data"
			method="post"
			name="fm"
		>
			<aui:input name="redirect" type="hidden" value="<%= redirectURL.toString() %>" />
			<aui:input name="groupId" type="hidden" value="<%= selGroup.getGroupId() %>" />
			<aui:input name="liveGroupId" type="hidden" value="<%= liveGroupId %>" />
			<aui:input name="stagingGroupId" type="hidden" value="<%= layoutsAdminDisplayContext.getStagingGroupId() %>" />
			<aui:input name="selPlid" type="hidden" value="<%= layoutsAdminDisplayContext.getSelPlid() %>" />
			<aui:input name="privateLayout" type="hidden" value="<%= privateLayout %>" />
			<aui:input name="layoutSetId" type="hidden" value="<%= selLayoutSet.getLayoutSetId() %>" />
			<aui:input name="<%= PortletDataHandlerKeys.SELECTED_LAYOUTS %>" type="hidden" />

			<clay:sheet cssClass="mt-4">
				<clay:sheet-header cssClass="mb-0">
					<h3 class="sheet-title"><liferay-ui:message key="theme" /></h3>
				</clay:sheet-header>

				<liferay-util:include page="/look_and_feel_themes.jsp" servletContext="<%= application %>" />
			</clay:sheet>

			<clay:sheet cssClass="mt-4">
				<clay:sheet-header cssClass="mb-0">
					<h3 class="sheet-title"><liferay-ui:message key="basic-settings" /></h3>
				</clay:sheet-header>

				<liferay-util:include page="/layout_set/basic_settings.jsp" servletContext="<%= application %>" />
			</clay:sheet>

			<clay:sheet cssClass="mt-4 panel-group panel-group-flush">
				<clay:sheet-header cssClass="mb-0">
					<h3 class="sheet-title"><liferay-ui:message key="customization" /></h3>
				</clay:sheet-header>

				<liferay-util:include page="/look_and_feel_theme_css.jsp" servletContext="<%= application %>" />

				<c:if test='<%= FeatureFlagManagerUtil.isEnabled("LPS-166479") %>'>
					<clay:sheet-section cssClass="mb-3">
						<liferay-frontend:fieldset
							collapsible="<%= true %>"
							label="theme-spritemap-client-extension"
						>
						<clay:alert
							displayType="info"
							message='<%= LanguageUtil.get(request, "to-add-or-edit-the-existing-spritemap-simply-copy-paste-and-make-changes-as-needed-to-your-registered-extension") %>'
						/>

						<p>
							<liferay-ui:message key="use-this-client-extension-to-fully-replace-the-default-spritemap-contained-in-the-theme" />
						</p>

						<div>
							<react:component
								module="js/layout/look_and_feel/ThemeSpritemapCETsConfiguration"
								props="<%= layoutLookAndFeelDisplayContext.getThemeSpritemapCETConfigurationProps(LayoutSet.class.getName(), selLayoutSet.getLayoutSetId()) %>"
							/>
						</div>
						</liferay-frontend:fieldset>
					</clay:sheet-section>
				</c:if>

				<clay:sheet-section cssClass="mb-3">
					<liferay-frontend:fieldset
						collapsible="<%= true %>"
						label="css-client-extensions"
					>
					<react:component
						module="js/layout/look_and_feel/GlobalCSSCETsConfiguration"
						props="<%= layoutLookAndFeelDisplayContext.getGlobalCSSCETsConfigurationProps(LayoutSet.class.getName(), selLayoutSet.getLayoutSetId()) %>"
					/>
					</liferay-frontend:fieldset>
				</clay:sheet-section>

				<liferay-util:include page="/layout_set/javascript.jsp" servletContext="<%= application %>" />

				<clay:sheet-section cssClass="mb-3">
					<liferay-frontend:fieldset
						collapsible="<%= true %>"
						label="seo"
					>
				<liferay-util:include page="/layout_set/robots.jsp" servletContext="<%= application %>" />

				<liferay-util:include page="/layout_set/sitemap.jsp" servletContext="<%= application %>" />
					</liferay-frontend:fieldset>
				</clay:sheet-section>
			</clay:sheet>

			<clay:sheet cssClass="mt-4">
				<liferay-frontend:form-navigator
					formModelBean="<%= selLayoutSet %>"
					id="<%= FormNavigatorConstants.FORM_NAVIGATOR_ID_LAYOUT_SET %>"
					showButtons="<%= false %>"
				/>
			</clay:sheet>

			<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, selGroup, ActionKeys.MANAGE_LAYOUTS) && SitesUtil.isLayoutSetPrototypeUpdateable(selLayoutSet) %>">
				<div class="mt-4">
					<aui:button cssClass="mr-3" type="submit" />

					<c:if test="<%= Validator.isNotNull(backURL) %>">
						<aui:button href="<%= backURL %>" name="cancelButton" type="cancel" />
					</c:if>
				</div>
			</c:if>
		</aui:form>
	</div>
</div>