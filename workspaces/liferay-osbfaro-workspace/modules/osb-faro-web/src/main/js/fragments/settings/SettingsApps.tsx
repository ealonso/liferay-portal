import BundleRouter from 'route-middleware/BundleRouter';
import ErrorPage from 'shared/pages/ErrorPage';
import Loading from 'shared/components/Loading';
import PageApp, {IPageAppProps} from '../PageApp';
import React, {lazy, useEffect} from 'react';
import SettingsPage from 'settings/pages/Settings';
import {DEVELOPER_MODE} from 'shared/util/constants';
import {ENABLE_BLOCKLIST_KEYWORDS} from 'shared/util/feature-flags';
import {useLDPEnabled} from 'shared/hooks/useLDPEnabled';
import {useStore} from 'react-redux';
import {config} from 'shared/util/config';

const AccessTokenList = lazy(
	() =>
		import(

			/* webpackChunkName: "AccessTokenList" */ 'settings/apis/pages/AccessTokenList'
		)
);

const AttributeView = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsEventAttributesView" */ 'settings/definitions/event-attributes/pages/AttributeView'
		)
);

const ChannelList = lazy(
	() =>
		import(

			/* webpackChunkName: "ChannelList" */ 'settings/channels/pages/ChannelList'
		)
);

const ChannelView = lazy(
	() =>
		import(

			/* webpackChunkName: "ChannelView" */ 'settings/channels/pages/View'
		)
);

const ConfigureCSV = lazy(
	() =>
		import(

			/* webpackChunkName: "ConfigureCSV" */ 'settings/pages/data-source/ConfigureCSV'
		)
);

const DataPrivacyOverview = lazy(
	() =>
		import(

			/* webpackChunkName: "DataPrivacyOverview" */ 'settings/data-privacy/pages/Overview'
		)
);

const DataSource = lazy(
	() =>
		import(

			/* webpackChunkName: "DataSource" */ 'settings/pages/data-source/View'
		)
);

const DataSourceEdit = lazy(
	() =>
		import(

			/* webpackChunkName: "DataSourceEdit" */ 'settings/pages/data-source/Edit'
		)
);

const DataSourceList = lazy(
	() =>
		import(

			/* webpackChunkName: "DataSourceList" */ 'settings/pages/DataSourceList'
		)
);

const DataSourceOnboarding = lazy(
	() =>
		import(

			/* webpackChunkName: "DataSourceOnboarding" */ 'settings/pages/data-source/Onboarding'
		)
);

const DefinitionsOverview = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsOverview" */ 'settings/definitions/pages/Overview'
		)
);

const DeleteDataSource = lazy(
	() =>
		import(

			/* webpackChunkName: "DeleteDataSource" */ 'settings/pages/data-source/Delete'
		) as unknown as Promise<{default: React.ComponentType}>
);

const EventAttributes = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsEventAttributes" */ 'settings/definitions/event-attributes/pages/EventAttributes'
		)
);

const EventBlockList = lazy(
	() =>
		import(

			/* webpackChunkName: "BlockList" */ 'settings/definitions/events/pages/BlockList'
		)
);

const EventView = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsEventView" */ 'settings/definitions/events/pages/View'
		)
);

const Events = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsEvents" */ 'settings/definitions/events/pages/Events'
		)
);

const FeatureFlags = lazy(
	() =>
		import(

			/* webpackChunkName: "FeatureFlags" */ 'settings/pages/FeatureFlags'
		)
);

const IndividualAttributes = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsIndividualAttributes" */ 'settings/definitions/pages/IndividualAttributes'
		)
);

const InterestTopics = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsInterestTopics" */ 'settings/definitions/pages/InterestTopics'
		)
);

const RecommendationCreateItemSimilarity = lazy(
	() =>
		import(

			/* webpackChunkName: "RecommendationCreateItemSimilarity" */ 'settings/recommendations/pages/CreateItemSimilarity'
		)
);

const RecommendationEdit = lazy(
	() =>
		import(

			/* webpackChunkName: "RecommendationEdit" */ 'settings/recommendations/pages/Edit'
		)
);

const RecommendationList = lazy(
	() =>
		import(

			/* webpackChunkName: "RecommendationList" */ 'settings/recommendations/pages/Recommendations'
		)
);

const RecommendationView = lazy(
	() =>
		import(

			/* webpackChunkName: "RecommendationView" */ 'settings/recommendations/pages/View'
		)
);

const RequestLog = lazy(
	() =>
		import(

			/* webpackChunkName: "RequestLog" */ 'settings/data-privacy/pages/RequestLog'
		)
);

const Search = lazy(
	() =>
		import(

			/* webpackChunkName: "DefinitionsSearch" */ 'settings/definitions/pages/search/Search'
		)
);

const SuppressedUsers = lazy(
	() =>
		import(

			/* webpackChunkName: "SupressedUsers" */ 'settings/data-privacy/pages/SuppressedUsers'
		)
);

const TrackedBehaviors = lazy(
	() =>
		import(

			/* webpackChunkName: "TrackedBehaviors" */ 'settings/definitions/pages/TrackedBehaviors'
		)
);

const UsageOverview = lazy(
	() =>
		import(

			/* webpackChunkName: "UsageOverview" */ 'settings/pages/UsageOverview'
		)
);

const UsageOverviewExternal = lazy(
	() =>
		import(

			/* webpackChunkName: "UsageOverviewExternal" */ 'settings/pages/UsageOverviewExternal'
		)
);

const User = lazy(
	() => import(/* webpackChunkName: "Users" */ 'settings/pages/user')
);

const UserList = lazy(
	() =>
		import(

			/* webpackChunkName: "UserManagement" */ 'settings/pages/user/UserList'
		)
);

const UserRequest = lazy(
	() =>
		import(

			/* webpackChunkName: "UserRequest" */ 'settings/pages/user/UserRequest'
		)
);

const WorkspaceSettings = lazy(
	() =>
		import(

			/* webpackChunkName: "WorkspaceSettings" */ 'settings/pages/Workspace'
		)
);

interface IRecommendationsGateProps {
	children: React.ReactNode;
}

const RecommendationsGate: React.FC<IRecommendationsGateProps> = ({
	children,
}) => {
	const {groupId = ''} = config;
	const store = useStore();

	const recommendationsEnabled = store
		.getState()
		.getIn(['projects', groupId, 'data', 'recommendationsEnabled'], false);

	if (!recommendationsEnabled) {
		return <ErrorPage />;
	}

	return <>{children}</>;
};

const DataPrivacyContent: React.FC = () => {
	const {groupId = ''} = config;

	const {view} = config.params;

	if (view === 'request-log') {
		return (
			<BundleRouter
				componentProps={{groupId}}
				data={RequestLog}
				destructured={false}
			/>
		);
	}

	if (view === 'suppressed-users') {
		return (
			<BundleRouter
				componentProps={{groupId}}
				data={SuppressedUsers}
				destructured={false}
			/>
		);
	}

	return <BundleRouter data={DataPrivacyOverview} />;
};

const EditRecommendationContent: React.FC = () => {
	const {jobId} = config.params;

	return (
		<BundleRouter
			data={
				jobId ? RecommendationEdit : RecommendationCreateItemSimilarity
			}
			destructured={false}
		/>
	);
};

const EventDefinitionsContent: React.FC = () => {
	const {tab} = config.params;

	return (
		<BundleRouter data={tab === 'block-list' ? EventBlockList : Events} />
	);
};

const SettingsRedirect: React.FC = () => {
	useEffect(() => {
		window.location.replace(config.dataSourcesURL);
	}, []);

	return <Loading />;
};

const UsageContent: React.FC = () => {
	const {groupId = ''} = config;
	const store = useStore();
	const isLDP = useLDPEnabled({groupId});

	const project = store.getState().getIn(['projects', groupId, 'data']);

	const subscriptionName = project?.faroSubscription?.get('name');

	return (
		<BundleRouter
			data={
				subscriptionName?.includes('SaaS') || isLDP
					? UsageOverviewExternal
					: UsageOverview
			}
		/>
	);
};

interface IListParams {
	[name: string]: string | undefined;
	delta?: string;
	field?: string;
	page?: string;
	query?: string;
	sortOrder?: string;
}

interface ITabListParams extends IListParams {
	tab?: string;
}

interface IDataPrivacyParams extends IListParams {
	rangeEnd?: string;
	rangeKey?: string;
	rangeStart?: string;
	statuses?: string;
	types?: string;
	view?: string;
}

interface IConfigureCSVParams {
	[name: string]: string | undefined;
	fileVersionId?: string;
	id?: string;
	name?: string;
}

interface IDataSourceParams extends IListParams {
	id: string;
}

interface IDataSourceOnboardingParams {
	[name: string]: string | undefined;
	dataSourceId?: string;
	id: string;
	stepIndex?: string;
}

interface IEditDataSourceParams {
	[name: string]: string | undefined;
	id: string;
}

interface IEditRecommendationParams {
	[name: string]: string | undefined;
	jobId?: string;
}

interface IEventAttributeParams {
	[name: string]: string | undefined;
	attributeId: string;
}

interface IEventDefinitionParams {
	[name: string]: string | undefined;
	eventId: string;
}

interface IPropertyParams extends IListParams {
	id: string;
}

interface IRecommendationParams {
	[name: string]: string | undefined;
	jobId: string;
}

function createSettingsApp<
	Params extends {[name: string]: string | undefined} = {
		[name: string]: string | undefined;
	},
>(content: React.ReactNode): React.FC<IPageAppProps<Params>> {
	return (props) => (
		<PageApp {...props} channelScoped={false}>
			<SettingsPage>{content}</SettingsPage>
		</PageApp>
	);
}

export const AccessRequestsApp = createSettingsApp<IListParams>(
	<User
		renderContent={({onSetUserRequest}) => (
			<BundleRouter
				componentProps={{onSetUserRequest}}
				data={UserRequest}
			/>
		)}
	/>
);

export const ApiTokensApp = createSettingsApp(
	<BundleRouter data={AccessTokenList} />
);

export const BehaviorsApp = createSettingsApp(
	DEVELOPER_MODE ? <BundleRouter data={TrackedBehaviors} /> : <ErrorPage />
);

export const ConfigureCSVApp = createSettingsApp<IConfigureCSVParams>(
	<BundleRouter data={ConfigureCSV} />
);

export const DataPrivacyApp = createSettingsApp<IDataPrivacyParams>(
	<DataPrivacyContent />
);

export const DataSourceApp = createSettingsApp<IDataSourceParams>(
	<BundleRouter data={DataSource} />
);

export const DataSourceOnboardingApp =
	createSettingsApp<IDataSourceOnboardingParams>(
		<BundleRouter data={DataSourceOnboarding} />
	);

export const DataSourcesApp = createSettingsApp<IListParams>(
	<BundleRouter data={DataSourceList} />
);

export const DefinitionsApp = createSettingsApp(
	<BundleRouter data={DefinitionsOverview} />
);

export const DefinitionsSearchApp = createSettingsApp(
	<BundleRouter data={Search} />
);

export const DeleteDataSourceApp = createSettingsApp<IEditDataSourceParams>(
	<BundleRouter data={DeleteDataSource} />
);

export const EditDataSourceApp = createSettingsApp<IEditDataSourceParams>(
	<BundleRouter data={DataSourceEdit} />
);

export const EditRecommendationApp =
	createSettingsApp<IEditRecommendationParams>(
		<RecommendationsGate>
			<EditRecommendationContent />
		</RecommendationsGate>
	);

export const EventAttributeApp = createSettingsApp<IEventAttributeParams>(
	<BundleRouter data={AttributeView} />
);

export const EventAttributesApp = createSettingsApp<ITabListParams>(
	<BundleRouter data={EventAttributes} />
);

export const EventDefinitionApp = createSettingsApp<IEventDefinitionParams>(
	<BundleRouter data={EventView} />
);

export const EventDefinitionsApp = createSettingsApp<ITabListParams>(
	<EventDefinitionsContent />
);

export const FeatureFlagsApp = createSettingsApp(
	<BundleRouter data={FeatureFlags} />
);

export const IndividualAttributesApp = createSettingsApp(
	<BundleRouter data={IndividualAttributes} />
);

export const InterestTopicsApp = createSettingsApp<IListParams>(
	ENABLE_BLOCKLIST_KEYWORDS ? (
		<BundleRouter data={InterestTopics} />
	) : (
		<ErrorPage />
	)
);

export const PropertiesApp = createSettingsApp<IListParams>(
	<BundleRouter data={ChannelList} />
);

export const PropertyApp = createSettingsApp<IPropertyParams>(
	<BundleRouter data={ChannelView} />
);

export const RecommendationApp = createSettingsApp<IRecommendationParams>(
	<RecommendationsGate>
		<BundleRouter data={RecommendationView} destructured={false} />
	</RecommendationsGate>
);

export const RecommendationsApp = createSettingsApp<IListParams>(
	<RecommendationsGate>
		<BundleRouter data={RecommendationList} destructured={false} />
	</RecommendationsGate>
);

export const SettingsApp = createSettingsApp(<SettingsRedirect />);

export const UsageApp = createSettingsApp(<UsageContent />);

export const UsersApp = createSettingsApp<IListParams>(
	<User
		renderContent={({currentUser}) => (
			<BundleRouter componentProps={{currentUser}} data={UserList} />
		)}
	/>
);

export const WorkspaceSettingsApp = createSettingsApp(
	<BundleRouter data={WorkspaceSettings} />
);
