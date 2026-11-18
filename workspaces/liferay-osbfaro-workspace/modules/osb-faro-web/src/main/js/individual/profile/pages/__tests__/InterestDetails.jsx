import * as data from 'test/data';
import InterestDetails from '../InterestDetails';
import React from 'react';
import {Individual} from 'shared/util/records';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';

const defaultProps = {
	active: 'true',
	groupId: '23',
	id: 'test',
	individual: new Individual(data.mockIndividual()),
	interestId: 1
};

jest.unmock('react-dom');

describe('InterestDetails', () => {
	it('should render', async () => {
		const {container, getByRole} = render(
			<InterestDetails {...defaultProps} />
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByRole('link', {name: 'Back to Interests'})).toHaveAttribute(
			'href',
			'/individual-interests?id=test'
		);
	});

	it('should render an active pages list tab', async () => {
		const {container, getByText} = render(
			<InterestDetails {...defaultProps} />
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByText('Active Pages')).toBeTruthy();
		expect(getByText('Active Pages').parentElement).toHaveClass('active');
		expect(getByText('Active Pages').closest('a')).toHaveAttribute(
			'href',
			'/individual-interest?active=true&id=test&interestId=1'
		);
	});

	it('should render an inactive pages list tab', async () => {
		const {container, getByText} = render(
			<InterestDetails {...defaultProps} active='false' />
		);

		await waitForLoadingToBeRemoved(container);

		expect(getByText('Inactive Pages')).toBeTruthy();
		expect(getByText('Inactive Pages').parentElement).toHaveClass('active');
	});
});
