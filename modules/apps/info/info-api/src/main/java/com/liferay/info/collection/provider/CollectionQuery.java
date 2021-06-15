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

package com.liferay.info.collection.provider;

import com.liferay.info.filter.InfoFilter;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import java.util.Optional;

/**
 * @author Jorge Ferrer
 * @author Eudaldo Alonso
 */
public class CollectionQuery {

	public Company getCompany() {
		return _company;
	}

	public Group getGroup() {
		return _group;
	}

	public Optional<InfoFilter> getInfoFiltersOptional() {
		return Optional.ofNullable(_infoFilter);
	}

	public Layout getLayout() {
		return _layout;
	}

	public Pagination getPagination() {
		if (_pagination == null) {
			return Pagination.of(20, 0);
		}

		return _pagination;
	}

	public Optional<Sort> getSortOptional() {
		return Optional.ofNullable(_sort);
	}

	public User getUser() {
		return _user;
	}

	public void setContext(Company company) {
		_company = company;
	}

	public void setContext(Group group) {
		_group = group;

		_company = CompanyLocalServiceUtil.fetchCompany(group.getCompanyId());
	}

	public void setContext(Layout layout) {
		_layout = layout;

		_company = CompanyLocalServiceUtil.fetchCompany(layout.getCompanyId());
		_group = layout.getGroup();
	}

	public void setInfoFilter(InfoFilter infoFilter) {
		_infoFilter = infoFilter;
	}

	public void setPagination(Pagination pagination) {
		_pagination = pagination;
	}

	public void setSort(Sort sort) {
		_sort = sort;
	}

	public void setUser(User user) {
		_user = user;
	}

	private Company _company;
	private Group _group;
	private InfoFilter _infoFilter;
	private Layout _layout;
	private Pagination _pagination;
	private Sort _sort;
	private User _user;

}