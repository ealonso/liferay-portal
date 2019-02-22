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
JournalArticle article = journalDisplayContext.getArticle();

JournalEditArticleDisplayContext journalEditArticleDisplayContext = new JournalEditArticleDisplayContext(request, liferayPortletResponse, article);

DDMStructure ddmStructure = journalEditArticleDisplayContext.getDDMStructure();
%>

<aui:input name="groupId" type="hidden" value="<%= journalEditArticleDisplayContext.getGroupId() %>" />
<aui:input name="ddmStructureKey" type="hidden" value="<%= ddmStructure.getStructureKey() %>" />

<div class="article-structure">
	<b><liferay-ui:message key="structure" /></b>: <%= HtmlUtil.escape(ddmStructure.getName(locale)) %>
</div>

<c:if test="<%= article != null %>">
	<div class="article-version-status mt-4">
		<b><liferay-ui:message key="version" />:<%= article.getVersion() %></b>

		<span class="label label-<%= LabelItem.getStyleFromWorkflowStatus(article.getStatus()) %> text-uppercase">
			<liferay-ui:message key="<%= WorkflowConstants.getStatusLabel(article.getStatus()) %>" />
		</span>
	</div>
</c:if>

<c:if test="<%= !journalWebConfiguration.journalArticleForceAutogenerateId() %>">
	<div class="article-id mt-4">
		<aui:input disabled="<%= true %>" name="newArticleId" type="text" value="<%= (article != null) ? article.getArticleId() : 0 %>" />

		<%
		String taglibOnChange = "Liferay.Util.toggleDisabled('#" + renderResponse.getNamespace() + "newArticleId', event.target.checked);";
		%>

		<aui:input checked="<%= true %>" label="autogenerate-id" name="autoArticleId" onChange="<%= taglibOnChange %>" type="checkbox" value="<%= true %>" />
	</div>
</c:if>

<div class="article-content-description mt-4">
	<label for="<portlet:namespace />descriptionMapAsXML"><liferay-ui:message key="summary" /></label>

	<liferay-ui:input-localized
		cssClass="form-control"
		defaultLanguageId="<%= journalEditArticleDisplayContext.getDefaultLanguageId() %>"
		editorName="alloyeditor"
		formName="fm"
		ignoreRequestValue="<%= journalEditArticleDisplayContext.isChangeStructure() %>"
		name="descriptionMapAsXML"
		placeholder="description"
		type="editor"
		xml="<%= (article != null) ? article.getDescriptionMapAsXML() : StringPool.BLANK %>"
	/>
</div>