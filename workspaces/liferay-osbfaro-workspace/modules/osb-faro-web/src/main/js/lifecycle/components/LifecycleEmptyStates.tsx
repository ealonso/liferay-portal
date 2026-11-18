import ClayLink from '@clayui/link';
import DataSourceEmptyState from 'shared/components/DataSourceEmptyState';
import ErrorDisplay from 'shared/components/ErrorDisplay';
import Loading from 'shared/components/Loading';
import NoResultsDisplay from 'shared/components/NoResultsDisplay';
import React from 'react';
import {Sizes} from 'shared/util/constants';
import {AccountDataStatus} from 'lifecycle/utils/types';
import {config} from 'shared/util/config';

export const ConfigureLifecycleEmptyState = () => (
	<NoResultsDisplay
		description={Liferay.Language.get(
			'complete-the-configuration-to-start-seeing-insights'
		)}
		displayCard
		icon={{
			border: false,
			size: Sizes.XXXLarge,
			symbol: 'ac_lifecycle_empty',
		}}
		spacer
		title={Liferay.Language.get('configure-a-new-lifecycle')}
	>
		<ClayLink
			button
			className="button-root mt-1"
			displayType="primary"
			href={config.editLifecycleURL}
		>
			{Liferay.Language.get('new-lifecycle')}
		</ClayLink>
	</NoResultsDisplay>
);

export const NoDataSourcesEmptyState = ({
	authorized,
}: {
	authorized: boolean;
}) => (
	<DataSourceEmptyState
		authorized={authorized}
		description={
			authorized
				? Liferay.Language.get(
						'connect-a-data-source-to-sync-lifecycle-stages'
					)
				: Liferay.Language.get(
						'please-contact-your-workspace-administrator-to-add-data-sources'
					)
		}
		title={Liferay.Language.get('no-data-sources-are-connected')}
	/>
);

export const NoAccountDataEmptyState = ({
	authorized,
}: {
	authorized: boolean;
}) => (
	<DataSourceEmptyState
		authorized={authorized}
		description={
			authorized
				? Liferay.Language.get(
						'connect-a-data-source-containing-account-data'
					)
				: Liferay.Language.get(
						'contact-an-administrator-to-connect-a-data-source-containing-account-data'
					)
		}
		title={Liferay.Language.get('no-account-data-available')}
	/>
);

export const LifecycleAccountDataEmptyState = ({
	authorized,
	onReload,
	status,
}: {
	authorized: boolean;
	onReload: () => void;
	status: AccountDataStatus;
}) => {
	if (status === AccountDataStatus.NoDataSources) {
		return <NoDataSourcesEmptyState authorized={authorized} />;
	}

	if (status === AccountDataStatus.Error) {
		return <ErrorDisplay onReload={onReload} spacer />;
	}

	if (status === AccountDataStatus.NoAccountData) {
		return <NoAccountDataEmptyState authorized={authorized} />;
	}

	return <Loading />;
};
