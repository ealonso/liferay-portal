import mockStore from 'test/mock-store';
import React from 'react';
import Touchpoints from '../Touchpoints';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockPreferenceReq, mockTimeRangeReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

const DefaultComponent = () => (
	<Provider store={mockStore()}>
		<MockedProvider
			cache={
				new InMemoryCache({
					addTypename: false,
					freezeResults: false,
				})
			}
			mocks={[mockTimeRangeReq(), mockPreferenceReq()]}
		>
			<Touchpoints router={{params: {}, query: {}}} />
		</MockedProvider>
	</Provider>
);

describe('Sites Dashboard Touchpoints Page', () => {
	it('render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
