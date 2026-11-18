import ClayAlert from '@clayui/alert';
import ClayForm from '@clayui/form';
import DisconnectDataSourceModal from 'shared/components/modals/DisconnectDataSourceModal';
import React from 'react';
import {Alert} from 'shared/types';
import {ConnectMarketoCampaignAuth} from 'settings/components/marketo/ConnectMarketoCampaignAuth';
import {DataSourceStatuses} from 'shared/util/constants';
import {disconnect} from 'shared/api/data-source';
import {sub} from 'shared/util/lang';
import {updateSearchParams} from 'settings/components/base-page/utis';
import {useModal} from '@clayui/modal';
import {useWizardPage} from '../../base-page/WizardPageContext';
import {WizardPageButtonGroup} from 'settings/components/base-page/WizardPageButtonGroup';
import {config} from 'shared/util/config';

interface IConnectMarketoCampaignStepProps {
	addAlert: Alert.AddAlert;
	groupId: string;
	onNext: () => void;
}

const ConnectMarketoCampaignStep = ({
	addAlert,
	groupId,
	onNext,
}: IConnectMarketoCampaignStepProps) => {
	const {dataSource, refetchDataSource} = useWizardPage();
	const modal = useModal();

	if (!dataSource) {
		return (
			<ConnectMarketoCampaignAuth
				addAlert={addAlert}
				buttonProps={{block: true}}
				onCancel={() => {
					window.location.assign(config.dataSourcesURL);
				}}
				onSubmit={(dataSource) => {
					updateSearchParams('dataSourceId', dataSource.id);

					onNext();
				}}
			/>
		);
	}

	if (dataSource.get('status') === DataSourceStatuses.Active) {
		return (
			<ClayForm
				onSubmit={(event) => {
					event.preventDefault();

					onNext();
				}}
			>
				<ClayAlert
					displayType="success"
					title={Liferay.Language.get('success')}
				>
					{Liferay.Language.get(
						'connection-established-successfully'
					)}
				</ClayAlert>

				<ConnectMarketoCampaignAuth
					addAlert={addAlert}
					buttonProps={{block: true}}
					dataSource={dataSource}
					disabled
					onSubmit={onNext}
				/>

				<WizardPageButtonGroup
					nextButtonLabel={Liferay.Language.get('continue')}
					onCancel={() => {
						modal.onOpenChange(true);
					}}
					prevButtonLabel={Liferay.Language.get(
						'disconnect-data-source'
					)}
				/>

				{modal.open && (
					<DisconnectDataSourceModal
						addAlert={addAlert}
						errorMessage={Liferay.Language.get(
							'there-was-an-error-processing-your-request.-try-again.-if-the-problem-persists,-please-contact-support'
						)}
						message={sub(
							Liferay.Language.get(
								'this-action-will-stop-syncing-data-from-x-to-this-workspace.-the-data-that-was-already-synced-will-remain-available-in-the-properties-the-data-source-was-connected-to.-are-you-sure-you-want-to-continue'
							),
							[Liferay.Language.get('marketo')]
						)}
						observer={modal.observer}
						onClose={() => modal.onOpenChange(false)}
						onSubmit={async () => {
							await disconnect({
								groupId,
								id: dataSource.id,
							});

							refetchDataSource(dataSource.id || '');
						}}
					/>
				)}
			</ClayForm>
		);
	}

	return (
		<ConnectMarketoCampaignAuth
			addAlert={addAlert}
			buttonProps={{block: true}}
			dataSource={dataSource}
			onCancel={() => {
				window.location.assign(config.dataSourcesURL);
			}}
			onSubmit={onNext}
		/>
	);
};

export {ConnectMarketoCampaignStep};
