import BaseEditPage from 'shared/components/base-edit-page';
import Breadcrumbs from 'shared/components/Breadcrumbs';
import ClayIcon from 'shared/components/Icon';
import ClayLink from '@clayui/link';
import DocumentTitle from 'shared/components/DocumentTitle';
import getCN from 'classnames';
import MaintenanceAlert from 'shared/components/MaintenanceAlert';
import NotificationAlertList, {
	useNotificationsAPI,
} from 'shared/components/NotificationAlertList';
import React from 'react';
import TextTruncate from 'shared/components/TextTruncate';
import {DEVELOPER_MODE} from 'shared/util/constants';
import {IBreadcrumbArgs} from 'shared/util/breadcrumbs';
import {PageActions} from 'shared/components/base-page/Header';
import {config} from 'shared/util/config';

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
	const {groupId = ''} = config;
	const notificationResponse = useNotificationsAPI(groupId);

	return (
		<div className="settings-root">
			<BaseEditPage.Toolbar
				backURL={config.sitesURL}
				className="mb-4"
				title={Liferay.Language.get('settings')}
			/>

			<div className="content-wrapper">
				<nav className="section-side settings-side-bar">
					{(config.sidebarNavigationItems ?? []).map(
						({label, navigationItems = []}) => (
							<div key={label}>
								{label && (
									<h5 className="section-title">{label}</h5>
								)}

								<ul className="nav">
									{navigationItems
										.filter(
											({data}) =>
												DEVELOPER_MODE ||
												!data?.developerMode
										)
										.map(({active, href, icon, label}) => (
											<li
												className={getCN('item', {
													active,
												})}
												key={label}
											>
												<ClayLink
													className="button-root"
													href={href}
												>
													{icon && (
														<span className="icon-wrapper">
															<ClayIcon
																className="icon-root"
																symbol={icon}
															/>
														</span>
													)}

													{label}
												</ClayLink>
											</li>
										))}
								</ul>
							</div>
						)
					)}
				</nav>

				<div className="content section-main">
					<div
						className={getCN('settings-base-page-root', className)}
					>
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

						{breadcrumbItems && (
							<Breadcrumbs items={breadcrumbItems} />
						)}

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
												<TextTruncate
													title={pageTitle}
												/>
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
		</div>
	);
};

export default SettingsBasePage;
