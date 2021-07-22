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
import PropTypes from 'prop-types';
import React from 'react';

import {getLayoutDataItemPropTypes} from '../../../prop-types';
import {useSelector} from '../../contexts/StoreContext';
import selectLanguageId from '../../selectors/selectLanguageId';

const CollectionFilter = React.forwardRef(({item}, ref) => {
	const languageId = useSelector(selectLanguageId);

	const label =
		item.config.label?.[languageId] ||
		item.config.label?.defaultValue ||
		item.config.source?.title ||
		Liferay.Language.get('label');

	return (
		<div ref={ref}>
			{item.config.showLabel && label ? (
				<p className="font-weight-bold mb-1 mt-0 small">{label}</p>
			) : null}
			<ClayButton
				className="bg-light dropdown-toggle font-weight-bold form-control-select form-control-sm text-left w-100"
				displayType="secondary"
				small
			>
				{Liferay.Language.get('select')}
			</ClayButton>
		</div>
	);
});

CollectionFilter.displayName = 'CollectionFilter';

CollectionFilter.propTypes = {
	item: getLayoutDataItemPropTypes({
		config: PropTypes.shape({
			label: PropTypes.objectOf(PropTypes.string),
			showLabel: PropTypes.bool,
		}),
	}).isRequired,
};

export default CollectionFilter;
