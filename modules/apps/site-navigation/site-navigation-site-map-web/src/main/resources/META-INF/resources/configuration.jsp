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
SiteNavigationMenu siteNavigationMenu = siteNavigationSiteMapDisplayContext.getSiteNavigationMenu();

String siteNavigationMenuName = LanguageUtil.get(request, "default");

if (siteNavigationMenu != null) {
	siteNavigationMenuName = siteNavigationMenu.getName();
}
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<liferay-frontend:edit-form-body>
		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset>
				<div class="display-template">
					<liferay-ddm:template-selector
						className="<%= LayoutSet.class.getName() %>"
						displayStyle="<%= siteNavigationSiteMapPortletInstanceConfiguration.displayStyle() %>"
						displayStyleGroupId="<%= siteNavigationSiteMapDisplayContext.getDisplayStyleGroupId() %>"
						refreshURL="<%= configurationRenderURL %>"
						showEmptyOption="<%= true %>"
					/>
				</div>

				<aui:input id="siteNavigationMenuId" name="preferences--siteNavigationMenuId--" type="hidden" value="<%= siteNavigationSiteMapDisplayContext.getSiteNavigationMenuId() %>" />
				<aui:input id="siteNavigationMenuType" name="preferences--siteNavigationMenuType--" type="hidden" value="<%= siteNavigationSiteMapDisplayContext.getSiteNavigationMenuType() %>" />

				<c:choose>
					<c:when test="<%= SiteNavigationMenuLocalServiceUtil.getSiteNavigationMenusCount(scopeGroupId) > 0 %>">
						<div>
							<aui:input checked="<%= !siteNavigationSiteMapDisplayContext.isSiteNavigationMenuSelected() %>" cssClass="select-navigation" label="select-navigation" name="selectNavigation" type="radio" value="0" />

							<aui:select disabled="<%= siteNavigationSiteMapDisplayContext.isSiteNavigationMenuSelected() %>" label="" name="selectSiteNavigationMenuType" value="<%= siteNavigationSiteMapDisplayContext.getSelectSiteNavigationMenuType() %>">
								<aui:option label="primary-navigation" selected="<%= siteNavigationSiteMapDisplayContext.getSelectSiteNavigationMenuType() == SiteNavigationConstants.TYPE_PRIMARY %>" value="<%= SiteNavigationConstants.TYPE_PRIMARY %>" />
								<aui:option label="private-navigation" selected="<%= siteNavigationSiteMapDisplayContext.getSelectSiteNavigationMenuType() == SiteNavigationConstants.TYPE_PRIVATE %>" value="<%= SiteNavigationConstants.TYPE_PRIVATE %>" />
								<aui:option label="secondary-navigation" selected="<%= siteNavigationSiteMapDisplayContext.getSelectSiteNavigationMenuType() == SiteNavigationConstants.TYPE_SECONDARY %>" value="<%= SiteNavigationConstants.TYPE_SECONDARY %>" />
								<aui:option label="social-navigation" selected="<%= siteNavigationSiteMapDisplayContext.getSelectSiteNavigationMenuType() == SiteNavigationConstants.TYPE_SOCIAL %>" value="<%= SiteNavigationConstants.TYPE_SOCIAL %>" />
							</aui:select>

							<aui:input checked="<%= siteNavigationSiteMapDisplayContext.isSiteNavigationMenuSelected() %>" cssClass="select-navigation" label="choose-menu" name="selectNavigation" type="radio" value="-1" />

							<div class="mb-2 text-muted">
								<span id="<portlet:namespace />navigationMenuName">
									<c:if test="<%= siteNavigationSiteMapDisplayContext.getSiteNavigationMenuId() > 0 %>">
										<%= siteNavigationMenuName %>
									</c:if>
								</span>
								<span class="mt-1 <%= (siteNavigationSiteMapDisplayContext.getSiteNavigationMenuId() > 0) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />removeSiteNavigationMenu" role="button">
									<aui:icon cssClass="icon-monospaced" image="times" markupView="lexicon" />
								</span>
							</div>

							<aui:button cssClass="mb-2" disabled="<%= !siteNavigationSiteMapDisplayContext.isSiteNavigationMenuSelected() %>" name="chooseSiteNavigationMenu" value="select" />
						</div>
					</c:when>
					<c:otherwise>
						<div class="card card-horizontal taglib-horizontal-card">
							<div class="card-row card-row-padded ">
								<div class="card-col-field">
									<div class="sticker sticker-secondary sticker-static">
										<aui:icon image="blogs" markupView="lexicon" />
									</div>
								</div>

								<div class="card-col-content card-col-gutters">
									<span class="lfr-card-title-text truncate-text" id="<portlet:namespace />siteNavigationMenuName">
										<%= siteNavigationMenuName %>
									</span>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

				<aui:select name="preferences--displayDepth--">
					<aui:option label="unlimited" value="0" />

					<%
					for (int i = 1; i <= 20; i++) {
					%>

						<aui:option label="<%= i %>" selected="<%= siteNavigationSiteMapPortletInstanceConfiguration.displayDepth() == i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />chooseSiteNavigationMenu').on(
		'click',
		function(event) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true,
						modal: true
					},
					eventName: '<%= siteNavigationSiteMapDisplayContext.getSiteNavigationMenuEventName() %>',
					id: '<portlet:namespace />selectSiteNavigationMenu',
					title: '<liferay-ui:message key="select-site-navigation-menu" />',
					uri: '<%= siteNavigationSiteMapDisplayContext.getSiteNavigationMenuItemSelectorURL() %>'
				},
				function(selectedItem) {
					if (selectedItem.id) {
						$('#<portlet:namespace />siteNavigationMenuId').val(selectedItem.id);

						$('#<portlet:namespace />navigationMenuName').text(selectedItem.name);

						$('#<portlet:namespace />removeSiteNavigationMenu').toggleClass('hide');
					}
				}
			);
		}
	);

	$('.select-navigation').on(
		'change',
		function() {
			var chooseSiteNavigationMenu = $('#<portlet:namespace />chooseSiteNavigationMenu');
			var selectSiteNavigationMenuType = $('#<portlet:namespace />selectSiteNavigationMenuType')

			var state = selectSiteNavigationMenuType.prop('disabled');

			chooseSiteNavigationMenu.prop('disabled', state);
			chooseSiteNavigationMenu.toggleClass('disabled', state);

			selectSiteNavigationMenuType.prop('disabled', !state);

			$('#<portlet:namespace />siteNavigationMenuId').val(0);

			$('#<portlet:namespace />siteNavigationMenuType').val(-1);

			$('#<portlet:namespace />navigationMenuName').text('');

			$('#<portlet:namespace />removeSiteNavigationMenu').addClass('hide');
		}
	);

	$('#<portlet:namespace />removeSiteNavigationMenu').on(
		'click',
		function(event) {
			$('#<portlet:namespace />siteNavigationMenuId').val('0');

			$('#<portlet:namespace />navigationMenuName').text('');

			$('#<portlet:namespace />removeSiteNavigationMenu').toggleClass('hide');
		}
	);
</aui:script>