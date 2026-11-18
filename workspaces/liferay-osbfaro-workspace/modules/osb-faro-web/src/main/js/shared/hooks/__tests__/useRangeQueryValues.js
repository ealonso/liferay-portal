import React from 'react';
import {render} from '@testing-library/react';
import {
	getAPIRangeParams,
	useRangeQueryValues,
} from 'shared/hooks/useRangeQueryValues';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const renderRangeQueryValues = (params) => {
	let rangeQueryValues;

	const TestComponent = () => {
		rangeQueryValues = useRangeQueryValues();

		return null;
	};

	render(
		<WorkspacePage params={params}>
			<TestComponent />
		</WorkspacePage>
	);

	return rangeQueryValues;
};

describe('useRangeQueryValues', () => {
	it('should keep the custom range key on the route params only', () => {
		const {apiRangeParams, routeRangeParams} = renderRangeQueryValues({
			rangeEnd: '2026-02-20',
			rangeKey: 'CUSTOM',
			rangeStart: '2026-02-10',
		});

		expect(apiRangeParams).toEqual({
			rangeEnd: '2026-02-20',
			rangeStart: '2026-02-10',
		});
		expect(routeRangeParams).toEqual({
			rangeEnd: '2026-02-20',
			rangeKey: 'CUSTOM',
			rangeStart: '2026-02-10',
		});
	});

	it('should default to the last 30 days with no empty bounds', () => {
		const {apiRangeParams, rangeSelectors, routeRangeParams} =
			renderRangeQueryValues();

		expect(rangeSelectors.rangeKey).toBe('30');
		expect(apiRangeParams).toEqual({rangeKey: '30'});
		expect(routeRangeParams).toEqual({rangeKey: '30'});
	});
});

describe('getAPIRangeParams', () => {
	it('should drop the custom range key and the empty bounds', () => {
		expect(
			getAPIRangeParams({
				rangeEnd: '2026-02-20',
				rangeKey: 'CUSTOM',
				rangeStart: '2026-02-10',
			})
		).toEqual({rangeEnd: '2026-02-20', rangeStart: '2026-02-10'});
		expect(
			getAPIRangeParams({rangeEnd: null, rangeKey: '7', rangeStart: null})
		).toEqual({rangeKey: '7'});
	});
});
