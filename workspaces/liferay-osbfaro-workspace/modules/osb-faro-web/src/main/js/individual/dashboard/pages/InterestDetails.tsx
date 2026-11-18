import BasePage from 'shared/components/base-page';
import ClayIcon from 'shared/components/Icon';
import ClayLink from '@clayui/link';
import InterestDetails from 'shared/components/InterestDetails';
import React from 'react';
import {Router} from 'shared/types';
import {config} from 'shared/util/config';
import {setUriQueryValues} from 'shared/util/url';

interface IInterestDetailsProps extends React.HTMLAttributes<HTMLDivElement> {
	router: Router;
}

const InterestDetailsPage: React.FC<IInterestDetailsProps> = ({router}) => {
	const {
		query: {rangeKey},
	} = router;

	return (
		<BasePage.Body
			className="individuals-dashboard-interest-details-root"
			pageContainer
		>
			<div className="back-button-root mb-2">
				<ClayLink
					borderless
					button
					displayType="secondary"
					href={setUriQueryValues(
						{rangeKey},
						config.individualsInterestsURL
					)}
				>
					<ClayIcon
						className="icon-root mr-2"
						symbol="angle-left-small"
					/>

					{Liferay.Language.get('back-to-interests')}
				</ClayLink>
			</div>

			<InterestDetails router={router} />
		</BasePage.Body>
	);
};

export default InterestDetailsPage;
