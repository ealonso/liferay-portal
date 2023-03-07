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

package com.liferay.fragment.web.internal.display.context;

import com.liferay.fragment.configuration.FragmentServiceConfigurationProvider;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class FragmentServiceConfigurationDisplayContext {

	public FragmentServiceConfigurationDisplayContext(
		FragmentServiceConfigurationProvider
			fragmentServiceConfigurationProvider,
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_fragmentServiceConfigurationProvider =
			fragmentServiceConfigurationProvider;
		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getProps() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"alreadyPropagateContributedFragmentChanges",
			_isAlreadyPropagateContributedFragmentChanges()
		).put(
			"editFragmentServiceConfigurationConfigurationURL",
			_getEditFragmentServiceConfigurationConfigurationURL()
		).put(
			"isFragmentServiceConfigurationDefined",
			_isFragmentServiceConfigurationDefined()
		).put(
			"isPropagateChanges", _isPropagateChanges()
		).put(
			"isPropagateContributedFragmentChanges",
			_isPropagateContributedFragmentChanges()
		).put(
			"namespace", _liferayPortletResponse.getNamespace()
		).put(
			"propagateContributedFragmentEntryChangesURL",
			_getPropagateContributedFragmentEntryChangesURL()
		).put(
			"redirectURL", ParamUtil.getString(_httpServletRequest, "redirect")
		).build();
	}

	private String _getEditFragmentServiceConfigurationConfigurationURL() {
		return PortletURLBuilder.createActionURL(
			_liferayPortletResponse
		).setActionName(
			"/instance_settings/edit_fragment_service_configuration"
		).setMVCRenderCommandName(
			"/configuration_admin/view_configuration_screen"
		).setRedirect(
			PortalUtil.getCurrentURL(_httpServletRequest)
		).setParameter(
			"configurationScreenKey", "fragments-service"
		).buildString();
	}

	private String _getPropagateContributedFragmentEntryChangesURL() {
		return PortletURLBuilder.createActionURL(
			_liferayPortletResponse
		).setActionName(
			"/instance_settings/propagate_contributed_fragment_entries_changes"
		).setMVCRenderCommandName(
			"/configuration_admin/view_configuration_screen"
		).setRedirect(
			PortalUtil.getCurrentURL(_httpServletRequest)
		).setParameter(
			"configurationScreenKey", "fragments-service"
		).buildString();
	}

	private boolean _isAlreadyPropagateContributedFragmentChanges() {
		PortletPreferences portletPreferences =
			PortalPreferencesLocalServiceUtil.getPreferences(
				_themeDisplay.getCompanyId(),
				PortletKeys.PREFS_OWNER_TYPE_COMPANY);

		return GetterUtil.getBoolean(
			portletPreferences.getValue(
				"propagateContributedFragmentChanges",
				Boolean.FALSE.toString()));
	}

	private boolean _isFragmentServiceConfigurationDefined() throws Exception {
		return _fragmentServiceConfigurationProvider.
			isFragmentServiceConfigurationDefined(_themeDisplay.getCompanyId());
	}

	private boolean _isPropagateChanges() {
		return _fragmentServiceConfigurationProvider.isPropagateChanges(
			_themeDisplay.getCompanyId());
	}

	private boolean _isPropagateContributedFragmentChanges() {
		return _fragmentServiceConfigurationProvider.
			isPropagateContributedFragmentChanges(_themeDisplay.getCompanyId());
	}

	private final FragmentServiceConfigurationProvider
		_fragmentServiceConfigurationProvider;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final ThemeDisplay _themeDisplay;

}