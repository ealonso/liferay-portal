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

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryInstanceLink;
import com.liferay.fragment.model.LayoutFragment;
import com.liferay.fragment.service.base.LayoutFragmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Jürgen Kappler
 */
public class LayoutFragmentLocalServiceImpl
	extends LayoutFragmentLocalServiceBaseImpl {

	@Override
	public LayoutFragment addLayoutFragment(
			long groupId, long plid, long fragmentEntryId, String css,
			String html, String js, String editableValues, int position)
		throws PortalException {

		// Layout Fragment entry

		long layoutFragmentEntryId = counterLocalService.increment();

		LayoutFragment layoutFragment = layoutFragmentPersistence.create(
			layoutFragmentEntryId);

		layoutFragment.setGroupId(groupId);
		layoutFragment.setPlid(plid);
		layoutFragment.setFragmentEntryId(fragmentEntryId);
		layoutFragment.setCss(css);
		layoutFragment.setHtml(html);
		layoutFragment.setJs(js);
		layoutFragment.setEditableValues(editableValues);
		layoutFragment.setPosition(position);

		layoutFragmentPersistence.update(layoutFragment);

		return layoutFragment;
	}

	@Override
	public void addLayoutFragments(
			long groupId, long plid,
			List<FragmentEntryInstanceLink> fragmentEntryInstanceLinks)
		throws PortalException {

		int position = 0;

		for (FragmentEntryInstanceLink fragmentEntryInstanceLink :
				fragmentEntryInstanceLinks) {

			FragmentEntry fragmentEntry =
				fragmentEntryLocalService.getFragmentEntry(
					fragmentEntryInstanceLink.getFragmentEntryId());

			addLayoutFragment(
				groupId, plid, fragmentEntry.getFragmentEntryId(),
				fragmentEntry.getCss(), fragmentEntry.getHtml(),
				fragmentEntry.getJs(),
				fragmentEntryInstanceLink.getEditableValues(), position++);
		}
	}

	@Override
	public LayoutFragment deleteLayoutFragment(LayoutFragment layoutFragment)
		throws PortalException {

		layoutFragmentPersistence.remove(layoutFragment);

		return layoutFragment;
	}

	@Override
	public LayoutFragment deleteLayoutFragment(long layoutFragmentId)
		throws PortalException {

		LayoutFragment layoutFragment = getLayoutFragment(layoutFragmentId);

		return deleteLayoutFragment(layoutFragment);
	}

}