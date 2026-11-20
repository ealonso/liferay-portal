import InterestPagesList from '../InterestPagesList';
import React from 'react';
import {render, waitFor} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

/**
 * For ActivePagesList (active: true) validation, It's possible to order by
 * unique visits count, whereas for InactivePagesList (active: false)
 * it's not, that's why we're validating by the presence
 * of the order button on those tests.
 */
describe('InterestPagesList', () => {
	it('should render', async () => {
		const {container} = render(
			<WorkspacePage><InterestPagesList dataSourceParams={{active: true}} /></WorkspacePage>
		);

		await waitFor(() => {});

		expect(
			container.querySelector('.searchable-table-root')
		).toBeInTheDocument();
	});
});
