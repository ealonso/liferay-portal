import {ConnectorAuthStep} from '../../steps/ConnectorAuthStep';
import {ConnectorConfig} from '../../types';
import {render} from '@testing-library/react';
import {updateSearchParams} from 'settings/components/base-page/utis';

jest.unmock('react-dom');

const useWizardPageMock = jest.fn();

jest.mock('settings/components/base-page/WizardPageContext', () => ({
	useWizardPage: () => useWizardPageMock(),
}));

jest.mock('settings/components/base-page/utis', () => ({
	updateSearchParams: jest.fn(),
}));

const connectorAuthSpy = jest.fn();

jest.mock('settings/components/3rd-party-connector/ConnectorAuth', () => ({
	__esModule: true,
	default: (props: any) => {
		connectorAuthSpy(props);
		return null;
	},
}));

const buildConfig = (
	overrides: Partial<ConnectorConfig> = {}
): ConnectorConfig => ({
	columns: [],
	displayName: 'Acme',
	endpointPath: '/api/acme',
	entities: [],
	languages: {
		connectDescription: 'connectDescription',
		connectTitle: 'connectTitle',
		endpointHelper: 'endpointHelper',
		endpointLabel: 'endpointLabel',
		tokenLabel: 'tokenLabel',
	},
	singleton: false,
	slug: 'acme',
	type: 'ACME',
	...overrides,
});

const originalLocation = window.location;

const assign = jest.fn();

describe('ConnectorAuthStep', () => {
	beforeAll(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: {...originalLocation, assign},
		});
	});

	afterAll(() => {
		Object.defineProperty(window, 'location', {
			configurable: true,
			value: originalLocation,
		});
	});

	beforeEach(() => {
		assign.mockClear();
		connectorAuthSpy.mockClear();
		(updateSearchParams as jest.Mock).mockClear();

		useWizardPageMock.mockReturnValue({dataSource: undefined});
	});

	it('renders ConnectorAuth without a dataSource when the wizard has none', () => {
		render(
			<ConnectorAuthStep
				addAlert={jest.fn() as any}
				config={buildConfig()}
				groupId="23"
				onNext={jest.fn()}
			/>
		);

		const props = connectorAuthSpy.mock.calls[0][0];

		expect(props.dataSource).toBeUndefined();
		expect(props.config.slug).toBe('acme');
		expect(props.groupId).toBe('23');
	});

	it('updates the dataSourceId search param and advances after creating a data source', () => {
		const onNext = jest.fn();

		render(
			<ConnectorAuthStep
				addAlert={jest.fn() as any}
				config={buildConfig()}
				groupId="23"
				onNext={onNext}
			/>
		);

		const props = connectorAuthSpy.mock.calls[0][0];

		props.onSubmit({id: 'new-id'});

		expect(updateSearchParams).toHaveBeenCalledWith(
			'dataSourceId',
			'new-id'
		);
		expect(onNext).toHaveBeenCalled();
	});

	it('passes the wizard data source through and uses onNext as onSubmit when present', () => {
		const onNext = jest.fn();
		const dataSource = {id: 'existing'};

		useWizardPageMock.mockReturnValue({dataSource});

		render(
			<ConnectorAuthStep
				addAlert={jest.fn() as any}
				config={buildConfig()}
				groupId="23"
				onNext={onNext}
			/>
		);

		const props = connectorAuthSpy.mock.calls[0][0];

		expect(props.dataSource).toBe(dataSource);
		expect(props.onSubmit).toBe(onNext);
	});

	it('navigates to the data source list when canceling', () => {
		render(
			<ConnectorAuthStep
				addAlert={jest.fn() as any}
				config={buildConfig()}
				groupId="23"
				onNext={jest.fn()}
			/>
		);

		const props = connectorAuthSpy.mock.calls[0][0];

		props.onCancel();

		expect(assign).toHaveBeenCalledTimes(1);
		expect(assign).toHaveBeenCalledWith('/data-sources');
	});
});
