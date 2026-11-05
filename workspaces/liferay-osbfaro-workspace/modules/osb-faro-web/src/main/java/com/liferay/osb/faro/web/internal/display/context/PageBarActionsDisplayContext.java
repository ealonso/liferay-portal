/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.faro.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class PageBarActionsDisplayContext {

	public PageBarActionsDisplayContext(HttpServletRequest httpServletRequest) {
		_httpServletRequest = httpServletRequest;

		_themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public List<DropdownItem> getLanguageDropdownItems() {
		DropdownItemList dropdownItemList = new DropdownItemList();

		for (Locale locale :
				LanguageUtil.getAvailableLocales(
					_themeDisplay.getSiteGroupId())) {

			String languageId = LocaleUtil.toLanguageId(locale);

			dropdownItemList.add(
				dropdownItem -> {
					dropdownItem.setActive(
						Objects.equals(
							languageId, _themeDisplay.getLanguageId()));
					dropdownItem.setHref(_getUpdateLanguageURL(languageId));
					dropdownItem.setLabel(
						LocaleUtil.getLongDisplayName(locale, new HashSet<>()));
				});
		}

		return dropdownItemList;
	}

	public String getLanguageLabel() {
		Locale locale = _themeDisplay.getLocale();

		return StringBundler.concat(
			StringUtil.toUpperCase(locale.getLanguage()), " (",
			locale.getCountry(), StringPool.CLOSE_PARENTHESIS);
	}

	public String getSettingsURL() throws PortalException {
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
			_themeDisplay.getScopeGroupId(), false, "/settings");

		if (layout == null) {
			return null;
		}

		return PortalUtil.getLayoutFullURL(layout, _themeDisplay);
	}

	public List<DropdownItem> getUserDropdownItems() {
		User user = _themeDisplay.getUser();

		return DropdownItemListBuilder.addGroup(
			dropdownGroupItem -> {
				dropdownGroupItem.setDropdownItems(
					DropdownItemListBuilder.add(
						dropdownItem -> {
							dropdownItem.setHref("/web/guest/workspaces");
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "switch-workspaces"));
						}
					).add(
						dropdownItem -> {
							dropdownItem.setHref(_themeDisplay.getURLSignOut());
							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, "sign-out"));
						}
					).build());
				dropdownGroupItem.setLabel(user.getEmailAddress());
			}
		).build();
	}

	public String getUserInitials() {
		User user = _themeDisplay.getUser();

		return user.getInitials();
	}

	private String _getUpdateLanguageURL(String languageId) {
		String url = _themeDisplay.getPathMain() + "/portal/update_language";

		url = HttpComponentsUtil.addParameter(
			url, "p_l_id", _themeDisplay.getPlid());
		url = HttpComponentsUtil.addParameter(
			url, "redirect", PortalUtil.getCurrentURL(_httpServletRequest));

		return HttpComponentsUtil.addParameter(url, "languageId", languageId);
	}

	private final HttpServletRequest _httpServletRequest;
	private final ThemeDisplay _themeDisplay;

}