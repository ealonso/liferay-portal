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

package com.liferay.journal.web.internal.display.context;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditJournalDisplayContext {

	public EditJournalDisplayContext(
		HttpServletRequest request, RenderResponse renderResponse,
		JournalArticle article) {

		_request = request;
		_renderResponse = renderResponse;
		_article = article;

		boolean showHeader = ParamUtil.getBoolean(request, "showHeader", true);

		if (!showHeader) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		portletDisplay.setShowBackIcon(true);
		portletDisplay.setURLBack(_getBackURL());

		_renderResponse.setTitle(_getTitle());
	}

	public long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		_classNameId = BeanParamUtil.getLong(_article, _request, "classNameId");

		return _classNameId;
	}

	public String getDefaultLanguageId() {
		String defaultLanguageId = LocaleUtil.toLanguageId(
			LocaleUtil.getSiteDefault());

		if (_article == null) {
			return defaultLanguageId;
		}

		return LocalizationUtil.getDefaultLanguageId(
			_article.getContent(), LocaleUtil.getSiteDefault());
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_request, "redirect");

		return _redirect;
	}

	public boolean isEditDefaultValues() {
		if (getClassNameId() > JournalArticleConstants.CLASSNAME_ID_DEFAULT) {
			return true;
		}

		return false;
	}

	private String _getBackURL() {
		if (Validator.isNotNull(getRedirect())) {
			return getRedirect();
		}

		if (!isEditDefaultValues() && (_article != null)) {
			PortletURL backURL = _renderResponse.createRenderURL();

			backURL.setParameter(
				"groupId", String.valueOf(_article.getGroupId()));
			backURL.setParameter(
				"folderId", String.valueOf(_article.getFolderId()));

			return backURL.toString();
		}

		return StringPool.BLANK;
	}

	private String _getTitle() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (isEditDefaultValues()) {
			return LanguageUtil.get(_request, "structure-default-values");
		}
		else if ((_article != null) && !_article.isNew()) {
			return _article.getTitle(themeDisplay.getLocale());
		}

		return LanguageUtil.get(_request, "new-web-content");
	}

	private final JournalArticle _article;
	private Long _classNameId;
	private String _redirect;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;

}