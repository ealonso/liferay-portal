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

package com.liferay.layout.internal.search.util;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.DefaultFragmentRendererContext;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.util.constants.LayoutDataItemTypeConstants;
import com.liferay.layout.util.template.LayoutStructure;
import com.liferay.layout.util.template.LayoutStructureItem;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
 */
public class LayoutPageTemplateStructureRenderUtil {

	public static String renderLayoutContent(
			FragmentRendererController fragmentRendererController,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			LayoutPageTemplateStructure layoutPageTemplateStructure,
			String mode, Map<String, Object> parameterMap, Locale locale,
			long[] segmentsExperienceIds)
		throws PortalException {

		if (fragmentRendererController == null) {
			return StringPool.BLANK;
		}

		String data = layoutPageTemplateStructure.getData(
			segmentsExperienceIds);

		if (Validator.isNull(data)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		LayoutStructure layoutStructure = new LayoutStructure(data);

		for (LayoutStructureItem layoutStructureItem :
				layoutStructure.getLayoutStructureItems()) {

			if (!Objects.equals(
					layoutStructureItem.getItemType(),
					LayoutDataItemTypeConstants.TYPE_FRAGMENT)) {

				continue;
			}

			JSONObject itemConfigJSONObject =
				layoutStructureItem.getItemConfigJSONObject();

			long fragmentEntryLinkId = itemConfigJSONObject.getLong(
				"fragmentEntryLinkId");

			if (fragmentEntryLinkId <= 0) {
				continue;
			}

			FragmentEntryLink fragmentEntryLink =
				FragmentEntryLinkLocalServiceUtil.fetchFragmentEntryLink(
					fragmentEntryLinkId);

			if (fragmentEntryLink == null) {
				continue;
			}

			DefaultFragmentRendererContext fragmentRendererContext =
				new DefaultFragmentRendererContext(fragmentEntryLink);

			fragmentRendererContext.setFieldValues(parameterMap);
			fragmentRendererContext.setLocale(locale);
			fragmentRendererContext.setMode(mode);
			fragmentRendererContext.setSegmentsExperienceIds(
				segmentsExperienceIds);

			sb.append(
				fragmentRendererController.render(
					fragmentRendererContext, httpServletRequest,
					httpServletResponse));
		}

		return sb.toString();
	}

}