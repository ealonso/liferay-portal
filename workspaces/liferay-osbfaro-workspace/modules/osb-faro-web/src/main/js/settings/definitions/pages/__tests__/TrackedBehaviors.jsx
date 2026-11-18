import mockStore from 'test/mock-store';
import React from 'react';
import ReactDOM from 'react-dom';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {TrackedBehaviors} from '../TrackedBehaviors';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

ReactDOM.createPortal = jest.fn();

describe('TrackedBehaviorsList', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<TrackedBehaviors groupId='23' />
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
