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

package com.liferay.template.web.internal.info.item.renderer;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.item.provider.InfoItemFormVariationsProvider;
import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.template.info.item.renderer.TemplateInfoItemTemplatedRenderer;
import com.liferay.template.model.TemplateEntry;
import com.liferay.template.service.TemplateEntryLocalService;
import com.liferay.template.web.internal.portlet.template.TemplateDisplayTemplateTransformer;

import java.io.Writer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(immediate = true, service = TemplateInfoItemTemplatedRenderer.class)
public class TemplateInfoItemTemplatedRendererImpl
	implements TemplateInfoItemTemplatedRenderer {

	@Override
	public List<InfoItemRendererTemplate> getInfoItemRendererTemplates(
		String infoItemClassName, String infoItemFormVariationKey,
		Locale locale) {

		List<InfoItemRendererTemplate> infoItemRendererTemplates =
			new ArrayList<>();

		for (TemplateEntry templateEntry :
				_getTemplateEntries(
					infoItemClassName, infoItemFormVariationKey)) {

			if (_stagingGroupHelper.isLiveGroup(templateEntry.getGroupId())) {
				continue;
			}

			DDMTemplate ddmTemplate = _ddmTemplateLocalService.fetchDDMTemplate(
				templateEntry.getDDMTemplateId());

			infoItemRendererTemplates.add(
				new InfoItemRendererTemplate(
					ddmTemplate.getName(locale),
					String.valueOf(templateEntry.getTemplateEntryId())));
		}

		return infoItemRendererTemplates;
	}

	@Override
	public String getInfoItemRendererTemplatesGroupLabel(
		String infoItemClassName, String infoItemFormVariationKey,
		Locale locale) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return StringPool.BLANK;
		}

		return Optional.ofNullable(
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemFormVariationsProvider.class, infoItemClassName)
		).map(
			infoItemFormVariationsProvider ->
				infoItemFormVariationsProvider.getInfoItemFormVariation(
					serviceContext.getScopeGroupId(), infoItemFormVariationKey)
		).filter(
			Objects::nonNull
		).map(
			infoItemFormVariation -> infoItemFormVariation.getLabel(locale)
		).orElse(
			getLabel(locale)
		);
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "information-templates");
	}

	@Override
	public void renderTemplate(
		String infoItemClassName, Object itemObject, String templateKey,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return;
		}

		TemplateEntry templateEntry =
			_templateEntryLocalService.fetchTemplateEntry(
				GetterUtil.getLong(templateKey));

		if (templateEntry == null) {
			return;
		}

		try {
			InfoItemFieldValues infoItemFieldValues =
				InfoItemFieldValues.builder(
				).build();

			InfoItemFieldValuesProvider<Object> infoItemFieldValuesProvider =
				_infoItemServiceTracker.getFirstInfoItemService(
					InfoItemFieldValuesProvider.class, infoItemClassName);

			if (infoItemFieldValuesProvider != null) {
				infoItemFieldValues =
					infoItemFieldValuesProvider.getInfoItemFieldValues(
						itemObject);
			}

			TemplateDisplayTemplateTransformer
				templateDisplayTemplateTransformer =
					new TemplateDisplayTemplateTransformer(
						templateEntry, infoItemFieldValues);

			String content = templateDisplayTemplateTransformer.transform(
				serviceContext.getLocale());

			Writer writer = httpServletResponse.getWriter();

			writer.write(content);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private List<TemplateEntry> _getTemplateEntries(
		String infoItemClassName, String infoItemFormVariationKey) {

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return Collections.emptyList();
		}

		return _templateEntryLocalService.getTemplateEntries(
			serviceContext.getScopeGroupId(), infoItemClassName,
			infoItemFormVariationKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private Portal _portal;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

	@Reference
	private TemplateEntryLocalService _templateEntryLocalService;

}