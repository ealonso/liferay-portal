import * as data from 'test/data';
import BaseDetails from '../BaseDetails';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<BaseDetails
			dataSourceFn={() => Promise.resolve(data.mockAccountDetails())}
			groupId='23'
			id='test'
			{...props}
		/>
	</Provider>
);

describe('BaseDetails', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(<WorkspacePage><DefaultComponent /></WorkspacePage>);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should render w/o loading', () => {
		const {container} = render(<WorkspacePage><DefaultComponent /></WorkspacePage>);

		expect(container.querySelector('.loading-animation')).toBeTruthy();
	});

	it('should render w/ ErrorDisplay', async () => {
		const {container, queryByText} = render(
			<WorkspacePage><DefaultComponent dataSourceFn={() => Promise.reject({})} /></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(queryByText(/^reload$/i)).toBeTruthy();
	});
});
