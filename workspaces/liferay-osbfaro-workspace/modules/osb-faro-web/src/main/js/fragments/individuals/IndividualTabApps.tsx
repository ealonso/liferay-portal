import BundleRouter from 'route-middleware/BundleRouter';
import IndividualProfile, {
	IIndividualProfileContentArgs,
} from 'individual/profile/pages/IndividualProfile';
import PageApp, {IPageAppProps} from '../PageApp';
import React, {lazy} from 'react';

const AssociatedSegments = lazy(
	() => import('individual/profile/pages/AssociatedSegments')
);
const Details = lazy(() => import('individual/profile/pages/Details'));
const IndividualProfileCDP = lazy(
	() => import('individual/profile/pages/IndividualProfileCDP')
);
const InterestDetails = lazy(
	() => import('individual/profile/pages/InterestDetails')
);
const Interests = lazy(() => import('individual/profile/pages/Interests'));
const Overview = lazy(() => import('individual/profile/pages/Overview'));
const OverviewCDP = lazy(() => import('individual/profile/pages/OverviewCDP'));

interface IIndividualParams {
	[name: string]: string | undefined;
	id: string;
}

interface IIndividualOverviewParams extends IIndividualParams {
	query?: string;
	rangeEnd?: string;
	rangeKey?: string;
	rangeStart?: string;
	tabId?: string;
}

interface IIndividualListParams extends IIndividualParams {
	delta?: string;
	field?: string;
	page?: string;
	query?: string;
	sortOrder?: string;
}

interface IIndividualInterestParams extends IIndividualListParams {
	active?: string;
	interestId: string;
}

function createIndividualTabApp<
	Params extends {[name: string]: string | undefined},
>(
	renderContent: (args: IIndividualProfileContentArgs) => React.ReactNode
): React.FC<IPageAppProps<Params>> {
	return (props) => (
		<PageApp {...props}>
			<BundleRouter
				componentProps={{renderContent}}
				data={IndividualProfile}
			/>
		</PageApp>
	);
}

export const IndividualApp = createIndividualTabApp<IIndividualOverviewParams>(
	({LDPEnabled, individual}) => (
		<BundleRouter
			componentProps={{individual}}
			data={LDPEnabled ? OverviewCDP : Overview}
		/>
	)
);

export const IndividualDetailsApp =
	createIndividualTabApp<IIndividualOverviewParams>(
		({LDPEnabled, individual}) => (
			<BundleRouter
				componentProps={{individual}}
				data={LDPEnabled ? IndividualProfileCDP : Details}
			/>
		)
	);

export const IndividualInterestApp =
	createIndividualTabApp<IIndividualInterestParams>(({individual}) => (
		<BundleRouter componentProps={{individual}} data={InterestDetails} />
	));

export const IndividualInterestsApp =
	createIndividualTabApp<IIndividualListParams>(({individual}) => (
		<BundleRouter componentProps={{individual}} data={Interests} />
	));

export const IndividualSegmentsApp =
	createIndividualTabApp<IIndividualListParams>(({individual}) => (
		<BundleRouter componentProps={{individual}} data={AssociatedSegments} />
	));
