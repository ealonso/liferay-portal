import Loading from 'shared/components/Loading';
import NoPropertiesAvailable from 'shared/pages/NoPropertiesAvailable';
import React, {Suspense} from 'react';
import {Outlet, useParams} from 'react-router-dom';
import {useChannelContext} from 'shared/context/channel';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';

const SidebarLayout: React.FC = () => {
	const {groupId = ''} = useParams<{groupId: string}>();

	const {selectedChannel} = useChannelContext();

	const currentUser = useCurrentUser();

	return (
		<Suspense fallback={<Loading />}>
			{selectedChannel ? (
				<Outlet />
			) : (
				<NoPropertiesAvailable
					currentUser={currentUser}
					groupId={groupId}
				/>
			)}
		</Suspense>
	);
};

export default SidebarLayout;
