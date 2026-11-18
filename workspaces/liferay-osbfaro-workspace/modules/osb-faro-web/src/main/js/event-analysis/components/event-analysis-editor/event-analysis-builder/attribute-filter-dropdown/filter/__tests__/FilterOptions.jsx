import FilterOptions from '../index';
import mockStore from 'test/mock-store';
import React from 'react';
import {AttributesProvider} from '../../../../context/attributes';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = props => (
	<Provider store={mockStore()}>
		<WorkspacePage channelId='456' groupId='123' params={{id: '789'}}>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
			>
				<AttributesProvider>
					<FilterOptions
						attribute={{
							dataType: 'STRING',
							displayName: 'Filed Ticket',
							id: '4',
							name: 'filedTicket'
						}}
						onActiveChange={jest.fn()}
						onAttributeChange={jest.fn()}
						onEditClick={jest.fn()}
						{...props}
					/>
				</AttributesProvider>
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('FilterOptions', () => {
	it('should render', () => {
		const {container} = render(<DefaultComponent />);

		expect(container).toMatchSnapshot();
	});
});
