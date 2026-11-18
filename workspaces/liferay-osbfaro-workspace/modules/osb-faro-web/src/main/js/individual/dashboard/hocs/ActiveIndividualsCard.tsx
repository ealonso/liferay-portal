import ActiveIndividualsChart from '../components/ActiveIndividualsChart';
import BaseCard from 'shared/components/base-card';
import Card from 'shared/components/Card';
import IndividualSiteMetricsQuery from 'shared/queries/IndividualSiteMetricsQuery';
import React from 'react';
import {compose} from 'redux';
import {graphql, OperationOption} from '@apollo/client/react/hoc';
import {
	mapPropsToOptions,
	mapResultToProps,
} from '../hocs/mappers/site-metrics-query';
import {ReportContainer} from 'shared/components/download-report/DownloadPDFReport';
import {withError} from 'shared/hoc';
import {config} from 'shared/util/config';

const ChartWithData = compose<any>(
	graphql(IndividualSiteMetricsQuery, {
		options: mapPropsToOptions,
		props: mapResultToProps,
	} as OperationOption<object, object>),
	withError({page: false})
)(ActiveIndividualsChart);

const ActiveIndividualsCard = () => {
	const {channelId} = config;

	return (
		<BaseCard
			label={Liferay.Language.get('active-individuals')}
			legacyDropdownRangeKey={false}
			minHeight={536}
			reportContainer={ReportContainer.ActiveIndividualsCard}
			showInterval
		>
			{({interval, rangeSelectors}) => (
				<Card.Body className="justify-content-center">
					<ChartWithData
						active
						channelId={channelId}
						interval={interval}
						rangeSelectors={rangeSelectors}
					/>
				</Card.Body>
			)}
		</BaseCard>
	);
};

export default ActiveIndividualsCard;
