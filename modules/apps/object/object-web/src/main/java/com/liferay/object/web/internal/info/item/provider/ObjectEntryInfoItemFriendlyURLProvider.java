/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.web.internal.info.item.provider;

import com.liferay.friendly.url.info.item.provider.InfoItemFriendlyURLProvider;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.service.FriendlyURLEntryLocalService;
import com.liferay.friendly.url.util.comparator.FriendlyURLEntryLocalizationComparator;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;

/**
 * @author Carolina Barbosa
 */
public class ObjectEntryInfoItemFriendlyURLProvider
	implements InfoItemFriendlyURLProvider<ObjectEntry> {

	public ObjectEntryInfoItemFriendlyURLProvider(
		FriendlyURLEntryLocalService friendlyURLEntryLocalService,
		ObjectDefinitionLocalService objectDefinitionLocalService,
		Portal portal) {

		_friendlyURLEntryLocalService = friendlyURLEntryLocalService;
		_objectDefinitionLocalService = objectDefinitionLocalService;
		_portal = portal;
	}

	@Override
	public String getFriendlyURL(ObjectEntry objectEntry, String languageId) {
		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectEntry.getObjectDefinitionId());

		if (objectDefinition == null) {
			return String.valueOf(objectEntry.getObjectEntryId());
		}

		String urlTitle = objectEntry.getURLTitle(
			LocaleUtil.fromLanguageId(languageId));

		if (Validator.isNotNull(urlTitle)) {
			return objectDefinition.getName() + StringPool.SLASH + urlTitle;
		}

		if (!objectDefinition.isDefaultStorageType()) {
			return objectEntry.getExternalReferenceCode();
		}

		return String.valueOf(objectEntry.getObjectEntryId());
	}

	@Override
	public List<FriendlyURLEntryLocalization> getFriendlyURLEntryLocalizations(
		ObjectEntry objectEntry, String languageId) {

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.fetchObjectDefinition(
				objectEntry.getObjectDefinitionId());

		if (objectDefinition == null) {
			return Collections.emptyList();
		}

		try {
			return _friendlyURLEntryLocalService.
				getFriendlyURLEntryLocalizations(
					objectEntry.getNonzeroGroupId(),
					_portal.getClassNameId(objectDefinition.getClassName()),
					objectEntry.getObjectEntryId(), languageId,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					FriendlyURLEntryLocalizationComparator.getInstance(false));
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException);
			}

			return Collections.emptyList();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ObjectEntryInfoItemFriendlyURLProvider.class);

	private final FriendlyURLEntryLocalService _friendlyURLEntryLocalService;
	private final ObjectDefinitionLocalService _objectDefinitionLocalService;
	private final Portal _portal;

}