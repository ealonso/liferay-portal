import ActivatingDisplay from '../ActivatingDisplay';
import mockStore from 'test/mock-store';
import React from 'react';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('ActivatingDisplay', () => {
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<ActivatingDisplay groupId='123123' />
			</Provider></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});
});
