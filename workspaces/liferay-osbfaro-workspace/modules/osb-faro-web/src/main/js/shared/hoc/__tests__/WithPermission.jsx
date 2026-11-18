jest.mock(
	'shared/hoc/WithAction',
	() => () => wrappedComponent => wrappedComponent
);

import * as data from 'test/data';
import FaroConstants from 'shared/util/constants';
import React from 'react';
import withPermission, {withAdminPermission} from '../WithPermission';
import {render} from '@testing-library/react';
import {User} from 'shared/util/records';

const {userRoleNames} = FaroConstants;

const mockUser = data.getImmutableMock(User, data.mockUser);

jest.unmock('react-dom');

jest.mock('shared/hooks/useCurrentUser', () => ({
	useCurrentUser: () => mockUser
}));

describe('withPermission', () => {
	it('should render an error page', () => {
		const Component = withPermission(['foo'])(() => <h1>{'Foobar'}</h1>);

		const {container} = render(<Component />);

		expect(container).toMatchSnapshot();
	});

	it('should render the wrapped component', () => {
		const Component = withPermission([userRoleNames.owner])(() => (
			<h1>{'Foobar'}</h1>
		));

		const {container} = render(<Component />);

		expect(container).toMatchSnapshot();
	});
});

describe('withAdminPermission', () => {
	it('should render the wrapped component', () => {
		const Component = withAdminPermission(() => <h1>{'Foobar'}</h1>);

		const {container} = render(<Component />);

		expect(container).toMatchSnapshot();
	});
});
