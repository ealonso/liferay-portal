import * as data from 'test/data';
import AttributeList from '../AttributeList';
import mockStore from 'test/mock-store';
import React from 'react';
import {AttributeTypes} from 'event-analysis/utils/types';
import {MockedProvider} from '@apollo/client/testing';
import {mockEventAttributeDefinitionsReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = ({
	groupId = '23',
	mocks = [
		mockEventAttributeDefinitionsReq(
			[
				data.mockEventAttributeDefinition(0, {
					__typename: 'EventAttributeDefinition',
					dataType: 'STRING'
				})
			],
			{
				keyword: '',
				page: 0,
				size: 2,
				sort: {column: 'name', type: 'ASC'},
				type: AttributeTypes.Local
			}
		)
	]
}) => (
	<Provider store={mockStore()}>
		<WorkspacePage
			channelId='456'
			groupId={groupId}
			params={{tab: 'local'}}
		>
			<MockedProvider addTypename={false} mocks={mocks}>
				<AttributeList />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('AttributeList', () => {
	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should render Data Typecast column with a label', async () => {
		const {getByText} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved();

		expect(getByText('String').closest('.label')).toHaveClass(
			'label-inverse-info'
		);
	});
});
