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

package com.liferay.html.preview.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for HtmlPreview. This utility wraps
 * {@link com.liferay.html.preview.service.impl.HtmlPreviewServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see HtmlPreviewService
 * @see com.liferay.html.preview.service.base.HtmlPreviewServiceBaseImpl
 * @see com.liferay.html.preview.service.impl.HtmlPreviewServiceImpl
 * @generated
 */
@ProviderType
public class HtmlPreviewServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.html.preview.service.impl.HtmlPreviewServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.html.preview.model.HtmlPreview generateHtmlPreview(
		long userId, long groupId, long classNameId, long classPK,
		java.lang.String content, java.lang.String mimeType,
		boolean asynchronous,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .generateHtmlPreview(userId, groupId, classNameId, classPK,
			content, mimeType, asynchronous, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static HtmlPreviewService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HtmlPreviewService, HtmlPreviewService> _serviceTracker =
		ServiceTrackerFactory.open(HtmlPreviewService.class);
}