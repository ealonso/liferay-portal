import ActiveIndividualsCard from '../ActiveIndividualsCard';
import client from 'shared/apollo/client';
import React from 'react';
import {ApolloProvider} from '@apollo/client';
import {cleanup, render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('ActiveIndividualsCard', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<ApolloProvider client={client}>
				<WorkspacePage channelId='456' params={{rangeKey: '30'}}>
					<ActiveIndividualsCard />
				</WorkspacePage>
			</ApolloProvider>
		);
		expect(container).toMatchSnapshot();
	});
});
