import DataSourcesProvider from 'shared/context/dataSources';
import ErrorPage from 'shared/pages/ErrorPage';
import Loading from 'shared/components/Loading';
import ProjectGate from 'shared/components/workspaces/ProjectGate';
import React from 'react';
import {DownloadReportProvider} from 'shared/components/download-report/DownloadReportContext';
import {isValidChannel} from 'shared/components/channels-menu';
import {useChannelContext} from 'shared/context/channel';
import {useChannels} from 'shared/hooks/useChannels';
import {useOnboardingModal} from 'shared/hooks/useOnboardingModal';
import {useUnassignedSegmentsModal} from 'shared/hooks/useUnassignedSegmentsModal';
import {usePageConfig} from 'shared/context/pageConfig';

const WorkspaceModals: React.FC<{groupId: string}> = ({groupId}) => {
	const onboardingModal = useOnboardingModal({groupId});

	const unassignedSegmentsModal = useUnassignedSegmentsModal({groupId});

	return (
		<>
			{onboardingModal}

			{unassignedSegmentsModal}
		</>
	);
};

interface IChannelGateProps {
	children: React.ReactNode;
	groupId: string;
}

const ChannelGate: React.FC<IChannelGateProps> = ({children, groupId}) => {
	const {channelId, channels: initialChannels} = usePageConfig();

	const {channels, error, loading} = useChannels({
		channelId,
		groupId,
		initialChannels,
	});

	const {selectedChannel} = useChannelContext();

	if (error) {
		return <ErrorPage />;
	}

	if (loading) {
		return <Loading />;
	}

	if (!isValidChannel(channelId, channels)) {
		return <ErrorPage />;
	}

	return (
		<>
			<WorkspaceModals groupId={groupId} />

			<DataSourcesProvider groupId={groupId} skip={!selectedChannel}>
				<DownloadReportProvider>{children}</DownloadReportProvider>
			</DataSourcesProvider>
		</>
	);
};

interface IWorkspaceGateProps {
	children: React.ReactNode;
}

const WorkspaceGate: React.FC<IWorkspaceGateProps> = ({children}) => {
	const {groupId = ''} = usePageConfig();

	return (
		<ProjectGate groupId={groupId}>
			<ChannelGate groupId={groupId}>{children}</ChannelGate>
		</ProjectGate>
	);
};

export default WorkspaceGate;
