import Events from '../EventAttributes';
import mockStore from 'test/mock-store';
import React from 'react';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('EventAttributes', () => {
	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage
					navigationItems={[
					{
						active: true,
						href: '/event-attributes',
						label: 'Global Attributes'
					},
					{
						active: false,
						href: '/event-attributes?tab=local',
						label: 'Attributes'
					}
				]}
				>
					<Events />
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
