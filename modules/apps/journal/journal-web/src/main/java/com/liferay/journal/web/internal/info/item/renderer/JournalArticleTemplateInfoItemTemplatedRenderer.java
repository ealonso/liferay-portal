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

package com.liferay.journal.web.internal.info.item.renderer;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemTemplatedRenderer;
import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.template.info.item.renderer.TemplateInfoItemTemplatedRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	property = "service.ranking:Integer=100", service = InfoItemRenderer.class
)
public class JournalArticleTemplateInfoItemTemplatedRenderer
	implements InfoItemTemplatedRenderer<JournalArticle> {

	@Override
	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		JournalArticle journalArticle, Locale locale) {

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		return _getInfoItemRendererTemplates(
			locale, ddmStructure.getTemplates());
	}

	@Override
	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String className, String classTypeKey, Locale locale) {

		if (!Objects.equals(className, JournalArticle.class.getName())) {
			return Collections.emptyList();
		}

		return _getInfoItemRendererTemplates(
			locale,
			_ddmTemplateLocalService.getTemplates(
				GetterUtil.getLong(classTypeKey)));
	}

	@Override
	public String getInfoItemRendererTemplatesGroupLabel(
		JournalArticle journalArticle, Locale locale) {

		DDMStructure ddmStructure = journalArticle.getDDMStructure();

		return ddmStructure.getName(locale);
	}

	public String getInfoItemRendererTemplatesGroupLabel(
		String className, String classTypeKey, Locale locale) {

		return _templateInfoItemTemplatedRenderer.
			getInfoItemRendererTemplatesGroupLabel(
				className, classTypeKey, locale);
	}

	@Override
	public String getLabel(Locale locale) {
		return _templateInfoItemTemplatedRenderer.getLabel(locale);
	}

	@Override
	public void render(
		JournalArticle journalArticle, String templateKey,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		if (Validator.isNull(templateKey)) {
			render(journalArticle, httpServletRequest, httpServletResponse);

			return;
		}

		_templateInfoItemTemplatedRenderer.renderTemplate(
			JournalArticle.class.getName(), journalArticle, templateKey,
			httpServletRequest, httpServletResponse);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private List<InfoItemRendererTemplate> _getInfoItemRendererTemplates(
		Locale locale, List<DDMTemplate> templates) {

		List<InfoItemRendererTemplate> infoItemRendererTemplates =
			new ArrayList<>();

		for (DDMTemplate ddmTemplate : templates) {
			if (_stagingGroupHelper.isLiveGroup(ddmTemplate.getGroupId()) ||
				(!Objects.equals(
					ddmTemplate.getClassNameId(),
					_portal.getClassNameId(DDMStructure.class.getName())) &&
				 !Objects.equals(
					 ddmTemplate.getClassNameId(),
					 _portal.getClassNameId(JournalArticle.class.getName())))) {

				continue;
			}

			infoItemRendererTemplates.add(
				new InfoItemRendererTemplate(
					ddmTemplate.getName(locale), ddmTemplate.getTemplateKey()));
		}

		return infoItemRendererTemplates;
	}

	private void _renderJournalTemplate(
		JournalArticle article, String templateKey,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			httpServletRequest.setAttribute(WebKeys.JOURNAL_ARTICLE, article);
			httpServletRequest.setAttribute(
				WebKeys.JOURNAL_TEMPLATE_ID, templateKey);

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(
					"/info/item/renderer/ddm_template_article_renderer.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private Portal _portal;

	private ServletContext _servletContext;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

	@Reference
	private TemplateInfoItemTemplatedRenderer
		_templateInfoItemTemplatedRenderer;

}