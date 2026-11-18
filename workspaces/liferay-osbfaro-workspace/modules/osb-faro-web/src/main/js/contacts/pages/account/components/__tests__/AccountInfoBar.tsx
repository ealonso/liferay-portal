import AccountInfoBar from '../AccountInfoBar';
import React from 'react';
import {cleanup, render, screen} from '@testing-library/react';
import {useLifecycleSelection} from 'shared/hooks/useLifecycleSelection';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

jest.mock('shared/hooks/useLifecycleSelection', () => ({
	useLifecycleSelection: jest.fn(),
}));

const mockedUseLifecycleSelection = useLifecycleSelection as jest.Mock;

const buildLifecycleStage = (lifecycleId: string, name: string) => ({
	lifecycleId,
	lifecycleName: `Lifecycle ${lifecycleId}`,
	name,
	stageExternalReferenceCode: `erc-${lifecycleId}-${name}`,
});

const mockAccount = {
	accountName: 'Hydrofield',
	accountType: 'Prospect',
	annualRevenue: 120000000,
	country: 'Australia',
	industry: 'Health Sector',
	lifecycleStages: [
		buildLifecycleStage('al-1', 'Engaged'),
		buildLifecycleStage('al-2', 'At Risk'),
	],
};

const lifecycleSelection = {
	lifecycles: [
		{id: 'al-1', name: 'Customer'},
		{id: 'al-2', name: 'Partner'},
	],
	loading: false,
	selectedLifecycleId: 'al-1',
	setSelectedLifecycleId: jest.fn(),
};

const AccountPage = ({children}: {children: React.ReactNode}) => (
	<WorkspacePage channelId="123" groupId="23">
		{children}
	</WorkspacePage>
);

const renderAccountInfoBar = (ui: React.ReactElement) =>
	render(ui, {wrapper: AccountPage});

describe('AccountInfoBar', () => {
	afterEach(cleanup);

	beforeEach(() => {
		mockedUseLifecycleSelection.mockReturnValue(lifecycleSelection);
	});

	describe('rendering', () => {
		it('should render the account name', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(screen.getByText('Hydrofield')).toBeInTheDocument();
		});

		it('should render every firmographic value', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(screen.getByText('Australia')).toBeInTheDocument();
			expect(screen.getByText('Health Sector')).toBeInTheDocument();
		});

		it('should abbreviate the annual revenue', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(screen.getByText('120M Revenue')).toBeInTheDocument();
		});

		it('should render the stage of the account in the selected lifecycle and the account type as labels', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(screen.getByText('Engaged')).toBeInTheDocument();
			expect(screen.queryByText('At Risk')).not.toBeInTheDocument();
			expect(screen.getByText('Type: Prospect')).toBeInTheDocument();
		});

		it('should render the stage of the account when another lifecycle is selected', () => {
			mockedUseLifecycleSelection.mockReturnValue({
				...lifecycleSelection,
				selectedLifecycleId: 'al-2',
			});

			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(screen.getByText('At Risk')).toBeInTheDocument();
			expect(screen.queryByText('Engaged')).not.toBeInTheDocument();
		});

		it('should load the lifecycles of the channel being viewed', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(mockedUseLifecycleSelection).toHaveBeenCalledWith({
				channelId: '123',
				groupId: '23',
			});
		});

		it('should render the lifecycle selector with the selected lifecycle', () => {
			renderAccountInfoBar(<AccountInfoBar {...mockAccount} />);

			expect(
				screen.getByRole('combobox', {name: 'Lifecycle'})
			).toHaveTextContent('Customer');
		});

		it.each([
			['Engaged', 'label-inverse-warning'],
			['At Risk', 'label-inverse-danger'],
			['NOT_A_STAGE', 'label-inverse-secondary'],
		])('should color the %s stage as %s', (stageName, className) => {
			renderAccountInfoBar(
				<AccountInfoBar
					lifecycleStages={[buildLifecycleStage('al-1', stageName)]}
				/>
			);

			expect(screen.getByText(stageName).closest('.label')).toHaveClass(
				className
			);
		});
	});

	describe('missing values', () => {
		it('should render a field blank rather than a placeholder', () => {
			renderAccountInfoBar(<AccountInfoBar accountName="Hydrofield" />);

			expect(screen.getByText('Hydrofield')).toBeInTheDocument();
			expect(screen.queryByText('Australia')).not.toBeInTheDocument();
			expect(screen.queryByText(/Revenue/)).not.toBeInTheDocument();
			expect(screen.queryByText(/Type:/)).not.toBeInTheDocument();
		});

		it('should not render the stage label when the account has no stage in the selected lifecycle', () => {
			const {container} = renderAccountInfoBar(
				<AccountInfoBar
					accountName="Hydrofield"
					accountType="Prospect"
					lifecycleStages={[buildLifecycleStage('al-2', 'Engaged')]}
				/>
			);

			expect(screen.queryByText('Engaged')).not.toBeInTheDocument();
			expect(container.querySelectorAll('.label')).toHaveLength(1);
			expect(screen.getByText('Type: Prospect')).toBeInTheDocument();
		});

		it('should render a zero annual revenue', () => {
			renderAccountInfoBar(
				<AccountInfoBar accountName="Hydrofield" annualRevenue={0} />
			);

			expect(screen.getByText(/Revenue/)).toHaveTextContent('0');
		});

		it('should render nothing for an empty account', () => {
			const {container} = renderAccountInfoBar(<AccountInfoBar />);

			expect(container.querySelectorAll('.label')).toHaveLength(0);
			expect(container.querySelectorAll('.lexicon-icon')).toHaveLength(0);
		});
	});
});
