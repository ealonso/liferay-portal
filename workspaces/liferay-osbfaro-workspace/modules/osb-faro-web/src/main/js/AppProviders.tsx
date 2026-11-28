import ChannelProvider from 'shared/context/channel';
import client from 'shared/apollo/client';
import React, {useState} from 'react';
import store from 'shared/store';
import UnassignedSegmentsProvider from 'shared/context/unassignedSegments';
import {ApolloProvider} from '@apollo/client';

import {ClayIconSpriteContext} from '@clayui/icon';
import {CLAY_ICONS_SPRITEMAP} from 'shared/components/Icon';
import {ClayTooltipProvider} from '@clayui/tooltip';
import {OnboardingContext} from 'shared/context/onboarding';
import {Provider} from 'react-redux';

const ONBOARDING_TRIGGERED_KEY = 'onboardingTriggered';

interface IAppProvidersProps {
	children: React.ReactNode;
}

const AppProviders: React.FC<IAppProvidersProps> = ({children}) => {
	const [onboardingTriggered, setOnboardingTriggered] = useState(() => {
		try {
			return (
				window.sessionStorage.getItem(ONBOARDING_TRIGGERED_KEY) ===
				'true'
			);
		}
		catch (error) {
			return false;
		}
	});

	return (
		<ApolloProvider client={client}>
			<Provider store={store}>
				<ClayIconSpriteContext.Provider value={CLAY_ICONS_SPRITEMAP}>
					<UnassignedSegmentsProvider>
						<OnboardingContext.Provider
							value={{
								onboardingTriggered,
								setOnboardingTriggered: () => {
									try {
										window.sessionStorage.setItem(
											ONBOARDING_TRIGGERED_KEY,
											'true'
										);
									}
									catch (error) {}

									setOnboardingTriggered(true);
								},
							}}
						>
							<ChannelProvider>
								<ClayTooltipProvider>
									<div>{children}</div>
								</ClayTooltipProvider>
							</ChannelProvider>
						</OnboardingContext.Provider>
					</UnassignedSegmentsProvider>
				</ClayIconSpriteContext.Provider>
			</Provider>
		</ApolloProvider>
	);
};

export default AppProviders;
