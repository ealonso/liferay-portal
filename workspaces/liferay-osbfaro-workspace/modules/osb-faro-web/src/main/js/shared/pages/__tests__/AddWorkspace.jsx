import * as data from 'test/data';
import mockStore from 'test/mock-store';
import React from 'react';
import {AddWorkspace, routingFn} from '../AddWorkspace';
import {cleanup, render} from '@testing-library/react';
import {fromJS} from 'immutable';
import {Project} from 'shared/util/records';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

const mockConfiguredProject = new Project(
	fromJS(data.mockProject(23, {url: '/web/workspace-23'}))
);

const mockUnconfiguredProject = new Project(
	fromJS(data.mockProject(0, {name: ''}))
);

jest.mock('shared/components/workspaces/AddWorkspaceForm', () => () => null);

describe('AddWorkspace', () => {
	afterEach(cleanup);
	it('should render', () => {
		const {getByText} = render(
			<WorkspacePage><Provider store={mockStore()}>
				<AddWorkspace project={mockUnconfiguredProject} />
			</Provider></WorkspacePage>
		);

		expect(getByText(/^create workspace$/i)).toBeInTheDocument();
	});
});

describe('routingFn', () => {
	it('should route the user to the workspace home page if the project is already configred', () => {
		expect(routingFn({project: mockConfiguredProject})).toBe(
			'/web/workspace-23'
		);
	});
});
