import React from 'react';
import ReadyToGo from '../ReadyToGo';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {noop} from 'lodash';

jest.unmock('react-dom');

describe('ReadyToGo', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<ReadyToGo groupId='123' onClose={noop} />
		);

		expect(container).toMatchSnapshot();
	});

	it('calls onClose when "Done" is clicked', () => {
		const spy = jest.fn();

		const {queryByText} = render(
			<ReadyToGo groupId='123' onClose={spy} />
		);

		expect(spy).not.toBeCalled();

		fireEvent.click(queryByText('Done'));

		expect(spy).toBeCalled();
	});
});
