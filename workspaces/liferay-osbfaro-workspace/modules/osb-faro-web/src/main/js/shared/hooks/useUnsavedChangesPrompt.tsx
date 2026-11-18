import {useEffect} from 'react';

/**
 * Asks the browser to confirm leaving the page while there are unsaved
 * changes.
 */
export function useUnsavedChangesPrompt(when: boolean): void {
	useEffect(() => {
		if (!when) {
			return;
		}

		const handleBeforeUnload = (event: BeforeUnloadEvent) =>
			event.preventDefault();

		window.addEventListener('beforeunload', handleBeforeUnload);

		return () =>
			window.removeEventListener('beforeunload', handleBeforeUnload);
	}, [when]);
}

export default useUnsavedChangesPrompt;
