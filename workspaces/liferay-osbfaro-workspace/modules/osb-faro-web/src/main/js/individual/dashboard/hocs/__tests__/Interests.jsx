import Interests from '../Interests';
import React from 'react';
import {MockedProvider} from '@apollo/client/testing';
import {mockIndividualInterestsReq} from 'test/graphql-data';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

describe('Interests', () => {
	it('renders', async () => {
		const {getByRole, getByText} = render(
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
				<WorkspacePage channelId='456' groupId='123'>
					<Interests />
				</WorkspacePage>
			</MockedProvider>
		);

		await waitForLoadingToBeRemoved(document.body);

		jest.runAllTimers();

		expect(getByText(/^interest topics$/i)).toBeInTheDocument();
		expect(getByRole('link', {name: 'mesh'})).toHaveAttribute(
			'href',
			'/individuals-interest?interestId=mesh'
		);
	});
});
