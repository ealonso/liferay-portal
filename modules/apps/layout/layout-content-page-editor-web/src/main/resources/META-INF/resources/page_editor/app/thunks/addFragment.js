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

import addFragmentEntryLinks from '../actions/addFragmentEntryLinks';
import {FRAGMENT_TYPES} from '../config/constants/fragmentTypes';
import FragmentService from '../services/FragmentService';
import LayoutService from '../services/LayoutService';

export default function addFragment({
	fragmentEntryKey,
	groupId,
	parentItemId,
	position,
	selectItem = () => {},
	store,
	type,
}) {
	return dispatch => {
		const {segmentsExperienceId} = store;

		const params = {
			fragmentEntryKey,
			groupId,
			onNetworkStatus: dispatch,
			parentItemId,
			position,
			segmentsExperienceId,
			type,
		};

		const updateState = (fragmentEntryLinks, layoutData, itemId) => {
			dispatch(
				addFragmentEntryLinks({
					fragmentEntryLinks,
					layoutData,
				})
			);

			selectItem(itemId);
		};

		if (type === FRAGMENT_TYPES.composition) {
			FragmentService.addFragmentEntryLinks(params).then(
				({fragmentEntryLinks, layoutData}) => {
					updateState(
						Object.values(fragmentEntryLinks),
						layoutData,
						parentItemId
					);
				}
			);
		}
		else {
			FragmentService.addFragmentEntryLink(params).then(
				({addedItemId, fragmentEntryLink, layoutData}) => {
					let l = layoutData;
					const f = {...fragmentEntryLink, dropZones: {}};
					const fe = document.createElement('div');
					fe.innerHTML = f.content;

					console.log(
						`add ${addedItemId} from fragment ${fragmentEntryKey}`
					);

					const ps = Array.from(
						fe.querySelectorAll('lfr-dropzone')
					).map(dropZoneElement => {
						return LayoutService.addItem({
							itemType: 'root',
							onNetworkStatus: dispatch,
							parentItemId: addedItemId,
							position: 0,
							segmentsExperienceId,
						}).then(({addedItemId, layoutData}) => {
							console.log(
								`add ${addedItemId} for dropZone ${dropZoneElement.getAttribute(
									'id'
								)}`
							);

							l = layoutData;
							f.dropZones[
								dropZoneElement.getAttribute('id')
							] = addedItemId;
						});
					});

					Promise.all(ps).then(() => {
						updateState([f], l, addedItemId);
					});
				}
			);
		}
	};
}
