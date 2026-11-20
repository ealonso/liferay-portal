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
import {
	UsageApp,
	UsageExternalApp,
} from '../../../fragments/settings/SettingsApps';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';

jest.mock('../../../fragments/PageApp', () => ({children}) => children);

const mockStoreDataSaaS = mockStoreData.setIn(
	['projects', '23', 'data', 'faroSubscription'],
	fromJS({name: SubscriptionNames.LiferaySaasEnterprisePlan})
);

const renderUsagePage = (App, store, ldpEnabled = false) =>
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
				<WorkspacePage channelId='' groupId='23' ldpEnabled={ldpEnabled}>
					<App channelId='' groupId='23' pageURLs={{}} />
				</WorkspacePage>
			</MockedProvider>
		</Provider>
	);

describe('Settings usage page', () => {
	beforeEach(() => {
		mockCurrentUser();
	});

	afterEach(() => {
		API.projects.fetch.mockImplementation(() =>
			Promise.resolve(data.mockProject())
		);
	});

	it('should render the classic Usage Overview page', async () => {
		renderUsagePage(UsageApp, mockStore());

		expect(
			await screen.findByTestId('subscription-details')
		).toBeInTheDocument();
	});

	it('should render the external Usage Overview page for an LDP project', async () => {
		API.projects.fetch.mockReturnValue(
			Promise.resolve(
				data.mockProject('23', {
					faroSubscription: fromJS({
						name: SubscriptionNames.LiferayDataPlatformPrivateBeta
					})
				})
			)
		);

		renderUsagePage(UsageExternalApp, mockStore(mockStoreDataLDP), true);

		expect(
			await screen.findByText(/^view your workspace metrics$/i)
		).toBeInTheDocument();
	});

	it('should render the external Usage Overview page for a SaaS project', async () => {
		API.projects.fetch.mockReturnValue(
			Promise.resolve(
				data.mockProject('23', {
					faroSubscription: fromJS({
						name: SubscriptionNames.LiferaySaasEnterprisePlan
					})
				})
			)
		);

		renderUsagePage(UsageExternalApp, mockStore(mockStoreDataSaaS));

		expect(
			await screen.findByText(/^view your saas project metrics$/i)
		).toBeInTheDocument();
	});
});
