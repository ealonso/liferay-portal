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

package com.liferay.staging.internal.service;

import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutLocalServiceWrapper;
import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class LayoutLocalServiceStagingAdviceWrapper
	extends LayoutLocalServiceWrapper {

	public LayoutLocalServiceStagingAdviceWrapper() {
		super(null);
	}

	public LayoutLocalServiceStagingAdviceWrapper(
		LayoutLocalService layoutLocalService) {

		super(layoutLocalService);
	}

}