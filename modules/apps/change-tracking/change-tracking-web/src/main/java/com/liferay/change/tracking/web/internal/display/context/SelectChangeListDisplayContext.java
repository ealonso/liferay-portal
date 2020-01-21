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

package com.liferay.change.tracking.web.internal.display.context;

import com.liferay.change.tracking.constants.CTConstants;
import com.liferay.change.tracking.model.CTCollection;
import com.liferay.change.tracking.model.CTPreferences;
import com.liferay.change.tracking.service.CTCollectionLocalServiceUtil;
import com.liferay.change.tracking.service.CTPreferencesLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Samuel Trong Tran
 */
public class SelectChangeListDisplayContext {

	public SelectChangeListDisplayContext(
		HttpServletRequest httpServletRequest, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_httpServletRequest = httpServletRequest;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CTPreferences ctPreferences =
			CTPreferencesLocalServiceUtil.fetchCTPreferences(
				_themeDisplay.getCompanyId(), 0);

		long ctCollectionId = CTConstants.CT_COLLECTION_ID_PRODUCTION;

		if (ctPreferences != null) {
			ctPreferences = CTPreferencesLocalServiceUtil.fetchCTPreferences(
				_themeDisplay.getCompanyId(), _themeDisplay.getUserId());

			if (ctPreferences != null) {
				ctCollectionId = ctPreferences.getCtCollectionId();
			}
		}

		_ctCollectionId = ctCollectionId;
	}

	public String getClearResultsURL() {
		PortletURL clearResultsURL = getPortletURL();

		clearResultsURL.setParameter("keywords", StringPool.BLANK);

		return clearResultsURL.toString();
	}

	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	public String getEmptyResultsMessage() {
		return "no-publications-were-found";
	}

	public String getEventName() {
		return _renderResponse.getNamespace() + "selectChangeList";
	}

	public List<DropdownItem> getFilterDropdownItems() {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							getOrderByDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								_httpServletRequest, "filter-by-navigation"));
					});
			}
		};
	}

	public int getItemsTotal() {
		SearchContainer<CTCollection> searchContainer = getSearchContainer();

		return searchContainer.getTotal();
	}

	public String getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		return _keywords;
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_httpServletRequest, "orderByCol", "modifiedDate");

		return _orderByCol;
	}

	public List<DropdownItem> getOrderByDropdownItems() {
		return new DropdownItemList() {
			{
				add(
					dropdownItem -> {
						dropdownItem.setActive(
							Objects.equals("modifiedDate", getOrderByCol()));
						dropdownItem.setHref(
							getPortletURL(), "orderByCol", "modifiedDate");
						dropdownItem.setLabel(
							LanguageUtil.get(
								_httpServletRequest, "modified-date"));
					});

				add(
					dropdownItem -> {
						dropdownItem.setActive(
							Objects.equals("name", getOrderByCol()));
						dropdownItem.setHref(
							getPortletURL(), "orderByCol", "name");
						dropdownItem.setLabel(
							LanguageUtil.get(_httpServletRequest, "name"));
					});
			}
		};
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_httpServletRequest, "orderByType", "desc");

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath", "/change_lists/select_change_list.jsp");
		portletURL.setParameter("keywords", getKeywords());
		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());

		return portletURL;
	}

	public List<CTCollection> getResults() {
		SearchContainer<CTCollection> searchContainer = getSearchContainer();

		return searchContainer.getResults();
	}

	public String getSearchActionURL() {
		PortletURL searchActionURL = getPortletURL();

		return searchActionURL.toString();
	}

	public SearchContainer<CTCollection> getSearchContainer() {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		SearchContainer<CTCollection> searchContainer = new SearchContainer(
			_renderRequest, new DisplayTerms(_httpServletRequest),
			new DisplayTerms(_httpServletRequest),
			SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA,
			getPortletURL(), null,
			LanguageUtil.get(_httpServletRequest, getEmptyResultsMessage()));

		searchContainer.setDelta(5);
		searchContainer.setDeltaConfigurable(false);
		searchContainer.setId(getSearchContainerId());
		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());

		int count = (int)CTCollectionLocalServiceUtil.dynamicQueryCount(
			_getSearchDynamicQuery(
				_themeDisplay.getCompanyId(), getKeywords()));

		searchContainer.setTotal(count);

		OrderByComparator<CTCollection> orderByComparator =
			OrderByComparatorFactoryUtil.create(
				"CTCollection", getOrderByCol(),
				getOrderByType().equals("asc"));

		List<CTCollection> results = CTCollectionLocalServiceUtil.dynamicQuery(
			_getSearchDynamicQuery(_themeDisplay.getCompanyId(), getKeywords()),
			searchContainer.getStart(), searchContainer.getEnd(),
			orderByComparator);

		searchContainer.setResults(results);

		_searchContainer = searchContainer;

		return _searchContainer;
	}

	public String getSearchContainerId() {
		return "selectChangeList";
	}

	public String getSortingURL() throws Exception {
		PortletURL sortingURL = PortletURLUtil.clone(
			getPortletURL(), _renderResponse);

		sortingURL.setParameter("keywords", getKeywords());
		sortingURL.setParameter(
			"orderByType", getOrderByType().equals("asc") ? "desc" : "asc");

		return sortingURL.toString();
	}

	public boolean showEmptyResultsMessage() {
		List<CTCollection> results = getResults();

		return results.isEmpty();
	}

	private DynamicQuery _getSearchDynamicQuery(
		long companyId, String keywords) {

		DynamicQuery dynamicQuery = CTCollectionLocalServiceUtil.dynamicQuery();

		if (companyId > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("companyId", companyId));
		}

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"status", WorkflowConstants.STATUS_DRAFT));

		Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

		for (String keyword : StringUtil.split(keywords, CharPool.SPACE)) {
			disjunction.add(
				RestrictionsFactoryUtil.ilike(
					"name", StringUtil.quote(keyword, StringPool.PERCENT)));
			disjunction.add(
				RestrictionsFactoryUtil.ilike(
					"description",
					StringUtil.quote(keyword, StringPool.PERCENT)));
		}

		return dynamicQuery.add(disjunction);
	}

	private final long _ctCollectionId;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CTCollection> _searchContainer;
	private final ThemeDisplay _themeDisplay;

}