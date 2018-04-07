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
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class EditJournalDisplayContext {

	public EditJournalDisplayContext(
		HttpServletRequest request, JournalArticle article) {

		_request = request;
		_article = article;
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

	public boolean isEditDefaultValues() {
		if (getClassNameId() > JournalArticleConstants.CLASSNAME_ID_DEFAULT) {
			return true;
		}

		return false;
	}

	private final JournalArticle _article;
	private Long _classNameId;
	private final HttpServletRequest _request;

}