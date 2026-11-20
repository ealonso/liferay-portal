import mockStore from 'test/mock-store';
import React from 'react';
import WorkspaceNotFound from '../WorkspaceNotFound';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

const renderWithProvider = (props, pageConfig) => (
	<WorkspacePage {...pageConfig}>
		<Provider store={mockStore()}>
			<WorkspaceNotFound {...props} />
		</Provider>
	</WorkspacePage>
);

describe('WorkspaceNotFound', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(renderWithProvider());

		expect(container).toMatchSnapshot();
	});

	it('should contain the link to go back to the workspaces page', () => {
		const {getByText} = render(renderWithProvider());

		expect(getByText(/^go back to your workspaces$/i)).toHaveAttribute(
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

	it('should render the Analytics Cloud brand', () => {
		const {getByText, queryByText} = render(renderWithProvider());

		expect(getByText('Analytics Cloud')).toBeInTheDocument();
		expect(queryByText('Liferay Data Platform')).toBeNull();
	});

	it('should render the Liferay Data Platform brand when LDP is enabled', () => {
		const {getByText, queryByText} = render(
			renderWithProvider({}, {ldpEnabled: true})
		);

		expect(getByText('Liferay Data Platform')).toBeInTheDocument();
		expect(queryByText('Analytics Cloud')).toBeNull();
	});
});
