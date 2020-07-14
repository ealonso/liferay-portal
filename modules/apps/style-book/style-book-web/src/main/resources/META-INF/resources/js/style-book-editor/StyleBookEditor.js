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

import {fetch, objectToFormData} from 'frontend-js-web';
import React, {useEffect, useState} from 'react';

import PagePreview from './PagePreview';
import Sidebar from './Sidebar';
import {StyleBookContextProvider} from './StyleBookContext';

const TOKEN_CATEGORIES = [
	{
		name: 'category1',
		tokenSets: [
			{name: 'General', tokens: [{name: 'portlet-topper-bg'}]},
			{name: 'Colors', tokens: [{name: 'primary'}, {name: 'blue'}]},
		],
	},
	{
		name: 'category2',
		tokenSets: [
			{name: 'Custom', tokens: [{name: 'fontSize'}]},
			{name: 'Colors', tokens: [{name: 'secondary'}, {name: 'blue'}]},
		],
	},
];

const StyleBookEditor = ({
	namespace,
	previewURL,
	publishURL,
	styleBookEntryId,
	saveDraftURL,
	tokenCategories = [],
	tokenValues: initialTokenValues = {},
} = {}) => {
	const [tokenValues, setTokenValues] = useState(initialTokenValues);

	useEffect(() => {
		if (tokenValues === initialTokenValues) {
			return;
		}

		const body = objectToFormData({
			[`${namespace}tokenValues`]: JSON.stringify(tokenValues),
			[`${namespace}styleBookEntryId`]: styleBookEntryId,
		});

		fetch(saveDraftURL, {body, method: 'post'});
	}, [
		initialTokenValues,
		namespace,
		saveDraftURL,
		styleBookEntryId,
		tokenValues,
	]);

	return (
		<StyleBookContextProvider
			value={{
				namespace,
				previewURL,
				publishURL,
				saveDraftURL,
				setTokenValues,
				styleBookEntryId,
				tokenCategories: TOKEN_CATEGORIES,
				tokenValues,
			}}
		>
			<div className="style-book-editor">
				<PagePreview />
				<Sidebar />
			</div>
		</StyleBookContextProvider>
	);
};

export default StyleBookEditor;
