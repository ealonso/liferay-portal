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

<%@ include file="/layout/edit/init.jsp" %>

<%
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectDisplayPage");
boolean privateLayout = ParamUtil.getBoolean(renderRequest, "privateLayout");

JSONObject layoutsJSONObject = linkToPageLayoutTypeControllerDisplayContext.getLayoutsJSON();

JSONObject publicLayoutsJSONObject = layoutsJSONObject.getJSONObject("public");
JSONObject privateLayoutsJSONObject = layoutsJSONObject.getJSONObject("private");
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<c:choose>
			<c:when test="<%= privateLayout %>">
				<aui:nav-item
					label="private-pages"
					selected="<%= true %>"
				/>
			</c:when>
			<c:otherwise>
				<aui:nav-item
					label="public-pages"
					selected="<%= true %>"
				/>
			</c:otherwise>
		</c:choose>
	</aui:nav>
</aui:nav-bar>

<aui:form cssClass="container-fluid-1280" name="selectDisplayPageFm">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<div class="layout-selector-tree" id="<portlet:namespace />layoutContainer">
			</div>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>

<aui:script require="journal-web/js/CardsTreeView.es,metal-dom/src/dom">
	var CardsTreeView = journalWebJsCardsTreeViewEs.default;
	var dom = metalDomSrcDom.default;

	new CardsTreeView(
		{
			events: {
				selectedNodesChanged: function(event) {
					var node = event.newVal[0];

					var data = {
						id: node.id,
						name: node.value
					};

					Liferay.Util.getOpener().Liferay.fire(
						'<%= HtmlUtil.escapeJS(eventName) %>',
						{
							data: data
						}
					);
				}
			},

			<c:choose>
				<c:when test="<%= privateLayout %>">
					nodes: [<%= privateLayoutsJSONObject.toString() %>],
				</c:when>
				<c:otherwise>
					nodes: [<%= publicLayoutsJSONObject.toString() %>],
				</c:otherwise>
			</c:choose>

			pathThemeImages: '<%= themeDisplay.getPathThemeImages() %>'
		},
		'#<portlet:namespace />layoutContainer'
	);
</aui:script>