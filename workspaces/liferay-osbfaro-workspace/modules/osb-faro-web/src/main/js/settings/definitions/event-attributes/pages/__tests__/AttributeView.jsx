import * as data from 'test/data';
import AttributeView from '../AttributeView';
import client from 'shared/apollo/client';
import mockStore from 'test/mock-store';
import React from 'react';
import {act, render} from '@testing-library/react';
import {ApolloProvider} from '@apollo/client';
import {getISODate} from 'shared/util/date';
import {MockedProvider} from '@apollo/client/testing';
import {mockEventAttributeDefinitionWithRecentValuesReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {waitForLoading} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const Wrapper = ({children, recentValue}) => (
	<WorkspacePage params={{attributeId: '0'}}>
		<ApolloProvider client={client}>
			<Provider store={mockStore()}>
				<MockedProvider
					mocks={[
						mockEventAttributeDefinitionWithRecentValuesReq(
							data.mockEventAttributeDefinition(0, {
								recentValues: [
									{
										__typename: 'EventAttributeValue',
										lastSeenDate: getISODate(
											data.getTimestamp()
										),
										value: 'RecentValue',
										...recentValue
									}
								]
							}),
							{id: '0'}
						)
					]}
				>
					{children}
				</MockedProvider>
			</Provider>
		</ApolloProvider>
	</WorkspacePage>
);

describe('AttributeView', () => {
	it('should render', async () => {
		const {container} = render(
			<Wrapper>
				<AttributeView attributeId='0' groupId='23' />
			</Wrapper>
		);

		await waitForLoading(container);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});

	it('should render with a table', async () => {
		const {getByText} = render(
			<Wrapper>
				<AttributeView attributeId='0' groupId='23' />
			</Wrapper>
		);

		await act(async () => {
			jest.runOnlyPendingTimers();
		});

		expect(getByText('Sample Raw Data')).toBeTruthy();
	});

	it('should render table with empty data if there is no column accessor value', async () => {
		const {getByText} = render(
			<Wrapper recentValue={{value: ''}}>
				<AttributeView attributeId='0' groupId='23' />
			</Wrapper>
		);

		await act(async () => {
			jest.runOnlyPendingTimers();
		});

		expect(getByText('–')).toBeTruthy();
	});
});
