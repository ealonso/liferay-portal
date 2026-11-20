import NavigationWarning from '../NavigationWarning';
import React from 'react';
import {act, cleanup, fireEvent, render, screen} from '@testing-library/react';

const dispatchBeforeUnload = () => {
	const event = new Event('beforeunload', {cancelable: true});

	window.dispatchEvent(event);

	return event;
};

const navigateWithSenna = (path) => {
	const [, handleBeforeNavigate] = Liferay.on.mock.calls.find(
		([eventName]) => eventName === 'beforeNavigate'
	);

	const originalEvent = {preventDefault: jest.fn()};

	act(() => handleBeforeNavigate({originalEvent, path}));

	return originalEvent;
};

describe('NavigationWarning', () => {
	afterEach(() => {
		cleanup();

		jest.clearAllMocks();
	});

	it('renders nothing', () => {
		const {container} = render(<NavigationWarning when />);

		expect(container).toBeEmptyDOMElement();
	});

	it('asks to confirm leaving the page while there are unsaved changes', () => {
		render(<NavigationWarning when />);

		expect(dispatchBeforeUnload().defaultPrevented).toBe(true);
	});

	it('lets the user leave the page without unsaved changes', () => {
		render(<NavigationWarning when={false} />);

		expect(dispatchBeforeUnload().defaultPrevented).toBe(false);
	});

	it('stops asking once it is unmounted', () => {
		const {unmount} = render(<NavigationWarning when />);

		unmount();

		expect(dispatchBeforeUnload().defaultPrevented).toBe(false);
	});

	it('stops a page navigation and asks to confirm it while there are unsaved changes', async () => {
		render(<NavigationWarning when />);

		const originalEvent = navigateWithSenna('/web/workspace/events');

		expect(originalEvent.preventDefault).toHaveBeenCalled();
		expect(await screen.findByText('Unsaved Changes')).toBeInTheDocument();
		expect(Liferay.Util.navigate).not.toHaveBeenCalled();
	});

	it('stays on the page when the user chooses to stay', async () => {
		render(<NavigationWarning when />);

		navigateWithSenna('/web/workspace/events');

		fireEvent.click(
			await screen.findByRole('button', {name: 'Stay on Page'})
		);

		expect(Liferay.Util.navigate).not.toHaveBeenCalled();
	});

	it('goes to the requested page when the user chooses to leave', async () => {
		render(<NavigationWarning when />);

		navigateWithSenna('/web/workspace/events');

		fireEvent.click(
			await screen.findByRole('button', {name: 'Leave Page'})
		);

		expect(Liferay.Util.navigate).toHaveBeenCalledWith(
			'/web/workspace/events'
		);
	});

	it('does not listen to page navigations without unsaved changes', () => {
		render(<NavigationWarning when={false} />);

		expect(Liferay.on).not.toHaveBeenCalledWith(
			'beforeNavigate',
			expect.any(Function)
		);
	});
});
