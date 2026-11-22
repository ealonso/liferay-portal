import CreateLifecycleForm from 'lifecycle/components/CreateLifecycleForm';
import LifecyclesPage from 'lifecycle/components/LifecyclesPage';
import React from 'react';
import {LifecycleAccountDataEmptyState} from 'lifecycle/components/LifecycleEmptyStates';
import {useLifecycleAccountData} from 'lifecycle/hooks/useLifecycleAccountData';
import {useParams} from 'react-router-dom';
import {AccountDataStatus} from 'lifecycle/utils/types';
import {usePageConfig} from 'shared/context/pageConfig';

const CreateLifecycle = () => {
	const {currentUser} = usePageConfig();

	const {groupId} = useParams();

	const {refetch: refetchAccountData, status: accountDataStatus} =
		useLifecycleAccountData();

	if (accountDataStatus !== AccountDataStatus.Ready) {
		return (
			<LifecyclesPage
				showTitle={accountDataStatus !== AccountDataStatus.Loading}
			>
				<LifecycleAccountDataEmptyState
					authorized={currentUser.isAdmin()}
					groupId={groupId!}
					onReload={refetchAccountData}
					status={accountDataStatus}
				/>
			</LifecyclesPage>
		);
	}

	return (
		<CreateLifecycleForm description="" name="" stageConfigs={() => []} />
	);
};

export default CreateLifecycle;
