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

package com.liferay.layout.utility.page.admin.web.internal.display.context;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.layout.utility.page.model.LayoutUtilityPageEntry;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryService;
import com.liferay.layout.utility.page.service.LayoutUtilityPageEntryServiceUtil;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jürgen Kappler
 */
public class LayoutUtilityPageDisplayContext {

	public LayoutUtilityPageDisplayContext(
		ItemSelector itemSelector,
		LayoutUtilityPageEntryService layoutUtilityPageEntryService,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_itemSelector = itemSelector;
		_layoutUtilityPageEntryService = layoutUtilityPageEntryService;

		_httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_liferayPortletRequest = PortalUtil.getLiferayPortletRequest(
			renderRequest);
		_liferayPortletResponse = PortalUtil.getLiferayPortletResponse(
			renderResponse);
	}

	public String getEventName() {
		return _liferayPortletResponse.getNamespace() + "selectPage";
	}

	public String getItemSelectorURL() {
		LayoutItemSelectorCriterion layoutItemSelectorCriterion =
			new LayoutItemSelectorCriterion();

		layoutItemSelectorCriterion.setCheckDisplayPage(false);
		layoutItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			new UUIDItemSelectorReturnType());
		layoutItemSelectorCriterion.setEnableCurrentPage(false);
		layoutItemSelectorCriterion.setShowBreadcrumb(false);
		layoutItemSelectorCriterion.setShowHiddenPages(true);

		layoutItemSelectorCriterion.setShowPrivatePages(false);
		layoutItemSelectorCriterion.setShowPublicPages(true);

		return PortletURLBuilder.create(
			_itemSelector.getItemSelectorURL(
				RequestBackedPortletURLFactoryUtil.create(
					_liferayPortletRequest),
				getEventName(), layoutItemSelectorCriterion)
		).setParameter(
			"layoutUuid", getLayoutUuid()
		).buildString();
	}

	public String getLayoutName() {
		Layout layout = _getLayout();

		if (layout == null) {
			return StringPool.BLANK;
		}

		return layout.getName(_themeDisplay.getLocale());
	}

	public LayoutUtilityPageEntry getLayoutUtilityPageEntry() {
		if (_layoutUtilityPageEntry != null) {
			return _layoutUtilityPageEntry;
		}

		long layoutUtilityPageEntryId = getLayoutUtilityPageEntryId();

		LayoutUtilityPageEntry layoutUtilityPageEntry = null;

		if (layoutUtilityPageEntryId > 0) {
			layoutUtilityPageEntry =
				LayoutUtilityPageEntryServiceUtil.fetchLayoutUtilityPageEntry(
					layoutUtilityPageEntryId);
		}

		_layoutUtilityPageEntry = layoutUtilityPageEntry;

		return layoutUtilityPageEntry;
	}

	public Long getLayoutUtilityPageEntryId() {
		if (_layoutUtilityPageEntryId != null) {
			return _layoutUtilityPageEntryId;
		}

		_layoutUtilityPageEntryId = ParamUtil.getLong(
			_httpServletRequest, "layoutUtilityPageEntryId");

		return _layoutUtilityPageEntryId;
	}

	public SearchContainer<LayoutUtilityPageEntry>
		getLayoutUtilityPageEntrySearchContainer() {

		if (_layoutUtilityPageEntrySearchContainer != null) {
			return _layoutUtilityPageEntrySearchContainer;
		}

		SearchContainer<LayoutUtilityPageEntry>
			layoutUtilityPageEntrySearchContainer = new SearchContainer<>(
				_liferayPortletRequest, _getPortletURL(), null,
				"there-are-no-utility-pages");

		layoutUtilityPageEntrySearchContainer.setOrderByCol(getOrderByCol());
		layoutUtilityPageEntrySearchContainer.setOrderByType(getOrderByType());
		layoutUtilityPageEntrySearchContainer.setResultsAndTotal(
			() -> _layoutUtilityPageEntryService.getLayoutUtilityPageEntries(
				_themeDisplay.getScopeGroupId(),
				layoutUtilityPageEntrySearchContainer.getStart(),
				layoutUtilityPageEntrySearchContainer.getEnd(), null),
			_layoutUtilityPageEntryService.getLayoutUtilityPageEntriesCount(
				_themeDisplay.getScopeGroupId()));
		layoutUtilityPageEntrySearchContainer.setRowChecker(
			new EmptyOnClickRowChecker(_liferayPortletResponse));

		_layoutUtilityPageEntrySearchContainer =
			layoutUtilityPageEntrySearchContainer;

		return _layoutUtilityPageEntrySearchContainer;
	}

	public String getLayoutUuid() {
		Layout layout = _getLayout();

		if (layout == null) {
			return StringPool.BLANK;
		}

		return layout.getUuid();
	}

	public String getTitle() {
		LayoutUtilityPageEntry layoutUtilityPageEntry =
			getLayoutUtilityPageEntry();

		if (layoutUtilityPageEntry == null) {
			return LanguageUtil.get(_httpServletRequest, "new-utility-page");
		}

		return layoutUtilityPageEntry.getName();
	}

	protected String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_liferayPortletRequest, SearchContainer.DEFAULT_ORDER_BY_COL_PARAM,
			"modified-date");

		return _orderByCol;
	}

	protected String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_liferayPortletRequest, SearchContainer.DEFAULT_ORDER_BY_TYPE_PARAM,
			"asc");

		return _orderByType;
	}

	private Layout _getLayout() {
		if (_layout != null) {
			return _layout;
		}

		LayoutUtilityPageEntry layoutUtilityPageEntry =
			getLayoutUtilityPageEntry();

		if (layoutUtilityPageEntry == null) {
			return null;
		}

		_layout = LayoutLocalServiceUtil.fetchLayout(
			layoutUtilityPageEntry.getPlid());

		return _layout;
	}

	private PortletURL _getPortletURL() {
		return PortletURLBuilder.createRenderURL(
			_liferayPortletResponse
		).buildPortletURL();
	}

	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private Layout _layout;
	private LayoutUtilityPageEntry _layoutUtilityPageEntry;
	private Long _layoutUtilityPageEntryId;
	private SearchContainer<LayoutUtilityPageEntry>
		_layoutUtilityPageEntrySearchContainer;
	private final LayoutUtilityPageEntryService _layoutUtilityPageEntryService;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
	private final ThemeDisplay _themeDisplay;

}