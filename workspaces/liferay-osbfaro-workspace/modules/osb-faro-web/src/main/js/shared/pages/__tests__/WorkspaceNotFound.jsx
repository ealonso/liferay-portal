import mockStore from 'test/mock-store';
import React from 'react';
import WorkspaceNotFound from '../WorkspaceNotFound';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';

jest.unmock('react-dom');

const renderWithProvider = props => (
	<Provider store={mockStore()}>
		<WorkspaceNotFound {...props} />
	</Provider>
);

describe('WorkspaceNotFound', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(renderWithProvider());

		expect(container).toMatchSnapshot();
	});

	it('should contain the link to go back to the workspaces page', () => {
		const {getByText} = render(renderWithProvider());

		expect(getByText('Go Back to Your Workspaces')).toHaveAttribute(
			'href',
			'/workspaces'
		);
	});

	it('should add a custom classname', () => {
		const {container} = render(
			renderWithProvider({className: 'custom-classname'})
		);

		expect(
			container.querySelector('.workspace-not-found-root').classList
		).toContain('custom-classname');
	});
});
