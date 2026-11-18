import * as breadcrumbs from 'shared/util/breadcrumbs';
import BasePage from 'shared/components/base-page';
import IndividualsOverviewCDP from './IndividualsOverviewCDP';
import React from 'react';
import {useChannelContext} from 'shared/context/channel';
import {config} from 'shared/util/config';

const IndividualsDashboardCDP = () => {
	const {channelId = '', groupId = ''} = config;
	const {selectedChannel} = useChannelContext();

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
					breadcrumbs.getEntityName({
						label: Liferay.Language.get('individuals'),
					}),
				]}
				groupId={groupId}
			>
				<BasePage.Header.TitleSection
					title={Liferay.Language.get('individuals')}
				/>
			</BasePage.Header>

			<IndividualsOverviewCDP />
		</BasePage>
	);
};

export default IndividualsDashboardCDP;
