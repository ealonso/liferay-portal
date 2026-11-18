import getEventDashboardUrl, {
	EventDashboardContext,
} from '../getEventDashboardUrl';
import {RangeKeyTimeRanges} from 'shared/util/constants';
import {UserSessionEvent} from 'shared/queries/UserSessionQuery';

const CONTEXT: EventDashboardContext = {
	channelId: '420253908131944590',
	groupId: 'liferay.com',
	rangeSelectors: {
		rangeEnd: null,
		rangeKey: RangeKeyTimeRanges.Last30Days,
		rangeStart: null,
	},
};

const buildEvent = (
	overrides: Partial<UserSessionEvent> = {}
): UserSessionEvent =>
	({
		applicationId: 'WebContent',
		assetTitle: 'Company - Nav Item 1',
		canonicalUrl: 'https://www.liferay.com/careers',
		name: 'webContentViewed',
		pageTitle: 'Careers',
		properties: [
			{name: 'articleId', value: '521827670'},
			{name: 'type', value: 'web-content'},
		],
		...overrides,
	}) as unknown as UserSessionEvent;

describe('getEventDashboardUrl', () => {
	it('builds an asset dashboard link for an asset event', () => {
		expect(getEventDashboardUrl(buildEvent(), CONTEXT)).toBe(
			'/asset?assetId=521827670&assetType=web-content&touchpoint=Any&type=webContent&title=Company+-+Nav+Item+1&rangeKey=30'
		);
	});

	it('builds a page dashboard link for a pageViewed event', () => {
		const event = buildEvent({
			applicationId: 'Page',
			assetTitle: null as unknown as string,
			canonicalUrl: 'https://www.liferay.com/legal',
			name: 'pageViewed',
			pageTitle: 'Legal 2026 - Liferay DXP',
			properties: [{name: 'externalReferenceCode', value: 'abc'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBe(
			'/page-overview?touchpoint=https%3A%2F%2Fwww.liferay.com%2Flegal&title=Legal+2026+-+Liferay+DXP&rangeKey=30'
		);
	});

	it('builds an asset dashboard link for a form event using formId', () => {
		const event = buildEvent({
			applicationId: 'Form',
			assetTitle: 'Contact Form',
			name: 'formSubmitted',
			properties: [{name: 'formId', value: '54321'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBe(
			'/asset?assetId=54321&assetType=forms&touchpoint=Any&type=form&title=Contact+Form&rangeKey=30'
		);
	});

	it('builds an asset dashboard link for a blog event using entryId', () => {
		const event = buildEvent({
			applicationId: 'Blog',
			assetTitle: 'A Guide to Headless CMS',
			name: 'blogViewed',
			properties: [{name: 'entryId', value: '372644605'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBe(
			'/asset?assetId=372644605&assetType=blogs&touchpoint=Any&type=blog&title=A+Guide+to+Headless+CMS&rangeKey=30'
		);
	});

	it('builds an asset dashboard link for a document event using fileEntryId', () => {
		const event = buildEvent({
			applicationId: 'Document',
			assetTitle: 'Liferay-vs-Adobe',
			name: 'documentPreviewed',
			properties: [{name: 'fileEntryId', value: '527759973'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBe(
			'/asset?assetId=527759973&assetType=documents-and-media&touchpoint=Any&type=document&title=Liferay-vs-Adobe&rangeKey=30'
		);
	});

	it('builds an object-entry dashboard link using the external reference code and object definition name', () => {
		const event = buildEvent({
			applicationId: 'ObjectEntry',
			assetTitle: 'England vs Argentina',
			name: 'objectEntryViewed',
			properties: [
				{name: 'externalReferenceCode', value: 'match-102'},
				{name: 'objectDefinitionName', value: 'WorldCupMatch'},
			],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBe(
			'/asset?assetId=match-102&assetType=object-entry&touchpoint=Any&type=WorldCupMatch&title=England+vs+Argentina&rangeKey=30'
		);
	});

	it('returns undefined for an asset event missing its id property', () => {
		const event = buildEvent({
			applicationId: 'Document',
			assetTitle: 'Untracked download',
			name: 'documentPreviewed',
			properties: [],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBeUndefined();
	});

	it('returns undefined for an object-entry event missing the object definition name', () => {
		const event = buildEvent({
			applicationId: 'ObjectEntry',
			assetTitle: 'England vs Argentina',
			name: 'objectEntryViewed',
			properties: [{name: 'externalReferenceCode', value: 'match-102'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBeUndefined();
	});

	it('returns undefined for a webhook event', () => {
		expect(
			getEventDashboardUrl(buildEvent(), {...CONTEXT, isWebhook: true})
		).toBeUndefined();
	});

	it('returns undefined for an unmapped application id', () => {
		const event = buildEvent({
			applicationId: 'CustomEvent',
			properties: [{name: 'customId', value: '1'}],
		});

		expect(getEventDashboardUrl(event, CONTEXT)).toBeUndefined();
	});

	it('returns undefined when the group or channel is missing', () => {
		expect(
			getEventDashboardUrl(buildEvent(), {
				rangeSelectors: CONTEXT.rangeSelectors,
			})
		).toBeUndefined();
	});

	it('includes accountId and accountName as query params on a page dashboard link when provided', () => {
		const event = buildEvent({
			applicationId: 'Page',
			assetTitle: null as unknown as string,
			canonicalUrl: 'https://www.liferay.com/legal',
			name: 'pageViewed',
			pageTitle: 'Legal 2026 - Liferay DXP',
			properties: [{name: 'externalReferenceCode', value: 'abc'}],
		});

		const url = getEventDashboardUrl(event, {
			...CONTEXT,
			accountId: 'acc-1',
			accountName: 'Acme Corporation',
		});

		expect(url).toContain('accountId=acc-1');
		expect(url).toContain('accountName=Acme');
	});

	it('includes accountId and accountName as query params on an asset dashboard link when provided', () => {
		const url = getEventDashboardUrl(buildEvent(), {
			...CONTEXT,
			accountId: 'acc-1',
			accountName: 'Acme Corporation',
		});

		expect(url).toContain(
			'/asset?assetId=521827670&assetType=web-content&touchpoint=Any&type=webContent&title=Company+-+Nav+Item+1'
		);
		expect(url).toContain('rangeKey=30');
		expect(url).toContain('accountId=acc-1');
		expect(url).toContain('accountName=Acme');
	});

	it('includes accountId and accountName as query params on an object-entry dashboard link when provided', () => {
		const event = buildEvent({
			applicationId: 'ObjectEntry',
			assetTitle: 'England vs Argentina',
			name: 'objectEntryViewed',
			properties: [
				{name: 'externalReferenceCode', value: 'match-102'},
				{name: 'objectDefinitionName', value: 'WorldCupMatch'},
			],
		});

		const url = getEventDashboardUrl(event, {
			...CONTEXT,
			accountId: 'acc-1',
			accountName: 'Acme Corporation',
		});

		expect(url).toContain(
			'/asset?assetId=match-102&assetType=object-entry&touchpoint=Any&type=WorldCupMatch&title=England+vs+Argentina'
		);
		expect(url).toContain('accountId=acc-1');
		expect(url).toContain('accountName=Acme');
	});

	it('omits the account query params when the timeline provides no account context', () => {
		const assetUrl = getEventDashboardUrl(buildEvent(), CONTEXT);

		expect(assetUrl).not.toContain('accountId');
		expect(assetUrl).not.toContain('accountName');

		const objectEntryEvent = buildEvent({
			applicationId: 'ObjectEntry',
			assetTitle: 'England vs Argentina',
			name: 'objectEntryViewed',
			properties: [
				{name: 'externalReferenceCode', value: 'match-102'},
				{name: 'objectDefinitionName', value: 'WorldCupMatch'},
			],
		});

		const objectEntryUrl = getEventDashboardUrl(objectEntryEvent, CONTEXT);

		expect(objectEntryUrl).not.toContain('accountId');
		expect(objectEntryUrl).not.toContain('accountName');
	});
});
