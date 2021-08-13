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

import {createPortletURL, fetch, objectToFormData} from 'frontend-js-web';

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

			const element = document.querySelector(
				`[data-collection-display-item-id="${targetCollectionId}"]`
			);

			if (element) {
				const filterValueMap = getTargetCollectionFilterValueMap(
					targetCollectionId
				);

				const url = createPortletURL(
					themeDisplay.getPathMain() +
						'/portal/layout/render_collection'
				);

				fetch(url.toString(), {
					body: objectToFormData({
						collectionItemId: targetCollectionId,
						filterValues: JSON.stringify(
							Array.from(filterValueMap)
								.map(([fragmentEntryLinkId, value]) => ({ fragmentEntryLinkId, value }))
						),
					}),
					method: 'POST',
				})
					.then((response) => {
						if (response.status >= 400 || response.status < 200) {
							throw new Error(response);
						}

						return response.text();
					})
					.then((html) => {
						if (!html) {
							throw new Error();
						}

						element.innerHTML = html;
					})
					.catch(() => {
						if (process.env.NODE_ENV === 'development') {
							console.error(
								`Could not update collection "${targetCollectionId}"`
							);
						}
					});
			}
			else if (process.env.NODE_ENV === 'development') {
				console.error(
					`Cannot find collection display for itemId "${targetCollectionId}" linked to filter "${filterFragmentEntryLinkId}"`
				);
			}
		}
	);
}
