import BasePage from 'shared/components/base-page';
import ClayLink from '@clayui/link';
import Interests from '../hocs/Interests';
import React from 'react';
import StatesRenderer from 'shared/components/states-renderer/StatesRenderer';
import URLConstants from 'shared/util/url-constants';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {useDataSources} from 'shared/context/dataSources';
import {config} from 'shared/util/config';

const InterestsPage = () => {
	const currentUser = useCurrentUser();
	const authorized = currentUser.isAdmin();

	const dataSourceStates = useDataSources();

	return (
		<BasePage.Body pageContainer>
			<StatesRenderer {...dataSourceStates}>
				<StatesRenderer.Empty
					description={
						<>
							{authorized
								? Liferay.Language.get(
										'connect-a-data-source-with-sites-data'
									)
								: Liferay.Language.get(
										'please-contact-your-workspace-administrator-to-add-data-sources'
									)}

							<ClayLink
								className="d-block mb-3"
								href={URLConstants.DataSourceConnection}
								key="DOCUMENTATION"
								target="_blank"
							>
								{Liferay.Language.get(
									'access-our-documentation-to-learn-more'
								)}
							</ClayLink>

							{authorized && (
								<ClayLink
									button
									className="button-root"
									displayType="primary"
									href={config.dataSourcesURL}
								>
									{Liferay.Language.get(
										'connect-data-source'
									)}
								</ClayLink>
							)}
						</>
					}
					displayCard
					title={Liferay.Language.get(
						'no-sites-have-been-synced-from-data-sources'
					)}
				/>

				<StatesRenderer.Success>
					<div className="individuals-dashboard-interests-root">
						<Interests />
					</div>
				</StatesRenderer.Success>
			</StatesRenderer>
		</BasePage.Body>
	);
};

export default InterestsPage;
