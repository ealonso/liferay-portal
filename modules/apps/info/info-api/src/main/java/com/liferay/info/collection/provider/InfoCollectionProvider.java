/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.collection.provider;

import com.liferay.info.pagination.InfoPage;
import com.liferay.info.type.Keyed;
import com.liferay.info.type.Labeled;
import com.liferay.petra.reflect.GenericUtil;

/**
 * @author Jorge Ferrer
 */
public interface InfoCollectionProvider<T> extends Keyed, Labeled {

	public default InfoPage<T> getCollectionInfoPage(
		CollectionContext collectionContext, CollectionQuery collectionQuery) {

		return getCollectionInfoPage(collectionQuery);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #getCollectionInfoPage(CollectionContext, CollectionQuery)}
	 */
	@Deprecated
	public InfoPage<T> getCollectionInfoPage(CollectionQuery collectionQuery);

	public default Class<?> getCollectionItemClass() {
		return GenericUtil.getGenericClass(this);
	}

	public default String getCollectionItemClassName() {
		Class<?> clazz = getCollectionItemClass();

		return clazz.getName();
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 *             #isAvailable(CollectionContext)}
	 */
	@Deprecated
	public default boolean isAvailable() {
		return true;
	}

	public default boolean isAvailable(CollectionContext collectionContext) {
		return true;
	}

}