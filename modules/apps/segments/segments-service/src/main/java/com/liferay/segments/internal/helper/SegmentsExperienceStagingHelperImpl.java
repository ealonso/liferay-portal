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

import com.liferay.exportimport.kernel.staging.LayoutStaging;
import com.liferay.exportimport.kernel.staging.Staging;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutBranch;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.LayoutBranchLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.segments.helper.SegmentsExperienceStagingHelper;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = SegmentsExperienceStagingHelper.class)
public class SegmentsExperienceStagingHelperImpl
	implements SegmentsExperienceStagingHelper {

	@Override
	public long getClassNameId(Layout layout) {
		if (isPageVersioningEnabled(layout)) {
			LayoutSet layoutSet = layout.getLayoutSet();

			long layoutSetBranchId = _staging.getRecentLayoutSetBranchId(
				PrincipalThreadLocal.getUserId(), layoutSet.getLayoutSetId());

			List<LayoutBranch> layoutBranches =
				_layoutBranchLocalService.getLayoutBranches(
					layoutSetBranchId, _getPublishedPlid(layout), 0, 1, null);

			if (ListUtil.isNotEmpty(layoutBranches)) {
				return _portal.getClassNameId(LayoutBranch.class);
			}
		}

		return _portal.getClassNameId(Layout.class);
	}

	@Override
	public long getClassPK(Layout layout) {
		try {
			if (isPageVersioningEnabled(layout)) {
				LayoutSet layoutSet = layout.getLayoutSet();

				long layoutSetBranchId = _staging.getRecentLayoutSetBranchId(
					PrincipalThreadLocal.getUserId(),
					layoutSet.getLayoutSetId());

				List<LayoutBranch> layoutBranches =
					_layoutBranchLocalService.getLayoutBranches(
						layoutSetBranchId, _getPublishedPlid(layout), 0, 1,
						null);

				if (ListUtil.isNotEmpty(layoutBranches)) {
					LayoutBranch layoutBranch = layoutBranches.get(0);

					return layoutBranch.getLayoutBranchId();
				}
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return layout.getPlid();
	}

	@Override
	public boolean isPageVersioningEnabled(Layout layout) {
		if (GetterUtil.getBoolean(PropsUtil.get("feature.flag.LPS-142162")) &&
			_layoutStaging.isBranchingLayoutSet(
				layout.getGroup(), layout.isPrivateLayout())) {

			return true;
		}

		return false;
	}

	private long _getPublishedPlid(Layout layout) {
		if (layout.isDraftLayout()) {
			return layout.getClassPK();
		}

		return layout.getPlid();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SegmentsExperienceStagingHelperImpl.class);

	@Reference
	private LayoutBranchLocalService _layoutBranchLocalService;

	@Reference
	private LayoutStaging _layoutStaging;

	@Reference
	private Portal _portal;

	@Reference
	private Staging _staging;

}