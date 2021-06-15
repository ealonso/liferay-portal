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
import com.liferay.portal.kernel.model.User;

import java.util.Optional;

/**
 * @author Jorge Ferrer
 * @author Eudaldo Alonso
 */
public class CollectionQuery {

	public static Builder builder() {
		return new Builder();
	}

	public Optional<InfoFilter> getInfoFilterOptional() {
		return Optional.of(_builder._infoFilter);
	}

	public Optional<Pagination> getPaginationOptional() {
		return Optional.of(_builder._pagination);
	}

	public Optional<Sort> getSortOptional() {
		return Optional.of(_builder._sort);
	}

	public Optional<User> getUserOptional() {
		return Optional.of(_builder._user);
	}

	public static class Builder {

		public CollectionQuery build() {
			return new CollectionQuery(this);
		}

		public Builder setInfoFilter(InfoFilter infoFilter) {
			_infoFilter = infoFilter;

			return this;
		}

		public Builder setPagination(Pagination pagination) {
			_pagination = pagination;

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

		private InfoFilter _infoFilter;
		private Pagination _pagination;
		private Sort _sort;
		private User _user;

	}

	private CollectionQuery(Builder builder) {
		_builder = builder;
	}

	private final Builder _builder;

}