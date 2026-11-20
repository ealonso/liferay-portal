import * as pedantic from 'test/pedantic';
import HeaderDefault from '../HeaderDefault';
import React from 'react';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';
import {INTERVAL_KEY_MAP} from 'shared/util/time';
import {MockedProvider} from '@apollo/client/testing';
import {mockPreferenceReq, mockTimeRangeReq} from 'test/graphql-data';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

const DefaultComponent = ({
	mocks = [mockTimeRangeReq(), mockPreferenceReq()],
	onChangeInterval = jest.fn(),
	...props
}) => (
	<MockedProvider addTypename={false} mocks={mocks}>
		<HeaderDefault
			label='Title'
			onChangeInterval={onChangeInterval}
			{...props}
		/>
	</MockedProvider>
);

describe('HeaderDefault', () => {
	beforeEach(() => {
		pedantic.disable();
	});

	afterEach(() => {
		pedantic.enable();
	});

	it('should render', async () => {
		const {container} = render(<WorkspacePage><DefaultComponent /></WorkspacePage>);

		await waitForLoadingToBeRemoved(container);

		expect(container).toMatchSnapshot();
	});

	it('should call the onChangeInterval prop fn with "day" if the rangekey is changed to an hourly value', async () => {
		const spy = jest.fn();
		render(
			<WorkspacePage><DefaultComponent onChangeInterval={spy} rangeKey='LAST_30_DAYS' /></WorkspacePage>
		);

		await waitForLoadingToBeRemoved();

		const dropdownToggle = screen.getByRole('button', {
			name: /select date range/i
		});

		fireEvent.click(dropdownToggle);

		const last24HoursBtn = screen.getByText(/^last 24 hours$/i);

		fireEvent.click(last24HoursBtn);

		await waitFor(() =>
			expect(spy).toHaveBeenCalledWith(INTERVAL_KEY_MAP.day)
		);
	});
});
