import Header from '../Header';
import mockStore from 'test/mock-store';
import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {WorkspacePage} from 'test/mock-page-context';
import {Provider} from 'react-redux';

jest.unmock('react-dom');

const WrappedComponent = props => (
	<Provider store={mockStore()}>
		<Header {...props}></Header>
	</Provider>
);

describe('BasePage.Header', () => {
	afterEach(cleanup);

	it('renders Header', () => {
		const {container} = render(
			<WrappedComponent>{'Test Test'}</WrappedComponent>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders Header w/ Breadcrumbs', () => {
		const {container} = render(
			<WrappedComponent
				breadcrumbs={[
					{active: false, label: 'Foo'},
					{active: true, label: 'Bar'}
				]}
			>
				{'Test Test'}
			</WrappedComponent>
		);

		expect(container).toMatchSnapshot();
	});

	describe('NavBar', () => {
		const renderNavBar = navigationItems =>
			render(
				<WorkspacePage navigationItems={navigationItems}>
					<Header.NavBar />
				</WorkspacePage>
			);

		it('renders NavBar', () => {
			const {container} = renderNavBar([
				{
					active: true,
					href: '/account?id=321&rangeKey=30',
					label: 'Test'
				}
			]);

			expect(container).toMatchSnapshot();
		});

		it('renders NavBar with deprecated label', () => {
			const {container} = renderNavBar([
				{
					active: true,
					deprecated: true,
					href: '/account?id=321&rangeKey=30',
					label: 'Test'
				}
			]);

			const deprecatedBadge = document.querySelector(
				'.badge.badge-warning.badge-translucent'
			);

			expect(container).toMatchSnapshot();
			expect(deprecatedBadge).toBeInTheDocument();
			expect(deprecatedBadge).toHaveTextContent('DEPRECATED');
		});

		it('marks the navigation item of the current page as active', () => {
			renderNavBar([
				{href: '/account?id=321', label: 'Overview'},
				{
					active: true,
					href: '/account-individuals?id=321',
					label: 'Individuals'
				}
			]);

			expect(
				screen.getByRole('link', {name: 'Individuals'})
			).toHaveAttribute('aria-current', 'page');
			expect(
				screen.getByRole('link', {name: 'Overview'})
			).not.toHaveAttribute('aria-current');
			expect(
				screen.getByRole('button', {
					name: 'Open Menu, Current Page: Individuals'
				})
			).toBeInTheDocument();
		});
	});

	describe('PageActions', () => {
		it('renders PageActions', () => {
			const {container} = render(
				<Header.PageActions
					actions={[{label: 'Test Action'}]}
					label='Test Label'
				/>
			);

			expect(container).toMatchSnapshot();
		});
	});

	describe('Section', () => {
		it('renders Section', () => {
			const {container} = render(
				<Header.Section>{'Test Test'}</Header.Section>
			);

			expect(container).toMatchSnapshot();
		});
	});

	describe('TitleSection', () => {
		it('renders TitleSection w/ Title', () => {
			const {container} = render(
				<Header.TitleSection>{'Test Test'}</Header.TitleSection>
			);

			expect(container).toMatchSnapshot();
		});

		it('renders TitleSection w/ a sticker before the title', () => {
			const {getByTestId, getByText} = render(
				<Header.TitleSection
					sticker={<span data-testid='sticker' />}
					title='Test Title'
				/>
			);

			expect(
				getByTestId('sticker').compareDocumentPosition(
					getByText('Test Title')
				) & Node.DOCUMENT_POSITION_FOLLOWING
			).toBeTruthy();
		});

		it('renders TitleSection w/ title & no children', () => {
			const {container} = render(
				<Header.TitleSection title='Test Title' />
			);

			expect(container).toMatchSnapshot();
		});

		it('renders TitleSection w/ subtitle', () => {
			const {container} = render(
				<Header.TitleSection subtitle='Test Subtitle'>
					{'Test Test'}
				</Header.TitleSection>
			);

			expect(container).toMatchSnapshot();
		});

		it('renders TitleSection w/ subtitle & label', () => {
			const {container} = render(
				<Header.TitleSection label subtitle='Test Label'>
					{'Test Test'}
				</Header.TitleSection>
			);

			expect(container).toMatchSnapshot();
		});

		it('renders TitleSection w/ label & no subtitle', () => {
			const {container} = render(
				<Header.TitleSection label>{'Test Test'}</Header.TitleSection>
			);

			expect(container).toMatchSnapshot();
			expect(document.querySelector('.label')).toBeNull();
		});
	});
});
