import ConfirmationModal from 'shared/components/ConfirmationModal';
import React, {useEffect, useRef, useState} from 'react';
import {useModal} from '@clayui/modal';

interface IBeforeNavigateEvent {
	originalEvent: Event;
	path: string;
}

/**
 * Blocks the navigation while there are unsaved changes and asks the user
 * whether to leave. The caller renders the returned modal. Reloading or
 * closing the page falls back to the browser prompt.
 */
export function useUnsavedChangesPrompt(
	when: boolean,
	message?: string
): React.ReactNode {
	const modal = useModal();

	const leavingRef = useRef(false);

	const [path, setPath] = useState<string | null>(null);

	useEffect(() => {
		if (!when) {
			return;
		}

		const handleBeforeNavigate = (event: IBeforeNavigateEvent) => {
			if (leavingRef.current) {
				return;
			}

			event.originalEvent.preventDefault();

			setPath(event.path);

			modal.onOpenChange(true);
		};

		const handleBeforeUnload = (event: BeforeUnloadEvent) => {
			if (!leavingRef.current) {
				event.preventDefault();
			}
		};

		Liferay.on('beforeNavigate', handleBeforeNavigate);

		window.addEventListener('beforeunload', handleBeforeUnload);

		return () => {
			Liferay.detach('beforeNavigate', handleBeforeNavigate);

			window.removeEventListener('beforeunload', handleBeforeUnload);
		};
	}, [when]);

	if (!modal.open) {
		return null;
	}

	return (
		<ConfirmationModal
			cancelMessage={Liferay.Language.get('stay-on-page')}
			closeAfterSubmit={false}
			message={message}
			observer={modal.observer}
			onClose={() => modal.onOpenChange(false)}
			onSubmit={() => {
				modal.onOpenChange(false);

				if (path) {
					leavingRef.current = true;

					Liferay.Util.navigate(path);
				}
			}}
			submitButtonDisplay="warning"
			submitMessage={Liferay.Language.get('leave-page')}
			title={Liferay.Language.get('unsaved-changes')}
			type="warning"
		/>
	);
}

export default useUnsavedChangesPrompt;
