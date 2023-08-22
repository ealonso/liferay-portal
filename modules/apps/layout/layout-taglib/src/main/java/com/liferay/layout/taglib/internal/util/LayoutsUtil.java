/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.taglib.internal.util;

import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.LayoutItemSelectorReturnType;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.service.LayoutServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class LayoutsUtil {

	public static JSONArray getLayoutsJSONArray(
			boolean checkDisplayPage, long groupId, HttpServletRequest httpServletRequest,
			String itemSelectorReturnType, long parentLayoutId, boolean privateLayout,
			String[] selectedLayoutUuids)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Layout> layouts = LayoutServiceUtil.getLayouts(
			groupId, privateLayout, parentLayoutId, false, 0,
			PropsValues.LAYOUT_MANAGE_PAGES_INITIAL_CHILDREN);

		for (Layout layout : layouts) {
			if (_isExcludedLayout(layout)) {
				continue;
			}

			int childLayoutsCount = LayoutServiceUtil.getLayoutsCount(
				groupId, privateLayout, parentLayoutId);

			JSONArray childLayoutsJSONArray = getLayoutsJSONArray(
				checkDisplayPage, groupId, httpServletRequest,
				itemSelectorReturnType, layout.getLayoutId(), privateLayout,
				selectedLayoutUuids);

			jsonArray.put(
				JSONUtil.put(
					"children", childLayoutsJSONArray
				).put(
					"disabled",
					() -> {
						if ((checkDisplayPage &&
							 !layout.isContentDisplayPage()) ||
							(checkDisplayPage &&
							 (layout.getPlid() == _getSelPlid(httpServletRequest)))) {

							return true;
						}

						return null;
					}
				).put(
					"groupId", layout.getGroupId()
				).put(
					"hasChildren", childLayoutsCount > 0
				).put(
					"icon", layout.getIcon()
				).put(
					"id", layout.getUuid()
				).put(
					"layoutId", layout.getLayoutId()
				).put(
					"name", layout.getName(themeDisplay.getLocale())
				).put(
					"paginated",
					() -> {
						if (childLayoutsCount >
							childLayoutsJSONArray.length()) {

							return true;
						}

						return null;
					}
				).put(
					"payload", _getPayload(httpServletRequest, itemSelectorReturnType, layout, themeDisplay)
				).put(
					"privateLayout", layout.isPrivateLayout()
				).put(
					"returnType", itemSelectorReturnType
				).put(
					"selected",
					() -> {
						if (ArrayUtil.contains(
								selectedLayoutUuids, layout.getUuid())) {

							return true;
						}

						return false;
					}
				).put(
					"url",
					PortalUtil.getLayoutRelativeURL(layout, themeDisplay, false)
				).put(
					"value", layout.getBreadcrumb(themeDisplay.getLocale())
				));
		}

		return jsonArray;
	}

	private static String _getPayload(
			HttpServletRequest httpServletRequest, String itemSelectorReturnType, Layout layout,
			ThemeDisplay themeDisplay)
		throws Exception {

		if (Objects.equals(
			LayoutItemSelectorReturnType.class.getName(),
			itemSelectorReturnType)) {

			return JSONUtil.put(
				"layoutId", layout.getLayoutId()
			).put(
				"name", layout.getName(themeDisplay.getLocale())
			).put(
				"plid", layout.getPlid()
			).put(
				"previewURL",
				() -> {
					String layoutURL = HttpComponentsUtil.addParameter(
						PortalUtil.getLayoutFullURL(layout, themeDisplay),
						"p_l_mode", Constants.PREVIEW);

					return HttpComponentsUtil.addParameter(
						layoutURL, "p_p_auth",
						AuthTokenUtil.getToken(httpServletRequest));
				}
			).put(
				"private", layout.isPrivateLayout()
			).put(
				"url", PortalUtil.getLayoutFullURL(layout, themeDisplay)
			).put(
				"uuid", layout.getUuid()
			).toString();
		}
		else if (Objects.equals(
			UUIDItemSelectorReturnType.class.getName(),
			itemSelectorReturnType)) {

			return layout.getUuid();
		}

		return PortalUtil.getLayoutRelativeURL(layout, themeDisplay, false);
	}

	private static long _getSelPlid(HttpServletRequest httpServletRequest) {
		return ParamUtil.getLong(
			httpServletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);
	}

	private static boolean _isExcludedLayout(Layout layout) {
		if (!layout.isTypeContent()) {
			return false;
		}

		if (layout.fetchDraftLayout() != null) {
			return !layout.isPublished();
		}

		if (layout.isApproved() && !layout.isHidden() && !layout.isSystem()) {
			return false;
		}

		return true;
	}

}