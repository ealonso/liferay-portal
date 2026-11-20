import client from 'shared/apollo/client';
import mockStore from 'test/mock-store';
import React from 'react';
import RecommendationStepCard from '../index';
import {ApolloProvider} from '@apollo/client';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('RecommendationStepCard', () => {
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><ApolloProvider client={client}>
				<Provider store={mockStore()}>
					<RecommendationStepCard
						router={{params: {groupId: '123'}}}
					/>
				</Provider>
			</ApolloProvider></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});
});
