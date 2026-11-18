import * as breadcrumbs from 'shared/util/breadcrumbs';
import AccountDropdown from 'shared/components/AccountDropdown';
import BasePage from 'shared/components/base-page';
import ClayLink from '@clayui/link';
import DownloadCSVReport from 'shared/components/download-report/DownloadCSVReport';
import DownloadPDFReport from 'shared/components/download-report/DownloadPDFReport';
import getCN from 'classnames';
import Loading from 'shared/components/Loading';
import React, {Suspense} from 'react';
import SegmentDropdown from 'shared/components/SegmentDropdown';
import StatesRenderer from 'shared/components/states-renderer/StatesRenderer';
import URLConstants from 'shared/util/url-constants';
import {CSVType} from 'shared/components/download-report/utils';
import {Router} from 'shared/types';
import {useAccountFilter} from 'shared/hooks/useAccountFilter';
import {useChannelContext} from 'shared/context/channel';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {useDataSources} from 'shared/context/dataSources';
import {useLDPEnabled} from 'shared/hooks/useLDPEnabled';
import {useSegmentFilter} from 'shared/hooks/useSegmentFilter';
import {config} from 'shared/util/config';

export interface IDashboardContentArgs {
	channelName?: string | null;
}

interface IDashboardProps extends React.HTMLAttributes<HTMLDivElement> {
	renderContent: (args: IDashboardContentArgs) => React.ReactNode;
	router: Router;
}

export const Dashboard: React.FC<IDashboardProps> = ({
	renderContent,
	router,
}) => {
	const {channelId, groupId = ''} = config;
	const {accountId, accountName, setAccount} = useAccountFilter();
	const {segmentId, segmentName, setSegment} = useSegmentFilter();
	const dataSourceStates = useDataSources();
	const LDPEnabled = useLDPEnabled({groupId});
	const {selectedChannel} = useChannelContext();
	const currentUser = useCurrentUser();

	if (!channelId) {
		return null;
	}

	const authorized = currentUser.isAdmin();
	const selectedChannelName = selectedChannel && selectedChannel.name;

	return (
		<BasePage
			className="sites-dashboard-root"
			documentTitle={Liferay.Language.get('sites')}
		>
			<BasePage.Header
				breadcrumbs={[
					breadcrumbs.getHome({
						channelId,
						groupId,
						label: selectedChannelName,
					}),
				]}
				groupId={groupId}
			>
				<BasePage.Header.TitleSection
					className={getCN({'no-sites-connected': !selectedChannel})}
					title={
						selectedChannel
							? Liferay.Language.get('sites')
							: Liferay.Language.get('no-sites-are-connected')
					}
				/>

				<BasePage.Header.NavBar />
			</BasePage.Header>

			<BasePage.SubHeader>
				{LDPEnabled && (
					<AccountDropdown
						className="mr-2"
						initialAccountId={accountId}
						initialAccountName={accountName}
						onFilterChange={setAccount}
					/>
				)}

				{LDPEnabled && (
					<SegmentDropdown
						className="mr-2"
						initialSegmentId={segmentId}
						initialSegmentName={segmentName}
						onFilterChange={setSegment}
					/>
				)}

				<div className="d-flex justify-content-end w-100">
					{config.friendlyURL === '/sites' && (
						<DownloadPDFReport
							disabled={!!dataSourceStates.empty}
							subtitle={selectedChannelName ?? undefined}
							title={Liferay.Language.get('sites-dashboard')}
						/>
					)}

					{config.friendlyURL === '/search-terms' && (
						<DownloadCSVReport
							disabled={!!dataSourceStates.empty}
							type={CSVType.SearchTerms}
							typeLang={Liferay.Language.get('search-terms')}
						/>
					)}

					{config.friendlyURL === '/pages' && (
						<DownloadCSVReport
							disabled={!!dataSourceStates.empty}
							type={CSVType.Page}
							typeLang={Liferay.Language.get('pages')}
						/>
					)}
				</div>
			</BasePage.SubHeader>

			<BasePage.Context.Provider
				value={{
					accountId,
					accountName,
					filters: {},
					router,
					segmentId,
					segmentName,
				}}
			>
				<BasePage.Body>
					<Suspense fallback={<Loading center />}>
						<StatesRenderer {...dataSourceStates}>
							<StatesRenderer.Empty
								description={
									<>
										{authorized
											? Liferay.Language.get(
													'connect-a-data-source-with-sites-data'
												)
											: Liferay.Language.get(
													'please-contact-your-workspace-administrator-to-add-data-sources'
												)}

										<ClayLink
											className="d-block mb-3"
											href={
												URLConstants.DataSourceConnection
											}
											key="DOCUMENTATION"
											target="_blank"
										>
											{Liferay.Language.get(
												'access-our-documentation-to-learn-more'
											)}
										</ClayLink>

										{authorized && (
											<ClayLink
												button
												className="button-root"
												displayType="primary"
												href={config.dataSourcesURL}
											>
												{Liferay.Language.get(
													'connect-data-source'
												)}
											</ClayLink>
										)}
									</>
								}
								displayCard
								title={Liferay.Language.get(
									'no-sites-have-been-synced-from-data-sources'
								)}
							/>

							<StatesRenderer.Success>
								{renderContent({
									channelName: selectedChannelName,
								})}
							</StatesRenderer.Success>
						</StatesRenderer>
					</Suspense>
				</BasePage.Body>
			</BasePage.Context.Provider>
		</BasePage>
	);
};

export default Dashboard;
