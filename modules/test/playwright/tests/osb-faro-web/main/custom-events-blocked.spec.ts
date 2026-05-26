/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../../fixtures/featureFlagsTest';
import {isolatedChannelTest} from '../../../fixtures/isolatedChannelTest';
import {loginAnalyticsCloudTest} from '../../../fixtures/loginAnalyticsCloudTest';
import {loginTest} from '../../../fixtures/loginTest';
import getRandomString from '../../../utils/getRandomString';
import {ACPage, navigateToACSettingsViaURL} from './utils/navigation';

const test = mergeTests(
	apiHelpersTest,
	featureFlagsTest({
		'LPS-178052': {enabled: true},
	}),
	isolatedChannelTest,
	loginAnalyticsCloudTest(),
	loginTest()
);

async function seedCustomEvents({
	apiHelpers,
	channelId,
	count,
	prefix,
}: {
	apiHelpers;
	channelId: string;
	count: number;
	prefix: string;
}) {
	const eventNames = Array.from(
		{length: count},
		(_, index) => `${prefix}-${index + 1}`
	);

	const date = new Date();

	await apiHelpers.jsonWebServicesOSBAsah.createEventDefinition(
		eventNames.map((name) => ({
			applicationId: 'CustomEvent',
			displayName: name,
			name,
			type: 'CUSTOM',
		}))
	);

	await apiHelpers.jsonWebServicesOSBAsah.createEvents(
		eventNames.map((name) => ({
			applicationId: 'CustomEvent',
			canonicalUrl: 'https://www.liferay.com',
			channelId,
			eventDate: date.toISOString(),
			eventId: name,
			title: name,
			userId: '1',
		}))
	);

	return eventNames;
}

test(
	'Blocked custom events list keeps entries visible after sorting by name and last seen',
	{
		tag: '@LRAC-10360',
	},

	async ({analyticsChannel: channel, apiHelpers, page, project}) => {
		const prefix = 'blockSort' + getRandomString();

		const eventNames = await seedCustomEvents({
			apiHelpers,
			channelId: channel.id,
			count: 3,
			prefix,
		});

		await navigateToACSettingsViaURL({
			acPage: ACPage.definitionsEventsCustomPage,
			page,
			projectID: project.groupId,
		});

		for (const name of eventNames) {
			await expect(
				page.getByRole('cell', {exact: true, name})
			).toBeVisible();
		}

		// Select all custom events and block them

		await page.locator('input[type="checkbox"]').first().check();

		await page.getByRole('button', {exact: true, name: 'Block'}).click();

		await navigateToACSettingsViaURL({
			acPage: ACPage.definitionsEventsBlockListPage,
			page,
			projectID: project.groupId,
		});

		for (const sortColumn of ['Last Seen', 'Name']) {
			await page
				.getByRole('columnheader', {exact: true, name: sortColumn})
				.click();

			for (const name of eventNames) {
				await expect(
					page.getByRole('cell', {exact: true, name})
				).toBeVisible();
			}
		}
	}
);

test(
	'Custom events list surfaces the 100 event allow-list limit and the View Block List action',
	{
		tag: '@LRAC-10164',
	},

	async ({analyticsChannel: channel, apiHelpers, page, project}) => {
		test.setTimeout(180000);

		const prefix = 'limit' + getRandomString();

		await seedCustomEvents({
			apiHelpers,
			channelId: channel.id,
			count: 101,
			prefix,
		});

		await navigateToACSettingsViaURL({
			acPage: ACPage.definitionsEventsCustomPage,
			page,
			projectID: project.groupId,
		});

		await expect(
			page.getByText('Showing 1 to 20 of 100 entries.')
		).toBeVisible();

		await page.getByRole('button', {name: 'View Block List'}).click();

		await expect(
			page.getByRole('cell', {exact: true, name: `${prefix}-101`})
		).toBeVisible();
	}
);
