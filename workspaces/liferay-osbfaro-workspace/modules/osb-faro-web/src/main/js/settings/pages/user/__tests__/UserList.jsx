import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import UserList from '../UserList';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {User} from 'shared/util/records';
import {UserRoleNames} from 'shared/util/constants';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const defaultProps = {
	currentUser: new User(data.mockUser()),
	groupId: '23'
};

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage friendlyURL='/users'>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
			>
				<UserList {...defaultProps} {...props} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('UserList', () => {
	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it("should render rows as disabled without row actions, invite members button, or checkboxes if the current user's role is member", async () => {
		const {container, queryByTestId, queryByText} = render(
			<DefaultComponent
				currentUser={
					new User(data.mockUser(0, {roleName: UserRoleNames.Member}))
				}
			/>
		);

		await waitForLoadingToBeRemoved(container);

		expect(
			container.querySelector(
				'.table > tbody:nth-of-type(1) > tr.disabled'
			)
		).not.toBeNull();

		expect(queryByTestId('select-all-checkbox')).not.toBeInTheDocument();

		expect(queryByText('Invite Users')).not.toBeInTheDocument();
	});
});
