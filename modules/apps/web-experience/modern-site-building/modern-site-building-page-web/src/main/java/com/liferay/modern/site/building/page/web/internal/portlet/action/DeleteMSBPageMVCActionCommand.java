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

package com.liferay.modern.site.building.page.web.internal.portlet.action;

import com.liferay.modern.site.building.page.web.constants.MSBPagesPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MSBPagesPortletKeys.PAGES,
		"mvc.command.name=/pages/delete_layout"
	},
	service = MVCActionCommand.class
)
public class DeleteMSBPageMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] layoutIds = null;

		long groupId = ParamUtil.getLong(actionRequest, "groupId");
		boolean privateLayout = ParamUtil.getBoolean(
			actionRequest, "privateLayout");

		long layoutId = ParamUtil.getLong(actionRequest, "layoutId");

		if (layoutId > 0) {
			layoutIds = new long[] {layoutId};
		}
		else {
			layoutIds = ParamUtil.getLongValues(actionRequest, "rowIds");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		for (long deleteLayoutId : layoutIds) {
			_layoutService.deleteLayout(
				groupId, privateLayout, deleteLayoutId, serviceContext);
		}
	}

	@Reference
	private LayoutService _layoutService;

}