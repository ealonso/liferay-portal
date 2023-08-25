/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import React, {useState} from 'react';
import {ClayButtonWithIcon} from '@clayui/button';
import classNames from 'classnames';

const SearchBar = () => {
	const [query, setQuery] = useState('');

	const onSubmit = (event) => {
		event.preventDefault();

		window.Liferay.fire('search-term-submit', {
			term: query,
		});
	}

	const updateQuery = (query) => {
		setQuery(query);

		window.Liferay.fire('search-term-update', {
			term: query,
		});
	}

	return (
		<form className="commerce-search" onSubmit={onSubmit}>
			<div className="commerce-search__input">
				<input
					autoComplete="off"
					data-onkeydown="_handleKeyDown"
					data-onkeyup="_handleKeyUp"
					onKeyDown={(event) => {
						if (event.key === 'ArrowDown' || event.key === 'ArrowUp') {
							event.preventDefault();
						}
					}}
					onKeyUp={(event) => {updateQuery(event.target.value);}}
					name="queryString"
					placeholder={Liferay.Language.get('search')}
					type="text"
					value={query}
				/>
			</div>

			<ClayButtonWithIcon
				aria-label={Liferay.Language.get('clear')}
				className={classNames(
					'commerce-search__button',
					{
						'is-ninja': query === '',
					}
				)}
				onClick={() => {updateQuery('')}}
				symbol="times"
			/>
		</form>
	);
}

export default SearchBar;