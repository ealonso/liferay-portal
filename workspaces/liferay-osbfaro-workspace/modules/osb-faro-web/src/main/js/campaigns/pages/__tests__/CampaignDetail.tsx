import * as API from 'shared/api';
import CampaignDetail from '../CampaignDetail';
import mockStore from 'test/mock-store';
import React from 'react';
import {ChannelContext} from 'shared/context/channel';
import {mockChannelContext} from 'test/mock-channel-context';
import {Provider} from 'react-redux';
import {render, waitFor} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

let lastAccountDropdownProps: any;
let lastDropdownRangeKeyProps: any;

jest.mock('shared/components/AccountDropdown', () => ({
	__esModule: true,
	default: (props: any) => {
		lastAccountDropdownProps = props;

		return <div data-testid="account-dropdown" />;
	},
}));

// The real dropdown queries its presets through Apollo, which these tests do
// not provide. Its own suite covers it.

jest.mock('shared/components/dropdown-range-key/DropdownRangeKey', () => ({
	DropdownRangeKey: (props: any) => {
		lastDropdownRangeKeyProps = props;

		return <div data-testid="dropdown-range-key" />;
	},
}));

jest.mock('../../components/MembershipsCard', () => ({
	__esModule: true,
	default: () => <div data-testid="memberships-card" />,
}));

jest.mock('../../components/CampaignUtmParameters', () => ({
	__esModule: true,
	default: () => <div data-testid="campaign-utm-parameters" />,
}));

const fetchCampaign = API.campaigns.fetchCampaign as jest.Mock;

const renderCampaignDetail = (query = '') =>
	render(
		<Provider store={mockStore()}>
			<ChannelContext.Provider value={mockChannelContext() as any}>
				<WorkspacePage
					channelId="123"
					friendlyURL="/campaign"
					params={{
						...Object.fromEntries(new URLSearchParams(query)),
						id: '7',
					}}
				>
					<CampaignDetail />
				</WorkspacePage>
			</ChannelContext.Provider>
		</Provider>
	);

describe('CampaignDetail', () => {
	beforeEach(() => {
		fetchCampaign.mockReset();
		fetchCampaign.mockResolvedValue({
			campaignName: 'Multi-Cloud Solutions Guide',
			id: '7',
		});

		lastAccountDropdownProps = undefined;
		lastDropdownRangeKeyProps = undefined;
	});

	it('should ask the endpoint for the campaign the route names', async () => {
		renderCampaignDetail();

		await waitFor(() =>
			expect(fetchCampaign).toHaveBeenCalledWith(
				expect.objectContaining({
					channelId: '123',
					groupId: '23',
					id: '7',
				})
			)
		);
	});

	it('should title the screen with the campaign it fetched', async () => {
		const {findAllByText} = renderCampaignDetail();

		// The name is both the page title and the last breadcrumb.

		expect(
			(await findAllByText('Multi-Cloud Solutions Guide')).length
		).toBeGreaterThan(0);
	});

	it('should trail the breadcrumb back through Campaigns, keeping the range', () => {
		const {container} = renderCampaignDetail('?rangeKey=7');

		const hrefs = Array.from(
			container.querySelectorAll('.breadcrumb a'),
			(anchor) => anchor.getAttribute('href')
		);

		expect(hrefs).toContain('/campaigns?rangeKey=7');
	});

	it('should render the range selector in the sub header', () => {
		const {container} = renderCampaignDetail();

		expect(
			container.querySelector(
				'.sub-header-root [data-testid="dropdown-range-key"]'
			)
		).toBeTruthy();
		expect(lastDropdownRangeKeyProps.rangeSelectors.rangeKey).toBe('30');
	});

	it('should render the account selector in the sub header', () => {
		const {container} = renderCampaignDetail();

		expect(
			container.querySelector(
				'.sub-header-root [data-testid="account-dropdown"]'
			)
		).toBeTruthy();
		expect(lastAccountDropdownProps.initialAccountId).toBeUndefined();
	});

	it('should show the account the URL carries in the selector', () => {
		renderCampaignDetail('?accountId=9&accountName=Acme');

		expect(lastAccountDropdownProps.initialAccountId).toBe('9');
		expect(lastAccountDropdownProps.initialAccountName).toBe('Acme');
	});

	it('should read the range from the URL', () => {
		renderCampaignDetail('?rangeKey=7');

		expect(lastDropdownRangeKeyProps.rangeSelectors.rangeKey).toBe('7');
	});

	it('should keep a custom range on the way back to Campaigns', () => {
		const {container} = renderCampaignDetail(
			'?rangeEnd=2026-10-20&rangeKey=CUSTOM&rangeStart=2026-10-01'
		);

		const hrefs = Array.from(
			container.querySelectorAll('.breadcrumb a'),
			(anchor) => anchor.getAttribute('href')
		);

		expect(hrefs).toContain(
			'/campaigns?rangeEnd=2026-10-20&rangeKey=CUSTOM&rangeStart=2026-10-01'
		);
	});

	it('should keep the account on the way back to Campaigns', () => {
		const {container} = renderCampaignDetail(
			'?accountId=9&accountName=Acme&rangeKey=7'
		);

		const href = Array.from(
			container.querySelectorAll('.breadcrumb a'),
			(anchor) => anchor.getAttribute('href')!
		).find((href) => href.startsWith('/campaigns?'))!;

		const searchParams = new URL(href, 'http://localhost').searchParams;

		expect(searchParams.get('accountId')).toBe('9');
		expect(searchParams.get('accountName')).toBe('Acme');
		expect(searchParams.get('rangeKey')).toBe('7');
	});

	it('should leave the account off the way back when none is selected', () => {
		const {container} = renderCampaignDetail('?rangeKey=7');

		const hrefs = Array.from(
			container.querySelectorAll('.breadcrumb a'),
			(anchor) => anchor.getAttribute('href')!
		);

		expect(hrefs.some((href) => href.includes('accountId'))).toBe(false);
	});

	it('should render no name while the campaign is still in flight', () => {
		const {queryByText} = renderCampaignDetail();

		// Asserted before awaiting, so this is the first paint. A placeholder
		// would appear twice, since the title doubles as the breadcrumb.

		expect(queryByText('Multi-Cloud Solutions Guide')).toBeNull();
	});
});
