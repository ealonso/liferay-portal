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

package com.liferay.fragment.taglib.servlet.taglib;

import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryServiceUtil;
import com.liferay.fragment.taglib.servlet.ServletContextUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Pavel Savinov
 */
public class FragmentEntryPreviewTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_fragmentEntry = FragmentEntryServiceUtil.fetchFragmentEntry(
				_fragmentEntryId);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get fragment entry preview", e);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public void setFragmentEntryId(long fragmentEntryId) {
		_fragmentEntryId = fragmentEntryId;
	}

	public void setHeight(int height) {
		_height = height;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setWidth(int width) {
		_width = width;
	}

	@Override
	protected void cleanUp() {
		_fragmentEntry = null;
		_fragmentEntryId = 0;
		_height = 0;
		_width = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:fragmentEntryName",
			_fragmentEntry.getName());
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:height", _height);
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:previewURL",
			_fragmentEntry.getPreviewImageUrl());
		request.setAttribute(
			"liferay-fragment:fragment-entry-preview:width", _width);
	}

	private static final String _PAGE = "/fragment_entry_preview/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryPreviewTag.class);

	private FragmentEntry _fragmentEntry;
	private long _fragmentEntryId;
	private int _height;
	private int _width;

}