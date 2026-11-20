import * as data from 'test/data';
import EntityDetailsList from '../EntityDetailsList';
import React from 'react';
import {fireEvent, render} from '@testing-library/react';
import {fromJS} from 'immutable';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const defaultProps = {
	groupId: '23',
	title: 'Test Test'
};

const DefaultComponent = props => (
	<EntityDetailsList {...defaultProps} {...props} />
);

describe('EntityDetailsList', () => {
	it('should render', async () => {
		const {container} = render(
			<WorkspacePage><DefaultComponent
				demographicsIMap={fromJS(data.mockAccountDetails())}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should filter results by query', async () => {
		const {container, getByPlaceholderText} = render(
			<WorkspacePage><DefaultComponent
				demographicsIMap={fromJS(data.mockAccountDetails())}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		fireEvent.change(getByPlaceholderText(/^search$/i), {
			target: {value: 'Agriculture'}
		});

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(
			container.querySelector('.subnav-tbar .tbar-item')
		).toHaveTextContent('1 Result for "Agriculture"');

		expect(container.querySelectorAll('table > tbody').length).toBe(1);
	});
});
