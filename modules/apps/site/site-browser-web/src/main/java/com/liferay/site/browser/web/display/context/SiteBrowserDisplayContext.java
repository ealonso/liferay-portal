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

package com.liferay.site.browser.web.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLUtil;
import com.liferay.portlet.sites.util.SitesUtil;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.portlet.usersadmin.search.GroupSearchTerms;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class SiteBrowserDisplayContext {

	public SiteBrowserDisplayContext(HttpServletRequest request) {
		_request = request;
	}

	public String getFilter() {
		if (_filter != null) {
			return _filter;
		}

		_filter = ParamUtil.getString(_request, "filter");

		return _filter;
	}

	public long getGroupId() {
		if (_groupId != null) {
			return _groupId;
		}

		_groupId = ParamUtil.getLong(_request, "groupId");

		return _groupId;
	}

	public LinkedHashMap<String, Object> getGroupParams()
		throws PortalException {

		if (_groupParams != null) {
			return _groupParams;
		}

		long groupId = ParamUtil.getLong(_request, "groupId");
		boolean includeCurrentGroup = ParamUtil.getBoolean(
			_request, "includeCurrentGroup", true);

		String type = getType();

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();
		User user = themeDisplay.getUser();

		boolean filterManageableGroups = true;

		if (permissionChecker.isCompanyAdmin()) {
			filterManageableGroups = false;
		}

		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<>();

		if (isManualMembership()) {
			groupParams.put("manualMembership", Boolean.TRUE);
		}

		if (type.equals("child-sites")) {
			Group parentGroup = GroupLocalServiceUtil.getGroup(groupId);

			List<Group> parentGroups = new ArrayList<>();

			parentGroups.add(parentGroup);

			groupParams.put("groupsTree", parentGroups);
		}
		else if (filterManageableGroups) {
			groupParams.put("usersGroups", user.getUserId());
		}

		groupParams.put("site", Boolean.TRUE);

		if (!includeCurrentGroup && (groupId > 0)) {
			List<Long> excludedGroupIds = new ArrayList<>();

			Group group = GroupLocalServiceUtil.getGroup(groupId);

			if (group.isStagingGroup()) {
				excludedGroupIds.add(group.getLiveGroupId());
			}
			else {
				excludedGroupIds.add(groupId);
			}

			groupParams.put("excludedGroupIds", excludedGroupIds);
		}

		_groupParams = groupParams;

		return _groupParams;
	}

	public GroupSearch getGroupSearch() throws Exception {
		if (_groupSearch != null) {
			return _groupSearch;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Company company = themeDisplay.getCompany();

		LiferayPortletRequest portletRequest = _getLiferayPortletRequest();

		GroupSearch groupSearch = new GroupSearch(
			portletRequest,
			PortletURLUtil.clone(
				getPortletURL(), _getLiferayPortletResponse()));

		GroupSearchTerms groupSearchTerms =
			(GroupSearchTerms)groupSearch.getSearchTerms();

		List<Group> results = new ArrayList<>();

		int additionalSites = 0;
		int total = 0;

		boolean includeCompany = ParamUtil.getBoolean(
			_request, "includeCompany");
		boolean includeUserPersonalSite = ParamUtil.getBoolean(
			_request, "includeUserPersonalSite");

		String type = getType();

		if (includeCompany) {
			if (groupSearch.getStart() == 0) {
				results.add(company.getGroup());
			}

			additionalSites++;
		}

		if (includeUserPersonalSite) {
			if (groupSearch.getStart() == 0) {
				Group userPersonalSite = GroupLocalServiceUtil.getGroup(
					company.getCompanyId(), GroupConstants.USER_PERSONAL_SITE);

				results.add(userPersonalSite);
			}

			additionalSites++;
		}

		if (type.equals("layoutScopes")) {
			total = GroupLocalServiceUtil.getGroupsCount(
					themeDisplay.getCompanyId(), Layout.class.getName(),
					getGroupId());
		}
		else if (type.equals("parent-sites")) {
		}
		else if (groupSearchTerms.isAdvancedSearch()) {
			total = GroupLocalServiceUtil.searchCount(
				themeDisplay.getCompanyId(), null, groupSearchTerms.getName(),
				groupSearchTerms.getDescription(), getGroupParams(),
				groupSearchTerms.isAndOperator());
		}
		else {
			total = GroupLocalServiceUtil.searchCount(
				themeDisplay.getCompanyId(), null,
				groupSearchTerms.getKeywords(), getGroupParams(),
				groupSearchTerms.isAndOperator());
		}

		total += additionalSites;

		groupSearch.setTotal(total);

		int start = groupSearch.getStart();

		if (groupSearch.getStart() > additionalSites) {
			start = groupSearch.getStart() - additionalSites;
		}

		int end = groupSearch.getEnd() - additionalSites;

		List<Group> groups = null;

		if (type.equals("layoutScopes")) {
			groups = GroupLocalServiceUtil.getGroups(
				company.getCompanyId(), Layout.class.getName(), getGroupId(),
				start, end);

			groups = _filterLayoutGroups(groups, isPrivateLayout());
		}
		else if (type.equals("parent-sites")) {
			Group group = GroupLocalServiceUtil.getGroup(getGroupId());

			groups = group.getAncestors();

			String filter = getFilter();

			if (Validator.isNotNull(filter)) {
				groups = _filterGroups(groups, filter);
			}

			total = groups.size();

			total += additionalSites;

			groupSearch.setTotal(total);
		}
		else if (groupSearchTerms.isAdvancedSearch()) {
			groups = GroupLocalServiceUtil.search(
				company.getCompanyId(), null, groupSearchTerms.getName(),
				groupSearchTerms.getDescription(), getGroupParams(),
				groupSearchTerms.isAndOperator(), start, end,
				groupSearch.getOrderByComparator());
		}
		else {
			groups = GroupLocalServiceUtil.search(
				company.getCompanyId(), null, groupSearchTerms.getKeywords(),
				getGroupParams(), start, end,
				groupSearch.getOrderByComparator());
		}

		results.addAll(groups);

		groupSearch.setResults(results);

		_groupSearch = groupSearch;

		return _groupSearch;
	}

	public PortletURL getPortletURL() throws PortalException {
		LiferayPortletResponse portletResponse = _getLiferayPortletResponse();

		PortletURL portletURL = portletResponse.createRenderURL();

		User selUser = PortalUtil.getSelectedUser(_request);

		String eventName = ParamUtil.getString(
			_request, "eventName",
			portletResponse.getNamespace() + "selectSite");
		boolean includeCompany = ParamUtil.getBoolean(
			_request, "includeCompany");
		boolean includeUserPersonalSite = ParamUtil.getBoolean(
			_request, "includeUserPersonalSite");
		long[] selectedGroupIds = StringUtil.split(
			ParamUtil.getString(_request, "selectedGroupIds"), 0L);
		String target = ParamUtil.getString(_request, "target");

		if (selUser != null) {
			portletURL.setParameter(
				"p_u_i_d", String.valueOf(selUser.getUserId()));
		}

		portletURL.setParameter("groupId", String.valueOf(getGroupId()));
		portletURL.setParameter(
			"selectedGroupIds", StringUtil.merge(selectedGroupIds));
		portletURL.setParameter("type", getType());
		portletURL.setParameter("types", getTypes());
		portletURL.setParameter("filter", getFilter());
		portletURL.setParameter(
			"includeCompany", String.valueOf(includeCompany));
		portletURL.setParameter(
			"includeUserPersonalSite", String.valueOf(includeUserPersonalSite));
		portletURL.setParameter(
			"manualMembership", String.valueOf(isManualMembership()));
		portletURL.setParameter("eventName", eventName);
		portletURL.setParameter("target", target);

		return portletURL;
	}

	public String getType() {
		if (_type != null) {
			return _type;
		}

		_type = ParamUtil.getString(_request, "type");

		String[] types = getTypes();

		if (Validator.isNull(_type)) {
			_type = types[0];
		}

		return _type;
	}

	public String[] getTypes() {
		if (_types != null) {
			return _types;
		}

		_types = ParamUtil.getParameterValues(_request, "types");

		if (_types.length == 0) {
			_types = new String[] {"sites-that-i-administer"};
		}

		return _types;
	}

	public Boolean isManualMembership() {
		if (_manualMembership != null) {
			return _manualMembership;
		}

		_manualMembership = ParamUtil.getBoolean(_request, "manualMembership");

		return _manualMembership;
	}

	public Boolean isPrivateLayout() {
		Boolean privateLayout = null;

		String privateLayoutString = _request.getParameter("privateLayout");

		if (Validator.isNotNull(privateLayoutString)) {
			privateLayout = GetterUtil.getBoolean(privateLayoutString);
		}

		return privateLayout;
	}

	private List<Group> _filterGroups(List<Group> groups, String filter)
		throws Exception {

		List<Group> filteredGroups = new ArrayList();

		for (Group group : groups) {
			if (filter.equals("contentSharingWithChildrenEnabled") &&
				SitesUtil.isContentSharingWithChildrenEnabled(group)) {

				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	private List<Group> _filterLayoutGroups(
			List<Group> groups, Boolean privateLayout)
		throws Exception {

		List<Group> filteredGroups = new ArrayList();

		if (privateLayout == null) {
			return groups;
		}

		for (Group group : groups) {
			if (!group.isLayout()) {
				continue;
			}

			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getClassPK());

			if (layout.isPrivateLayout() == privateLayout) {
				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	private LiferayPortletRequest _getLiferayPortletRequest() {
		PortletRequest portletRequest = (PortletRequest) _request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		return PortalUtil.getLiferayPortletRequest(portletRequest);
	}

	private LiferayPortletResponse _getLiferayPortletResponse() {
		PortletResponse portletResponse =
				(PortletResponse) _request.getAttribute(
					JavaConstants.JAVAX_PORTLET_RESPONSE);

		return PortalUtil.getLiferayPortletResponse(portletResponse);
	}

	private String _filter;
	private Long _groupId;
	private LinkedHashMap<String, Object> _groupParams;
	private GroupSearch _groupSearch;
	private Boolean _manualMembership;
	private final HttpServletRequest _request;
	private String _type;
	private String[] _types;

}