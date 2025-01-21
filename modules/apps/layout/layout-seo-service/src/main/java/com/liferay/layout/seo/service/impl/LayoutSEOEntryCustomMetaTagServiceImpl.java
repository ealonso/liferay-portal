/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service.impl;

import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.layout.seo.service.base.LayoutSEOEntryCustomMetaTagServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = {
		"json.web.service.context.name=layoutseo",
		"json.web.service.context.path=LayoutSEOEntryCustomMetaTag"
	},
	service = AopService.class
)
public class LayoutSEOEntryCustomMetaTagServiceImpl
	extends LayoutSEOEntryCustomMetaTagServiceBaseImpl {

	@Override
	public LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			Map<Locale, String> contentMap)
		throws PortalException {

		LayoutSEOEntry layoutSEOEntry =
			_layoutSEOEntryLocalService.getLayoutSEOEntry(layoutSEOEntryId);

		LayoutPermissionUtil.checkLayoutUpdatePermission(
			getPermissionChecker(),
			_layoutLocalService.getLayout(
				groupId, layoutSEOEntry.isPrivateLayout(),
				layoutSEOEntry.getLayoutId()));

		return layoutSEOEntryCustomMetaTagLocalService.
			addLayoutSEOEntryCustomMetaTag(
				groupId, layoutSEOEntryId, property, contentMap);
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSEOEntryLocalService _layoutSEOEntryLocalService;

}