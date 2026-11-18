import DistributionCard from '../index';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {fetchDistributionTabs} from 'shared/actions/preferences';
import {Provider} from 'react-redux';

jest.mock('shared/actions/preferences');

jest.unmock('react-dom');

describe('DistributionCard', () => {
	afterEach(() => {
		fetchDistributionTabs.mockClear();

		cleanup();
	});

	it('renders', () => {
		fetchDistributionTabs.mockReturnValue(() => Promise.resolve({}));

		const {getByText} = render(
			<Provider store={mockStore()}>
				<DistributionCard
					distributionKey='individualsDashboard'
					fetchDistribution={() => Promise.resolve()}
				/>
			</Provider>
		);

		expect(
			getByText(Liferay.Language.get('explore-breakdown'))
		).toBeInTheDocument();
	});

	it('renders empty state via props with noResultsRenderer', () => {
		fetchDistributionTabs.mockReturnValue(() => Promise.resolve({}));

		const {getByText} = render(
			<Provider store={mockStore()}>
				<DistributionCard
					distributionKey='individualsDashboard'
					fetchDistribution={() => Promise.resolve()}
					noResultsRenderer={() => <div>{'empty state'}</div>}
				/>
			</Provider>
		);

		expect(getByText('empty state')).toBeInTheDocument();
	});

	it('renders w/ showAddDataSource', () => {
		fetchDistributionTabs.mockReturnValue(() => Promise.resolve({}));

		const {getByText} = render(
			<Provider store={mockStore()}>
				<DistributionCard
					distributionKey='individualsDashboard'
					fetchDistribution={() => Promise.resolve()}
					groupId='123'
					showAddDataSource
				/>
			</Provider>
		);

		expect(
			getByText(Liferay.Language.get('add-data-source'))
		).toBeInTheDocument();
		expect(
			getByText(Liferay.Language.get('distribution'))
		).toBeInTheDocument();
	});

	it('renders /w AddPropertyForm', () => {
		fetchDistributionTabs.mockReturnValue(() => Promise.resolve({}));

		const {getByText} = render(
			<Provider store={mockStore()}>
				<DistributionCard
					distributionKey='123'
					fetchDistribution={() => Promise.resolve()}
					id='123'
				/>
			</Provider>
		);

		expect(
			getByText(
				Liferay.Language.get('add-a-breakdown-by-individual-attribute')
			)
		).toBeInTheDocument();
		expect(getByText(Liferay.Language.get('save'))).toBeInTheDocument();
	});
});
