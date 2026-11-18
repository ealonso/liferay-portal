import * as API from 'shared/api';
import BasePage from 'settings/components/base-page/BasePage';
import Card from 'shared/components/Card';
import ClayBadge from '@clayui/badge';
import ClayLink from '@clayui/link';
import ClayNavigationBar from '@clayui/navigation-bar';
import getCN from 'classnames';
import Loading from 'shared/components/Loading';
import React, {Suspense, useState} from 'react';
import {useCurrentUser} from 'shared/hooks/useCurrentUser';
import {User as UserRecord} from 'shared/util/records';
import {UserStatuses} from 'shared/util/constants';
import {config} from 'shared/util/config';

interface IUserContentArgs {
	currentUser: UserRecord;
	onSetUserRequest: (userRequest: number) => void;
}

interface IUserProps {
	className?: string;
	renderContent: (args: IUserContentArgs) => React.ReactNode;
}

export const User: React.FC<IUserProps> = ({className, renderContent}) => {
	const {groupId = ''} = config;
	const currentUser = useCurrentUser();
	const [userRequest, setUserRequest] = useState<number>(0);

	const onSetUserRequest = (userRequest: number) =>
		setUserRequest(userRequest);

	API.user
		.fetchCount({
			groupId,
			statuses: [UserStatuses.Requested],
		})
		.then(setUserRequest);

	const navigationItems = config.navigationItems ?? [];

	const initialItem =
		navigationItems.find(({active}) => active) ?? navigationItems[0];

	const [activeTriggerLabel, setActiveTriggerLabel] = useState(
		initialItem?.label
	);

	return (
		<BasePage
			className={getCN('user-list-page-root', className)}
			key="userListPage"
			pageDescription={Liferay.Language.get(
				'invite-new-users-to-analytics-cloud-and-or-configure-existing-users'
			)}
			pageTitle={Liferay.Language.get('user-management')}
		>
			<Card key="cardContainer" pageDisplay>
				{currentUser.isAdmin() && (
					<ClayNavigationBar
						className="page-subnav mx-4 my-3"
						triggerLabel={activeTriggerLabel}
					>
						{navigationItems.map(({active, data, href, label}) => (
							<ClayNavigationBar.Item active={active} key={label}>
								<ClayLink
									href={href}
									onClick={() => setActiveTriggerLabel(label)}
								>
									{label}

									{data?.id === 'requests' &&
										userRequest > 0 && (
											<ClayBadge
												className="ml-2"
												label={userRequest}
											/>
										)}
								</ClayLink>
							</ClayNavigationBar.Item>
						))}
					</ClayNavigationBar>
				)}

				<Suspense fallback={<Loading />}>
					{renderContent({currentUser, onSetUserRequest})}
				</Suspense>
			</Card>
		</BasePage>
	);
};

export default User;
