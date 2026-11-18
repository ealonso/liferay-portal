import BundleRouter from 'route-middleware/BundleRouter';
import Card from 'shared/components/Card';
import ClayIcon from 'shared/components/Icon';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import Loading from 'shared/components/Loading';
import React, {lazy, Suspense, useState} from 'react';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

const CustomEventList = lazy(
	() => import(/* webpackChunkName: "CustomEventList" */ './CustomEventList')
);

const EventList = lazy(
	() => import(/* webpackChunkName: "EventList" */ './EventList')
);

const TabsCard: React.FC = () => {
	const {tab} = config.params;

	const customEventTab = tab === 'custom';

	const navigationItems = config.navigationItems ?? [];

	const initialItem =
		navigationItems.find(({active}) => active) ?? navigationItems[0];

	const [activeLabel, setActiveLabel] = useState(initialItem?.label);

	return (
		<Card key="cardContainer" pageDisplay>
			<ClayNavigationBar className="my-3" triggerLabel={activeLabel}>
				{navigationItems.map(({active, href, label}) => (
					<ClayNavigationBar.Item active={active} key={label}>
						<ClayLink
							href={href}
							onClick={() => setActiveLabel(label)}
						>
							{label}
						</ClayLink>
					</ClayNavigationBar.Item>
				))}
			</ClayNavigationBar>

			{customEventTab && (
				<ClayLink
					borderless
					button
					className="block-list-button button-root m-3"
					displayType="secondary"
					href={setUriQueryValues(
						{
							tab: 'block-list',
						},
						config.eventDefinitionsURL
					)}
					small
				>
					<ClayIcon className="icon-root mr-2" symbol="ac_block" />

					{Liferay.Language.get('block-list')}
				</ClayLink>
			)}

			<Suspense fallback={<Loading />}>
				{customEventTab ? (
					<BundleRouter data={CustomEventList} />
				) : (
					<BundleRouter data={EventList} />
				)}
			</Suspense>
		</Card>
	);
};

export default TabsCard;
