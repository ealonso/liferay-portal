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

package com.liferay.modern.site.building.fragment.taglib.servlet.taglib;

import com.liferay.modern.site.building.fragment.model.MSBFragmentEntry;
import com.liferay.modern.site.building.fragment.service.MSBFragmentEntryServiceUtil;
import com.liferay.modern.site.building.fragment.taglib.servlet.ServletContextUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.util.IncludeTag;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Jürgen Kappler
 */
public class MSBFragmentEntryRendererTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_msbFragmentEntry =
				MSBFragmentEntryServiceUtil.fetchMSBFragmentEntry(
					getGroupId(), _name);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to get fragment entry", pe);
			}

			return SKIP_BODY;
		}

		return super.doStartTag();
	}

	public long getGroupId() {
		if (_groupId > 0) {
			return _groupId;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return themeDisplay.getScopeGroupId();
	}

	public void setContext(Map<String, Object> context) {
		_context = context;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		_context = null;
		_groupId = 0;
		_msbFragmentEntry = null;
		_name = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-modern-site-building-fragment:" +
			"msb-fragment-entry-renderer:context", _context);
		request.setAttribute(
			"liferay-modern-site-building-fragment:" +
				"msb-fragment-entry-renderer:msbFragmentEntry",
			_msbFragmentEntry);
	}

	private static final String _PAGE = "/msb_fragment_entry/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		MSBFragmentEntryRendererTag.class);

	private Map<String, Object> _context;
	private long _groupId;
	private MSBFragmentEntry _msbFragmentEntry;
	private String _name;

}