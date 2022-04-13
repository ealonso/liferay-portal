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

import ClayLoadingIndicator from '@clayui/loading-indicator';
import React, {useEffect, useMemo, useState} from 'react';

import {EDITABLE_TYPES} from '../../../../../../app/config/constants/editableTypes';
import {FORM_FRAGMENT_KEY} from '../../../../../../app/config/constants/formFragmentKey';
import {FREEMARKER_FRAGMENT_ENTRY_PROCESSOR} from '../../../../../../app/config/constants/freemarkerFragmentEntryProcessor';
import {LAYOUT_DATA_ITEM_TYPES} from '../../../../../../app/config/constants/layoutDataItemTypes';
import {config} from '../../../../../../app/config/index';
import {
	useDispatch,
	useSelector,
} from '../../../../../../app/contexts/StoreContext';
import selectSegmentsExperienceId from '../../../../../../app/selectors/selectSegmentsExperienceId';
import InfoItemService from '../../../../../../app/services/InfoItemService';
import updateEditableValues from '../../../../../../app/thunks/updateEditableValues';
import {setIn} from '../../../../../../app/utils/setIn';
import Collapse from '../../../../../../common/components/Collapse';
import MappingFieldSelector from '../../../../../../common/components/MappingFieldSelector';

const FIELD_ID_CONFIGURATION_KEY = 'inputFieldId';

export function FormInputGeneralPanel({item}) {
	const dispatch = useDispatch();
	const [fields, setFields] = useState(null);
	const fragmentEntryLinks = useSelector((state) => state.fragmentEntryLinks);
	const layoutData = useSelector((state) => state.layoutData);
	const segmentsExperienceId = useSelector(selectSegmentsExperienceId);

	const layoutDataItem = layoutData.items[item.itemId];

	const fragmentEntryLink =
		fragmentEntryLinks[layoutDataItem.config.fragmentEntryLinkId];

	const formConfiguration = useMemo(() => {
		if (!layoutDataItem) {
			return null;
		}

		const findFormConfiguration = (childItem) => {
			const parentItem = layoutData.items[childItem?.parentId];

			if (!parentItem) {
				return null;
			}

			const fragmentEntryLink =
				parentItem.type === LAYOUT_DATA_ITEM_TYPES.fragment
					? fragmentEntryLinks[parentItem.config?.fragmentEntryLinkId]
					: null;

			return fragmentEntryLink?.fragmentEntryKey === FORM_FRAGMENT_KEY
				? fragmentEntryLink.editableValues[
						FREEMARKER_FRAGMENT_ENTRY_PROCESSOR
				  ]
				: findFormConfiguration(parentItem);
		};

		return findFormConfiguration(layoutDataItem);
	}, [fragmentEntryLinks, layoutData, layoutDataItem]);

	const handleValueSelect = (event) =>
		dispatch(
			updateEditableValues({
				editableValues: setIn(
					fragmentEntryLink.editableValues,
					[
						FREEMARKER_FRAGMENT_ENTRY_PROCESSOR,
						FIELD_ID_CONFIGURATION_KEY,
					],
					event.target.value
				),
				fragmentEntryLinkId: fragmentEntryLink.fragmentEntryLinkId,
				languageId: config.defaultLanguageId,
				segmentsExperienceId,
			})
		);

	useEffect(() => {
		const {classNameId, classTypeId} = formConfiguration || {};

		if (!classNameId || !classTypeId) {
			return;
		}

		InfoItemService.getAvailableStructureMappingFields({
			classNameId,
			classTypeId,
			onNetworkStatus: () => {},
		}).then(setFields);
	}, [formConfiguration]);

	if (!formConfiguration) {
		return (
			<p className="alert alert-info text-center" role="alert">
				{Liferay.Language.get(
					'you-need-to-put-inputs-inside-a-form-item'
				)}
			</p>
		);
	}

	if (!formConfiguration.classNameId || !formConfiguration.classTypeId) {
		return (
			<p className="alert alert-info text-center" role="alert">
				{Liferay.Language.get(
					'you-need-to-select-a-form-item-type-first'
				)}
			</p>
		);
	}

	return (
		<Collapse label="form-input-config" open>
			{fields ? (
				<MappingFieldSelector
					fieldType={EDITABLE_TYPES.text}
					fields={fields}
					onValueSelect={handleValueSelect}
					value={
						fragmentEntryLink.editableValues[
							FREEMARKER_FRAGMENT_ENTRY_PROCESSOR
						][FIELD_ID_CONFIGURATION_KEY] || ''
					}
				/>
			) : (
				<ClayLoadingIndicator />
			)}
		</Collapse>
	);
}
