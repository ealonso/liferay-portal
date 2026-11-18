import ConfigureCSV from '../ConfigureCSV';
import mockStore from 'test/mock-store';
import React from 'react';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

describe('ConfigureCSV', () => {
	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage channelId='456' groupId='2000'>
					<ConfigureCSV groupId='23' id='123' />
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});
});
