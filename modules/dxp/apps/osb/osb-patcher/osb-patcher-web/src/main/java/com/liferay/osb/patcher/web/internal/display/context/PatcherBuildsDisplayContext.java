/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.patcher.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.osb.patcher.constants.PatcherActionKeys;
import com.liferay.osb.patcher.constants.PatcherBuildConstants;
import com.liferay.osb.patcher.constants.WorkflowConstants;
import com.liferay.osb.patcher.model.PatcherBuild;
import com.liferay.osb.patcher.permission.resource.PatcherPermission;
import com.liferay.osb.patcher.service.PatcherBuildLocalServiceUtil;
import com.liferay.osb.patcher.util.JenkinsUtil;
import com.liferay.osb.patcher.util.PatcherBuildRelUtil;
import com.liferay.osb.patcher.util.PatcherBuildUtil;
import com.liferay.osb.patcher.util.PatcherUtil;
import com.liferay.petra.function.transform.TransformUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchResultUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class PatcherBuildsDisplayContext {

	public PatcherBuildsDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getDropdownItems(PatcherBuild patcherBuild) {
		return DropdownItemListBuilder.add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.EDIT, patcherBuild.getUserId()) &&
				PatcherBuildUtil.isLatestPatcherBuild(patcherBuild) &&
				(patcherBuild.getType() != PatcherBuildConstants.TYPE_FIX_PACK),
			dropdownItem -> {
				dropdownItem.setHref(
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/edit_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).buildString());
				dropdownItem.setIcon("pencil");
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "edit"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.EDIT, patcherBuild.getUserId()) &&
				PatcherBuildUtil.isLatestPatcherBuild(patcherBuild) &&
				(patcherBuild.getType() != PatcherBuildConstants.TYPE_FIX_PACK),
			dropdownItem -> {
				dropdownItem.setHref(
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/add_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).buildString());
				dropdownItem.setIcon("pencil");
				dropdownItem.setLabel(
					LanguageUtil.get(
						_httpServletRequest, "use-as-build-template"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.EDIT_COMMENTS_FIELD,
					patcherBuild.getUserId()) &&
				(patcherBuild.getType() != PatcherBuildConstants.TYPE_FIX_PACK),
			dropdownItem -> {
				dropdownItem.putData("action", "openModal");
				dropdownItem.putData(
					"editPatcherBuildCommentsFieldURL",
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/edit_comments_field_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setWindowState(
						LiferayWindowState.POP_UP
					).buildString());
				dropdownItem.putData(
					"title",
					UnicodeLanguageUtil.format(
						_httpServletRequest,
						"edit-engineer-comments-for-build-id-x",
						patcherBuild.getPatcherBuildId()));
				dropdownItem.setIcon("pencil");
				dropdownItem.setLabel(
					LanguageUtil.get(
						_httpServletRequest, "edit-engineer-comments"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.EDIT_QA_FIELDS,
					patcherBuild.getUserId()) &&
				(patcherBuild.getType() != PatcherBuildConstants.TYPE_FIX_PACK),
			dropdownItem -> {
				dropdownItem.putData("action", "openModal");
				dropdownItem.putData(
					"editPatcherBuildQAFieldsURL",
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/edit_qa_fields_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setWindowState(
						LiferayWindowState.POP_UP
					).buildString());
				dropdownItem.putData(
					"title",
					UnicodeLanguageUtil.format(
						_httpServletRequest, "edit-qa-status-for-build-id-x",
						patcherBuild.getPatcherBuildId()));
				dropdownItem.setIcon("pencil");
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "edit-qa-status"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.SEND_REQUEST, patcherBuild.getUserId()) &&
				JenkinsUtil.isValidJenkinsSetup() &&
				JenkinsUtil.isValidSendDistJenkinsRequest(patcherBuild) &&
				(patcherBuild.getType() != PatcherBuildConstants.TYPE_FIX_PACK),
			dropdownItem -> {
				dropdownItem.putData("action", "buildPatcherBuild");
				dropdownItem.putData(
					"buildPatcherBuildURL",
					PortletURLBuilder.createActionURL(
						_renderResponse
					).setActionName(
						"/patcher/build_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).buildString());
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "build"));
			}
		).add(
			() ->
				patcherBuild.getStatus() ==
					WorkflowConstants.STATUS_BUILD_COMPLETE,
			dropdownItem -> {
				dropdownItem.putData("action", "testPatcherBuild");
				dropdownItem.putData(
					"testPatcherBuildURL",
					PortletURLBuilder.createActionURL(
						_renderResponse
					).setActionName(
						"/patcher/test_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setParameter(
						"status",
						WorkflowConstants.STATUS_BUILD_QA_AUTOMATION_STARTED
					).buildString());
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "test"));
			}
		).add(
			() ->
				patcherBuild.getStatus() ==
					WorkflowConstants.STATUS_BUILD_COMPLETE,
			dropdownItem -> {
				dropdownItem.putData("action", "smokeTestPatcherBuild");
				dropdownItem.putData(
					"smokeTestPatcherBuildURL",
					PortletURLBuilder.createActionURL(
						_renderResponse
					).setActionName(
						"/patcher/test_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setParameter(
						"status",
						WorkflowConstants.
							STATUS_BUILD_QA_AUTOMATION_STARTED_SMOKE_ONLY
					).buildString());
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "smoke-test"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.FIXES, patcherBuild.getUserId()) &&
				!PatcherBuildRelUtil.hasChildPatcherBuilds(patcherBuild),
			dropdownItem -> {
				dropdownItem.putData("action", "openModal");
				dropdownItem.putData(
					"title",
					UnicodeLanguageUtil.format(
						_httpServletRequest, "view-fixes-for-build-id-x",
						patcherBuild.getPatcherBuildId()));
				dropdownItem.putData(
					"viewPatcherBuildPatcherFixesURL",
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/view_fixes_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setWindowState(
						LiferayWindowState.POP_UP
					).buildString());
				dropdownItem.setIcon("view");
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "view-fixes"));
			}
		).add(
			() ->
				PatcherPermission.contains(
					_themeDisplay.getPermissionChecker(), patcherBuild,
					PatcherActionKeys.CHILD_BUILDS, patcherBuild.getUserId()) &&
				PatcherBuildRelUtil.hasChildPatcherBuilds(patcherBuild),
			dropdownItem -> {
				dropdownItem.putData("action", "openModal");
				dropdownItem.putData(
					"title",
					UnicodeLanguageUtil.format(
						_httpServletRequest, "view-child-builds-for-build-id-x",
						patcherBuild.getPatcherBuildId()));
				dropdownItem.putData(
					"viewChildPatcherBuildsURL",
					PortletURLBuilder.createRenderURL(
						_renderResponse
					).setMVCRenderCommandName(
						"/patcher/view_child_builds_builds"
					).setRedirect(
						_themeDisplay.getURLCurrent()
					).setParameter(
						"patcherBuildId", patcherBuild.getPatcherBuildId()
					).setWindowState(
						LiferayWindowState.POP_UP
					).buildString());
				dropdownItem.setIcon("view");
				dropdownItem.setLabel(
					LanguageUtil.get(_httpServletRequest, "view-child-builds"));
			}
		).build();
	}

	public SearchContainer<PatcherBuild> getSearchContainer() throws Exception {
		if (_patcherBuildSearchContainer != null) {
			return _patcherBuildSearchContainer;
		}

		SearchContainer<PatcherBuild> patcherBuildSearchContainer =
			new SearchContainer<>(
				_renderRequest, _renderResponse.createRenderURL(), null,
				"there-are-no-builds");

		Indexer<PatcherBuild> indexer = IndexerRegistryUtil.getIndexer(
			PatcherBuild.class);

		SearchContext searchContext = SearchContextFactory.getInstance(
			_httpServletRequest);

		boolean advancedSearch = ParamUtil.getBoolean(
			_httpServletRequest, "advancedSearch");

		if (!advancedSearch) {
			searchContext.setAttribute("buildsIndexSearch", Boolean.TRUE);
		}

		searchContext.setEnd(patcherBuildSearchContainer.getEnd());
		searchContext.setGroupIds(null);

		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		String patcherBuildName = ParamUtil.getString(
			_httpServletRequest, "patcherBuildName");

		if ((!PatcherUtil.isPatcherTickets(keywords) ||
			 PatcherUtil.isPatcherProjectVersionName(keywords)) &&
			!PatcherUtil.isPatcherTickets(patcherBuildName)) {

			searchContext.setSorts(
				new Sort("statusDate", Sort.LONG_TYPE, true));
		}

		searchContext.setStart(patcherBuildSearchContainer.getStart());

		Hits hits = indexer.search(searchContext);

		patcherBuildSearchContainer.setResultsAndTotal(
			() -> TransformUtil.transform(
				SearchResultUtil.getSearchResults(
					hits, LocaleUtil.getDefault()),
				searchResult -> PatcherBuildLocalServiceUtil.fetchPatcherBuild(
					searchResult.getClassPK())),
			hits.getLength());

		_patcherBuildSearchContainer = patcherBuildSearchContainer;

		return _patcherBuildSearchContainer;
	}

	private final HttpServletRequest _httpServletRequest;
	private SearchContainer<PatcherBuild> _patcherBuildSearchContainer;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}