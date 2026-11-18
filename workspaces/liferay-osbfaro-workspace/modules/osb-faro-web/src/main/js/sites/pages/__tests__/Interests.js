import configureStore from 'shared/store/store-dev';
import Interests from '../Interests';
import React from 'react';
import {MockedProvider} from '@apollo/client/testing';
import {mockPreferenceReq, mockTimeRangeReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = ({
	mocks = [mockTimeRangeReq(), mockPreferenceReq()],
	...props
}) => (
	<Provider store={configureStore()}>
		<WorkspacePage friendlyURL="/site-interests">
			<MockedProvider mocks={mocks}>
				<Interests
					router={{params: {groupId: '23'}, query: {}}}
					{...props}
				/>
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('Sites Dashboard Interests', () => {
	it('render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
