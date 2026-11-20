import React from 'react';
import SourceCell from '../Source';
import {cleanup, render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';

describe('SourceCell', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<WorkspacePage><SourceCell
				data={{
					dataSourceId: '456',
					dataSourceName: 'Test Data Source'
				}}
				groupId='123'
			/></WorkspacePage>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render without an href', () => {
		const {container} = render(
			<WorkspacePage><SourceCell
				data={{
					dataSourceName: 'Test Data Source'
				}}
				groupId='123'
			/></WorkspacePage>
		);

		expect(container.querySelector('a')).toBeNull();
	});

	it.each([null, undefined, ''])(
		'should render a %p data source name as no data',
		dataSourceName => {
			render(
				<WorkspacePage><SourceCell data={{dataSourceName}} groupId='123' /></WorkspacePage>
			);

			expect(screen.getByText('–')).toBeInTheDocument();
		}
	);

	it('should render a missing data source name as no data inside the link', () => {
		const {container} = render(
			<WorkspacePage><SourceCell data={{dataSourceId: '456'}} groupId='123' /></WorkspacePage>
		);

		expect(container.querySelector('a')).toHaveTextContent('–');
		expect(screen.getByText('–')).toBeInTheDocument();
	});
});
