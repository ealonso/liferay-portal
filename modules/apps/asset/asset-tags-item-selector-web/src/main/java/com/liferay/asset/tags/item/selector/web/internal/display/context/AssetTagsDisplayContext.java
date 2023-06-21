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

package com.liferay.asset.tags.item.selector.web.internal.display.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagServiceUtil;
import com.liferay.asset.tags.item.selector.web.internal.constants.AssetTagsSelectorPortletKeys;
import com.liferay.asset.tags.item.selector.web.internal.search.EntriesChecker;
import com.liferay.item.selector.ItemSelector;
import com.liferay.asset.tags.item.selector.AssetTagsItemSelectorReturnType;
import com.liferay.asset.tags.item.selector.criterion.AssetTagsItemSelectorCriterion;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.SearchOrderByUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.asset.util.comparator.AssetTagNameComparator;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;

import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author Stefan Tanasie
 */
public class AssetTagsDisplayContext {

	public AssetTagsDisplayContext(
		ItemSelector itemSelector,
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		RenderResponse renderResponse, RenderRequest renderRequest,
		AssetTagsItemSelectorCriterion assetTagsItemSelectorCriterion) {

		_httpServletRequest = httpServletRequest;
		_itemSelector = itemSelector;
		_portletURL = portletURL;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_rowChecker = false;
	}

	public String getSelectAssetTagsURL() {
		if (_selectSegmentsEntryURL != null) {
			return _selectSegmentsEntryURL;
		}

		AssetTagsItemSelectorCriterion assetTagsItemSelectorCriterion =
			new AssetTagsItemSelectorCriterion();

		assetTagsItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.singletonList(
				new AssetTagsItemSelectorReturnType()));

		_selectSegmentsEntryURL = String.valueOf(
			_itemSelector.getItemSelectorURL(
				RequestBackedPortletURLFactoryUtil.create(_renderRequest),
				"selectEntity", assetTagsItemSelectorCriterion));

		return _selectSegmentsEntryURL;
	}

	public SearchContainer<AssetTag> getTagSearchContainer() {
		if (_tagsSearchContainer != null) {
			return _tagsSearchContainer;
		}

		SearchContainer<AssetTag> tagsSearchContainer = new SearchContainer(
			_renderRequest, _portletURL, null, "there-are-no-tags");

		tagsSearchContainer.setOrderByCol(_getOrderByCol());

		boolean orderByAsc = false;

		String orderByType = _getOrderByType();

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		tagsSearchContainer.setOrderByComparator(
			new AssetTagNameComparator(orderByAsc));
		tagsSearchContainer.setOrderByType(orderByType);
		tagsSearchContainer.setResultsAndTotal(
			() -> AssetTagServiceUtil.getTags(
				_getGroupIds(), _getKeywords(), tagsSearchContainer.getStart(),
				tagsSearchContainer.getEnd(),
				tagsSearchContainer.getOrderByComparator()),
			AssetTagServiceUtil.getTagsCount(_getGroupIds(), _getKeywords()));

		if (_rowChecker) {
			tagsSearchContainer.setRowChecker(
				new EntriesChecker(_renderRequest, _renderResponse));
		}

		_tagsSearchContainer = tagsSearchContainer;

		return _tagsSearchContainer;
	}

	protected String _getEventName() {
		if (Validator.isNotNull(_eventName)) {
			return _eventName;
		}

		_eventName = ParamUtil.getString(
			_httpServletRequest, "eventName",
			_renderResponse.getNamespace() + "selectTag");

		return _eventName;
	}

	private String[] _getSelectedTagNames() {
		if (ArrayUtil.isNotEmpty(_selectedTagNames)) {
			return _selectedTagNames;
		}

		_selectedTagNames = ParamUtil.getStringValues(
			_renderRequest, "selectedTagNames");

		return _selectedTagNames;
	}

	private PortletURL _getTagNamesURL() {
		return PortletURLBuilder.createRenderURL(
			_renderResponse
		).setMVCPath(
			_getMvcPath()
		).setParameter(
			"eventName", _getEventName()
		).setParameter(
			"groupIds", StringUtil.merge(_getGroupIds())
		).setParameter(
			"selectedTagNames", StringUtil.merge(_getSelectedTagNames())
		).buildPortletURL();
	}

	private long[] _getGroupIds() {
		if (ArrayUtil.isNotEmpty(_groupIds)) {
			return _groupIds;
		}

		long[] groupIds = StringUtil.split(
			ParamUtil.getString(_httpServletRequest, "groupIds"), 0L);

		if (ArrayUtil.isEmpty(groupIds)) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		for (long groupId : groupIds) {
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);

			if ((group == null) || !group.isLayout() ||
				ArrayUtil.contains(groupIds, group.getParentGroupId())) {

				continue;
			}

			try {
				groupIds = ArrayUtil.append(
					groupIds,
					PortalUtil.getCurrentAndAncestorSiteGroupIds(
						group.getParentGroupId()));
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException);
				}
			}
		}

		_groupIds = groupIds;

		return _groupIds;
	}

	private String _getKeywords() {
		if (Validator.isNotNull(_keywords)) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(_httpServletRequest, "keywords", null);

		return _keywords;
	}

	private String _getMvcPath() {
		if (Validator.isNotNull(_mvcPath)) {
			return _mvcPath;
		}

		_mvcPath = ParamUtil.getString(
			_httpServletRequest, "mvcPath", "/view.jsp");

		return _mvcPath;
	}

	private String _getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = SearchOrderByUtil.getOrderByCol(
			_httpServletRequest,
			AssetTagsSelectorPortletKeys.ASSET_TAGS_SELECTOR, "name");

		return _orderByCol;
	}

	private String _getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = SearchOrderByUtil.getOrderByType(
			_httpServletRequest,
			AssetTagsSelectorPortletKeys.ASSET_TAGS_SELECTOR, "asc");

		return _orderByType;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetTagsDisplayContext.class);

	private final RenderResponse _renderResponse;
	private String _mvcPath;
	private String _keywords;
	private long[] _groupIds;
	private boolean _rowChecker;
	private String _eventName;
	private String[] _selectedTagNames;
	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;
	private SearchContainer<AssetTag> _tagsSearchContainer;
	private String _orderByCol;
	private String _orderByType;
	private final PortletURL _portletURL;
	private final RenderRequest _renderRequest;
	private String _selectSegmentsEntryURL;

}