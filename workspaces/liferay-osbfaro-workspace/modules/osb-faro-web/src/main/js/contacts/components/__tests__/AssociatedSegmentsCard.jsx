import * as data from 'test/data';
import AssociatedSegmentsCard from '../AssociatedSegmentsCard';
import NoResultsDisplay from 'shared/components/NoResultsDisplay';
import React from 'react';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const DefaultComponent = props => (
	<AssociatedSegmentsCard groupId='23' id='123' pageUrl='/foo' {...props} />
);

describe('AssociatedSegmentsCard', () => {
	it('should render', async () => {
		const {container} = render(
			<WorkspacePage><DefaultComponent
				dataSourceFn={() =>
					Promise.resolve(data.mockSearch(data.mockSegment, 2))
				}
			/></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should render with an error display', async () => {
		const {container, getByText} = render(
			<WorkspacePage><DefaultComponent dataSourceFn={() => Promise.reject({})} /></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByText(/^an unexpected error occurred\.$/i)).toBeTruthy();
	});

	it('should render with an no results display', async () => {
		const {container} = render(
			<WorkspacePage><DefaultComponent
				dataSourceFn={() =>
					Promise.resolve(data.mockSearch(data.mockSegment, 0))
				}
				noResultsRenderer={() => <NoResultsDisplay />}
			/></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
