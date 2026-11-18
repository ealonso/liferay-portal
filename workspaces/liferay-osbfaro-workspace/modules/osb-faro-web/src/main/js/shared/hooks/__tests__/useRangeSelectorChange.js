import React from 'react';
import {render} from '@testing-library/react';
import {useRangeSelectorChange} from 'shared/hooks/useRangeSelectorChange';

jest.unmock('react-dom');

describe('useRangeSelectorChange', () => {
	const {location} = window;

	afterEach(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: location,
		});
	});

	it('should drop the custom bounds when a preset is selected', () => {
		const assign = jest.fn();

		Object.defineProperty(window, 'location', {
			configurable: true,
			value: {
				assign,
				href: 'http://localhost/?rangeEnd=2026-02-20&rangeKey=CUSTOM&rangeStart=2026-02-10',
			},
		});

		let handleRangeSelectorChange;

		const TestComponent = () => {
			({handleRangeSelectorChange} = useRangeSelectorChange());

			return null;
		};

		render(<TestComponent />);

		handleRangeSelectorChange({
			rangeEnd: null,
			rangeKey: '90',
			rangeStart: null,
		});

		expect(assign).toHaveBeenCalledWith('/?rangeKey=90');
	});
});
