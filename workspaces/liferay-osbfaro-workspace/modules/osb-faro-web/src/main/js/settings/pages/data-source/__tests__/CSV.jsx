import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import {CSV} from '../CSV';
import {DataSource, User} from 'shared/util/records';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {UserRoleNames} from 'shared/util/constants';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage params={{id: 'test'}}>
			<CSV
				currentUser={new User(data.mockUser())}
				dataSource={new DataSource(data.mockCSVDataSource())}
				groupId='23'
				id='test'
				{...props}
			/>
		</WorkspacePage>
	</Provider>
);

describe('CSV', () => {
	it('should render', () => {
		const {container, queryByText} = render(<DefaultComponent />);

		expect(queryByText(/Edit CSV/)).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('should not render an Edit CSV Configuration button if the user role is member', () => {
		const {queryByText} = render(
			<DefaultComponent
				currentUser={
					new User(data.mockUser(0, {roleName: UserRoleNames.Member}))
				}
			/>
		);

		expect(queryByText(/Edit CSV/)).toBeNull();
	});
});
