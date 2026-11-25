import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RecommendationStepCard from '../components/recommendation-step-card';
import withRecommendation from 'shared/hoc/WithRecommendation';
import {compose} from 'redux';
import {get} from 'lodash';
import {getRecommendations} from 'shared/util/breadcrumbs';
import {Job} from '../utils/utils';
import {Router} from 'shared/types';
import {usePageConfig} from 'shared/context/pageConfig';
import {setUriQueryValues} from 'shared/util/url';

interface IEditProps {
	job: Job;
	router: Router;
}

const Edit: React.FC<IEditProps> = ({job, router}) => {
	const config = usePageConfig();

	const {recommendationURL} = config;

	const {jobId} = router.params;

	const name = get(job, 'name');

	return (
		<BasePage
			breadcrumbItems={[
				getRecommendations(config),
				{
					active: true,
					label: name,
				},
			]}
			pageDescription={Liferay.Language.get(
				'item-similarity-model-uses-items-and-iteractions-for-training'
			)}
			pageTitle={name}
		>
			<div className="row">
				<div className="col-xl-8">
					<RecommendationStepCard
						cancelHref={setUriQueryValues(
							{jobId},
							recommendationURL
						)}
						job={job}
						router={router}
					/>
				</div>
			</div>
		</BasePage>
	);
};

export default compose<React.ComponentType<any>>(withRecommendation)(Edit);
