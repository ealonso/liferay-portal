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

package com.liferay.segments.internal.helper;

import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.segments.helper.SegmentsExperienceStagingHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = SegmentsExperienceStagingHelper.class)
public class SegmentsExperienceStagingHelperImpl
	implements SegmentsExperienceStagingHelper {

	@Override
	public long getRecentLayoutSetBranchId(LayoutSet layoutSet, User user) {
		return 0;
	}

	@Override
	public boolean isPageVersioningEnabled() {
		return false;
	}

}