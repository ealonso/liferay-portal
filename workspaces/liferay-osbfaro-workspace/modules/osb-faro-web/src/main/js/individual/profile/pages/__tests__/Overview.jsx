import * as data from 'test/data';
import mockStore from 'test/mock-store';
import Overview from '../Overview';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {Individual} from 'shared/util/records';
import {MockedProvider} from '@apollo/client/testing';
import {
	mockEventMetrics,
	mockPreferenceReq,
	mockSessions,
	mockTimeRangeReq
} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const variables = {channelId: undefined};

describe('IndividualOverview', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container, getByText} = render(
			<WorkspacePage><MockedProvider
				mocks={[
					mockEventMetrics(variables),
					mockTimeRangeReq(),
					mockPreferenceReq(),
					mockSessions(variables)
				]}
			>
				<Provider store={mockStore()}>
					<Overview
						groupId='23'
						id='test'
						individual={data.getImmutableMock(
							Individual,
							data.mockIndividual
						)}
					/>
				</Provider>
			</MockedProvider></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(getByText(/^view all details$/i)).toBeInTheDocument();
	});
});
