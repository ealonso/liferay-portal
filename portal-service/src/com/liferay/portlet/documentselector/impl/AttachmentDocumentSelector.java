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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class AttachmentDocumentSelector {

	public List<FileEntry> getFileEntries(HttpServletRequest request)
		throws Exception {

		long wikiPageResourcePrimKey = ParamUtil.getLong(
			request, "wikiPageResourcePrimKey");

		if (wikiPageResourcePrimKey <= 0) {
			return Collections.emptyList();
		}

		WikiPage wikiPage = WikiPageLocalServiceUtil.getPage(
			wikiPageResourcePrimKey);

		return wikiPage.getAttachmentsFileEntries();
	}

}