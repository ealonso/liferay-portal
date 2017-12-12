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

package com.liferay.fragment.service.impl;

import com.liferay.fragment.model.FragmentLayoutTemplateLink;
import com.liferay.fragment.service.base.FragmentLayoutTemplateLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jürgen Kappler
 */
public class FragmentLayoutTemplateLinkLocalServiceImpl
	extends FragmentLayoutTemplateLinkLocalServiceBaseImpl {

	@Override
	public FragmentLayoutTemplateLink addFragmentLayoutTemplateLink(
			long groupId, long fragmentEntryId, long layoutPageTemplateEntryId)
		throws PortalException {

		long fragmentLayoutTemplateLinkId = counterLocalService.increment();

		FragmentLayoutTemplateLink fragmentLayoutTemplateLink =
			fragmentLayoutTemplateLinkPersistence.create(
				fragmentLayoutTemplateLinkId);

		fragmentLayoutTemplateLink.setGroupId(groupId);
		fragmentLayoutTemplateLink.setFragmentEntryId(fragmentEntryId);
		fragmentLayoutTemplateLink.setLayoutPageTemplateEntryId(
			layoutPageTemplateEntryId);

		fragmentLayoutTemplateLinkPersistence.update(
			fragmentLayoutTemplateLink);

		return fragmentLayoutTemplateLink;
	}

	@Override
	public List<FragmentLayoutTemplateLink> deleteFragmentLayoutTemplateLinks(
			long groupId, long layoutPageTemplateEntryId)
		throws PortalException {

		List<FragmentLayoutTemplateLink>
			deletedFragmentLayoutPageTemplateLinks = new ArrayList<>();

		List<FragmentLayoutTemplateLink> fragmentLayoutTemplateLinks =
			fragmentLayoutTemplateLinkPersistence.findByG_L(
				groupId, layoutPageTemplateEntryId);

		for (FragmentLayoutTemplateLink fragmentLayoutTemplateLink :
				fragmentLayoutTemplateLinks) {

			fragmentLayoutTemplateLinkPersistence.remove(
				fragmentLayoutTemplateLink);

			deletedFragmentLayoutPageTemplateLinks.add(
				fragmentLayoutTemplateLink);
		}

		return deletedFragmentLayoutPageTemplateLinks;
	}

}