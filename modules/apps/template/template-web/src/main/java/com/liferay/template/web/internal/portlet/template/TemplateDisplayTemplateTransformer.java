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

package com.liferay.template.web.internal.portlet.template;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.info.field.InfoField;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.templateparser.Transformer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Lourdes Fernández Besada
 */
public class TemplateDisplayTemplateTransformer {

	public TemplateDisplayTemplateTransformer(
		DDMTemplate ddmTemplate, InfoItemFieldValues infoItemFieldValues) {

		_ddmTemplate = ddmTemplate;
		_infoItemFieldValues = infoItemFieldValues;
	}

	public String transform(Locale locale) throws Exception {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		Transformer transformer = TransformerHolder.getTransformer();

		Map<String, Object> contextObjects = new HashMap<>();

		for (InfoFieldValue<Object> infoFieldValue :
				_infoItemFieldValues.getInfoFieldValues()) {

			InfoField infoField = infoFieldValue.getInfoField();

			contextObjects.put(
				infoField.getName(), infoFieldValue.getValue(locale));
		}

		contextObjects.put(
			TemplateConstants.CLASS_NAME_ID, _ddmTemplate.getClassNameId());

		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(
				InfoItemFormProvider.class.getName());

		contextObjects.putAll(templateHandler.getCustomContextObjects());

		return transformer.transform(
			themeDisplay, contextObjects, _ddmTemplate.getScript(),
			_ddmTemplate.getLanguage(), new UnsyncStringWriter(),
			themeDisplay.getRequest(), themeDisplay.getResponse());
	}

	private final DDMTemplate _ddmTemplate;
	private final InfoItemFieldValues _infoItemFieldValues;

	private static class TransformerHolder {

		public static Transformer getTransformer() {
			return _transformer;
		}

		private static final Transformer _transformer = new Transformer(
			PropsKeys.PORTLET_DISPLAY_TEMPLATES_ERROR, true);

	}

}