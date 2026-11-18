import * as pedantic from 'test/pedantic';
import DurationFilter from '../DurationFilter';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';

jest.unmock('react-dom');

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<MockedProvider
			cache={
				new InMemoryCache({
					addTypename: false
				})
			}
			mocks={[]}
		>
			<DurationFilter
				attributeId='123'
				displayName='Duration'
				onSubmit={jest.fn()}
				{...props}
			/>
		</MockedProvider>
	</Provider>
);

describe('DurationFilter', () => {
	beforeEach(() => {
		pedantic.disable();
	});

	afterEach(() => {
		pedantic.enable();
		cleanup();
	});

	it('should render', () => {
		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});
});
