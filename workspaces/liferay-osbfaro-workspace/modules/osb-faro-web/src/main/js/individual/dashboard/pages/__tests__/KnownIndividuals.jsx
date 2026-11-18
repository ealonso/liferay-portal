import * as API from 'shared/api';
import * as useDataSources from 'shared/context/dataSources';
import KnownIndividuals from '../KnownIndividuals';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {mockEmptyState, mockSuccessState} from 'test/__mocks__/mock-objects';
import {Provider} from 'react-redux';
import {RangeKeyTimeRanges} from 'shared/util/constants';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const mockUseDataSource = useDataSources;

const WrappedComponent = () => (
	<Provider store={mockStore()}>
		<WorkspacePage channelId='321321' groupId='23'>
			<KnownIndividuals />
		</WorkspacePage>
	</Provider>
);

describe('Individuals Dashboard KnownIndividuals List', () => {
	afterEach(cleanup);

	it('renders', async () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockSuccessState);

		const {container} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(
			container.querySelector(
				'.individuals-dashboard-known-individuals-root'
			)
		).toBeInTheDocument();
	});

	it('renders with data source empty state', () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockEmptyState);

		const {getByText} = render(<WrappedComponent />);

		expect(getByText('No Data Sources Are Connected')).toBeInTheDocument();
	});

	it('searches with the last year range key', async () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockSuccessState);

		const {container} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(API.individuals.search).toHaveBeenCalledWith(
			expect.objectContaining({
				rangeKey: RangeKeyTimeRanges.LastYear,
			})
		);
	});
});
