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

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.InfoItemClassDetails;
import com.liferay.info.item.InfoItemFormVariation;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.item.provider.InfoItemFormVariationsProvider;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.template.comparator.TemplateHandlerComparator;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portlet.display.template.PortletDisplayTemplate;
import com.liferay.template.web.internal.constants.TemplatesWebKeys;
import com.liferay.template.web.internal.info.item.capability.TemplateInfoItemCapability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(immediate = true, service = {})
public class TemplatesUtil {

	public static JSONArray getMappingTypesJSONArray(
		String currentTab, long groupId, Locale locale) {

		if (Objects.equals(TemplatesWebKeys.WIDGET_TEMPLATES, currentTab)) {
			JSONArray mappingSubtypesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (TemplateHandler templateHandler :
					_getTemplateHandlers(locale)) {

				mappingSubtypesJSONArray.put(
					JSONUtil.put(
						"label", templateHandler.getName(locale)
					).put(
						"value",
						String.valueOf(
							_classNameLocalService.getClassNameId(
								templateHandler.getClassName()))
					));
			}

			return JSONUtil.put(
				JSONUtil.put(
					"hiddenFields",
					JSONUtil.put(
						"classNameId", "subtype"
					).put(
						"classPK", String.valueOf(0)
					)
				).put(
					"label", LanguageUtil.get(locale, "widget-templates")
				).put(
					"subtypes", mappingSubtypesJSONArray
				).put(
					"value",
					String.valueOf(
						_classNameLocalService.getClassNameId(
							PortletDisplayTemplate.class))
				));
		}

		if (Objects.equals(
				TemplatesWebKeys.INFORMATION_TEMPLATES, currentTab)) {

			JSONArray mappingTypesJSONArray = JSONFactoryUtil.createJSONArray();

			for (InfoItemFormProvider<?> infoItemFormProvider :
					_getDisplayableInfoItemFormProviderList()) {

				InfoForm infoForm = infoItemFormProvider.getInfoForm();

				long resourceClassNameId =
					_classNameLocalService.getClassNameId(infoForm.getName());

				InfoItemFormVariationsProvider<?>
					infoItemFormVariationsProvider =
						_infoItemServiceTracker.getFirstInfoItemService(
							InfoItemFormVariationsProvider.class,
							infoForm.getName());

				if (infoItemFormVariationsProvider == null) {
					_putInfoItemFormProviderWithoutVariations(
						mappingTypesJSONArray, infoForm.getLabel(locale),
						resourceClassNameId);

					continue;
				}

				JSONArray mappingSubtypesJSONArray = _getVariationsJSONArray(
					infoItemFormVariationsProvider, groupId, locale);

				_putInfoItemFormProviderWithVariations(
					mappingTypesJSONArray, mappingSubtypesJSONArray,
					infoForm.getLabel(locale),
					_getClassNameId(infoItemFormVariationsProvider, groupId),
					resourceClassNameId);
			}

			return mappingTypesJSONArray;
		}

		return null;
	}

	@Deactivate
	protected void deactivate() {
		_classNameLocalService = null;
		_infoItemServiceTracker = null;
		_portletDisplayTemplate = null;
	}

	@Reference(unbind = "-")
	protected void setClassNameLocalService(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Reference(unbind = "-")
	protected void setInfoItemServiceTracker(
		InfoItemServiceTracker infoItemServiceTracker) {

		_infoItemServiceTracker = infoItemServiceTracker;
	}

	@Reference(unbind = "-")
	protected void setPortletDisplayTemplate(
		PortletDisplayTemplate portletDisplayTemplate) {

		_portletDisplayTemplate = portletDisplayTemplate;
	}

	private static long _getClassNameId(
		InfoItemFormVariationsProvider infoItemFormVariationsProvider,
		long groupId) {

		long classNameId = 0;

		Collection<InfoItemFormVariation> infoItemFormVariationCollection =
			infoItemFormVariationsProvider.getInfoItemFormVariations(groupId);

		if (!infoItemFormVariationCollection.isEmpty()) {
			classNameId = _classNameLocalService.getClassNameId(
				DDMStructure.class);
		}

		return classNameId;
	}

private static List<InfoItemFormProvider>
		_getDisplayableInfoItemFormProviderList() {

		List<InfoItemClassDetails> infoItemClassDetailsList =
			_infoItemServiceTracker.getInfoItemClassDetails(
				TemplateInfoItemCapability.KEY);

		Stream<InfoItemClassDetails> infoItemClassDetailsStream =
			infoItemClassDetailsList.stream();

		return infoItemClassDetailsStream.map(
			infoItemClassDetails ->
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemFormProvider.class,
					infoItemClassDetails.getClassName())
		).collect(
			Collectors.toList()
		);
	}

	private static List<TemplateHandler> _getTemplateHandlers(Locale locale) {
		List<TemplateHandler> templateHandlers =
			_portletDisplayTemplate.getPortletDisplayTemplateHandlers();

		ListUtil.sort(templateHandlers, new TemplateHandlerComparator(locale));

		return templateHandlers;
	}

	private static JSONArray _getVariationsJSONArray(
		InfoItemFormVariationsProvider infoItemFormVariationsProvider,
		long groupId, Locale locale) {

		JSONArray mappingSubtypesJSONArray = JSONFactoryUtil.createJSONArray();

		Collection<InfoItemFormVariation> infoItemFormVariationCollection =
			infoItemFormVariationsProvider.getInfoItemFormVariations(groupId);

		if (!infoItemFormVariationCollection.isEmpty()) {
			mappingSubtypesJSONArray.put(
				JSONUtil.put(
					"label", LanguageUtil.get(locale, "all")
				).put(
					"value", "0"
				));
		}

		for (InfoItemFormVariation infoItemFormVariation :
				infoItemFormVariationCollection) {

			mappingSubtypesJSONArray.put(
				JSONUtil.put(
					"label", infoItemFormVariation.getLabel(locale)
				).put(
					"value", infoItemFormVariation.getKey()
				));
		}

		return mappingSubtypesJSONArray;
	}

	private static void _putInfoItemFormProviderWithoutVariations(
		JSONArray mappingTypesJSONArray, String label,
		long resourceClassNameId) {

		mappingTypesJSONArray.put(
			JSONUtil.put(
				"hiddenFields",
				JSONUtil.put(
					"classNameId", String.valueOf(0)
				).put(
					"classPK", String.valueOf(0)
				)
			).put(
				"label", label
			).put(
				"subtypes", JSONFactoryUtil.createJSONArray()
			).put(
				"value", String.valueOf(resourceClassNameId)
			));
	}

	private static void _putInfoItemFormProviderWithVariations(
		JSONArray mappingTypesJSONArray, JSONArray mappingSubtypesJSONArray,
		String label, long classNameId, long resourceClassNameId) {

		mappingTypesJSONArray.put(
			JSONUtil.put(
				"hiddenFields",
				JSONUtil.put(
					"classNameId", String.valueOf(classNameId)
				).put(
					"classPK", "subtype"
				)
			).put(
				"label", label
			).put(
				"subtypes", mappingSubtypesJSONArray
			).put(
				"value", String.valueOf(resourceClassNameId)
			));
	}

	private static ClassNameLocalService _classNameLocalService;
	private static InfoItemServiceTracker _infoItemServiceTracker;
	private static PortletDisplayTemplate _portletDisplayTemplate;

}