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

import React, {useMemo} from 'react';

import 'product-navigation-control-menu/css/AddPanel.scss';

import TabsPanel from './TabsPanel';

const LAYOUT_DATA_ITEM_TYPES = {
	fragment: 'fragment',
};
const CONTENT = [
	{
		icon: 'page-template',
		itemId: '1',
		title: 'Cheesecake Recipe',
		type: 'Recipe Article',
	},
	{
		icon: 'page-template',
		itemId: '2',
		title: 'Chez Marco Food',
		type: 'Recipe Article',
	},
	{
		icon: 'page-template',
		itemId: '3',
		title: 'Content Example Orange',
		type: 'Blog Entry',
	},
	{
		icon: 'page-template',
		itemId: '4',
		title: 'Content Example Template',
		type: 'Blog Entry',
	},
	{
		icon: 'page-template',
		itemId: '5',
		title: 'Content Example Template',
		type: 'Blog Entry',
	},
];

const AddPanel = ({widgets}) => {
	const tabs = useMemo(
		() => [
			{
				collections: widgets.map((collection) => ({
					children: collection.portlets.map(normalizeWidget),
					collectionId: collection.path,
					label: collection.title,
				})),
				label: Liferay.Language.get('widgets'),
			},
			{
				collections: [{title: '$Recent'}].map((collection) => ({
					children: CONTENT.map(normalizeContent),
					label: collection.title,
				})),
				label: Liferay.Language.get('content'),
			},
		],
		[widgets]
	);

	return (
		<div className="sidebar-content__panel">
			<TabsPanel tabs={tabs} />
		</div>
	);
};

const normalizeWidget = (widget) => {
	return {
		data: {
			instanceable: widget.instanceable,
			portletId: widget.portletId,
			used: widget.used,
		},
		disabled: !widget.instanceable && widget.used,
		icon: widget.instanceable ? 'cards2' : 'square-hole',
		itemId: widget.portletId,
		label: widget.title,
		preview: '',
		type: LAYOUT_DATA_ITEM_TYPES.fragment,
	};
};

const normalizeContent = (content) => {
	return {
		icon: content.icon,
		itemId: content.itemId,
		label: content.title,
		type: content.type,
	};
};

export default AddPanel;
