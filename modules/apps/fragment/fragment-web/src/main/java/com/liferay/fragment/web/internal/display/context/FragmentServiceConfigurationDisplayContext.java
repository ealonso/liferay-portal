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
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

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

	public String getEditFragmentServiceConfigurationConfigurationURL() {
		return PortletURLBuilder.createActionURL(
			_liferayPortletResponse
		).setActionName(
			"/instance_settings/edit_fragment_service_configuration"
		).setRedirect(
			PortalUtil.getCurrentURL(_httpServletRequest)
		).buildString();
	}

	public String getPropagateContributedFragmentEntryChangesURL() {
		return PortletURLBuilder.createActionURL(
			_liferayPortletResponse
		).setActionName(
			"/fragment/propagate_contributed_fragment_entries_changes"
		).setRedirect(
			PortalUtil.getCurrentURL(_httpServletRequest)
		).buildString();
	}

	public boolean isFragmentServiceConfigurationDefined()
		throws ConfigurationException {

		return _fragmentServiceConfigurationProvider.
			isFragmentServiceConfigurationDefined(_themeDisplay.getCompanyId());
	}

	public boolean isPropagateChanges() {
		return _fragmentServiceConfigurationProvider.isPropagateChanges(
			_themeDisplay.getCompanyId());
	}

	public boolean isPropagateContributedFragmentChanges() {
		return _fragmentServiceConfigurationProvider.
			isPropagateContributedFragmentChanges(_themeDisplay.getCompanyId());
	}

	private final FragmentServiceConfigurationProvider
		_fragmentServiceConfigurationProvider;
	private final HttpServletRequest _httpServletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private final ThemeDisplay _themeDisplay;

}