import AlertFeed from 'shared/components/AlertFeed';
import Loading from 'shared/components/Loading';
import React, {Suspense, useEffect, useState} from 'react';
import TrackingConsentBanner from 'shared/components/TrackingConsentBanner';
import {FaroEnv} from 'shared/util/constants';
import {Pendo, TrackingConsentValues} from 'shared/util/pendo';
import {Project} from 'shared/util/records';
import {syncAIHubChatbot} from 'shared/util/ai-hub-chatbot';
import {resolveLocale, setLocale} from 'shared/util/locale';
import {useSelector} from 'react-redux';
import {usePageConfig} from 'shared/context/pageConfig';

interface IAppShellProps {
	children: React.ReactNode;
	groupId?: string;
}

const AppShell: React.FC<IAppShellProps> = ({children, groupId}) => {
	const {currentUser} = usePageConfig();

	const projectGroupId = groupId ?? '0';

	const project: Project = useSelector<any, any>((state) =>
		state.getIn(['projects', projectGroupId, 'data'])
	);

	const [trackingConsent, setTrackingConsent] =
		useState<TrackingConsentValues | null>(null);

	useEffect(() => {
		setLocale(resolveLocale(currentUser.languageId));
	}, [currentUser.languageId]);

	// The chatbot is provisioned per workspace, so it follows the route.

	useEffect(() => {
		syncAIHubChatbot(groupId);
	}, [groupId]);

	// The stored cookie decides whether Pendo may start. `trackingConsent` is
	// not read here: it only re-runs the effect once the banner stores a
	// decision, so tracking starts without a reload. A loaded workspace is
	// enough: LDP workspaces carry no corp project, so gating on one would
	// leave them untracked.

	useEffect(() => {
		const pendo = new Pendo();

		if (
			currentUser?.id &&
			project?.groupId &&
			pendo.getUserConsent() === TrackingConsentValues.Accepted
		) {
			pendo.initialize({currentUser, project});
		}
	}, [currentUser?.id, project?.groupId, trackingConsent]);

	return (
		<>
			<AlertFeed />

			<Suspense fallback={<Loading />}>{children}</Suspense>

			{!!currentUser?.id && FARO_ENV === FaroEnv.Production && (
				<TrackingConsentBanner onDecision={setTrackingConsent} />
			)}
		</>
	);
};

export default AppShell;
