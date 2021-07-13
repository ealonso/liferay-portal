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

package com.liferay.journal.content.web.internal.model.listener;

import com.liferay.journal.constants.JournalContentPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.PortletPreferenceValueLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(immediate = true, service = ModelListener.class)
public class JournalContentPortletPreferencesModelListener
	extends BaseModelListener<PortletPreferences> {

	@Override
	public void onAfterUpdate(PortletPreferences portletPreferences)
		throws ModelListenerException {

		String portletId = portletPreferences.getPortletId();

		if (portletId.startsWith(JournalContentPortletKeys.JOURNAL_CONTENT)) {
			javax.portlet.PortletPreferences strictPortletPreferences =
				_portletPreferenceValueLocalService.getPreferences(
					portletPreferences);

			long groupId = GetterUtil.getLong(
				strictPortletPreferences.getValue("groupId", "0"));

			if (groupId > 0) {
				Group group = _groupLocalService.fetchGroup(groupId);

				if (group != null) {
					String lfrScopeType = strictPortletPreferences.getValue(
						"lfrScopeType", StringPool.BLANK);

					if (!_matchGroupScopeType(group, lfrScopeType)) {
						_resetArticlePortletPreference(
							portletPreferences, portletId,
							strictPortletPreferences);
					}
				}
			}
		}
	}

	private boolean _matchGroupScopeType(Group group, String lfrScopeType) {
		if (group.isCompany() && Objects.equals("company", lfrScopeType)) {
			return true;
		}

		if (group.isLayout() && Objects.equals("layout", lfrScopeType)) {
			return true;
		}

		if (!group.isCompany() && !group.isLayout() &&
			Validator.isNull(lfrScopeType)) {

			return true;
		}

		return false;
	}

	private void _resetArticlePortletPreference(
		PortletPreferences portletPreferences, String portletId,
		javax.portlet.PortletPreferences strictPortletPreferences) {

		try {
			strictPortletPreferences.setValue("assetEntryId", StringPool.BLANK);
			strictPortletPreferences.setValue("articleId", StringPool.BLANK);
			strictPortletPreferences.setValue(
				"ddmTemplateKey", StringPool.BLANK);
			strictPortletPreferences.setValue("groupId", StringPool.BLANK);

			_portletPreferencesLocalService.updatePreferences(
				portletPreferences.getOwnerId(),
				portletPreferences.getOwnerType(), portletPreferences.getPlid(),
				portletId, strictPortletPreferences);
		}
		catch (ReadOnlyException readOnlyException) {
			_log.error(
				"Unabled to update portlePreference for portletId:" + portletId,
				readOnlyException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalContentPortletPreferencesModelListener.class);

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private PortletPreferenceValueLocalService
		_portletPreferenceValueLocalService;

}