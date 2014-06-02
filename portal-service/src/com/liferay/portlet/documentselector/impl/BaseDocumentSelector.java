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

package com.liferay.portlet.documentselector.impl;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;
import com.liferay.portlet.documentselector.DocumentSelector;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class BaseDocumentSelector implements DocumentSelector {

	@Override
	public List<FileEntry> getFileEntries(
			long repositoryId, SearchContext searchContext)
		throws Exception {

		String[] mimeTypes = getFileEntryMimeTypes();

		if (!ArrayUtil.isEmpty(mimeTypes)) {
			searchContext.setAttribute("mimeTypes", mimeTypes);
		}

		Hits hits = DLAppServiceUtil.search(repositoryId, searchContext);

		return DLUtil.getFileEntries(hits);
	}

	@Override
	public List<Folder> getFolders(
			long repositoryId, long folderId, int start, int end)
		throws Exception {

		return DLAppServiceUtil.getFolders(repositoryId, folderId, start, end);
	}

	@Override
	public int getFoldersCount(long repositoryId, long folderId)
		throws Exception {

		return DLAppServiceUtil.getFoldersCount(repositoryId, folderId);
	}

	protected String[] getFileEntryMimeTypes() {
		return null;
	}

}