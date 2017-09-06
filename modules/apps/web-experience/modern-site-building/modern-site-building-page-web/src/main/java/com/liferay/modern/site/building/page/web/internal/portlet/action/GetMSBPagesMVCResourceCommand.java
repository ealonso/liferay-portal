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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

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

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

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

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			groupId, privateLayout, parentLayoutId, true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, orderByComparator);

		for (Layout layout : layouts) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONObject actionsJSONObject =
				MSBPagesPortletUtil.getActionsJSONObject(
					layout, resourceRequest);

			if (actionsJSONObject.length() > 0) {
				jsonObject.put("actions", actionsJSONObject);
			}

			jsonObject.put("active", layout.getLayoutId() == selectedLayoutId);

			int childLayoutsCount = _layoutLocalService.getLayoutsCount(
				themeDisplay.getScopeGroup(), privateLayout,
				layout.getLayoutId());

			jsonObject.put("hasChild", childLayoutsCount > 0);

			jsonObject.put("icon", "page");
			jsonObject.put("layoutId", layout.getLayoutId());
			jsonObject.put("parentLayoutId", layout.getParentLayoutId());
			jsonObject.put("selected", jsonObject.getBoolean("active"));
			jsonObject.put("title", layout.getName(themeDisplay.getLocale()));

			jsonArray.put(jsonObject);
		}

		JSONPortletResponseUtil.writeJSON(
			resourceRequest, resourceResponse, jsonArray);
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

}