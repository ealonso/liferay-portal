import React from 'react';
import Welcome from '../Welcome';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {noop} from 'lodash';
import {WorkspacePage} from 'test/mock-page-context';

describe('Welcome', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(<WorkspacePage><Welcome onClose={noop} onNext={noop} /></WorkspacePage>);

		expect(container).toMatchSnapshot();
	});

	it('calls onNext when "Next" is clicked', () => {
		const spy = jest.fn();

		const {queryByText} = render(<WorkspacePage><Welcome onClose={noop} onNext={spy} /></WorkspacePage>);

		expect(spy).not.toBeCalled();

		fireEvent.click(queryByText(/^next$/i));

		expect(spy).toBeCalled();
	});

	it('shows the Analytics Cloud welcome message for a non-LDP workspace', () => {
		const {queryByText} = render(<WorkspacePage><Welcome onClose={noop} onNext={noop} /></WorkspacePage>);

		expect(queryByText(/^welcome to analytics cloud$/i)).toBeTruthy();
	});

	it('shows the Liferay Data Platform welcome message for an LDP workspace', () => {
		const {queryByText} = render(<WorkspacePage ldpEnabled><Welcome onClose={noop} onNext={noop} /></WorkspacePage>);

		expect(queryByText(/^welcome to liferay data platform$/i)).toBeTruthy();
	});
});
