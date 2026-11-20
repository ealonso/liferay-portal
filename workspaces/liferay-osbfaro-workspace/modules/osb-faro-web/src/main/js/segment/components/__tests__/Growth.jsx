import * as API from 'shared/api';
import * as data from 'test/data';
import React from 'react';
import SegmentGrowthWithList, {
	SegmentGrowthChart,
	SelectedPointInfo
} from '../Growth';
import {render, screen} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.mock('recharts', () => {
	const OriginalModule = jest.requireActual('recharts');

	return {
		...OriginalModule,
		ResponsiveContainer: ({children}) => (
			<OriginalModule.ResponsiveContainer height={350} width={800}>
				{children}
			</OriginalModule.ResponsiveContainer>
		)
	};
});

describe('SegmentGrowthWithList', () => {
	it('should render', async () => {
		const {container} = render(
			<WorkspacePage><SegmentGrowthWithList
				channelId='123'
				data={[
					{
						added: 1,
						modifiedDate: data.getTimestamp(),
						removed: 3
					}
				]}
				groupId='23'
				id='3'
				onPointSelect={jest.fn()}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(screen.getByText(/^members$/i)).toBeInTheDocument();
	});

	it('requests only known individuals when the segment excludes anonymous users', async () => {
		const {container} = render(
			<WorkspacePage><SegmentGrowthWithList
				channelId='123'
				data={[
					{
						added: 1,
						modifiedDate: data.getTimestamp(),
						removed: 3
					}
				]}
				groupId='23'
				id='3'
				includeAnonymousUsers={false}
				onPointSelect={jest.fn()}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(API.individuals.search).toHaveBeenCalledWith(
			expect.objectContaining({individualTypes: ['KNOWN']})
		);
	});
});

describe('SegmentGrowthChart', () => {
	it('should render', () => {
		render(<WorkspacePage><SegmentGrowthChart data={[]} onPointSelect={jest.fn()} /></WorkspacePage>);

		expect(
			screen.getByText(/^there is no data for segment membership\.$/i)
		).toBeInTheDocument();
	});

	it('renders the selected point tooltip header with the custom date format', () => {
		render(
			<WorkspacePage><SegmentGrowthChart
				data={[
					{
						added: 2,
						anonymousCount: 1,
						knownCount: 4,
						modifiedDate: Date.UTC(2026, 5, 9),
						removed: 0,
						value: 5
					},
					{
						added: 3,
						anonymousCount: 2,
						knownCount: 6,
						modifiedDate: Date.UTC(2026, 5, 10, 14, 30),
						removed: 1,
						value: 8
					}
				]}
				hasSelectedPoint
				selectedPoint={1}
			/></WorkspacePage>
		);

		expect(screen.getByText('As of Jun 10, 2026')).toBeInTheDocument();
	});
});

describe('SelectedPointInfo', () => {
	it('should render', () => {
		render(
			<WorkspacePage><SelectedPointInfo
				data={[
					{
						added: 1,
						modifiedDate: data.getTimestamp(),
						removed: 3
					}
				]}
				hasSelectedPoint
				onClearSelection={jest.fn()}
				selectedPoint={0}
			/></WorkspacePage>
		);

		expect(screen.getByText(/^members$/i)).toBeInTheDocument();
	});
});
