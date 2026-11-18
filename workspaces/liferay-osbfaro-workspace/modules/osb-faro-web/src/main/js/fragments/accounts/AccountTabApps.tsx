import AccountProfile, {
	IAccountProfileContentArgs,
} from 'contacts/pages/account/AccountProfile';
import BundleRouter from 'route-middleware/BundleRouter';
import ErrorPage from 'shared/pages/ErrorPage';
import PageApp, {IPageAppProps} from '../PageApp';
import React, {lazy} from 'react';
import {LDPElement} from 'shared/components/LDPElement';

const Activities = lazy(() => import('contacts/pages/account/Activities'));
const Overview = lazy(() => import('contacts/pages/account/Overview'));
const Profile = lazy(() => import('contacts/pages/account/Profile'));

interface IAccountParams {
	[name: string]: string | undefined;
	id: string;
	rangeEnd?: string;
	rangeKey?: string;
	rangeStart?: string;
}

function createAccountTabApp(
	renderContent: (args: IAccountProfileContentArgs) => React.ReactNode
): React.FC<IPageAppProps<IAccountParams>> {
	return (props) => (
		<PageApp {...props}>
			<LDPElement fallback={<ErrorPage />}>
				<AccountProfile renderContent={renderContent} />
			</LDPElement>
		</PageApp>
	);
}

export const AccountActivitiesApp = createAccountTabApp(({accountName}) => (
	<BundleRouter componentProps={{accountName}} data={Activities} />
));

export const AccountApp = createAccountTabApp(({account}) => (
	<BundleRouter componentProps={{account}} data={Overview} />
));

export const AccountProfileApp = createAccountTabApp(({account, loading}) => (
	<BundleRouter componentProps={{account, loading}} data={Profile} />
));
