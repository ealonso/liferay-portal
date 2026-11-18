import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import {DataSource} from 'shared/util/records';
import {Edit} from '../Edit';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useRequest', () => ({
	useRequest: jest.fn
}));

const csvProps = {
	groupId: '23',
	id: '23'
};

describe('Edit', () => {
	it('should render a CSV data-source page', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage params={{id: '23'}}>
					<Edit
						{...csvProps}
						dataSource={new DataSource(data.mockCSVDataSource())}
					/>
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('Configure CSV')).toBeInTheDocument();
	});
});
