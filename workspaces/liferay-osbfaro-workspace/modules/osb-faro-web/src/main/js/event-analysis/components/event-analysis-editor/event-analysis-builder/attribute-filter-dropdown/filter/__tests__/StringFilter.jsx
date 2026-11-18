import mockStore from 'test/mock-store';
import React from 'react';
import StringFilter from '../StringFilter';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage channelId='456' groupId='123' params={{id: '789'}}>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
			>
				<StringFilter onSubmit={jest.fn()} {...props} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('StringFilter', () => {
	it('should render', () => {
		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});
});
