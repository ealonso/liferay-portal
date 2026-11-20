import * as data from 'test/data';
import AssociatedSegments from '../AssociatedSegments';
import mockStore from 'test/mock-store';
import React from 'react';
import {Individual} from 'shared/util/records';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

describe('IndividualAssociatedSegments', () => {
	it('should render', async () => {
		const {container, getByText} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<AssociatedSegments
					groupId='23'
					id='test'
					individual={data.getImmutableMock(
						Individual,
						data.mockIndividual
					)}
				/>
			</Provider></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		jest.runAllTimers();

		expect(getByText(/^associated segments$/i)).toBeInTheDocument();
	});
});
