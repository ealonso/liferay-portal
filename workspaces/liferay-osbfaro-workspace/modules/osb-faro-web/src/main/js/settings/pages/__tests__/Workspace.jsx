import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import {addAlert} from 'shared/actions/alerts';
import {cleanup, render} from '@testing-library/react';
import {Project, User} from 'shared/util/records';
import {Provider} from 'react-redux';
import {Workspace} from '../Workspace';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

jest.mock('shared/actions/alerts', () => ({
	actionTypes: {},
	addAlert: jest.fn(() => ({meta: {}, payload: {}, type: 'addAlert'}))
}));

const defaultProps = {
	addAlert,
	emailAddressDomains: ['liferay.com'],
	groupId: '23',
	project: new Project(data.mockProject())
};

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage>
			<Workspace {...defaultProps} {...props} />
		</WorkspacePage>
	</Provider>
);

describe('Workspace Settting', () => {
	afterEach(cleanup);

	it('should render', () => {
		mockCurrentUser();

		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});

	it('should render as disabled if the user is not an AC admin', () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		const {container, getByDisplayValue, getByLabelText} = render(
			<DefaultComponent
				currentUser={data.getImmutableMock(User, data.mockMemberUser)}
			/>
		);

		expect(getByLabelText(/^workspace name$/i)).toBeDisabled();
		expect(getByDisplayValue(/^oregon, usa$/i)).toBeDisabled();
		expect(
			container.querySelector('.input-list-root input')
		).toBeDisabled();
	});
});
