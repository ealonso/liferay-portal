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

import {
	getFilterFragmentCollections,
	getTargetCollectionFilterValueMap,
	setTargetCollectionFilterValue,
} from './FragmentEntryLinkTargetCollectionsRegister';

/**
 * @param {string} filterFragmentEntryLinkId
 * @param {*} value
 */
export function onCollectionFilterChange(filterFragmentEntryLinkId, value) {
	getFilterFragmentCollections(filterFragmentEntryLinkId).forEach(
		(targetCollectionId) => {
			setTargetCollectionFilterValue(
				filterFragmentEntryLinkId,
				targetCollectionId,
				value
			);

			const filterValueMap = getTargetCollectionFilterValueMap(
				targetCollectionId
			);

			const search = new URLSearchParams(window.location.search);

			Array.from(filterValueMap).forEach(
				([fragmentEntryLinkId, value]) => {
					search.append(
						`categoryId_${fragmentEntryLinkId}`,
						Object.values(value).toString());
				}
			);

			window.location.search = search;
		}
	);
}
