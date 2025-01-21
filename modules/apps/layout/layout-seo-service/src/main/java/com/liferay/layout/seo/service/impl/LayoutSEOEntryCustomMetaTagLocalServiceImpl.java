/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service.impl;

import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.layout.seo.service.base.LayoutSEOEntryCustomMetaTagLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag",
	service = AopService.class
)
public class LayoutSEOEntryCustomMetaTagLocalServiceImpl
	extends LayoutSEOEntryCustomMetaTagLocalServiceBaseImpl {

	@Override
	public LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			Map<Locale, String> contentMap)
		throws PortalException {

		LayoutSEOEntryCustomMetaTag layoutSEOEntryCustomMetaTag =
			layoutSEOEntryCustomMetaTagPersistence.create(
				counterLocalService.increment());

		layoutSEOEntryCustomMetaTag.setGroupId(groupId);

		Group group = _groupLocalService.getGroup(groupId);

		layoutSEOEntryCustomMetaTag.setCompanyId(group.getCompanyId());

		layoutSEOEntryCustomMetaTag.setLayoutSEOEntryId(layoutSEOEntryId);
		layoutSEOEntryCustomMetaTag.setProperty(property);
		layoutSEOEntryCustomMetaTag.setContentMap(contentMap);

		return layoutSEOEntryCustomMetaTagPersistence.update(
			layoutSEOEntryCustomMetaTag);
	}

	@Override
	public void deleteLayoutSEOEntryCustomMetaTags(
		long groupId, long layoutSEOEntryId) {

		layoutSEOEntryCustomMetaTagPersistence.removeByG_L(
			groupId, layoutSEOEntryId);
	}

	@Override
	public List<LayoutSEOEntryCustomMetaTag> getLayoutSEOEntryCustomMetaTags(
		long groupId, long layoutSEOEntryId) {

		return layoutSEOEntryCustomMetaTagPersistence.findByG_L(
			groupId, layoutSEOEntryId);
	}

	@Reference
	private GroupLocalService _groupLocalService;

}