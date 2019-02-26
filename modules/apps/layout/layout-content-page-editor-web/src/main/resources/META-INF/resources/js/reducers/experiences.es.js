import {
	CREATE_EXPERIENCE,
	END_CREATE_EXPERIENCE,
	SELECT_EXPERIENCE,
	START_CREATE_EXPERIENCE
} from '../actions/actions.es';
import {setIn} from '../utils/utils.es';

/**
 * @param {!object} state
 * @param {!string} actionType
 * @param {object} payload
 * @param {string} payload.segmentId
 * @return {object}
 * @review
 */
export function createExperienceReducer(state, actionType, payload) {
	return new Promise(
		resolve => {
			let nextState = state;
			if (actionType === CREATE_EXPERIENCE) {
				const {experienceLabel, segmentId} = payload;

				const {
					classNameId,
					classPK
				} = nextState;

				const nameMap = JSON.stringify(
					{
						[state.defaultLanguageId]: experienceLabel
					}
				);
				const priority = Object.values(nextState.availableExperiences || []).length;

				Liferay.Service(
					CREATE_EXPERIENCE_URL,
					{
						active: true,
						classNameId,
						classPK,
						nameMap,
						priority,
						segmentsEntryId: segmentId
					},
					(obj) => {

						const {
							active,
							nameCurrentValue,
							priority,
							segmentsEntryId,
							segmentsExperienceId
						} = obj;

						nextState = Object.assign(
							{},
							nextState,
							{
								availableExperiences: Object.assign(
									{},
									nextState.availableExperiences,
									{
										[segmentsExperienceId]: {
											active,
											experienceId: segmentsExperienceId,
											experienceLabel: nameCurrentValue,
											priority,
											segmentId: segmentsEntryId
										}
									}
								),
								experienceId: segmentsExperienceId
							}
						);
						resolve(nextState);
					},
					error => {
						resolve(nextState);
					}
				);
			}
			else {
				resolve(nextState);
			}
		}
	);
}

/**
 * @param {!object} state
 * @param {!string} actionType
 * @param {object} [payload]
 * @return {object}
 * @review
 */
export function startCreateExperience(state, actionType, payload) {
	let nextState = state;

	if (actionType === START_CREATE_EXPERIENCE) {
		nextState = Object.assign(
			{},
			state,
			{
				experienceCreation: Object.assign(
					{},
					state.experienceCreation,
					{
						creatingExperience: true,
						error: null
					}
				)
			}
		);
	}
	return nextState;
}

/**
 * @param {!object} state
 * @param {!string} actionType
 * @param {object} [payload]
 * @return {object}
 * @review
 */
export function endCreateExperience(state, actionType, payload) {
	let nextState = state;

	if (actionType === END_CREATE_EXPERIENCE) {
		nextState = Object.assign(
			{},
			state,
			{
				experienceCreation: Object.assign(
					{},
					state.experienceCreation,
					{
						creatingExperience: false,
						error: null
					}
				)
			}
		);
	}
	return nextState;
}

/**
 *
 *
 * @export
 * @param {!object} state
 * @param {!string} actionType
 * @param {!object} payload
 * @param {!string} payload.string
 * @returns
 */
export function selectExperienceReducer(state, actionType, payload) {
	let nextState = state;

	if (actionType === SELECT_EXPERIENCE) {
		nextState = setIn(
			nextState,
			['experienceId'],
			payload.experienceId,
		);
	}

	return nextState;
}

const CREATE_EXPERIENCE_URL = '/segments.segmentsexperience/add-segments-experience';