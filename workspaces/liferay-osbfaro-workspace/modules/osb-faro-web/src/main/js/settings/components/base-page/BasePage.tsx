import Breadcrumbs from 'shared/components/Breadcrumbs';
import DocumentTitle from 'shared/components/DocumentTitle';
import getCN from 'classnames';
import MaintenanceAlert from 'shared/components/MaintenanceAlert';
import NotificationAlertList, {
	useNotificationsAPI,
} from 'shared/components/NotificationAlertList';
import React from 'react';
import TextTruncate from 'shared/components/TextTruncate';
import {IBreadcrumbArgs} from 'shared/util/breadcrumbs';
import {PageActions} from 'shared/components/base-page/Header';
import {usePageConfig} from 'shared/context/pageConfig';

interface ISettingsBasePageProps {
	breadcrumbItems?: Array<IBreadcrumbArgs>;
	children?: React.ReactNode;
	className?: string;
	documentTitle?: string;
	pageActions?: Array<any>;
	pageActionsDisplayLimit?: number;
	pageDescription?: React.ReactNode;
	pageTitle?: React.ReactNode;
	subTitle?: React.ReactNode;
}

const SettingsBasePage: React.FC<ISettingsBasePageProps> = ({
	breadcrumbItems,
	children,
	className,
	documentTitle,
	pageActions = [],
	pageActionsDisplayLimit,
	pageDescription,
	pageTitle,
	subTitle,
}) => {
	const {groupId = ''} = usePageConfig();
	const notificationResponse = useNotificationsAPI(groupId);

	return (
		<div className="settings-root">
			<div className="content section-main">
				<div className={getCN('settings-base-page-root', className)}>
					<DocumentTitle
						title={`${
							documentTitle || pageTitle
						} - ${Liferay.Language.get('settings')}`}
					/>

					<NotificationAlertList
						{...notificationResponse}
						groupId={groupId}
					/>

					<MaintenanceAlert />

					{breadcrumbItems && <Breadcrumbs items={breadcrumbItems} />}

					{(!!pageTitle ||
						!!pageDescription ||
						!!pageActions.length) && (
						<div
							className={getCN('content-header', {
								['has-page-actions']: !!pageActions.length,
							})}
						>
							<div className="header-text">
								{pageTitle && (
									<div className="d-flex">
										<h3 className="title-text text-truncate">
											<TextTruncate title={pageTitle} />
										</h3>

										{subTitle && (
											<TextTruncate
												className="subtitle-text ml-2"
												title={subTitle}
											/>
										)}
									</div>
								)}

								{pageDescription && (
									<div className="description">
										{pageDescription}
									</div>
								)}
							</div>

							<div className="page-actions-container">
								<PageActions
									actions={pageActions}
									actionsDisplayLimit={
										pageActionsDisplayLimit
									}
								/>
							</div>
						</div>
					)}

					<div>{children}</div>
				</div>
			</div>
		</div>
	);
};

export default SettingsBasePage;
