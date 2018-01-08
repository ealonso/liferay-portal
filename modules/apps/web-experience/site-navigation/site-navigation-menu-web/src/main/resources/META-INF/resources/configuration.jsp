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

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:row>
				<aui:col width="<%= 50 %>">
					<aui:input id="siteNavigationMenuId" name="preferences--siteNavigationMenuId--" type="hidden" value="<%= siteNavigationMenuDisplayContext.getSiteNavigationMenuId() %>" />

					<%
					SiteNavigationMenu siteNavigationMenu = siteNavigationMenuDisplayContext.getSiteNavigationMenu();
					%>

					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset cssClass="p-3" label="navigation-menu">
							<c:if test="<%= siteNavigationMenu != null %>">
								<liferay-frontend:horizontal-card
									text="<%= siteNavigationMenu.getName() %>"
								>
									<liferay-frontend:horizontal-card-col>
										<liferay-frontend:horizontal-card-icon icon="blogs" />
									</liferay-frontend:horizontal-card-col>
								</liferay-frontend:horizontal-card>
							</c:if>

							<aui:button name="chooseSiteNavigationMenu" value="choose" />

							<div class="display-template mt-4">
								<liferay-ddm:template-selector
									className="<%= NavItem.class.getName() %>"
									displayStyle="<%= siteNavigationMenuDisplayContext.getDisplayStyle() %>"
									displayStyleGroupId="<%= siteNavigationMenuDisplayContext.getDisplayStyleGroupId() %>"
									refreshURL="<%= configurationRenderURL %>"
								/>
							</div>
						</aui:fieldset>

						<aui:fieldset cssClass="p-3" label="menu-items-to-show">

							<%
							String rootItemType = siteNavigationMenuDisplayContext.getRootItemType();
							%>

							<aui:row>
								<aui:col width="<%= 80 %>">
									<aui:select id="rootItemType" label="start-with-menu-items-in" name="preferences--rootItemType--" value="<%= rootItemType %>">
										<aui:option label="level" value="absolute" />
										<aui:option label="level-relative-to-the-current-menu-item" value="relative" />
										<aui:option label="select" value="select" />
									</aui:select>
								</aui:col>

								<aui:col width="<%= 20 %>">
									<div class="mt-4 <%= rootItemType.equals("absolute") || rootItemType.equals("relative-parent-up-by") ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />rootItemLevel">
										<aui:select label="" name="preferences--rootItemLevel--">

											<%
											for (int i = 0; i <= 4; i++) {
											%>

												<aui:option label="<%= i %>" selected="<%= siteNavigationMenuDisplayContext.getRootItemLevel() == i %>" />

											<%
											}
											%>

										</aui:select>
									</div>
								</aui:col>
							</aui:row>

							<aui:row>
								<aui:col width="<%= 80 %>">
									<div class="mb-2 <%= rootItemType.equals("select") ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />rootItemIdPanel">
										<aui:input id="rootItemId" ignoreRequestValue="<%= true %>" name="preferences--rootItemId--" type="hidden" value="<%= siteNavigationMenuDisplayContext.getRootItemId() %>" />

										<%
										SiteNavigationMenuItem siteNavigationMenuItem = SiteNavigationMenuItemLocalServiceUtil.fetchSiteNavigationMenuItem(siteNavigationMenuDisplayContext.getRootItemId());
										%>

										<c:if test="<%= siteNavigationMenuItem != null %>">

											<%
											SiteNavigationMenuItemType siteNavigationMenuItemType = siteNavigationMenuItemTypeRegistry.getSiteNavigationMenuItemType(siteNavigationMenuItem.getType());
											%>

											<liferay-frontend:horizontal-card
												cssClass="mb-3"
												text="<%= siteNavigationMenuItemType.getTitle(siteNavigationMenuItem, locale) %>"
											>
												<liferay-frontend:horizontal-card-col>
													<liferay-frontend:horizontal-card-icon icon="<%= siteNavigationMenuItemType.getIcon() %>" />
												</liferay-frontend:horizontal-card-col>
											</liferay-frontend:horizontal-card>
										</c:if>

										<aui:button class='<%= siteNavigationMenuDisplayContext.getSiteNavigationMenuId() > 0 ? "" : "disabled" %>' name="chooseRootItem" value="menu-item" />
									</div>
								</aui:col>
							</aui:row>

							<aui:row>
								<aui:col width="<%= 50 %>">
									<aui:select label="sublevels-to-display" name="preferences--displayDepth--">
										<aui:option label="unlimited" value="0" />

										<%
										for (int i = 1; i <= 20; i++) {
										%>

											<aui:option label="<%= i %>" selected="<%= siteNavigationMenuDisplayContext.getDisplayDepth() == i %>" />

										<%
										}
										%>

									</aui:select>
								</aui:col>

								<aui:col width="<%= 50 %>">
									<aui:fieldset label="expand-sublevels">
										<aui:field-wrapper cssClass="custom-control">
											<aui:input checked='<%= !siteNavigationMenuDisplayContext.getExpandedLevels().equals("all") %>' inlineField="<%= true %>" label="auto" name="preferences--expandedLevels--" type="radio" value="auto" />
											<aui:input checked='<%= siteNavigationMenuDisplayContext.getExpandedLevels().equals("all") %>' inlineField="<%= true %>" label="all" name="preferences--expandedLevels--" type="radio" value="all" />
										</aui:field-wrapper>
									</aui:fieldset>
								</aui:col>
							</aui:row>
						</aui:fieldset>
					</aui:fieldset-group>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-portlet:preview
						portletName="<%= portletResource %>"
						showBorders="<%= true %>"
					/>
				</aui:col>
			</aui:row>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script sandbox="<%= true %>" use="liferay-item-selector-dialog">
	var form = $('#<portlet:namespace />fm');
	var curPortletBoundaryId = '#p_p_id_<%= HtmlUtil.escapeJS(portletResource) %>_';

	form.on(
		'change',
		'select',
		function() {
			<portlet:namespace/>resetPreview();
		}
	);

	function <portlet:namespace/>resetPreview(refreshUrl) {
		var selectDisplayDepth = form.fm('displayDepth');
		var selectDisplayStyle = form.fm('displayStyle');
		var selectExpandedLevels = form.fm('expandedLevels');
		var selectRootItemLevel = form.fm('rootItemLevel');
		var selectRootItemType = form.fm('rootItemType');
		var rootItemId = form.fm('rootItemId');
		var siteNavigationMenuId = form.fm('siteNavigationMenuId');

		if (!refreshUrl) {
			var data = {
				displayStyle: selectDisplayStyle.val(),
				preview: true
			};

			data.displayDepth = selectDisplayDepth.val();
			data.expandedLevels = selectExpandedLevels.val();
			data.rootItemLevel = selectRootItemLevel.val();
			data.rootItemType = selectRootItemType.val();
			data.rootItemId = rootItemId.val();
			data.siteNavigationMenuId = siteNavigationMenuId.val()

			data = Liferay.Util.ns('_<%= HtmlUtil.escapeJS(portletResource) %>_', data);

			Liferay.Portlet.refresh(curPortletBoundaryId, data);
		}
		else {
			var uri = '<%= configurationRenderURL %>';

			uri = Liferay.Util.addParams('<portlet:namespace />displayDepth=' + selectDisplayDepth.val(), uri);
			uri = Liferay.Util.addParams('<portlet:namespace />expandedLevels=' + selectExpandedLevels.val(), uri);
			uri = Liferay.Util.addParams('<portlet:namespace />rootItemLevel=' + selectRootItemLevel.val(), uri);
			uri = Liferay.Util.addParams('<portlet:namespace />rootItemType=' + selectRootItemType.val(), uri);
			uri = Liferay.Util.addParams('<portlet:namespace />rootItemId=' + rootItemId.val(), uri);
			uri = Liferay.Util.addParams('<portlet:namespace />siteNavigationMenuId=' + siteNavigationMenuId.val(), uri);

			location.href = uri;
		}
	}

	var chooseRootItem = $('#<portlet:namespace />chooseRootItem');

	chooseRootItem.on(
		'click',
		function(event) {
			event.preventDefault();

			if (chooseRootItem.hasClass('disabled')) {
				return;
			}

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: '<%= siteNavigationMenuDisplayContext.getRootItemEventName() %>',
					on: {
						selectedItemChange: function(event) {
							var selectedItem = event.newVal;

							if (selectedItem) {
								var rootItemId = form.fm('rootItemId');

								rootItemId.val(selectedItem['site-navigation-menu-item-id']);

								<portlet:namespace/>resetPreview(true);
							}
						}
					},
					'strings.add': '<liferay-ui:message key="done" />',
					title: '<liferay-ui:message key="select-site-navigation-menu-item" />',
					url: '<%= siteNavigationMenuDisplayContext.getRootItemSelectorURL() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);

	Liferay.Util.toggleSelectBox('<portlet:namespace />rootItemType', 'select', '<portlet:namespace />rootItemIdPanel');

	Liferay.Util.toggleSelectBox(
		'<portlet:namespace />rootItemType',
		function(currentValue, value) {
			return currentValue === 'absolute' || currentValue === 'relative';
		},
		'<portlet:namespace />rootItemLevel'
	);

	var siteNavigationMenuId = $('#<portlet:namespace />siteNavigationMenuId');

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
					eventName: '<%= siteNavigationMenuDisplayContext.getSiteNavigationMenuEventName() %>',
					id: '<portlet:namespace />selectSiteNavigationMenu',
					title: '<liferay-ui:message key="select-site-navigation-menu" />',
					uri: '<%= siteNavigationMenuDisplayContext.getSiteNavigationMenuItemSelectorURL() %>'
				},
				function(selectedItem) {
					if (selectedItem.id) {
						siteNavigationMenuId.val(selectedItem.id);

						<portlet:namespace/>resetPreview(true);
					}
				}
			);

		}
	);
</aui:script>