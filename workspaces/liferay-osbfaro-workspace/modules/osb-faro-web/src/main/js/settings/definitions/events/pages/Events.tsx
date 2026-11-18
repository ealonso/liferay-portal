import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import TabsCard from '../components/TabsCard';
import {getDefinitions} from 'shared/util/breadcrumbs';
import {usePageConfig} from 'shared/context/pageConfig';

const Events: React.FC = () => {
	const config = usePageConfig();

	return (
		<BasePage
			breadcrumbItems={[
				getDefinitions(config),
				{active: true, label: Liferay.Language.get('events')},
			]}
			pageDescription={Liferay.Language.get(
				'this-is-the-data-model-of-events-sent-to-analytics-cloud'
			)}
			pageTitle={Liferay.Language.get('events')}
		>
			<TabsCard />
		</BasePage>
	);
};

export default Events;
