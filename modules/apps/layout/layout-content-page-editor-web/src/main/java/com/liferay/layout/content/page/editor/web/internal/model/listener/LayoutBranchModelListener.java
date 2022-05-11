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

package com.liferay.layout.content.page.editor.web.internal.model.listener;

import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.layout.content.page.editor.web.internal.segments.SegmentsExperienceUtil;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutBranch;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.helper.SegmentsExperienceStagingHelper;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;

import java.util.Collections;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ModelListener.class)
public class LayoutBranchModelListener extends BaseModelListener<LayoutBranch> {

	@Override
	public void onAfterCreate(LayoutBranch layoutBranch)
		throws ModelListenerException {

		Layout layout = _layoutLocalService.fetchLayout(layoutBranch.getPlid());

		if (!_segmentsExperienceStagingHelper.isPageVersioningEnabled(layout) ||
			!layout.isTypeContent()) {

			return;
		}
		
		Layout draftLayout = layout.fetchDraftLayout();

		if (draftLayout == null) {
			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		try {
			SegmentsExperience segmentsExperience =
				_segmentsExperienceLocalService.addSegmentsExperience(
					layout.getUserId(), layout.getGroupId(),
					SegmentsEntryConstants.ID_DEFAULT,
					SegmentsExperienceConstants.KEY_DEFAULT,
					_portal.getClassNameId(LayoutBranch.class),
					layoutBranch.getLayoutBranchId(),
					Collections.singletonMap(
						LocaleUtil.getSiteDefault(),
						LanguageUtil.get(
							LocaleUtil.getSiteDefault(),
							"default-experience-name")),
					0, true, new UnicodeProperties(true),
					serviceContext);

			SegmentsExperience defaultSegmentsExperience =
				_segmentsExperienceLocalService.fetchSegmentsExperience(
					layout.getGroupId(),
					SegmentsExperienceConstants.KEY_DEFAULT,
					_portal.getClassNameId(Layout.class), layout.getPlid());

			SegmentsExperienceUtil.copySegmentsExperienceData(
				layout.getPlid(), _commentManager, layout.getGroupId(),
				_portletRegistry,
				defaultSegmentsExperience.getSegmentsExperienceId(),
				segmentsExperience.getSegmentsExperienceId(),
				className -> serviceContext, layout.getUserId());

			SegmentsExperienceUtil.copySegmentsExperienceData(
				draftLayout.getPlid(), _commentManager,
				draftLayout.getGroupId(), _portletRegistry,
				defaultSegmentsExperience.getSegmentsExperienceId(),
				segmentsExperience.getSegmentsExperienceId(),
				className -> serviceContext, draftLayout.getUserId());
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Reference
	private CommentManager _commentManager;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletRegistry _portletRegistry;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference
	private SegmentsExperienceStagingHelper _segmentsExperienceStagingHelper;

}