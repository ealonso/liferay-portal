import Dashboard from '../index';
import DataSourcesProvider from 'shared/context/dataSources';
import mockStore from 'test/mock-store';
import React from 'react';
import {ChannelContext} from 'shared/context/channel';
import {cleanup, render} from '@testing-library/react';
import {mockChannelContext} from 'test/mock-channel-context';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const navigationItems = [
	{active: true, href: '/individuals', label: 'Overview'},
	{href: '/known-individuals', label: 'Known Individuals'},
	{href: '/individuals-interests', label: 'Interests'},
	{href: '/individuals-distribution', label: 'Distribution'}
];

describe('Individuals Dashboard', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage
					channelId='456'
					friendlyURL='/individuals'
					groupId='123'
					navigationItems={navigationItems}
				>
					<ChannelContext.Provider value={mockChannelContext()}>
						<DataSourcesProvider groupId='123'>
							<Dashboard />
						</DataSourcesProvider>
					</ChannelContext.Provider>
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
