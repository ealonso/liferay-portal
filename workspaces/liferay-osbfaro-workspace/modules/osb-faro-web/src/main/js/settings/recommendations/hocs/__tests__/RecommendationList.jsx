import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import RecommendationList from '../RecommendationList';
import RecommendationListQuery from '../../queries/RecommendationListQuery';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockJobBag} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {range} from 'lodash';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

export function mockRecommendationListReq() {
	return {
		request: {
			query: RecommendationListQuery,
			variables: {
				keywords: '',
				size: 10,
				sort: {column: 'name', type: 'DESC'},
				start: 0
			}
		},
		result: {
			data: mockJobBag(range(10).map(i => data.mockRecommendationJob(i)))
		}
	};
}

const defaultProps = {
	router: {params: {groupId: '23'}, query: {delta: '10', page: '1'}}
};

const WrappedComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage params={{delta: '10', field: 'name', sortOrder: 'DESC'}}>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
				mocks={[mockRecommendationListReq()]}
			>
				<RecommendationList
					groupId='23'
					{...defaultProps}
					{...props}
				/>
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('RecommendationList', () => {
	it('should render', async () => {
		const {container} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
