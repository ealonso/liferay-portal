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

long groupId = BeanParamUtil.getLong(article, request, "groupId", scopeGroupId);

long classNameId = ParamUtil.getLong(request, "classNameId");

DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");

DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute("edit_article.jsp-template");
%>

<div class="article-structure-template-toolbar journal-metadata">
	<span class="alert alert-warning hide structure-message" id="<portlet:namespace />structureMessage">
		<liferay-ui:message key="this-structure-has-not-been-saved" />

		<liferay-ui:message arguments='<%= new Object[] {"journal-save-structure-trigger", "#"} %>' key="click-here-to-save-it-now" />
	</span>

	<aui:row>
		<aui:col cssClass="article-structure" width="<%= 50 %>">
			<span class="article-structure-label"><liferay-ui:message key="structure" />:</span>

			<aui:fieldset>
				<div class="journal-form-presentation-label">
					<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
					<aui:input name="ddmStructureKey" type="hidden" value="<%= ddmStructure.getStructureKey() %>" />
					<aui:input name="structureName" type="hidden" value="<%= ddmStructure.getName(locale) %>" />
					<aui:input name="structureDescription" type="hidden" value="<%= ddmStructure.getDescription(locale) %>" />

					<span class="structure-name-label" id="<portlet:namespace />structureNameLabel">
						<c:choose>
							<c:when test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, JournalPortletKeys.JOURNAL, ActionKeys.UPDATE) %>">
								<aui:a href="javascript:;" id="editDDMStructure" label="<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>" />
							</c:when>
							<c:otherwise>
								<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>
							</c:otherwise>
						</c:choose>
					</span>

					<c:if test="<%= classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>">
						<liferay-ui:icon
							iconCssClass="icon-search"
							id="selectStructure"
							label="<%= true %>"
							linkCssClass="btn btn-default"
							message="select"
							url="javascript:;"
						/>
					</c:if>
				</div>
			</aui:fieldset>
		</aui:col>

		<aui:col cssClass="article-template" width="<%= 50 %>">
			<span class="article-template-label"><liferay-ui:message key="template" />:</span>

			<aui:fieldset cssClass="article-template-toolbar">
				<div class="journal-form-presentation-label">
					<aui:input name="ddmTemplateKey" type="hidden" value="<%= (ddmTemplate != null) ? ddmTemplate.getTemplateKey() : StringPool.BLANK %>" />

					<span class="template-name-label" id="<portlet:namespace />templateNameLabel">
						<c:if test="<%= (ddmTemplate != null) && ddmTemplate.isSmallImage() %>">
							<img alt="" class="article-template-image" id="<portlet:namespace />templateImage" src="<%= HtmlUtil.escapeAttribute(ddmTemplate.getTemplateImageURL(themeDisplay)) %>" />
						</c:if>

						<c:choose>
							<c:when test="<%= (ddmTemplate != null) && DDMTemplatePermission.contains(permissionChecker, scopeGroupId, ddmTemplate, JournalPortletKeys.JOURNAL, ActionKeys.UPDATE) %>">
								<aui:a href="javascript:;" id="editDDMTemplate" label="<%= HtmlUtil.escape(ddmTemplate.getName(locale)) %>" />
							</c:when>
							<c:otherwise>
								<%= (ddmTemplate != null) ? HtmlUtil.escape(ddmTemplate.getName(locale)) : LanguageUtil.get(request, "none") %>
							</c:otherwise>
						</c:choose>
					</span>

					<liferay-ui:icon
						iconCssClass="icon-search"
						id="selectTemplate"
						label="<%= true %>"
						linkCssClass="btn btn-default"
						message="select"
						url="javascript:;"
					/>
				</div>
			</aui:fieldset>
		</aui:col>
	</aui:row>
</div>