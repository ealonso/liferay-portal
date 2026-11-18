import NavigationWarning from '../NavigationWarning';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

jest.unmock('react-dom');

const dispatchBeforeUnload = () => {
	const event = new Event('beforeunload', {cancelable: true});

	window.dispatchEvent(event);

	return event;
};

describe('NavigationWarning', () => {
	afterEach(cleanup);

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
});
