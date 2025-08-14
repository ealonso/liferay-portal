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
import {featureFlagsTest} from "../../../fixtures/featureFlagsTest";
import {createReadStream} from "fs";
import path from "node:path";

export const test = mergeTests(
	apiHelpersTest,
	contentDashboardPagesTest,
	featureFlagsTest({
		'LPS-178052': {enabled: true},
	}),
	isolatedSiteTest,
	loginTest()
);

test('Given The Extension filter modal When the user selects one or more extensions Then the number of file extensions preselected will be shown as a text with the format [number] Subtypes selected',
	{
		tag: '@LPS-133354',
	},
	async ({
	   apiHelpers,
	   contentDashboardPage,
	   page,
	   site,
   }) => {

		const document1 = await apiHelpers.headlessDelivery.postDocument(
			site.id,
			createReadStream(
				path.join(__dirname, '/dependencies/attachment.docx')
			)
		);

		const document2 = await apiHelpers.headlessDelivery.postDocument(
			site.id,
			createReadStream(
				path.join(__dirname, '/dependencies/attachment.jpeg')
			)
		);

		const document3 = await apiHelpers.headlessDelivery.postDocument(
			site.id,
			createReadStream(
				path.join(__dirname, '/dependencies/attachment.txt')
			)
		);

		await contentDashboardPage.goto(site.friendlyUrlPath);

		await contentDashboardPage.openFilterDropdown();

		/*
		task ("Navigate to the content Dashboard and filter by file extension") {
			ContentDashboard.filterByExtension(extensionNameList = "mp3,Image,Text");
		}

		task ("Check if extension is present") {
			AssertTextEquals(
				locator1 = "ManagementBar#SEARCH_RESULT_SUMMARY",
				value1 = "10 Results Found With Filters");
		}
		 */
	}
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
		const basicWebContentTitle = getRandomString();

		await apiHelpers.headlessDelivery.postStructuredContent({
			contentStructureId: await getBasicWebContentStructureId(apiHelpers),
			datePublished: null,
			siteId: site.id,
			title: basicWebContentTitle,
		});

		await contentDashboardPage.goto(site.friendlyUrlPath);

		await clickAndExpectToBeVisible({
			autoClick: true,
			target: page.getByRole('menuitem', {name: 'Info'}),
			trigger: page
				.locator(`//p[@title="${basicWebContentTitle}"]/../..`)
				.getByLabel('More actions'),
		});

		const infoPanel = page.getByLabel('Info Panel', {exact: true});

		await expect(
			infoPanel.locator('.sidebar-header')
		).toContainText(basicWebContentTitle);
});
