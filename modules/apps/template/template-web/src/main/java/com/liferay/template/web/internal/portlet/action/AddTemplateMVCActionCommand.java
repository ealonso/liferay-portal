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

package com.liferay.template.web.internal.portlet.action;

import com.liferay.dynamic.data.mapping.constants.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.template.TemplateHandlerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.template.web.internal.constants.TemplatePortletKeys;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + TemplatePortletKeys.TEMPLATE,
		"mvc.command.name=/template/add_template"
	},
	service = MVCActionCommand.class
)
public class AddTemplateMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long classNameId = _getParamValue(actionRequest, "classNameId");
		long classPK = _getParamValue(actionRequest, "classPK");
		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		long resourceClassNameId = ParamUtil.getLong(actionRequest, "itemType");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");

		String script = "<#-- Empty script -->";

		TemplateHandler templateHandler =
			TemplateHandlerRegistryUtil.getTemplateHandler(resourceClassNameId);

		if (templateHandler == null) {
			templateHandler = TemplateHandlerRegistryUtil.getTemplateHandler(
				classNameId);
		}

		if (templateHandler != null) {
			script = templateHandler.getTemplatesHelpContent(
				TemplateConstants.LANG_TYPE_FTL);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DDMTemplate.class.getName(), actionRequest);

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);

		_ddmTemplateService.addTemplate(
			groupId, classNameId, classPK, resourceClassNameId, nameMap,
			Collections.emptyMap(), DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY,
			StringPool.BLANK, TemplateConstants.LANG_TYPE_FTL, script,
			serviceContext);
	}

	private long _getParamValue(ActionRequest actionRequest, String paramName) {
		String paramStringValue = ParamUtil.getString(actionRequest, paramName);

		if (Objects.equals(paramStringValue, "subtype")) {
			return ParamUtil.getLong(actionRequest, "itemSubType");
		}

		return GetterUtil.getLong(paramStringValue);
	}

	@Reference
	private DDMTemplateService _ddmTemplateService;

	@Reference
	private Portal _portal;

}