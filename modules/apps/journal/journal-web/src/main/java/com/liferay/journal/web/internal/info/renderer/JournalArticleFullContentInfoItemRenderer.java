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

package com.liferay.journal.web.internal.info.renderer;

import com.liferay.info.renderer.InfoItemRenderer;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleDisplay;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalContent;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletRequestModel;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = InfoItemRenderer.class)
public class JournalArticleFullContentInfoItemRenderer
	implements InfoItemRenderer<JournalArticle> {

	@Override
	public void render(
		JournalArticle article, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		httpServletRequest.setAttribute(
			WebKeys.JOURNAL_ARTICLE_DISPLAY,
			_getArticleDisplay(article, httpServletRequest));

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/asset/full_content.jsp");

		try {
			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private JournalArticleDisplay _getArticleDisplay(
		JournalArticle article, HttpServletRequest httpServletRequest) {

		boolean workflowAssetPreview = GetterUtil.getBoolean(
			httpServletRequest.getAttribute(WebKeys.WORKFLOW_ASSET_PREVIEW));

		String ddmTemplateKey = (String)httpServletRequest.getAttribute(
			WebKeys.JOURNAL_TEMPLATE_ID);

		if (Validator.isNull(ddmTemplateKey)) {
			ddmTemplateKey = ParamUtil.getString(
				httpServletRequest, "ddmTemplateKey");
		}

		String viewMode = ParamUtil.getString(
			httpServletRequest, "viewMode", Constants.VIEW);
		String languageId = LanguageUtil.getLanguageId(httpServletRequest);
		int articlePage = ParamUtil.getInteger(httpServletRequest, "page", 1);
		PortletRequestModel portletRequestModel = _getPortletRequestModel(
			httpServletRequest);
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		if (!workflowAssetPreview && article.isApproved()) {
			return _journalContent.getDisplay(
				article.getGroupId(), article.getArticleId(),
				article.getVersion(), ddmTemplateKey, viewMode, languageId,
				articlePage, portletRequestModel, themeDisplay);
		}

		try {
			return _journalArticleLocalService.getArticleDisplay(
				article, ddmTemplateKey, viewMode, languageId, articlePage,
				portletRequestModel, themeDisplay);
		}
		catch (Exception e) {
		}

		return null;
	}

	private PortletRequestModel _getPortletRequestModel(
		HttpServletRequest httpServletRequest) {

		PortletRequest portletRequest =
			(PortletRequest)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_REQUEST);
		PortletResponse portletResponse =
			(PortletResponse)httpServletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_RESPONSE);

		if ((portletRequest == null) || (portletResponse == null)) {
			return null;
		}

		return new PortletRequestModel(portletRequest, portletResponse);
	}

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalContent _journalContent;

	private ServletContext _servletContext;

}