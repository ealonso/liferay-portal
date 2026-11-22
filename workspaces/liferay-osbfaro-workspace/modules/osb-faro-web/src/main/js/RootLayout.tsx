import AlertFeed from 'shared/components/AlertFeed';
import Loading from 'shared/components/Loading';
import React, {Suspense, useEffect, useState} from 'react';
import TrackingConsentBanner from 'shared/components/TrackingConsentBanner';
import {FaroEnv} from 'shared/util/constants';
import {Outlet, ScrollRestoration, useMatch} from 'react-router-dom';
import {Pendo, TrackingConsentValues} from 'shared/util/pendo';
import {Project} from 'shared/util/records';
import {syncAIHubChatbot} from 'shared/util/ai-hub-chatbot';
import {useSelector} from 'react-redux';
import {usePageConfig} from 'shared/context/pageConfig';

const RootLayout = () => {
	const {currentUser} = usePageConfig();

	const match = useMatch('/workspace/:groupId/*');
	const groupId = match?.params.groupId ?? '0';

	const project: Project = useSelector<any, any>((state) =>
		state.getIn(['projects', groupId, 'data'])
	);

	const [trackingConsent, setTrackingConsent] =
		useState<TrackingConsentValues | null>(null);

	// The chatbot is provisioned per workspace, so it follows the route.

	useEffect(() => {
		syncAIHubChatbot(match?.params.groupId);
	}, [match?.params.groupId]);

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
			<ScrollRestoration />

			<AlertFeed />

			<Suspense fallback={<Loading />}>
				<Outlet />
			</Suspense>

			{!!currentUser?.id && FARO_ENV === FaroEnv.Production && (
				<TrackingConsentBanner onDecision={setTrackingConsent} />
			)}
		</>
	);
};

export default RootLayout;
