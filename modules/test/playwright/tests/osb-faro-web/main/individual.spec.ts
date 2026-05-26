/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../../fixtures/featureFlagsTest';
import {isolatedSiteTest} from '../../../fixtures/isolatedSiteTest';
import {loginAnalyticsCloudTest} from '../../../fixtures/loginAnalyticsCloudTest';
import {loginTest} from '../../../fixtures/loginTest';
import getRandomString from '../../../utils/getRandomString';
import {syncAnalyticsCloud} from '../../analytics-settings-web/main/utils/analytics-settings';
import getFragmentDefinition from '../../layout-content-page-editor-web/main/utils/getFragmentDefinition';
import getPageDefinition from '../../layout-content-page-editor-web/main/utils/getPageDefinition';
import {
	addBreakdownByAttribute,
	viewBreakdownRechartsData,
} from './utils/distribution';
import {createIndividuals, generateIndividual} from './utils/individuals';
import {ACPage, navigateToACPageViaURL} from './utils/navigation';
import {changeTimeFilter} from './utils/time-filter';

export const test = mergeTests(
	apiHelpersTest,
	featureFlagsTest({
		'LPS-178052': {enabled: true},
	}),
	isolatedSiteTest,
	loginAnalyticsCloudTest(),
	loginTest()
);

const channelName = 'My Property ' + getRandomString();

let channel;
let project;

test.beforeEach(async ({apiHelpers, page, site}) => {
	await apiHelpers.headlessDelivery.createSitePage({
		pageDefinition: getPageDefinition([
			getFragmentDefinition({
				id: getRandomString(),
				key: 'BASIC_COMPONENT-heading',
			}),
		]),
		siteId: site.id,
		title: 'My Page',
	});

	const result = await syncAnalyticsCloud({
		apiHelpers,
		channelName,
		page,
		siteName: site.name,
	});

	channel = result.channel;
	project = result.project;
});

test.afterEach(async ({apiHelpers}) => {
	await test.step('Delete channel', async () => {
		await apiHelpers.jsonWebServicesOSBFaro.deleteChannel(
			`[${channel.id}]`,
			project.groupId
		);
	});
});

test(
	'Add a new breakdown by an attribute and assert that correct results appear',
	{
		tag: '@Legacy',
	},
	async ({apiHelpers, page}) => {
		const individualName = 'ac';
		const individuals = [
			generateIndividual({
				name: individualName,
			}),
		];

		await test.step('Create new Individual', async () => {
			await createIndividuals({
				apiHelpers,
				individuals,
			});
		});

		const date = new Date();
		await test.step('Create Individual Event', async () => {
			const events = individuals.map((individual) => ({
				applicationId: 'Page',
				canonicalUrl: 'https://www.liferay.com',
				channelId: channel.id,
				eventDate: date.toISOString(),
				eventId: 'pageViewed',
				title: 'Liferay',
				userId: individual.id,
			}));

			await apiHelpers.jsonWebServicesOSBAsah.createEvents(events);
		});

		await test.step('Create Individual Session', async () => {
			const sessions = individuals.map((individual) => ({
				channelId: channel.id,
				id: individual.id,
				sessionEnd: date.toISOString(),
				sessionStart: date.toISOString(),
				userId: individual.id,
			}));

			await apiHelpers.jsonWebServicesOSBAsah.createSessions(sessions);
		});

		await test.step('Go to Individuals Dashboard', async () => {
			await navigateToACPageViaURL({
				acPage: ACPage.individualPage,
				channelID: channel.id,
				page,
				projectID: project.groupId,
			});
		});

		await test.step('Add a new breakdown', async () => {
			await addBreakdownByAttribute({
				attributeName: 'email',
				page,
			});
		});

		await test.step('Check if the correct results appear (email and maximum count)', async () => {
			await viewBreakdownRechartsData({
				attributeValue: `${individualName}@liferay.com`,
				maxCount: '1',
				page,
			});
		});

		await test.step('Close breakdown tab', async () => {
			await page.getByLabel('Close').click();
		});
	}
);

test(
	'Distribution page can be filtered by a specific string',
	{
		tag: '@Legacy',
	},
	async ({apiHelpers, page}) => {
		const individualName = 'ac';
		const individuals = [
			generateIndividual({
				name: individualName,
			}),
		];

		await test.step('Create new Individual', async () => {
			await createIndividuals({
				apiHelpers,
				individuals,
			});
		});

		const date = new Date();
		await test.step('Create Individual Event', async () => {
			const events = individuals.map((individual) => ({
				applicationId: 'Page',
				canonicalUrl: 'https://www.liferay.com',
				channelId: channel.id,
				eventDate: date.toISOString(),
				eventId: 'pageViewed',
				title: 'Liferay',
				userId: individual.id,
			}));

			await apiHelpers.jsonWebServicesOSBAsah.createEvents(events);
		});

		await test.step('Create Individual Session', async () => {
			const sessions = individuals.map((individual) => ({
				channelId: channel.id,
				id: individual.id,
				sessionEnd: date.toISOString(),
				sessionStart: date.toISOString(),
				userId: individual.id,
			}));

			await apiHelpers.jsonWebServicesOSBAsah.createSessions(sessions);
		});

		await test.step('Go to Individuals Dashboard', async () => {
			await navigateToACPageViaURL({
				acPage: ACPage.individualPage,
				channelID: channel.id,
				page,
				projectID: project.groupId,
			});
		});

		await test.step('Go to Distribution tab', async () => {
			await page.getByRole('link', {name: 'Distribution'}).click();

			await expect(
				page.getByText('Distribution by attribute')
			).toBeVisible();
		});

		await test.step('Add a new breakdown', async () => {
			await page.locator('.selected-item-container').click();

			await page.getByRole('menuitem', {name: 'email'}).click();
		});

		await test.step('Check if the correct results appear (email and maximum count)', async () => {
			await expect(
				page.getByText(`${individualName}@liferay.com - 100.0%`)
			).toBeVisible();
		});
	}
);

test(
	'Enriched Profiles count increases by one when an anonymous individual is later created as known',
	{
		tag: '@LRAC-8911',
	},
	async ({apiHelpers, page}) => {
		const individualId = getRandomString();
		const individualName = 'enriched' + getRandomString();

		const date = new Date();

		await test.step('Create an anonymous identity and page event', async () => {
			await apiHelpers.jsonWebServicesOSBAsah.createIdentities([
				{createDate: date.toISOString(), id: individualId},
			]);

			await apiHelpers.jsonWebServicesOSBAsah.createEvents([
				{
					applicationId: 'Page',
					canonicalUrl: 'https://www.liferay.com',
					channelId: channel.id,
					eventDate: date.toISOString(),
					eventId: 'pageViewed',
					title: 'My Page',
					userId: individualId,
				},
			]);

			await apiHelpers.jsonWebServicesOSBAsah.createSessions([
				{
					channelId: channel.id,
					id: individualId,
					sessionEnd: date.toISOString(),
					sessionStart: date.toISOString(),
					userId: individualId,
				},
			]);
		});

		await navigateToACPageViaURL({
			acPage: ACPage.individualPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await test.step('Verify Enriched Profiles count is zero before enrichment', async () => {
			await expect(
				page.locator('.enriched-profiles-card-root')
			).toContainText('0 Profiles');
		});

		await test.step('Create a known individual record for the anonymous identity', async () => {
			await createIndividuals({
				apiHelpers,
				individuals: [{id: individualId, name: individualName}],
			});

			await apiHelpers.jsonWebServicesOSBAsah.createEvents([
				{
					applicationId: 'Page',
					canonicalUrl: 'https://www.liferay.com',
					channelId: channel.id,
					eventDate: new Date().toISOString(),
					eventId: 'pageViewed',
					title: 'My Page',
					userId: individualId,
				},
			]);
		});

		await page.reload();

		await test.step('Verify Enriched Profiles count became one after enrichment', async () => {
			await expect(
				page.locator('.enriched-profiles-card-root')
			).toContainText('1 Profiles');
		});
	}
);

test(
	'Individual events card shows the empty state when no events match the active time filter',
	{
		tag: '@LRAC-10513',
	},
	async ({apiHelpers, page}) => {
		const individualName = 'empty' + getRandomString();

		const individuals = [generateIndividual({name: individualName})];

		await createIndividuals({apiHelpers, individuals});

		// Create an old event outside the Last 24 hours window so the user appears in the list but the events card is empty under that filter

		const oldDate = new Date();

		oldDate.setDate(oldDate.getDate() - 10);

		await apiHelpers.jsonWebServicesOSBAsah.createEvents([
			{
				applicationId: 'Page',
				canonicalUrl: 'https://www.liferay.com',
				channelId: channel.id,
				eventDate: oldDate.toISOString(),
				eventId: 'pageViewed',
				title: 'My Page',
				userId: individuals[0].id,
			},
		]);

		await apiHelpers.jsonWebServicesOSBAsah.createSessions([
			{
				channelId: channel.id,
				id: individuals[0].id,
				sessionEnd: oldDate.toISOString(),
				sessionStart: oldDate.toISOString(),
				userId: individuals[0].id,
			},
		]);

		await navigateToACPageViaURL({
			acPage: ACPage.individualPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await page.getByRole('link', {name: 'Known Individuals'}).click();

		await changeTimeFilter({page, timeFilterPeriod: 'Last 30 days'});

		await page.getByPlaceholder('Search').first().fill(individualName);

		await page
			.getByRole('link', {name: new RegExp(individualName, 'i')})
			.first()
			.click();

		// Switch the Individual Events card time filter to Last 24 hours to exclude the seeded event

		await page
			.locator('.individual-events-card-root, [id*="individualEvents"]')
			.first()
			.getByRole('button', {name: /Last/i})
			.first()
			.click();

		await page.getByRole('menuitem', {name: 'Last 24 hours'}).click();

		await expect(
			page.getByText('There are no events found.').first()
		).toBeVisible();
	}
);

test(
	'Individual activities feed shows only that individuals own events',
	{
		tag: '@LRAC-10509',
	},
	async ({apiHelpers, page}) => {
		const runId = getRandomString();

		const individualA = generateIndividual({name: 'userA' + runId});
		const individualB = generateIndividual({name: 'userB' + runId});

		await createIndividuals({
			apiHelpers,
			individuals: [individualA, individualB],
		});

		const customEventA = 'customEventA-' + runId;
		const customEventB = 'customEventB-' + runId;

		const date = new Date();

		await apiHelpers.jsonWebServicesOSBAsah.createEvents([
			{
				applicationId: 'Custom',
				canonicalUrl: 'https://www.liferay.com',
				channelId: channel.id,
				eventDate: date.toISOString(),
				eventId: customEventA,
				title: customEventA,
				userId: individualA.id,
			},
			{
				applicationId: 'Custom',
				canonicalUrl: 'https://www.liferay.com',
				channelId: channel.id,
				eventDate: date.toISOString(),
				eventId: customEventB,
				title: customEventB,
				userId: individualB.id,
			},
		]);

		await apiHelpers.jsonWebServicesOSBAsah.createSessions([
			{
				channelId: channel.id,
				id: individualA.id,
				sessionEnd: date.toISOString(),
				sessionStart: date.toISOString(),
				userId: individualA.id,
			},
			{
				channelId: channel.id,
				id: individualB.id,
				sessionEnd: date.toISOString(),
				sessionStart: date.toISOString(),
				userId: individualB.id,
			},
		]);

		await navigateToACPageViaURL({
			acPage: ACPage.individualPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await page.getByRole('link', {name: 'Known Individuals'}).click();

		await test.step('Open user A and assert only their custom event is listed', async () => {
			await page
				.getByPlaceholder('Search')
				.first()
				.fill(individualA.name);

			await page
				.getByRole('link', {name: new RegExp(individualA.name, 'i')})
				.first()
				.click();

			await expect(page.getByText(customEventA).first()).toBeVisible();

			await expect(page.getByText(customEventB)).toHaveCount(0);
		});

		await page.goBack();

		await test.step('Open user B and assert only their custom event is listed', async () => {
			await page
				.getByPlaceholder('Search')
				.first()
				.fill(individualB.name);

			await page
				.getByRole('link', {name: new RegExp(individualB.name, 'i')})
				.first()
				.click();

			await expect(page.getByText(customEventB).first()).toBeVisible();

			await expect(page.getByText(customEventA)).toHaveCount(0);
		});
	}
);
