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

package com.liferay.template.web.internal.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.comparator.TemplateHandlerComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import com.liferay.template.web.internal.constants.TemplatesWebKeys;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(immediate = true, service = {})
public class TemplatesUtil {

	public static JSONArray getMappingTypesJSONArray(
		String currentTab, Locale locale) {

		if (Objects.equals(TemplatesWebKeys.WIDGET_TEMPLATES, currentTab)) {
			JSONArray mappingSubtypesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (TemplateHandler templateHandler :
					_getTemplateHandlers(locale)) {

				mappingSubtypesJSONArray.put(
					JSONUtil.put(
						"classNameId",
						String.valueOf(
							_classNameLocalService.getClassNameId(
								templateHandler.getClassName()))
					).put(
						"label", templateHandler.getName(locale)
					));
			}

			return JSONUtil.put(
				JSONUtil.put(
					"classNameId", mappingSubtypesJSONArray
				).put(
					"classPK", String.valueOf(0)
				).put(
					"hiddenFields", "classPK,resourceClassNameId"
				).put(
					"label", LanguageUtil.get(locale, "widget-templates")
				).put(
					"resourceClassNameId",
					String.valueOf(
						_classNameLocalService.getClassNameId(
							PortletDisplayTemplate.class))
				).put(
					"selectField", "classNameId"
				));
		}

		return null;
	}

	@Deactivate
	protected void deactivate() {
		_classNameLocalService = null;
		_portletDisplayTemplate = null;
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortletDisplayTemplate(
		PortletDisplayTemplate portletDisplayTemplate) {

		_portletDisplayTemplate = portletDisplayTemplate;
	}

	private static List<TemplateHandler> _getTemplateHandlers(Locale locale) {
		List<TemplateHandler> templateHandlers =
			_portletDisplayTemplate.getPortletDisplayTemplateHandlers();

		ListUtil.sort(templateHandlers, new TemplateHandlerComparator(locale));

		return templateHandlers;
	}

	private static ClassNameLocalService _classNameLocalService;
	private static PortletDisplayTemplate _portletDisplayTemplate;

}