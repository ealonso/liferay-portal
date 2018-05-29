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

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/upload_fragment_entry_thumbnail"
	},
	service = MVCActionCommand.class
)
public class UploadFragmentEntryThumbnailMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long fragmentEntryId = ParamUtil.getLong(
			actionRequest, "fragmentEntryId");

		String fileName = ParamUtil.getString(actionRequest, "fileName");

		StringBundler sb = new StringBundler(4);

		sb.append(fragmentEntryId);
		sb.append("_thumbnail");
		sb.append(CharPool.PERIOD);
		sb.append(FileUtil.getExtension(fileName));

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				themeDisplay.getScopeGroupId(), FragmentPortletKeys.FRAGMENT);

		FileEntry fileEntry = null;

		long folderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (repository != null) {
			fileEntry = PortletFileRepositoryUtil.fetchPortletFileEntry(
				themeDisplay.getScopeGroupId(), repository.getDlFolderId(),
				sb.toString());

			folderId = repository.getDlFolderId();
		}

		if (fileEntry != null) {
			PortletFileRepositoryUtil.deletePortletFileEntry(
				fileEntry.getFileEntryId());
		}

		fileEntry = PortletFileRepositoryUtil.addPortletFileEntry(
			themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
			FragmentEntry.class.getName(), fragmentEntryId,
			FragmentPortletKeys.FRAGMENT, folderId,
			actionRequest.getPortletInputStream(), sb.toString(),
			StringPool.BLANK, false);

		jsonObject.put("fileEntryId", fileEntry.getFileEntryId());
		jsonObject.put(
			"imageUrl", DLUtil.getImagePreviewURL(fileEntry, themeDisplay));

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

}