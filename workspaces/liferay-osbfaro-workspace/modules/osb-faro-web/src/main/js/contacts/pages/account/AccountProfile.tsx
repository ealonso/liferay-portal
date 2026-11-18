import * as API from 'shared/api';
import * as breadcrumbs from 'shared/util/breadcrumbs';
import BasePage from 'shared/components/base-page';
import ErrorPage from 'shared/pages/ErrorPage';
import Loading from 'shared/components/Loading';
import React, {Suspense, useContext} from 'react';
import {ChannelContext} from 'shared/context/channel';
import {IAccount} from './components/AccountInfo';
import {useRequest} from 'shared/hooks/useRequest';
import {config} from 'shared/util/config';

export interface IAccountProfileContentArgs {
	account: IAccount;
	accountName: string;
	loading: boolean;
}

interface IAccountProfileProps {
	renderContent: (args: IAccountProfileContentArgs) => React.ReactNode;
}

const AccountProfile: React.FC<IAccountProfileProps> = ({renderContent}) => {
	const {selectedChannel} = useContext(ChannelContext);

	const {channelId, groupId} = config;

	const {id} = config.params;

	const {data, error, loading} = useRequest({
		dataSourceFn: API.accounts.fetch,
		variables: {accountId: id!, channelId: channelId!, groupId: groupId!},
	});

	if (loading) {
		return <Loading />;
	}

	if (error || !data) {
		return (
			<ErrorPage
				href={config.accountsURL}
				linkLabel={Liferay.Language.get('go-to-accounts')}
				message={Liferay.Language.get(
					'the-account-you-are-looking-for-does-not-exist'
				)}
				subtitle={Liferay.Language.get('account-was-not-found')}
			/>
		);
	}

	const account: IAccount = data;

	const accountName: string =
		account.accountName || Liferay.Language.get('account');

	return (
		<BasePage
			documentTitle={`${accountName} - ${Liferay.Language.get(
				'account'
			)}`}
			fluid
		>
			<BasePage.Header
				breadcrumbs={[
					breadcrumbs.getHome({
						channelId: channelId!,
						groupId: groupId!,
						label: selectedChannel?.name,
					}),
					breadcrumbs.getAccounts(),
					breadcrumbs.getEntityName({label: accountName}),
				]}
				groupId={groupId!}
			>
				<BasePage.Row>
					<BasePage.Header.TitleSection title={accountName} />
				</BasePage.Row>

				<BasePage.Header.NavBar />
			</BasePage.Header>

			<BasePage.Body>
				<Suspense fallback={<Loading />}>
					{renderContent({account, accountName, loading})}
				</Suspense>
			</BasePage.Body>
		</BasePage>
	);
};

export default AccountProfile;
