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

package com.liferay.layout.admin.web.internal.portlet.action;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.fragment.service.FragmentEntryLocalService;
import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.constants.SegmentsConstants;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + LayoutAdminPortletKeys.GROUP_PAGES,
		"mvc.command.name=/layout/export_layout_data"
	},
	service = MVCResourceCommand.class
)
public class ExportLayoutDataMVCResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		long plid = ParamUtil.getLong(resourceRequest, "plid");

		try {
			LayoutPageTemplateStructure layoutPageTemplateStructure =
				_laLayoutPageTemplateStructureLocalService.
					fetchLayoutPageTemplateStructure(
						groupId, _portal.getClassNameId(Layout.class.getName()),
						plid, true);

			String data = layoutPageTemplateStructure.getData(
				SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT);

			String parseData = _parseLayoutContent(data);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse,
				"layout-data-" + Time.getTimestamp() + ".json",
				parseData.getBytes(), ContentTypes.APPLICATION_ZIP);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return false;
	}

	private String _getRendererKey(FragmentEntryLink fragmentEntryLink) {
		if (Validator.isNotNull(fragmentEntryLink.getRendererKey())) {
			return fragmentEntryLink.getRendererKey();
		}

		if (fragmentEntryLink.getFragmentEntryId() <= 0) {
			return null;
		}

		FragmentEntry fragmentEntry =
			_fragmentEntryLocalService.fetchFragmentEntry(
				fragmentEntryLink.getFragmentEntryId());

		return fragmentEntry.getFragmentEntryKey();
	}

	private String _parseLayoutContent(String data) throws JSONException {
		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

		JSONArray structureJSONArray = dataJSONObject.getJSONArray("structure");

		for (int i = 0; i < structureJSONArray.length(); i++) {
			JSONObject rowJSONObject = structureJSONArray.getJSONObject(i);

			JSONArray columnsJSONArray = rowJSONObject.getJSONArray("columns");

			for (int j = 0; j < columnsJSONArray.length(); j++) {
				JSONObject columnJSONObject = columnsJSONArray.getJSONObject(j);

				JSONArray fragmentEntryLinkIdsJSONArray =
					columnJSONObject.getJSONArray("fragmentEntryLinkIds");

				JSONArray fragmentEntriesJSONArray =
					JSONFactoryUtil.createJSONArray();

				for (int k = 0; k < fragmentEntryLinkIdsJSONArray.length();
					 k++) {

					long fragmentEntryLinkId =
						fragmentEntryLinkIdsJSONArray.getLong(k);

					if (fragmentEntryLinkId <= 0) {
						continue;
					}

					FragmentEntryLink fragmentEntryLink =
						_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
							fragmentEntryLinkId);

					if (fragmentEntryLink == null) {
						continue;
					}

					String rendererKey = _getRendererKey(fragmentEntryLink);

					if (Validator.isNull(rendererKey)) {
						continue;
					}

					JSONObject fragmentEntryJSONObject = JSONUtil.put(
						"editableValues", fragmentEntryLink.getEditableValues()
					).put(
						"fragmentEntryKey", _getRendererKey(fragmentEntryLink)
					);

					fragmentEntriesJSONArray.put(fragmentEntryJSONObject);
				}

				columnJSONObject.remove("fragmentEntryLinkIds");

				columnJSONObject.put(
					"fragmentEntries", fragmentEntriesJSONArray);
			}
		}

		return dataJSONObject.toString();
	}

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private FragmentEntryLocalService _fragmentEntryLocalService;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_laLayoutPageTemplateStructureLocalService;

	@Reference
	private Portal _portal;

}