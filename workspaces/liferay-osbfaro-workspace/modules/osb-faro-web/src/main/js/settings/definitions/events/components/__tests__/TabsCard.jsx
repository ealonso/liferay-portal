import mockStore from 'test/mock-store';
import React from 'react';
import TabsCard from '../TabsCard';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('TabsCard', () => {
	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage
					navigationItems={[
					{
						active: true,
						href: '/event-definitions',
						label: 'Default Events'
					},
					{
						active: false,
						href: '/event-definitions?tab=custom',
						label: 'Custom Events'
					}
				]}
				>
					<TabsCard />
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
