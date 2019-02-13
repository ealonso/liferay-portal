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

<%@ include file="/asset_tags_selector/init.jsp" %>

<%
PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
String namespace = AUIUtil.getNamespace(portletRequest, portletResponse);

String addCallback = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:addCallback"));
boolean allowAddEntry = GetterUtil.getBoolean((String)request.getAttribute("liferay-asset:asset-tags-selector:allowAddEntry"));
boolean autoFocus = GetterUtil.getBoolean((String)request.getAttribute("liferay-asset:asset-tags-selector:autoFocus"));
String eventName = (String)request.getAttribute("liferay-asset:asset-tags-selector:eventName");
long[] groupIds = (long[])request.getAttribute("liferay-asset:asset-tags-selector:groupIds");
String hiddenInput = (String)request.getAttribute("liferay-asset:asset-tags-selector:hiddenInput");
String id = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:id"));
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-asset:asset-tags-selector:portletURL");
String removeCallback = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:removeCallback"));
String tagNamesSeparatedWithCommas = GetterUtil.getString((String)request.getAttribute("liferay-asset:asset-tags-selector:tagNames"));

List<String> tagNames = Arrays.asList(StringUtil.split(tagNamesSeparatedWithCommas));

List<Object> selectedItems = new ArrayList<>();
for (String tagName : tagNames){
	HashMap<String, String> item = new HashMap<>();
	item.put("label", tagName);
	item.put("value", tagName);
	selectedItems.add(item);
}


String headerCSRFToken = "X-CSRF-Token";
String authToken = "zaGrGI9V";

HashMap<String, Object> requestOptions = new HashMap<>();
requestOptions.put("credentials", "include");
requestOptions.put(
	"headers",
	new HashMap<String, Object>() {
		{
			put(headerCSRFToken, authToken);
		}
	});

String locator = "name";
String jsonWebServiceLiferay = "http://localhost:8080/api/jsonws/assettag/get-groups-tags/group-ids/20126";
String anotherUrl = "https://jsonplaceholder.typicode.com/users";


String inputName = namespace + hiddenInput;
System.out.println("--------------------");
System.out.println(selectedItems);
System.out.println("--------------------");
System.out.println(inputName);
System.out.println("--------------------\n" +
	tagNames + "\n--------------------\n" +
	hiddenInput + "\n--------------------\n" +
	eventName);
%>

<h4>
	<liferay-ui:message key="tags" />
</h4>

<clay:multi-select
	componentId="myMultiselect"
	dataSource="<%= anotherUrl %>"
	helpText="Amazing help text"
	labelLocator="name"
	valueLocator="name"
	inputName="<%= inputName %>"
	selectedItems="<%= selectedItems %>"
/>

<aui:script use="liferay-asset-taglib-tags-selector">
	Liferay.componentReady('myMultiselect').then(
		function(multiSelect) {
			multiSelect.on(
				'buttonClicked',
				function(event) {
					const selectedTagNames = multiSelect.selectedItems
						.map(item => item.value).join();
					_showMultiSelectPopUp(
						selectedTagNames,
						event,
						function(event) {
							multiSelect.selectedItems = event.selectedItems;
						}
					);
				}
			);
		}
	);

	const _showMultiSelectPopUp = function(selectedTagNames, event, callback) {
		event.preventDefault();

		const uri = A.Lang.sub(
			decodeURIComponent("<%= portletURL %>"),
			{
				selectedTagNames: selectedTagNames
			}
		);

		const itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: "<%= eventName %>",
				on: {
					selectedItemChange: function(event) {
						var selectedItem = event.newVal;

						if (selectedItem) {
							event.selectedItems = [];
							A.Array.each(
								selectedItem.items.split(','),
								function(value) {
									if(_hasContent(value)){
										event.selectedItems.push(_createMultiSelectItemObject(value));
									}
								}
							);
							if (callback) {
								callback(event);
							}
						}
					}
				},
				'strings.add': Liferay.Language.get('done'),
				title: Liferay.Language.get('tags'),
				url: uri
			}
		);

		itemSelectorDialog.open();
	}

	const _hasContent = function(value) {
		return value !== undefined && value !== "" && value!== null;
	}

	const _createMultiSelectItemObject = function(value) {
		return {label: value, value: value};
	}
</aui:script>