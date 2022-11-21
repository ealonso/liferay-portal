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

export default function PagesTree({
	config,
	isPrivateLayoutsTree,
	items,
	portletNamespace: namespace,
}) {
	const {loadMoreItemsURL, maxPageSize} = config;

	const [selectedKeys, setSelectionChange] = useState([]);

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
					[`${namespace}parentLayoutId`]: item.layoutId,
					[`${namespace}privateLayout`]: isPrivateLayoutsTree,
					[`${namespace}selPlid`]: item.plid,
					[`${namespace}start`]: cursor * maxPageSize,
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
		[isPrivateLayoutsTree, loadMoreItemsURL, maxPageSize, namespace]
	);

	return (
		<>
			<ClayTreeView
				defaultItems={items}
				dragAndDrop
				nestedKey="children"
				onLoadMore={onLoadMore}
				onSelectionChange={(keys) =>
					setSelectionChange(Array.from(keys))
				}
				selectedKeys={new Set(selectedKeys)}
				selectionMode="multiple-recursive"
				showExpanderOnHover={false}
			>
				{(item, selection, expand, load) => (
					<TreeItem expand={expand} item={item} load={load} />
				)}
			</ClayTreeView>

			<input
				name={`${namespace}selectedLayoutIds`}
				readOnly
				type="hidden"
				value={JSON.stringify(selectedKeys)}
			/>
		</>
	);
}

PagesTree.propTypes = {
	config: PropTypes.object.isRequired,
	isPrivateLayoutsTree: PropTypes.bool.isRequired,
	items: PropTypes.array.isRequired,
	portletNamespace: PropTypes.string.isRequired,
};

function TreeItem({expand, item, load}) {
	return (
		<ClayTreeView.Item>
			<ClayTreeView.ItemStack>
				<ClayCheckbox containerProps={{className: 'mb-0'}} />

				{item.icon && <ClayIcon symbol={item.icon} />}

				{item.name}
			</ClayTreeView.ItemStack>

			<ClayTreeView.Group items={item.children}>
				{(item) => (
					<ClayTreeView.Item>
						<ClayCheckbox />

						{item.icon && <ClayIcon symbol={item.icon} />}

						{item.name}
					</ClayTreeView.Item>
				)}
			</ClayTreeView.Group>

			{load.get(item.id) !== null &&
				expand.has(item.id) &&
				item.paginated && (
					<ClayButton
						borderless
						className="ml-3 text-light"
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
