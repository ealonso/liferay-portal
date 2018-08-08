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

package com.liferay.layout.page.template.service.impl;

import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;
import com.liferay.layout.page.template.service.base.LayoutPageTemplateSettingLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.Date;

/**
 * @author Jürgen Kappler
 */
public class LayoutPageTemplateSettingLocalServiceImpl
	extends LayoutPageTemplateSettingLocalServiceBaseImpl {

	@Override
	public LayoutPageTemplateSetting addLayoutPageTemplateSetting(
			long userId, long groupId, long classNameId, long classPK,
			String settings, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long layoutPageTemplateSettingId = counterLocalService.increment();

		LayoutPageTemplateSetting layoutPageTemplateSetting =
			layoutPageTemplateSettingPersistence.create(
				layoutPageTemplateSettingId);

		layoutPageTemplateSetting.setUuid(serviceContext.getUuid());
		layoutPageTemplateSetting.setGroupId(groupId);
		layoutPageTemplateSetting.setCompanyId(user.getCompanyId());
		layoutPageTemplateSetting.setUserId(user.getUserId());
		layoutPageTemplateSetting.setUserName(user.getFullName());
		layoutPageTemplateSetting.setCreateDate(
			serviceContext.getCreateDate(new Date()));
		layoutPageTemplateSetting.setModifiedDate(
			serviceContext.getModifiedDate(new Date()));
		layoutPageTemplateSetting.setClassNameId(classNameId);
		layoutPageTemplateSetting.setClassPK(classPK);
		layoutPageTemplateSetting.setSettings(settings);

		layoutPageTemplateSettingPersistence.update(layoutPageTemplateSetting);

		return layoutPageTemplateSetting;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public LayoutPageTemplateSetting deleteLayoutPageTemplateSetting(
			long groupId, long classNameId, long classPK)
		throws PortalException {

		LayoutPageTemplateSetting layoutPageTemplateSetting =
			layoutPageTemplateSettingPersistence.findByG_C_C(
				groupId, classNameId, classPK);

		layoutPageTemplateSettingPersistence.remove(layoutPageTemplateSetting);

		return layoutPageTemplateSetting;
	}

	@Override
	public LayoutPageTemplateSetting fetchLayoutPageTemplateSetting(
		long groupId, long classNameId, long classPK) {

		return layoutPageTemplateSettingPersistence.fetchByG_C_C(
			groupId, classNameId, classPK);
	}

	@Override
	public LayoutPageTemplateSetting updateLayoutPageTemplateSetting(
			long groupId, long classNameId, long classPK, String settings)
		throws PortalException {

		LayoutPageTemplateSetting layoutPageTemplateSetting =
			layoutPageTemplateSettingPersistence.findByG_C_C(
				groupId, classNameId, classPK);

		layoutPageTemplateSetting.setModifiedDate(new Date());
		layoutPageTemplateSetting.setSettings(settings);

		layoutPageTemplateSettingPersistence.update(layoutPageTemplateSetting);

		return layoutPageTemplateSetting;
	}

}