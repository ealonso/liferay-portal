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
import com.liferay.change.tracking.service.CTEntryLocalService;
import com.liferay.change.tracking.service.CTPreferencesLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public class ChangeTrackingIndicatorDisplayContext {

	public ChangeTrackingIndicatorDisplayContext(
		HttpServletRequest httpServletRequest,
		CTCollectionLocalService ctCollectionLocalService,
		CTEntryLocalService ctEntryLocalService,
		CTPreferencesLocalService ctPreferencesLocalService, Html html,
		Language language, Portal portal) {

		_httpServletRequest = httpServletRequest;
		_ctCollectionLocalService = ctCollectionLocalService;
		_ctEntryLocalService = ctEntryLocalService;
		_ctPreferencesLocalService = ctPreferencesLocalService;
		_html = html;
		_language = language;
		_portal = portal;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CTPreferences ctPreferences =
			_ctPreferencesLocalService.fetchCTPreferences(
				_themeDisplay.getCompanyId(), 0);

		long ctCollectionId = CTConstants.CT_COLLECTION_ID_PRODUCTION;

		if (ctPreferences != null) {
			ctPreferences = _ctPreferencesLocalService.fetchCTPreferences(
				_themeDisplay.getCompanyId(), _themeDisplay.getUserId());

			if (ctPreferences != null) {
				ctCollectionId = ctPreferences.getCtCollectionId();
			}
		}

		_ctCollection = _ctCollectionLocalService.fetchCTCollection(
			ctCollectionId);
		_ctPreferences = ctPreferences;
	}

	public String getCheckoutURL() {
		PortletURL checkoutURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.ACTION_PHASE);

		checkoutURL.setParameter(
			ActionRequest.ACTION_NAME, "/change_lists/checkout_ct_collection");

		return checkoutURL.toString();
	}

	public String getEventName() {
		return _portal.getPortletNamespace(CTPortletKeys.CHANGE_LISTS) +
			"openDialog";
	}

	public String getNamespace() {
		return _portal.getPortletNamespace(CTPortletKeys.CHANGE_LISTS);
	}

	public Map<String, Object> getReactData() {
		Map<String, Object> data = new HashMap<>();

		if (_ctCollection != null) {
			data.put("iconClass", "change-tracking-indicator-icon-change-list");
			data.put("iconName", "radio-button");
			data.put("title", _ctCollection.getName());
		}
		else {
			data.put("iconClass", "change-tracking-indicator-icon-production");
			data.put("iconName", "simple-circle");

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				_themeDisplay.getLocale(),
				ChangeTrackingIndicatorDisplayContext.class);

			data.put("title", _language.get(resourceBundle, "production"));
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (_ctPreferences != null) {
			PortletURL checkoutURL = _portal.getControlPanelPortletURL(
				_httpServletRequest, _themeDisplay.getScopeGroup(),
				CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.ACTION_PHASE);

			checkoutURL.setParameter(
				ActionRequest.ACTION_NAME,
				"/change_lists/checkout_ct_collection");

			long ctCollectionId = CTConstants.CT_COLLECTION_ID_PRODUCTION;

			if (_ctCollection != null) {
				ctCollectionId = _ctCollection.getCtCollectionId();
			}

			long previousCtCollectionId =
				_ctPreferences.getPreviousCtCollectionId();

			if (previousCtCollectionId != ctCollectionId) {
				if (previousCtCollectionId ==
						CTConstants.CT_COLLECTION_ID_PRODUCTION) {

					checkoutURL.setParameter(
						"ctCollectionId", String.valueOf(0L));

					jsonArray.put(
						JSONUtil.put(
							"href", checkoutURL.toString()
						).put(
							"label",
							LanguageUtil.get(
								_httpServletRequest, "work-on-production")
						).put(
							"symbolLeft", "simple-circle"
						));
				}
				else if (previousCtCollectionId !=
							CTConstants.CT_COLLECTION_ID_NONE) {

					checkoutURL.setParameter(
						"ctCollectionId",
						String.valueOf(previousCtCollectionId));

					CTCollection previousCtCollection =
						_ctCollectionLocalService.fetchCTCollection(
							previousCtCollectionId);

					if (previousCtCollection != null) {
						jsonArray.put(
							JSONUtil.put(
								"href", checkoutURL.toString()
							).put(
								"label",
								LanguageUtil.format(
									_httpServletRequest, "work-on-x",
									previousCtCollection.getName(), false)
							).put(
								"symbolLeft", "radio-button"
							));
					}
				}
			}
		}

		jsonArray.put(
			JSONUtil.put(
				"href", "javascript:Liferay.fire('" + getEventName() + "', {});"
			).put(
				"label",
				LanguageUtil.get(_httpServletRequest, "select-a-publication")
			).put(
				"symbolLeft", "cards2"
			));

		PortletURL addURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.RENDER_PHASE);

		PortletURL overviewURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.RENDER_PHASE);

		addURL.setParameter("backURL", overviewURL.toString());

		addURL.setParameter(
			"mvcRenderCommandName", "/change_lists/add_ct_collection");

		jsonArray.put(
			JSONUtil.put(
				"href", addURL.toString()
			).put(
				"label",
				LanguageUtil.get(_httpServletRequest, "create-new-publication")
			).put(
				"symbolLeft", "plus"
			));

		if (_ctCollection != null) {
			jsonArray.put(
				JSONUtil.put("type", "divider")
			).put(
				JSONUtil.put(
					"href", overviewURL.toString()
				).put(
					"label",
					LanguageUtil.get(_httpServletRequest, "review-changes")
				).put(
					"symbolLeft", "list-ul"
				)
			);

			int count = _ctEntryLocalService.getCTCollectionCTEntriesCount(
				_ctCollection.getCtCollectionId());

			if (count > 0) {
				jsonArray.put(JSONUtil.put("type", "divider"));

				PortletURL publishURL = _portal.getControlPanelPortletURL(
					_httpServletRequest, _themeDisplay.getScopeGroup(),
					CTPortletKeys.CHANGE_LISTS, 0, 0,
					PortletRequest.ACTION_PHASE);

				publishURL.setParameter(
					ActionRequest.ACTION_NAME,
					"/change_lists/publish_ct_collection");

				publishURL.setParameter(
					"ctCollectionId",
					String.valueOf(_ctCollection.getCtCollectionId()));
				publishURL.setParameter("name", _ctCollection.getName());

				String href = StringBundler.concat(
					"javascript:confirm('",
					_html.escapeJS(
						_language.format(
							_httpServletRequest,
							"are-you-sure-you-want-to-publish-x-change-list",
							_ctCollection.getName(), false)),
					"') && Liferay.Util.navigate('",
					_html.escapeJS(publishURL.toString()), "')");

				jsonArray.put(
					JSONUtil.put(
						"href", href
					).put(
						"label",
						LanguageUtil.get(_httpServletRequest, "publish")
					).put(
						"symbolLeft", "upload"
					));
			}
		}

		data.put("items", jsonArray);

		return data;
	}

	public String getSelectChangeListURL() throws WindowStateException {
		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, _themeDisplay.getScopeGroup(),
			CTPortletKeys.CHANGE_LISTS, 0, 0, PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcPath", "/change_lists/select_change_list.jsp");

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		return portletURL.toString();
	}

	private final CTCollection _ctCollection;
	private final CTCollectionLocalService _ctCollectionLocalService;
	private final CTEntryLocalService _ctEntryLocalService;
	private final CTPreferences _ctPreferences;
	private final CTPreferencesLocalService _ctPreferencesLocalService;
	private final Html _html;
	private final HttpServletRequest _httpServletRequest;
	private final Language _language;
	private final Portal _portal;
	private final ThemeDisplay _themeDisplay;

}