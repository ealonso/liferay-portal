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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.fragment.model.LayoutFragment;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class ContentLayoutDisplayContext {

	public ContentLayoutDisplayContext(HttpServletRequest request)
		throws PortalException {

		_request = request;
	}

	public JSONArray getLayoutFragmentsJSONArray() throws PortalException {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<LayoutFragment> layoutFragments =
			FragmentEntryServiceUtil.getLayoutFragments(
				themeDisplay.getScopeGroupId(), getSelPlid());

		for (LayoutFragment layoutFragment : layoutFragments) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"fragmentEntryId", layoutFragment.getLayoutFragmentId());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public long getSelPlid() {
		if (_selPlid != null) {
			return _selPlid;
		}

		_selPlid = ParamUtil.getLong(_request, "selPlid");

		return _selPlid;
	}

	private final HttpServletRequest _request;
	private Long _selPlid;

}