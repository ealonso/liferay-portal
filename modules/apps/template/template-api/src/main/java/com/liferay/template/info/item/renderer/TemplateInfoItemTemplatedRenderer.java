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

import java.util.List;
import java.util.Locale;

/**
 * @author Lourdes Fernández Besada
 */
public interface TemplateInfoItemTemplatedRenderer {

	public default List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String className, Locale locale) {

		return getInfoItemRendererTemplates(className, 0, locale);
	}

	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String className, long classPK, Locale locale);

	public String getInfoItemRendererTemplatesGroupLabel(
		String className, String classTypeKey, Locale locale);

	public String getLabel(Locale locale);

}