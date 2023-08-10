/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.collection.provider;

import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Locale;

/**
 * @author Eudaldo Alonso
 */
public class ThemeDisplayCollectionContext implements CollectionContext {

	public ThemeDisplayCollectionContext(ThemeDisplay themeDisplay) {
		_companyId = themeDisplay.getCompanyId();
		_groupId = themeDisplay.getScopeGroupId();
		_locale = themeDisplay.getLocale();
		_userId = themeDisplay.getUserId();
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public Locale getLocale() {
		return _locale;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	private final long _companyId;
	private final long _groupId;
	private final Locale _locale;
	private final long _userId;

}