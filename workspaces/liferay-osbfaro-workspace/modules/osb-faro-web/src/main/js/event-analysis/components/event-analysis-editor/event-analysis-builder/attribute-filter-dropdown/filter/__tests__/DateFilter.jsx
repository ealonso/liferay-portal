import DateFilter from '../DateFilter';
import mockStore from 'test/mock-store';
import React from 'react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockPreferenceReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';

jest.unmock('react-dom');

const WrappedComponent = props => (
	<Provider store={mockStore()}>
		<MockedProvider
			cache={
				new InMemoryCache({
					addTypename: false,
					freezeResults: false
				})
			}
			mocks={[mockPreferenceReq()]}
		>
			<DateFilter onSubmit={jest.fn()} {...props} />
		</MockedProvider>
	</Provider>
);

describe('DateFilter', () => {
	it('should render', () => {
		const {container} = render(<WrappedComponent />);

		expect(container).toMatchSnapshot();
	});
});
