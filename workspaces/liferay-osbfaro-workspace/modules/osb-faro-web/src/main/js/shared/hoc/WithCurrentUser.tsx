import React from 'react';
import {usePageConfig} from 'shared/context/pageConfig';

/**
 * CurrentUser HOC
 * @deprecated Use usePageConfig().currentUser for functional components.
 */
export default <P extends object>(Component: React.ComponentType<P>) =>
	(props: P) => {
		const {currentUser} = usePageConfig();

		return <Component {...props} currentUser={currentUser} />;
	};
