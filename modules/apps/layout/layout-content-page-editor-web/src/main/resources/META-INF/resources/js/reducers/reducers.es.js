import {addFragmentEntryLinkReducer, moveFragmentEntryLinkReducer, removeFragmentEntryLinkReducer, updateEditableValueReducer} from './fragments.es';
import {addPortletReducer} from './portlets.es';
import {addSectionReducer, moveSectionReducer, removeSectionReducer, updateSectionConfigReducer} from './sections.es';
import {hideFragmentsEditorSidebarReducer, toggleFragmentsEditorSidebarReducer} from './sidebar.es';
import {hideMappingDialogReducer, hideMappingTypeDialogReducer, openAssetTypeDialogReducer, openMappingFieldsDialogReducer, selectMappeableTypeReducer} from './dialogs.es';
import {languageIdReducer, translationStatusReducer} from './translations.es';
import {saveChangesReducer} from './changes.es';
import {segmentIdReducer} from './segments.es';
import {updateActiveItemReducer, updateDropTargetReducer, updateHoveredItemReducer} from './placeholders.es';
import {createExperienceReducer, endCreateExperience, selectExperienceReducer, startCreateExperience} from './experiences.es';

/**
 * List of reducers
 * @type {function[]}
 */
const reducers = [
	addFragmentEntryLinkReducer,
	addPortletReducer,
	addSectionReducer,
	createExperienceReducer,
	endCreateExperience,
	hideFragmentsEditorSidebarReducer,
	hideMappingDialogReducer,
	hideMappingTypeDialogReducer,
	languageIdReducer,
	moveFragmentEntryLinkReducer,
	moveSectionReducer,
	openAssetTypeDialogReducer,
	openMappingFieldsDialogReducer,
	removeFragmentEntryLinkReducer,
	removeSectionReducer,
	saveChangesReducer,
	segmentIdReducer,
	selectMappeableTypeReducer,
	toggleFragmentsEditorSidebarReducer,
	translationStatusReducer,
	updateActiveItemReducer,
	updateDropTargetReducer,
	updateEditableValueReducer,
	updateHoveredItemReducer,
	updateSectionConfigReducer,
	selectExperienceReducer,
	startCreateExperience
];

export {reducers};
export default reducers;