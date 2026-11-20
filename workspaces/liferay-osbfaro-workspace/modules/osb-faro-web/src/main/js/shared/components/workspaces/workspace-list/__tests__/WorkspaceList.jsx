import * as data from 'test/data';
import React from 'react';
import WorkspaceList from '..';
import {cleanup, render} from '@testing-library/react';
import {DataSourceStates} from 'shared/util/constants';
import {fromJS} from 'immutable';
import {Project} from 'shared/util/records';
import {range, uniqueId} from 'lodash';
import {WorkspacePage} from 'test/mock-page-context';

const FRIENDLY_URL_BASE = '/faro-liferay-';

const mockAccountList = range(3).map(
	i =>
		new Project(
			fromJS(
				data.mockProject(i, {
					groupId: Number(uniqueId()),
					name: `mockProject_AccountA ${i}`,
					url: `/web/workspace-${i}`
				})
			)
		)
);

const mockAccountWithFriendlyURLList = range(2).map(
	i =>
		new Project(
			fromJS(
				data.mockProject(i, {
					corpProjectUuid: null,
					friendlyURL: `${FRIENDLY_URL_BASE}${i}`,
					groupId: 123,
					name: `mockProject_AccountA ${i}`,
					url: `/web${FRIENDLY_URL_BASE}${i}`
				})
			)
		)
);

const mockAccountWithNullStateList = range(2).map(
	i =>
		new Project(
			fromJS(
				data.mockProject(i, {
					corpProjectUuid: null,
					friendlyURL: `${FRIENDLY_URL_BASE}${i}`,
					groupId: null,
					name: `mockProject_AccountA ${i}`,
					state: null
				})
			)
		)
);

const mockAccountWithUnconfiguredList = range(2).map(
	i =>
		new Project(
			fromJS(
				data.mockProject(i, {
					corpProjectUuid: '12345',
					groupId: 123,
					name: `mockProject_AccountA ${i}`,
					state: DataSourceStates.Unconfigured
				})
			)
		)
);

const DefaultComponent = props => (
	<WorkspaceList {...props} />
);

describe('WorkspaceList', () => {
	afterEach(cleanup);
	it('should render', () => {
		const {container} = render(
			<WorkspacePage><DefaultComponent accounts={mockAccountList} /></WorkspacePage>
		);
		expect(container).toMatchSnapshot();
	});

	it('should render WorkspaceList with friendlyURL', () => {
		const {container} = render(
			<WorkspacePage><DefaultComponent accounts={mockAccountWithFriendlyURLList} /></WorkspacePage>
		);
		expect(container).toMatchSnapshot();
	});

	it('should render an unconfigured WorkspaceList', () => {
		const {getAllByText} = render(
			<WorkspacePage><DefaultComponent accounts={mockAccountWithUnconfiguredList} /></WorkspacePage>
		);

		expect(getAllByText(/^finish setting up workspace$/i)).toHaveLength(
			mockAccountWithUnconfiguredList.length
		);
	});

	it('should render an unconfigured WorkspaceList when the state and groupId is null', () => {
		const {getAllByText} = render(
			<WorkspacePage><DefaultComponent accounts={mockAccountWithNullStateList} /></WorkspacePage>
		);

		expect(getAllByText(/^finish setting up workspace$/i)).toHaveLength(
			mockAccountWithNullStateList.length
		);
	});
});
