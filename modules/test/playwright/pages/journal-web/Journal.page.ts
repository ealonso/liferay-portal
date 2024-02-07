/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page} from '@playwright/test';

import {ProductMenuPage} from '../product-navigation-product-menu/ProductMenu.page';

export class JournalPage {
	readonly newButton: Locator;
	readonly page: Page;
	readonly productMenuPage: ProductMenuPage;
	readonly templatesButton: Locator;

	constructor(page: Page) {
		this.page = page;
		this.productMenuPage = new ProductMenuPage(page);
		this.newButton = page.getByLabel('New', {exact: true});
		this.templatesButton = page.getByLabel('Templates');
	}

	async goto() {
		await this.productMenuPage.goToJournalMenuItem();
	}

	private async goToTemplates() {
		await this.goto();
		await this.templatesButton.click();
	}

	async goToCreateNewTemplate() {
		await this.goToTemplates();
		await this.page.getByLabel('New', {exact: true}).click();
	}

}
