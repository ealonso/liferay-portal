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

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayForm, {ClayCheckbox} from '@clayui/form';
import React, {useState} from 'react';

import {CategoryTreeNodeSelectorField} from '../../../../../../app/components/fragment-configuration-fields/CategoryTreeNodeSelectorField';
import {CheckboxField} from '../../../../../../app/components/fragment-configuration-fields/CheckboxField';
import {LAYOUT_DATA_ITEM_TYPES} from '../../../../../../app/config/constants/layoutDataItemTypes';
import {useHoverItem} from '../../../../../../app/contexts/ControlsContext';
import {
	useDispatch,
	useSelector,
	useSelectorCallback,
} from '../../../../../../app/contexts/StoreContext';
import selectLanguageId from '../../../../../../app/selectors/selectLanguageId';
import selectSegmentsExperienceId from '../../../../../../app/selectors/selectSegmentsExperienceId';
import updateItemConfig from '../../../../../../app/thunks/updateItemConfig';
import {useId} from '../../../../../../app/utils/useId';
import {FieldSet} from './FieldSet';

function TargetCollectionsField({onValueSelect, value}) {
	const [active, setActive] = useState(false);
	const inputId = useId();
	const [nextValue, setNextValue] = useState(value || []);
	const hoverItem = useHoverItem();

	const inputValue = useSelectorCallback(
		(state) => {
			if (nextValue.length === 0) {
				return '';
			}
			else if (nextValue.length === 1) {
				return state.layoutData.items[nextValue[0]]?.config?.collection
					?.title;
			}

			return Liferay.Language.get('multiple');
		},
		[nextValue]
	);

	const handleChange = (layoutItemId, checked) => {
		const included = nextValue.includes(layoutItemId);
		let selectedItems = nextValue;

		if (checked && !included) {
			selectedItems = [...nextValue, layoutItemId];

			setNextValue(selectedItems);
			onValueSelect('targetCollections', selectedItems);
		}
		else if (included) {
			selectedItems = nextValue.filter(
				(itemId) => itemId !== layoutItemId
			);

			setNextValue(selectedItems);
			onValueSelect('targetCollections', selectedItems);
		}
	};

	const items = useSelectorCallback(
		(state) =>
			Object.values(state.layoutData.items)
				.filter(
					(item) =>
						item.type === LAYOUT_DATA_ITEM_TYPES.collection &&
						item.config?.collection?.key
				)
				.map((item) => ({
					checked: nextValue.includes(item.itemId),
					label: item.config.collection.title,
					onChange: (checked) => handleChange(item.itemId, checked),
					type: 'checkbox',
					value: item.itemId,
				})),
		[nextValue]
	);

	return (
		<ClayForm.Group className="mt-1">
			<label htmlFor={inputId}>
				{Liferay.Language.get('target-collection')}
			</label>

			<ClayDropDown
				active={active}
				id={inputId}
				onActiveChange={setActive}
				trigger={
					<ClayButton
						aria-label={Liferay.Language.get('select')}
						className="bg-light font-weight-normal form-control-select text-left w-100"
						displayType="secondary"
						small
					>
						{inputValue ? (
							<span className="text-dark">{inputValue}</span>
						) : (
							Liferay.Language.get('select')
						)}
					</ClayButton>
				}
			>
				{items.map((item) => (
					<label
						className="d-flex dropdown-item"
						key={item.value}
						onMouseLeave={() => hoverItem(null)}
						onMouseOver={() => hoverItem(item.value)}
					>
						<ClayCheckbox
							checked={item.checked}
							onChange={item.onChange}
						/>
						<span className="font-weight-normal ml-2">
							{item.label}
						</span>
					</label>
				))}
			</ClayDropDown>
		</ClayForm.Group>
	);
}

export default function CollectionFilterGeneralPanel({item}) {
	const dispatch = useDispatch();
	const languageId = useSelector(selectLanguageId);
	const segmentsExperienceId = useSelector(selectSegmentsExperienceId);

	const onValueSelect = (name, value) => {
		let nextValue = value;

		if (name === 'label') {
			nextValue = {
				...(item.config.label || {}),
				[languageId]: nextValue,
			};
		}

		dispatch(
			updateItemConfig({
				itemConfig: {...item.config, [name]: nextValue},
				itemId: item.itemId,
				segmentsExperienceId,
			})
		);
	};

	return (
		<>
			<TargetCollectionsField
				onValueSelect={onValueSelect}
				value={item.config.targetCollections}
			/>

			<CategoryTreeNodeSelectorField
				field={{
					label: Liferay.Language.get('source'),
					name: 'source',
					type: 'categoryTreeNodeSelector',
				}}
				onValueSelect={onValueSelect}
				value={item.config.source}
			/>

			<CheckboxField
				field={{
					label: Liferay.Language.get('single-selection'),
					name: 'singleSelection',
					type: 'checkbox',
				}}
				onValueSelect={onValueSelect}
				value={item.config.singleSelection}
			/>

			<CheckboxField
				field={{
					label: Liferay.Language.get('include-search-field'),
					name: 'includeSearchField',
					type: 'checkbox',
				}}
				onValueSelect={onValueSelect}
				value={item.config.includeSearchField}
			/>

			<CheckboxField
				field={{
					label: Liferay.Language.get('show-label'),
					name: 'showLabel',
					type: 'checkbox',
				}}
				onValueSelect={onValueSelect}
				value={item.config.showLabel}
			/>

			<FieldSet
				fields={[
					{
						label: Liferay.Language.get('label'),
						localizable: true,
						name: 'label',
						type: 'text',
						typeOptions: {
							placeholder:
								item.config.source?.title ||
								Liferay.Language.get('label'),
						},
					},
				]}
				languageId={languageId}
				onValueSelect={onValueSelect}
				values={item.config}
			/>
		</>
	);
}
