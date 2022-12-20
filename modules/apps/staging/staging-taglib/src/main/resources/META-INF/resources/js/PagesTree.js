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
import {TreeView as ClayTreeView} from '@clayui/core';
import {ClayCheckbox} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import {fetch, openToast} from 'frontend-js-web';
import PropTypes from 'prop-types';
import React, {useCallback, useState} from 'react';

const ROOT_ITEM_ID = '0';

export default function PagesTree({
	config,
	groupId,
	items,
	portletNamespace: namespace,
	privateLayout,
	selectedPlids: initialSelectedPlids,
	treeId,
}) {
	const {changeItemSelectionURL, loadMoreItemsURL, maxPageSize} = config;

	const [selectedPlids, setSelectedPlids] = useState(initialSelectedPlids);

	const onLoadMore = useCallback(
		(item, initialCursor = 1) => {
			if (!item.hasChildren) {
				return Promise.resolve({
					cursor: null,
					items: null,
				});
			}

			const cursor = item.children ? initialCursor : 0;

			return fetch(loadMoreItemsURL, {
				body: Liferay.Util.objectToURLSearchParams({
					[`parentLayoutId`]: item.layoutId,
					[`privateLayout`]: privateLayout,
					[`selPlid`]: item.plid,
					[`start`]: cursor * maxPageSize,
				}),
				method: 'post',
			})
				.then((response) => response.json())
				.then(({hasMoreElements, items: nextItems}) => ({
					cursor: hasMoreElements ? cursor + 1 : null,
					items: nextItems,
				}))
				.catch(() => openErrorToast());
		},
		[loadMoreItemsURL, maxPageSize, privateLayout]
	);

	const onSelectedChange = useCallback(
		(selected, itemId) => {
			fetch(changeItemSelectionURL, {
				body: Liferay.Util.objectToFormData({
					cmd: selected ? 'layoutCheck' : 'layoutUncheck',
					doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
					groupId,
					plid: itemId,
					privateLayout,
					recursive: true,
					treeId: `${treeId}SelectedNode`,
				}),
				method: 'post',
			})
				.then((response) => response.json())
				.then((nextSelectedPlids) =>
					setSelectedPlids(nextSelectedPlids)
				)
				.catch(() => openErrorToast());
		},
		[changeItemSelectionURL, groupId, privateLayout, treeId]
	);

	return (
		<>
			<ClayTreeView
				defaultExpandedKeys={new Set([ROOT_ITEM_ID])}
				defaultItems={items}
				onLoadMore={onLoadMore}
				onSelectionChange={() => {}}
				selectedKeys={new Set(selectedPlids)}
				selectionMode="multiple-recursive"
				showExpanderOnHover={false}
			>
				{(item, selection, expand, load) => (
					<TreeItem
						expand={expand}
						item={item}
						load={load}
						onSelectedChange={onSelectedChange}
						selectedPlids={selectedPlids}
					/>
				)}
			</ClayTreeView>

			<input
				name={`${namespace}layoutIds`}
				readOnly
				type="hidden"
				value={JSON.stringify(selectedPlids)}
			/>
		</>
	);
}

PagesTree.propTypes = {
	config: PropTypes.object.isRequired,
	groupId: PropTypes.number.isRequired,
	isPrivateLayoutsTree: PropTypes.bool.isRequired,
	items: PropTypes.array.isRequired,
	portletNamespace: PropTypes.string.isRequired,
	selectedPlids: PropTypes.array.isRequired,
	treeId: PropTypes.string.isRequired,
};

function TreeItem({expand, item, load, onSelectedChange}) {
	return (
		<ClayTreeView.Item>
			<ClayTreeView.ItemStack>
				<ClayCheckbox
					containerProps={{className: 'mb-0'}}
					indeterminate
					onChange={(event) => {
						onSelectedChange(event.target.checked, item.id);
					}}
				/>

				{item.icon && <ClayIcon symbol={item.icon} />}

				{item.name}
			</ClayTreeView.ItemStack>

			<ClayTreeView.Group items={item.children}>
				{(childItem) => (
					<ClayTreeView.Item expandable={childItem.hasChildren}>
						<ClayCheckbox
							containerProps={{className: 'mb-0'}}
							indeterminate
							onChange={(event) =>
								onSelectedChange(
									event.target.checked,
									childItem.id
								)
							}
						/>

						{childItem.icon && <ClayIcon symbol={childItem.icon} />}

						{childItem.name}
					</ClayTreeView.Item>
				)}
			</ClayTreeView.Group>

			{load.get(item.id) !== null &&
				expand.has(item.id) &&
				item.paginated && (
					<ClayButton
						borderless
						className="ml-3"
						displayType="secondary"
						onClick={() => load.loadMore(item.id, item)}
					>
						{Liferay.Language.get('load-more-results')}
					</ClayButton>
				)}
		</ClayTreeView.Item>
	);
}

TreeItem.propTypes = {
	expand: PropTypes.object.isRequired,
	item: PropTypes.object.isRequired,
	load: PropTypes.object.isRequired,
};

function openErrorToast() {
	openToast({
		message: Liferay.Language.get('an-unexpected-error-occurred'),
		title: Liferay.Language.get('error'),
		type: 'danger',
	});
}
