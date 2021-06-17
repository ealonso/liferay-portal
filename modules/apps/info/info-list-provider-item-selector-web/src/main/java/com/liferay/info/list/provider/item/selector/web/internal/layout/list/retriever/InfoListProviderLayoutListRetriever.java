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

package com.liferay.info.list.provider.item.selector.web.internal.layout.list.retriever;

import com.liferay.info.filter.InfoFilter;
import com.liferay.info.filter.InfoRequestItemProvider;
import com.liferay.info.item.InfoItemServiceTracker;
import com.liferay.info.item.provider.filter.PropertyInfoItemServiceFilter;
import com.liferay.info.list.provider.CollectionQuery;
import com.liferay.info.list.provider.InfoItemListProvider;
import com.liferay.info.list.provider.InfoListProviderTracker;
import com.liferay.info.list.provider.item.selector.criterion.InfoListProviderItemSelectorReturnType;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.layout.list.retriever.KeyListObjectReference;
import com.liferay.layout.list.retriever.LayoutListRetriever;
import com.liferay.layout.list.retriever.LayoutListRetrieverContext;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = LayoutListRetriever.class)
public class InfoListProviderLayoutListRetriever
	implements LayoutListRetriever
		<InfoListProviderItemSelectorReturnType, KeyListObjectReference> {

	@Override
	public List<Object> getList(
		KeyListObjectReference keyListObjectReference,
		LayoutListRetrieverContext layoutListRetrieverContext) {

		InfoItemListProvider<?, ?> infoItemListProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemListProvider.class, keyListObjectReference.getKey());

		if (infoItemListProvider == null) {
			return Collections.emptyList();
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Optional<Pagination> paginationOptional =
			layoutListRetrieverContext.getPaginationOptional();

		CollectionQuery collectionQuery = CollectionQuery.builder(
		).setGroup(
			_groupLocalService.fetchGroup(serviceContext.getScopeGroupId())
		).setPagination(
			paginationOptional.orElse(
				Pagination.of(QueryUtil.ALL_POS, QueryUtil.ALL_POS))
		).setInfoFilter(
			_getInfoFilter(infoItemListProvider, layoutListRetrieverContext)
		).setUser(
			_userLocalService.fetchUser(PrincipalThreadLocal.getUserId())
		).build();

		InfoPage infoPage = infoItemListProvider.getInfoPage(collectionQuery);

		return infoPage.getPageItems();
	}

	@Override
	public int getListCount(
		KeyListObjectReference keyListObjectReference,
		LayoutListRetrieverContext layoutListRetrieverContext) {

		InfoItemListProvider<?, ?> infoItemListProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoItemListProvider.class, keyListObjectReference.getKey());

		if (infoItemListProvider == null) {
			return 0;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Optional<Pagination> paginationOptional =
			layoutListRetrieverContext.getPaginationOptional();

		CollectionQuery collectionQuery = CollectionQuery.builder(
		).setGroup(
			_groupLocalService.fetchGroup(serviceContext.getScopeGroupId())
		).setPagination(
			paginationOptional.orElse(
				Pagination.of(QueryUtil.ALL_POS, QueryUtil.ALL_POS))
		).setInfoFilter(
			_getInfoFilter(infoItemListProvider, layoutListRetrieverContext)
		).setUser(
			_userLocalService.fetchUser(PrincipalThreadLocal.getUserId())
		).build();

		InfoPage infoPage = infoItemListProvider.getInfoPage(collectionQuery);

		return infoPage.getTotalCount();
	}

	private InfoFilter _getInfoFilter(
		InfoItemListProvider<?, ?> infoItemListProvider,
		LayoutListRetrieverContext layoutListRetrieverContext) {

		Optional<HttpServletRequest> httpServletRequestOptional =
			layoutListRetrieverContext.getHttpServletRequestOptional();

		HttpServletRequest httpServletRequest =
			httpServletRequestOptional.orElse(null);

		if (!httpServletRequestOptional.isPresent()) {
			return null;
		}

		Class<?> infoFilterClass = infoItemListProvider.getInfoFilterClass();

		InfoRequestItemProvider<InfoFilter> infoRequestItemProvider =
			_infoItemServiceTracker.getFirstInfoItemService(
				InfoRequestItemProvider.class, InfoFilter.class.getName(),
				new PropertyInfoItemServiceFilter(
					"infoFilterKey", infoFilterClass.getName()));

		return infoRequestItemProvider.create(httpServletRequest);
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private InfoItemServiceTracker _infoItemServiceTracker;

	@Reference
	private InfoListProviderTracker _infoListProviderTracker;

	@Reference
	private UserLocalService _userLocalService;

}