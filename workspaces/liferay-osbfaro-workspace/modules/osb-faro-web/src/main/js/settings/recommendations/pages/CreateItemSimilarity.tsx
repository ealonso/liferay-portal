import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RecommendationStepCard from '../components/recommendation-step-card';
import {getRecommendations} from 'shared/util/breadcrumbs';
import {JobTypes} from 'shared/util/constants';
import {Router} from 'shared/types';
import {withAdminPermission} from 'shared/hoc';
import {config} from 'shared/util/config';

interface ICreateItemSimilarityProps {
	router: Router;
}

const CreateItemSimilarity: React.FC<ICreateItemSimilarityProps> = ({
	router,
}) => (
	<BasePage
		breadcrumbItems={[
			getRecommendations(),
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
					cancelHref={config.recommendationsURL}
					jobType={JobTypes.ItemSimilarity}
					router={router}
				/>
			</div>
		</div>
	</BasePage>
);

export default withAdminPermission(CreateItemSimilarity);
