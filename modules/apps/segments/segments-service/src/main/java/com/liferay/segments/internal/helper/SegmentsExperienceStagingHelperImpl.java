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

import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.segments.helper.SegmentsExperienceStagingHelper;
import com.liferay.segments.internal.configuration.FFSegmentsExperienceStagingConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	configurationPid = "com.liferay.segments.internal.configuration.FFSegmentsExperienceStagingConfiguration",
	immediate = true, service = SegmentsExperienceStagingHelper.class
)
public class SegmentsExperienceStagingHelperImpl
	implements SegmentsExperienceStagingHelper {

	@Override
	public long getRecentLayoutSetBranchId(LayoutSet layoutSet, User user) {
		if (isPageVersioningEnabled()) {
			return _staging.getRecentLayoutSetBranchId(
				user, layoutSet.getLayoutSetId());
		}

		return 0;
	}

	@Override
	public boolean isPageVersioningEnabled() {
		return _ffSegmentsExperienceStagingConfiguration.
			pageVersioningEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_ffSegmentsExperienceStagingConfiguration =
			ConfigurableUtil.createConfigurable(
				FFSegmentsExperienceStagingConfiguration.class, properties);
	}

	private volatile FFSegmentsExperienceStagingConfiguration
		_ffSegmentsExperienceStagingConfiguration;

	@Reference
	private Staging _staging;

}