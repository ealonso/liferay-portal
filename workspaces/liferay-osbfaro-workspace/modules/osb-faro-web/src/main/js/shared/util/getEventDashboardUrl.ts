import {AssetTypes} from 'shared/util/constants';
import {getAssetDescriptorByRESTType} from 'assets/descriptors';
import {isString, pickBy} from 'lodash';
import {RangeSelectors} from 'shared/types';
import {UserSessionEvent} from 'shared/queries/UserSessionQuery';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

/**
 * Maps an event's `applicationId` to the trailing `:type` segment value and the
 * event property that holds the asset id (which varies by asset type — e.g.
 * `articleId` for web content, `formId` for forms).
 */
const ASSET_APPLICATIONS: Record<string, {idProperty: string; type: string}> = {
	[AssetTypes.Blog]: {
		idProperty: 'entryId',
		type: 'blog',
	},
	[AssetTypes.Document]: {
		idProperty: 'fileEntryId',
		type: 'document',
	},
	[AssetTypes.Form]: {
		idProperty: 'formId',
		type: 'form',
	},
	[AssetTypes.WebContent]: {
		idProperty: 'articleId',
		type: 'webContent',
	},
};

export interface EventDashboardContext {
	accountId?: string;
	accountName?: string;
	channelId?: string;
	groupId?: string;
	isWebhook?: boolean;
	rangeSelectors?: RangeSelectors;
	timeZoneId?: string;
}

const getProperty = (
	event: UserSessionEvent,
	name: string
): string | undefined =>
	event.properties?.find((property) => property.name === name)?.value;

/**
 * Builds the dashboard link for an activity-stream event's title. Shared by the
 * account and individual timelines (the event behavior is not account-specific).
 *
 * - Webhook events are only logs, so they have no dashboard (returns undefined).
 * - Asset events (identified by their `applicationId`) link to the asset
 *   dashboard, using the type-specific id property as the asset id; the
 *   touchpoint is always "Any".
 * - Object-entry events link to the object-entry dashboard, using the entry's
 *   external reference code as the asset id and the object definition name as
 *   the dashboard type.
 * - pageViewed events link to the page (touchpoint) dashboard, using the
 *   canonical URL as the touchpoint.
 *
 * Every dashboard link carries `accountId`/`accountName` when the caller
 * provides them, so the destination opens pre-filtered by the same account the
 * timeline is scoped to. Only the account timeline passes them; the individual
 * timeline never does, so its links stay untouched.
 *
 * Anything else (e.g. unmapped application ids) returns undefined.
 */
export const getEventDashboardUrl = (
	event: UserSessionEvent,
	{
		accountId,
		accountName,
		channelId,
		groupId,
		isWebhook,
		rangeSelectors,
	}: EventDashboardContext
): string | undefined => {
	if (isWebhook || !channelId || !groupId) {
		return undefined;
	}

	// One statement of how a dashboard link is assembled: the page, the date
	// range, then the account. The account params are left off entirely when
	// there is no account context, so the individual timeline never gains them.

	const link = (url: string, params: object): string =>
		setUriQueryValues(
			{
				...params,
				...pickBy(
					{
						accountId,
						accountName,
						rangeEnd: rangeSelectors?.rangeEnd,
						rangeKey: rangeSelectors?.rangeKey,
						rangeStart: rangeSelectors?.rangeStart,
					},
					isString
				),
			},
			url
		);

	const assetApplication = ASSET_APPLICATIONS[event.applicationId];

	if (assetApplication) {
		const assetId = getProperty(event, assetApplication.idProperty);

		if (!assetId) {
			return undefined;
		}

		const title = event.assetTitle || event.pageTitle;

		return link(config.assetURL, {
			assetId,
			assetType: getAssetDescriptorByRESTType(assetApplication.type).slug,
			touchpoint: 'Any',
			type: assetApplication.type,
			...(title && {title}),
		});
	}

	if (event.applicationId === AssetTypes.ObjectEntry) {
		const assetId = getProperty(event, 'externalReferenceCode');
		const type = getProperty(event, 'objectDefinitionName');

		if (!assetId || !type) {
			return undefined;
		}

		const title = event.assetTitle || event.pageTitle;

		return link(config.assetURL, {
			assetId,
			assetType: 'object-entry',
			touchpoint: 'Any',
			type,
			...(title && {title}),
		});
	}

	if (event.applicationId === AssetTypes.WebPage && event.canonicalUrl) {
		const title = event.pageTitle || event.assetTitle;

		return link(config.pageOverviewURL, {
			touchpoint: event.canonicalUrl,
			...(title && {title}),
		});
	}

	return undefined;
};

export default getEventDashboardUrl;
