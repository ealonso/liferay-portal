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

package com.liferay.asset.display.page.item.selector.web.internal;

import com.liferay.asset.display.page.item.selector.criterion.AssetDisplayPageSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.layout.page.template.constants.LayoutPageTemplateEntryTypeConstants;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.service.LayoutPageTemplateEntryService;
import com.liferay.layout.page.template.util.comparator.LayoutPageTemplateEntryCreateDateComparator;
import com.liferay.layout.page.template.util.comparator.LayoutPageTemplateEntryNameComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(service = ItemSelectorView.class)
public class AssetDisplayPagesItemSelectorView
	implements ItemSelectorView<AssetDisplayPageSelectorCriterion> {

	@Override
	public Class<? extends AssetDisplayPageSelectorCriterion>
		getItemSelectorCriterionClass() {

		return AssetDisplayPageSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return _language.get(locale, "display-page-templates");
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			AssetDisplayPageSelectorCriterion assetDisplayPageSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		_itemSelectorViewDescriptorRenderer.renderHTML(
			servletRequest, servletResponse, assetDisplayPageSelectorCriterion,
			portletURL, itemSelectedEventName, search,
			new AssetDisplayPagesItemSelectorViewDescriptor(
				(HttpServletRequest)servletRequest,
				assetDisplayPageSelectorCriterion, portletURL));
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
			new UUIDItemSelectorReturnType());

	@Reference
	private ItemSelectorViewDescriptorRenderer
		<AssetDisplayPageSelectorCriterion> _itemSelectorViewDescriptorRenderer;

	@Reference
	private Language _language;

	@Reference
	private LayoutPageTemplateEntryService _layoutPageTemplateEntryService;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.asset.display.page.item.selector.web)"
	)
	private ServletContext _servletContext;

	private class AssetDisplayPagesItemSelectorViewDescriptor
		implements ItemSelectorViewDescriptor<LayoutPageTemplateEntry> {

		public AssetDisplayPagesItemSelectorViewDescriptor(
			HttpServletRequest httpServletRequest,
			AssetDisplayPageSelectorCriterion assetDisplayPageSelectorCriterion,
			PortletURL portletURL) {

			_httpServletRequest = httpServletRequest;
			_assetDisplayPageSelectorCriterion =
				assetDisplayPageSelectorCriterion;
			_portletURL = portletURL;
		}

		@Override
		public ItemDescriptor getItemDescriptor(
			LayoutPageTemplateEntry layoutPageTemplateEntry) {

			return new ItemDescriptor() {

				@Override
				public String getIcon() {
					return "page";
				}

				@Override
				public String getImageURL() {
					return StringPool.BLANK;
				}

				@Override
				public String getPayload() {
					return JSONUtil.put(
						"id",
						String.valueOf(
							layoutPageTemplateEntry.
								getLayoutPageTemplateEntryId())
					).put(
						"name", layoutPageTemplateEntry.getName()
					).put(
						"type", "asset-display-page"
					).toString();
				}

				@Override
				public String getSubtitle(Locale locale) {
					Date createDate = layoutPageTemplateEntry.getCreateDate();

					String createDateDescription =
						LanguageUtil.getTimeDescription(
							_httpServletRequest,
							System.currentTimeMillis() - createDate.getTime(),
							true);

					return LanguageUtil.format(
						_httpServletRequest, "x-ago", createDateDescription);
				}

				@Override
				public String getTitle(Locale locale) {
					return layoutPageTemplateEntry.getName();
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

		public String getOrderByType() {
			if (Validator.isNotNull(_orderByType)) {
				return _orderByType;
			}

			_orderByType = ParamUtil.getString(
				_httpServletRequest, "orderByType", "asc");

			return _orderByType;
		}

		@Override
		public SearchContainer<LayoutPageTemplateEntry> getSearchContainer()
			throws PortalException {

			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletRequest portletRequest =
				(PortletRequest)_httpServletRequest.getAttribute(
					JavaConstants.JAVAX_PORTLET_REQUEST);

			SearchContainer<LayoutPageTemplateEntry>
				assetDisplayPageSearchContainer = new SearchContainer<>(
					portletRequest, _portletURL, null,
					"there-are-no-display-page-templates");

			assetDisplayPageSearchContainer.setOrderByCol(_getOrderByCol());

			OrderByComparator<LayoutPageTemplateEntry> orderByComparator =
				_getLayoutPageTemplateEntryOrderByComparator(
					_getOrderByCol(), getOrderByType());

			assetDisplayPageSearchContainer.setOrderByComparator(
				orderByComparator);

			assetDisplayPageSearchContainer.setOrderByType(getOrderByType());

			List<LayoutPageTemplateEntry> layoutPageTemplateEntries = null;
			int layoutPageTemplateEntriesCount = 0;

			if (Validator.isNotNull(_getKeywords())) {
				layoutPageTemplateEntriesCount =
					_layoutPageTemplateEntryService.
						getLayoutPageTemplateEntriesCount(
							themeDisplay.getScopeGroupId(),
							_assetDisplayPageSelectorCriterion.getClassNameId(),
							_assetDisplayPageSelectorCriterion.getClassTypeId(),
							_getKeywords(),
							LayoutPageTemplateEntryTypeConstants.
								TYPE_DISPLAY_PAGE,
							WorkflowConstants.STATUS_APPROVED);

				layoutPageTemplateEntries =
					_layoutPageTemplateEntryService.
						getLayoutPageTemplateEntries(
							themeDisplay.getScopeGroupId(),
							_assetDisplayPageSelectorCriterion.getClassNameId(),
							_assetDisplayPageSelectorCriterion.getClassTypeId(),
							_getKeywords(),
							LayoutPageTemplateEntryTypeConstants.
								TYPE_DISPLAY_PAGE,
							WorkflowConstants.STATUS_APPROVED,
							assetDisplayPageSearchContainer.getStart(),
							assetDisplayPageSearchContainer.getEnd(),
							assetDisplayPageSearchContainer.
								getOrderByComparator());
			}
			else {
				layoutPageTemplateEntriesCount =
					_layoutPageTemplateEntryService.
						getLayoutPageTemplateEntriesCount(
							themeDisplay.getScopeGroupId(),
							_assetDisplayPageSelectorCriterion.getClassNameId(),
							_assetDisplayPageSelectorCriterion.getClassTypeId(),
							LayoutPageTemplateEntryTypeConstants.
								TYPE_DISPLAY_PAGE,
							WorkflowConstants.STATUS_APPROVED);

				layoutPageTemplateEntries =
					_layoutPageTemplateEntryService.
						getLayoutPageTemplateEntries(
							themeDisplay.getScopeGroupId(),
							_assetDisplayPageSelectorCriterion.getClassNameId(),
							_assetDisplayPageSelectorCriterion.getClassTypeId(),
							LayoutPageTemplateEntryTypeConstants.
								TYPE_DISPLAY_PAGE,
							WorkflowConstants.STATUS_APPROVED,
							assetDisplayPageSearchContainer.getStart(),
							assetDisplayPageSearchContainer.getEnd(),
							assetDisplayPageSearchContainer.
								getOrderByComparator());
			}

			assetDisplayPageSearchContainer.setTotal(
				layoutPageTemplateEntriesCount);
			assetDisplayPageSearchContainer.setResults(
				layoutPageTemplateEntries);

			return assetDisplayPageSearchContainer;
		}

		@Override
		public boolean isShowBreadcrumb() {
			return false;
		}

		@Override
		public boolean isShowManagementToolbar() {
			return true;
		}

		@Override
		public boolean isShowSearch() {
			return true;
		}

		private String _getKeywords() {
			if (Validator.isNotNull(_keywords)) {
				return _keywords;
			}

			_keywords = ParamUtil.getString(_httpServletRequest, "keywords");

			return _keywords;
		}

		private OrderByComparator<LayoutPageTemplateEntry>
			_getLayoutPageTemplateEntryOrderByComparator(
				String orderByCol, String orderByType) {

			boolean orderByAsc = false;

			if (orderByType.equals("asc")) {
				orderByAsc = true;
			}

			OrderByComparator<LayoutPageTemplateEntry> orderByComparator = null;

			if (orderByCol.equals("create-date")) {
				orderByComparator =
					new LayoutPageTemplateEntryCreateDateComparator(orderByAsc);
			}
			else if (orderByCol.equals("name")) {
				orderByComparator = new LayoutPageTemplateEntryNameComparator(
					orderByAsc);
			}

			return orderByComparator;
		}

		private String _getOrderByCol() {
			if (Validator.isNotNull(_orderByCol)) {
				return _orderByCol;
			}

			_orderByCol = ParamUtil.getString(
				_httpServletRequest, "orderByCol", "create-date");

			return _orderByCol;
		}

		private final AssetDisplayPageSelectorCriterion
			_assetDisplayPageSelectorCriterion;
		private final HttpServletRequest _httpServletRequest;
		private String _keywords;
		private String _orderByCol;
		private String _orderByType;
		private final PortletURL _portletURL;

	}

}