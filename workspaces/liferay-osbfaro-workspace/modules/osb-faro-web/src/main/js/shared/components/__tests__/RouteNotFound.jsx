import React from 'react';
import RouteNotFound from '../RouteNotFound';
import {cleanup, render} from '@testing-library/react';

jest.unmock('react-dom');

describe('RouteNotFound', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<RouteNotFound />
		);
		expect(container).toBeTruthy();
	});
});
