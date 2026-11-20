import * as data from 'test/data';
import BaseInterestDetails from '../BaseInterestDetails';
import mockDate from 'test/mock-date';
import React from 'react';
import {Account, Segment} from 'shared/util/records';
import {ACCOUNTS, SEGMENTS} from 'shared/util/constants';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';
import {WorkspacePage} from 'test/mock-page-context';

describe('BaseInterestDetails', () => {
	beforeAll(() => mockDate());

	afterAll(() => jest.restoreAllMocks());

	it('should render', async () => {
		const {container, getByText} = render(
			<WorkspacePage><BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='1'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(getByText(/^back to interests$/i)).toBeInTheDocument();
	});

	it('should link the back button and the tabs to the interest pages', () => {
		const {getByRole} = render(
			<WorkspacePage><BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='1'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/></WorkspacePage>
		);

		expect(getByRole('link', {name: 'Back to Interests'})).toHaveAttribute(
			'href',
			'/segment-interests?id=test'
		);
		expect(getByRole('link', {name: 'Active Pages'})).toHaveAttribute(
			'href',
			'/segment-interest?active=true&id=test&interestId=1&tabId=pages'
		);
		expect(getByRole('link', {name: 'Inactive Pages'})).toHaveAttribute(
			'href',
			'/segment-interest?active=false&id=test&interestId=1&tabId=pages'
		);
		expect(getByRole('link', {name: 'Individuals'})).toHaveAttribute(
			'href',
			'/segment-interest?id=test&interestId=1&tabId=individuals'
		);
	});

	it('should render an individuals list tab', () => {
		const {getByText} = render(
			<WorkspacePage><BaseInterestDetails
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='individuals'
				type={ACCOUNTS}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		const individualsGrandparentElement =
			getByText(/^individuals$/i).parentElement?.parentElement;

		expect(getByText(/^individuals$/i)).toBeTruthy();

		expect(individualsGrandparentElement).toHaveClass('active');
	});

	it('should render an active pages list tab', () => {
		const {getByText} = render(
			<WorkspacePage><BaseInterestDetails
				active='true'
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='pages'
				type={ACCOUNTS}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		const activePagesGrandparentElement =
			getByText(/^active pages$/i).parentElement?.parentElement;

		expect(getByText(/^active pages$/i)).toBeTruthy();

		expect(activePagesGrandparentElement).toHaveClass('active');
	});

	it('should render a pages list tab of inactive pages', () => {
		const {getByText} = render(
			<WorkspacePage><BaseInterestDetails
				active='false'
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='pages'
				type={ACCOUNTS}
			/></WorkspacePage>
		);

		jest.runAllTimers();

		const InactivePagesGrandparentElement =
			getByText(/^inactive pages$/i).parentElement?.parentElement;

		expect(getByText(/^inactive pages$/i)).toBeTruthy();

		expect(InactivePagesGrandparentElement).toHaveClass('active');
	});
});

describe('BaseInterestDetails date', () => {
	beforeEach(() => {
		jest.setSystemTime(new Date('2026-06-10T14:30:00Z'));
	});

	it('renders the individuals list title as of the previous day', () => {
		const {container} = render(
			<WorkspacePage><BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='Liferay'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/></WorkspacePage>
		);

		expect(container.querySelector('.list-title')).toHaveTextContent(
			'Members Interested in "Liferay" - As of June 9, 2026'
		);
	});
});
