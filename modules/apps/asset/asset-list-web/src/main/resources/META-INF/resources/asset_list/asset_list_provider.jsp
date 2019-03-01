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
AssetListProviderTracker assetListProviderTracker = (AssetListProviderTracker)request.getAttribute(AssetListWebKeys.ASSET_LIST_PROVIDER_TRACKER);
%>

<portlet:actionURL name="/asset_list/edit_asset_list_entry_settings" var="editAssetListEntrySettingsURL" />

<liferay-frontend:edit-form
	action="<%= editAssetListEntrySettingsURL %>"
	method="post"
	name="fm"
>
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="assetListEntryId" type="hidden" value="<%= assetListDisplayContext.getAssetListEntryId() %>" />

	<liferay-frontend:edit-form-body>
		<h1 class="sheet-title">
			<liferay-ui:message key="asset-list-provider" />
		</h1>

		<liferay-frontend:fieldset-group>
			<liferay-frontend:fieldset
				cssClass="asset-list-provider-container"
			>
				<aui:select label="" name="TypeSettingsProperties--assetListProviderClassName--">

					<%
					for (AssetListProvider assetListProvider : assetListProviderTracker.getAssetListProviders()) {
						Class<?> clazz = assetListProvider.getClass();
					%>

						<aui:option label="<%= assetListProvider.getLabel(locale) %>" selected="<%= Objects.equals(clazz.getName(), assetListDisplayContext.getSelectedAssetListProviderClassName()) %>" value="<%= clazz.getName() %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-frontend:fieldset>
		</liferay-frontend:fieldset-group>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button name="save" type="submit" />

		<aui:button href="<%= editAssetListDisplayContext.getRedirectURL() %>" type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>