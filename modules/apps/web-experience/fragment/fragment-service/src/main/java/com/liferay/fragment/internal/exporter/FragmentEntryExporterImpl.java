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

package com.liferay.fragment.internal.exporter;

import com.liferay.fragment.exporter.FragmentEntryExporter;
import com.liferay.fragment.model.FragmentCollection;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true)
public class FragmentEntryExporterImpl implements FragmentEntryExporter {

	@Override
	public File export(FragmentEntry... fragmentEntries) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter();

		for (FragmentEntry fragmentEntry : fragmentEntries) {
			zipWriter.addEntry(
				String.valueOf(fragmentEntry.getFragmentEntryId()) + "/css",
				fragmentEntry.getCss());
			zipWriter.addEntry(
				String.valueOf(fragmentEntry.getFragmentEntryId()) + "/html",
				fragmentEntry.getHtml());
			zipWriter.addEntry(
				String.valueOf(fragmentEntry.getFragmentEntryId()) + "/js",
				fragmentEntry.getJs());

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", fragmentEntry.getName());
			jsonObject.put(
				"fragmentEntryId", fragmentEntry.getFragmentEntryId());

			jsonArray.put(jsonObject);
		}

		zipWriter.addEntry("fragments.json", jsonArray.toString());

		return zipWriter.getFile();
	}

}
