import AcquisitionsCard from 'sites/components/AcquisitionsCard';
import BasePage from 'shared/components/base-page';
import CohortAnalysisCard from 'sites/hocs/CohortAnalysisCard';
import DevicesCard from 'sites/hocs/DevicesCard';
import InterestsCard from 'sites/hocs/InterestsCard';
import LocationsCard from 'sites/hocs/LocationsCard';
import React, {FC, useContext} from 'react';
import SearchTermsCard from 'sites/hocs/SearchTermsCard';
import SiteMetricsCard from 'sites/components/SiteMetricCard';
import TopPagesCard from 'sites/components/TopPagesCard';
import VisitorsByTimeCard from 'sites/hocs/VisitorsByTimeCard';
import {CompositionTypes} from 'shared/util/constants';
import {isString, pickBy} from 'lodash';
import {sub} from 'shared/util/lang';
import {setUriQueryValues} from 'shared/util/url';
import {config} from 'shared/util/config';

interface IOverviewProps extends React.HTMLAttributes<HTMLDivElement> {
	channelName: string;
}

const Overview: FC<IOverviewProps> = ({channelName}) => {
	const {accountId, accountName, segmentId, segmentName} = useContext(
		BasePage.Context
	);

	return (
		<div className="sites-dashboard-overview-root overview-root">
			<div className="row">
				<div className="col-xl-12">
					<SiteMetricsCard
						label={
							sub(Liferay.Language.get('x-activities'), [
								channelName,
							]) as string
						}
						showIntervals
					/>
				</div>
			</div>

			<div className="row">
				<div className="col-xl-6">
					<TopPagesCard
						className="top-pages-card-root"
						footer={{
							href: setUriQueryValues(
								pickBy(
									{
										accountId,
										accountName,
										segmentId,
										segmentName,
									},
									isString
								),
								config.pagesURL
							),
							label: Liferay.Language.get('view-all'),
						}}
						label={Liferay.Language.get('top-pages')}
						legacyDropdownRangeKey={false}
						minHeight={575}
					/>
				</div>

				<div className="col-xl-6">
					<AcquisitionsCard
						className="acquisitions-card-root"
						compositionBagName={CompositionTypes.Acquisitions}
						label={Liferay.Language.get('acquisitions')}
						legacyDropdownRangeKey={false}
						minHeight={575}
					/>
				</div>
			</div>

			<div className="row">
				<div className="col-xl-4">
					<VisitorsByTimeCard
						className="visitors-by-time-card"
						label={Liferay.Language.get('visitors-by-day-and-time')}
						minHeight={545}
					/>
				</div>

				<div className="col-xl-4">
					<SearchTermsCard minHeight={545} />
				</div>

				<div className="col-xl-4">
					<InterestsCard minHeight={545} />
				</div>
			</div>

			<div className="row">
				<div className="col-xl-6">
					<LocationsCard
						label={Liferay.Language.get('sessions-by-location')}
						legacyDropdownRangeKey={false}
						metricLabel={Liferay.Language.get('sessions')}
					/>
				</div>

				<div className="col-xl-6">
					<DevicesCard
						label={Liferay.Language.get('session-technology')}
						legacyDropdownRangeKey={false}
						metricLabel={Liferay.Language.get('sessions')}
					/>
				</div>
			</div>

			<div className="row">
				<div className="col-xl-12">
					<CohortAnalysisCard />
				</div>
			</div>
		</div>
	);
};

export default Overview;
