import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RequestList from '../hocs/RequestList';
import {getDataPrivacy} from 'shared/util/breadcrumbs';
import {useTimeZone} from 'shared/hooks/useTimeZone';
import {usePageConfig} from 'shared/context/pageConfig';

export const RequestLog = () => {
	const config = usePageConfig();

	const {currentUser} = config;
	const {timeZoneId} = useTimeZone();

	return (
		<BasePage
			breadcrumbItems={[
				getDataPrivacy(config),
				{
					active: true,
					label: Liferay.Language.get('request-log'),
				},
			]}
			className="request-log-page-root"
			documentTitle={Liferay.Language.get('request-log')}
		>
			<RequestList currentUser={currentUser} timeZoneId={timeZoneId} />
		</BasePage>
	);
};

export default RequestLog;
