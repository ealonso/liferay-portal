import AccountIndividuals from '../AccountIndividuals';
import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('@liferay/frontend-data-set-web', () => ({
	...jest.requireActual('@liferay/frontend-data-set-web'),
	FrontendDataSet: (props: any) => (
		<div data-testid="fds-component" id={props.id} />
	),
}));

let lastDropdownProps: any;

// Mocking the dropdown keeps these off the Apollo query it runs to load the
// range presets; what matters here is the range it is given and the range it
// hands back.

jest.mock('shared/components/dropdown-range-key/DropdownRangeKey', () => ({
	DropdownRangeKey: (props: any) => {
		lastDropdownProps = props;

		return <div data-testid="dropdown-range-key" />;
	},
}));

const mockLocationAssign = jest.fn();

const originalLocation = window.location;

const mockLocation = (href = 'http://localhost/account') => {
	Object.defineProperty(window, 'location', {
		configurable: true,
		value: {...originalLocation, assign: mockLocationAssign, href},
	});
};

const renderAccountIndividuals = (params: {[name: string]: string} = {}) =>
	render(
		<WorkspacePage
			channelId="456"
			groupId="23"
			params={{id: 'acc-1', ...params}}
		>
			<AccountIndividuals />
		</WorkspacePage>
	);

describe('AccountIndividuals', () => {
	beforeEach(() => {
		jest.clearAllMocks();
		lastDropdownProps = undefined;

		mockLocation();
	});

	afterEach(cleanup);

	afterAll(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: originalLocation,
		});
	});

	it('should render the card title', () => {
		renderAccountIndividuals();

		expect(screen.getByText('Account Individuals')).toBeInTheDocument();
	});

	it('should render the range dropdown', () => {
		renderAccountIndividuals();

		expect(lastDropdownProps).toBeDefined();
	});

	it('should default the range to the last thirty days', () => {
		renderAccountIndividuals();

		expect(lastDropdownProps.rangeSelectors.rangeKey).toBe('30');
	});

	it('should show the range that is on the query string', () => {
		renderAccountIndividuals({rangeKey: '7'});

		expect(lastDropdownProps.rangeSelectors.rangeKey).toBe('7');
	});

	it('should offer the custom range picker', () => {
		renderAccountIndividuals();

		expect(lastDropdownProps.legacy).toBe(false);
	});

	it('should write the selected preset to the query string', () => {
		renderAccountIndividuals();

		lastDropdownProps.onRangeSelectorChange({
			rangeEnd: null,
			rangeKey: '90',
			rangeStart: null,
		});

		expect(mockLocationAssign).toHaveBeenCalledWith('/account?rangeKey=90');
	});

	it('should drop the previous bounds when a preset is selected', () => {
		mockLocation(
			'http://localhost/account?rangeEnd=2026-02-20&rangeKey=CUSTOM&rangeStart=2026-02-10'
		);

		renderAccountIndividuals({
			rangeEnd: '2026-02-20',
			rangeKey: 'CUSTOM',
			rangeStart: '2026-02-10',
		});

		lastDropdownProps.onRangeSelectorChange({
			rangeEnd: null,
			rangeKey: '30',
			rangeStart: null,
		});

		const [assignedURL] = mockLocationAssign.mock.calls[0];

		expect(assignedURL).not.toContain('rangeEnd');
		expect(assignedURL).not.toContain('rangeStart');
		expect(assignedURL).toContain('rangeKey=30');
	});

	it('should render the card description', () => {
		renderAccountIndividuals();

		expect(
			screen.getByText(
				'Lists all individuals associated with this account.'
			)
		).toBeInTheDocument();
	});

	it('should render the individuals data set', () => {
		renderAccountIndividuals();

		expect(screen.getByTestId('fds-component')).toHaveAttribute(
			'id',
			'account-individuals-dataset'
		);
	});
});
