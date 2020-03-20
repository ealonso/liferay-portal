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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.Collections;
import java.util.Map;

import javax.portlet.RenderResponse;

/**
 * @author Eudaldo Alonso
 */
public class MillerColumnsDisplayContext {

	public MillerColumnsDisplayContext(
		LayoutsAdminDisplayContext layoutsAdminDisplayContext,
		RenderResponse renderResponse) {

		_layoutsAdminDisplayContext = layoutsAdminDisplayContext;
		_renderResponse = renderResponse;
	}

	public Map<String, Object> getData() throws Exception {
		return HashMapBuilder.<String, Object>put(
			"context",
			Collections.singletonMap(
				"namespace", _renderResponse.getNamespace())
		).put(
			"props",
			HashMapBuilder.<String, Object>put(
				"breadcrumbEntries",
				_layoutsAdminDisplayContext.getBreadcrumbEntriesJSONArray()
			).put(
				"getItemChildrenURL",
				_layoutsAdminDisplayContext.getLayoutChildrenURL()
			).put(
				"layoutColumns",
				_layoutsAdminDisplayContext.getLayoutColumnsJSONArray()
			).put(
				"moveItemURL",
				_layoutsAdminDisplayContext.getMoveLayoutColumnItemURL()
			).put(
				"searchContainerId", "pages"
			).build()
		).build();
	}

	private final LayoutsAdminDisplayContext _layoutsAdminDisplayContext;
	private final RenderResponse _renderResponse;

}