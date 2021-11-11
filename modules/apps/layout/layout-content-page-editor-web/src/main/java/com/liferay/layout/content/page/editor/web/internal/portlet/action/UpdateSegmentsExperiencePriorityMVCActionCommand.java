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

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.segments.SegmentsExperienceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceLocalService;
import com.liferay.segments.service.SegmentsExperienceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/layout_content_page_editor/update_segments_experience_priority"
	},
	service = MVCActionCommand.class
)
public class UpdateSegmentsExperiencePriorityMVCActionCommand
	extends BaseContentPageEditorTransactionalMVCActionCommand {

	@Override
	protected JSONObject doTransactionalCommand(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long segmentsExperienceId = ParamUtil.getLong(
			actionRequest, "segmentsExperienceId");

		String direction = ParamUtil.getString(actionRequest, "direction");

		_updateSegmentsExperiencePriority(
			themeDisplay.getScopeGroupId(), themeDisplay.getPlid(),
			segmentsExperienceId, direction);

		return JSONUtil.put(
			"availableSegmentsExperiences",
			SegmentsExperienceUtil.getAvailableSegmentsExperiences(
				_portal.getHttpServletRequest(actionRequest)));
	}

	private List<SegmentsExperience> _getSegmentsExperiences(
			long groupId, long plid)
		throws Exception {

		List<SegmentsExperience> availableSegmentsExperiences =
			new ArrayList<>();

		List<SegmentsExperience> segmentsExperiences =
			_segmentsExperienceLocalService.getSegmentsExperiences(
				groupId, _portal.getClassNameId(Layout.class.getName()), plid,
				true);

		boolean addedDefault = false;

		for (SegmentsExperience segmentsExperience : segmentsExperiences) {
			if ((segmentsExperience.getPriority() <
					SegmentsExperienceConstants.PRIORITY_DEFAULT) &&
				!addedDefault) {

				SegmentsExperience defaultSegmentsExperience =
					_segmentsExperienceLocalService.createSegmentsExperience(
						SegmentsExperienceConstants.ID_DEFAULT);

				defaultSegmentsExperience.setPriority(
					SegmentsExperienceConstants.PRIORITY_DEFAULT);

				availableSegmentsExperiences.add(defaultSegmentsExperience);

				addedDefault = true;
			}

			availableSegmentsExperiences.add(segmentsExperience);
		}

		if (!addedDefault) {
			SegmentsExperience defaultSegmentsExperience =
				_segmentsExperienceLocalService.createSegmentsExperience(
					SegmentsExperienceConstants.ID_DEFAULT);

			defaultSegmentsExperience.setPriority(
				SegmentsExperienceConstants.PRIORITY_DEFAULT);

			availableSegmentsExperiences.add(defaultSegmentsExperience);
		}

		return availableSegmentsExperiences;
	}

	private void _updateSegmentsExperiencePriority(
			long groupId, long plid, long segmentsExperienceId,
			String direction)
		throws Exception {

		List<SegmentsExperience> segmentsExperiences = _getSegmentsExperiences(
			groupId, plid);

		SegmentsExperience segmentsExperience =
			_segmentsExperienceLocalService.fetchSegmentsExperience(
				segmentsExperienceId);

		int index = segmentsExperiences.indexOf(segmentsExperience);

		if (Objects.equals(direction, "up")) {
			for (int i = index - 1; i >= 0; i--) {
				SegmentsExperience targetSegmentsExperience =
					segmentsExperiences.get(i);

				_segmentsExperienceService.updateSegmentsExperiencePriority(
					segmentsExperienceId,
					targetSegmentsExperience.getPriority());

				if ((targetSegmentsExperience.getSegmentsExperienceId() ==
						SegmentsExperienceConstants.ID_DEFAULT) ||
					(segmentsExperience.getLayoutSetBranchId() ==
						targetSegmentsExperience.getLayoutSetBranchId())) {

					return;
				}
			}
		}
		else if (Objects.equals(direction, "down")) {
			for (int i = index + 1; i < segmentsExperiences.size(); i++) {
				SegmentsExperience targetSegmentsExperience =
					segmentsExperiences.get(i);

				_segmentsExperienceService.updateSegmentsExperiencePriority(
					segmentsExperienceId,
					targetSegmentsExperience.getPriority());

				if ((targetSegmentsExperience.getSegmentsExperienceId() ==
						SegmentsExperienceConstants.ID_DEFAULT) ||
					(segmentsExperience.getLayoutSetBranchId() ==
						targetSegmentsExperience.getLayoutSetBranchId())) {

					return;
				}
			}
		}
	}

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsExperienceLocalService _segmentsExperienceLocalService;

	@Reference
	private SegmentsExperienceService _segmentsExperienceService;

}