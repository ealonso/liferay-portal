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

/**
 * @type {Map<string, string[]>}
 */
const filterTargetCollections = new Map();

/**
 * @type {Map<string, Map<string, *>>}
 */
const targetCollectionFilterValues = new Map();

/**
 * @param {string} filterFragmentEntryLinkId
 * @returns {string[]}
 */
export const getFilterFragmentCollections = (filterFragmentEntryLinkId) => {
	return filterTargetCollections.get(filterFragmentEntryLinkId) || [];
};

/**
 * @param {string} targetCollectionId
 * @return {Map<string, *> | null}
 */
export const getTargetCollectionFilterValueMap = (targetCollectionId) => {
	return targetCollectionFilterValues.get(targetCollectionId);
};

/**
 * @param {string} filterFragmentEntryLinkId
 * @param {string} targetCollectionId
 * @param {*} value
 */
export const setTargetCollectionFilterValue = (
	filterFragmentEntryLinkId,
	targetCollectionId,
	value
) => {
	const filterValueMap = getTargetCollectionFilterValueMap(
		targetCollectionId
	);

	if (filterValueMap) {
		filterValueMap.set(filterFragmentEntryLinkId, value);
	}
	else if (process.env.NODE_ENV === 'development') {
		console.error(
			`Target collection "${targetCollectionId}" has not been registered`
		);
	}
};

export default function FragmentEntryLinkTargetCollectionsRegister({
	fragmentEntryLinkId: filterFragmentEntryLinkId,
	targetCollections: targetCollectionsString,
}) {
	if (!targetCollectionsString) {
		return;
	}

	try {
		const targetCollections = JSON.parse(targetCollectionsString);

		if (Array.isArray(targetCollections)) {
			targetCollections.forEach((targetCollectionId) => {
				targetCollectionFilterValues.set(
					targetCollectionId,
					targetCollectionFilterValues.get(targetCollectionId) ||
						new Map()
				);
			});

			filterTargetCollections.set(
				filterFragmentEntryLinkId,
				targetCollections
			);

			return () => {
				targetCollections.forEach((targetCollectionId) => {
					const filterValuesMap = targetCollectionFilterValues.get(
						targetCollectionId
					);

					if (filterValuesMap) {
						filterValuesMap.delete(filterFragmentEntryLinkId);

						if (filterValuesMap.size === 0) {
							targetCollectionFilterValues.delete(
								targetCollectionId
							);
						}
					}

					filterTargetCollections.delete(filterFragmentEntryLinkId);
				});
			};
		}
	}
	catch (error) {
		if (process.env.NODE_ENV) {
			console.error(
				`Cannot register targetCollections for fragmentEntryLink "${filterFragmentEntryLinkId}"`
			);
		}
	}
}
