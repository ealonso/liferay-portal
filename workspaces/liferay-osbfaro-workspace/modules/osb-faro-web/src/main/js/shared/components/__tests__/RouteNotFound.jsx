import React from 'react';
import RouteNotFound from '../RouteNotFound';
import {cleanup, render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('RouteNotFound', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WorkspacePage><RouteNotFound /></WorkspacePage>
		);
		expect(container).toBeTruthy();
	});
});
