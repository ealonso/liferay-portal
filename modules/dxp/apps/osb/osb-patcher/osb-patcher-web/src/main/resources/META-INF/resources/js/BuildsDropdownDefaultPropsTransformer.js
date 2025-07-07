/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openModal} from 'frontend-js-components-web';

const ACTIONS = {
	buildPatcherBuild(itemData) {
		submitForm(document.hrefFm, itemData.buildPatcherBuildURL);
	},
	openModal(itemData) {
		openModal({
			title: itemData.title,
			url: itemData.viewChildPatcherBuildsURL,
		});
	},
	smokeTestPatcherBuild(itemData) {
		submitForm(document.hrefFm, itemData.smokeTestPatcherBuildURL);
	},
	testPatcherBuild(itemData) {
		submitForm(document.hrefFm, itemData.testPatcherBuildURL);
	},
};

export default function propsTransformer({items, ...props}) {
	return {
		...props,
		items: items.map((item) => {
			return {
				...item,
				onClick(event) {
					const action = item.data?.action;

					if (action) {
						event.preventDefault();

						ACTIONS[action](item.data);
					}
				},
			};
		}),
	};
}
