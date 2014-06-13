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
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFolder");

TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(className);

TrashRenderer trashRenderer = trashHandler.getTrashRenderer(classPK);
%>

<div class="alert alert-block">
	<liferay-ui:message arguments="<%= new Object[] {trashHandler.getContainerModelName(), HtmlUtil.escape(trashRenderer.getTitle(locale))} %>" key="the-original-x-does-not-exist-anymore" translateArguments="<%= false %>" />
</div>

<aui:form method="post" name="selectFolderFm">
	<liferay-ui:header
		showBackURL="<%= containerModel != null %>"
		title='<%= LanguageUtil.format(pageContext, "select-x", trashHandler.getContainerModelName(classPK)) %>'
	/>

	<c:choose>
		<c:when test="<%= trashHandler.isBaseModel() %>">

			<%
			BaseModel baseModel = (BaseModel)request.getAttribute(WebKeys.TRASH_BASE_MODEL);

			long baseModelId = 0;

			if (baseModel != null) {
				baseModelId = (Long)baseModel.getPrimaryKeyObj();
			}

			PortletURL baseModelURL = renderResponse.createRenderURL();

			baseModelURL.setParameter("struts_action", "/trash/view_base_model");
			baseModelURL.setParameter("redirect", currentURL);
			baseModelURL.setParameter("className", className);
			baseModelURL.setParameter("classPK", String.valueOf(classPK));
			baseModelURL.setParameter("baseModelClassName", baseModel.getModelClassName());
			%>

			<aui:button-row>

				<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("classname", className);
					data.put("classpk", classPK);
					data.put("parentBaseModelId", 0);
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value='<%= LanguageUtil.format(pageContext, "choose-this-x", trashHandler.getContainerModelName(classPK)) %>' />
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

						<liferay-ui:icon
							iconCssClass="<%= containerTrashRenderer.getIconCssClass() %>"
							label="<%= true %>"
							message="<%= curContainerModel.getContainerModelName() %>"
							method="get"
							url="<%= containerURL.toString() %>"
						/>
					</c:when>
					<c:otherwise>
						<%= curContainerModel.getContainerModelName() %>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<c:if test="<%= !trashHandler.isBaseModel() %>">
				<liferay-ui:search-container-column-text
					name='<%= LanguageUtil.format(pageContext, "num-of-x", trashHandler.getContainerModelName()) %>'
					value="<%= String.valueOf(trashHandler.getContainerModelsCount(classPK, curContainerModel.getContainerModelId())) %>"
					/>
			</c:if>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("classname", className);
				data.put("classpk", classPK);
				data.put("containermodelid", curContainerModel.getContainerModelId());
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectFolderFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>