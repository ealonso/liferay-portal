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

package com.liferay.layout.utility.page.internal.security.permission.resource;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.layout.utility.page.constants.LayoutUtilityPageAdminPortletKeys;
import com.liferay.layout.utility.page.constants.LayoutUtilityPageConstants;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryLocalService;
import com.liferay.portal.kernel.security.permission.resource.BaseModelResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	property = "model.class.name=com.liferay.layout.utility.page.model.LayoutUtilityPageEntry",
	service = ModelResourcePermission.class
)
public class LayoutUtilityPageEntryModelResourcePermissionWrapper
	extends BaseModelResourcePermissionWrapper<LayoutUtilityPageEntry> {

	@Override
	protected ModelResourcePermission<LayoutUtilityPageEntry>
		doGetModelResourcePermission() {

		return ModelResourcePermissionFactory.create(
			LayoutUtilityPageEntry.class,
			LayoutUtilityPageEntry::getLayoutUtilityPageEntryId,
			_layoutUtilityPageEntryLocalService::getLayoutUtilityPageEntry,
			_portletResourcePermission,
			(modelResourcePermission, consumer) -> consumer.accept(
				new StagedModelPermissionLogic<>(
					_stagingPermission,
					LayoutUtilityPageAdminPortletKeys.LAYOUT_UTILITY_PAGES,
					LayoutUtilityPageEntry::getLayoutUtilityPageEntryId)));
	}

	@Reference
	private LayoutUtilityPageEntryLocalService
		_layoutUtilityPageEntryLocalService;

	@Reference(
		target = "(resource.name=" + LayoutUtilityPageConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private StagingPermission _stagingPermission;

}