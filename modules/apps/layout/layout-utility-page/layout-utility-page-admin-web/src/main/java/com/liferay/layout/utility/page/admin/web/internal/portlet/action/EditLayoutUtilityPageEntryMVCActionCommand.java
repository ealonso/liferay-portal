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
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryService;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

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
		"mvc.command.name=/layout_utility_pages/edit_layout_utility_page_entry"
	},
	service = MVCActionCommand.class
)
public class EditLayoutUtilityPageEntryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long layoutUtilityPageEntryId = ParamUtil.getLong(
			actionRequest, "layoutUtilityPageEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		String layoutUuid = ParamUtil.getString(actionRequest, "layoutUuid");
		int type = ParamUtil.get(actionRequest, "type", 0);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		try {
			_layoutUtilityPageEntryService.updateLayoutUtilityPageEntry(
				layoutUtilityPageEntryId, name,
				_getPlid(layoutUuid, serviceContext), type, serviceContext);

			sendRedirect(
				actionRequest, actionResponse, getRedirectURL(actionResponse));
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}
	}

	protected String getRedirectURL(ActionResponse actionResponse) {
		return PortletURLBuilder.createRenderURL(
			_portal.getLiferayPortletResponse(actionResponse)
		).setMVCPath(
			"/view.jsp"
		).buildString();
	}

	private long _getPlid(String layoutUuid, ServiceContext serviceContext) {
		try {
			Layout layout = _layoutLocalService.getLayoutByUuidAndGroupId(
				layoutUuid, serviceContext.getScopeGroupId(), false);

			return layout.getPlid();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}
		}

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditLayoutUtilityPageEntryMVCActionCommand.class);

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutUtilityPageEntryService _layoutUtilityPageEntryService;

	@Reference
	private Portal _portal;

}