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

<liferay-util:html-top>
	<link href="<%= PortalUtil.getStaticResourceURL(request, application.getContextPath() + "/css/main.css") %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<aui:input name="valuesLength" type="hidden" value="<%= 0 %>" />

<div id="<portlet:namespace/>selectedLayouts"></div>

<%
String eventName = renderResponse.getNamespace() + "selectLayout";

ItemSelector itemSelector = (ItemSelector)request.getAttribute(SiteNavigationMenuItemTypeLayoutWebKeys.ITEM_SELECTOR);

LayoutItemSelectorCriterion layoutItemSelectorCriterion = new LayoutItemSelectorCriterion();

List<ItemSelectorReturnType> desiredItemSelectorReturnTypes = new ArrayList<ItemSelectorReturnType>();

desiredItemSelectorReturnTypes.add(new UUIDItemSelectorReturnType());

layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(desiredItemSelectorReturnTypes);
layoutItemSelectorCriterion.setMultiSelection(true);

PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(RequestBackedPortletURLFactoryUtil.create(renderRequest), eventName, layoutItemSelectorCriterion);
%>

<iframe class="layout-item-selector-frame" src="<%= itemSelectorURL.toString() %>"></iframe>

<aui:script use="aui-base">
	var submitButton = A.one('#<portlet:namespace/>fm button[type="submit"]');

	var TPL_LAYOUT =
		'<div class="selected-layout"><input name="<portlet:namespace/>{index}-TypeSettingsProperties--groupId--" type="hidden" value="{groupId}"/>' +
		'<input name="<portlet:namespace/>{index}-TypeSettingsProperties--layoutUuid--" type="hidden" value="{id}"/>' +
		'<input name="<portlet:namespace/>{index}-TypeSettingsProperties--privateLayout--" type="hidden" value="{privateLayout}"/></div>';

	Liferay.on(
		'<%= eventName %>',
		(event) => {
			if (event.data.length > 0) {
				submitButton.removeClass('disabled');
			}
			else {
				submitButton.addClass('disabled');

				return;
			}

			A.one('#<portlet:namespace/>valuesLength').val(event.data.length);

			var selectedLayouts = A.one('#<portlet:namespace/>selectedLayouts');

			selectedLayouts.all('.selected-layout').remove();

			event.data.forEach(
				(value, index) => {
					value.index = index;

					var node = A.Node.create(A.Lang.sub(TPL_LAYOUT, value));

					selectedLayouts.appendChild(node);
				}
			);
		}
	);
</aui:script>