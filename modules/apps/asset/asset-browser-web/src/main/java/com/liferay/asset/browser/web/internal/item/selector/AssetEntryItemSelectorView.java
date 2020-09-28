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

package com.liferay.asset.browser.web.internal.item.selector;

import com.liferay.asset.browser.web.internal.configuration.AssetBrowserWebConfigurationValues;
import com.liferay.asset.browser.web.internal.search.AddAssetEntryChecker;
import com.liferay.asset.browser.web.internal.search.AssetBrowserSearch;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.util.AssetHelper;
import com.liferay.depot.model.DepotEntry;
import com.liferay.depot.service.DepotEntryService;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.item.selector.criteria.AssetEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.item.selector.criteria.asset.criterion.AssetEntryItemSelectorCriterion;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(service = ItemSelectorView.class)
public class AssetEntryItemSelectorView
	implements ItemSelectorView<AssetEntryItemSelectorCriterion> {

	@Override
	public Class<? extends AssetEntryItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return AssetEntryItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return _language.get(locale, "select-asset");
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			AssetEntryItemSelectorCriterion assetEntryItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		_itemSelectorViewDescriptorRenderer.renderHTML(
			servletRequest, servletResponse, assetEntryItemSelectorCriterion,
			portletURL, itemSelectedEventName, search,
			new AssetEntryItemSelectorViewDescriptor(
				assetEntryItemSelectorCriterion,
				(HttpServletRequest)servletRequest, portletURL));
	}

	private long _getGroupId(
		AssetEntryItemSelectorCriterion assetEntryItemSelectorCriterion,
		ServletRequest servletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)servletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (assetEntryItemSelectorCriterion.getGroupId() ==
				themeDisplay.getRefererGroupId()) {

			return themeDisplay.getScopeGroupId();
		}

		return assetEntryItemSelectorCriterion.getGroupId();
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
			new AssetEntryItemSelectorReturnType());

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetHelper _assetHelper;

	@Reference
	private DepotEntryService _depotEntryService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ItemSelectorViewDescriptorRenderer<AssetEntryItemSelectorCriterion>
		_itemSelectorViewDescriptorRenderer;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference(target = "(osgi.web.symbolicname=com.liferay.asset.browser.web)")
	private ServletContext _servletContext;

	private class AssetEntryItemSelectorViewDescriptor
		implements ItemSelectorViewDescriptor<AssetEntry> {

		public AssetEntryItemSelectorViewDescriptor(
			AssetEntryItemSelectorCriterion assetEntryItemSelectorCriterion,
			HttpServletRequest httpServletRequest, PortletURL portletURL) {

			_assetEntryItemSelectorCriterion = assetEntryItemSelectorCriterion;
			_httpServletRequest = httpServletRequest;
			_portletURL = portletURL;

			_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
		}

		@Override
		public String getDefaultDisplayStyle() {
			return "descriptive";
		}

		@Override
		public ItemDescriptor getItemDescriptor(AssetEntry assetEntry) {
			AssetRenderer<?> assetRenderer = assetEntry.getAssetRenderer();
			AssetRendererFactory<?> assetRendererFactory =
				assetEntry.getAssetRendererFactory();

			return new ItemDescriptor() {

				@Override
				public String getIcon() {
					AssetRendererFactory<?> assetRendererFactory =
						assetEntry.getAssetRendererFactory();

					return assetRendererFactory.getIconCssClass();
				}

				@Override
				public String getImageURL() {
					AssetRenderer<?> assetRenderer =
						assetEntry.getAssetRenderer();

					PortletRequest portletRequest =
						(PortletRequest)_httpServletRequest.getAttribute(
							JavaConstants.JAVAX_PORTLET_REQUEST);

					try {
						return assetRenderer.getThumbnailPath(portletRequest);
					}
					catch (Exception exception) {
					}

					return null;
				}

				@Override
				public String getPayload() {
					JSONObject jsonObject = JSONUtil.put(
						"assetclassname", assetEntry.getClassName()
					).put(
						"assetclassnameid",
						String.valueOf(assetEntry.getClassNameId())
					).put(
						"assetclasspk", String.valueOf(assetEntry.getClassPK())
					).put(
						"assettitle",
						assetRenderer.getTitle(_themeDisplay.getLocale())
					).put(
						"assettype",
						assetRendererFactory.getTypeName(
							_themeDisplay.getLocale(),
							_assetEntryItemSelectorCriterion.
								getSubtypeSelectionId())
					).put(
						"entityid", String.valueOf(assetEntry.getEntryId())
					);

					Group group = _groupLocalService.fetchGroup(
						assetEntry.getGroupId());

					if (group != null) {
						try {
							jsonObject.put(
								"groupdescriptivename",
								group.getDescriptiveName(
									_themeDisplay.getLocale()));
						}
						catch (Exception exception) {
						}
					}

					return jsonObject.toString();
				}

				@Override
				public String getSubtitle(Locale locale) {
					if (Validator.isNull(
							_assetEntryItemSelectorCriterion.
								getTypeSelection())) {

						return HtmlUtil.escape(
							assetRendererFactory.getTypeName(
								_themeDisplay.getLocale(),
								_assetEntryItemSelectorCriterion.
									getSubtypeSelectionId()));
					}

					if (_isSearchEverywhere()) {
						Group group = _groupLocalService.fetchGroup(
							assetEntry.getGroupId());

						try {
							return HtmlUtil.escape(
								group.getDescriptiveName(
									_themeDisplay.getLocale()));
						}
						catch (Exception exception) {
						}
					}

					return null;
				}

				@Override
				public String getTitle(Locale locale) {
					return assetRenderer.getTitle(_themeDisplay.getLocale());
				}

			};
		}

		@Override
		public ItemSelectorReturnType getItemSelectorReturnType() {
			return new UUIDItemSelectorReturnType();
		}

		@Override
		public String[] getOrderByKeys() {
			return new String[] {"name", "create-date"};
		}

		public long getRefererAssetEntryId() {
			if (_refererAssetEntryId != null) {
				return _refererAssetEntryId;
			}

			_refererAssetEntryId = ParamUtil.getLong(
				_httpServletRequest, "refererAssetEntryId");

			return _refererAssetEntryId;
		}

		@Override
		public SearchContainer<AssetEntry> getSearchContainer()
			throws PortalException {

			PortletRequest portletRequest =
				(PortletRequest)_httpServletRequest.getAttribute(
					JavaConstants.JAVAX_PORTLET_REQUEST);

			AssetBrowserSearch assetBrowserSearch = new AssetBrowserSearch(
				portletRequest, _portletURL);

			if (!_assetEntryItemSelectorCriterion.isSingleSelect()) {
				PortletResponse portletResponse =
					(PortletResponse)_httpServletRequest.getAttribute(
						JavaConstants.JAVAX_PORTLET_RESPONSE);

				assetBrowserSearch.setRowChecker(
					new AddAssetEntryChecker(
						portletResponse, getRefererAssetEntryId()));
			}

			assetBrowserSearch.setOrderByCol(_getOrderByCol());
			assetBrowserSearch.setOrderByType(_getOrderByType());

			if (AssetBrowserWebConfigurationValues.SEARCH_WITH_DATABASE) {
				long[] subtypeSelectionIds = null;

				if (_assetEntryItemSelectorCriterion.getSubtypeSelectionId() >
						0) {

					subtypeSelectionIds = new long[] {
						_assetEntryItemSelectorCriterion.getSubtypeSelectionId()
					};
				}

				int total = _assetEntryLocalService.getEntriesCount(
					_getFilterGroupIds(), _getClassNameIds(),
					subtypeSelectionIds, _getKeywords(), _getKeywords(),
					_getKeywords(), _getKeywords(), _getListable(), false,
					false);

				assetBrowserSearch.setTotal(total);

				List<AssetEntry> assetEntries =
					_assetEntryLocalService.getEntries(
						_getFilterGroupIds(), _getClassNameIds(),
						subtypeSelectionIds, _getKeywords(), _getKeywords(),
						_getKeywords(), _getKeywords(), _getListable(), false,
						false, assetBrowserSearch.getStart(),
						assetBrowserSearch.getEnd(), "modifiedDate",
						StringPool.BLANK, _getOrderByType(), StringPool.BLANK);

				assetBrowserSearch.setResults(assetEntries);

				return assetBrowserSearch;
			}

			Sort sort = null;

			boolean orderByAsc = false;

			if (Objects.equals(_getOrderByType(), "asc")) {
				orderByAsc = true;
			}

			if (Objects.equals(_getOrderByCol(), "modified-date")) {
				sort = new Sort(
					Field.MODIFIED_DATE, Sort.LONG_TYPE, !orderByAsc);
			}
			else if (Objects.equals(_getOrderByCol(), "title")) {
				String sortFieldName = Field.getSortableFieldName(
					"localized_title_".concat(_themeDisplay.getLanguageId()));

				sort = new Sort(sortFieldName, Sort.STRING_TYPE, !orderByAsc);
			}

			Hits hits = _assetEntryLocalService.search(
				_themeDisplay.getCompanyId(), _getFilterGroupIds(),
				_themeDisplay.getUserId(), _getClassNameIds(),
				_assetEntryItemSelectorCriterion.getSubtypeSelectionId(),
				_getKeywords(),
				_assetEntryItemSelectorCriterion.isShowNonindexable(),
				_getStatuses(), assetBrowserSearch.getStart(),
				assetBrowserSearch.getEnd(), sort);

			assetBrowserSearch.setResults(_assetHelper.getAssetEntries(hits));

			assetBrowserSearch.setTotal(hits.getLength());

			return assetBrowserSearch;
		}

		@Override
		public boolean isShowBreadcrumb() {
			return false;
		}

		@Override
		public boolean isShowSearch() {
			return true;
		}

		private long[] _getClassNameIds() {
			if (_classNameIds != null) {
				return _classNameIds;
			}

			AssetRendererFactory<?> assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(
						_assetEntryItemSelectorCriterion.getTypeSelection());

			if (assetRendererFactory != null) {
				_classNameIds = new long[] {
					assetRendererFactory.getClassNameId()
				};
			}

			return _classNameIds;
		}

		private long[] _getFilterGroupIds() throws PortalException {
			if (_filterGroupIds != null) {
				return _filterGroupIds;
			}

			long groupId = _getGroupId(
				_assetEntryItemSelectorCriterion, _httpServletRequest);

			if (groupId == 0) {
				_filterGroupIds =
					_assetEntryItemSelectorCriterion.getSelectedGroupIds();
			}
			else if (!_isSearchEverywhere()) {
				_filterGroupIds = new long[] {groupId};
			}
			else {
				_filterGroupIds = ArrayUtil.append(
					_portal.getCurrentAndAncestorSiteGroupIds(groupId),
					ListUtil.toLongArray(
						_depotEntryService.getGroupConnectedDepotEntries(
							groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS),
						DepotEntry::getGroupId));
			}

			return _filterGroupIds;
		}

		private String _getKeywords() {
			if (Validator.isNotNull(_keywords)) {
				return _keywords;
			}

			_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

			return _keywords;
		}

		private Boolean _getListable() {
			Boolean listable = null;

			String listableValue = ParamUtil.getString(
				_httpServletRequest, "listable", null);

			if (Validator.isNotNull(listableValue)) {
				listable = ParamUtil.getBoolean(
					_httpServletRequest, "listable", true);
			}

			return listable;
		}

		private String _getOrderByCol() {
			if (_orderByCol != null) {
				return _orderByCol;
			}

			_orderByCol = ParamUtil.getString(
				_httpServletRequest, "orderByCol", "create-date");

			return _orderByCol;
		}

		private String _getOrderByType() {
			if (_orderByType != null) {
				return _orderByType;
			}

			_orderByType = ParamUtil.getString(
				_httpServletRequest, "orderByType", "asc");

			return _orderByType;
		}

		private int[] _getStatuses() {
			int[] statuses = {WorkflowConstants.STATUS_APPROVED};

			if (_assetEntryItemSelectorCriterion.isShowScheduled()) {
				statuses = new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_SCHEDULED
				};
			}

			return statuses;
		}

		private boolean _isSearchEverywhere() {
			if (_searchEverywhere != null) {
				return _searchEverywhere;
			}

			_searchEverywhere = Objects.equals(
				ParamUtil.getString(_httpServletRequest, "scope"),
				"everywhere");

			return _searchEverywhere;
		}

		private final AssetEntryItemSelectorCriterion
			_assetEntryItemSelectorCriterion;
		private long[] _classNameIds;
		private long[] _filterGroupIds;
		private final HttpServletRequest _httpServletRequest;
		private String _keywords;
		private String _orderByCol;
		private String _orderByType;
		private final PortletURL _portletURL;
		private Long _refererAssetEntryId;
		private Boolean _searchEverywhere;
		private final ThemeDisplay _themeDisplay;

	}

}