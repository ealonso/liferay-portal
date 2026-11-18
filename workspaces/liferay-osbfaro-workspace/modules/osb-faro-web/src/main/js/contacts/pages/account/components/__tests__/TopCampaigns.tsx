import React from 'react';
import TopCampaigns from '../TopCampaigns';
import {render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('@liferay/frontend-data-set-web', () => ({
	...jest.requireActual('@liferay/frontend-data-set-web'),
	FrontendDataSet: () => <div data-testid="fds-component" />,
}));

jest.mock('shared/components/dropdown-range-key/DropdownRangeKey', () => ({
	DropdownRangeKey: () => <div data-testid="dropdown-range-key" />,
}));

const renderTopCampaigns = (
	ui: React.ReactElement,
	params: {[name: string]: string} = {id: 'acc-1'}
) =>
	render(
		<WorkspacePage channelId="456" groupId="23" params={params}>
			{ui}
		</WorkspacePage>
	);

const getViewAllHref = () =>
	screen.getByRole('link', {name: 'View All'}).getAttribute('href')!;

describe('TopCampaigns', () => {
	it('should render the card title', () => {
		renderTopCampaigns(<TopCampaigns accountName="IQVIA" />);

		expect(screen.getByText('TOP CAMPAIGNS')).toBeInTheDocument();
	});

	it('should link to the campaigns filtered by the account', () => {
		renderTopCampaigns(<TopCampaigns accountName="IQVIA" />);

		expect(getViewAllHref()).toBe(
			'/campaigns?rangeKey=30&accountId=acc-1&accountName=IQVIA'
		);
	});

	it('should leave the account name off while it is loading', () => {
		renderTopCampaigns(<TopCampaigns />);

		expect(getViewAllHref()).not.toContain('accountName');
	});

	it('should render neither the list nor the link while the account id is missing', () => {
		renderTopCampaigns(<TopCampaigns accountName="IQVIA" />, {});

		expect(screen.queryByTestId('fds-component')).not.toBeInTheDocument();
		expect(
			screen.queryByRole('link', {name: 'View All'})
		).not.toBeInTheDocument();
	});
});
