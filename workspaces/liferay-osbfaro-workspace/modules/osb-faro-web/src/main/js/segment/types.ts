export type Metric = {
	previousValue: number;
	trend: Trend;
	value: number;
};

export enum MetricNames {
	AverageSegmentMembershipDurationMetric = 'averageSegmentMembershipDurationMetric',
	EntryRateMetric = 'entryRateMetric',
	ExitRateMetric = 'exitRate',
	TotalMembersMetric = 'totalMembersMetric',
}

export type TotalMembersMetric = Metric & {
	totalIndividuals: number;
};

export type Trend = {
	percentage: number;
	trendClassification: TrendClassification;
};

export enum TrendClassification {
	Negative = 'NEGATIVE',
	Neutral = 'NEUTRAL',
	Positive = 'POSITIVE',
}
