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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutFragmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see LayoutFragmentService
 * @generated
 */
@ProviderType
public class LayoutFragmentServiceWrapper implements LayoutFragmentService,
	ServiceWrapper<LayoutFragmentService> {
	public LayoutFragmentServiceWrapper(
		LayoutFragmentService layoutFragmentService) {
		_layoutFragmentService = layoutFragmentService;
	}

	@Override
	public com.liferay.portal.kernel.model.Layout addContentLayout(
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
		return _layoutFragmentService.addContentLayout(groupId, privateLayout,
			parentLayoutId, layoutPageTemplateEntryId, localeNamesMap,
			localeTitlesMap, descriptionMap, keywordsMap, robotsMap, type,
			friendlyURLMap, serviceContext);
	}

	@Override
	public void deleteContentLayout(long groupId, long plid,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_layoutFragmentService.deleteContentLayout(groupId, plid, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.layout.service.model.LayoutFragment> getLayoutFragments(
		long groupId, long plid) {
		return _layoutFragmentService.getLayoutFragments(groupId, plid);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _layoutFragmentService.getOSGiServiceIdentifier();
	}

	@Override
	public LayoutFragmentService getWrappedService() {
		return _layoutFragmentService;
	}

	@Override
	public void setWrappedService(LayoutFragmentService layoutFragmentService) {
		_layoutFragmentService = layoutFragmentService;
	}

	private LayoutFragmentService _layoutFragmentService;
}