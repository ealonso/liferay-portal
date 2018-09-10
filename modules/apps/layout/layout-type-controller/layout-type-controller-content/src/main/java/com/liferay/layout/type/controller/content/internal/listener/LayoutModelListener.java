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

package com.liferay.layout.type.controller.content.internal.listener;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateSetting;
import com.liferay.layout.page.template.service.LayoutPageTemplateSettingLocalService;
import com.liferay.layout.type.controller.content.internal.constants.ContentLayoutTypeControllerConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(service = ModelListener.class)
public class LayoutModelListener extends BaseModelListener<Layout> {

	@Override
	public void onAfterCreate(Layout layout) throws ModelListenerException {
		if (!Objects.equals(
				layout.getType(),
				ContentLayoutTypeControllerConstants.LAYOUT_TYPE_CONTENT)) {

			return;
		}

		UnicodeProperties typeSettingsProperties =
			layout.getTypeSettingsProperties();

		long layoutPageTemplateEntryId = GetterUtil.getLong(
			typeSettingsProperties.get("layoutPageTemplateEntryId"));

		LayoutPageTemplateSetting existingLayoutPageTemplateSetting =
			_layoutPageTemplateSettingLocalService.
				fetchLayoutPageTemplateSetting(
					layout.getGroupId(),
					_portal.getClassNameId(
						LayoutPageTemplateEntry.class.getName()),
					layoutPageTemplateEntryId);

		if ((existingLayoutPageTemplateSetting == null) ||
			Validator.isNull(existingLayoutPageTemplateSetting.getSettings())) {

			return;
		}

		try {
			JSONObject existingSettings = JSONFactoryUtil.createJSONObject(
				existingLayoutPageTemplateSetting.getSettings());

			JSONArray existingJsonStructureArray =
				existingSettings.getJSONArray("structure");

			if (existingJsonStructureArray == null) {
				return;
			}

			Map<Long, FragmentEntryLink> fragmentEntryLinksMap =
				_fragmentEntryLinkLocalService.getFragmentEntryLinksMap(
					layout.getGroupId(),
					_portal.getClassNameId(
						LayoutPageTemplateEntry.class.getName()),
					layoutPageTemplateEntryId);

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			JSONArray newJsonStructureArray = JSONFactoryUtil.createJSONArray();

			for (int i = 0; i < existingJsonStructureArray.length(); i++) {
				FragmentEntryLink existingFragmentEntryLink =
					fragmentEntryLinksMap.get(
						existingJsonStructureArray.getLong(i));

				if (existingFragmentEntryLink != null) {
					FragmentEntryLink fragmentEntryLink =
						_fragmentEntryLinkLocalService.addFragmentEntryLink(
							existingFragmentEntryLink.getUserId(),
							existingFragmentEntryLink.getGroupId(),
							existingFragmentEntryLink.getFragmentEntryLinkId(),
							existingFragmentEntryLink.getFragmentEntryId(),
							_portal.getClassNameId(Layout.class.getName()),
							layout.getPlid(),
							existingFragmentEntryLink.getCss(),
							existingFragmentEntryLink.getHtml(),
							existingFragmentEntryLink.getJs(),
							existingFragmentEntryLink.getEditableValues(),
							existingFragmentEntryLink.getPosition(),
							serviceContext);

					newJsonStructureArray.put(
						fragmentEntryLink.getFragmentEntryLinkId());
				}
			}

			JSONObject newSettings = JSONFactoryUtil.createJSONObject();

			newSettings.put("structure", newJsonStructureArray);

			LayoutPageTemplateSetting newLayoutPageTemplateSetting =
				_layoutPageTemplateSettingLocalService.
					fetchLayoutPageTemplateSetting(
						layout.getGroupId(),
						_portal.getClassNameId(Layout.class), layout.getPlid());

			if (newLayoutPageTemplateSetting != null) {
				_layoutPageTemplateSettingLocalService.
					updateLayoutPageTemplateSetting(
						layout.getGroupId(),
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						newSettings.toString());
			}
			else {
				_layoutPageTemplateSettingLocalService.
					addLayoutPageTemplateSetting(
						layout.getUserId(), layout.getGroupId(),
						_portal.getClassNameId(Layout.class), layout.getPlid(),
						newSettings.toString(), serviceContext);
			}
		}
		catch (PortalException pe) {
			pe.printStackTrace();
		}
	}

	@Override
	public void onBeforeRemove(Layout layout) throws ModelListenerException {
		_fragmentEntryLinkLocalService.
			deleteLayoutPageTemplateEntryFragmentEntryLinks(
				layout.getGroupId(),
				_portal.getClassNameId(Layout.class.getName()),
				layout.getPlid());
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private LayoutPageTemplateSettingLocalService
		_layoutPageTemplateSettingLocalService;

	@Reference
	private Portal _portal;

}