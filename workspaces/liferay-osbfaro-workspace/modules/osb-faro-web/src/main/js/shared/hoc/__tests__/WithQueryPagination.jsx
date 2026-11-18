import React from 'react';
import withQueryPagination from '../WithQueryPagination';
import {createOrderIOMap, getSortFromOrderIOMap} from 'shared/util/pagination';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const DefaultComponent = ({delta, orderIOMap, page, query}) => {
	const {column, type} = getSortFromOrderIOMap(orderIOMap);

	return (
		<div>
			{delta}

			{column}

			{type}

			{page}

			{query}
		</div>
	);
};
const WrapperComponent = ({children, params}) => (
	<WorkspacePage params={params}>{children}</WorkspacePage>
);

describe('withQueryPagination', () => {
	it('should return initial values', () => {
		const TestComponent = withQueryPagination({
			initialDelta: 6,
			initialOrderIOMap: createOrderIOMap('initial orderIOMap'),
			initialPage: 13,
			initialQuery: 'initial query'
		})(DefaultComponent);

		const {container} = render(
			<WrapperComponent>
				<TestComponent />
			</WrapperComponent>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});

	it('should return pagination params from query params values', () => {
		const TestComponent = withQueryPagination({
			initialDelta: 6,
			initialOrderIOMap: createOrderIOMap('initial orderIOMap'),
			initialPage: 13,
			initialQuery: 'initial query'
		})(DefaultComponent);

		const {container} = render(
			<WrapperComponent
				params={{
					delta: '5',
					field: 'name',
					page: '1',
					query: 'meow',
					sortOrder: 'DESC'
				}}
			>
				<TestComponent />
			</WrapperComponent>
		);

		jest.runAllTimers();

		expect(container).toMatchSnapshot();
	});
});
