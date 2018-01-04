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

package com.liferay.layout.service.service.impl;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateFragment;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.service.LayoutPageTemplateFragmentLocalService;
import com.liferay.layout.service.model.LayoutFragment;
import com.liferay.layout.service.service.base.LayoutFragmentServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Jürgen Kappler
 */
public class LayoutFragmentServiceImpl extends LayoutFragmentServiceBaseImpl {

	@Override
	public Layout addContentLayout(
			long groupId, boolean privateLayout, long parentLayoutId,
			long layoutPageTemplateEntryId, Map<Locale, String> localeNamesMap,
			Map<Locale, String> localeTitlesMap,
			Map<Locale, String> descriptionMap, Map<Locale, String> keywordsMap,
			Map<Locale, String> robotsMap, String type,
			Map<Locale, String> friendlyURLMap, ServiceContext serviceContext)
		throws PortalException {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.setProperty(
			"layoutPageTemplateId", String.valueOf(layoutPageTemplateEntryId));

		Layout layout = _layoutService.addLayout(
			groupId, privateLayout, parentLayoutId, localeNamesMap,
			localeTitlesMap, descriptionMap, keywordsMap, robotsMap, type,
			typeSettingsProperties.toString(), false, friendlyURLMap,
			serviceContext);

		List<LayoutPageTemplateFragment> layoutPageTemplateFragments =
			_layoutPageTemplateFragmentLocalService.
				getLayoutPageTemplateFragmentsByPageTemplate(
					groupId, layoutPageTemplateEntryId);

		int position = 0;

		for (LayoutPageTemplateFragment layoutPageTemplateFragment :
				layoutPageTemplateFragments) {

			FragmentEntry fragmentEntry =
				FragmentEntryServiceUtil.fetchFragmentEntry(
					layoutPageTemplateFragment.getFragmentEntryId());

			layoutFragmentLocalService.addLayoutFragment(
				getUserId(), groupId, layout.getPlid(), fragmentEntry,
				position++, serviceContext);
		}

		return layout;
	}

	@Override
	public void deleteContentLayout(
			long groupId, long plid, ServiceContext serviceContext)
		throws PortalException {

		_layoutService.deleteLayout(plid, serviceContext);

		List<LayoutFragment> layoutFragments = getLayoutFragments(
			groupId, plid);

		for (LayoutFragment layoutFragment : layoutFragments) {
			layoutFragmentLocalService.deleteLayoutFragment(layoutFragment);
		}
	}

	@Override
	public List<LayoutFragment> getLayoutFragments(long groupId, long plid) {
		return layoutFragmentPersistence.findByG_P(groupId, plid);
	}

	@ServiceReference(type = LayoutPageTemplateEntryService.class)
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@ServiceReference(type = LayoutPageTemplateFragmentLocalService.class)
	private LayoutPageTemplateFragmentLocalService
		_layoutPageTemplateFragmentLocalService;

	@ServiceReference(type = LayoutService.class)
	private LayoutService _layoutService;

}