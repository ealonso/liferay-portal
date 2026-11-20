import mockStore from 'test/mock-store';
import React from 'react';
import {Dashboard} from '../index';
import {Provider} from 'react-redux';
import {Router} from 'shared/types';
import {render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

jest.mock('shared/components/download-report/DownloadCSVReport', () => ({
	__esModule: true,
	default: () => null,
}));

jest.mock('shared/components/download-report/DownloadPDFReport', () => ({
	__esModule: true,
	default: () => null,
}));

jest.mock('shared/components/Loading', () => ({
	__esModule: true,
	default: () => null,
}));

jest.mock('shared/components/AccountDropdown', () => ({
	__esModule: true,
	default: ({
		initialAccountId,
		initialAccountName,
	}: {
		initialAccountId?: string;
		initialAccountName?: string;
	}) => (
		<div
			data-initial-account-id={initialAccountId}
			data-initial-account-name={initialAccountName}
			data-testid="filter-by-account"
		/>
	),
}));

jest.mock('shared/components/SegmentDropdown', () => ({
	__esModule: true,
	default: ({
		initialSegmentId,
		initialSegmentName,
	}: {
		initialSegmentId?: string;
		initialSegmentName?: string;
	}) => (
		<div
			data-initial-segment-id={initialSegmentId}
			data-initial-segment-name={initialSegmentName}
			data-testid="filter-by-segment"
		/>
	),
}));

jest.mock('shared/context/channel', () => ({
	useChannelContext: () => ({selectedChannel: {name: 'test channel'}}),
}));

jest.mock('shared/context/dataSources', () => ({
	useDataSources: () => ({empty: false, error: false, loading: false}),
}));

beforeEach(() => mockCurrentUser({roleName: UserRoleNames.Member}));

describe('Dashboard', () => {
	const router: Router = {
		params: {
			channelId: '456',
			groupId: '789',
		},
		query: {},
	};

	it('shows the account filter for LDP workspaces', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage channelId="456" groupId="789" ldpEnabled>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.getByTestId('filter-by-account')).toBeInTheDocument();
	});

	it('hides the account filter for non-LDP workspaces', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage channelId="456" groupId="789" ldpEnabled={false}>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.queryByTestId('filter-by-account')).toBeNull();
	});

	it('seeds the account filter from the accountId/accountName page params', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage
					channelId="456"
					groupId="789"
					ldpEnabled
					params={{accountId: '100', accountName: 'Account 100'}}
				>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.getByTestId('filter-by-account')).toHaveAttribute(
			'data-initial-account-id',
			'100'
		);
		expect(screen.getByTestId('filter-by-account')).toHaveAttribute(
			'data-initial-account-name',
			'Account 100'
		);
	});

	it('shows the segment filter for LDP workspaces', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage channelId="456" groupId="789" ldpEnabled>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.getByTestId('filter-by-segment')).toBeInTheDocument();
	});

	it('hides the segment filter for non-LDP workspaces', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage channelId="456" groupId="789" ldpEnabled={false}>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.queryByTestId('filter-by-segment')).toBeNull();
	});

	it('seeds the segment filter from the segmentId/segmentName page params', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacePage
					channelId="456"
					groupId="789"
					ldpEnabled
					params={{segmentId: '100', segmentName: 'Segment 100'}}
				>
					<Dashboard renderContent={() => null} router={router} />
				</WorkspacePage>
			</Provider>
		);

		expect(screen.getByTestId('filter-by-segment')).toHaveAttribute(
			'data-initial-segment-id',
			'100'
		);
		expect(screen.getByTestId('filter-by-segment')).toHaveAttribute(
			'data-initial-segment-name',
			'Segment 100'
		);
	});
});
