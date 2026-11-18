import React from 'react';
import withToolbar from '../WithToolbar';
import {render} from '@testing-library/react';

jest.unmock('react-dom');

describe('withToolbar', () => {
	it('renders', () => {
		const WrappedComponent = withToolbar({showRangeDropdownKey: true})(() => (
			<div>{'foobar'}</div>
		));

		const {container} = render(<WrappedComponent />);

		expect(container).toMatchSnapshot();
	});
});
