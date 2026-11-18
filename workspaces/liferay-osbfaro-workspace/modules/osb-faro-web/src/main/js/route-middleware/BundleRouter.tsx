import React from 'react';
import {usePageConfig} from 'shared/context/pageConfig';

interface IBundleRouterProps {
	componentProps?: Record<string, unknown>;
	data: React.ComponentType<any>;
	destructured?: boolean;
}

const BundleRouter = ({
	componentProps = {},
	data: Component,
	destructured = true,
}: IBundleRouterProps) => {
	const config = usePageConfig();

	const params = {
		...config.params,
		channelId: config.channelId,
		groupId: config.groupId,
	};
	const {params: query} = config;

	if (destructured) {
		return <Component {...query} {...params} {...componentProps} />;
	}

	return <Component router={{params, query}} {...componentProps} />;
};

export default BundleRouter;
