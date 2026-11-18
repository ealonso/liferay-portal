import BasePage from 'settings/components/base-page/BasePage';
import React from 'react';
import SuppressedUserList from '../hocs/SuppressedUserList';
import {getDataPrivacy} from 'shared/util/breadcrumbs';
import {Router} from 'shared/types';
import {useTimeZone} from 'shared/hooks/useTimeZone';
import {usePageConfig} from 'shared/context/pageConfig';

export const SuppressedUsers = ({router}: {router: Router}) => {
	const config = usePageConfig();

	const {currentUser} = config;
	const {timeZoneId} = useTimeZone();

	return (
		<BasePage
			breadcrumbItems={[
				getDataPrivacy(config),
				{
					active: true,
					label: Liferay.Language.get('suppressed-user-list'),
				},
			]}
			className="suppressed-users-page-root"
			documentTitle={Liferay.Language.get('suppressed-user-list')}
		>
			<SuppressedUserList
				currentUser={currentUser}
				router={router}
				timeZoneId={timeZoneId}
			/>
		</BasePage>
	);
};

export default SuppressedUsers;
