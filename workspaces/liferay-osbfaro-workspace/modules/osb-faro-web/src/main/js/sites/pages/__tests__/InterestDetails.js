import client from 'shared/apollo/client';
import InterestDetails from '../InterestDetails';
import React from 'react';
import {ApolloProvider} from '@apollo/client';
import {ChannelContext} from 'shared/context/channel';
import {mockChannelContext} from 'test/mock-channel-context';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('Sites Dashboard InterestDetails', () => {
	it('render', () => {
		const {container} = render(
			<ApolloProvider client={client}>
				<WorkspacePage
					channelId="456"
					groupId="123"
					params={{interestId: 'Liferay', rangeKey: '30'}}
				>
					<ChannelContext.Provider value={mockChannelContext()}>
						<InterestDetails
							channelName="Test Channel"
							router={{
								params: {channelId: '456', groupId: '123'},
								query: {rangeKey: '30'},
							}}
						/>
					</ChannelContext.Provider>
				</WorkspacePage>
			</ApolloProvider>
		);

		expect(container).toMatchSnapshot();
	});
});
