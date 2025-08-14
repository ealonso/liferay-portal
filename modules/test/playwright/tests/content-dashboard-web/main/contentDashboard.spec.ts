/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {isolatedSiteTest} from '../../../fixtures/isolatedSiteTest';
import {loginTest} from '../../../fixtures/loginTest';
import {contentDashboardPagesTest} from './fixtures/contentDashboardPagesTest';
import getBasicWebContentStructureId from "../../../utils/structured-content/getBasicWebContentStructureId";
import getRandomString from "../../../utils/getRandomString";
import {
	clickAndExpectToBeVisible
} from "../../../utils/clickAndExpectToBeVisible";

export const test = mergeTests(
	apiHelpersTest,
	contentDashboardPagesTest,
	isolatedSiteTest,
	loginTest()
);

test('Validate if the user can open de Info side panel of a web content',
	{
		tag: '@LPS-114912',
	},
	async ({
		apiHelpers,
		contentDashboardPage,
		page,
		site,
	}) => {
		await contentDashboardPage.goto(site.friendlyUrlPath);

		const basicWebContentTitle = getRandomString();

		await apiHelpers.headlessDelivery.postStructuredContent({
			contentStructureId: await getBasicWebContentStructureId(apiHelpers),
			datePublished: null,
			siteId: site.id,
			title: basicWebContentTitle,
		});

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'Info'}),
			trigger: page
				.locator(`//p[@title="${basicWebContentTitle}"]/../..`)
				.getByLabel('More actions'),
		});

		await expect(
			page.getByRole('heading', {name: basicWebContentTitle})
		).toBeVisible();
});
