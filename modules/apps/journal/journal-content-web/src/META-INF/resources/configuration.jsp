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
JournalArticle article = journalContentDisplayContext.getArticle();

PortletRequestModel portletRequestModel = new PortletRequestModel(renderRequest, renderResponse);

JournalArticleDisplay articleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(article, null, null, LanguageUtil.getLanguageId(locale), 1, portletRequestModel, themeDisplay);

String ddmTemplateKey = journalContentDisplayContext.getDDMTemplateKey();
%>

<liferay-ui:error exception="<%= NoSuchArticleException.class %>" message="the-web-content-could-not-be-found" />

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" varImpl="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	<aui:input name="preferences--assetEntryId--" type="hidden" value="<%= journalContentDisplayContext.getAssetEntryId() %>" />
	<aui:input name="preferences--extensions--" type="hidden" value="<%= journalContentDisplayContext.getExtensions() %>" />
	<aui:input name="preferences--showAvailableLocales--" type="hidden" value="<%= journalContentDisplayContext.isShowAvailableLocales() %>" />
	<aui:input name="preferences--enablePrint--" type="hidden" value="<%= journalContentDisplayContext.isEnablePrint() %>" />
	<aui:input name="preferences--enableViewCountIncrement--" type="hidden" value="<%= journalContentDisplayContext.isEnableViewCountIncrement() %>" />

	<div class="content-display">
		<div class="article">
			<label class="title"><liferay-ui:message key="displaying-content" />:</label>
			<span class="change-content"><%= (article != null) ? article.getTitle(locale) : StringPool.BLANK %> <aui:button name="webContentSelector" value="change" /></span>
		</div>

		<%
		List<DDMTemplate> ddmTemplates = journalContentDisplayContext.getDDMTemplates();
		%>

		<label class="title"><liferay-ui:message key="template" />:</label>

		<div class="template">
			<c:choose>
				<c:when test="<%= ddmTemplates.size() > 1 %>">
					<aui:select label="" name="preferences--ddmTemplateKey--">

						<%
						for (DDMTemplate ddmTemplate : ddmTemplates) {
						%>

							<aui:option label="<%= ddmTemplate.getName(locale) %>" selected="<%= ddmTemplateKey.equals(ddmTemplate.getTemplateKey()) %>" value="<%= ddmTemplate.getTemplateKey() %>" />

						<%
						}
						%>

					</aui:select>
				</c:when>
				<c:otherwise>

					<%
					DDMTemplate ddmTemplate = article.getDDMTemplate();
					%>

					<span><%= ddmTemplate.getName(locale) %></span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="panel">
		<div class="tools">
			<label><liferay-ui:message key="conversion-and-user-tools" /> <i class="icon-cog" id="<portlet:namespace />conversionAndUserToolsButton"></i></label>
			<div class="tools-container" id="<portlet:namespace />toolsContainer">
				<div class="available-locales <%= journalContentDisplayContext.isShowAvailableLocales() ? StringPool.BLANK : "hide" %>">
					<liferay-ui:language displayStyle="<%= 0 %>" languageId="<%= LanguageUtil.getLanguageId(request) %>" languageIds="<%= articleDisplay.getAvailableLocales() %>" />
				</div>
				<div class="enable-print <%= journalContentDisplayContext.isEnablePrint() ? StringPool.BLANK : "hide" %>">
					<liferay-ui:icon
						iconCssClass="icon-print"
						label="<%= true %>"
						message='<%= LanguageUtil.format(request, "print-x-x", new Object[] {"hide-accessible", HtmlUtil.escape(articleDisplay.getTitle())}, false) %>'
					/>
				</div>
				<div class="export-actions <%= (journalContentDisplayContext.getExtensions().length > 0) ? StringPool.BLANK : "hide" %>">
					<liferay-ui:icon-list>

						<%
						for (String extension : journalContentDisplayContext.getExtensions()) {
						%>

							<liferay-ui:icon
								iconCssClass="<%= DLUtil.getFileIconCssClass(extension) %>"
								label="<%= true %>"
								message='<%= LanguageUtil.format(request, "x-convert-x-to-x", new Object[] {"hide-accessible", HtmlUtil.escape(articleDisplay.getTitle()), StringUtil.toUpperCase(HtmlUtil.escape(extension))}) %>'
							/>

						<%
						}
						%>

					</liferay-ui:icon-list>
				</div>

				<%
				String cssClass = StringPool.BLANK;

				if (journalContentDisplayContext.isShowAvailableLocales() || journalContentDisplayContext.isEnablePrint() || (journalContentDisplayContext.getExtensions().length > 0)) {
					cssClass = "hide";
				}
				%>

				<span class="message <%= cssClass %>"><liferay-ui:message key="this-is-not-active-click-in-the-cog-and-choose-your-options" /></span>
			</div>
		</div>
		<div class="article">
			<div class="image">

				<%
				String imageURL = article.getArticleImageURL(themeDisplay);

				if (Validator.isNull(imageURL)) {
					imageURL = themeDisplay.getPathThemeImages() + "/file_system/large/article.png";
				}
				%>

				<img alt="<%= article.getTitle(locale) %>" src="<%= imageURL %>">
			</div>
			<div class="details">
				<div class="title">
					<%= article.getTitle(locale) %>
				</div>
				<div class="content">

					<%
					String content = article.getDescription(locale);

					if (Validator.isNull(content)) {
						content = articleDisplay.getContent();
					}
					%>

					<%= HtmlUtil.escape(StringUtil.shorten(content, 320)) %>
				</div>
				<div class="author">
					<liferay-ui:message key="created-by" /> <strong><%= article.getUserName() %></strong> <liferay-ui:message key="<%= Time.getRelativeTimeDescription(article.getCreateDate(), locale, timeZone) %>" />
				</div>
			</div>
		</div>
		<div class="metadata">
			<label><liferay-ui:message key="content-metadata" /> <i class="icon-cog"></i></label>
			<div class="metadata-container">
				<span class="message"><liferay-ui:message key="this-is-not-active-click-in-the-cog-and-choose-your-options" /></span>
			</div>
		</div>
	</div>

	<div class="hide">
		<div class="configuration-container" id="<portlet:namespace />conversionAndUserToolsContainer">
			<div class="conversions">
				<label class="title"><liferay-ui:message key="conversions" /></label>

				<%
				String[] extensions = journalContentDisplayContext.getExtensions();

				for (String conversion : journalContentDisplayContext.getConversions()) {
				%>

					<aui:input checked="<%= ArrayUtil.contains(extensions, conversion) %>" inlineField="<%= true %>" name="<%= conversion %>" type="checkbox" wrapperCssClass="conversion" />

				<%
				}
				%>

			</div>

			<div class="user-tools">
				<label class="title"><liferay-ui:message key="user-tools" /></label>

				<aui:input name="showAvailableLocales" type="checkbox" value="<%= journalContentDisplayContext.isShowAvailableLocales() %>" />

				<aui:input name="enablePrint" type="checkbox" value="<%= journalContentDisplayContext.isEnablePrint() %>" />

				<aui:input name="enableViewCountIncrement" type="checkbox" value="<%= journalContentDisplayContext.isEnableViewCountIncrement() %>" />
			</div>
		</div>
	</div>

	<aui:button-row>
		<aui:button name="saveButton" type="submit" />

		<aui:button type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script sandbox="<%= true %>">
	var form = AUI.$(document.<portlet:namespace />fm);

	$('#<portlet:namespace />webContentSelector').on(
		'click',
		function(event) {
			event.preventDefault();

			<%
			String portletId = PortletProviderUtil.getPortletId(JournalArticle.class.getName(), PortletProvider.Action.BROWSE);
			%>

			<liferay-portlet:renderURL plid="<%= PortalUtil.getControlPanelPlid(company.getCompanyId()) %>" portletName="<%= portletId %>" var="selectWebContentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
				<portlet:param name="selectedGroupIds" value="<%= StringUtil.merge(PortalUtil.getSharedContentSiteGroupIds(company.getCompanyId(), scopeGroupId, user.getUserId())) %>" />
				<portlet:param name="typeSelection" value="<%= JournalArticle.class.getName() %>" />
				<portlet:param name="eventName" value="selectContent" />
			</liferay-portlet:renderURL>

			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true,
						modal: true
					},
					eventName: 'selectContent',
					id: 'selectContent',
					title: '<liferay-ui:message key="select-web-content" />',
					uri: '<%= selectWebContentURL %>'
				},
				function(event) {
					form.fm('assetEntryId').val(event.assetentryid);
					form.fm('ddmTemplateKey').val('');

					$('.displaying-article-id-holder').removeClass('hide');
					$('.displaying-help-message-holder').addClass('hide');

					var displayArticleId = $('.displaying-article-id');

					displayArticleId.html(event.assettitle + ' (<liferay-ui:message key="modified" />)');

					displayArticleId.addClass('modified');
				}
			);
		}
	);

	$('#<portlet:namespace />conversionAndUserToolsButton').on(
		'click',
		function(event) {
			var conversionAndUserToolsDialog = Liferay.Util.Window.getWindow(
				{
					dialog: {
						bodyContent: $('#<portlet:namespace />conversionAndUserToolsContainer'),
						centered: true,
						modal: true,
						toolbars: {
							footer: [
								{
									label: '<liferay-ui:message key="ok" />',
									on: {
										click: function(event) {
											event.domEvent.preventDefault();

											var toolsContainer = $('#<portlet:namespace />toolsContainer');

											var showAvailableLocales = $('#<portlet:namespace />showAvailableLocales');

											document.<portlet:namespace />fm.<portlet:namespace />preferences--showAvailableLocales--.value = showAvailableLocales;

											var enablePrint = $('#<portlet:namespace />enablePrint');

											var enableViewCountIncrement = $('#<portlet:namespace />showAvailableLocales');

											conversionAndUserToolsDialog.hide();
										}
									},
									primary: true
								},
								{
									label: '<liferay-ui:message key="cancel" />',
									on: {
										click: function(event) {
											event.domEvent.preventDefault();

											conversionAndUserToolsDialog.hide();
										}
									}
								}
							]
						},
						width: 400
					},
					title: '<liferay-ui:message key="conversion-and-user-tools" />'
				}
			);

			conversionAndUserToolsDialog.show();
		}
	);
</aui:script>