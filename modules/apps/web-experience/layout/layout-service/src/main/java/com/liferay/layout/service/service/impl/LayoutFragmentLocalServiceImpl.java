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
import com.liferay.layout.service.model.LayoutFragment;
import com.liferay.layout.service.service.base.LayoutFragmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

/**
 * @author Jürgen Kappler
 */
public class LayoutFragmentLocalServiceImpl
	extends LayoutFragmentLocalServiceBaseImpl {

	@Override
	public LayoutFragment addLayoutFragment(
			long userId, long groupId, long plid, FragmentEntry fragmentEntry,
			int position, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long layoutFragmentId = counterLocalService.increment();

		LayoutFragment layoutFragment = layoutFragmentPersistence.create(
			layoutFragmentId);

		layoutFragment.setGroupId(groupId);
		layoutFragment.setCompanyId(user.getCompanyId());
		layoutFragment.setUserId(user.getUserId());
		layoutFragment.setUserName(user.getFullName());
		layoutFragment.setCreateDate(serviceContext.getCreateDate(new Date()));
		layoutFragment.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		layoutFragment.setPlid(plid);
		layoutFragment.setFragmentEntryId(fragmentEntry.getFragmentEntryId());
		layoutFragment.setCss(fragmentEntry.getCss());
		layoutFragment.setHtml(fragmentEntry.getHtml());
		layoutFragment.setJs(fragmentEntry.getJs());
		layoutFragment.setPosition(position);

		layoutFragmentPersistence.update(layoutFragment);

		return layoutFragment;
	}

	@Override
	public LayoutFragment deleteLayoutFragment(LayoutFragment layoutFragment) {
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