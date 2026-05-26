/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Page, expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../../fixtures/apiHelpersTest';
import {customFieldsPagesTest} from '../../../fixtures/customFieldsPagesTest';
import {featureFlagsTest} from '../../../fixtures/featureFlagsTest';
import {isolatedSiteTest} from '../../../fixtures/isolatedSiteTest';
import {loginAnalyticsCloudTest} from '../../../fixtures/loginAnalyticsCloudTest';
import {loginTest} from '../../../fixtures/loginTest';
import {clickAndExpectToBeVisible} from '../../../utils/clickAndExpectToBeVisible';
import getRandomString from '../../../utils/getRandomString';
import {syncAnalyticsCloud} from '../../analytics-settings-web/main/utils/analytics-settings';
import {ACPage, navigateToACPageViaURL} from './utils/navigation';
import {
	addSegmentField,
	createBatchSegment,
	editCriteriaAttributeValue,
	saveSegment,
	selectOperator,
	setSegmentName,
} from './utils/segments';
import {SegmentConditions} from './utils/selectors';
import {viewNameOnTableList} from './utils/utils';

const test = mergeTests(
	apiHelpersTest,
	customFieldsPagesTest,
	featureFlagsTest({
		'LPS-178052': {enabled: true},
	}),
	isolatedSiteTest,
	loginAnalyticsCloudTest(),
	loginTest()
);

async function setUserCustomFieldValue({
	customFieldLabel,
	customFieldValue,
	page,
	userScreenName,
}: {
	customFieldLabel: string;
	customFieldValue: string;
	page: Page;
	userScreenName: string;
}) {
	await page.goto(
		'/group/control_panel/manage?p_p_id=com_liferay_users_admin_web_portlet_UsersAdminPortlet'
	);

	await page.getByPlaceholder('Search').first().fill(userScreenName);

	await page.getByPlaceholder('Search').first().press('Enter');

	await page
		.getByRole('link', {exact: false, name: userScreenName})
		.first()
		.click();

	await page.getByRole('link', {exact: true, name: 'Custom Fields'}).click();

	await page.getByLabel(customFieldLabel).fill(customFieldValue);

	await page.getByRole('button', {name: 'Save'}).click();

	await expect(
		page.getByText('Your request completed successfully')
	).toBeVisible();
}

async function syncCustomFieldAttributes({
	attributeNames,
	page,
}: {
	attributeNames: string[];
	page: Page;
}) {

	// Navigate to Analytics Cloud instance settings and reopen the wizard at the
	// Sync Attributes step so the custom field definitions can be pushed into AC.

	await page.goto(
		'/group/control_panel/manage?p_p_id=com_liferay_analytics_settings_web_internal_portlet_AnalyticsSettingsPortlet'
	);

	await page.getByRole('button', {name: 'Sync Attributes'}).click();

	await page.getByRole('button', {exact: true, name: 'People'}).click();

	for (const attributeName of attributeNames) {
		await page.getByPlaceholder('Search').fill(attributeName);

		await page
			.getByRole('row', {name: attributeName})
			.getByRole('checkbox')
			.check();
	}

	await page.getByRole('button', {exact: true, name: 'Sync'}).click();
}

async function setOrganizationCustomFieldValue({
	customFieldLabel,
	customFieldValue,
	organizationName,
	page,
}: {
	customFieldLabel: string;
	customFieldValue: string;
	organizationName: string;
	page: Page;
}) {
	await page.goto(
		'/group/control_panel/manage?p_p_id=com_liferay_users_admin_web_portlet_UsersAdminPortlet&_com_liferay_users_admin_web_portlet_UsersAdminPortlet_mvcRenderCommandName=%2Forganizations%2Fview'
	);

	await page.getByPlaceholder('Search').first().fill(organizationName);

	await page.getByPlaceholder('Search').first().press('Enter');

	await page.getByRole('link', {exact: true, name: organizationName}).click();

	await page.getByRole('link', {exact: true, name: 'Custom Fields'}).click();

	await page.getByLabel(customFieldLabel).fill(customFieldValue);

	await page.getByRole('button', {name: 'Save'}).click();

	await expect(
		page.getByText('Your request completed successfully')
	).toBeVisible();
}

test(
	'Batch segment can be created with an individual text custom field criterion',
	{
		tag: '@LRAC-8569',
	},
	async ({addCustomFieldPage, apiHelpers, page, site}) => {
		const runId = getRandomString();
		const customFieldName = `user custom field ${runId}`;

		const user = await apiHelpers.headlessAdminUser.postUserAccount();

		await addCustomFieldPage.addCustomField({
			fieldName: customFieldName,
			fieldType: 'inputField',
			fieldValues: {
				dataType: 'text',
			},
			resource: 'User',
		});

		await setUserCustomFieldValue({
			customFieldLabel: customFieldName,
			customFieldValue: 'VIP AC',
			page,
			userScreenName: user.alternateName,
		});

		const {channel, project} = await syncAnalyticsCloud({
			apiHelpers,
			channelName: `My Property - ${runId}`,
			page,
			siteName: site.name,
		});

		await syncCustomFieldAttributes({
			attributeNames: [customFieldName],
			page,
		});

		await navigateToACPageViaURL({
			acPage: ACPage.segmentPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await createBatchSegment(page);

		await setSegmentName({
			page,
			segmentName: `Dynamic Segment Custom Field ${runId}`,
		});

		await addSegmentField({
			criterionName: customFieldName,
			criterionType: 'Individual Attributes',
			page,
		});

		await selectOperator({
			operator: 'contains',
			operatorField: SegmentConditions.criteriaCondition,
			page,
		});

		await editCriteriaAttributeValue({attributeValue: 'VIP AC', page});

		// Open the in-editor View Members preview and assert the user appears

		await clickAndExpectToBeVisible({
			target: page.getByText('Known Segment Members'),
			trigger: page.getByTitle('View Members'),
		});

		await viewNameOnTableList({
			itemNames: `${user.givenName} ${user.familyName}`,
			page,
		});
	}
);

test(
	'Batch segment can be created with individual list-type custom fields (dropdown, checkbox, radio)',
	{
		tag: '@LRAC-12005',
	},
	async ({addCustomFieldPage, apiHelpers, page, site}) => {
		const runId = getRandomString();
		const dropdownField = `user custom field dropdown ${runId}`;
		const checkboxField = `user custom field checkbox ${runId}`;
		const radioField = `user custom field radio ${runId}`;

		const user = await apiHelpers.headlessAdminUser.postUserAccount();

		await addCustomFieldPage.addCustomField({
			fieldName: dropdownField,
			fieldType: 'dropdown',
			fieldValues: {
				values: 'Option1\nOption2\nOption3',
			},
			resource: 'User',
		});

		await addCustomFieldPage.addCustomField({
			fieldName: checkboxField,
			fieldType: 'checkbox',
			fieldValues: {
				values: 'QA\nDEV\nEPM',
			},
			resource: 'User',
		});

		await addCustomFieldPage.addCustomField({
			fieldName: radioField,
			fieldType: 'radio',
			fieldValues: {
				values: 'AC\nDXP\nLXC',
			},
			resource: 'User',
		});

		// Set the user's value for each new custom field via the user admin edit form

		await page.goto(
			'/group/control_panel/manage?p_p_id=com_liferay_users_admin_web_portlet_UsersAdminPortlet'
		);

		await page.getByPlaceholder('Search').first().fill(user.alternateName);

		await page.getByPlaceholder('Search').first().press('Enter');

		await page
			.getByRole('link', {exact: false, name: user.alternateName})
			.first()
			.click();

		await page
			.getByRole('link', {exact: true, name: 'Custom Fields'})
			.click();

		await page.getByLabel(dropdownField).selectOption('Option2');

		await page.getByLabel(checkboxField).check();

		await page.getByLabel(radioField).check();

		await page.getByRole('button', {name: 'Save'}).click();

		await expect(
			page.getByText('Your request completed successfully')
		).toBeVisible();

		const {channel, project} = await syncAnalyticsCloud({
			apiHelpers,
			channelName: `My Property - ${runId}`,
			page,
			siteName: site.name,
		});

		await syncCustomFieldAttributes({
			attributeNames: [dropdownField, checkboxField, radioField],
			page,
		});

		await navigateToACPageViaURL({
			acPage: ACPage.segmentPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await createBatchSegment(page);

		await setSegmentName({
			page,
			segmentName: `Dynamic Segment List Custom Fields ${runId}`,
		});

		// Add a criterion for each list-type custom field

		const fields = [
			{name: dropdownField, value: 'Option2'},
			{name: checkboxField, value: 'QA'},
			{name: radioField, value: 'LXC'},
		];

		for (const [index, field] of fields.entries()) {
			await addSegmentField({
				criterionName: field.name,
				criterionType: 'Individual Attributes',
				page,
			});

			await selectOperator({
				index,
				operator: 'contains',
				operatorField: SegmentConditions.criteriaCondition,
				page,
			});

			await page
				.getByPlaceholder('Select an option')
				.nth(index)
				.fill(field.value);

			await page.getByRole('option', {name: field.value}).click();
		}

		// Preview the segment and verify the user matches all three criteria

		await clickAndExpectToBeVisible({
			target: page.getByText('Known Segment Members'),
			trigger: page.getByTitle('View Members'),
		});

		await viewNameOnTableList({
			itemNames: `${user.givenName} ${user.familyName}`,
			page,
		});

		await page.locator('button.close').click();

		await saveSegment(page);
	}
);

test(
	'Batch segment can be created with an organization custom field criterion',
	{
		tag: '@LRAC-8570',
	},
	async ({addCustomFieldPage, apiHelpers, page, site}) => {
		const runId = getRandomString();
		const customFieldName = `org custom field ${runId}`;
		const customFieldValue = 'Hey AC Team';

		const user = await apiHelpers.headlessAdminUser.postUserAccount();

		const organization =
			await apiHelpers.headlessAdminUser.postOrganization({
				name: `Organization CF ${runId}`,
			});

		await apiHelpers.headlessAdminUser.assignUserToOrganizationByEmailAddress(
			organization.id,
			user.emailAddress
		);

		await addCustomFieldPage.addCustomField({
			fieldName: customFieldName,
			fieldType: 'inputField',
			fieldValues: {
				dataType: 'text',
				startingValue: customFieldValue,
			},
			resource: 'Organization',
		});

		await setOrganizationCustomFieldValue({
			customFieldLabel: customFieldName,
			customFieldValue,
			organizationName: organization.name,
			page,
		});

		const {channel, project} = await syncAnalyticsCloud({
			apiHelpers,
			channelName: `My Property - ${runId}`,
			organizationName: organization.name,
			page,
			siteName: site.name,
		});

		await syncCustomFieldAttributes({
			attributeNames: [customFieldName],
			page,
		});

		await navigateToACPageViaURL({
			acPage: ACPage.segmentPage,
			channelID: channel.id,
			page,
			projectID: project.groupId,
		});

		await createBatchSegment(page);

		await setSegmentName({
			page,
			segmentName: `Dynamic Segment Organization CF ${runId}`,
		});

		await addSegmentField({
			criterionName: customFieldName,
			criterionType: 'Organization Attributes',
			page,
		});

		await selectOperator({
			operator: 'is',
			operatorField: SegmentConditions.criteriaCondition,
			page,
		});

		await page.getByPlaceholder('Select an option').fill(customFieldValue);

		await page.getByRole('option', {name: customFieldValue}).click();

		// Preview the segment and verify the user matches the organization criterion

		await clickAndExpectToBeVisible({
			target: page.getByText('Known Segment Members'),
			trigger: page.getByTitle('View Members'),
		});

		await viewNameOnTableList({
			itemNames: `${user.givenName} ${user.familyName}`,
			page,
		});

		await page.locator('button.close').click();

		await saveSegment(page);
	}
);
