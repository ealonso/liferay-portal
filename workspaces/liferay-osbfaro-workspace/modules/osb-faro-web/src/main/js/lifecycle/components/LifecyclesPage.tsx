import * as breadcrumbs from 'shared/util/breadcrumbs';
import BasePage from 'shared/components/base-page';
import React, {useContext} from 'react';
import {ChannelContext} from 'shared/context/channel';
import {config} from 'shared/util/config';

interface ILifecyclesPageProps {
	children: React.ReactNode;
	documentTitle?: string;
	showTitle?: boolean;
}

const LifecyclesPage: React.FC<ILifecyclesPageProps> = ({
	children,
	documentTitle = Liferay.Language.get('lifecycles'),
	showTitle = true,
}) => {
	const {selectedChannel} = useContext(ChannelContext);

	const {channelId, groupId} = config;

	return (
		<BasePage documentTitle={documentTitle}>
			<BasePage.Header
				breadcrumbs={[
					breadcrumbs.getHome({
						channelId: channelId!,
						groupId: groupId!,
						label: selectedChannel?.name,
					}),
					{
						...breadcrumbs.getLifecycles(),
						active: true,
					},
				]}
				fluid
				groupId={groupId!}
			>
				{showTitle && (
					<BasePage.Row>
						<BasePage.Header.TitleSection
							className="mb-3"
							title={Liferay.Language.get('lifecycles')}
						/>
					</BasePage.Row>
				)}
			</BasePage.Header>

			<BasePage.Body fluid>{children}</BasePage.Body>
		</BasePage>
	);
};

export default LifecyclesPage;
