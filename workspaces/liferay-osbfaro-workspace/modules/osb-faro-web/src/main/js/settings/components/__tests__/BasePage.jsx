import BasePage from '../base-page/BasePage';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {Provider} from 'react-redux';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const mockBreadcrumbItems = [
	{
		active: true,
		href: 'test123',
		label: 'testLabelBreadcrumbItems'
	}
];

const mockPageActions = [
	{
		actions: [{label: 'Test Action'}],
		label: 'testLabelPageActions '
	}
];

describe('BasePage', () => {
	afterEach(cleanup);

	it('should render', () => {
		const {container} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage />
				</WorkspacePage>
			</Provider>
		);

		expect(container).toMatchSnapshot();
	});

	it('should render with a description', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage pageDescription='testPageDescription' />
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('testPageDescription')).toBeTruthy();
	});

	it('should render with a breadcrumb', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage breadcrumbItems={mockBreadcrumbItems} />
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('testLabelBreadcrumbItems')).toBeTruthy();
	});

	it('should render with a page action', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage groupId='23' pageActions={mockPageActions} />
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('testLabelPageActions')).toBeTruthy();
	});

	it('should render with a title', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage groupId='23' pageTitle='testPageTitle' />
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('testPageTitle')).toBeTruthy();
	});

	it('should render with an inline title with subtitle', () => {
		const {getByText} = render(
			<Provider store={mockStore()}>
				<WorkspacePage>
					<BasePage
						groupId='23'
						pageTitle='testPageTitle'
						subTitle='mysubtitle'
					/>
				</WorkspacePage>
			</Provider>
		);

		expect(getByText('testPageTitle').parentElement).toHaveClass(
			'title-text'
		);
		expect(getByText('mysubtitle').parentElement).toBeTruthy();
	});
});
