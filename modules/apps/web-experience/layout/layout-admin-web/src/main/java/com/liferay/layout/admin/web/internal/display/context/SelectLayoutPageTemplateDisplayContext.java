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

import com.liferay.layout.admin.web.internal.util.LayoutPageTemplatePortletUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateCollectionServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryLocalServiceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class SelectLayoutPageTemplateDisplayContext
	extends LayoutPageTemplateDisplayContext {

	public SelectLayoutPageTemplateDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest request)
		throws PortalException {

		super(renderRequest, renderResponse, request);
	}

	@Override
	public long getLayoutPageTemplateCollectionId() {
		if (Validator.isNotNull(layoutPageTemplateCollectionId)) {
			return layoutPageTemplateCollectionId;
		}

		layoutPageTemplateCollectionId = ParamUtil.getLong(
			request, "layoutPageTemplateCollectionId");

		if (Validator.isNotNull(layoutPageTemplateCollectionId)) {
			return layoutPageTemplateCollectionId;
		}

		try {
			List<LayoutPageTemplateCollection> layoutPageTemplateCollections =
				getLayoutPageTemplateCollections();

			if (ListUtil.isNotEmpty(layoutPageTemplateCollections)) {
				LayoutPageTemplateCollection layoutPageTemplateCollection =
					layoutPageTemplateCollections.get(0);

				layoutPageTemplateCollectionId =
					layoutPageTemplateCollection.
						getLayoutPageTemplateCollectionId();

				return layoutPageTemplateCollectionId;
			}
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}
		}

		return layoutPageTemplateCollectionId;
	}

	public List<LayoutPageTemplateCollection> getLayoutPageTemplateCollections()
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		return LayoutPageTemplateCollectionServiceUtil.
			getLayoutPageTemplateCollections(themeDisplay.getScopeGroupId());
	}

	@Override
	public SearchContainer getLayoutPageTemplateEntriesSearchContainer()
		throws PortalException {

		if (this.layoutPageTemplateEntriesSearchContainer != null) {
			return this.layoutPageTemplateEntriesSearchContainer;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		SearchContainer layoutPageTemplateEntriesSearchContainer =
			new SearchContainer(
				renderRequest, renderResponse.createRenderURL(), null,
				"there-are-no-page-templates");

		layoutPageTemplateEntriesSearchContainer.setOrderByCol(getOrderByCol());

		OrderByComparator<LayoutPageTemplateEntry> orderByComparator =
			LayoutPageTemplatePortletUtil.
				getLayoutPageTemplateEntryOrderByComparator(
					getOrderByCol(), getOrderByType());

		layoutPageTemplateEntriesSearchContainer.setOrderByComparator(
			orderByComparator);

		layoutPageTemplateEntriesSearchContainer.setOrderByType(
			getOrderByType());

		List<LayoutPageTemplateEntry> layoutPageTemplateEntries =
			LayoutPageTemplateEntryLocalServiceUtil.
				getLayoutPageTemplateEntries(
					themeDisplay.getScopeGroupId(),
					getLayoutPageTemplateCollectionId(),
					layoutPageTemplateEntriesSearchContainer.getStart(),
					layoutPageTemplateEntriesSearchContainer.getEnd(),
					orderByComparator);

		int layoutPageTemplateEntriesCount =
			LayoutPageTemplateEntryServiceUtil.
				getLayoutPageTemplateEntriesCount(
					themeDisplay.getScopeGroupId(),
					getLayoutPageTemplateCollectionId());

		layoutPageTemplateEntriesSearchContainer.setResults(
			layoutPageTemplateEntries);
		layoutPageTemplateEntriesSearchContainer.setTotal(
			layoutPageTemplateEntriesCount);

		this.layoutPageTemplateEntriesSearchContainer =
			layoutPageTemplateEntriesSearchContainer;

		return this.layoutPageTemplateEntriesSearchContainer;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SelectLayoutPageTemplateDisplayContext.class);

}