import mockStore from 'test/mock-store';
import React from 'react';
import WorkspacesSuccessDisplay from '../SuccessDisplay';
import {Provider} from 'react-redux';
import {render, screen} from '@testing-library/react';

jest.unmock('react-dom');

describe('WorkspacesSuccessDisplay', () => {
	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacesSuccessDisplay url='/web/foo' />
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});

	it('should link to the workspace on the configured faroURL', () => {
		render(
			<Provider store={mockStore()}>
				<WorkspacesSuccessDisplay url='/web/foo' />
			</Provider>
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
