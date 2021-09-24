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

package com.liferay.blogs.web.internal.info.item.renderer;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemTemplatedRenderer;
import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.template.info.item.renderer.TemplateInfoItemTemplatedRenderer;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

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
public class BlogsEntryTemplateInfoItemTemplatedRenderer
	implements InfoItemTemplatedRenderer<BlogsEntry> {

	@Override
	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		BlogsEntry blogsEntry, Locale locale) {

		return _templateInfoItemTemplatedRenderer.getInfoItemRendererTemplates(
			BlogsEntry.class.getName(), locale);
	}

	@Override
	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String className, String classTypeKey, Locale locale) {

		if (!Objects.equals(className, BlogsEntry.class.getName())) {
			return Collections.emptyList();
		}

		return _templateInfoItemTemplatedRenderer.getInfoItemRendererTemplates(
			BlogsEntry.class.getName(), classTypeKey, locale);
	}

	@Override
	public String getInfoItemRendererTemplatesGroupLabel(
		BlogsEntry blogsEntry, Locale locale) {

		return _templateInfoItemTemplatedRenderer.
			getInfoItemRendererTemplatesGroupLabel(
				BlogsEntry.class.getName(), locale);
	}

	@Override
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
		BlogsEntry blogsEntry, String templateKey,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		if (Validator.isNull(templateKey)) {
			render(blogsEntry, httpServletRequest, httpServletResponse);

			return;
		}

		_templateInfoItemTemplatedRenderer.renderTemplate(
			BlogsEntry.class.getName(), blogsEntry, templateKey,
			httpServletRequest, httpServletResponse);
	}

	@Reference
	private TemplateInfoItemTemplatedRenderer
		_templateInfoItemTemplatedRenderer;

}