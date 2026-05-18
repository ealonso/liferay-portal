/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {loginAnalyticsCloudTest} from '../../../fixtures/loginAnalyticsCloudTest';
import {loginTest} from '../../../fixtures/loginTest';
import {clickAndExpectToBeVisible} from '../../../utils/clickAndExpectToBeVisible';
import getRandomString from '../../../utils/getRandomString';
import {faroConfig} from './faro.config';
import {ACPage, navigateToACSettingsViaURL} from './utils/navigation';
import {signInAC, signOutAC} from './utils/portal';
import {
	createProperty,
	deleteProperty,
	switchProperty,
} from './utils/properties';

const test = mergeTests(apiHelpersTest, loginAnalyticsCloudTest(), loginTest());

test(
	'Property menu reflects creation, persists across relogin, and reflects deletion',
	{tag: ['@LRAC-8972', '@LRAC-8974']},
	async ({apiHelpers, page}) => {
		const projects = await apiHelpers.jsonWebServicesOSBFaro.getProjects();

		const project = projects.find(({name}) => name === 'FARO-DEV-liferay');

		const propertyName = `Test Property ${getRandomString()}`;

		// Create a new property

		await navigateToACSettingsViaURL({
			acPage: ACPage.propertiesPage,
			page,
			projectID: project.groupId,
		});

		await createProperty({page, propertyName});

		// Switch to the new property via the sidebar menu

		await page.goto(
			`${faroConfig.environment.baseUrl}/workspace/${project.groupId}`
		);

		await switchProperty({page, propertyName});

		// Sign out and back in, assert the last selected property persists

		await signOutAC(page);

		await signInAC(page);

		await page.goto(
			`${faroConfig.environment.baseUrl}/workspace/${project.groupId}`
		);

		await expect(
			page.locator('button.channels-menu .channels-menu-label')
		).toHaveText(propertyName);

		// Delete the property and assert it is removed from the sidebar menu

		await navigateToACSettingsViaURL({
			acPage: ACPage.propertiesPage,
			page,
			projectID: project.groupId,
		});

		await deleteProperty({page, propertyName});

		await page.goto(
			`${faroConfig.environment.baseUrl}/workspace/${project.groupId}`
		);

		await page.locator('button.channels-menu').click();

		await expect(
			page
				.locator('.channels-menu-dropdown-body')
				.getByRole('link', {name: propertyName})
		).not.toBeVisible();
	}
);

test(
	'Property menu search shows no rows for a non-existent name',
	{tag: '@LRAC-9230'},
	async ({page}) => {
		await clickAndExpectToBeVisible({
			target: page.getByPlaceholder('search'),
			trigger: page.locator('button.channels-menu'),
		});

		await page.getByPlaceholder('search').fill('Non Existent Property');

		await expect(
			page.locator('.channels-menu-dropdown-body .sites-dropdown-item')
		).toHaveCount(0);
	}
);
