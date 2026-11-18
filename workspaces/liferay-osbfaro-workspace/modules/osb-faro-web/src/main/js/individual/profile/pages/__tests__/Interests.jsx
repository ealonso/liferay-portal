import Interests, {ContributionsCell} from '../Interests';
import React from 'react';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('Interests', () => {
	it('should render', async () => {
		const {getByText} = render(
			<WorkspacePage
				channelId='123'
				groupId='23'
				params={{
					delta: '2',
					field: 'count',
					id: '321321',
					page: '1',
					sortOrder: 'DESC'
				}}
			>
				<Interests channelId='123' groupId='23' id='test' />
			</WorkspacePage>
		);

		await waitForLoadingToBeRemoved(document.body);

		jest.runAllTimers();

		expect(getByText('Contributing Pages')).toBeInTheDocument();
	});
});

describe('ContributionsCell', () => {
	it('should render', () => {
		const {getByText} = render(
			<table>
				<tbody>
					<tr>
						<ContributionsCell data={{relatedPagesCount: 8}} />
					</tr>
				</tbody>
			</table>
		);

		expect(getByText('8 Contributing Pages')).toBeInTheDocument();
	});
});
