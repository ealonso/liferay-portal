/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service;

import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutSEOEntryCustomMetaTagService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntryCustomMetaTagService
 * @generated
 */
public class LayoutSEOEntryCustomMetaTagServiceWrapper
	implements LayoutSEOEntryCustomMetaTagService,
			   ServiceWrapper<LayoutSEOEntryCustomMetaTagService> {

	public LayoutSEOEntryCustomMetaTagServiceWrapper() {
		this(null);
	}

	public LayoutSEOEntryCustomMetaTagServiceWrapper(
		LayoutSEOEntryCustomMetaTagService layoutSEOEntryCustomMetaTagService) {

		_layoutSEOEntryCustomMetaTagService =
			layoutSEOEntryCustomMetaTagService;
	}

	@Override
	public LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			java.util.Map<java.util.Locale, String> contentMap)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _layoutSEOEntryCustomMetaTagService.
			addLayoutSEOEntryCustomMetaTag(
				groupId, layoutSEOEntryId, property, contentMap);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _layoutSEOEntryCustomMetaTagService.getOSGiServiceIdentifier();
	}

	@Override
	public LayoutSEOEntryCustomMetaTagService getWrappedService() {
		return _layoutSEOEntryCustomMetaTagService;
	}

	@Override
	public void setWrappedService(
		LayoutSEOEntryCustomMetaTagService layoutSEOEntryCustomMetaTagService) {

		_layoutSEOEntryCustomMetaTagService =
			layoutSEOEntryCustomMetaTagService;
	}

	private LayoutSEOEntryCustomMetaTagService
		_layoutSEOEntryCustomMetaTagService;

}