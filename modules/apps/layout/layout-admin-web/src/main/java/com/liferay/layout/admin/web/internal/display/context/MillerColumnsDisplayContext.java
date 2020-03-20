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

import java.util.Collections;
import java.util.HashMap;
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
		Map<String, Object> layoutData = new HashMap<>();

		layoutData.put(
			"context",
			Collections.singletonMap(
				"namespace", _renderResponse.getNamespace()));

		Map<String, Object> layoutProps = new HashMap<>();

		layoutProps.put(
			"breadcrumbEntries",
			_layoutsAdminDisplayContext.getBreadcrumbEntriesJSONArray());
		layoutProps.put(
			"getItemChildrenURL",
			_layoutsAdminDisplayContext.getLayoutChildrenURL());
		layoutProps.put(
			"layoutColumns",
			_layoutsAdminDisplayContext.getLayoutColumnsJSONArray());
		layoutProps.put(
			"moveItemURL",
			_layoutsAdminDisplayContext.getMoveLayoutColumnItemURL());
		layoutProps.put("searchContainerId", "pages");

		layoutData.put("props", layoutProps);

		return layoutData;
	}

	private final LayoutsAdminDisplayContext _layoutsAdminDisplayContext;
	private final RenderResponse _renderResponse;

}