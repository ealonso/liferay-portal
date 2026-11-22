import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import RequestList from '../hocs/RequestList';
import {getDataPrivacy} from 'shared/util/breadcrumbs';
import {useParams} from 'react-router-dom';
import {useTimeZone} from 'shared/hooks/useTimeZone';
import {usePageConfig} from 'shared/context/pageConfig';

export const RequestLog = () => {
	const {currentUser} = usePageConfig();

	const {groupId = ''} = useParams<{groupId: string}>();
	const {timeZoneId} = useTimeZone();

	return (
		<BasePage
			breadcrumbItems={[
				getDataPrivacy({groupId}),
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
