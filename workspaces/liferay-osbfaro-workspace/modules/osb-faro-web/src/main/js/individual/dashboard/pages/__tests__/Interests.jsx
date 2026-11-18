import * as useDataSources from 'shared/context/dataSources';
import Interests from '../Interests';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {MockedProvider} from '@apollo/client/testing';
import {mockEmptyState, mockSuccessState} from 'test/__mocks__/mock-objects';
import {mockIndividualInterestsReq} from 'test/graphql-data';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const mockUseDataSource = useDataSources;

const WrappedComponent = () => (
	<MockedProvider
		mocks={[
			mockIndividualInterestsReq(defaultVars => ({
				...defaultVars,
				id: undefined,
				keywords: '',
				size: 2
			}))
		]}
	>
		<Provider store={mockStore()}>
			<WorkspacePage channelId='456' groupId='123'>
				<Interests />
			</WorkspacePage>
		</Provider>
	</MockedProvider>
);

describe('Individuals Dashboard Individuals Interests', () => {
	afterEach(cleanup);

	it('renders', async () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockSuccessState);

		const {getByText} = render(<WrappedComponent />);

		await waitForLoadingToBeRemoved(document.body);

		jest.runAllTimers();

		expect(getByText('Interest Topics')).toBeInTheDocument();
	});

	it('renders with data source empty state', () => {
		mockUseDataSource.useDataSources = jest.fn(() => mockEmptyState);

		const {getByText} = render(<WrappedComponent />);

		expect(
			getByText('No Sites Have Been Synced from Data Sources')
		).toBeInTheDocument();
	});
});
