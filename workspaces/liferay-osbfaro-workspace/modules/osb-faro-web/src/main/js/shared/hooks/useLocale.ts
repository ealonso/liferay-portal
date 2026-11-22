import {resolveLocale} from 'shared/util/locale';
import {usePageConfig} from 'shared/context/pageConfig';

/**
 * Resolves the current user's locale from their account language
 * preference.
 */
export const useLocale = (): string => {
	const {currentUser} = usePageConfig();

	const {languageId} = currentUser;

	return resolveLocale(languageId);
};
