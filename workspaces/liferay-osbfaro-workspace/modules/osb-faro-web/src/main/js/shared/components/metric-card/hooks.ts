import {DocumentNode, useQuery} from '@apollo/client';
import {usePageConfig} from 'shared/context/pageConfig';

import {fetchPolicyDefinition} from 'shared/util/graphql';
import {getFilters, RawFilters} from 'shared/util/filter';
import {
	getSafeDecodedURIComponent,
	getSafeRangeSelectors,
	getSafeTouchpoint,
} from 'shared/util/util';
import {ICommonVariables, Interval, RangeSelectors} from 'shared/types';

export const useAssetVariables = (variables: ICommonVariables) => {
	const {
		channelId = '',
		params: {assetId = '', title = '', touchpoint = ''},
	} = usePageConfig();

	const {type, ...commonVariables} = variables;

	return {
		assetId: getSafeDecodedURIComponent(assetId),
		touchpoint: getSafeTouchpoint(touchpoint),
		...(type !== 'objectEntry' && {
			channelId,
			title: getSafeDecodedURIComponent(title),
		}),
		...commonVariables,
	};
};

type TMetricQueryParams = {
	accountId?: string;
	experienceId?: string;
	filters: RawFilters;
	interval: Interval;
	Query: DocumentNode;
	rangeSelectors: RangeSelectors;
	segmentId?: string;
	variables: (commonVariables: ICommonVariables) => any;
};

const buildQueryVariables = ({
	accountId,
	experienceId,
	filters,
	interval,
	rangeSelectors,
	segmentId,
	variables,
}: Omit<TMetricQueryParams, 'Query'>) =>
	variables({
		interval,
		...getFilters(filters),
		...getSafeRangeSelectors(rangeSelectors),
		...(accountId && {accountId}),
		...(experienceId && {experienceId}),
		...(segmentId && {segmentId}),
	});

export const useMetricQuery = ({
	Query,
	accountId,
	experienceId,
	filters,
	interval,
	rangeSelectors,
	segmentId,
	variables,
}: TMetricQueryParams) => {
	const {data, error, loading} = useQuery(Query, {
		fetchPolicy: fetchPolicyDefinition(rangeSelectors),
		variables: buildQueryVariables({
			accountId,
			experienceId,
			filters,
			interval,
			rangeSelectors,
			segmentId,
			variables,
		}),
	});

	return {data, error, loading};
};
