import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RecommendationList from '../hocs/RecommendationList';
import {Router} from 'shared/types';

interface IRecommendationsProps {
	router: Router;
}

const Recommendations: React.FC<IRecommendationsProps> = ({router}) => (
	<BasePage
		pageDescription={Liferay.Language.get(
			'create-and-train-machine-learning-models-to-use-in-your-recommendations'
		)}
		pageTitle={Liferay.Language.get('recommendations')}
	>
		<RecommendationList router={router} />
	</BasePage>
);

export default Recommendations;
