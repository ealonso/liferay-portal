/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.admin.web.internal.portlet.action;

import com.liferay.layout.page.template.admin.constants.LayoutPageTemplateAdminPortletKeys;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionService;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yurena Cabrera
 */
@Component(
	property = {
		"javax.portlet.name=" + LayoutPageTemplateAdminPortletKeys.LAYOUT_PAGE_TEMPLATES,
		"mvc.command.name=/layout_page_template_admin/add_display_page_collection"
	},
	service = MVCActionCommand.class
)
public class AddDisplayPageCollectionMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		if (!FeatureFlagManagerUtil.isEnabled("LPS-189856")) {
			throw new UnsupportedOperationException();
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_layoutPageTemplateCollectionService.addLayoutPageTemplateCollection(
			themeDisplay.getScopeGroupId(),
			ParamUtil.getString(actionRequest, "name"),
			ParamUtil.getString(actionRequest, "description"),
			ServiceContextFactory.getInstance(actionRequest));

		JSONObject jsonObject = JSONUtil.put(
			"redirectURL", getRedirectURL(actionRequest, actionResponse));

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected String getRedirectURL(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		return PortletURLBuilder.createRenderURL(
			_portal.getLiferayPortletResponse(actionResponse)
		).setMVCPath(
			"/view_display_pages.jsp"
		).setRedirect(
			() -> {
				String backURL = ParamUtil.getString(actionRequest, "backURL");

				if (backURL != null) {
					return backURL;
				}

				return null;
			}
		).setTabs1(
			"display-page-templates"
		).buildString();
	}

	@Reference
	private LayoutPageTemplateCollectionService
		_layoutPageTemplateCollectionService;

	@Reference
	private Portal _portal;

}