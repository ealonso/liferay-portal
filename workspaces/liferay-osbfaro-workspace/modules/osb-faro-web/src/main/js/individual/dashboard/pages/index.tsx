import * as breadcrumbs from 'shared/util/breadcrumbs';
import BasePage from 'shared/components/base-page';
import DownloadPDFReport from 'shared/components/download-report/DownloadPDFReport';
import Loading from 'shared/components/Loading';
import React, {Suspense} from 'react';
import {CSVType} from 'shared/components/download-report/utils';
import {DownloadStaticCSVReport} from 'shared/components/download-report/DownloadStaticCSVReport';
import {sub} from 'shared/util/lang';
import {useChannelContext} from 'shared/context/channel';
import {useDataSources} from 'shared/context/dataSources';
import {config} from 'shared/util/config';

interface IDashboardProps {
	children: React.ReactNode;
}

const Dashboard: React.FC<IDashboardProps> = ({children}) => {
	const dataSourceStates = useDataSources();
	const {selectedChannel} = useChannelContext();
	const {channelId = '', groupId = ''} = config;

	return (
		<BasePage
			className="individuals-dashboard-root"
			documentTitle={Liferay.Language.get('individuals')}
		>
			<BasePage.Header
				breadcrumbs={[
					breadcrumbs.getHome({
						channelId,
						groupId,
						label: selectedChannel && selectedChannel.name,
					}),
				]}
				groupId={groupId}
			>
				<BasePage.Header.TitleSection
					title={Liferay.Language.get('individuals')}
				/>

				<BasePage.Header.NavBar />
			</BasePage.Header>

			{config.friendlyURL === '/individuals' && (
				<BasePage.SubHeader>
					<div className="d-flex justify-content-end w-100">
						<DownloadPDFReport
							dateRangeDescription={
								sub(
									Liferay.Language.get(
										'only-select-a-date-range-if-you-want-to-modify-the-current-date-filter-for-the-x-report'
									),
									[Liferay.Language.get('active-individuals')]
								) as string
							}
							disabled={!!dataSourceStates.empty}
							subtitle={selectedChannel?.name}
							title={Liferay.Language.get(
								'individuals-dashboard'
							)}
						/>
					</div>
				</BasePage.SubHeader>
			)}

			{config.friendlyURL === '/known-individuals' && (
				<BasePage.SubHeader>
					<div className="d-flex justify-content-end w-100">
						<DownloadStaticCSVReport
							disabled={!!dataSourceStates.empty}
							type={CSVType.Individual}
							typeLang={Liferay.Language.get('individuals')}
						/>
					</div>
				</BasePage.SubHeader>
			)}

			<Suspense fallback={<Loading />}>{children}</Suspense>
		</BasePage>
	);
};

export default Dashboard;
