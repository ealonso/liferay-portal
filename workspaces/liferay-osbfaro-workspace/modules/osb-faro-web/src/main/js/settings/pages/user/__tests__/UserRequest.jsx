import mockStore from 'test/mock-store';
import React from 'react';
import UserRequest from '../UserRequest';
import {MockedProvider} from '@apollo/client/testing';
import {noop} from 'lodash';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage friendlyURL='/access-requests'>
			<MockedProvider freezeResults={false}>
				<UserRequest {...props} onSetUserRequest={noop} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('UserRequest', () => {
	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
