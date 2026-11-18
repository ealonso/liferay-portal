import fs from 'fs';
import path from 'path';
import React from 'react';
import {initializeConfig} from 'shared/util/config';

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

export function WorkspacePage({
	channelId = '321',
	children,
	friendlyURL = '/sites',
	groupId = '23',
	navigationItems = null,
	params = {},
	sidebarNavigationItems = null,
}) {
	initializeConfig({
		channelId,
		friendlyURL,
		groupId,
		navigationItems,
		pageURLs: PAGE_URLS,
		params,
		sidebarNavigationItems,
	});

	return children;
}

export function withWorkspacePage(Component) {
	return (props) => (
		<WorkspacePage>
			<Component {...props} />
		</WorkspacePage>
	);
}
