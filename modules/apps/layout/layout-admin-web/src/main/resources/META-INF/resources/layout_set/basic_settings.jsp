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
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();
Group liveGroup = layoutsAdminDisplayContext.getLiveGroup();
%>

<liferay-frontend:fieldset
	collapsible="<%= true %>"
	label="favicon"
>
	<div class="form-group">
		<img alt="<%= HtmlUtil.escape(layoutsAdminDisplayContext.getFaviconTitle()) %>" class="mb-2" height="16" id="<portlet:namespace />faviconImage" src="<%= layoutsAdminDisplayContext.getFaviconURL() %>" width="16" />

		<p>
			<b><liferay-ui:message key="favicon-name" />:</b> <span id="<portlet:namespace />faviconTitle"><%= layoutsAdminDisplayContext.getFaviconTitle() %></span>
		</p>

		<aui:input name="faviconFileEntryId" type="hidden" value="<%= selLayoutSet.getFaviconFileEntryId() %>" />
		<aui:input name="themeFaviconCETExternalReferenceCode" type="hidden" value="<%= layoutsAdminDisplayContext.getThemeFaviconCETExternalReferenceCode() %>" />

		<aui:button name="selectFaviconButton" value="change-favicon" />

		<aui:button disabled="<%= !layoutsAdminDisplayContext.isClearFaviconButtonEnabled() %>" name="clearFaviconButton" value="clear" />

		<aui:script sandbox="<%= true %>">
			const clearFaviconButton = document.getElementById(
				'<portlet:namespace />clearFaviconButton'
			);
			const faviconFileEntryId = document.getElementById(
				'<portlet:namespace />faviconFileEntryId'
			);
			const faviconImage = document.getElementById(
				'<portlet:namespace />faviconImage'
			);
			const faviconTitle = document.getElementById(
				'<portlet:namespace />faviconTitle'
			);
			const selectLayoutButton = document.getElementById(
				'<portlet:namespace />selectFaviconButton'
			);
			const themeFaviconCETExternalReferenceCode = document.getElementById(
				'<portlet:namespace />themeFaviconCETExternalReferenceCode'
			);

			selectLayoutButton.addEventListener('click', (event) => {
				event.preventDefault();

				Liferay.Util.openSelectionModal({
					onSelect: function (selectedItem) {
						if (
							faviconFileEntryId &&
							faviconImage &&
							faviconTitle &&
							selectedItem &&
							selectedItem.value &&
							themeFaviconCETExternalReferenceCode
						) {
							const itemValue = JSON.parse(selectedItem.value);

							if (
								selectedItem.returnType ===
								'<%= CETItemSelectorReturnType.class.getName() %>'
							) {
								faviconFileEntryId.value = 0;
								themeFaviconCETExternalReferenceCode.value =
									itemValue.cetExternalReferenceCode;
							}
							else {
								faviconFileEntryId.value = itemValue.fileEntryId;
								themeFaviconCETExternalReferenceCode.value = '';
							}

							if (itemValue.url) {
								faviconImage.src = itemValue.url;
							}
							else {
								faviconImage.classList.add('d-none');
							}

							faviconTitle.innerHTML = itemValue.title || itemValue.name;
						}
					},
					selectEventName:
						'<%= layoutsAdminDisplayContext.getSelectFaviconEventName() %>',
					title: '<liferay-ui:message key="change-favicon" />',
					url: '<%= layoutsAdminDisplayContext.getFileEntryItemSelectorURL() %>',
				});
			});

			if (
				clearFaviconButton &&
				faviconFileEntryId &&
				faviconImage &&
				faviconTitle &&
				themeFaviconCETExternalReferenceCode
			) {
				clearFaviconButton.addEventListener('click', (event) => {
					faviconFileEntryId.value = 0;
					faviconImage.classList.add('d-none');
					faviconTitle.innerHTML =
						'<liferay-ui:message key="favicon-from-theme" />';
					themeFaviconCETExternalReferenceCode.value = '';
				});
			}
		</aui:script>
	</div>
</liferay-frontend:fieldset>


<liferay-frontend:fieldset
	collapsible="<%= true %>"
	label="logo"
>

	<liferay-ui:error-marker
		key="<%= WebKeys.ERROR_SECTION %>"
		value="logo"
	/>

	<liferay-ui:error exception="<%= FileSizeException.class %>">

		<%
		FileSizeException fileSizeException = (FileSizeException)errorException;
		%>

		<liferay-ui:message arguments="<%= LanguageUtil.formatStorageSize(fileSizeException.getMaxSize(), locale) %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
	</liferay-ui:error>

	<p class="text-muted">

		<%
		Group group = layoutsAdminDisplayContext.getGroup();
		%>

		<c:choose>
			<c:when test="<%= group.isPrivateLayoutsEnabled() %>">
				<liferay-ui:message key='<%= "upload-a-logo-for-the-" + (layoutsAdminDisplayContext.isPrivateLayout() ? "private" : "public") + "-pages-that-is-used-instead-of-the-default-enterprise-logo" %>' />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="upload-a-logo-for-pages-that-is-used-instead-of-the-default-enterprise-logo" />
			</c:otherwise>
		</c:choose>
	</p>

	<c:if test="<%= liveGroup.isLayoutSetPrototype() && !PropsValues.LAYOUT_SET_PROTOTYPE_PROPAGATE_LOGO %>">
		<div class="alert alert-warning">
			<liferay-ui:message key="modifying-the-site-template-logo-only-affects-sites-that-are-not-yet-created" />
		</div>
	</c:if>

	<%
	String companyLogoURL = themeDisplay.getPathImage() + "/company_logo?img_id=" + company.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(company.getLogoId());

	boolean defaultLogo = false;

	if (selLayoutSet.getLogoId() == 0) {
		defaultLogo = true;
	}
	else {
		LayoutSet guestGroupLayoutSet = layoutsAdminDisplayContext.getGuestGroupLayoutSet(company.getCompanyId());

		if (selLayoutSet.getLogoId() == guestGroupLayoutSet.getLogoId()) {
			defaultLogo = true;
		}
	}
	%>

	<liferay-ui:logo-selector
		currentLogoURL='<%= (selLayoutSet.getLogoId() == 0) ? companyLogoURL : themeDisplay.getPathImage() + "/layout_set_logo?img_id=" + selLayoutSet.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(selLayoutSet.getLogoId()) %>'
		defaultLogo="<%= defaultLogo %>"
		defaultLogoURL="<%= companyLogoURL %>"
		logoDisplaySelector=".layoutset-logo"
		showButtons="<%= GroupPermissionUtil.contains(permissionChecker, layoutsAdminDisplayContext.getSelGroup(), ActionKeys.MANAGE_LAYOUTS) && SitesUtil.isLayoutSetPrototypeUpdateable(selLayoutSet) %>"
		tempImageFileName="<%= String.valueOf(selLayoutSet.getLayoutSetId()) %>"
	/>

	<%
	Theme selTheme = selLayoutSet.getTheme();

	boolean showSiteNameSupported = GetterUtil.getBoolean(selTheme.getSetting("show-site-name-supported"), true);

	boolean showSiteNameDefault = GetterUtil.getBoolean(selTheme.getSetting("show-site-name-default"), showSiteNameSupported);
	%>

	<aui:input disabled="<%= !showSiteNameSupported %>" helpMessage='<%= showSiteNameSupported ? StringPool.BLANK : "the-theme-selected-for-the-site-does-not-support-displaying-the-title" %>' inlineLabel="right" label="show-site-name" labelCssClass="simple-toggle-switch" name="TypeSettingsProperties--showSiteName--" type="toggle-switch" value='<%= GetterUtil.getBoolean(selLayoutSet.getSettingsProperty("showSiteName"), showSiteNameDefault) %>' />
</liferay-frontend:fieldset>
