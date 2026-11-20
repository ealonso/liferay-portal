import client from 'shared/apollo/client';
import VisitorsListCard from '../VisitorsListCard';
import mockStore from 'test/mock-store';
import React from 'react';
import {ApolloProvider} from '@apollo/client';
import {fireEvent, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

const MOCK_ROUTER = {
	params: {
		channelId: '456',
		groupId: '789',
	},
	query: {},
};

describe('VisitorsListCard', () => {
	it('shows an Accounts tab active by default and switches to the Known Individuals tab on click', () => {
		const {getByTestId, getByText} = render(
			<WorkspacePage>
				<Provider store={mockStore()}>
					<ApolloProvider client={client}>
						<VisitorsListCard router={MOCK_ROUTER} />
					</ApolloProvider>
				</Provider>
			</WorkspacePage>
		);

		expect(getByText(/^accounts$/i)).toBeInTheDocument();
		expect(getByText(/^known individuals$/i)).toBeInTheDocument();

		expect(getByTestId('accounts').className).toContain('active');
		expect(getByTestId('individuals').className).not.toContain('active');

		fireEvent.click(getByText(/^known individuals$/i));

		expect(getByTestId('individuals').className).toContain('active');
		expect(getByTestId('accounts').className).not.toContain('active');
	});
});
