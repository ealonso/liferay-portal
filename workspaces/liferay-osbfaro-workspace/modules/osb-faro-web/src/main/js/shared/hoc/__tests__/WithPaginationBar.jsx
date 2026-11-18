import React from 'react';
import withPaginationBar from '../WithPaginationBar';
import {render} from '@testing-library/react';

jest.unmock('react-dom');

describe('withPaginationBar', () => {
	it('renders', () => {
		const WrappedComponent = withPaginationBar({defaultDelta: 10})(() => (
			<div>{'foobar'}</div>
		));

		const {container} = render(
			<WrappedComponent delta={5} page={1} total={15} />
		);

		expect(container).toMatchSnapshot();
	});

	it('renders w/o the pagination bar', () => {
		const WrappedComponent = withPaginationBar({defaultDelta: 10})(() => (
			<div>{'foobar'}</div>
		));

		const {container} = render(<WrappedComponent total={0} />);

		expect(container).toMatchSnapshot();
	});
});
