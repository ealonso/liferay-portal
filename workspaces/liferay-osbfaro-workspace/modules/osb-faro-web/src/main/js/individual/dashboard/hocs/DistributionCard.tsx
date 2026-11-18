import ClayLink from '@clayui/link';
import DistributionCard from 'contacts/components/distribution-card';
import NoResultsDisplay from 'shared/components/NoResultsDisplay';
import React from 'react';
import URLConstants from 'shared/util/url-constants';
import {connect, ConnectedProps} from 'react-redux';
import {fetchIndividualsDistribution} from 'shared/actions/distributions';
import {toPromise} from 'shared/util/validators';
import {config} from 'shared/util/config';

const connector = connect(null, {
	fetchDistribution: fetchIndividualsDistribution,
});

type PropsFromRedux = ConnectedProps<typeof connector>;

interface IIndividualsDistributionCardProps
	extends React.HTMLAttributes<HTMLElement>,
		PropsFromRedux {
	id?: string;
	showAddDataSource?: boolean;
}

const IndividualsDistributionCard: React.FC<
	IIndividualsDistributionCardProps
> = ({fetchDistribution, id, ...otherProps}) => {
	const {channelId = '', groupId = ''} = config;

	return (
		<DistributionCard
			channelId={channelId}
			distributionKey="individualsDashboard"
			fetchDistribution={(name) => toPromise(fetchDistribution(name))}
			groupId={groupId}
			id={id ?? ''}
			noResultsRenderer={() => (
				<NoResultsDisplay
					description={
						<>
							{Liferay.Language.get(
								'try-choosing-a-different-breakdown'
							)}

							<ClayLink
								className="d-block"
								href={
									URLConstants.IndividualsDashboardBreakdownDocumentation
								}
								key="DOCUMENTATION"
								target="_blank"
							>
								{Liferay.Language.get(
									'learn-more-about-distribution'
								)}
							</ClayLink>
						</>
					}
					title={Liferay.Language.get('no-results-were-found')}
				/>
			)}
			viewAllLink={config.individualsDistributionURL}
			{...otherProps}
		/>
	);
};

export default connector(IndividualsDistributionCard);
