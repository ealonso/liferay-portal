import ConnectorAuth from '../ConnectorAuth';
import React from 'react';
import {Alert} from 'shared/types';
import {ConnectorConfig} from '../types';
import {updateSearchParams} from 'settings/components/base-page/utils';
import {useWizardPage} from '../../base-page/WizardPageContext';
import {usePageConfig} from 'shared/context/pageConfig';

interface IConnectorAuthStepProps {
	addAlert: Alert.AddAlert;
	config: ConnectorConfig;
	groupId: string;
	onNext: () => void;
}

const ConnectorAuthStep = ({
	addAlert,
	config,
	groupId,
	onNext,
}: IConnectorAuthStepProps) => {
	const {dataSource} = useWizardPage();
	const {dataSourcesURL} = usePageConfig();

	const handleCancel = () => {
		Liferay.Util.navigate(dataSourcesURL);
	};

	if (!dataSource) {
		return (
			<ConnectorAuth
				addAlert={addAlert}
				buttonProps={{block: true}}
				config={config}
				groupId={groupId}
				onCancel={handleCancel}
				onSubmit={(createdDataSource) => {
					updateSearchParams('dataSourceId', createdDataSource.id);
					onNext();
				}}
			/>
		);
	}

	return (
		<ConnectorAuth
			addAlert={addAlert}
			buttonProps={{block: true}}
			config={config}
			dataSource={dataSource}
			groupId={groupId}
			onCancel={handleCancel}
			onSubmit={onNext}
		/>
	);
};

export {ConnectorAuthStep};
