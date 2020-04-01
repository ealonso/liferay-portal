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

package com.liferay.fragment.entry.processor.drop.zone.model.listener;

import com.liferay.fragment.entry.processor.drop.zone.DropZoneFragmentEntryProcessor;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.layout.util.structure.LayoutStructure;
import com.liferay.layout.util.structure.LayoutStructureItem;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ModelListener.class)
public class FragmentEntryLinkModelListener
	extends BaseModelListener<FragmentEntryLink> {

	@Override
	public void onAfterCreate(FragmentEntryLink fragmentEntryLink)
		throws ModelListenerException {

		try {
			JSONObject editableValuesJSONObject =
				JSONFactoryUtil.createJSONObject(
					fragmentEntryLink.getEditableValues());

			JSONObject dropZoneProcessorJSONObject =
				editableValuesJSONObject.getJSONObject(
					DropZoneFragmentEntryProcessor.class.getName());

			if (dropZoneProcessorJSONObject == null) {
				return;
			}

			JSONArray dropZoneJSONArray =
				dropZoneProcessorJSONObject.getJSONArray("dropZones");

			if (dropZoneJSONArray == null) {
				return;
			}

			LayoutStructure layoutStructure = _getLayoutStructure(
				fragmentEntryLink);

			if (layoutStructure == null) {
				return;
			}

			for (int i = 0; i < dropZoneJSONArray.length(); i++) {
				JSONObject dropZoneJSONObject = dropZoneJSONArray.getJSONObject(
					i);

				String uuid = dropZoneJSONObject.getString("uuid");

				if (Validator.isNotNull(uuid)) {
					continue;
				}

				LayoutStructureItem layoutStructureItem =
					layoutStructure.addRootLayoutStructureItem();

				dropZoneJSONObject.put("uuid", layoutStructureItem.getItemId());
			}

			fragmentEntryLink.setEditableValues(
				editableValuesJSONObject.toString());

			JSONObject dataJSONObject = layoutStructure.toJSONObject();

			_layoutPageTemplateStructureLocalService.
				updateLayoutPageTemplateStructure(
					fragmentEntryLink.getGroupId(),
					fragmentEntryLink.getClassNameId(),
					fragmentEntryLink.getClassPK(),
					fragmentEntryLink.getSegmentsExperienceId(),
					dataJSONObject.toString());
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	private LayoutStructure _getLayoutStructure(
		FragmentEntryLink fragmentEntryLink) {

		LayoutPageTemplateStructure layoutPageTemplateStructure =
			_layoutPageTemplateStructureLocalService.
				fetchLayoutPageTemplateStructure(
					fragmentEntryLink.getGroupId(),
					fragmentEntryLink.getClassNameId(),
					fragmentEntryLink.getClassPK());

		String data = layoutPageTemplateStructure.getData(
			fragmentEntryLink.getSegmentsExperienceId());

		if (Validator.isNull(data)) {
			return null;
		}

		return LayoutStructure.of(data);
	}

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureLocalService;

}