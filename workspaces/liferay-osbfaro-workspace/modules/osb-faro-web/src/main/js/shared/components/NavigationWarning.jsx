import {useUnsavedChangesPrompt} from 'shared/hooks/useUnsavedChangesPrompt';

const NavigationWarning = ({when}) => {
	useUnsavedChangesPrompt(when);

	return null;
};

export default NavigationWarning;
