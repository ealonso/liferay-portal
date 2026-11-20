import CampaignsEmptyState from '../CampaignsEmptyState';
import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {useDataSources} from 'shared/context/dataSources';
import {WorkspacePage} from 'test/mock-page-context';

jest.mock('shared/context/dataSources', () => ({useDataSources: jest.fn()}));


describe('CampaignsEmptyState', () => {
	afterEach(cleanup);

	beforeEach(() => {
		useDataSources.mockReturnValue({empty: false, loading: false});
	});

	it('should point to connecting a data source when none is connected', () => {
		useDataSources.mockReturnValue({empty: true, loading: false});

		render(
			<WorkspacePage>
				<CampaignsEmptyState />
			</WorkspacePage>
		);

		expect(
			screen.getByText(/no campaign data has been synced/i)
		).toBeInTheDocument();
		expect(
			screen.getByText(/connect a data source containing campaign data/i)
		).toBeInTheDocument();
	});

	it('should tell that no campaign was synced when a data source is connected', () => {
		render(
			<WorkspacePage>
				<CampaignsEmptyState />
			</WorkspacePage>
		);

		expect(screen.getByText(/no campaigns were found/i)).toBeInTheDocument();
		expect(
			screen.getByText(
				/no campaigns were synced from the connected data sources/i
			)
		).toBeInTheDocument();
	});

	it('should wait for the data sources before telling why it is empty', () => {
		useDataSources.mockReturnValue({empty: false, loading: true});

		render(
			<WorkspacePage>
				<CampaignsEmptyState />
			</WorkspacePage>
		);

		expect(
			screen.queryByText(/no campaigns were found/i)
		).not.toBeInTheDocument();
		expect(
			screen.queryByText(/no campaign data has been synced/i)
		).not.toBeInTheDocument();
	});
});
