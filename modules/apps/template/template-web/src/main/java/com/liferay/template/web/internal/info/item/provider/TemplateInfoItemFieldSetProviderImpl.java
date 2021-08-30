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

package com.liferay.template.web.internal.info.item.provider;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;
import com.liferay.template.web.internal.info.item.field.reader.TemplateInfoItemFieldReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(immediate = true, service = TemplateInfoItemFieldSetProvider.class)
public class TemplateInfoItemFieldSetProviderImpl
	implements TemplateInfoItemFieldSetProvider {

	@Override
	public InfoFieldSet getInfoFieldSet(
		String itemClassName, String itemVariationKey) {

		return InfoFieldSet.builder(
		).infoFieldSetEntry(
			consumer -> {
				for (TemplateInfoItemFieldReader templateInfoItemFieldReader :
						_getTemplateInfoItemFieldReaders(
							itemClassName, itemVariationKey,
							InfoItemFieldValues.builder(
							).build())) {

					consumer.accept(templateInfoItemFieldReader.getInfoField());
				}
			}
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(getClass(), "information-templates")
		).name(
			"information-templates"
		).build();
	}

	@Override
	public List<InfoFieldValue<Object>> getInfoFieldValues(
		String itemClassName, Object itemObject, String itemVariationKey) {

		InfoItemFieldValues infoItemFieldValues = InfoItemFieldValues.builder(
		).build();

		InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFieldValuesProvider.class, itemClassName);

		if (infoItemFieldValuesProvider != null) {
			infoItemFieldValues =
				infoItemFieldValuesProvider.getTemplateInfoItemFieldValues(
					itemObject);
		}

		List<InfoFieldValue<Object>> infoFieldValues = new ArrayList<>();

		for (TemplateInfoItemFieldReader templateInfoItemFieldReader :
				_getTemplateInfoItemFieldReaders(
					itemClassName, itemVariationKey, infoItemFieldValues)) {

			InfoFieldValue<Object> infoFieldValue = new InfoFieldValue<>(
				templateInfoItemFieldReader.getInfoField(),
				templateInfoItemFieldReader.getValue(itemObject));

			infoFieldValues.add(infoFieldValue);
		}

		return infoFieldValues;
	}

	private long _getInfoItemFormProviderClassNameId() {
		if (_infoItemFormProviderClassNameId != null) {
			return _infoItemFormProviderClassNameId;
		}

		_infoItemFormProviderClassNameId = _portal.getClassNameId(
			InfoItemFormProvider.class.getName());

		return _infoItemFormProviderClassNameId;
	}

	private List<TemplateInfoItemFieldReader> _getTemplateInfoItemFieldReaders(
		String itemClassName, String itemVariationKey,
		InfoItemFieldValues infoItemFieldValues) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return Collections.emptyList();
		}

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		try {
			List<DDMTemplate> allVariationTemplates =
				_ddmTemplateLocalService.getTemplates(
					themeDisplay.getScopeGroupId(),
					_portal.getClassNameId(itemClassName),
					GetterUtil.getLong(itemVariationKey), true);

			Stream<DDMTemplate> allVariationTemplatesStream =
				allVariationTemplates.stream();

			return allVariationTemplatesStream.filter(
				ddmTemplate ->
					ddmTemplate.getResourceClassNameId() ==
						_getInfoItemFormProviderClassNameId()
			).map(
				ddmTemplate -> new TemplateInfoItemFieldReader(
					ddmTemplate, infoItemFieldValues)
			).collect(
				Collectors.toList()
			);
		}
		catch (PortalException portalException) {
			throw new RuntimeException(
				"Caught unexpected exception", portalException);
		}
	}

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	private Long _infoItemFormProviderClassNameId;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private Portal _portal;

}