import React from 'react';
import {config} from 'shared/util/config';

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
	const params = {
		...config.params,
		channelId: config.channelId,
		groupId: config.groupId,
	};
	const query = config.params;

	if (destructured) {
		return <Component {...query} {...params} {...componentProps} />;
	}

	return <Component router={{params, query}} {...componentProps} />;
};

export default BundleRouter;
