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

package com.liferay.layout.page.template.util;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

/**
 * @author Jürgen
 */
public class LayoutPageTemplateStructureHelperUtil {

	public static JSONObject generateContentLayoutStructure(
		List<FragmentEntryLink> fragmentEntryLinks) {

		return generateContentLayoutStructure(
			fragmentEntryLinks,
			LayoutPageTemplateEntryTypeConstants.TYPE_BASIC);
	}

	public static JSONObject generateContentLayoutStructure(
		List<FragmentEntryLink> fragmentEntryLinks, int type) {

		if (fragmentEntryLinks.isEmpty() &&
			(type == LayoutPageTemplateEntryTypeConstants.TYPE_MASTER_LAYOUT)) {

			LayoutStructure layoutStructure =
				LayoutStructure.emptyLayoutStructure();

			layoutStructure.addDropZoneLayoutStructureItem(
				layoutStructure.getMainItemId(), 0);

			return layoutStructure.toJSONObject();
		}

		if (fragmentEntryLinks.isEmpty()) {
			LayoutStructure layoutStructure =
				LayoutStructure.emptyLayoutStructure();

			return layoutStructure.toJSONObject();
		}

		LayoutStructure layoutStructure =
			LayoutStructure.emptyLayoutStructure();

		LayoutStructureItem containerLayoutStructureItem =
			layoutStructure.addContainerLayoutStructureItem(
				layoutStructure.getMainItemId(), 0);

		for (int i = 0; i < fragmentEntryLinks.size(); i++) {
			FragmentEntryLink fragmentEntryLink = fragmentEntryLinks.get(i);

			layoutStructure.addFragmentLayoutStructureItem(
				fragmentEntryLink.getFragmentEntryLinkId(),
				containerLayoutStructureItem.getItemId(), i);
		}

		return layoutStructure.toJSONObject();
	}

	public static LayoutStructure getLayoutStructure(
		long groupId, long plid, long segmentExperienceId) {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			LayoutPageTemplateStructureLocalServiceUtil.
				fetchLayoutPageTemplateStructure(
					groupId, PortalUtil.getClassNameId(Layout.class.getName()),
					plid);

		return LayoutStructure.of(
			layoutPageTemplateStructure.getData(segmentExperienceId));
	}

}