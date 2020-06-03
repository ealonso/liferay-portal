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

package com.liferay.layout.page.template.model.impl;

import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.page.template.util.LayoutPageTemplateStructureHelperUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;

/**
 * @author Eudaldo Alonso
 */
public class LayoutPageTemplateStructureRelImpl
	extends LayoutPageTemplateStructureRelBaseImpl {

	@Override
	public String getData() {
		String data = super.getData();

		if (Validator.isNotNull(data)) {
			return data;
		}

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			LayoutPageTemplateStructureLocalServiceUtil.
				fetchLayoutPageTemplateStructure(
					getLayoutPageTemplateStructureId());

		Layout layout = LayoutLocalServiceUtil.fetchLayout(
			layoutPageTemplateStructure.getClassPK());

		JSONObject dataJSONObject =
			LayoutPageTemplateStructureHelperUtil.
				generateContentLayoutStructure(
					Collections.emptyList(),
					_getLayoutPageTemplateEntryType(layout));

		return dataJSONObject.toString();
	}

	private int _getLayoutPageTemplateEntryType(Layout layout) {
		LayoutPageTemplateEntry layoutPageTemplateEntry =
			LayoutPageTemplateEntryLocalServiceUtil.
				fetchLayoutPageTemplateEntryByPlid(layout.getClassPK());

		if (layoutPageTemplateEntry != null) {
			return layoutPageTemplateEntry.getType();
		}

		Layout draftLayout = LayoutLocalServiceUtil.fetchLayout(
			PortalUtil.getClassNameId(Layout.class), layout.getPlid());

		if (draftLayout != null) {
			LayoutPageTemplateEntry draftLayoutPageTemplateEntry =
				LayoutPageTemplateEntryLocalServiceUtil.
					fetchLayoutPageTemplateEntryByPlid(
						draftLayout.getClassPK());

			if (draftLayoutPageTemplateEntry != null) {
				return draftLayoutPageTemplateEntry.getType();
			}
		}

		return LayoutPageTemplateEntryTypeConstants.TYPE_BASIC;
	}

}