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

package com.liferay.layout.utility.page.admin.web.internal.portlet.action;

import com.liferay.layout.utility.page.constants.LayoutUtilityPageAdminPortletKeys;
import com.liferay.layout.utility.page.exception.LayoutUtilityPageEntryNameException;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryService;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LayoutUtilityPageAdminPortletKeys.LAYOUT_UTILITY_PAGES,
		"mvc.command.name=/layout_utility_pages/add_layout_utility_page_entry"
	},
	service = MVCActionCommand.class
)
public class AddLayoutUtilityPageEntryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		JSONObject jsonObject = _addLayoutUtilityPage(
			actionRequest, actionResponse, serviceContext);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected String getRedirectURL(
		ActionResponse actionResponse,
		LayoutUtilityPageEntry layoutUtilityPageEntry) {

		return PortletURLBuilder.createRenderURL(
			_portal.getLiferayPortletResponse(actionResponse)
		).setMVCPath(
			"/edit_layout_utility_page.jsp"
		).setParameter(
			"layoutUtilityPageEntryId",
			layoutUtilityPageEntry.getLayoutUtilityPageEntryId()
		).buildString();
	}

	private JSONObject _addLayoutUtilityPage(
		ActionRequest actionRequest, ActionResponse actionResponse,
		ServiceContext serviceContext) {

		JSONObject errorJSONObject = JSONFactoryUtil.createJSONObject();

		String name = ParamUtil.getString(actionRequest, "name");

		try {
			LayoutUtilityPageEntry layoutUtilityPageEntry =
				_layoutUtilityPageEntryService.addLayoutUtilityPageEntry(
					null, serviceContext.getScopeGroupId(), name, 0, 0,
					serviceContext);

			return JSONUtil.put(
				"redirectURL",
				getRedirectURL(actionResponse, layoutUtilityPageEntry));
		}
		catch (PortalException portalException) {
			errorJSONObject = JSONUtil.put(
				"name",
				() -> {
					ThemeDisplay themeDisplay =
						serviceContext.getThemeDisplay();

					String errorMessage = null;

					if (portalException instanceof
							LayoutUtilityPageEntryNameException) {

						errorMessage = _language.get(
							themeDisplay.getLocale(),
							"please-enter-a-valid-name");
					}

					if (Validator.isNull(errorMessage)) {
						errorMessage = _language.get(
							themeDisplay.getLocale(),
							"an-unexpected-error-occurred");

						_log.error(portalException);
					}

					return errorMessage;
				});
		}

		return JSONUtil.put("error", errorJSONObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AddLayoutUtilityPageEntryMVCActionCommand.class);

	@Reference
	private Language _language;

	@Reference
	private LayoutUtilityPageEntryService _layoutUtilityPageEntryService;

	@Reference
	private Portal _portal;

}