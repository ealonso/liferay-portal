import type {Channel} from 'shared/components/channels-menu';
import {camelCase} from 'lodash';

export interface INavigationItem {
	active?: boolean;
	data?: {[key: string]: boolean | string};
	deprecated?: boolean;
	href?: string;
	icon?: string;
	label: string;
	navigationItems?: INavigationItem[];
}

export interface IBackendConfig {
	channelId?: string;
	channels?: Channel[] | null;
	friendlyURL?: string;
	groupId?: string;
	ldpEnabled?: boolean;
	navigationItems?: INavigationItem[] | null;
	pageURLs?: {[friendlyURL: string]: string};
	params?: {[name: string]: string | undefined} | null;
	signOutURL?: string;
}

export type Config = {
	readonly [key: `${string}URL`]: string;
} & {
	readonly channelId: string;
	readonly channels: Channel[] | null;
	readonly friendlyURL: string;
	readonly groupId: string;
	readonly ldpEnabled: boolean;
	readonly navigationItems: INavigationItem[] | null;
	readonly params: Readonly<{[name: string]: string}>;
	readonly signOutURL: string;
};

export function buildConfig({
	channelId = '',
	channels,
	friendlyURL = '',
	groupId = '',
	ldpEnabled = false,
	navigationItems,
	pageURLs = {},
	params,
	signOutURL = '',
}: IBackendConfig): Config {
	const pageParams: {[name: string]: string} = {};

	for (const [name, value] of Object.entries(params ?? {})) {
		if (value) {
			pageParams[name] = value;
		}
	}

	const urls: {[key: string]: string} = {};

	for (const [pageFriendlyURL, url] of Object.entries(pageURLs)) {
		urls[`${camelCase(pageFriendlyURL)}URL`] = url;
	}

	return Object.freeze({
		...urls,
		channelId,
		channels: channels ?? null,
		friendlyURL,
		groupId,
		ldpEnabled,
		navigationItems: navigationItems ?? null,
		params: Object.freeze(pageParams),
		signOutURL,
	}) as Config;
}
