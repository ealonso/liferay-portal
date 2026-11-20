import * as API from 'shared/api';
import * as data from 'test/data';
import React from 'react';
import WorkspaceListItem from '../ListItem';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {ProjectStates} from 'shared/util/constants';
import {waitForLoadingToBeRemoved} from 'test/helpers';

describe('WorkspaceListItem', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WorkspaceListItem
				accountName=''
				projectState={ProjectStates.Ready}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render a workspace item as enabled if is unavailable and the user clicks the item to reload and it then changes to available', async () => {
		API.projects.fetch.mockImplementationOnce(() =>
			Promise.resolve(data.mockProject('23'))
		);

		const {container, queryByText} = render(
			<WorkspaceListItem projectState={ProjectStates.Unavailable} />
		);

		const button = queryByText(/^workspace unavailable; click to reload\.$/i);

		expect(button).toBeTruthy();

		fireEvent.click(button);

		jest.runAllTimers();

		await waitForLoadingToBeRemoved(container);

		expect(
			queryByText(/^workspace unavailable; click to reload\.$/i)
		).toBeNull();
	});

	it('should render a workspace with projects you can join', () => {
		const {queryByText} = render(
			<WorkspaceListItem
				isJoinableProjects
				projectState={ProjectStates.Ready}
			/>
		);

		expect(queryByText(/^request access$/i)).toBeTruthy();
	});

	it('should render a workspace with a deactivated project', () => {
		const {queryByText} = render(
			<WorkspaceListItem projectState={ProjectStates.Deactivated} />
		);
		expect(queryByText(/^activate$/i)).toBeTruthy();
	});

	it('should render contact sales and the Analytics Cloud limit message when hasLimitReached and ldpEnabled is false', () => {
		const {getByText} = render(
			<WorkspaceListItem
				hasLimitReached
				projectState={ProjectStates.Ready}
			/>
		);

		expect(
			getByText(
				/^access to analytics cloud has been restricted because your workspace has reached the known individuals or page view limit\. please contact sales to proceed\.$/i
			)
		).toBeTruthy();

		expect(getByText(/^contact sales$/i)).toBeTruthy();
	});

	it('should render the Liferay Data Platform limit message when hasLimitReached and ldpEnabled is true', () => {
		const {getByText} = render(
			<WorkspaceListItem
				hasLimitReached
				ldpEnabled
				projectState={ProjectStates.Ready}
			/>
		);

		expect(
			getByText(
				/^access to liferay data platform has been restricted because your workspace has reached the known individuals or page view limit\. please contact sales to proceed\.$/i
			)
		).toBeTruthy();
	});
});
