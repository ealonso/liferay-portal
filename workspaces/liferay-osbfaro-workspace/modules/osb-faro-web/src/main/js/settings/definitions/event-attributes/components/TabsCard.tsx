import BundleRouter from 'route-middleware/BundleRouter';
import Card from 'shared/components/Card';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import Loading from 'shared/components/Loading';
import React, {lazy, Suspense, useState} from 'react';
import {config} from 'shared/util/config';

const AttributeList = lazy(
	() => import(/* webpackChunkName: "AttributeList" */ './AttributeList')
);

const GlobalAttributeList = lazy(
	() =>
		import(

			/* webpackChunkName: "GlobalAttributeList" */ './GlobalAttributeList'
		)
);

const TabsCard: React.FC = () => {
	const {tab} = config.params;

	const globalAttributeTab = tab !== 'local';

	const navigationItems = config.navigationItems ?? [];

	const initialItem =
		navigationItems.find(({active}) => active) ?? navigationItems[0];

	const [activeLabel, setActiveLabel] = useState(initialItem?.label);

	return (
		<Card key="cardContainer" pageDisplay>
			<ClayNavigationBar
				className="page-subnav mx-4 my-3"
				triggerLabel={activeLabel}
			>
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

			<Suspense fallback={<Loading />}>
				{globalAttributeTab ? (
					<BundleRouter data={GlobalAttributeList} />
				) : (
					<BundleRouter data={AttributeList} />
				)}
			</Suspense>
		</Card>
	);
};
export default TabsCard;
