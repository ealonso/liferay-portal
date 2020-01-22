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
import com.liferay.layout.util.constants.LayoutDataItemTypeConstants;
import com.liferay.layout.util.template.LayoutStructure;
import com.liferay.layout.util.template.LayoutStructureItem;
import com.liferay.portal.kernel.json.JSONObject;

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

		LayoutStructure layoutStructure = new LayoutStructure();

		LayoutStructureItem containerLayoutStructureItem =
			layoutStructure.addLayoutStructureItem(
				LayoutDataItemTypeConstants.TYPE_CONTAINER,
				layoutStructure.getMainItemId(), 0);

		LayoutStructureItem rowLayoutStructureItem =
			layoutStructure.addLayoutStructureItem(
				LayoutDataItemTypeConstants.TYPE_ROW,
				containerLayoutStructureItem.getItemId(), 0);

		LayoutStructureItem columnLayoutStructureItem =
			layoutStructure.addLayoutStructureItem(
				LayoutDataItemTypeConstants.TYPE_COLUMN,
				rowLayoutStructureItem.getItemId(), 0);

		if (fragmentEntryLinks.isEmpty() &&
			(type == LayoutPageTemplateEntryTypeConstants.TYPE_MASTER_LAYOUT)) {

			layoutStructure.addLayoutStructureItem(
				LayoutDataItemTypeConstants.TYPE_DROP_ZONE,
				columnLayoutStructureItem.getItemId(), 0);
		}
		else {
			for (int i = 0; i < fragmentEntryLinks.size(); i++) {
				FragmentEntryLink fragmentEntryLink = fragmentEntryLinks.get(i);

				layoutStructure.addFragmentLayoutStructureItem(
					fragmentEntryLink.getFragmentEntryLinkId(),
					columnLayoutStructureItem.getItemId(), i);
			}
		}

		return layoutStructure.toJSONObject();
	}

}