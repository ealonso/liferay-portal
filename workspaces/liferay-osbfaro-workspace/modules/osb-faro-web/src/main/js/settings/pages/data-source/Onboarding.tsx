import ConnectConnector from './ConnectConnector';
import ConnectLiferayDXP from './ConnectLiferayDXP';
import ConnectMarketoCampaign from './ConnectMarketoCampaign';
import ConnectSalesforce from './ConnectSalesforce';
import React from 'react';
import RouteNotFound from 'shared/components/RouteNotFound';
import {DataSourceTypes} from 'shared/util/constants';
import {getConnectorConfig} from 'settings/components/3rd-party-connector/registry';
import {usePageConfig} from 'shared/context/pageConfig';

const PAGE_MAP = {
	[DataSourceTypes.Liferay]: ConnectLiferayDXP,
	[DataSourceTypes.MarketoCampaign]: ConnectMarketoCampaign,
	[DataSourceTypes.Salesforce]: ConnectSalesforce,
};

const DataSourceOnboarding = () => {
	const {
		params: {id = ''},
	} = usePageConfig();

	const normalizedId = id.toUpperCase();

	if (getConnectorConfig(normalizedId)) {
		return <ConnectConnector />;
	}

	const Component = (PAGE_MAP as {[key: string]: React.ComponentType})[
		normalizedId
	];

	if (Component) {
		return <Component />;
	}

	return <RouteNotFound />;
};

export default DataSourceOnboarding;
