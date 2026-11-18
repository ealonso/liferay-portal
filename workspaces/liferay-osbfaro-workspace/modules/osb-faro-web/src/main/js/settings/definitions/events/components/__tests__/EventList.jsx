import * as data from 'test/data';
import EventList from '../EventList';
import mockStore from 'test/mock-store';
import React from 'react';
import {fireEvent, render} from '@testing-library/react';
import {InMemoryCache} from '@apollo/client';
import {MockedProvider} from '@apollo/client/testing';
import {mockEventDefinitionsReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = ({event, ...otherProps}) => (
	<Provider store={mockStore()}>
		<WorkspacePage params={{delta: '1'}}>
			<MockedProvider
				cache={
					new InMemoryCache({
						addTypename: false,
						freezeResults: false
					})
				}
				mocks={[
					mockEventDefinitionsReq([
						data.mockEventDefinition(0, {
							__typename: 'EventDefinition',
							...event
						})
					])
				]}
			>
				<EventList groupId='23' {...otherProps} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('EventList', () => {
	it('should render', async () => {
		const {container} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should render hide/unhide icon and not move to the right side border of the table when row is clicked', async () => {
		const {container} = render(
			<DefaultComponent
				event={{
					hidden: true
				}}
			/>
		);

		await waitForLoadingToBeRemoved(container);

		const firstTr = container.querySelector('.clickable');

		fireEvent.click(firstTr);

		expect(firstTr.querySelector('.custom-control-input').checked).toBe(
			true
		);

		expect(firstTr.querySelector('.row-actions')).toBeTruthy();
	});
});
