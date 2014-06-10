
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

<%@ include file="/html/portlet/trash/init.jsp" %>

<%
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectParent");

TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(className);

TrashRenderer trashRenderer = trashHandler.getTrashRenderer(classPK);

BaseModel baseModel = (BaseModel)request.getAttribute(WebKeys.TRASH_BASE_MODEL);

long baseModelId = 0;

PortletURL baseModelURL = renderResponse.createRenderURL();

baseModelURL.setParameter("struts_action", "/trash/view_base_model");
baseModelURL.setParameter("redirect", currentURL);
baseModelURL.setParameter("className", className);
baseModelURL.setParameter("classPK", String.valueOf(classPK));
baseModelURL.setParameter("baseModelClassName", baseModel.getModelClassName());
%>

<div class="alert alert-block">
	<liferay-ui:message arguments="<%= new Object[] {trashHandler.getBaseModelName(), HtmlUtil.escape(trashRenderer.getTitle(locale))} %>" key="the-original-x-does-not-exist-anymore" translateArguments="<%= false %>" />
</div>

<aui:form method="post" name="selectParentFm">
	<liferay-ui:header
		showBackURL="<%= baseModel != null %>"
		title='<%= LanguageUtil.format(pageContext, "select-x", trashHandler.getBaseModelName()) %>'
	/>

	<liferay-ui:breadcrumb showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

	<aui:button-row>

		<%
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("classname", className);
		data.put("classpk", classPK);
		data.put("parentBaseModelId", 0);
		%>

		<aui:button cssClass="selector-button" data="<%= data %>" value='<%= LanguageUtil.format(pageContext, "choose-this-x", trashHandler.getContainerModelName()) %>' />
	</aui:button-row>

	<br />

	<%
	baseModelURL.setParameter("baseModelId", String.valueOf(baseModelId));
	%>

	<liferay-ui:search-container
		searchContainer="<%= new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, baseModelURL, null, null) %>"
		total="<%= trashHandler.getBaseModelsCount((Long)baseModel.getPrimaryKeyObj()) %>"
	>
		<liferay-ui:search-container-results
			results="<%= trashHandler.getBaseModels((Long)baseModel.getPrimaryKeyObj(), searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.BaseModel"
			keyProperty="primaryKeyObj"
			modelVar="curBaseModel"
		>

			<%
			baseModelURL.setParameter("baseModelId", String.valueOf(curBaseModel.getPrimaryKeyObj()));
			%>

			<liferay-ui:search-container-column-text
				name="<%= LanguageUtil.get(pageContext, trashHandler.getBaseModelName()) %>"
			>
				<c:choose>
					<c:when test="<%= (Long)curBaseModel.getPrimaryKeyObj() > 0 %>">

						<%
						TrashHandler baseModelTrashHandler = TrashHandlerRegistryUtil.getTrashHandler(((BaseModel)curBaseModel).getModelClassName());

						TrashRenderer baseModelTrashRenderer = baseModelTrashHandler.getBaseModelTrashRenderer((Long)curBaseModel.getPrimaryKeyObj());
						%>

						<liferay-ui:icon
							iconCssClass="<%= baseModelTrashRenderer.getIconCssClass() %>"
							label="<%= true %>"
							message="<%= baseModelTrashRenderer.getTitle(locale) %>"
							method="get"
							url="<%= baseModelURL.toString() %>"
						/>
					</c:when>
					<c:otherwise>
						<%= trashHandler.getBaseModelName() %>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text
				name="<%= LanguageUtil.get(pageContext, trashHandler.getContainerModelName()) %>"
				value="<%= trashHandler.getBaseModelContainerTitle((Long)curBaseModel.getPrimaryKeyObj()) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("classname", className);
				data.put("classpk", classPK);
				data.put("parentBaseModelId", (Long)curBaseModel.getPrimaryKeyObj());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectParentFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>