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