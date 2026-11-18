import Loading from 'shared/components/Loading';
import ProjectGate from 'shared/components/workspaces/ProjectGate';
import React, {Suspense} from 'react';
import {useOnboardingModal} from 'shared/hooks/useOnboardingModal';
import {config} from 'shared/util/config';

interface ISettingsProps {
	children?: React.ReactNode;
}

export const Settings: React.FC<ISettingsProps> = ({children}) => {
	const {groupId = ''} = config;

	const onboardingModal = useOnboardingModal({groupId});

	return (
		<>
			<Suspense fallback={<Loading />}>{children}</Suspense>

			{onboardingModal}
		</>
	);
};

const SettingsPage: React.FC<ISettingsProps> = ({children}) => {
	const {groupId = ''} = config;

	return (
		<ProjectGate groupId={groupId}>
			<Settings>{children}</Settings>
		</ProjectGate>
	);
};

export default SettingsPage;
