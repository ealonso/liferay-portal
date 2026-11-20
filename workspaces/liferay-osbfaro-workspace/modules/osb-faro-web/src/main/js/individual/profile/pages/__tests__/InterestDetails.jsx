import * as data from 'test/data';
import InterestDetails from '../InterestDetails';
import React from 'react';
import {Individual} from 'shared/util/records';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const defaultProps = {
	active: 'true',
	groupId: '23',
	id: 'test',
	individual: new Individual(data.mockIndividual()),
	interestId: 1
};

describe('InterestDetails', () => {
	it('should render', async () => {
		const {container, getByRole} = render(
			<WorkspacePage><InterestDetails {...defaultProps} /></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByRole('link', {name: /^back to interests$/i})).toHaveAttribute(
			'href',
			'/individual-interests?id=test'
		);
	});

	it('should render an active pages list tab', async () => {
		const {container, getByText} = render(
			<WorkspacePage><InterestDetails {...defaultProps} /></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByText(/^active pages$/i)).toBeTruthy();
		expect(getByText(/^active pages$/i).parentElement).toHaveClass('active');
		expect(getByText(/^active pages$/i).closest('a')).toHaveAttribute(
			'href',
			'/individual-interest?active=true&id=test&interestId=1'
		);
	});

	it('should render an inactive pages list tab', async () => {
		const {container, getByText} = render(
			<WorkspacePage><InterestDetails {...defaultProps} active='false' /></WorkspacePage>
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByText(/^inactive pages$/i)).toBeTruthy();
		expect(getByText(/^inactive pages$/i).parentElement).toHaveClass('active');
	});
});
