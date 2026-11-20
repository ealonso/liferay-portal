import mockStore from 'test/mock-store';
import React from 'react';
import WorkspacesSuccessDisplay from '../SuccessDisplay';
import {Provider} from 'react-redux';
import {render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('WorkspacesSuccessDisplay', () => {
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<WorkspacesSuccessDisplay url='/web/foo' />
			</Provider></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});

	it('should link to the workspace on the configured faroURL', () => {
		render(
			<WorkspacePage><Provider store={mockStore()}>
				<WorkspacesSuccessDisplay url='/web/foo' />
			</Provider></WorkspacePage>
		);

		expect(
			screen.getByRole('link', {
				name: 'http://localhost:3000/web/foo',
			})
		).toHaveAttribute(
			'href',
			'http://localhost:3000/web/foo'
		);
	});
});
