import BundleRouter from 'route-middleware/BundleRouter';
import PageApp, {IPageAppProps} from '../PageApp';
import React, {lazy} from 'react';
import SitesDashboard, {IDashboardContentArgs} from 'sites/pages';

const InterestDetails = lazy(() => import('sites/pages/InterestDetails'));
const Interests = lazy(() => import('sites/pages/Interests'));
const Overview = lazy(() => import('sites/pages/Overview'));
const SearchTermsPage = lazy(() => import('sites/pages/SearchTermsPage'));
const Touchpoints = lazy(() => import('sites/pages/Touchpoints'));

interface ISitesParams {
	[name: string]: string | undefined;
	accountId?: string;
	accountName?: string;
	downloadReport?: string;
	rangeEnd?: string;
	rangeKey?: string;
	rangeStart?: string;
	segmentId?: string;
	segmentName?: string;
}

interface ISitesListParams extends ISitesParams {
	delta?: string;
	field?: string;
	page?: string;
	query?: string;
	sortOrder?: string;
}

interface IPagesParams extends ISitesListParams {
	useDB?: string;
}

interface ISiteInterestParams extends IPagesParams {
	interestId: string;
}

function createSitesDashboardApp<
	Params extends {[name: string]: string | undefined},
>(
	renderContent: (args: IDashboardContentArgs) => React.ReactNode
): React.FC<IPageAppProps<Params>> {
	return (props) => (
		<PageApp {...props}>
			<BundleRouter
				componentProps={{renderContent}}
				data={SitesDashboard}
				destructured={false}
			/>
		</PageApp>
	);
}

export const PagesApp = createSitesDashboardApp<IPagesParams>(() => (
	<BundleRouter data={Touchpoints} destructured={false} />
));

export const SearchTermsApp = createSitesDashboardApp<ISitesListParams>(() => (
	<BundleRouter data={SearchTermsPage} destructured={false} />
));

export const SiteInterestApp = createSitesDashboardApp<ISiteInterestParams>(
	() => <BundleRouter data={InterestDetails} destructured={false} />
);

export const SiteInterestsApp = createSitesDashboardApp<ISitesListParams>(
	() => <BundleRouter data={Interests} destructured={false} />
);

export const SitesApp = createSitesDashboardApp<ISitesParams>(
	({channelName}) => (
		<BundleRouter
			componentProps={{channelName}}
			data={Overview}
			destructured={false}
		/>
	)
);
