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

package com.liferay.layout.content.page.editor.web.internal.display.context;

import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.layout.content.page.editor.sidebar.panel.ContentPageEditorSidebarPanel;
import com.liferay.layout.content.page.editor.web.internal.constants.ContentPageEditorActionKeys;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructureRel;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureRelLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.constants.SegmentsExperienceConstants;
import com.liferay.segments.constants.SegmentsExperimentConstants;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.service.SegmentsEntryServiceUtil;
import com.liferay.segments.service.SegmentsExperienceLocalServiceUtil;
import com.liferay.segments.service.SegmentsExperienceServiceUtil;
import com.liferay.segments.service.SegmentsExperimentLocalServiceUtil;
import com.liferay.staging.StagingGroupHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class ContentPageLayoutEditorDisplayContext
	extends ContentPageEditorDisplayContext {

	public ContentPageLayoutEditorDisplayContext(
		HttpServletRequest httpServletRequest, RenderResponse renderResponse,
		CommentManager commentManager,
		List<ContentPageEditorSidebarPanel> contentPageEditorSidebarPanels,
		FragmentRendererController fragmentRendererController,
		StagingGroupHelper stagingGroupHelper) {

		super(
			httpServletRequest, renderResponse, commentManager,
			contentPageEditorSidebarPanels, fragmentRendererController);

		_stagingGroupHelper = stagingGroupHelper;
	}

	@Override
	public String getPublishURL() {
		return getFragmentEntryActionURL("/content_layout/publish_layout");
	}

	@Override
	public boolean isSingleSegmentsExperienceMode() {
		long segmentsExperienceId = ParamUtil.getLong(
			PortalUtil.getOriginalServletRequest(httpServletRequest),
			"segmentsExperienceId", -1);

		if (segmentsExperienceId == -1) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isWorkflowEnabled() throws PortalException {
		Layout publishedLayout = getPublishedLayout();

		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			publishedLayout.getCompanyId(), publishedLayout.getGroupId(),
			Layout.class.getName());
	}

	@Override
	protected Map<String, Object> getPermissionsState() throws Exception {
		if (!_isShowSegmentsExperiences()) {
			return Collections.emptyMap();
		}

		return HashMapBuilder.<String, Object>put(
			ContentPageEditorActionKeys.LOCKED_SEGMENTS_EXPERIMENT,
			_isLockedSegmentsExperience(getSegmentsExperienceId())
		).put(
			ContentPageEditorActionKeys.UPDATE, hasUpdatePermissions()
		).put(
			ContentPageEditorActionKeys.UPDATE_LAYOUT_CONTENT,
			hasUpdateContentPermissions()
		).build();
	}

	@Override
	protected Map<String, Object> getSegmentsConfig() throws Exception {
		if (!_isShowSegmentsExperiences()) {
			return super.getSegmentsConfig();
		}

		return HashMapBuilder.<String, Object>put(
			"addSegmentsExperienceURL",
			getFragmentEntryActionURL("/content_layout/add_segments_experience")
		).put(
			"availableSegmentsEntries", _getAvailableSegmentsEntries()
		).put(
			"defaultSegmentsEntryId", SegmentsEntryConstants.ID_DEFAULT
		).put(
			"defaultSegmentsExperienceId",
			String.valueOf(SegmentsExperienceConstants.ID_DEFAULT)
		).put(
			"deleteSegmentsExperienceURL",
			getFragmentEntryActionURL(
				"/content_layout/delete_segments_experience")
		).put(
			"editSegmentsEntryURL", _getEditSegmentsEntryURL()
		).put(
			"hasEditSegmentsEntryPermission", _hasEditSegmentsEntryPermission()
		).put(
			"layoutDataList", _getLayoutDataList()
		).put(
			"singleSegmentsExperienceMode", isSingleSegmentsExperienceMode()
		).put(
			"updateSegmentsExperiencePriorityURL",
			getFragmentEntryActionURL(
				"/content_layout/update_segments_experience_priority")
		).put(
			"updateSegmentsExperienceURL",
			getFragmentEntryActionURL(
				"/content_layout/update_segments_experience")
		).build();
	}

	@Override
	protected long getSegmentsExperienceId() {
		if (_segmentsExperienceId != null) {
			return _segmentsExperienceId;
		}

		_segmentsExperienceId = SegmentsExperienceConstants.ID_DEFAULT;

		long selectedSegmentsExperienceId = ParamUtil.getLong(
			PortalUtil.getOriginalServletRequest(httpServletRequest),
			"segmentsExperienceId", -1);

		if ((selectedSegmentsExperienceId != -1) &&
			(selectedSegmentsExperienceId !=
				SegmentsExperienceConstants.ID_DEFAULT)) {

			SegmentsExperience segmentsExperience =
				SegmentsExperienceLocalServiceUtil.fetchSegmentsExperience(
					selectedSegmentsExperienceId);

			if (segmentsExperience != null) {
				_segmentsExperienceId =
					segmentsExperience.getSegmentsExperienceId();
			}
		}

		return _segmentsExperienceId;
	}

	@Override
	protected Map<String, Object> getSegmentsState() throws Exception {
		if (!_isShowSegmentsExperiences()) {
			return super.getSegmentsState();
		}

		return HashMapBuilder.<String, Object>put(
			"availableSegmentsExperiences", _getAvailableSegmentsExperiences()
		).put(
			"segmentsExperienceId", String.valueOf(getSegmentsExperienceId())
		).put(
			"segmentsExperimentStatus",
			_getSegmentsExperimentStatus(getSegmentsExperienceId())
		).build();
	}

	@Override
	protected List<Map<String, Object>> getSidebarPanels() {
		return super.getSidebarPanels(false);
	}

	private Map<String, Object> _getAvailableSegmentsEntries() {
		Map<String, Object> availableSegmentsEntries = new HashMap<>();

		List<SegmentsEntry> segmentsEntries =
			SegmentsEntryServiceUtil.getSegmentsEntries(
				_getStagingAwareGroupId(), true);

		for (SegmentsEntry segmentsEntry : segmentsEntries) {
			availableSegmentsEntries.put(
				String.valueOf(segmentsEntry.getSegmentsEntryId()),
				HashMapBuilder.<String, Object>put(
					"name", segmentsEntry.getName(themeDisplay.getLocale())
				).put(
					"segmentsEntryId",
					String.valueOf(segmentsEntry.getSegmentsEntryId())
				).build());
		}

		availableSegmentsEntries.put(
			String.valueOf(SegmentsEntryConstants.ID_DEFAULT),
			HashMapBuilder.<String, Object>put(
				"name",
				SegmentsEntryConstants.getDefaultSegmentsEntryName(
					themeDisplay.getLocale())
			).put(
				"segmentsEntryId", SegmentsEntryConstants.ID_DEFAULT
			).build());

		return availableSegmentsEntries;
	}

	private Map<String, Object> _getAvailableSegmentsExperiences()
		throws PortalException {

		Map<String, Object> availableSegmentsExperiences = new HashMap<>();

		Layout draftLayout = themeDisplay.getLayout();

		Layout layout = LayoutLocalServiceUtil.getLayout(
			draftLayout.getClassPK());

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			layout, themeDisplay);

		List<SegmentsExperience> segmentsExperiences =
			SegmentsExperienceServiceUtil.getSegmentsExperiences(
				getGroupId(), PortalUtil.getClassNameId(Layout.class.getName()),
				themeDisplay.getPlid(), true);

		for (SegmentsExperience segmentsExperience : segmentsExperiences) {
			availableSegmentsExperiences.put(
				String.valueOf(segmentsExperience.getSegmentsExperienceId()),
				HashMapBuilder.<String, Object>put(
					"hasLockedSegmentsExperiment",
					segmentsExperience.hasSegmentsExperiment()
				).put(
					"name", segmentsExperience.getName(themeDisplay.getLocale())
				).put(
					"priority", segmentsExperience.getPriority()
				).put(
					"segmentsEntryId",
					String.valueOf(segmentsExperience.getSegmentsEntryId())
				).put(
					"segmentsExperienceId",
					String.valueOf(segmentsExperience.getSegmentsExperienceId())
				).put(
					"segmentsExperimentStatus",
					_getSegmentsExperimentStatus(
						segmentsExperience.getSegmentsExperienceId())
				).put(
					"segmentsExperimentURL",
					_getSegmentsExperimentURL(
						layoutFullURL,
						segmentsExperience.getSegmentsExperienceId())
				).build());
		}

		availableSegmentsExperiences.put(
			String.valueOf(SegmentsExperienceConstants.ID_DEFAULT),
			HashMapBuilder.<String, Object>put(
				"hasLockedSegmentsExperiment",
				_hasDefaultSegmentsExperienceLockedSegmentsExperiment()
			).put(
				"name",
				SegmentsExperienceConstants.getDefaultSegmentsExperienceName(
					themeDisplay.getLocale())
			).put(
				"priority", SegmentsExperienceConstants.PRIORITY_DEFAULT
			).put(
				"segmentsEntryId",
				String.valueOf(SegmentsEntryConstants.ID_DEFAULT)
			).put(
				"segmentsExperienceId",
				String.valueOf(SegmentsExperienceConstants.ID_DEFAULT)
			).put(
				"segmentsExperimentStatus",
				_getSegmentsExperimentStatus(
					SegmentsExperienceConstants.ID_DEFAULT)
			).put(
				"segmentsExperimentURL",
				_getSegmentsExperimentURL(
					layoutFullURL, SegmentsExperienceConstants.ID_DEFAULT)
			).build());

		return availableSegmentsExperiences;
	}

	private String _getEditSegmentsEntryURL() throws PortalException {
		if (_editSegmentsEntryURL != null) {
			return _editSegmentsEntryURL;
		}

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, SegmentsEntry.class.getName(),
			PortletProvider.Action.EDIT);

		if (portletURL == null) {
			_editSegmentsEntryURL = StringPool.BLANK;
		}
		else {
			portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

			_editSegmentsEntryURL = portletURL.toString();
		}

		return _editSegmentsEntryURL;
	}

	private List<Map<String, Object>> _getLayoutDataList()
		throws PortalException {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			LayoutPageTemplateStructureLocalServiceUtil.
				fetchLayoutPageTemplateStructure(
					themeDisplay.getScopeGroupId(),
					PortalUtil.getClassNameId(Layout.class.getName()),
					themeDisplay.getPlid(), true);

		if (layoutPageTemplateStructure == null) {
			return Collections.emptyList();
		}

		List<Map<String, Object>> layoutDataList = new ArrayList<>();

		List<LayoutPageTemplateStructureRel> layoutPageTemplateStructureRels =
			LayoutPageTemplateStructureRelLocalServiceUtil.
				getLayoutPageTemplateStructureRels(
					layoutPageTemplateStructure.
						getLayoutPageTemplateStructureId());

		for (LayoutPageTemplateStructureRel layoutPageTemplateStructureRel :
				layoutPageTemplateStructureRels) {

			layoutDataList.add(
				HashMapBuilder.<String, Object>put(
					"layoutData",
					JSONFactoryUtil.createJSONObject(
						layoutPageTemplateStructureRel.getData())
				).put(
					"segmentsExperienceId",
					layoutPageTemplateStructureRel.getSegmentsExperienceId()
				).build());
		}

		return layoutDataList;
	}

	private Optional<SegmentsExperiment> _getSegmentsExperimentOptional(
			long segmentsExperienceId)
		throws PortalException {

		Layout draftLayout = themeDisplay.getLayout();

		Layout layout = LayoutLocalServiceUtil.getLayout(
			draftLayout.getClassPK());

		return Optional.ofNullable(
			SegmentsExperimentLocalServiceUtil.fetchSegmentsExperiment(
				segmentsExperienceId, PortalUtil.getClassNameId(Layout.class),
				layout.getPlid(),
				SegmentsExperimentConstants.Status.getExclusiveStatusValues()));
	}

	private Map<String, Object> _getSegmentsExperimentStatus(
			long segmentsExperienceId)
		throws PortalException {

		Optional<SegmentsExperiment> segmentsExperimentOptional =
			_getSegmentsExperimentOptional(segmentsExperienceId);

		if (!segmentsExperimentOptional.isPresent()) {
			return null;
		}

		SegmentsExperiment segmentsExperiment =
			segmentsExperimentOptional.get();

		SegmentsExperimentConstants.Status status =
			SegmentsExperimentConstants.Status.valueOf(
				segmentsExperiment.getStatus());

		return HashMapBuilder.<String, Object>put(
			"label",
			LanguageUtil.get(
				ResourceBundleUtil.getBundle(
					themeDisplay.getLocale(), getClass()),
				status.getLabel())
		).put(
			"value", status.getValue()
		).build();
	}

	private String _getSegmentsExperimentURL(
		String layoutFullURL, long segmentsExperienceId) {

		HttpUtil.addParameter(
			layoutFullURL, "p_l_back_url", themeDisplay.getURLCurrent());

		return HttpUtil.addParameter(
			layoutFullURL, "segmentsExperienceId", segmentsExperienceId);
	}

	private long _getStagingAwareGroupId() {
		long groupId = getGroupId();

		if (_stagingGroupHelper.isStagingGroup(groupId) &&
			!_stagingGroupHelper.isStagedPortlet(
				groupId, SegmentsPortletKeys.SEGMENTS)) {

			Group group = _stagingGroupHelper.fetchLiveGroup(groupId);

			if (group != null) {
				groupId = group.getGroupId();
			}
		}

		return groupId;
	}

	private boolean _hasDefaultSegmentsExperienceLockedSegmentsExperiment()
		throws PortalException {

		Optional<SegmentsExperiment> segmentsExperimentOptional =
			_getSegmentsExperimentOptional(
				SegmentsExperienceConstants.ID_DEFAULT);

		if (!segmentsExperimentOptional.isPresent()) {
			return false;
		}

		SegmentsExperiment segmentsExperiment =
			segmentsExperimentOptional.get();

		List<Integer> lockedStatusValuesList = ListUtil.fromArray(
			SegmentsExperimentConstants.Status.getLockedStatusValues());

		return lockedStatusValuesList.contains(segmentsExperiment.getStatus());
	}

	private boolean _hasEditSegmentsEntryPermission() throws PortalException {
		String editSegmentsEntryURL = _getEditSegmentsEntryURL();

		if (Validator.isNull(editSegmentsEntryURL)) {
			return false;
		}

		return true;
	}

	private Boolean _isLockedSegmentsExperience(long segmentsExperienceId)
		throws PortalException {

		if (_lockedSegmentsExperience != null) {
			return _lockedSegmentsExperience;
		}

		if (SegmentsExperienceConstants.ID_DEFAULT == segmentsExperienceId) {
			_lockedSegmentsExperience =
				_hasDefaultSegmentsExperienceLockedSegmentsExperiment();
		}
		else {
			SegmentsExperience segmentsExperience =
				SegmentsExperienceLocalServiceUtil.getSegmentsExperience(
					segmentsExperienceId);

			_lockedSegmentsExperience =
				segmentsExperience.hasSegmentsExperiment();
		}

		return _lockedSegmentsExperience;
	}

	private boolean _isShowSegmentsExperiences() throws PortalException {
		if (_showSegmentsExperiences != null) {
			return _showSegmentsExperiences;
		}

		Group group = GroupLocalServiceUtil.getGroup(getGroupId());

		if (!group.isLayoutSetPrototype() && !group.isUser()) {
			_showSegmentsExperiences = true;
		}
		else {
			_showSegmentsExperiences = false;
		}

		return _showSegmentsExperiences;
	}

	private String _editSegmentsEntryURL;
	private Boolean _lockedSegmentsExperience;
	private Long _segmentsExperienceId;
	private Boolean _showSegmentsExperiences;
	private final StagingGroupHelper _stagingGroupHelper;

}