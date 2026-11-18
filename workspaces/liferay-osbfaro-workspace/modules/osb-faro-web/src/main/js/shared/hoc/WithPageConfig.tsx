import React from 'react';
import {Config} from 'shared/util/config';
import {usePageConfig} from 'shared/context/pageConfig';

export default <P extends {pageConfig: Config}>(
		Component: React.ComponentType<P>
	) =>
	(props: Omit<P, 'pageConfig'>) => (
		<Component {...(props as P)} pageConfig={usePageConfig()} />
	);
