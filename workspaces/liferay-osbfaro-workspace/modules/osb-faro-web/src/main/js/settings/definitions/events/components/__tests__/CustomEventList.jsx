import * as data from 'test/data';
import * as NotificationAlertList from 'shared/components/NotificationAlertList';
import CustomEventList from '../CustomEventList';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockEventDefinitionsReq} from 'test/graphql-data';
import {NotificationSubtypes} from 'shared/util/records/Notification';
import {Provider} from 'react-redux';
import {range} from 'lodash';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

const mockNotificationAlertList = NotificationAlertList;

const WrappedComponent = ({
	children,
	mocks = [
		mockEventDefinitionsReq(
			[
				data.mockEventDefinition(0, {
					__typename: 'EventDefinition',
					type: 'CUSTOM'
				})
			],
			{
				blocked: false,
				eventType: 'CUSTOM'
			}
		)
	]
}) => (
	<Provider store={mockStore()}>
		<WorkspacePage params={{delta: '1', tab: 'custom'}}>
			<MockedProvider
				cache={new InMemoryCache({freezeResults: false})}
				mocks={mocks}
			>
				{children}
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('CustomEventList', () => {
	afterEach(cleanup);

	it('should render', async () => {
		mockCurrentUser();

		const {container, queryByTestId} = render(
			<WrappedComponent>
				<CustomEventList groupId='23' />
			</WrappedComponent>
		);

		await waitForLoadingToBeRemoved(container);

		expect(queryByTestId('select-all-checkbox')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('should render w/o select all checkbox if user is not an admin', async () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		const {container, queryByTestId} = render(
			<WrappedComponent>
				<CustomEventList groupId='23' />
			</WrappedComponent>
		);

		await waitForLoadingToBeRemoved(container);

		expect(queryByTestId('select-all-checkbox')).toBeNull();
	});

	it('should render alert notification', async () => {
		mockCurrentUser();

		mockNotificationAlertList.useNotificationsAPI = jest.fn(() => ({
			data: range(1).map(i =>
				data.mockNotification(i, {
					subtype: NotificationSubtypes.BlockedEventsLimit
				})
			),
			loading: false,
			refetch: () => {}
		}));

		const {container} = render(
			<WrappedComponent>
				<CustomEventList groupId='23' />
			</WrappedComponent>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
