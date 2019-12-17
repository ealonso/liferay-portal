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

package com.liferay.change.tracking.web.internal.display.context;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.constants.CTPortletKeys;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTPreferences;
import com.liferay.change.tracking.service.CTCollectionLocalService;
import com.liferay.change.tracking.service.CTPreferencesLocalService;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public class ChangeTrackingIndicatorDisplayContext {

	public ChangeTrackingIndicatorDisplayContext(
		HttpServletRequest httpServletRequest,
		CTCollectionLocalService ctCollectionLocalService,
		CTPreferencesLocalService ctPreferencesLocalService, Language language,
		Portal portal) {

		_httpServletRequest = httpServletRequest;
		_ctCollectionLocalService = ctCollectionLocalService;
		_ctPreferencesLocalService = ctPreferencesLocalService;
		_language = language;
		_portal = portal;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CTPreferences ctPreferences =
			_ctPreferencesLocalService.fetchCTPreferences(
				_themeDisplay.getCompanyId(), _themeDisplay.getUserId());

		if ((ctPreferences != null) &&
			(ctPreferences.getCtCollectionId() !=
				CTConstants.CT_COLLECTION_ID_PRODUCTION)) {

			_ctCollection = _ctCollectionLocalService.fetchCTCollection(
				ctPreferences.getCtCollectionId());
		}
		else {
			_ctCollection = null;
		}
	}

	public String getChangeTrackingURL() {
		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.RENDER_PHASE);

		try {
			portletURL.setWindowState(WindowState.MAXIMIZED);
		}
		catch (WindowStateException wse) {
			ReflectionUtil.throwException(wse);
		}

		return portletURL.toString();
	}

	public String getIconClass() {
		if (_ctCollection == null) {
			return "change-tracking-indicator-icon-production";
		}

		return "change-tracking-indicator-icon-change-list";
	}

	public String getIconName() {
		if (_ctCollection == null) {
			return "simple-circle";
		}

		return "radio-button";
	}

	public String getTitle() {
		if (_ctCollection != null) {
			return _ctCollection.getName();
		}

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			_themeDisplay.getLocale(),
			ChangeTrackingIndicatorDisplayContext.class);

		return _language.get(resourceBundle, "production");
	}

	private final CTCollection _ctCollection;
	private final CTCollectionLocalService _ctCollectionLocalService;
	private final CTPreferencesLocalService _ctPreferencesLocalService;
	private final HttpServletRequest _httpServletRequest;
	private final Language _language;
	private final Portal _portal;
	private final ThemeDisplay _themeDisplay;

}