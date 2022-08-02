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

package com.liferay.layout.utility.page.admin.web.internal.portlet;

import com.liferay.item.selector.ItemSelector;
import com.liferay.layout.utility.page.admin.web.internal.constants.LayoutUtilityPageWebKeys;
import com.liferay.layout.utility.page.admin.web.internal.display.context.LayoutUtilityPageDisplayContext;
import com.liferay.layout.utility.page.constants.LayoutUtilityPageAdminPortletKeys;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-lsyout-utility-page-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=Layout Utility Page Admin",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LayoutUtilityPageAdminPortletKeys.LAYOUT_UTILITY_PAGES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator"
	},
	service = Portlet.class
)
public class LayoutUtilityPagesPortlet extends MVCPortlet {

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			LayoutUtilityPageWebKeys.LAYOUT_UTILITY_PAGE_DISPLAY_CONTEXT,
			new LayoutUtilityPageDisplayContext(
				_itemSelector, _layoutUtilityPageEntryService, renderRequest,
				renderResponse));

		super.doDispatch(renderRequest, renderResponse);
	}

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private LayoutUtilityPageEntryService _layoutUtilityPageEntryService;

}