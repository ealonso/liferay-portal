import AppProviders from '../AppProviders';
import AppShell from '../AppShell';
import HelpWidget from 'shared/components/HelpWidget';
import Loading from 'shared/components/Loading';
import NoPropertiesAvailable from 'shared/pages/NoPropertiesAvailable';
import React, {Suspense, useState} from 'react';
import store from 'shared/store';
import WorkspaceGate from 'shared/components/WorkspaceGate';
import {
	IDismissedAlerts,
	INavigationItem,
	buildConfig,
} from 'shared/util/config';
import {PageConfigProvider, usePageConfig} from 'shared/context/pageConfig';
import {Channel} from 'shared/components/channels-menu';
import {setDefaultGroupId} from 'shared/apollo/client';
import {receiveProject} from 'shared/actions/projects';
import {useChannelContext} from 'shared/context/channel';
import {useModalNotifications} from 'shared/hooks/useModalNotifications';

export interface IPageAppProps<
	Params extends {[name: string]: string | undefined} = {
		[name: string]: string | undefined;
	},
> {
	channelId: string;
	channelScoped?: boolean;
	channels?: Channel[] | null;
	currentUser: {[key: string]: unknown};
	dismissedAlerts?: IDismissedAlerts | null;
	friendlyURL: string;
	groupId: string;
	ldpEnabled: boolean;
	navigationItems?: INavigationItem[] | null;
	pageURLs: {[friendlyURL: string]: string};
	params?: Params | null;
	project?: {[key: string]: unknown} | null;
	signOutURL: string;
}

interface IPageLayoutProps {
	children: React.ReactNode;
	groupId: string;
}

const PageLayout: React.FC<IPageLayoutProps> = ({children, groupId}) => {
	const modalNotification = useModalNotifications(groupId);

	return (
		<>
			<Suspense fallback={<Loading />}>{children}</Suspense>

			{modalNotification}

			<HelpWidget groupId={groupId} />
		</>
	);
};

const PropertyGate: React.FC<IPageLayoutProps> = ({children, groupId}) => {
	const {selectedChannel} = useChannelContext();

	const {currentUser} = usePageConfig();

	if (!selectedChannel) {
		return (
			<NoPropertiesAvailable
				currentUser={currentUser}
				groupId={groupId}
			/>
		);
	}

	return <>{children}</>;
};

const PageApp: React.FC<IPageAppProps & {children: React.ReactNode}> = ({
	channelId,
	channelScoped = true,
	channels,
	children,
	currentUser,
	dismissedAlerts,
	friendlyURL,
	groupId,
	ldpEnabled,
	navigationItems,
	pageURLs,
	params,
	project,
	signOutURL,
}) => {
	const [config] = useState(() => {
		setDefaultGroupId(groupId);

		if (project) {
			store.dispatch(receiveProject(groupId, project));
		}

		return buildConfig({
			channelId,
			channels,
			currentUser,
			dismissedAlerts,
			friendlyURL,
			groupId,
			ldpEnabled,
			navigationItems,
			pageURLs,
			params,
			signOutURL,
		});
	});

	return (
		<PageConfigProvider config={config}>
			<AppProviders>
				<AppShell groupId={groupId}>
					<PageLayout groupId={groupId}>
						<WorkspaceGate>
							{channelScoped ? (
								<PropertyGate groupId={groupId}>
									{children}
								</PropertyGate>
							) : (
								children
							)}
						</WorkspaceGate>
					</PageLayout>
				</AppShell>
			</AppProviders>
		</PageConfigProvider>
	);
};

export default PageApp;
