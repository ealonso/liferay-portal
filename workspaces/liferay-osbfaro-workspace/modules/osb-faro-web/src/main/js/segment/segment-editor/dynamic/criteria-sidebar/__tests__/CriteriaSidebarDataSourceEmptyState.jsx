import CriteriaSidebarDataSourceEmptyState from '../CriteriaSidebarDataSourceEmptyState';
import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {mockCurrentUser, WorkspacePage} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

const EmptyState = () => (
	<WorkspacePage>
		<CriteriaSidebarDataSourceEmptyState
			description='Connect a data source.'
			title='No Data Has Been Synced'
		/>
	</WorkspacePage>
);

describe('CriteriaSidebarDataSourceEmptyState', () => {
	afterEach(cleanup);

	it('should offer an administrator to connect a data source', () => {
		mockCurrentUser();

		render(<EmptyState />);

		expect(
			screen.getByRole('link', {name: /learn more about data sources/i})
		).toBeInTheDocument();
		expect(
			screen.getByRole('link', {name: /^connect data source$/i})
		).toHaveAttribute('href', '/data-sources');
	});

	it('should not offer to connect a data source to a user who cannot', () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		render(<EmptyState />);

		expect(
			screen.getByRole('link', {name: /learn more about data sources/i})
		).toBeInTheDocument();
		expect(
			screen.queryByRole('link', {name: /^connect data source$/i})
		).not.toBeInTheDocument();
	});
});
