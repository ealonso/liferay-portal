/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.kernel.staging;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.service.Snapshot;

/**
 * @author Raymond Augé
 */
public class LayoutStagingUtil {

	public static boolean isBranchingLayoutSet(
		Group group, boolean privateLayout) {

		LayoutStaging layoutStaging = _layoutStagingSnapshot.get();

		return layoutStaging.isBranchingLayoutSet(group, privateLayout);
	}

	private static final Snapshot<LayoutStaging> _layoutStagingSnapshot =
		new Snapshot<>(LayoutStagingUtil.class, LayoutStaging.class);

}