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

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayDropDownWithItems} from '@clayui/drop-down';
import ClayForm, {ClayInput} from '@clayui/form';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import useLoad from '../../../core/hooks/useLoad';
import {ConfigurationFieldPropTypes} from '../../../prop-types/index';
import CollectionService from '../../services/CollectionService';
import {useId} from '../../utils/useId';

export const CollectionFilterSelectorField = ({
	field,
	onValueSelect,
	value,
}) => {
	const [collectionFilter, setCollectionFilter] = useState(null);
	const [collectionFiltersItems, setCollectionFiltersItems] = useState([]);
	const inputId = useId();
	const load = useLoad();

	useEffect(() => {
		CollectionService.getCollectionFilters()
			.then((collectionFilters) =>
				collectionFilters.map(({label, modulePath}) => ({
					label,
					onClick: () => {
						load(modulePath, modulePath).then((collectionFilter) => {
							setCollectionFilter(collectionFilter);

							collectionFilter.handleSelect();
						});
					},
				}))
			)
			.then(setCollectionFiltersItems);
	}, [load]);

	return (
		<ClayForm.Group small>
			<label htmlFor={inputId}>{Liferay.Language.get('source')}</label>
			<ClayInput.Group>
				<ClayInput.GroupItem>
					<ClayInput id={inputId} readOnly />
				</ClayInput.GroupItem>
				<ClayInput.GroupItem shrink>
					<ClayDropDownWithItems
						items={collectionFiltersItems}
						trigger={
							<ClayButtonWithIcon
								aria-label={Liferay.Language.get(
									'open-filter-selection-dropdown'
								)}
								displayType="secondary"
								small
								symbol="ellipsis-v"
							/>
						}
					/>
				</ClayInput.GroupItem>
			</ClayInput.Group>
		</ClayForm.Group>
	);
};

CollectionFilterSelectorField.propTypes = {
	field: PropTypes.shape(ConfigurationFieldPropTypes).isRequired,
	onValueSelect: PropTypes.func.isRequired,
	value: PropTypes.oneOfType([PropTypes.string, PropTypes.object]),
};
