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

package com.liferay.portlet.documentselector;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.SearchContext;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public interface DocumentSelector {

	public abstract List<FileEntry> getFileEntries(
			long repositoryId, SearchContext searchContext)
		throws Exception;

	public abstract List<Folder> getFolders(
			long repositoryId, long folderId, int start, int end)
		throws Exception;

	public int getFoldersCount(long repositoryId, long folderId)
		throws Exception;

}