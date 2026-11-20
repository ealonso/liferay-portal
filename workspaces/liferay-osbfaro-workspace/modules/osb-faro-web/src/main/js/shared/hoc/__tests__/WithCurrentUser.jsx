import React from 'react';
import withCurrentUser from '../WithCurrentUser';
import {render} from '@testing-library/react';
import {mockCurrentUser, WorkspacePage} from 'test/mock-page-context';

beforeEach(() => mockCurrentUser({name: 'fooUser'}));

const MockComponent = ({className, currentUser}) => (
	<div className={className}>{currentUser.name}</div>
);

describe('WithCurrentUser', () => {
	it('should pass the currentUser to the WrappedComponent', () => {
		const WrappedComponent = withCurrentUser(MockComponent);

		const {getByText} = render(<WorkspacePage><WrappedComponent className='my-class' /></WorkspacePage>);

		expect(getByText('fooUser')).toBeInTheDocument();
		expect(getByText('fooUser')).toHaveClass('my-class');
	});
});
