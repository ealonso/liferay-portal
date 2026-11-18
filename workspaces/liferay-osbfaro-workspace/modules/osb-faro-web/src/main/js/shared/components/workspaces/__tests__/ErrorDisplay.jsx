import mockStore from 'test/mock-store';
import React from 'react';
import WorkspacesErrorDisplay from '../ErrorDisplay';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {User} from 'shared/util/records';

jest.unmock('react-dom');

describe('WorkspacesErrorDisplay', () => {
	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacesErrorDisplay
					currentUser={
						new User({emailAddress: 'test@liferay.com'})
					}
				/>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
