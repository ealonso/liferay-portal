import ErrorPage from '../ErrorPage';
import React from 'react';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

const DefaultComponent = props => (
	<ErrorPage {...props} />
);

describe('ErrorPage', () => {
	it('should render', () => {
		const {container} = render(<WorkspacePage><DefaultComponent /></WorkspacePage>);

		expect(container).toMatchSnapshot();
	});

	it('should render a custom message', () => {
		const {getByText} = render(<WorkspacePage><DefaultComponent message='foo bar' /></WorkspacePage>);

		expect(getByText('foo bar')).toBeTruthy();
	});
});
