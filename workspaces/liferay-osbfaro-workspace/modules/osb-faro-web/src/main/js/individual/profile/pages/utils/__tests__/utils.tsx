import {buildHeaderSubtitle} from '../utils';
import {cleanup, render} from '@testing-library/react';
import {mockPageConfig} from 'test/mock-page-context';

const config = mockPageConfig();

describe('buildHeaderSubtitle', () => {
	afterEach(cleanup);

	it('links the account name to its account page when an account id exists', () => {
		const individual = {
			accountName: 'Acme Corporation',
			accounts: [{accountName: 'Acme Corporation', id: 'acc-1'}],
			lastSessionCountry: 'Brazil',
			properties: {email: 'jane@acme.com'},
		};

		const {getByRole} = render(buildHeaderSubtitle(individual, config));

		expect(getByRole('link', {name: 'Acme Corporation'})).toHaveAttribute(
			'href',
			'/account?id=acc-1'
		);
	});

	it('renders the account name as plain text when there is no account id', () => {
		const individual = {
			accountName: 'Acme Corporation',
			accounts: [],
			lastSessionCountry: 'Brazil',
			properties: {email: 'jane@acme.com'},
		};

		const {container, queryByRole} = render(
			buildHeaderSubtitle(individual, config)
		);

		expect(queryByRole('link')).toBeNull();
		expect(container.textContent).toContain('Acme Corporation');
	});
});
