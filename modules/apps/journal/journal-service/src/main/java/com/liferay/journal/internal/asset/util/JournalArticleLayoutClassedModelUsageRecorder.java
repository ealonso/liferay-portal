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

package com.liferay.journal.internal.asset.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.InfoItemServiceRegistry;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalContentSearch;
import com.liferay.journal.service.JournalContentSearchLocalService;
import com.liferay.layout.model.LayoutClassedModelUsage;
import com.liferay.layout.service.LayoutClassedModelUsageLocalService;
import com.liferay.layout.util.LayoutClassedModelUsageRecorder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferenceValue;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PortletPreferenceValueLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	property = "model.class.name=com.liferay.journal.model.JournalArticle",
	service = LayoutClassedModelUsageRecorder.class
)
public class JournalArticleLayoutClassedModelUsageRecorder
	implements LayoutClassedModelUsageRecorder {

	@Override
	public void record(long classNameId, long classPK) throws PortalException {
		if (_layoutClassedModelUsageLocalService.
				hasDefaultLayoutClassedModelUsage(classNameId, classPK)) {

			return;
		}

		InfoItemObjectProvider<JournalArticle> infoItemObjectProvider =
			_infoItemServiceRegistry.getFirstInfoItemService(
				InfoItemObjectProvider.class,
				_portal.getClassName(classNameId));

		JournalArticle article = infoItemObjectProvider.getInfoItem(
			new ClassPKInfoItemIdentifier(classPK));

		long portletClassNameId = _portal.getClassNameId(Portlet.class);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		_recordJournalContentSearches(
			article, classNameId, portletClassNameId, serviceContext);

		AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(
			classNameId, article.getResourcePrimKey());

		if (assetEntry != null) {
			_recordPortletPreferences(
				article, assetEntry, classNameId, portletClassNameId,
				serviceContext);
		}

		_layoutClassedModelUsageLocalService.addDefaultLayoutClassedModelUsage(
			article.getGroupId(), classNameId, classPK, serviceContext);
	}

	private void _recordJournalContentSearches(
		JournalArticle article, long classNameId, long portletClassNameId,
		ServiceContext serviceContext) {

		List<JournalContentSearch> contentSearches =
			_journalContentSearchLocalService.getArticleContentSearches(
				article.getGroupId(), article.getArticleId());

		for (JournalContentSearch contentSearch : contentSearches) {
			Layout layout = _layoutLocalService.fetchLayout(
				contentSearch.getGroupId(), contentSearch.isPrivateLayout(),
				contentSearch.getLayoutId());

			LayoutClassedModelUsage layoutClassedModelUsage =
				_layoutClassedModelUsageLocalService.
					fetchLayoutClassedModelUsage(
						classNameId, article.getResourcePrimKey(),
						contentSearch.getPortletId(), portletClassNameId,
						layout.getPlid());

			if (layoutClassedModelUsage != null) {
				continue;
			}

			_layoutClassedModelUsageLocalService.addLayoutClassedModelUsage(
				contentSearch.getGroupId(), classNameId,
				article.getResourcePrimKey(), contentSearch.getPortletId(),
				portletClassNameId, layout.getPlid(), serviceContext);
		}
	}

	private void _recordPortletPreferences(
			JournalArticle article, AssetEntry assetEntry, long classNameId,
			long portletClassNameId, ServiceContext serviceContext)
		throws PortalException {

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferences(
				article.getCompanyId(), PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT,
				AssetPublisherPortletKeys.ASSET_PUBLISHER);

		for (PortletPreferences portletPreferences : portletPreferencesList) {
			Layout layout = _layoutLocalService.fetchLayout(
				portletPreferences.getPlid());

			if ((layout == null) ||
				(layout.getGroupId() != article.getGroupId())) {

				continue;
			}

			PortletPreferenceValue selectionStylePortletPreferenceValue =
				_portletPreferenceValueLocalService.getPortletPreferenceValue(
					portletPreferences.getPortletPreferencesId(),
					"selectionStyle");

			String selectionStyle =
				selectionStylePortletPreferenceValue.getValue();

			if (Validator.isNull(selectionStyle) ||
				!StringUtil.equals(selectionStyle, "manual")) {

				continue;
			}

			PortletPreferenceValue assetEntryXmlPortletPreferenceValue =
				_portletPreferenceValueLocalService.getPortletPreferenceValue(
					portletPreferences.getPortletPreferencesId(),
					"assetEntryXml");

			String assetEntryXml =
				assetEntryXmlPortletPreferenceValue.getValue();

			if (!assetEntryXml.contains(assetEntry.getClassUuid())) {
				continue;
			}

			LayoutClassedModelUsage layoutClassedModelUsage =
				_layoutClassedModelUsageLocalService.
					fetchLayoutClassedModelUsage(
						classNameId, article.getResourcePrimKey(),
						portletPreferences.getPortletId(), portletClassNameId,
						portletPreferences.getPlid());

			if (layoutClassedModelUsage != null) {
				continue;
			}

			_layoutClassedModelUsageLocalService.addLayoutClassedModelUsage(
				article.getGroupId(), classNameId, article.getResourcePrimKey(),
				portletPreferences.getPortletId(), portletClassNameId,
				portletPreferences.getPlid(), serviceContext);
		}
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private InfoItemServiceRegistry _infoItemServiceRegistry;

	@Reference
	private JournalContentSearchLocalService _journalContentSearchLocalService;

	@Reference
	private LayoutClassedModelUsageLocalService
		_layoutClassedModelUsageLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private PortletPreferenceValueLocalService
		_portletPreferenceValueLocalService;

}