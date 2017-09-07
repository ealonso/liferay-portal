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
import com.liferay.modern.site.building.page.web.internal.util.MSBPagesPortletUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MSBPagesPortletKeys.MSB_PAGES,
		"mvc.command.name=/msb_pages/get_layouts"
	},
	service = MVCResourceCommand.class
)
public class GetMSBPagesMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		long parentLayoutId = ParamUtil.getLong(
			resourceRequest, "parentLayoutId");
		boolean privateLayout = ParamUtil.getBoolean(
			resourceRequest, "privateLayout");
		boolean loadParentLayouts = ParamUtil.getBoolean(
			resourceRequest, "loadParentLayouts");
		long selectedLayoutId = ParamUtil.getLong(
			resourceRequest, "selectedLayoutId");

		String orderByCol = ParamUtil.getString(resourceRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			resourceRequest, "orderByType");

		OrderByComparator orderByComparator =
			MSBPagesPortletUtil.getLayoutOrderByComparator(
				orderByCol, orderByType);

		Layout parentLayout = _layoutLocalService.fetchLayout(
			groupId, privateLayout, parentLayoutId);

		if (loadParentLayouts && (parentLayout != null)) {
			parentLayoutId = parentLayout.getParentLayoutId();

			selectedLayoutId = parentLayout.getLayoutId();
		}

		JSONArray jsonArray = MSBPagesPortletUtil.getLayoutsJSONArray(
			groupId, privateLayout, parentLayoutId, selectedLayoutId,
			orderByComparator, resourceRequest);

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonArray);
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

}