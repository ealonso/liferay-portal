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
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"mvc.command.name=/layout_utility_pages/delete_layout_utility_page_entry"
	},
	service = MVCActionCommand.class
)
public class DeleteLayoutUtilityPageEntryMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] deleteLayoutUtilityPageIds = null;

		long layoutUtilityPageId = ParamUtil.getLong(
			actionRequest, "layoutUtilityPageId");

		if (layoutUtilityPageId > 0) {
			deleteLayoutUtilityPageIds = new long[] {layoutUtilityPageId};
		}
		else {
			deleteLayoutUtilityPageIds = ParamUtil.getLongValues(
				actionRequest, "rowIds");
		}

		for (long deleteLayoutUtilityPageId : deleteLayoutUtilityPageIds) {
			_layoutUtilityPageEntryService.deleteLayoutUtilityPageEntry(
				deleteLayoutUtilityPageId);
		}
	}

	@Reference
	private LayoutUtilityPageEntryService _layoutUtilityPageEntryService;

}