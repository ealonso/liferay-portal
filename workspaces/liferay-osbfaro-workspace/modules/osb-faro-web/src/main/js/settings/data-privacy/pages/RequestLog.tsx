import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RequestList from '../hocs/RequestList';
import {getDataPrivacy} from 'shared/util/breadcrumbs';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {useTimeZone} from 'shared/hooks/useTimeZone';

export const RequestLog = () => {
	const currentUser = useCurrentUser();
	const {timeZoneId} = useTimeZone();

	return (
		<BasePage
			breadcrumbItems={[
				getDataPrivacy(),
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
