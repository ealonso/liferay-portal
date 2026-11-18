import BundleRouter from 'route-middleware/BundleRouter';
import mockStore from 'test/mock-store';
import React, {lazy} from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {Provider} from 'react-redux';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {User as UserRoutes} from '../index';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useCurrentUser', () => ({
	useCurrentUser: jest.fn()
}));

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
		useCurrentUser.mockImplementation(() => ({
			isAdmin: () => true
		}));

		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});

	it('if the user is AC Admin, then the tabs for toggling between users and user requests should render', () => {
		useCurrentUser.mockImplementation(() => ({
			isAdmin: () => true
		}));

		render(<DefaultComponent />);

		expect(
			screen.getByRole('link', {name: 'Manage Users'})
		).toHaveAttribute('href', '/users');
		expect(screen.getByRole('link', {name: 'Requests'})).toHaveAttribute(
			'href',
			'/access-requests'
		);
	});

	it('if the user is NOT an AC Admin, then the tabs for toggling between users and user requests should NOT render', () => {
		useCurrentUser.mockImplementation(() => ({
			isAdmin: () => false
		}));

		render(<DefaultComponent />);

		expect(screen.queryByRole('link', {name: 'Manage Users'})).toBeNull();
		expect(screen.queryByRole('link', {name: 'Requests'})).toBeNull();
	});
});
