import * as API from 'shared/api';
import * as breadcrumbs from 'shared/util/breadcrumbs';
import BasePage from 'shared/components/base-page';
import DownloadCSVReport from 'shared/components/download-report/DownloadCSVReport';
import Loading from 'shared/components/Loading';
import React, {Suspense, useContext} from 'react';
import {buildHeaderSubtitle, IndividualHeaderData} from './utils/utils';
import {ChannelContext} from 'shared/context/channel';
import {compose, withIndividual} from 'shared/hoc';
import {CSVType} from 'shared/components/download-report/utils';
import {useDataSources} from 'shared/context/dataSources';
import {useLDPEnabled} from 'shared/hooks/useLDPEnabled';
import {useRequest} from 'shared/hooks/useRequest';
import {config} from 'shared/util/config';

interface IIndividual {
	id: string;
	name?: string;
	toJS: () => IndividualHeaderData;
}

export interface IIndividualProfileContentArgs {
	individual: IIndividual;
	LDPEnabled: boolean;
}

interface IIndividualProfileProps {
	channelId: string;
	groupId: string;
	individual: IIndividual;
	renderContent: (args: IIndividualProfileContentArgs) => React.ReactNode;
}

const IndividualProfile: React.FC<IIndividualProfileProps> = ({
	channelId,
	groupId,
	individual,
	renderContent,
}) => {
	const dataSourceStates = useDataSources();

	const LDPEnabled = useLDPEnabled({groupId});

	const {selectedChannel} = useContext(ChannelContext);

	const entityName = individual.name || Liferay.Language.get('unknown');

	const {data: dataSourceData} = useRequest({
		dataSourceFn: API.dataSource.search,
		variables: {
			delta: 1,
			groupId,
		},
	});

	return (
		<BasePage
			className={
				config.friendlyURL === '/individual'
					? 'overview-root'
					: undefined
			}
			documentTitle={`${entityName} - ${Liferay.Language.get(
				'individuals'
			)}`}
		>
			<BasePage.Header
				breadcrumbs={[
					breadcrumbs.getHome({
						channelId,
						groupId,
						label: selectedChannel && selectedChannel.name,
					}),
					breadcrumbs.getIndividuals({LDPEnabled}),
					breadcrumbs.getEntityName({label: entityName}),
				]}
				groupId={groupId}
			>
				<BasePage.Header.TitleSection
					subtitle={buildHeaderSubtitle(individual.toJS())}
					title={entityName}
				/>

				<BasePage.Header.NavBar />
			</BasePage.Header>

			{config.friendlyURL === '/individual' &&
				dataSourceData?.total > 0 && (
					<BasePage.SubHeader>
						<div className="d-flex justify-content-end w-100">
							<DownloadCSVReport
								disabled={!!dataSourceStates.empty}
								individualId={individual.id}
								type={CSVType.Event}
								typeLang={Liferay.Language.get('events')}
							/>
						</div>
					</BasePage.SubHeader>
				)}

			<BasePage.Body>
				<Suspense fallback={<Loading />}>
					{renderContent({individual, LDPEnabled})}
				</Suspense>
			</BasePage.Body>
		</BasePage>
	);
};

export default compose(withIndividual)(IndividualProfile);
