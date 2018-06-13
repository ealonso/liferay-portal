<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil" %>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.LocalizationUtil" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedHashSet" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %><%--
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

<%@ include file="/html/init.jsp" %>

<div class="lfr-ddm-container" id="<%= randomNamespace %>">
	<c:if test="<%= ddmForm != null %>">
		<div class="input-group-item input-group-item-shrink input-localized-content" role="menu">

			<%
            List<String> languageIds = new ArrayList<String>();

            Locale defaultLocale = LocaleUtil.getSiteDefault();
            String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

            languageIds.add(defaultLanguageId);

            Set<Locale> availableLocales = LanguageUtil.getAvailableLocales(themeDisplay.getSiteGroupId());

			String normalizedDefaultLanguageId = StringUtil.replace(defaultLanguageId, '_', '-');
			%>

			<liferay-ui:icon-menu direction="left-side" id="Menu" icon="<%= StringUtil.toLowerCase(normalizedDefaultLanguageId) %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>" triggerCssClass="input-localized-trigger" triggerLabel="<%= normalizedDefaultLanguageId %>" triggerType="button">
				<div id="<portlet:namespace />PaletteBoundingBox">
					<div class="input-localized-palette-container palette-container" id="<portlet:namespace />PaletteContentBox">

						<%
						LinkedHashSet<String> uniqueLanguageIds = new LinkedHashSet<String>();

						uniqueLanguageIds.add(defaultLanguageId);

						for (Locale availableLocale : availableLocales) {
							String curLanguageId = LocaleUtil.toLanguageId(availableLocale);

							uniqueLanguageIds.add(curLanguageId);
						}

						int index = 0;

						for (String curLanguageId : uniqueLanguageIds) {
							String linkCssClass = "dropdown-item palette-item";

							Locale curLocale = LocaleUtil.fromLanguageId(curLanguageId);

							String title = HtmlUtil.escapeAttribute(curLocale.getDisplayName(LocaleUtil.fromLanguageId(LanguageUtil.getLanguageId(request)))) + " " + LanguageUtil.get(LocaleUtil.getDefault(), "translation");

							Map<String, Object> data = new HashMap<String, Object>();

							data.put("languageid", curLanguageId);

							Map<String, Object> iconData = new HashMap<>();
							iconData.put("index", index++);
							iconData.put("languageid", curLanguageId);
							iconData.put("value", curLanguageId);

							String translationStatus = LanguageUtil.get(request, "untranslated");
							String translationStatusCssClass = "warning";

							if (languageIds.contains(curLanguageId)) {
								translationStatus = LanguageUtil.get(request, "translated");
								translationStatusCssClass = "success";
							}

							if (defaultLanguageId.equals(curLanguageId)) {
								translationStatus = LanguageUtil.get(request, "default");
								translationStatusCssClass = "info";
							}
						%>

							<liferay-util:buffer
								var="linkContent"
							>
								<%= StringUtil.replace(curLanguageId, '_', '-') %>

								<span class="label label-<%= translationStatusCssClass %>"><%= translationStatus %></span>
							</liferay-util:buffer>

							<liferay-ui:icon
								alt="<%= title %>"
								data="<%= iconData %>"
								icon="<%= StringUtil.toLowerCase(StringUtil.replace(curLanguageId, '_', '-')) %>"
								iconCssClass="inline-item inline-item-before"
								linkCssClass="<%= linkCssClass %>"
								markupView="lexicon"
								message="<%= linkContent %>"
								url="javascript:;"
							>
							</liferay-ui:icon>

						<%
						}
						%>

					</div>
				</div>
			</liferay-ui:icon-menu>
		</div>

		<%
		request.setAttribute("checkRequired", checkRequired);

		DDMFormFieldRenderingContext ddmFormFieldRenderingContext = new DDMFormFieldRenderingContext();

		ddmFormFieldRenderingContext.setFields(fields);
		ddmFormFieldRenderingContext.setHttpServletRequest(request);
		ddmFormFieldRenderingContext.setHttpServletResponse(response);
		ddmFormFieldRenderingContext.setLocale(requestedLocale);
		ddmFormFieldRenderingContext.setMode(mode);
		ddmFormFieldRenderingContext.setNamespace(fieldsNamespace);
		ddmFormFieldRenderingContext.setPortletNamespace(portletResponse.getNamespace());
		ddmFormFieldRenderingContext.setReadOnly(readOnly);
		ddmFormFieldRenderingContext.setShowEmptyFieldLabel(showEmptyFieldLabel);
		%>

		<%= DDMFormRendererUtil.render(ddmForm, ddmFormFieldRenderingContext) %>

		<aui:input name="<%= HtmlUtil.getAUICompatibleId(ddmFormValuesInputName) %>" type="hidden" />

		<aui:script use="liferay-ddm-form">
			var liferayDDMForm = new Liferay.DDM.Form(
				{
					container: '#<%= randomNamespace %>',
					ddmFormValuesInput: '#<portlet:namespace /><%= HtmlUtil.getAUICompatibleId(ddmFormValuesInputName) %>',
					definition: <%= DDMUtil.getDDMFormJSONString(ddmForm) %>,
					doAsGroupId: <%= scopeGroupId %>,
					fieldsNamespace: '<%= HtmlUtil.escapeJS(fieldsNamespace) %>',
					mode: '<%= HtmlUtil.escapeJS(mode) %>',
					p_l_id: <%= themeDisplay.getPlid() %>,
					portletNamespace: '<portlet:namespace />',
					repeatable: <%= repeatable %>,
					requestedLocale: '<%= (requestedLocale == null) ? StringPool.BLANK : HtmlUtil.escapeJS(requestedLocale.toString()) %>'

					<c:if test="<%= ddmFormValues != null %>">
						, values: <%= DDMUtil.getDDMFormValuesJSONString(ddmFormValues) %>
					</c:if>
				}
			);

			var onDestroyPortlet = function(event) {
				if (event.portletId === '<%= portletDisplay.getId() %>') {
					liferayDDMForm.destroy();

					Liferay.detach('destroyPortlet', onDestroyPortlet);
				}
			};

			Liferay.on('destroyPortlet', onDestroyPortlet);
		</aui:script>
	</c:if>