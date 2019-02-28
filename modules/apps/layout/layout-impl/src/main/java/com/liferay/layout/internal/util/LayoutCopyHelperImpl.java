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

package com.liferay.layout.internal.util;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.layout.util.LayoutCopyHelper;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.sites.kernel.util.Sites;

import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = LayoutCopyHelper.class)
public class LayoutCopyHelperImpl implements LayoutCopyHelper {

	@Override
	public Layout copyLayout(Layout sourceLayout, Layout targetLayout)
		throws Exception {

		_sites.copyLookAndFeel(targetLayout, sourceLayout);
		_sites.copyPortletSetups(sourceLayout, targetLayout);
		_sites.copyPortletPermissions(targetLayout, sourceLayout);

		_copyFragmentEntryLinks(sourceLayout, targetLayout);

		return targetLayout;
	}

	private void _copyFragmentEntryLinks(
			Layout sourceLayout, Layout targetLayout)
		throws Exception {

		List<FragmentEntryLink> fragmentEntryLinks =
			_fragmentEntryLinkLocalService.getFragmentEntryLinks(
				sourceLayout.getGroupId(), _portal.getClassNameId(Layout.class),
				sourceLayout.getPlid());

		_fragmentEntryLinkLocalService.
			deleteLayoutPageTemplateEntryFragmentEntryLinks(
				targetLayout.getGroupId(), _portal.getClassNameId(Layout.class),
				targetLayout.getPlid());

		ServiceContext serviceContext = Optional.ofNullable(
			ServiceContextThreadLocal.getServiceContext()
		).orElse(
			new ServiceContext()
		);

		for (FragmentEntryLink fragmentEntryLink : fragmentEntryLinks) {
			_fragmentEntryLinkLocalService.addFragmentEntryLink(
				targetLayout.getUserId(), targetLayout.getGroupId(), 0,
				fragmentEntryLink.getFragmentEntryId(),
				_portal.getClassNameId(Layout.class), targetLayout.getPlid(),
				fragmentEntryLink.getCss(), fragmentEntryLink.getHtml(),
				fragmentEntryLink.getJs(),
				fragmentEntryLink.getEditableValues(),
				fragmentEntryLink.getPosition(), serviceContext);
		}
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private Sites _sites;

}