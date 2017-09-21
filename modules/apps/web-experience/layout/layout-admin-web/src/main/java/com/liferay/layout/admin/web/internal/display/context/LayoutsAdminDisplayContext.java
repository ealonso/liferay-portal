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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.layout.admin.web.internal.constants.LayoutAdminPortletKeys;
import com.liferay.layout.admin.web.internal.util.PagesPortletUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.LayoutDescription;
import com.liferay.portal.util.LayoutListUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletURL;

/**
 * @author Eudaldo Alonso
 */
public class LayoutsAdminDisplayContext extends BaseLayoutDisplayContext {

	public LayoutsAdminDisplayContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		super(liferayPortletRequest, liferayPortletResponse);

		_groupDisplayContextHelper = new GroupDisplayContextHelper(
			PortalUtil.getHttpServletRequest(liferayPortletRequest));

		this.liferayPortletRequest.setAttribute(
			WebKeys.LAYOUT_DESCRIPTIONS, getLayoutDescriptions());
	}

	@Override
	public PortletURL getAddLayoutURL(long selPlid, Boolean privateLayout) {
		PortletURL addLayoutURL = super.getAddLayoutURL(selPlid, privateLayout);

		addLayoutURL.setParameter(
			"backURL",
			PortalUtil.getCurrentURL(
				PortalUtil.getHttpServletRequest(liferayPortletRequest)));

		return addLayoutURL;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				liferayPortletRequest);

		_displayStyle = portalPreferences.getValue(
			LayoutAdminPortletKeys.GROUP_PAGES, "display-style", "icon");

		return _displayStyle;
	}

	public PortletURL getEditLayoutURL() {
		PortletURL editLayoutURL = super.getEditLayoutURL(
			getSelPlid(), isPrivateLayout());

		editLayoutURL.setParameter("redirect", getRedirect());

		return editLayoutURL;
	}

	public Group getGroup() {
		return _groupDisplayContextHelper.getGroup();
	}

	public Long getGroupId() {
		return _groupDisplayContextHelper.getGroupId();
	}

	public UnicodeProperties getGroupTypeSettings() {
		return _groupDisplayContextHelper.getGroupTypeSettings();
	}

	public JSONArray getLayoutBlocksJSONArray() throws Exception {
		List<JSONArray> layoutBlocksList = new ArrayList<>();

		JSONArray layoutBlocksJSONArray = JSONFactoryUtil.createJSONArray();

		long activeLayoutId = getSelectedLayoutId();

		Layout selectedLayout = LayoutLocalServiceUtil.fetchLayout(
			getGroupId(), isPrivateLayout(), activeLayoutId);

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Locale locale = themeDisplay.getLocale();

		if (selectedLayout != null) {
			layoutBlocksList.add(
				getLayoutsJSONArray(selectedLayout.getLayoutId()));
		}

		while (selectedLayout != null) {
			selectedLayout = LayoutLocalServiceUtil.fetchLayout(
				getGroupId(), isPrivateLayout(),
				selectedLayout.getParentLayoutId());

			if (selectedLayout != null) {
				layoutBlocksList.add(
					getLayoutsJSONArray(
						selectedLayout.getLayoutId(), activeLayoutId));

				activeLayoutId = selectedLayout.getLayoutId();
			}
		}

		layoutBlocksList.add(getLayoutsJSONArray(0, activeLayoutId));

		Collections.reverse(layoutBlocksList);

		for (JSONArray layoutBlockJSONArray : layoutBlocksList) {
			layoutBlocksJSONArray.put(layoutBlockJSONArray);
		}

		return layoutBlocksJSONArray;
	}

	public List<LayoutDescription> getLayoutDescriptions() {
		if (_layoutDescriptions != null) {
			return _layoutDescriptions;
		}

		_layoutDescriptions = LayoutListUtil.getLayoutDescriptions(
			getGroupId(), isPrivateLayout(), getRootNodeName(),
			themeDisplay.getLocale());

		return _layoutDescriptions;
	}

	@Override
	public Long getLayoutId() {
		if (_layoutId != null) {
			return _layoutId;
		}

		_layoutId = LayoutConstants.DEFAULT_PARENT_LAYOUT_ID;

		Layout selLayout = getSelLayout();

		if (selLayout != null) {
			_layoutId = selLayout.getLayoutId();
		}

		return _layoutId;
	}

	public JSONArray getLayoutsJSONArray(long parentLayoutId) throws Exception {
		return getLayoutsJSONArray(parentLayoutId, getSelectedLayoutId());
	}

	public JSONArray getLayoutsJSONArray(long parentLayoutId, long selectedId)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		JSONArray jsonArray;

		OrderByComparator orderByComparator =
			PagesPortletUtil.getLayoutOrderByComparator(
				getOrderByCol(), getOrderByType());

		jsonArray = PagesPortletUtil.getLayoutsJSONArray(
			themeDisplay.getScopeGroupId(), isPrivateLayout(), parentLayoutId,
			selectedId, orderByComparator, liferayPortletRequest);

		return jsonArray;
	}

	@Override
	public Group getLiveGroup() {
		return _groupDisplayContextHelper.getLiveGroup();
	}

	public Long getLiveGroupId() {
		return _groupDisplayContextHelper.getLiveGroupId();
	}

	public String getNavigation() {
		if (_navigation != null) {
			return _navigation;
		}

		_navigation = ParamUtil.getString(
			liferayPortletRequest, "navigation", "public-pages");

		return _navigation;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			liferayPortletRequest, "orderByCol", "create-date");

		return _orderByCol;
	}

	public JSONObject getOrderByJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("orderByCol", getOrderByCol());
		jsonObject.put("orderByType", getOrderByType());

		return jsonObject;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			liferayPortletRequest, "orderByType", "asc");

		return _orderByType;
	}

	public String[] getOrderColumns() {
		return new String[] {"create-date"};
	}

	public Organization getOrganization() {
		if (_organization != null) {
			return _organization;
		}

		Group liveGroup = getLiveGroup();

		if (liveGroup.isOrganization()) {
			_organization = OrganizationLocalServiceUtil.fetchOrganization(
				liveGroup.getOrganizationId());
		}

		return _organization;
	}

	public String getPagesName() {
		if (_pagesName != null) {
			return _pagesName;
		}

		Group liveGroup = getLiveGroup();

		if (liveGroup.isLayoutPrototype() || liveGroup.isLayoutSetPrototype() ||
			liveGroup.isUserGroup()) {

			_pagesName = "pages";
		}
		else if (isPrivateLayout()) {
			if (liveGroup.isUser()) {
				_pagesName = "my-dashboard";
			}
			else {
				_pagesName = "private-pages";
			}
		}
		else {
			if (liveGroup.isUser()) {
				_pagesName = "my-profile";
			}
			else {
				_pagesName = "public-pages";
			}
		}

		return _pagesName;
	}

	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("millerColumns", String.valueOf(Boolean.TRUE));
		portletURL.setParameter("navigation", getNavigation());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public String getRedirect() {
		if (_redirect != null) {
			return _redirect;
		}

		_redirect = ParamUtil.getString(liferayPortletRequest, "redirect");

		return _redirect;
	}

	public PortletURL getRedirectURL() {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/view.jsp");
		portletURL.setParameter("redirect", getRedirect());
		portletURL.setParameter("groupId", String.valueOf(getSelGroupId()));

		return portletURL;
	}

	public long getSelectedLayoutId() {
		if (_selectedLayoutId != null) {
			return _selectedLayoutId;
		}

		_selectedLayoutId = ParamUtil.getLong(
			liferayPortletRequest, "selectedLayoutId", 0);

		return _selectedLayoutId;
	}

	@Override
	public Group getSelGroup() {
		return _groupDisplayContextHelper.getSelGroup();
	}

	@Override
	public long getSelGroupId() {
		Group selGroup = getSelGroup();

		if (selGroup != null) {
			return selGroup.getGroupId();
		}

		return 0;
	}

	public User getSelUser() {
		if (_selUser != null) {
			return _selUser;
		}

		Group liveGroup = getLiveGroup();

		if (liveGroup.isUser()) {
			_selUser = UserLocalServiceUtil.fetchUser(liveGroup.getClassPK());
		}

		return _selUser;
	}

	@Override
	public Group getStagingGroup() {
		return _groupDisplayContextHelper.getStagingGroup();
	}

	public Long getStagingGroupId() {
		return _groupDisplayContextHelper.getStagingGroupId();
	}

	public UserGroup getUserGroup() {
		if (_userGroup != null) {
			return _userGroup;
		}

		Group liveGroup = getLiveGroup();

		if (liveGroup.isUserGroup()) {
			_userGroup = UserGroupLocalServiceUtil.fetchUserGroup(
				liveGroup.getClassPK());
		}

		return _userGroup;
	}

	public boolean isMillerColumnsEnabled() {
		if (_millerColumnsEnabled != null) {
			return _millerColumnsEnabled;
		}

		_millerColumnsEnabled = ParamUtil.getBoolean(
			liferayPortletRequest, "millerColumns");

		return _millerColumnsEnabled;
	}

	public boolean isPrivateLayout() {
		boolean privateLayout = ParamUtil.getBoolean(
			liferayPortletRequest, "privateLayout");

		if (privateLayout) {
			return true;
		}

		if (Objects.equals(getNavigation(), "private-pages")) {
			return true;
		}

		return false;
	}

	public JSONObject jsonObjectFromLayout(
		Layout layout, boolean active, Locale locale) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("actions", JSONFactoryUtil.createJSONObject());
		jsonObject.put("active", active);
		jsonObject.put("hasChild", layout.getChildren().size() > 0);
		jsonObject.put("icon", "");
		jsonObject.put("layoutId", layout.getLayoutId());
		jsonObject.put("selected", active);
		jsonObject.put("title", layout.getName(locale));

		return jsonObject;
	}

	protected boolean hasPowerUserRole() {
		try {
			User selUser = getSelUser();

			return RoleLocalServiceUtil.hasUserRole(
				selUser.getUserId(), themeDisplay.getCompanyId(),
				RoleConstants.POWER_USER, true);
		}
		catch (Exception e) {
		}

		return false;
	}

	protected boolean isPrivateLayoutsModifiable() {
		if ((!PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_POWER_USER_REQUIRED ||
			 hasPowerUserRole()) &&
			PropsValues.LAYOUT_USER_PRIVATE_LAYOUTS_ENABLED) {

			return true;
		}

		return false;
	}

	protected boolean isPublicLayoutsModifiable() {
		if ((!PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_POWER_USER_REQUIRED ||
			 hasPowerUserRole()) &&
			PropsValues.LAYOUT_USER_PUBLIC_LAYOUTS_ENABLED) {

			return true;
		}

		return false;
	}

	private String _displayStyle;
	private final GroupDisplayContextHelper _groupDisplayContextHelper;
	private List<LayoutDescription> _layoutDescriptions;
	private Long _layoutId;
	private Boolean _millerColumnsEnabled;
	private String _navigation;
	private String _orderByCol;
	private String _orderByType;
	private Organization _organization;
	private String _pagesName;
	private String _redirect;
	private Long _selectedLayoutId;
	private User _selUser;
	private UserGroup _userGroup;

}