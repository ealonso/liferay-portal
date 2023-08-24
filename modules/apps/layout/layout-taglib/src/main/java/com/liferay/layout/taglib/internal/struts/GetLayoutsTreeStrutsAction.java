/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.internal.struts;

import com.liferay.layout.taglib.internal.util.LayoutUtil;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(property = "path=/portal/get_layouts", service = StrutsAction.class)
public class GetLayoutsTreeStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(
			httpServletRequest, "groupId", themeDisplay.getScopeGroupId());

		boolean checkDisplayPage = ParamUtil.getBoolean(
			httpServletRequest, "checkDisplayPage");
		long[] disabledPages = ParamUtil.getLongValues(
			httpServletRequest, "disabledPages");
		boolean privateLayout = ParamUtil.getBoolean(
			httpServletRequest, "privateLayout");
		long parentLayoutId = ParamUtil.getLong(
			httpServletRequest, "parentLayoutId");
		String itemSelectorReturnType = ParamUtil.getString(
			httpServletRequest, "itemSelectorReturnType");
		String[] selectedLayoutUuids = ParamUtil.getStringValues(
			httpServletRequest, "layoutUuid");

		ServletResponseUtil.write(
			httpServletResponse,
			JSONUtil.put(
				"hasMoreElements",
				() -> {
					int childLayoutsCount = _layoutService.getLayoutsCount(
						groupId, privateLayout, parentLayoutId);

					int start = ParamUtil.getInteger(
						httpServletRequest, "start");

					start = Math.max(0, start);

					int pageSize = GetterUtil.getInteger(
						PropsValues.LAYOUT_MANAGE_PAGES_INITIAL_CHILDREN);

					int end = ParamUtil.getInteger(
						httpServletRequest, "end", start + pageSize);

					end = Math.max(start, end);

					if (childLayoutsCount > end) {
						return true;
					}

					return false;
				}
			).put(
				"items",
				LayoutUtil.getLayoutsJSONArray(
					checkDisplayPage, disabledPages, groupId, httpServletRequest,
					itemSelectorReturnType, parentLayoutId, privateLayout,
					selectedLayoutUuids)
			).toString());

		return null;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutService _layoutService;

}