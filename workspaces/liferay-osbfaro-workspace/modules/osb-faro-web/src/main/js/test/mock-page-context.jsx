import fs from 'fs';
import path from 'path';
import React from 'react';
import {buildConfig} from 'shared/util/config';
import {LanguageIds} from 'shared/util/constants';
import {mockUser} from './data';
import {PageConfigProvider} from 'shared/context/pageConfig';

const MODULES_DIR = path.resolve(__dirname, '../../../../..');

function getFriendlyURLs(dir) {
	if (!fs.existsSync(dir)) {
		return [];
	}

	return fs.readdirSync(dir, {withFileTypes: true}).flatMap((entry) => {
		const entryPath = path.join(dir, entry.name);

		if (entry.isDirectory()) {
			return getFriendlyURLs(entryPath);
		}

		if (entry.name !== 'page.json') {
			return [];
		}

		return [JSON.parse(fs.readFileSync(entryPath, 'utf8')).friendlyURL];
	});
}

export const PAGE_URLS = Object.fromEntries(
	[
		'osb-faro-site-initializer',
		'osb-faro-workspace-site-initializer',
	].flatMap((moduleName) =>
		getFriendlyURLs(
			path.join(
				MODULES_DIR,
				moduleName,
				'src/main/resources/site-initializer/layouts'
			)
		).map((friendlyURL) => [friendlyURL, friendlyURL])
	)
);

export const CURRENT_USER = mockUser('23', {
	groupId: '23',
	languageId: LanguageIds.English,
});

let currentUserData = CURRENT_USER;

export function mockCurrentUser(data = {}) {
	currentUserData = {...CURRENT_USER, ...data};
}

export function mockPageConfig({
	channelId = '321',
	currentUser = currentUserData,
	friendlyURL = '/sites',
	groupId = '23',
	ldpEnabled = false,
	navigationItems = null,
	params = {},
	...otherProps
} = {}) {
	return buildConfig({
		...otherProps,
		channelId,
		currentUser,
		friendlyURL,
		groupId,
		ldpEnabled,
		navigationItems,
		pageURLs: PAGE_URLS,
		params,
	});
}

export function WorkspacePage({children, ...props}) {
	return (
		<PageConfigProvider config={mockPageConfig(props)}>
			{children}
		</PageConfigProvider>
	);
}

export function withWorkspacePage(Component) {
	return (props) => (
		<WorkspacePage>
			<Component {...props} />
		</WorkspacePage>
	);
}
