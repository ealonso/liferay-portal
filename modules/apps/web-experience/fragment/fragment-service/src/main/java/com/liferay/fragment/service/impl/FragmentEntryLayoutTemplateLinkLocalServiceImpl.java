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

import com.liferay.fragment.model.FragmentEntryLayoutTemplateLink;
import com.liferay.fragment.service.base.FragmentEntryLayoutTemplateLinkLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jürgen Kappler
 */
public class FragmentEntryLayoutTemplateLinkLocalServiceImpl
	extends FragmentEntryLayoutTemplateLinkLocalServiceBaseImpl {

	@Override
	public FragmentEntryLayoutTemplateLink addFragmentEntryLayoutTemplateLink(
			long groupId, long fragmentEntryId, long layoutPageTemplateEntryId)
		throws PortalException {

		long fragmentEntryLayoutTemplateLinkId =
			counterLocalService.increment();

		FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink =
			fragmentEntryLayoutTemplateLinkPersistence.create(
				fragmentEntryLayoutTemplateLinkId);

		fragmentEntryLayoutTemplateLink.setGroupId(groupId);
		fragmentEntryLayoutTemplateLink.setFragmentEntryId(fragmentEntryId);
		fragmentEntryLayoutTemplateLink.setLayoutPageTemplateEntryId(
			layoutPageTemplateEntryId);

		fragmentEntryLayoutTemplateLinkPersistence.update(
			fragmentEntryLayoutTemplateLink);

		return fragmentEntryLayoutTemplateLink;
	}

	@Override
	public List<FragmentEntryLayoutTemplateLink>
			deleteFragmentEntryLayoutTemplateLinks(
				long groupId, long layoutPageTemplateEntryId)
		throws PortalException {

		List<FragmentEntryLayoutTemplateLink>
			deletedFragmentEntryLayoutPageTemplateLinks = new ArrayList<>();

		List<FragmentEntryLayoutTemplateLink> fragmentEntryLayoutTemplateLinks =
			fragmentEntryLayoutTemplateLinkPersistence.findByG_L(
				groupId, layoutPageTemplateEntryId);

		for (FragmentEntryLayoutTemplateLink fragmentEntryLayoutTemplateLink :
				fragmentEntryLayoutTemplateLinks) {

			fragmentEntryLayoutTemplateLinkPersistence.remove(
				fragmentEntryLayoutTemplateLink);

			deletedFragmentEntryLayoutPageTemplateLinks.add(
				fragmentEntryLayoutTemplateLink);
		}

		return deletedFragmentEntryLayoutPageTemplateLinks;
	}

}