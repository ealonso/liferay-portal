<%--
/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/osb_patcher/views/init.jsp" %>

<%
PatcherFixComponentsDisplayContext patcherFixComponentsDisplayContext = new PatcherFixComponentsDisplayContext(request, renderRequest, renderResponse);
%>

<liferay-util:include page="/osb_patcher/views/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tabs1" value="fix-components" />
</liferay-util:include>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= new PatcherFixComponentsManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, patcherFixComponentsDisplayContext.getSearchContainer()) %>"
/>

<liferay-ui:search-container
	searchContainer="<%= patcherFixComponentsDisplayContext.getSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.patcher.model.PatcherFixComponent"
		escapedModel="<%= true %>"
		keyProperty="patcherFixComponentId"
		modelVar="patcherFixComponent"
	>
		<liferay-ui:search-container-column-text
			property="name"
		/>

		<liferay-ui:search-container-column-text
			align="right"
		>
			<clay:dropdown-actions
				aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
				dropdownItems="<%= patcherFixComponentsDisplayContext.getDropdownItems(patcherFixComponent) %>"
				propsTransformer="{FixComponentsDropdownDefaultPropsTransformer} from osb-patcher-web"
			/>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>