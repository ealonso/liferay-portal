jest.unmock('react-dom');

import mockStore from 'test/mock-store';
import NumberFilter from '../NumberFilter';
import React from 'react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';

describe('NumberFilter', () => {
	it('should render', async () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<MockedProvider
					cache={
						new InMemoryCache({
							addTypename: false
						})
					}
				>
					<NumberFilter onSubmit={jest.fn()} />
				</MockedProvider>
			</Provider>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
