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
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.item.renderer.template.InfoItemRendererTemplate;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.staging.StagingGroupHelper;
import com.liferay.template.info.item.renderer.TemplateInfoItemTemplatedRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
		String className, long classPK, Locale locale) {

		List<InfoItemRendererTemplate> infoItemRendererTemplates =
			new ArrayList<>();

		for (DDMTemplate ddmTemplate : _getDDMTemplates(className, classPK)) {
			if (_stagingGroupHelper.isLiveGroup(ddmTemplate.getGroupId())) {
				continue;
			}

			infoItemRendererTemplates.add(
				new InfoItemRendererTemplate(
					ddmTemplate.getName(locale), ddmTemplate.getTemplateKey()));
		}

		return infoItemRendererTemplates;
	}

	private List<DDMTemplate> _getDDMTemplates(String className, long classPK) {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return Collections.emptyList();
		}

		return _ddmTemplateLocalService.getTemplates(
			serviceContext.getCompanyId(),
			ArrayUtil.append(
				_portal.getAncestorSiteGroupIds(
					serviceContext.getScopeGroupId()),
				new long[] {serviceContext.getScopeGroupId()}),
			new long[] {_portal.getClassNameId(className)},
			new long[] {classPK},
			_portal.getClassNameId(InfoItemFormProvider.class.getName()),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private Portal _portal;

	@Reference
	private StagingGroupHelper _stagingGroupHelper;

}