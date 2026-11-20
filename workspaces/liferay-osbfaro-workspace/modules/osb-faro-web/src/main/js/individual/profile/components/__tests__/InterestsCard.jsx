import * as data from 'test/data';
import InterestsCard, {InterestsList} from '../InterestsCard';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {mockSegment} from 'test/data';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

describe('InterestsCard', () => {
	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(
			<WorkspacePage><InterestsCard entity={mockSegment(15)} groupId='23' /></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});
});

describe('InterestsList', () => {
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><InterestsList
				groupId='23'
				id='foo'
				interests={[data.mockInterestData()]}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});
});
