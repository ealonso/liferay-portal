/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Page} from '@playwright/test';

import {JournalPage} from './Journal.page';

export class JournalEditTemplatePage {
	readonly page: Page;

	journalPage: JournalPage;

	constructor(page: Page) {
		this.journalPage = new JournalPage(page);
		this.page = page;
	}

	async goto() {
		await this.journalPage.goToCreateNewTemplate();
	}
}
