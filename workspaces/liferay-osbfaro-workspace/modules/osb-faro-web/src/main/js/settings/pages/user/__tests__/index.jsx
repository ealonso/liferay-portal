import * as API from 'shared/api';
import BundleRouter from 'route-middleware/BundleRouter';
import mockStore from 'test/mock-store';
import React, {lazy} from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {Provider} from 'react-redux';
import {User as UserRoutes} from '../index';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

const UserList = lazy(() => import('../UserList'));

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage
			navigationItems={[
			{
				active: true,
				href: '/users',
				label: 'Manage Users'
			},
			{
				active: false,
				data: {id: 'requests'},
				href: '/access-requests',
				label: 'Requests'
			}
		]}
		>
			<UserRoutes
				renderContent={({currentUser}) => (
					<BundleRouter
						componentProps={{currentUser}}
						data={UserList}
					/>
				)}
				{...props}
			/>
		</WorkspacePage>
	</Provider>
);

describe('UserRoutes', () => {
	afterEach(cleanup);

	it('should render', () => {
		mockCurrentUser();

		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});

	it('if the user is AC Admin, then the tabs for toggling between users and user requests should render', () => {
		mockCurrentUser();

		render(<DefaultComponent />);

		expect(
			screen.getByRole('link', {name: /^manage users$/i})
		).toHaveAttribute('href', '/users');
		expect(screen.getByRole('link', {name: /^requests$/i})).toHaveAttribute(
			'href',
			'/access-requests'
		);
	});

	it('if the user is NOT an AC Admin, then the tabs for toggling between users and user requests should NOT render', () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		render(<DefaultComponent />);

		expect(screen.queryByRole('link', {name: /^manage users$/i})).toBeNull();
		expect(screen.queryByRole('link', {name: /^requests$/i})).toBeNull();
	});

	it('fetches the access request count once, not on every render', () => {
		mockCurrentUser();

		API.user.fetchCount.mockClear();

		const {rerender} = render(<DefaultComponent />);

		rerender(<DefaultComponent />);

		expect(API.user.fetchCount).toHaveBeenCalledTimes(1);
	});
});
