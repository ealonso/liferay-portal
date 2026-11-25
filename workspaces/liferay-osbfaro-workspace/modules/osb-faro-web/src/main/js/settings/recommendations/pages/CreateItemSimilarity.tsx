import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RecommendationStepCard from '../components/recommendation-step-card';
import {getRecommendations} from 'shared/util/breadcrumbs';
import {JobTypes} from 'shared/util/constants';
import {Router} from 'shared/types';
import {usePageConfig} from 'shared/context/pageConfig';

interface ICreateItemSimilarityProps {
	router: Router;
}

const CreateItemSimilarity: React.FC<ICreateItemSimilarityProps> = ({
	router,
}) => {
	const config = usePageConfig();

	const {recommendationsURL} = config;

	return (
		<BasePage
			breadcrumbItems={[
				getRecommendations(config),
				{
					active: true,
					label: Liferay.Language.get('new-model'),
				},
			]}
			pageDescription={Liferay.Language.get(
				'item-similarity-model-uses-items-and-iteractions-for-training'
			)}
			pageTitle={Liferay.Language.get('new-item-similarity-model')}
		>
			<div className="row">
				<div className="col-xl-8">
					<RecommendationStepCard
						cancelHref={recommendationsURL}
						jobType={JobTypes.ItemSimilarity}
						router={router}
					/>
				</div>
			</div>
		</BasePage>
	);
};

export default CreateItemSimilarity;
