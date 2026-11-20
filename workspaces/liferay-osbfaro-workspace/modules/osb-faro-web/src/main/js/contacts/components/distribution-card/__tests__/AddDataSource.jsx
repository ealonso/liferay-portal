import AddDataSource from '../AddDataSource';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('DistributionCard AddDataSource', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {getByRole, getByText} = render(<WorkspacePage><AddDataSource groupId='123' /></WorkspacePage>);

		expect(
			getByRole('link', {name: Liferay.Language.get('add-data-source')})
		).toHaveAttribute('href', '/data-sources');
		expect(
			getByText(Liferay.Language.get('distribution'))
		).toBeInTheDocument();
	});
});
