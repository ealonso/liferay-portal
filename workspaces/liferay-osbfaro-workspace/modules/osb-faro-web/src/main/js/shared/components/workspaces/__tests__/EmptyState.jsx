import EmptyState, {CardEmpty} from '../EmptyState';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, fireEvent, render, screen} from '@testing-library/react';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

describe('Workspace Empty State', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<EmptyState />
			</Provider></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});

	it('should open the contact sales modal', async () => {
		render(
			<WorkspacePage><Provider store={mockStore()}>
				<EmptyState />
			</Provider></WorkspacePage>
		);

		expect(screen.queryByRole('dialog')).toBeNull();

		fireEvent.click(screen.getByText(/^contact sales$/i));

		expect(await screen.findByRole('dialog')).toBeInTheDocument();
		expect(
			await screen.findByRole('heading', {name: /^contact sales$/i})
		).toBeInTheDocument();
	});
});

describe('Workspace Empty State - CardEmpty', () => {
	it('should render CardEmpty', () => {
		const {container} = render(
			<WorkspacePage><CardEmpty
				buttonProps={{
					label: 'CardEmpty Button Label'
				}}
				description='CardEmpty Description'
				icon='home'
			/></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render a button with the secondary display', () => {
		const {getByText} = render(
			<WorkspacePage><CardEmpty
				buttonProps={{
					displayType: 'secondary',
					label: 'CardEmpty Button Label'
				}}
				description='CardEmpty Description'
				icon='home'
			/></WorkspacePage>
		);

		expect(
			getByText('CardEmpty Button Label').classList.contains(
				'btn-secondary'
			)
		).toBeTruthy();
	});

	it('should contain a button with onClick', () => {
		let foo = '';

		const {getByText} = render(
			<WorkspacePage><CardEmpty
				buttonProps={{
					displayType: 'secondary',
					label: 'CardEmpty Button Label',
					onClick: () => {
						foo = 'it was clicked';
					}
				}}
				description='CardEmpty Description'
				icon='home'
			/></WorkspacePage>
		);

		fireEvent.click(getByText('CardEmpty Button Label'));

		expect(foo).toEqual('it was clicked');
	});

	it('should contain a button with href', () => {
		const {getByText} = render(
			<WorkspacePage><CardEmpty
				buttonProps={{
					displayType: 'secondary',
					href: '/workspaces',
					label: 'CardEmpty Button Label'
				}}
				description='CardEmpty Description'
				icon='home'
			/></WorkspacePage>
		);

		expect(getByText('CardEmpty Button Label')).toHaveAttribute(
			'href',
			'/workspaces'
		);
	});
});
