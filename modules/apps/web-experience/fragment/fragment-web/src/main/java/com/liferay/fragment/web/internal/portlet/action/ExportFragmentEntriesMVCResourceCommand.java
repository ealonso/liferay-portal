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

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.exporter.FragmentEntryExporter;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryService;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=exportFragmentEntries"
	},
	service = MVCResourceCommand.class
)
public class ExportFragmentEntriesMVCResourceCommand
	implements MVCResourceCommand {

	@Reference
	private FragmentEntryService _fragmentEntryService;

	@Reference
	private FragmentEntryExporter _fragmentEntryExporter;

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		long[] exportFragmentEntryIds = null;

		long fragmentEntryId = ParamUtil.getLong(
			resourceRequest, "fragmentEntryId");

		if (fragmentEntryId > 0) {
			exportFragmentEntryIds = new long[] {fragmentEntryId};
		}
		else {
			exportFragmentEntryIds = ParamUtil.getLongValues(
				resourceRequest, "rowIds");
		}

		try {
			List<FragmentEntry> fragmentEntries =
				_fragmentEntryService.getFragmentEntries(
					exportFragmentEntryIds);

			FragmentEntry[] fragmentEntriesArray =
				fragmentEntries.stream().toArray(
					size -> new FragmentEntry[size]);

			File file = _fragmentEntryExporter.export(fragmentEntriesArray);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, file.getName(),
				new FileInputStream(file), ContentTypes.APPLICATION_ZIP);

			return true;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}
}
