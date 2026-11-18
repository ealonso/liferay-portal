import * as API from 'shared/api';
import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {DataSource} from 'shared/util/records';
import {Delete as DataSourceDelete} from '../Delete';
import {fromJS} from 'immutable';
import {MockedProvider} from '@apollo/client/testing';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const defaultProps = {
	addAlert: jest.fn(),
	dataSource: new DataSource(data.mockLiferayDataSource()),
	deleteDataSource: jest.fn(),
	entitiesCount: {
		2: 10,
		4: 20
	},
	groupId: '23',
	id: '26'
};

const Wrapper = ({children}) => (
	<Provider
		store={mockStore(
			fromJS({
				projects: {
					23: {
						data: {
							timeZone: {
								timeZoneId: 'UTC'
							}
						}
					}
				}
			})
		)}
	>
		<WorkspacePage params={{id: '26'}}>
			<MockedProvider addTypename={false}>{children}</MockedProvider>
		</WorkspacePage>
	</Provider>
);

describe('DataSourceDelete', () => {
	beforeEach(() => {
		API.dataSource.fetchDeletePreview.mockReturnValue(
			Promise.resolve({
				2: 10,
				4: 20
			})
		);
	});

	afterEach(cleanup);

	it('should render', async () => {
		const {container} = render(
			<Wrapper>
				<DataSourceDelete {...defaultProps} />
			</Wrapper>
		);

		// We use DataSourceDelete directly which bypasses the withRequest HOC that sets loading state.
		// So we don't need waitForLoadingToBeRemoved if we pass entitiesCount directly.

		expect(container).toMatchSnapshot();
	});
});
