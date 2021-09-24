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

package com.liferay.template.info.item.renderer;

import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.petra.string.StringPool;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Lourdes Fernández Besada
 */
public interface TemplateInfoItemTemplatedRenderer {

	public default List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String infoItemClassName, Locale locale) {

		return getInfoItemRendererTemplates(
			infoItemClassName, StringPool.BLANK, locale);
	}

	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String infoItemClassName, String infoItemFormVariationKey,
		Locale locale);

	public default String getInfoItemRendererTemplatesGroupLabel(
		String infoItemClassName, Locale locale) {

		return getInfoItemRendererTemplatesGroupLabel(
			infoItemClassName, StringPool.BLANK, locale);
	}

	public String getInfoItemRendererTemplatesGroupLabel(
		String infoItemClassName, String infoItemFormVariationKey,
		Locale locale);

	public String getLabel(Locale locale);

	public void renderTemplate(
		String infoItemClassName, Object itemObject, String templateKey,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse);

}