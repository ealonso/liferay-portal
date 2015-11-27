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

package com.liferay.journal.web.portlet.configuration.icon;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.web.portlet.action.ActionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class PreviewPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public PreviewPortletConfigurationIcon(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getMessage() {
		return "preview";
	}

	@Override
	public String getURL() {
		try {
			JournalArticle article = getArticle();

			PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
				request, JournalPortletKeys.JOURNAL,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/preview_article_content.jsp");
			portletURL.setParameter(
				"groupId", String.valueOf(article.getGroupId()));
			portletURL.setParameter("articleId", article.getArticleId());
			portletURL.setParameter(
				"version", String.valueOf(article.getVersion()));
			portletURL.setParameter(
				"ddmTemplateKey", article.getDDMTemplateKey());

			try {
				portletURL.setWindowState(LiferayWindowState.POP_UP);
			}
			catch (WindowStateException wse) {
			}

			return portletURL.toString();
		}
		catch (PortalException e) {
		}

		return StringPool.BLANK;
	}

	@Override
	public boolean isShow() {
		try {
			JournalArticle article = getArticle();

			if ((article != null) && !article.isNew()) {
				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isToolTip() {
		return false;
	}

	@Override
	public boolean isUseDialog() {
		return true;
	}

	protected JournalArticle getArticle() throws PortalException {
		return ActionUtil.getArticle(request);
	}

}