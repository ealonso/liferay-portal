import mockStore from 'test/mock-store';
import PreferenceQuery from 'shared/queries/PreferenceQuery';
import React from 'react';
import {DATA_RETENTION_PERIOD_KEY} from 'shared/util/constants';
import {fireEvent, render} from '@testing-library/react';
import {MockedProvider} from '@apollo/client/testing';
import {Overview} from '../Overview';
import {Provider} from 'react-redux';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

const mockPreferenceReq = (value = '18144000000') => ({
	request: {
		query: PreferenceQuery,
		variables: {
			key: DATA_RETENTION_PERIOD_KEY
		}
	},
	result: {
		data: {
			preference: {
				__typename: 'Preference',
				key: DATA_RETENTION_PERIOD_KEY,
				value
			}
		}
	}
});

const DefaultComponent = ({mocks = [mockPreferenceReq()], ...props}) => (
	<Provider store={mockStore()}>
		<WorkspacePage>
			<MockedProvider mocks={mocks}>
				<Overview groupId='23' {...props} />
			</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('Data Privacy Overview', () => {
	it('should render', async () => {
		mockCurrentUser();

		const {container, getByText} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		fireEvent.click(getByText('Select an option'));

		expect(getByText('7 Months')).toBeTruthy();
		expect(getByText('13 Months')).toBeTruthy();
		expect(container).toMatchSnapshot();
	});

	it('should render with disabled buttons in the Suppressed Users section if the user is not an AC admin', async () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		const {container, getByTestId} = render(<DefaultComponent />);

		await waitForLoadingToBeRemoved(container);

		expect(getByTestId('export-suppressed-user-button')).toBeDisabled();
		expect(
			getByTestId('data-retention-period-select-input')
		).toBeDisabled();
	});
});
