import mockStore from 'test/mock-store';
import React from 'react';
import Search from '../Search';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockSearchStringListReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const WrappedComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
				mocks={[mockSearchStringListReq()]}
			>
				<Search groupId='23' {...props} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('Search', () => {
	it('should render', async () => {
		const {container} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
