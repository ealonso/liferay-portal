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

package com.liferay.layout.taglib.servlet.taglib;

import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.layout.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Víctor Galán
 */
public class RenderFragmentLayoutTag extends IncludeTag {

	public FragmentRendererContext getFragmentRendererContext() {
		return _fragmentRendererContext;
	}

	public JSONArray getStructureJSONArray() {
		return _structureJSONArray;
	}

	public void setFragmentRendererContext(
		FragmentRendererContext fragmentRendererContext) {

		_fragmentRendererContext = fragmentRendererContext;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setStructureJSONArray(JSONArray structureJSONArray) {
		_structureJSONArray = structureJSONArray;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_fragmentRendererContext = null;
		_structureJSONArray = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		super.setAttributes(httpServletRequest);

		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:fragmentRendererContext",
			_fragmentRendererContext);
		httpServletRequest.setAttribute(
			"liferay-layout:render-fragment-layout:structureJSONArray",
			_structureJSONArray);
	}

	private static final String _PAGE = "/render_fragment_layout/page.jsp";

	private FragmentRendererContext _fragmentRendererContext;
	private JSONArray _structureJSONArray;

}