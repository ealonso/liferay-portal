import * as data from 'test/data';
import * as useDataSources from 'shared/context/dataSources';
import BasePage from 'shared/components/base-page';
import client from 'shared/apollo/client';
import mockStore from 'test/mock-store';
import React from 'react';
import {ApolloProvider} from '@apollo/client';
import {ChannelContext} from 'shared/context/channel';
import {cleanup, render} from '@testing-library/react';
import {Dashboard} from '../index';
import {mockChannelContext} from 'test/mock-channel-context';
import {mockEmptyState, mockSuccessState} from 'test/__mocks__/mock-objects';
import {Provider} from 'react-redux';
import {User} from 'shared/util/records';
import {UserRoleNames} from 'shared/util/constants';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const MEMBER_USER = new User(
	data.mockUser(23, {roleName: UserRoleNames.Member})
);

const MOCK_CONTEXT = {
	rangeKey: {defaultValue: '30'},
	router: {
		params: {
			channelId: '123',
			groupId: '2000'
		},
		query: {
			rangeKey: '30'
		}
	}
};

const mockUseDataSource = useDataSources;

const WrappedComponent = ({
	channelContext = mockChannelContext(),
	...props
}) => (
	<ApolloProvider client={client}>
		<Provider store={mockStore()}>
			<WorkspacePage channelId="123" groupId="2000">
				<ChannelContext.Provider value={channelContext}>
					<BasePage.Context.Provider value={MOCK_CONTEXT}>
						<Dashboard
							currentUser={MEMBER_USER}
							renderContent={() => null}
							router={MOCK_CONTEXT.router}
							{...props}
						/>
					</BasePage.Context.Provider>
				</ChannelContext.Provider>
			</WorkspacePage>
		</Provider>
	</ApolloProvider>
);

describe('Sites Dashboard Index', () => {
	afterEach(cleanup);
	mockUseDataSource.useDataSources = jest.fn(() => mockSuccessState);

	it('renders w/ "No Sites Are Connected" as title', () => {
		const CHANNEL_CONTEXT_MOCK = {
			channelDispatch: () => {},
			channels: [],
			selectedChannel: null
		};

		const {container} = render(
			<WrappedComponent channelContext={CHANNEL_CONTEXT_MOCK} />
		);

		expect(container.querySelector('.title-section')).toHaveTextContent(
			'No Sites Are Connected'
		);
	});
});

describe('sites with no Data Source', () => {
	it('should render EmptyState', () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockEmptyState);

		const {getByText} = render(<WrappedComponent />);

		expect(
			getByText('No Sites Have Been Synced from Data Sources')
		).toBeInTheDocument();
		expect(
			getByText('Connect a data source with sites data.')
		).toBeInTheDocument();
		expect(
			getByText('Access our documentation to learn more.')
		).toBeInTheDocument();
	});
});
