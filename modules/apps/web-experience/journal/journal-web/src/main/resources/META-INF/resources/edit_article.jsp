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
EditJournalDisplayContext editJournalDisplayContext = new EditJournalDisplayContext(request, renderResponse, journalDisplayContext.getArticle());

String portletResource = ParamUtil.getString(request, "portletResource");

long referringPlid = ParamUtil.getLong(request, "referringPlid");
String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

boolean changeStructure = GetterUtil.getBoolean(ParamUtil.getString(request, "changeStructure"));

JournalArticle article = journalDisplayContext.getArticle();

long classPK = BeanParamUtil.getLong(article, request, "classPK");

boolean hideDefaultSuccessMessage = ParamUtil.getBoolean(request, "hideDefaultSuccessMessage", false);

request.setAttribute("edit_article.jsp-changeStructure", changeStructure);
%>

<aui:model-context bean="<%= article %>" model="<%= JournalArticle.class %>" />

<liferay-frontend:edit-form
	action="<%= editJournalDisplayContext.getEditArticleActionURL() %>"
	enctype="multipart/form-data"
	method="post"
	name="fm1"
	onSubmit="event.preventDefault();"
>
	<aui:input name="<%= ActionRequest.ACTION_NAME %>" type="hidden" />
	<aui:input name="hideDefaultSuccessMessage" type="hidden" value="<%= hideDefaultSuccessMessage || (editJournalDisplayContext.getClassNameId() == PortalUtil.getClassNameId(DDMStructure.class)) %>" />
	<aui:input name="redirect" type="hidden" value="<%= editJournalDisplayContext.getRedirect() %>" />
	<aui:input name="portletResource" type="hidden" value="<%= portletResource %>" />
	<aui:input name="referringPlid" type="hidden" value="<%= referringPlid %>" />
	<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
	<aui:input name="groupId" type="hidden" value="<%= editJournalDisplayContext.getGroupId() %>" />
	<aui:input name="privateLayout" type="hidden" value="<%= layout.isPrivateLayout() %>" />
	<aui:input name="folderId" type="hidden" value="<%= editJournalDisplayContext.getFolderId() %>" />
	<aui:input name="classNameId" type="hidden" value="<%= editJournalDisplayContext.getClassNameId() %>" />
	<aui:input name="classPK" type="hidden" value="<%= classPK %>" />
	<aui:input name="articleId" type="hidden" value="<%= editJournalDisplayContext.getArticleId() %>" />
	<aui:input name="articleIds" type="hidden" value="<%= editJournalDisplayContext.getArticleId() + JournalPortlet.VERSION_SEPARATOR + editJournalDisplayContext.getVersion() %>" />
	<aui:input name="version" type="hidden" value="<%= ((article == null) || article.isNew()) ? editJournalDisplayContext.getVersion() : article.getVersion() %>" />
	<aui:input name="articleURL" type="hidden" value="<%= editJournalDisplayContext.getEditArticleRenderURL() %>" />
	<aui:input name="changeStructure" type="hidden" />
	<aui:input name="ddmStructureId" type="hidden" />
	<aui:input name="ddmTemplateId" type="hidden" />
	<aui:input name="workflowAction" type="hidden" value="<%= String.valueOf(WorkflowConstants.ACTION_SAVE_DRAFT) %>" />

	<liferay-frontend:edit-form-content>
		<liferay-ui:error exception="<%= ArticleContentSizeException.class %>" message="you-have-exceeded-the-maximum-web-content-size-allowed" />
		<liferay-ui:error exception="<%= ArticleFriendlyURLException.class %>" message="you-must-define-a-friendly-url-for-default-language" />
		<liferay-ui:error exception="<%= DuplicateFileEntryException.class %>" message="a-file-with-that-name-already-exists" />

		<liferay-ui:error exception="<%= FileSizeException.class %>">

			<%
			long fileMaxSize = DLValidatorUtil.getMaxAllowableSize();
			%>

			<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(fileMaxSize, locale) %>" key="please-enter-a-file-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
		</liferay-ui:error>

		<liferay-ui:error exception="<%= LiferayFileItemException.class %>">
			<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(LiferayFileItem.THRESHOLD_SIZE, locale) %>" key="please-enter-valid-content-with-valid-content-size-no-larger-than-x" translateArguments="<%= false %>" />
		</liferay-ui:error>

		<c:if test="<%= (article != null) && !article.isNew() && !editJournalDisplayContext.isEditDefaultValues() %>">
			<liferay-frontend:info-bar>
				<aui:workflow-status id="<%= String.valueOf(article.getArticleId()) %>" markupView="lexicon" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= article.getStatus() %>" version="<%= String.valueOf(article.getVersion()) %>" />
			</liferay-frontend:info-bar>
		</c:if>

		<c:if test="<%= !editJournalDisplayContext.isEditDefaultValues() %>">
			<c:if test="<%= editJournalDisplayContext.isApproved() %>">
				<div class="alert alert-info">
					<liferay-ui:message key="a-new-version-is-created-automatically-if-this-content-is-modified" />
				</div>
			</c:if>

			<c:if test="<%= editJournalDisplayContext.isPending() %>">
				<div class="alert alert-info">
					<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
				</div>
			</c:if>
		</c:if>

		<liferay-frontend:form-navigator
			formModelBean="<%= article %>"
			id="<%= FormNavigatorConstants.FORM_NAVIGATOR_ID_JOURNAL %>"
			showButtons="<%= false %>"
		/>
	</liferay-frontend:edit-form-content>

	<liferay-frontend:edit-form-footer>
		<liferay-frontend:button-row
			cssClass="journal-article-button-row"
		>

			<%
			boolean hasSavePermission = false;

			if ((article != null) && !article.isNew()) {
				hasSavePermission = JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE);
			}
			else {
				hasSavePermission = JournalFolderPermission.contains(permissionChecker, editJournalDisplayContext.getGroupId(), editJournalDisplayContext.getFolderId(), ActionKeys.ADD_ARTICLE);
			}

			String saveButtonLabel = "save";

			if ((article == null) || article.isApproved() || article.isDraft() || article.isExpired() || article.isScheduled()) {
				saveButtonLabel = "save-as-draft";
			}

			String publishButtonLabel = "publish";

			if (editJournalDisplayContext.isWorkflowEnabled()) {
				publishButtonLabel = "submit-for-publication";
			}

			if (editJournalDisplayContext.isEditDefaultValues()) {
				publishButtonLabel = "save";
			}
			%>

			<c:if test="<%= hasSavePermission %>">
				<aui:button data-actionname="<%= Constants.PUBLISH %>" disabled="<%= editJournalDisplayContext.isPending() %>" name="publishButton" type="submit" value="<%= publishButtonLabel %>" />

				<c:if test="<%= !editJournalDisplayContext.isEditDefaultValues() %>">
					<aui:button data-actionname='<%= ((article == null) || Validator.isNull(article.getArticleId())) ? "addArticle" : "updateArticle" %>' name="saveButton" primary="<%= false %>" type="submit" value="<%= saveButtonLabel %>" />
				</c:if>
			</c:if>

			<aui:button href="<%= editJournalDisplayContext.getRedirect() %>" type="cancel" />
		</liferay-frontend:button-row>
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<aui:script use="liferay-portlet-journal">
	new Liferay.Portlet.Journal(
		{
			article: {
				editUrl: '<%= editJournalDisplayContext.getEditArticleURL() %>',
				id: '<%= (article != null) ? HtmlUtil.escape(editJournalDisplayContext.getArticleId()) : StringPool.BLANK %>',

				<c:if test="<%= (article != null) && !article.isNew() %>">
					previewUrl: '<%= HtmlUtil.escapeJS(editJournalDisplayContext.getPreviewArticleContentURL()) %>',
				</c:if>

				title: '<%= (article != null) ? HtmlUtil.escapeJS(article.getTitle(locale)) : StringPool.BLANK %>'
			},
			namespace: '<portlet:namespace />',
			'strings.addTemplate': '<liferay-ui:message key="please-add-a-template-to-render-this-structure" />',
			'strings.saveAsDraftBeforePreview': '<liferay-ui:message key="in-order-to-preview-your-changes,-the-web-content-is-saved-as-a-draft" />'
		}
	);
</aui:script>