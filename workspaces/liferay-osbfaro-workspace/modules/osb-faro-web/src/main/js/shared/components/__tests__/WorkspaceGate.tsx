jest.mock(
	'shared/components/workspaces/SuccessDisplay',
	() => () => 'SuccessDisplay'
);

jest.mock(
	'shared/components/workspaces/ErrorDisplay',
	() =>
		({errorType}: {errorType: string}) =>
			`WorkspacesErrorDisplay ${errorType}`
);

jest.mock(
	'shared/components/workspaces/ActivatingDisplay',
	() => () => 'ActivatingDisplay'
);

jest.mock('shared/pages/WorkspaceNotFound', () => () => 'WorkspaceNotFound');

jest.mock('shared/pages/ErrorPage', () => () => 'ErrorPage');

import * as API from 'shared/api';
import * as data from 'test/data';
import mockStore, {mockStoreData} from 'test/mock-store';
import React from 'react';
import WorkspaceGate from '../WorkspaceGate';
import {
	ActionType,
	ChannelProvider,
	useChannelContext,
} from 'shared/context/channel';
import {Channel} from 'shared/components/channels-menu';
import {fireEvent, render, screen} from '@testing-library/react';
import {InMemoryCache} from '@apollo/client';
import {mockDataSourcesReq} from 'test/graphql-data';
import {MockedProvider} from '@apollo/client/testing';
import {ProjectStates} from 'shared/util/constants';
import {Provider} from 'react-redux';
import {useDataSources} from 'shared/context/dataSources';
import {WorkspacePage} from 'test/mock-page-context';

jest.unmock('react-dom');

const CHANNELS = [
	data.mockChannel(1, 0, {id: '1'}),
	data.mockChannel(2, 0, {id: '2'}),
] as unknown as Channel[];

const renderedChannels: (Channel | null)[] = [];

const SegmentsList: React.FC = () => {
	const {selectedChannel} = useChannelContext();

	return <p>{`segments list of ${selectedChannel?.name}`}</p>;
};

const WorkspaceHome: React.FC = () => {
	const {selectedChannel} = useChannelContext();

	renderedChannels.push(selectedChannel);

	return <p>{'workspace home'}</p>;
};

const OnboardingConnection: React.FC = () => {
	const {channelDispatch} = useChannelContext();

	const {loading} = useDataSources();

	return (
		<>
			<button
				onClick={() => {
					channelDispatch?.({
						payload: CHANNELS[0],
						type: ActionType.setSelectedChannel,
					});

					channelDispatch?.({
						payload: [CHANNELS[0]],
						type: ActionType.setChannels,
					});
				}}
				type="button"
			>
				{'Connect DXP'}
			</button>

			<p>{loading ? 'data sources loading' : 'data sources loaded'}</p>
		</>
	);
};

const renderGate = (
	children: React.ReactNode,
	{
		channelId = '',
		groupId = '23',
		selectedChannel = null,
	}: {
		channelId?: string;
		groupId?: string;
		selectedChannel?: Channel | null;
	} = {}
) =>
	render(
		<Provider store={mockStore(mockStoreData)}>
			<ChannelProvider selectedChannel={selectedChannel}>
				<MockedProvider
					cache={new InMemoryCache({addTypename: false})}
					mocks={[mockDataSourcesReq()]}
				>
					<WorkspacePage channelId={channelId} groupId={groupId}>
						<WorkspaceGate>{children}</WorkspaceGate>
					</WorkspacePage>
				</MockedProvider>
			</ChannelProvider>
		</Provider>
	);

describe('WorkspaceGate', () => {
	beforeEach(() => {
		jest.clearAllMocks();

		renderedChannels.length = 0;

		(API.channels.fetchAll as jest.Mock)
			.mockReset()
			.mockReturnValue(Promise.resolve({items: CHANNELS}));
		(API.individualSegment.searchUnassigned as jest.Mock).mockReturnValue(
			Promise.resolve({items: [], total: 0})
		);
		(API.projects.fetch as jest.Mock).mockImplementation(({groupId}) =>
			Promise.resolve(
				data.mockProject(groupId, {
					state:
						groupId === '25'
							? ProjectStates.Maintenance
							: ProjectStates.Ready,
				})
			)
		);
	});

	it('renders the page for a valid channel', async () => {
		renderGate(<SegmentsList />, {channelId: '2'});

		expect(
			await screen.findByText('segments list of Channel 2')
		).toBeInTheDocument();
	});

	it('renders the page when the page leaves out the channel', async () => {
		renderGate(<SegmentsList />);

		expect(await screen.findByText(/segments list/)).toBeInTheDocument();
		expect(screen.queryByText('ErrorPage')).toBeNull();
	});

	it('renders the workspace home without a channel', async () => {
		renderGate(<WorkspaceHome />);

		expect(await screen.findByText('workspace home')).toBeInTheDocument();
	});

	it('renders a segment link without a channel', async () => {
		renderGate(<p>{'segment link'}</p>);

		expect(await screen.findByText('segment link')).toBeInTheDocument();
	});

	it('clears the channel of the previous workspace before rendering one without channels', async () => {
		(API.channels.fetchAll as jest.Mock).mockReturnValue(
			Promise.resolve({items: []})
		);

		renderGate(<WorkspaceHome />, {selectedChannel: CHANNELS[1]});

		expect(await screen.findByText('workspace home')).toBeInTheDocument();
		expect(renderedChannels).not.toContain(CHANNELS[1]);
	});

	it('reloads the channels once when the page channel is missing from them', async () => {
		const newChannel = data.mockChannel(3, 0, {
			id: '3',
		}) as unknown as Channel;

		(API.channels.fetchAll as jest.Mock)
			.mockReturnValueOnce(Promise.resolve({items: CHANNELS}))
			.mockReturnValueOnce(
				Promise.resolve({items: [...CHANNELS, newChannel]})
			);

		renderGate(<SegmentsList />, {channelId: '3'});

		expect(
			await screen.findByText('segments list of Channel 3')
		).toBeInTheDocument();
		expect(API.channels.fetchAll).toHaveBeenCalledTimes(2);
	});

	it('loads the data sources once the onboarding connects the first channel', async () => {
		(API.channels.fetchAll as jest.Mock).mockReturnValue(
			Promise.resolve({items: []})
		);

		renderGate(<OnboardingConnection />);

		expect(
			await screen.findByText('data sources loading')
		).toBeInTheDocument();
		expect(API.dataSource.search).not.toHaveBeenCalled();

		fireEvent.click(screen.getByRole('button', {name: 'Connect DXP'}));

		expect(
			await screen.findByText('data sources loaded')
		).toBeInTheDocument();
		expect(API.dataSource.search).toHaveBeenCalled();
	});

	it('renders the error page for an unknown channel', async () => {
		renderGate(<SegmentsList />, {channelId: '999'});

		expect(await screen.findByText('ErrorPage')).toBeInTheDocument();
		expect(API.channels.fetchAll).toHaveBeenCalledTimes(2);
	});

	it('renders the error page when the channels cannot be loaded', async () => {
		(API.channels.fetchAll as jest.Mock).mockReturnValue(
			Promise.reject({})
		);

		renderGate(<SegmentsList />, {channelId: '2'});

		expect(await screen.findByText('ErrorPage')).toBeInTheDocument();
	});

	it('renders the project state screen when the workspace is not ready', async () => {
		renderGate(<SegmentsList />, {channelId: '1', groupId: '25'});

		expect(
			await screen.findByText(/WorkspacesErrorDisplay/)
		).toBeInTheDocument();
		expect(screen.queryByText(/segments list/)).not.toBeInTheDocument();
	});

	it('renders the activating screen while the workspace is being set up', async () => {
		(API.projects.fetch as jest.Mock).mockImplementation(({groupId}) =>
			Promise.resolve(
				data.mockProject(groupId, {state: ProjectStates.Activating})
			)
		);

		renderGate(<SegmentsList />, {channelId: '1', groupId: '26'});

		expect(
			await screen.findByText('ActivatingDisplay')
		).toBeInTheDocument();
	});

	it('renders WorkspaceNotFound when the workspace cannot be loaded', async () => {
		(API.projects.fetch as jest.Mock).mockReturnValue(
			Promise.reject({status: 404})
		);

		renderGate(<SegmentsList />, {channelId: '1', groupId: '27'});

		expect(
			await screen.findByText('WorkspaceNotFound')
		).toBeInTheDocument();
	});

	it('does not load the channels until the workspace is ready', async () => {
		renderGate(<SegmentsList />, {channelId: '1', groupId: '25'});

		await screen.findByText(/WorkspacesErrorDisplay/);

		expect(API.channels.fetchAll).not.toHaveBeenCalled();
	});
});
