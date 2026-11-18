import {Config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

type IBasicRouteArgs = {
	groupId: string;
	label?: string | null;
};

type IBasicSidebarRouteArgs = IBasicRouteArgs & {
	channelId: string;
};

export type IBreadcrumbArgs = {
	active?: boolean;
	href?: string | null;
	groupId?: string;
	id?: string;
	label: string;
	truncate?: boolean;
};

export const getEntityName = ({
	active = true,
	label,
	truncate = true,
	...otherData
}: IBreadcrumbArgs) => ({
	active,
	label,
	truncate,
	...otherData,
});

/**
 * Home
 */
export const getHome = ({label}: IBasicSidebarRouteArgs, config: Config) => ({
	href: config.sitesURL,
	label: label ? label : Liferay.Language.get('home'),
});

/**
 * Entities
 */
export const getAccounts = (config: Config) => ({
	href: config.accountsURL,
	label: Liferay.Language.get('accounts'),
});

export const getCampaigns = (
	{
		queryValues = {},
	}: {
		queryValues?: {[key: string]: number | string | undefined};
	},
	config: Config
) => ({
	href: setUriQueryValues(queryValues, config.campaignsURL),
	label: Liferay.Language.get('campaigns'),
});

export const getIndividuals = (
	{LDPEnabled}: {LDPEnabled: boolean},
	config: Config
) => ({
	href: LDPEnabled ? config.individualsURL : config.knownIndividualsURL,
	label: Liferay.Language.get('individuals'),
});

export const getLifecycles = (config: Config) => ({
	href: config.lifecyclesURL,
	label: Liferay.Language.get('lifecycles'),
});

export const getSegments = (href: string) => ({
	href,
	label: Liferay.Language.get('segments'),
});

export const getSites = (config: Config) => ({
	href: config.sitesURL,
	label: Liferay.Language.get('sites'),
	truncate: true,
});

export const getPages = (config: Config) => ({
	href: config.pagesURL,
	label: Liferay.Language.get('pages'),
	truncate: true,
});

export const getTests = (config: Config) => ({
	href: config.testsURL,
	label: Liferay.Language.get('tests'),
});

/**
 * Assets
 */

/**
 * `query` carries the list's date range, account and segment back, so the
 * breadcrumb returns to the list as it was left.
 */
export const getAssets = (
	{
		query = {},
	}: {
		query?: {[key: string]: number | string | undefined};
	},
	config: Config
) => ({
	href: setUriQueryValues(query, config.assetsURL),
	label: Liferay.Language.get('assets'),
});

/**
 * Settings
 */
export const getChannels = (config: Config) => ({
	href: config.propertiesURL,
	label: Liferay.Language.get('properties'),
});

export const getChannelName = (
	{active = false, id, label}: IBreadcrumbArgs,
	config: Config
) => ({
	active,
	href: id ? setUriQueryValues({id}, config.propertyURL) : null,
	label,
	truncate: true,
});

export const getDataPrivacy = (config: Config) => ({
	href: config.dataPrivacyURL,
	label: Liferay.Language.get('data-control-&-privacy'),
});

export const getDataSources = (config: Config) => ({
	href: config.dataSourcesURL,
	label: Liferay.Language.get('data-sources'),
});

export const getDataSourceName = (
	{active = false, id, label}: IBreadcrumbArgs,
	config: Config
) => ({
	active,
	href: id ? setUriQueryValues({id}, config.dataSourceURL) : null,
	label,
	truncate: true,
});

export const getDefinitions = (config: Config) => ({
	href: config.definitionsURL,
	label: Liferay.Language.get('definitions'),
});

export const getRecommendations = (config: Config) => ({
	href: config.recommendationsURL,
	label: Liferay.Language.get('recommendations'),
});

export const getEvents = (config: Config) => ({
	href: config.eventDefinitionsURL,
	label: Liferay.Language.get('events'),
});

export const getEventAnalysis = (config: Config) => ({
	href: config.eventsURL,
	label: Liferay.Language.get('event-analysis'),
});

export const getEventAttributes = (config: Config) => ({
	href: config.eventAttributesURL,
	label: Liferay.Language.get('event-attributes'),
});
