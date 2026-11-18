import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import View from '../View';
import {MockedProvider} from '@apollo/client/testing';
import {
	mockRecommendationJobRunsReq,
	mockRecommendationReq
} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({
		timeZoneId: 'UTC'
	})
}));

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage groupId='123' params={{jobId: '321'}}>
			<MockedProvider
				mocks={[
					mockRecommendationJobRunsReq([
						data.mockRecommendationJobRun(0)
					]),
					mockRecommendationReq(
						data.mockRecommendationJob('321', {
							nextRunDate: new Date().getTime()
						})
					)
				]}
			>
				<View
					router={{params: {groupId: '123', jobId: '321'}}}
					{...props}
				/>
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('View', () => {
	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
