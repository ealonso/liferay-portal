/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {isolatedSiteTest} from '../../../fixtures/isolatedSiteTest';
import {loginTest} from '../../../fixtures/loginTest';
import {collectionsPagesTest} from '../../../fixtures/collectionsPagesTest';
import getRandomString from "../../../utils/getRandomString";

const test = mergeTests(
	collectionsPagesTest,
	isolatedSiteTest,
	loginTest()
);

test(
	'Assert that the UI works correctly for adding, deleting and renaming Asset Lists',
	{
		tag: '@LPD-32724',
	},
	async ({
		collectionsPage,
		page,
		site,
	}) => {

		// Create a manual asset list

		await collectionsPage.goto(site.friendlyUrlPath);

		const manualCollectionName = getRandomString();

		await collectionsPage.addNewDynamicCollection(manualCollectionName);

		// Create a dynamic asset list

		await collectionsPage.goto(site.friendlyUrlPath);

		const dynamicCollectionName = getRandomString();

		await collectionsPage.addNewDynamicCollection(dynamicCollectionName);

		

		/*
		task ("When the site administrator hovers over the back button") {
			MouseOver(locator1 = "Icon#BACK");
		}

		task ("Then the site administrator could see the tooltip message") {
			AssertVisible(
				key_content = "Go to Collections",
				locator1 = "Tooltip#FLOATING_TOOLTIP_CONTENT");
		}

		task ("Assert Asset List Entry names and types") {
			AssetListsAdmin.openAssetListsAdmin(siteURLKey = "test-site-name");

			AssertTextEquals.assertPartialText(
				key_title = "Manual Asset List Test",
				locator1 = "AssetLists#ASSET_LIST_ENTRY_TITLE",
				value1 = "Manual Asset List Test");

			AssertTextEquals.assertPartialText(
				key_columnName = "lfr-type",
				key_tableEntry = "Manual",
				locator1 = "LexiconTable#TABLE_ENTRY_INFO",
				value1 = "Manual");

			AssertTextEquals.assertPartialText(
				key_title = "Dynamic Asset List Test",
				locator1 = "AssetLists#ASSET_LIST_ENTRY_TITLE",
				value1 = "Dynamic Asset List Test");

			AssertTextEquals.assertPartialText(
				key_columnName = "lfr-type",
				key_tableEntry = "Dynamic",
				locator1 = "LexiconTable#TABLE_ENTRY_INFO",
				value1 = "Dynamic");
		}

		task ("Create an asset list that has the same name as the first asset list") {
			LexiconEntry.gotoAddMenuItem(menuItem = "Manual Collection");

			PortletEntry.inputText(
				fieldLabel = "Title",
				text = "Manual Asset List Test");

			Button.clickSave();
		}

		task ("Assert an error displays notifying that the user is not allowed to create a duplicate asset list") {
			AssertTextEquals.assertPartialText(
				locator1 = "Message#ERROR_MODAL_CONTENT",
				value1 = "A collection with that title already exists.");
		}

		task ("Rename the asset list") {
			AssetListsAdmin.openAssetListsAdmin(siteURLKey = "test-site-name");

			AssetListsAdmin.renameAssetList(
				newAssetListTitle = "Manual Asset List Edit",
				oldAssetListTitle = "Manual Asset List Test");
		}

		task ("Assert that the name of the asset list changed correctly") {
			AssertTextEquals.assertPartialText(
				key_title = "Manual Asset List Edit",
				locator1 = "AssetLists#ASSET_LIST_ENTRY_TITLE",
				value1 = "Manual Asset List Edit");

			AssertTextEquals.assertPartialText(
				key_columnName = "lfr-type",
				key_tableEntry = "Manual",
				locator1 = "LexiconTable#TABLE_ENTRY_INFO",
				value1 = "Manual");
		}

		task ("Delete both asset lists") {
			AssetListsAdmin.deleteEntry(assetListTitle = "Manual Asset List Edit");

			AssetListsAdmin.deleteEntry(assetListTitle = "Dynamic Asset List Test");
		}

		task ("Assert the taglib that displays when there are no entries displays") {
			AssertElementPresent(locator1 = "Message#EMPTY_INFO_TAGLIB");
		}
		 */
	}
);
