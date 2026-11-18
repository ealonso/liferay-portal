import BasePage from 'settings/components/base-page/BasePage';
import BlockListCard from '../components/BlockListCard';
import React from 'react';
import {getDefinitions} from 'shared/util/breadcrumbs';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

interface IBlockListProps {
	groupId: string;
}

const BlockList: React.FC<IBlockListProps> = ({groupId, ...otherProps}) => (
	<BasePage
		breadcrumbItems={[
			getDefinitions(),
			{
				href: setUriQueryValues(
					{tab: 'custom'},
					config.eventDefinitionsURL
				),
				label: Liferay.Language.get('events'),
			},
			{active: true, label: Liferay.Language.get('block-list')},
		]}
		pageDescription={Liferay.Language.get(
			'blocked-events-are-not-processed-by-analytics-cloud'
		)}
		pageTitle={Liferay.Language.get('block-list')}
	>
		<BlockListCard {...otherProps} groupId={groupId} />
	</BasePage>
);

export default BlockList;
