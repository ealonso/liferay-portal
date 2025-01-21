/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.service;

import com.liferay.layout.seo.model.LayoutSEOEntryCustomMetaTag;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.service.Snapshot;

import java.util.Map;

/**
 * Provides the remote service utility for LayoutSEOEntryCustomMetaTag. This utility wraps
 * <code>com.liferay.layout.seo.service.impl.LayoutSEOEntryCustomMetaTagServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSEOEntryCustomMetaTagService
 * @generated
 */
public class LayoutSEOEntryCustomMetaTagServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.layout.seo.service.impl.LayoutSEOEntryCustomMetaTagServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static LayoutSEOEntryCustomMetaTag addLayoutSEOEntryCustomMetaTag(
			long groupId, long layoutSEOEntryId, String property,
			Map<java.util.Locale, String> contentMap)
		throws PortalException {

		return getService().addLayoutSEOEntryCustomMetaTag(
			groupId, layoutSEOEntryId, property, contentMap);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static LayoutSEOEntryCustomMetaTagService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<LayoutSEOEntryCustomMetaTagService>
		_serviceSnapshot = new Snapshot<>(
			LayoutSEOEntryCustomMetaTagServiceUtil.class,
			LayoutSEOEntryCustomMetaTagService.class);

}