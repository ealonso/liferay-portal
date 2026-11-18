import mockStore from 'test/mock-store';
import React from 'react';
import RequestLog from '../RequestLog';
import {cleanup, render} from '@testing-library/react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({
		timeZoneId: 'UTC'
	})
}));

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
			>
				<RequestLog
					router={{params: {groupId: '23'}, query: {}}}
					{...props}
				/>
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('RequestLog', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
