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

package com.liferay.document.library.web.internal.security.permission;

import com.liferay.dynamic.data.mapping.util.DDMTemplatePermissionSupport;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lourdes Fernández Besada
 */
@Component(
	property = "model.class.name=com.liferay.portal.kernel.repository.model.FileEntry",
	service = {
		DDMTemplatePermissionSupport.class,
		FileEntryDDMTemplatePermissionSupport.class
	}
)
public class FileEntryDDMTemplatePermissionSupport
	implements DDMTemplatePermissionSupport {

	@Override
	public String getResourceName(long classNameId) {
		return _RESOURCE_NAME;
	}

	@Activate
	protected void activate() {
		List<String> actions = ResourceActionsUtil.getModelResourceActions(
			_RESOURCE_NAME);

		if (actions.isEmpty()) {
			_resourceActionLocalServiceUtil.addResourceAction(
				_RESOURCE_NAME, ActionKeys.DELETE, 2);
			_resourceActionLocalServiceUtil.addResourceAction(
				_RESOURCE_NAME, ActionKeys.PERMISSIONS, 4);
			_resourceActionLocalServiceUtil.addResourceAction(
				_RESOURCE_NAME, ActionKeys.UPDATE, 8);
			_resourceActionLocalServiceUtil.addResourceAction(
				_RESOURCE_NAME, ActionKeys.VIEW, 1);
		}
	}

	private static final String _RESOURCE_NAME =
		"com.liferay.dynamic.data.mapping.model.DDMTemplate-" +
			"com.liferay.portal.kernel.repository.model.FileEntry";

	@Reference
	private static ResourceActionLocalService _resourceActionLocalServiceUtil;

}