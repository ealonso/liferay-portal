import * as API from 'shared/api';
import * as data from 'test/data';
import mockStore, {mockStoreData, mockStoreDataLDP} from 'test/mock-store';
import React from 'react';
import {fromJS} from 'immutable';
import {InMemoryCache} from '@apollo/client';
import {mockDataSourcesReq} from 'test/graphql-data';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render, screen} from '@testing-library/react';
import {SubscriptionNames} from 'shared/util/subscriptions';
import {UsageApp} from '../../../fragments/settings/SettingsApps';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('../../../fragments/PageApp', () => ({children}) => children);

jest.mock('shared/hooks/useCurrentUser', () => ({
	useCurrentUser: jest.fn()
}));

const mockStoreDataSaaS = mockStoreData.setIn(
	['projects', '23', 'data', 'faroSubscription'],
	fromJS({name: SubscriptionNames.LiferaySaasEnterprisePlan})
);

const renderUsagePage = store =>
	render(
		<Provider store={store}>
			<MockedProvider
				cache={new InMemoryCache({addTypename: false})}
				mocks={[
					mockDataSourcesReq([
						{
							__typename: 'DataSource',
							id: '123',
							name: 'foo datasource',
							url: 'foo.url'
						}
					])
				]}
			>
				<WorkspacePage channelId='' groupId='23'>
					<UsageApp channelId='' groupId='23' pageURLs={{}} />
				</WorkspacePage>
			</MockedProvider>
		</Provider>
	);

describe('Settings usage page', () => {
	beforeEach(() => {
		useCurrentUser.mockImplementation(() => ({isAdmin: () => true}));
	});

	afterEach(() => {
		API.projects.fetch.mockImplementation(() =>
			Promise.resolve(data.mockProject())
		);
	});

	it('should render the classic Usage Overview page for a non-SaaS, non-LDP project', async () => {
		renderUsagePage(mockStore());

		expect(
			await screen.findByTestId('subscription-details')
		).toBeInTheDocument();
	});

	it('should render the SaaS Usage Overview page for an LDP project', async () => {
		API.projects.fetch.mockReturnValue(
			Promise.resolve(
				data.mockProject('23', {
					faroSubscription: fromJS({
						name: SubscriptionNames.LiferayDataPlatformPrivateBeta
					})
				})
			)
		);

		renderUsagePage(mockStore(mockStoreDataLDP));

		expect(
			await screen.findByText('View Your Workspace Metrics')
		).toBeInTheDocument();
	});

	it('should render the SaaS Usage Overview page for a SaaS project', async () => {
		API.projects.fetch.mockReturnValue(
			Promise.resolve(
				data.mockProject('23', {
					faroSubscription: fromJS({
						name: SubscriptionNames.LiferaySaasEnterprisePlan
					})
				})
			)
		);

		renderUsagePage(mockStore(mockStoreDataSaaS));

		expect(
			await screen.findByText('View Your SaaS Project Metrics')
		).toBeInTheDocument();
	});
});
