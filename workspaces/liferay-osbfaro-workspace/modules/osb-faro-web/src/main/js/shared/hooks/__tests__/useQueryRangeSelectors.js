import React from 'react';
import {render} from '@testing-library/react';
import {useQueryRangeSelectors} from 'shared/hooks/useQueryRangeSelectors';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = ({rangeEnd, rangeKey, rangeStart}) => (
	<>
		{`rangeEnd ${rangeEnd}`}

		{`rangeKey: ${rangeKey}`}

		{`rangeStart: ${rangeStart}`}
	</>
);
const WrapperComponent = ({children, params}) => (
	<WorkspacePage params={params}>{children}</WorkspacePage>
);

describe('useQueryRangeSelectors', () => {
	it('should return initial values', () => {
		const TestComponent = () => {
			const queryRangeSelectorsParams = useQueryRangeSelectors({
				rangeEnd: 123123,
				rangeKey: 30,
				rangeStart: 321321,
			});

			return <DefaultComponent {...queryRangeSelectorsParams} />;
		};

		const {container} = render(
			<WrapperComponent>
				<TestComponent />
			</WrapperComponent>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});

	it('should return RangeSelectors params from query params values', () => {
		const TestComponent = () => {
			const queryRangeSelectorsParams = useQueryRangeSelectors({
				rangeEnd: 123123,
				rangeKey: 30,
				rangeStart: 321321,
			});

			return <DefaultComponent {...queryRangeSelectorsParams} />;
		};

		const {container} = render(
			<WrapperComponent
				params={{rangeEnd: '9087', rangeKey: '90', rangeStart: '54546'}}
			>
				<TestComponent />
			</WrapperComponent>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});
});
