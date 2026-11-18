import * as data from 'test/data';
import BaseInterestDetails from '../BaseInterestDetails';
import mockDate from 'test/mock-date';
import React from 'react';
import {Account, Segment} from 'shared/util/records';
import {ACCOUNTS, SEGMENTS} from 'shared/util/constants';
import {render} from '@testing-library/react';
import {waitForLoadingToBeRemoved} from 'test/helpers';

jest.unmock('react-dom');

describe('BaseInterestDetails', () => {
	beforeAll(() => mockDate());

	afterAll(() => jest.restoreAllMocks());

	it('should render', async () => {
		const {container, getByText} = render(
			<BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='1'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/>
		);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(getByText('Back to Interests')).toBeInTheDocument();
	});

	it('should link the back button and the tabs to the interest pages', () => {
		const {getByRole} = render(
			<BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='1'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/>
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
			<BaseInterestDetails
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='individuals'
				type={ACCOUNTS}
			/>
		);

		jest.runAllTimers();

		const individualsGrandparentElement =
			getByText('Individuals').parentElement?.parentElement;

		expect(getByText('Individuals')).toBeTruthy();

		expect(individualsGrandparentElement).toHaveClass('active');
	});

	it('should render an active pages list tab', () => {
		const {getByText} = render(
			<BaseInterestDetails
				active='true'
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='pages'
				type={ACCOUNTS}
			/>
		);

		jest.runAllTimers();

		const activePagesGrandparentElement =
			getByText('Active Pages').parentElement?.parentElement;

		expect(getByText('Active Pages')).toBeTruthy();

		expect(activePagesGrandparentElement).toHaveClass('active');
	});

	it('should render a pages list tab of inactive pages', () => {
		const {getByText} = render(
			<BaseInterestDetails
				active='false'
				entity={new Account(data.mockAccount())}
				groupId='23'
				id='test'
				interestDetailsFriendlyURL='/account-interest'
				interestId='1'
				interestsFriendlyURL='/account-interests'
				tabId='pages'
				type={ACCOUNTS}
			/>
		);

		jest.runAllTimers();

		const InactivePagesGrandparentElement =
			getByText('Inactive Pages').parentElement?.parentElement;

		expect(getByText('Inactive Pages')).toBeTruthy();

		expect(InactivePagesGrandparentElement).toHaveClass('active');
	});
});

describe('BaseInterestDetails date', () => {
	beforeEach(() => {
		jest.setSystemTime(new Date('2026-06-10T14:30:00Z'));
	});

	it('renders the individuals list title as of the previous day', () => {
		const {container} = render(
			<BaseInterestDetails
				channelId='123'
				entity={new Segment(data.mockSegment())}
				groupId='23'
				id='test'
				interestDetailsURL='/segment-interest'
				interestId='Liferay'
				interestsURL='/segment-interests'
				tabId='individuals'
				type={SEGMENTS}
			/>
		);

		expect(container.querySelector('.list-title')).toHaveTextContent(
			'Members Interested in "Liferay" - As of June 9, 2026'
		);
	});
});
