import AddDataSource from '../AddDataSource';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

jest.unmock('react-dom');

describe('DistributionCard AddDataSource', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {getByRole, getByText} = render(<AddDataSource groupId='123' />);

		expect(
			getByRole('link', {name: Liferay.Language.get('add-data-source')})
		).toHaveAttribute('href', '/data-sources');
		expect(
			getByText(Liferay.Language.get('distribution'))
		).toBeInTheDocument();
	});
});
