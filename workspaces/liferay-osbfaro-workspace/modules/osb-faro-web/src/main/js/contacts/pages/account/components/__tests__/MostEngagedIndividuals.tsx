import MostEngagedIndividuals from '../MostEngagedIndividuals';
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

const renderMostEngagedIndividuals = (
	params: {[name: string]: string} = {id: 'acc-1'}
) =>
	render(
		<WorkspacePage channelId="456" groupId="23" params={params}>
			<MostEngagedIndividuals />
		</WorkspacePage>
	);

describe('MostEngagedIndividuals', () => {
	afterEach(cleanup);

	afterAll(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: originalLocation,
		});
	});

	beforeEach(() => {
		jest.clearAllMocks();
		lastDropdownProps = undefined;

		mockLocation();
	});

	it('should render the card title', () => {
		renderMostEngagedIndividuals();

		expect(
			screen.getByText('MOST ENGAGED INDIVIDUALS')
		).toBeInTheDocument();
	});

	it('should render the individuals data set in preview mode', () => {
		renderMostEngagedIndividuals();

		expect(screen.getByTestId('fds-component')).toHaveAttribute(
			'id',
			'most-engaged-individuals-dataset'
		);
	});

	it('should link to the account profile for the full list', () => {
		renderMostEngagedIndividuals();

		expect(screen.getByRole('link', {name: 'View All'})).toHaveAttribute(
			'href',
			'/account-profile?rangeKey=30&id=acc-1'
		);
	});

	it('should carry the selected preset to the full list', () => {
		renderMostEngagedIndividuals({id: 'acc-1', rangeKey: '7'});

		expect(
			screen.getByRole('link', {name: 'View All'}).getAttribute('href')
		).toContain('rangeKey=7');
	});

	it('should carry the custom bounds to the full list', () => {
		renderMostEngagedIndividuals({
			id: 'acc-1',
			rangeEnd: '2026-02-20',
			rangeKey: 'CUSTOM',
			rangeStart: '2026-02-10',
		});

		const href = screen
			.getByRole('link', {name: 'View All'})
			.getAttribute('href');

		expect(href).toContain('rangeEnd=2026-02-20');
		expect(href).toContain('rangeKey=CUSTOM');
		expect(href).toContain('rangeStart=2026-02-10');
	});

	it('should leave the empty bounds off the full list link', () => {
		renderMostEngagedIndividuals({id: 'acc-1', rangeKey: '30'});

		const href = screen
			.getByRole('link', {name: 'View All'})
			.getAttribute('href');

		expect(href).not.toContain('rangeEnd');
		expect(href).not.toContain('rangeStart');
	});

	it('should render the range dropdown', () => {
		renderMostEngagedIndividuals();

		expect(lastDropdownProps).toBeDefined();
	});

	it('should default the range to the last thirty days', () => {
		renderMostEngagedIndividuals();

		expect(lastDropdownProps.rangeSelectors.rangeKey).toBe('30');
	});

	it('should show the range that is on the query string', () => {
		renderMostEngagedIndividuals({id: 'acc-1', rangeKey: '7'});

		expect(lastDropdownProps.rangeSelectors.rangeKey).toBe('7');
	});

	it('should offer the custom range picker', () => {
		renderMostEngagedIndividuals();

		expect(lastDropdownProps.legacy).toBe(false);
	});

	it('should write the selected preset to the query string', () => {
		renderMostEngagedIndividuals();

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

		renderMostEngagedIndividuals({
			id: 'acc-1',
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

	it('should render no link while the account id is missing', () => {
		renderMostEngagedIndividuals({});

		expect(
			screen.queryByRole('link', {name: 'View All'})
		).not.toBeInTheDocument();
	});
});
