import RecommendationQuery from 'settings/recommendations/queries/RecommendationQuery';
import {compose} from 'redux';
import {graphql} from '@apollo/client/react/hoc';
import {safeResultToProps} from 'shared/util/mappers';
import {withError, withLoading, withNull} from 'shared/hoc/util';
import {config} from 'shared/util/config';

const withRecommendation = compose(
	graphql(RecommendationQuery, {
		options: ({
			router: {
				params: {jobId},
			},
		}) => ({
			variables: {
				jobId,
			},
		}),
		props: safeResultToProps(({jobById}) => ({job: jobById})),
	}),
	withLoading(),
	withError({page: true}),
	withNull('job', {
		entityType: Liferay.Language.get('recommendation-model'),
		url: config.recommendationsURL,
	})
);

export default withRecommendation;
