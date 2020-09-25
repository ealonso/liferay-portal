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

package com.liferay.site.navigation.item.selector.web.internal;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.site.navigation.item.selector.criterion.SiteNavigationMenuItemSelectorCriterion;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.service.SiteNavigationMenuService;
import com.liferay.site.navigation.util.comparator.SiteNavigationMenuCreateDateComparator;
import com.liferay.site.navigation.util.comparator.SiteNavigationMenuNameComparator;

import java.io.IOException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

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
 * @author Pavel Savinov
 */
@Component(
	property = "item.selector.view.order:Integer=300",
	service = ItemSelectorView.class
)
public class SiteNavigationMenuItemSelectorView
	implements ItemSelectorView<SiteNavigationMenuItemSelectorCriterion> {

	@Override
	public Class<SiteNavigationMenuItemSelectorCriterion>
		getItemSelectorCriterionClass() {

		return SiteNavigationMenuItemSelectorCriterion.class;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, SiteNavigationMenuItemSelectorView.class);

		return ResourceBundleUtil.getString(resourceBundle, "navigation-menus");
	}

	@Override
	public void renderHTML(
			ServletRequest servletRequest, ServletResponse servletResponse,
			SiteNavigationMenuItemSelectorCriterion
				siteNavigationMenuItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		_itemSelectorViewDescriptorRenderer.renderHTML(
			servletRequest, servletResponse,
			siteNavigationMenuItemSelectorCriterion, portletURL,
			itemSelectedEventName, search,
			new SiteNavigationMenuItemSelectorViewDescriptor(
				(HttpServletRequest)servletRequest, portletURL));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteNavigationMenuItemSelectorView.class);

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.singletonList(
			new UUIDItemSelectorReturnType());

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ItemSelectorViewDescriptorRenderer
		<SiteNavigationMenuItemSelectorCriterion>
			_itemSelectorViewDescriptorRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.site.navigation.item.selector.web)"
	)
	private ServletContext _servletContext;

	@Reference
	private SiteNavigationMenuService _siteNavigationMenuService;

	private class SiteNavigationMenuItemSelectorViewDescriptor
		implements ItemSelectorViewDescriptor<SiteNavigationMenu> {

		public SiteNavigationMenuItemSelectorViewDescriptor(
			HttpServletRequest httpServletRequest, PortletURL portletURL) {

			_httpServletRequest = httpServletRequest;
			_portletURL = portletURL;
		}

		@Override
		public String getDefaultDisplayStyle() {
			return "descriptive";
		}

		@Override
		public ItemDescriptor getItemDescriptor(
			SiteNavigationMenu siteNavigationMenu) {

			return new ItemDescriptor() {

				@Override
				public String getIcon() {
					return "pages-tree";
				}

				@Override
				public String getImageURL() {
					return StringPool.BLANK;
				}

				@Override
				public String getPayload() {
					return JSONUtil.put(
						"id", siteNavigationMenu.getSiteNavigationMenuId()
					).put(
						"name", _getName()
					).toString();
				}

				@Override
				public String getSubtitle(Locale locale) {
					Date createDate = siteNavigationMenu.getCreateDate();

					String createDateDescription =
						LanguageUtil.getTimeDescription(
							_httpServletRequest,
							System.currentTimeMillis() - createDate.getTime(),
							true);

					return LanguageUtil.format(
						_httpServletRequest, "x-created-x-ago",
						new String[] {
							siteNavigationMenu.getUserName(),
							createDateDescription
						});
				}

				@Override
				public String getTitle(Locale locale) {
					return _getName();
				}

				@Override
				public boolean isCompact() {
					return true;
				}

				private String _getName() {
					ThemeDisplay themeDisplay =
						(ThemeDisplay)_httpServletRequest.getAttribute(
							WebKeys.THEME_DISPLAY);

					if (siteNavigationMenu.getGroupId() ==
							themeDisplay.getScopeGroupId()) {

						return HtmlUtil.escape(siteNavigationMenu.getName());
					}

					Group group = _groupLocalService.fetchGroup(
						siteNavigationMenu.getGroupId());

					String name = siteNavigationMenu.getName();

					try {
						name = StringUtil.appendParentheticalSuffix(
							name,
							group.getDescriptiveName(themeDisplay.getLocale()));
					}
					catch (PortalException portalException) {
						if (_log.isDebugEnabled()) {
							_log.debug(portalException, portalException);
						}
					}

					return HtmlUtil.escape(name);
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

		@Override
		public SearchContainer<SiteNavigationMenu> getSearchContainer()
			throws PortalException {

			ThemeDisplay themeDisplay =
				(ThemeDisplay)_httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			PortletRequest portletRequest =
				(PortletRequest)_httpServletRequest.getAttribute(
					JavaConstants.JAVAX_PORTLET_REQUEST);

			SearchContainer<SiteNavigationMenu> searchContainer =
				new SearchContainer<>(
					portletRequest, _portletURL, null,
					"there-are-no-navigation-menus");

			OrderByComparator<SiteNavigationMenu> orderByComparator =
				_getOrderByComparator(_getOrderByCol(), _getOrderByType());

			searchContainer.setOrderByCol(_getOrderByCol());
			searchContainer.setOrderByComparator(orderByComparator);
			searchContainer.setOrderByType(_getOrderByType());

			List<SiteNavigationMenu> menus = null;
			int menusCount = 0;

			long[] groupIds = {themeDisplay.getScopeGroupId()};

			Group scopeGroup = themeDisplay.getScopeGroup();

			if (!scopeGroup.isCompany()) {
				groupIds = ArrayUtil.append(
					groupIds, themeDisplay.getCompanyGroupId());
			}

			if (Validator.isNotNull(_getKeywords())) {
				menus = _siteNavigationMenuService.getSiteNavigationMenus(
					groupIds, _getKeywords(), searchContainer.getStart(),
					searchContainer.getEnd(), orderByComparator);

				menusCount =
					_siteNavigationMenuService.getSiteNavigationMenusCount(
						groupIds, _getKeywords());
			}
			else {
				menus = _siteNavigationMenuService.getSiteNavigationMenus(
					groupIds, searchContainer.getStart(),
					searchContainer.getEnd(), orderByComparator);

				menusCount =
					_siteNavigationMenuService.getSiteNavigationMenusCount(
						groupIds);
			}

			searchContainer.setResults(menus);
			searchContainer.setTotal(menusCount);

			return searchContainer;
		}

		@Override
		public boolean isShowBreadcrumb() {
			return false;
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

		private String _getOrderByCol() {
			if (_orderByCol != null) {
				return _orderByCol;
			}

			_orderByCol = ParamUtil.getString(
				_httpServletRequest, "orderByCol", "create-date");

			return _orderByCol;
		}

		private OrderByComparator<SiteNavigationMenu> _getOrderByComparator(
			String orderByCol, String orderByType) {

			boolean orderByAsc = false;

			if (orderByType.equals("asc")) {
				orderByAsc = true;
			}

			OrderByComparator<SiteNavigationMenu> orderByComparator = null;

			if (orderByCol.equals("create-date")) {
				orderByComparator = new SiteNavigationMenuCreateDateComparator(
					orderByAsc);
			}
			else if (orderByCol.equals("name")) {
				orderByComparator = new SiteNavigationMenuNameComparator(
					orderByAsc);
			}

			return orderByComparator;
		}

		private String _getOrderByType() {
			if (_orderByType != null) {
				return _orderByType;
			}

			_orderByType = ParamUtil.getString(
				_httpServletRequest, "orderByType", "asc");

			return _orderByType;
		}

		private final HttpServletRequest _httpServletRequest;
		private String _keywords;
		private String _orderByCol;
		private String _orderByType;
		private final PortletURL _portletURL;

	}

}