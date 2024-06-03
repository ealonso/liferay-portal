/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.field.item.selector.web.internal.search;

import com.liferay.info.field.InfoField;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;

import java.util.List;

import javax.portlet.RenderResponse;

/**
 * @author Eudaldo Alonso
 */
public class InfoFieldItemSelectorChecker extends EmptyOnClickRowChecker {

	public InfoFieldItemSelectorChecker(
		RenderResponse renderResponse, List<String> checkedInfoFieldUniqueIds) {

		super(renderResponse);

		_checkedInfoFieldUniqueIds = checkedInfoFieldUniqueIds;
	}

	@Override
	public boolean isChecked(Object object) {
		InfoField<?> infoField = (InfoField<?>)object;

		return _checkedInfoFieldUniqueIds.contains(infoField.getUniqueId());
	}

	@Override
	public boolean isDisabled(Object object) {
		return isChecked(object);
	}

	private final List<String> _checkedInfoFieldUniqueIds;

}