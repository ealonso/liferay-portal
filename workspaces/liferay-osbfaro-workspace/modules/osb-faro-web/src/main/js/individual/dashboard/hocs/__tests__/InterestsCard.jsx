import InterestsCard from '../InterestsCard';
import React from 'react';
import {act, cleanup, render} from '@testing-library/react';
import {
	mockCompositionBag,
	mockIndividualInterestsReq
} from 'test/graphql-data';
import {MockedProvider} from '@apollo/client/testing';
import {omit} from 'lodash';
import {waitFor} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('InterestsCard', () => {
	afterEach(cleanup);

	it('renders', async () => {
		const {getByText} = render(
			<MockedProvider
				mocks={[
					mockIndividualInterestsReq(variables =>
						omit(variables, 'keywords')
					)
				]}
			>
				<WorkspacePage channelId='456' groupId='123'>
					<InterestsCard />
				</WorkspacePage>
			</MockedProvider>
		);

		await act(async () => {
			await jest.advanceTimersByTimeAsync(0);
		});

		await waitFor(() =>
			expect(
				getByText('Top Interests as of Yesterday')
			).toBeInTheDocument()
		);
	});

	it('renders with empty data', async () => {
		const {getByText} = render(
			<MockedProvider
				mocks={[
					mockIndividualInterestsReq(
						variables => omit(variables, 'keywords'),
						{
							data: mockCompositionBag({
								compositionBagName: 'individualInterests',
								compositions: []
							})
						}
					)
				]}
			>
				<WorkspacePage channelId='456' groupId='123'>
					<InterestsCard />
				</WorkspacePage>
			</MockedProvider>
		);

		await act(async () => {
			await jest.advanceTimersByTimeAsync(0);
		});

		await waitFor(() =>
			expect(
				getByText('No interests were found.')
			).toBeInTheDocument()
		);
	});
});
