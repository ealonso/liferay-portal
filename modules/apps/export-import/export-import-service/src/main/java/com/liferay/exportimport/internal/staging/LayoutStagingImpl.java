/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.staging;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.staging.LayoutStaging;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutRevision;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetBranch;
import com.liferay.portal.kernel.model.LayoutSetStagingHandler;
import com.liferay.portal.kernel.model.LayoutStagingHandler;
import com.liferay.portal.kernel.service.LayoutRevisionLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Raymond Augé
 */
@Component(service = LayoutStaging.class)
public class LayoutStagingImpl implements LayoutStaging {

	@Override
	public Layout mergeLayoutRevisionIntoLayout(Layout layout) {
		LayoutStagingHandler layoutStagingHandler =
			layout.getLayoutStagingHandler();

		if (layoutStagingHandler == null) {
			return (Layout)layout.clone();
		}

		layout = layoutStagingHandler.getLayout();
		layout = (Layout)layout.clone();

		LayoutRevision layoutRevision =
			layoutStagingHandler.getLayoutRevision();

		layout.setName(layoutRevision.getName());
		layout.setTitle(layoutRevision.getTitle());
		layout.setDescription(layoutRevision.getDescription());
		layout.setKeywords(layoutRevision.getKeywords());
		layout.setRobots(layoutRevision.getRobots());
		layout.setTypeSettings(layoutRevision.getTypeSettings());
		layout.setIconImageId(layoutRevision.getIconImageId());
		layout.setThemeId(layoutRevision.getThemeId());
		layout.setColorSchemeId(layoutRevision.getColorSchemeId());
		layout.setCss(layoutRevision.getCss());

		return layout;
	}

	@Override
	public LayoutSet mergeLayoutSetRevisionIntoLayoutSet(LayoutSet layoutSet) {
		LayoutSetStagingHandler layoutSetStagingHandler =
			layoutSet.getLayoutSetStagingHandler();

		if (layoutSetStagingHandler == null) {
			return (LayoutSet)layoutSet.clone();
		}

		layoutSet = layoutSetStagingHandler.getLayoutSet();
		layoutSet = (LayoutSet)layoutSet.clone();

		LayoutSetBranch layoutSetBranch =
			layoutSetStagingHandler.getLayoutSetBranch();

		layoutSet.setLogoId(layoutSetBranch.getLogoId());
		layoutSet.setThemeId(layoutSetBranch.getThemeId());
		layoutSet.setColorSchemeId(layoutSetBranch.getColorSchemeId());
		layoutSet.setCss(layoutSetBranch.getCss());
		layoutSet.setSettings(layoutSetBranch.getSettings());
		layoutSet.setLayoutSetPrototypeUuid(
			layoutSetBranch.getLayoutSetPrototypeUuid());
		layoutSet.setLayoutSetPrototypeLinkEnabled(
			layoutSetBranch.isLayoutSetPrototypeLinkEnabled());

		return layoutSet;
	}

	@Override
	public boolean prepareLayoutStagingHandler(
		PortletDataContext portletDataContext, Layout layout) {

		boolean exportLAR = MapUtil.getBoolean(
			portletDataContext.getParameterMap(), "exportLAR");

		if (exportLAR || !layout.isBranchingLayout()) {
			return true;
		}

		long layoutSetBranchId = MapUtil.getLong(
			portletDataContext.getParameterMap(), "layoutSetBranchId");

		if (layoutSetBranchId <= 0) {
			return false;
		}

		LayoutRevision layoutRevision = null;

		List<LayoutRevision> layoutRevisions =
			_layoutRevisionLocalService.getLayoutRevisions(
				layoutSetBranchId, layout.getPlid(), true);

		if (!layoutRevisions.isEmpty()) {
			if (layoutRevisions.size() > 1) {
				layoutRevision = layout.getLayoutRevision();

				long layoutBranchId = GetterUtil.DEFAULT_LONG;

				if (layoutRevision != null) {
					layoutBranchId = layoutRevision.getLayoutBranchId();
				}

				layoutRevision =
					_layoutRevisionLocalService.fetchLayoutRevision(
						layoutSetBranchId, layoutBranchId, true,
						layout.getPlid());
			}

			if ((layoutRevision == null) && !layoutRevisions.isEmpty()) {
				layoutRevision = layoutRevisions.get(0);
			}
		}

		if (layoutRevision == null) {
			return false;
		}

		LayoutStagingHandler layoutStagingHandler =
			layout.getLayoutStagingHandler();

		layoutStagingHandler.setLayoutRevision(layoutRevision);

		return true;
	}

	@Reference
	private LayoutRevisionLocalService _layoutRevisionLocalService;

}