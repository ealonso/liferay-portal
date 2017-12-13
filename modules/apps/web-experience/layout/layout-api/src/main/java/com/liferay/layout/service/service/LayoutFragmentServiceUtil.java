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

package com.liferay.layout.service.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LayoutFragment. This utility wraps
 * {@link com.liferay.layout.service.service.impl.LayoutFragmentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentService
 * @see com.liferay.layout.service.service.base.LayoutFragmentServiceBaseImpl
 * @see com.liferay.layout.service.service.impl.LayoutFragmentServiceImpl
 * @generated
 */
@ProviderType
public class LayoutFragmentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.layout.service.service.impl.LayoutFragmentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.Layout addContentLayout(
		long groupId, boolean privateLayout, long parentLayoutId,
		long layoutPageTemplateEntryId,
		java.util.Map<java.util.Locale, java.lang.String> localeNamesMap,
		java.util.Map<java.util.Locale, java.lang.String> localeTitlesMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Map<java.util.Locale, java.lang.String> keywordsMap,
		java.util.Map<java.util.Locale, java.lang.String> robotsMap,
		java.lang.String type,
		java.util.Map<java.util.Locale, java.lang.String> friendlyURLMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addContentLayout(groupId, privateLayout, parentLayoutId,
			layoutPageTemplateEntryId, localeNamesMap, localeTitlesMap,
			descriptionMap, keywordsMap, robotsMap, type, friendlyURLMap,
			serviceContext);
	}

	public static void deleteContentLayout(long groupId, long plid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteContentLayout(groupId, plid, serviceContext);
	}

	public static java.util.List<com.liferay.layout.service.model.LayoutFragment> getLayoutFragments(
		long groupId, long plid) {
		return getService().getLayoutFragments(groupId, plid);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static LayoutFragmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LayoutFragmentService, LayoutFragmentService> _serviceTracker =
		ServiceTrackerFactory.open(LayoutFragmentService.class);
}