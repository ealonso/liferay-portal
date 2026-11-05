import BundleRouter from 'route-middleware/BundleRouter';
import client from 'shared/apollo/client';
import React, {useEffect} from 'react';
import RootLayout from './RootLayout';
import store from 'shared/store';
import Workspaces from 'shared/pages/Workspaces';
import {ApolloProvider} from '@apollo/client';
import {ClayIconSpriteContext} from '@clayui/icon';
import {CLAY_ICONS_SPRITEMAP} from 'shared/components/Icon';
import {ClayTooltipProvider} from '@clayui/tooltip';
import {
	createMemoryRouter,
	RouterProvider,
	useLocation,
} from 'react-router-dom';
import {Provider} from 'react-redux';

const FullPageLoad = () => {
	const {pathname, search} = useLocation();

	useEffect(() => {
		window.location.assign(pathname + search);
	}, [pathname, search]);

	return null;
};

const router = createMemoryRouter([
	{
		children: [
			{element: <BundleRouter data={Workspaces} />, index: true},
			{element: <FullPageLoad />, path: '*'},
		],
		element: <RootLayout />,
	},
]);

const WorkspacesApp = () => (
	<ApolloProvider client={client}>
		<Provider store={store}>
			<ClayIconSpriteContext.Provider value={CLAY_ICONS_SPRITEMAP}>
				<ClayTooltipProvider>
					<RouterProvider router={router} />
				</ClayTooltipProvider>
			</ClayIconSpriteContext.Provider>
		</Provider>
	</ApolloProvider>
);

export default WorkspacesApp;
