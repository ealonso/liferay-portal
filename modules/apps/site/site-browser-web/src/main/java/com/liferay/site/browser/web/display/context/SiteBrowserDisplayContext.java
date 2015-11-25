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

package com.liferay.site.browser.web.display.context;

import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portlet.sites.util.SitesUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Jürgen Kappler
 */
public class SiteBrowserDisplayContext {

	public SiteBrowserDisplayContext(PortletRequest request) {
		_request = request;
	}

	public List<Group> filterGroups(List<Group> groups, String filter)
		throws Exception {

		List<Group> filteredGroups = new ArrayList();

		for (Group group : groups) {
			if (filter.equals("contentSharingWithChildrenEnabled") &&
				SitesUtil.isContentSharingWithChildrenEnabled(group)) {

				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	public List<Group> filterLayoutGroups(
			List<Group> groups, Boolean privateLayout)
		throws Exception {

		List<Group> filteredGroups = new ArrayList();

		if (privateLayout == null) {
			return groups;
		}

		for (Group group : groups) {
			if (!group.isLayout()) {
				continue;
			}

			Layout layout = LayoutLocalServiceUtil.getLayout(
				group.getClassPK());

			if (layout.isPrivateLayout() == privateLayout) {
				filteredGroups.add(group);
			}
		}

		return filteredGroups;
	}

	private final PortletRequest _request;

}