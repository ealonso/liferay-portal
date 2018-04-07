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

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateServiceUtil;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.web.util.JournalUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

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

	public String getArticleId() {
		if (_articleId != null) {
			return _articleId;
		}

		_articleId = BeanParamUtil.getString(_article, _request, "articleId");

		return _articleId;
	}

	public long getClassNameId() {
		if (_classNameId != null) {
			return _classNameId;
		}

		_classNameId = BeanParamUtil.getLong(_article, _request, "classNameId");

		return _classNameId;
	}

	public DDMStructure getDDMStructure() {
		if (_ddmStructure != null) {
			return _ddmStructure;
		}

		long ddmStructureId = ParamUtil.getLong(_request, "ddmStructureId");

		if (ddmStructureId > 0) {
			_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
				ddmStructureId);
		}
		else if (Validator.isNotNull(getDDMStructureKey())) {
			ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
				WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getSiteGroupId();

			if (_article != null) {
				groupId = _article.getGroupId();
			}

			_ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(
				groupId, PortalUtil.getClassNameId(JournalArticle.class),
				getDDMStructureKey(), true);
		}

		return _ddmStructure;
	}

	public String getDDMStructureKey() {
		if (_ddmStructureKey != null) {
			return _ddmStructureKey;
		}

		_ddmStructureKey = ParamUtil.getString(_request, "ddmStructureKey");

		if (Validator.isNull(_ddmStructureKey) && (_article != null)) {
			_ddmStructureKey = _article.getDDMStructureKey();
		}

		return _ddmStructureKey;
	}

	public DDMTemplate getDDMTemplate() throws PortalException {
		if (_ddmTemplate != null) {
			return _ddmTemplate;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ddmTemplateId = ParamUtil.getLong(_request, "ddmTemplateId");

		if (ddmTemplateId > 0) {
			_ddmTemplate = DDMTemplateLocalServiceUtil.fetchDDMTemplate(
				ddmTemplateId);
		}
		else if (Validator.isNotNull(getDDMTemplateKey())) {
			long groupId = themeDisplay.getSiteGroupId();

			if (_article != null) {
				groupId = _article.getGroupId();
			}

			_ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
				groupId, PortalUtil.getClassNameId(DDMStructure.class),
				getDDMTemplateKey(), true);
		}

		if (_ddmTemplate == null) {
			DDMStructure ddmStructure = getDDMStructure();

			List<DDMTemplate> ddmTemplates =
				DDMTemplateServiceUtil.getTemplates(
					themeDisplay.getCompanyId(), ddmStructure.getGroupId(),
					PortalUtil.getClassNameId(DDMStructure.class),
					ddmStructure.getStructureId(),
					PortalUtil.getClassNameId(JournalArticle.class), true,
					WorkflowConstants.STATUS_APPROVED);

			if (!ddmTemplates.isEmpty()) {
				_ddmTemplate = ddmTemplates.get(0);
			}
		}

		return _ddmTemplate;
	}

	public String getDDMTemplateKey() {
		if (_ddmTemplateKey != null) {
			return _ddmTemplateKey;
		}

		_ddmTemplateKey = ParamUtil.getString(_request, "ddmTemplateKey");

		if (Validator.isNull(_ddmTemplateKey) && (_article != null) &&
			Objects.equals(
				_article.getDDMStructureKey(), getDDMStructureKey())) {

			_ddmTemplateKey = _article.getDDMTemplateKey();
		}

		return _ddmTemplateKey;
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

	public String getEditArticleActionURL() throws Exception {
		PortletURL editArticleActionURL = _renderResponse.createActionURL();

		editArticleActionURL.setParameter("mvcPath", "/edit_article.jsp");
		editArticleActionURL.setParameter(
			"ddmStructureKey", getDDMStructureKey());

		editArticleActionURL.setWindowState(WindowState.MAXIMIZED);

		return editArticleActionURL.toString();
	}

	public String getEditArticleRenderURL() throws Exception {
		PortletURL editArticleActionURL = _renderResponse.createRenderURL();

		editArticleActionURL.setParameter("mvcPath", "/edit_article.jsp");

		editArticleActionURL.setWindowState(WindowState.MAXIMIZED);

		return editArticleActionURL.toString();
	}

	public String getEditArticleURL() {
		PortletURL editArticleURL = _renderResponse.createRenderURL();

		editArticleURL.setParameter("redirect", getRedirect());
		editArticleURL.setParameter("mvcPath", "/edit_article.jsp");
		editArticleURL.setParameter("groupId", String.valueOf(getGroupId()));
		editArticleURL.setParameter("articleId", getArticleId());
		editArticleURL.setParameter("version", String.valueOf(getVersion()));

		return editArticleURL.toString();
	}

	public long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		_groupId = BeanParamUtil.getLong(
			_article, _request, "groupId", themeDisplay.getScopeGroupId());

		return _groupId;
	}

	public String getPreviewArticleContentURL() throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		LiferayPortletURL previewArticleContentURL =
			PortletURLFactoryUtil.create(
				_request, JournalPortletKeys.JOURNAL,
				PortletRequest.RENDER_PHASE);

		previewArticleContentURL.setParameter(
			"mvcPath", "/preview_article_content.jsp");

		if (_article != null) {
			DDMTemplate ddmTemplate = getDDMTemplate();

			previewArticleContentURL.setParameter(
				"groupId", String.valueOf(getGroupId()));
			previewArticleContentURL.setParameter("articleId", getArticleId());
			previewArticleContentURL.setParameter(
				"version", String.valueOf(getVersion()));

			if (ddmTemplate != null) {
				previewArticleContentURL.setParameter(
					"ddmTemplateKey", ddmTemplate.getTemplateKey());
			}
			else {
				previewArticleContentURL.setParameter(
					"ddmTemplateKey", _article.getDDMTemplateKey());
			}
		}

		previewArticleContentURL.setWindowState(LiferayWindowState.POP_UP);

		previewArticleContentURL.setPlid(
			JournalUtil.getPreviewPlid(_article, themeDisplay));

		return previewArticleContentURL.toString();
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(_request, "redirect");

		return _redirect;
	}

	public double getVersion() {
		if (_version != null) {
			return _version;
		}

		_version = BeanParamUtil.getDouble(
			_article, _request, "version",
			JournalArticleConstants.VERSION_DEFAULT);

		return _version;
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
	private String _articleId;
	private Long _classNameId;
	private DDMStructure _ddmStructure;
	private String _ddmStructureKey;
	private DDMTemplate _ddmTemplate;
	private String _ddmTemplateKey;
	private Long _groupId;
	private String _redirect;
	private final RenderResponse _renderResponse;
	private final HttpServletRequest _request;
	private Double _version;

}