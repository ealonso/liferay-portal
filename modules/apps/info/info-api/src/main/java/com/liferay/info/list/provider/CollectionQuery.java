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

package com.liferay.info.list.provider;

import com.liferay.info.filter.InfoFilter;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;

/**
 * @author Jorge Ferrer
 * @author Eudaldo Alonso
 */
public class CollectionQuery {

	public static Builder builder() {
		return new Builder();
	}

	public Company getCompany() {
		return _builder._company;
	}

	public Group getGroup() {
		return _builder._group;
	}

	public InfoFilter getInfoFilter() {
		return _builder._infoFilter;
	}

	public Layout getLayout() {
		return _builder._layout;
	}

	public Pagination getPagination() {
		return _builder._pagination;
	}

	public Object getRelatedObject() {
		return _builder._relatedObject;
	}

	public long[] getSegmentEntryIds() {
		return _builder._segmentEntryIds;
	}

	public Sort getSort() {
		return _builder._sort;
	}

	public User getUser() {
		return _builder._user;
	}

	public static class Builder {

		public CollectionQuery build() {
			return new CollectionQuery(this);
		}

		public Builder setCompany(Company company) {
			_company = company;

			return this;
		}

		public Builder setGroup(Group group) {
			_group = group;

			return this;
		}

		public Builder setInfoFilter(InfoFilter infoFilter) {
			_infoFilter = infoFilter;

			return this;
		}

		public Builder setLayout(Layout layout) {
			_layout = layout;

			return this;
		}

		public Builder setPagination(Pagination pagination) {
			_pagination = pagination;

			return this;
		}

		public Builder setRelatedObject(Object relatedObject) {
			_relatedObject = relatedObject;

			return this;
		}

		public Builder setSegmentEntryIds(long[] segmentEntryIds) {
			_segmentEntryIds = segmentEntryIds;

			return this;
		}

		public Builder setSort(Sort sort) {
			_sort = sort;

			return this;
		}

		public Builder setUser(User user) {
			_user = user;

			return this;
		}

		private Company _company;
		private Group _group;
		private InfoFilter _infoFilter;
		private Layout _layout;
		private Pagination _pagination;
		private Object _relatedObject;
		private long[] _segmentEntryIds;
		private Sort _sort;
		private User _user;

	}

	private CollectionQuery(Builder builder) {
		_builder = builder;
	}

	private final Builder _builder;

}