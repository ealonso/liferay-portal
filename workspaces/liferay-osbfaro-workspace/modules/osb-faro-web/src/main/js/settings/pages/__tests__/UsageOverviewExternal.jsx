import * as data from 'test/data';
import mockStore, {mockStoreDataLDP} from 'test/mock-store';
import React from 'react';
import UsageOverviewExternal from '../UsageOverviewExternal';
import {fromJS} from 'immutable';
import {Project} from 'shared/util/records';
import {Provider} from 'react-redux';
import {render} from '@testing-library/react';
import {SubscriptionNames} from 'shared/util/subscriptions';
import {WorkspacePage} from 'test/mock-page-context';
import {mockCurrentUser} from 'test/mock-page-context';
import {UserRoleNames} from 'shared/util/constants';

jest.mock('shared/hooks/useTimeZone', () => ({
	useTimeZone: () => ({timeZoneId: 'UTC'})
}));

const defaultProps = {
	groupId: '23',
	project: new Project(
		data.mockProject(23, {
			faroSubscription: fromJS(
				data.mockSubscription({
					name: SubscriptionNames.LiferaySaasEnterprisePlan
				})
			)
		})
	)
};

const WrappedComponent = ({ldpEnabled = false, store = mockStore(), ...props}) => (
	<Provider store={store}>
		<WorkspacePage ldpEnabled={ldpEnabled}>
			<UsageOverviewExternal {...props} />
		</WorkspacePage>
	</Provider>
);

describe('UsageOverviewExternal', () => {
	it('should render', () => {
		mockCurrentUser();

		const {container} = render(<WrappedComponent {...defaultProps} />);

		expect(container).toMatchSnapshot();
	});

	it('should render the Hero Banner', () => {
		const {container} = render(<WrappedComponent {...defaultProps} />);

		expect(container.querySelector('.saas-banner')).toBeTruthy();
	});

	it('should render the "Usage Overview" page for SaaS title', () => {
		const {getByText} = render(<WrappedComponent {...defaultProps} />);

		expect(getByText(/^view your saas project metrics$/i)).toBeInTheDocument();
	});

	it('should render the "Go to Customer Portal" button for admin user', () => {
		mockCurrentUser();

		const {queryByText} = render(<WrappedComponent {...defaultProps} />);

		expect(queryByText(/^go to customer portal$/i)).toBeInTheDocument();
	});

	it('should not render the "Go to Customer Portal" button for member user', () => {
		mockCurrentUser({roleName: UserRoleNames.Member});

		const {queryByText} = render(<WrappedComponent {...defaultProps} />);

		expect(queryByText(/^go to customer portal$/i)).toBeNull();
	});

	it('should render the "Sites and Users" and "Resource Usage" sections', () => {
		const {getByText} = render(<WrappedComponent {...defaultProps} />);

		expect(getByText(/^sites and users$/i)).toBeInTheDocument();
		expect(getByText(/^resource usage$/i)).toBeInTheDocument();
	});
});

describe('UsageOverviewExternal when the subscription is LDP', () => {
	it('should render the "View Your Workspace Metrics" title', () => {
		const {getByText} = render(
			<WrappedComponent {...defaultProps} ldpEnabled store={mockStore(mockStoreDataLDP)} />
		);

		expect(getByText(/^view your workspace metrics$/i)).toBeInTheDocument();
	});

	it('should render the "Go to Liferay One" button for admin user', () => {
		mockCurrentUser();

		const {getByText} = render(
			<WrappedComponent {...defaultProps} ldpEnabled store={mockStore(mockStoreDataLDP)} />
		);

		expect(getByText(/^go to liferay one$/i)).toBeInTheDocument();
	});

	it('should not render the "SaaS plan usage" description', () => {
		const {queryByText} = render(
			<WrappedComponent {...defaultProps} ldpEnabled store={mockStore(mockStoreDataLDP)} />
		);

		expect(
			queryByText(/^saas plan usage is determined by malus and apvs\.$/i)
		).toBeNull();
	});

	it('should redact the metric titles in the "Sites and Users" section', () => {
		const {container, queryByRole} = render(
			<WrappedComponent {...defaultProps} ldpEnabled store={mockStore(mockStoreDataLDP)} />
		);

		expect(
			queryByRole('heading', {level: 3, name: /^number of sites$/i})
		).toBeNull();
		expect(
			container.querySelectorAll('.card-title-rectangle')
		).toHaveLength(3);
	});
});
