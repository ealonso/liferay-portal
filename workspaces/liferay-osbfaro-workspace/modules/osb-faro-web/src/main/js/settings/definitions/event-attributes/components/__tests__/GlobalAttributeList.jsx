import * as data from 'test/data';
import GlobalAttributeList from '../GlobalAttributeList';
import React from 'react';
import {AttributeTypes} from 'event-analysis/utils/types';
import {MockedProvider} from '@apollo/client/testing';
import {mockEventAttributeDefinitionsReq} from 'test/graphql-data';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const mockGroupId = '23';

const Wrapper = ({children, mocks = []}) => (
	<WorkspacePage groupId={mockGroupId} params={{delta: '1'}}>
		<MockedProvider addTypename={false} mocks={mocks}>
			{children}
		</MockedProvider>
	</WorkspacePage>
);

describe('GlobalAttributeList', () => {
	it('should render', async () => {
		const mocks = [
			mockEventAttributeDefinitionsReq(
				[
					data.mockEventAttributeDefinition(0, {
						__typename: 'EventAttributeDefinition'
					})
				],
				{
					keyword: '',
					page: 0,
					size: 1,
					sort: {column: 'name', type: 'ASC'},
					type: AttributeTypes.Global
				}
			)
		];

		const {container} = render(
			<Wrapper mocks={mocks}>
				<GlobalAttributeList />
			</Wrapper>
		);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});
