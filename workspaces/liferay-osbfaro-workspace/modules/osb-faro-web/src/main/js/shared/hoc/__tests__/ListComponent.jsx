import ListComponent from '../ListComponent';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

jest.unmock('react-dom');

describe('ListComponent', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<ListComponent items={[]} total={0} />
		);

		expect(container).toMatchSnapshot();
	});
});
