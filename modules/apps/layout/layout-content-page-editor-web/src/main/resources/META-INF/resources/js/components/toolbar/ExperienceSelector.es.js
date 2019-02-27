import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import getConnectedComponent from '../../store/ConnectedComponent.es';
import templates from './ExperienceSelector.soy';
import {CREATE_EXPERIENCE, END_CREATE_EXPERIENCE, SELECT_EXPERIENCE, START_CREATE_EXPERIENCE} from '../../actions/actions.es';
import 'frontend-js-web/liferay/compat/modal/Modal.es';

/**
 * ExperienceSelector
 */
class ExperienceSelector extends Component {

	/**
	 * @private
	 * @review
	 * @param {Event} event
	 * @memberof ExperienceSelector
	 */
	_createExperience(event) {
		event.preventDefault();

		const {
			experienceName: experienceNameElem,
			experienceSegmentId: experienceSegmentIdElem
		} = this.refs.modal.refs;

		this.store.dispatchAction(
			CREATE_EXPERIENCE,
			{
				experienceLabel: experienceNameElem.value,
				segmentId: experienceSegmentIdElem.value
			}
		).dispatchAction(
			END_CREATE_EXPERIENCE
		);
	}

	/**
	 * @private
	 * @review
	 * @param {Event} event
	 * @memberof ExperienceSelector
	 */
	_handleDropdownButtonClick(event) {
		event.preventDefault();
		this._toggleDropdown();
	}

	/**
	 * @private
	 * @review
	 * @memberof ExperienceSelector
	 */
	_handleDropdownBlur() {
		clearTimeout(this.willToggleDropdownId);
		this.willToggleDropdownId = setTimeout(
			() => {
				this._toggleDropdown(false);
			},
			200
		);
	}

	_handleExperienceKeyPress(event) {
		if (event.key == 'Enter' || event.key == ' ') {
			const experienceId = event.delegateTarget.dataset.experienceId;
			this._selectExperience(experienceId);
		}
	}

	/**
	 * @private
	 * @review
	 * @memberof ExperienceSelector
	 */
	_handleDropdownFocus() {
		clearTimeout(this.willToggleDropdownId);
	}

	/**
	 * @private
	 * @review
	 * @param {Event} event
	 * @memberof ExperienceSelector
	 */
	_handleExperienceClick(event) {
		event.preventDefault();
		const experienceId = event.delegateTarget.dataset.experienceId;
		this._selectExperience(experienceId);
	}

	_showModal(event) {
		event.preventDefault();
		this.store.dispatchAction(
			START_CREATE_EXPERIENCE
		);
	}

	_closeModal(event) {
		event.preventDefault();
		this.store.dispatchAction(
			END_CREATE_EXPERIENCE
		);
	}

	_toggleModal() {
		const action = this.experienceCreation.creatingExperience ?
			END_CREATE_EXPERIENCE :
			START_CREATE_EXPERIENCE;
		this.store.dispatchAction(action);
	}

	_selectExperience(experienceId) {
		this.store.dispatchAction(
			SELECT_EXPERIENCE,
			{
				experienceId
			}
		);
	}

	/**
	 * @private
	 * @review
	 * @param {boolean} [newDropdownState=!this.openDropdown]
	 * @memberof ExperienceSelector
	 */
	_toggleDropdown(newDropdownState = !this.openDropdown) {
		this.openDropdown = newDropdownState;
	}

	/**
	 * Transforms `availableSegments` and `availableExperiences` objects into arrays
	 * Adds `activeExperienceLabel` to the component state
	 *
	 * @inheritDoc
	 * @review
	 */
	prepareStateForRender(state) {
		const availableExperiencesArray = Object.values(state.availableExperiences || [])
			.sort(comparePriority)
			.map(
				experience => {
					return Object.assign(
						{},
						experience,
						{
							segmentLabel: findSegmentLabelById(
								Object.values(state.availableSegments),
								experience.segmentId
							)
						}
					);
				}
			);

		const selectedExperienceId = state.experienceId || state.defaultExperienceId;

		const activeExperience = availableExperiencesArray.find(
			experience => experience.experienceId === selectedExperienceId
		);

		const availableSegments = Object.values(state.availableSegments || [])
			.filter(
				segment => segment.segmentId !== state.defaultSegmentId
			);

		let innerState = Object.assign(
			{},
			state,
			{
				activeExperienceLabel: activeExperience && activeExperience.experienceLabel,
				availableExperiences: availableExperiencesArray,
				availableSegments,
				experienceId: selectedExperienceId
			}
		);

		return innerState;
	}
}

ExperienceSelector.STATE = {
	modal: Config.bool().internal().value(false),
	openDropdown: Config.bool().internal().value(false),
	segmentId: Config.string().internal()
};

const ConnectedExperienceSelector = getConnectedComponent(
	ExperienceSelector,
	[
		'classPK',
		'availableExperiences',
		'experienceId',
		'defaultSegmentId'
	]
);

Soy.register(ConnectedExperienceSelector, templates);

export {ConnectedExperienceSelector};
export default ConnectedExperienceSelector;

/**
 * Tells if a priority an `obj2`
 * has higher, equal or lower priority
 * than `obj1`
 *
 * @review
 * @param {object} obj1
 * @param {object} obj2
 * @returns {1|0|-1}
 */
function comparePriority(obj1, obj2) {
	let result = 0;
	if (obj1.priority > obj2.priority) {
		result = -1;
	}
	if (obj1.priority < obj2.priority) {
		result = 1;
	}
	return result;
}

/**
 * Searchs for a segment based on its Id
 * and returns its label
 *
 * @review
 * @param {Array} segments
 * @param {string} segmentId
 * @returns {string|undefined}
 */
function findSegmentLabelById(segments, segmentId) {
	const mostWantedSegment = segments.find(
		segment => segment.segmentId === segmentId
	);
	return mostWantedSegment && mostWantedSegment.segmentLabel;
}