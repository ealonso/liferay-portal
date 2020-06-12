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
import ClayForm, {ClaySelectWithOption} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import React, {useState} from 'react';

import 'product-navigation-control-menu/css/ContentOptions.scss';
const OPTIONS = [
	{
		label: '4 $items',
		value: 4,
	},
	{
		label: '8 $items',
		value: 8,
	},
	{
		label: '10 $items',
		value: 10,
	},
	{
		label: '20 $items',
		value: 20,
	},
];

const TYPES = [
	'$Basic Document',
	'$Basic Web Content',
	'$Blog Entry',
	'$Bookmarks Entry',
];

const ContentOptions = ({grid, onChangeListMode, onChangeSelect}) => {
	const [active, setActive] = useState(false);

	return (
		<div className="sidebar-content__panel__content-options">
			<ClayForm.Group small>
				<ClaySelectWithOption
					aria-label="Select Label"
					className="btn-monospaced sidebar-content__panel__content-options-select"
					id="mySelectId"
					onChange={(event) => onChangeSelect(event.target.value)}
					options={OPTIONS}
					sizing="sm"
				/>
			</ClayForm.Group>
			<ClayButton
				className="btn-monospaced sidebar-content__panel__content-options-list"
				displayType="unstyled"
				onClick={() => onChangeListMode(!grid)}
				small
			>
				<ClayIcon symbol={grid ? 'list' : 'cards2'} />
				<span className="sr-only">{grid ? '$Grid' : '$List'}</span>
			</ClayButton>

			<ClayDropDown
				active={active}
				onActiveChange={setActive}
				trigger={
					<ClayButton
						className="btn-monospaced sidebar-content__panel__content-options-add"
						displayType="unstyled"
						small
					>
						<ClayIcon symbol="plus" />
						<span className="sr-only">$Add</span>
					</ClayButton>
				}
			>
				<ClayDropDown.ItemList>
					{TYPES.map((type) => (
						<ClayDropDown.Item
							key={type}
							onClick={() => setActive(false)}
						>
							{type}
						</ClayDropDown.Item>
					))}
				</ClayDropDown.ItemList>
			</ClayDropDown>
		</div>
	);
};

export default ContentOptions;
