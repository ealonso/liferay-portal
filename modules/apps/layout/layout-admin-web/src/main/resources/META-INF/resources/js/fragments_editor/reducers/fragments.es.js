import {DRAG_POSITIONS} from './placeholders.es';
import {
	ADD_FRAGMENT_ENTRY_LINK,
	REMOVE_FRAGMENT_ENTRY_LINK
} from '../actions/actions.es';

/**
 * @param {!object} state
 * @param {!string} actionType
 * @param {!object} payload
 * @param {!string} payload.fragmentEntryLinkId
 * @param {!string} payload.fragmentEntryLinkName
 * @return {object}
 * @review
 */

function addFragmentEntryLinkReducer(state, actionType, payload) {
	return new Promise(
		(resolve, reject) => {
			let nextState = Object.assign({}, state);

			if (actionType === ADD_FRAGMENT_ENTRY_LINK) {
				let fragmentEntryLink;

				const nextSettings = Object.assign(
					{},
					state.layoutSettings,
					{
						structure: [
							...(state.layoutSettings.structure || [])
						]
					}
				);

				_addFragmentEntryLink(
					state.addFragmentEntryLinkURL,
					payload.fragmentEntryId,
					payload.fragmentName,
					state.classNameId,
					state.classPK,
					state.portletNamespace
				)
					.then(
						response => {
							fragmentEntryLink = response;

							const position = _getDropFragmentPosition(
								nextSettings.structure,
								state.hoveredFragmentEntryLinkId,
								state.hoveredFragmentEntryLinkBorder
							);

							nextSettings.structure.splice(
								position,
								0,
								fragmentEntryLink.fragmentEntryLinkId
							);

							return _updateSettings(
								state.updateFragmentEntryLinksURL,
								state.portletNamespace,
								state.classNameId,
								state.classPK,
								nextSettings
							);
						}
					)
					.then(
						() => {
							return _getFragmentEntryLinkContent(
								state.renderFragmentEntryURL,
								fragmentEntryLink,
								state.portletNamespace
							);
						}
					)
					.then(
						response => {
							fragmentEntryLink = response;

							nextState.fragmentEntryLinks = Object.assign(
								{},
								nextState.fragmentEntryLinks,
								{[fragmentEntryLink.fragmentEntryLinkId]: fragmentEntryLink}
							);

							nextState.layoutSettings = nextSettings;

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
 * @param {!object} payload
 * @param {!string} payload.fragmentEntryLinkId
 * @return {object}
 * @review
 */

function removeFragmentEntryLinkReducer(state, actionType, payload) {
	return new Promise(
		resolve => {
			if (actionType === REMOVE_FRAGMENT_ENTRY_LINK) {
				const fragmentEntryLinkId = payload.fragmentEntryLinkId;
				const nextState = Object.assign({}, state);

				const nextSettings = Object.assign(
					{},
					state.layoutSettings,
					{
						structure: [
							...(state.layoutSettings.structure || [])
						]
					}
				);

				const index = state.layoutSettings.structure.indexOf(
					fragmentEntryLinkId
				);

				nextSettings.structure.splice(index, 1);

				_removeFragmentEntryLink(
					state.deleteFragmentEntryLinkURL,
					state.portletNamespace,
					state.classNameId,
					state.classPK,
					fragmentEntryLinkId,
					nextSettings
				).then(
					(response) => {
						nextState.layoutSettings = nextSettings;

						delete nextState.fragmentEntryLinks[payload.fragmentEntryLinkId];

						resolve(nextState);
					}
				);
			}
			else {
				resolve(state);
			}
		}
	);
}

function _addFragmentEntryLink(
	addFragmentEntryLinkURL,
	fragmentEntryId,
	fragmentName,
	classNameId,
	classPK,
	portletNamespace
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}fragmentId`, fragmentEntryId);
	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);

	return fetch(
		addFragmentEntryLinkURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	).then(
		response => {
			return response.json();
		}
	).then(
		response => {
			if (!response.fragmentEntryLinkId) {
				throw new Error();
			}

			return {
				config: {},
				content: '',
				editableValues: JSON.parse(response.editableValues),
				fragmentEntryId: fragmentEntryId,
				fragmentEntryLinkId: response.fragmentEntryLinkId,
				name: fragmentName
			};
		}
	);
}

function _getDropFragmentPosition(
	structure,
	targetFragmentEntryLinkId,
	targetBorder
) {
	let position = structure.length;
	const targetPosition = structure.indexOf(targetFragmentEntryLinkId);

	if (targetPosition > -1 && targetBorder) {
		if (targetBorder === DRAG_POSITIONS.top) {
			position = targetPosition;
		}
		else {
			position = targetPosition + 1;
		}
	}

	return position;
}

function _getFragmentEntryLinkContent(
	renderFragmentEntryURL,
	fragmentEntryLink,
	portletNamespace
) {
	const formData = new FormData();

	formData.append(
		`${portletNamespace}fragmentEntryLinkId`,
		fragmentEntryLink.fragmentEntryLinkId
	);

	return fetch(
		renderFragmentEntryURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	).then(
		response => response.json()
	).then(
		response => {
			if (!response.content) {
				throw new Error();
			}

			return Object.assign(
				{},
				fragmentEntryLink,
				{content: response.content}
			);
		}
	);
}

function _removeFragmentEntryLink(
	deleteFragmentEntryLinkURL,
	portletNamespace,
	classNameId,
	classPK,
	fragmentEntryLinkId,
	layoutSettings
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);
	formData.append(`${portletNamespace}settings`, JSON.stringify(layoutSettings));

	formData.append(
		`${portletNamespace}fragmentEntryLinkId`,
		fragmentEntryLinkId
	);

	return fetch(
		deleteFragmentEntryLinkURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	);
}

/**
 * Update layoutSettings
 * @param {!string} updateFragmentEntryLinksURL
 * @param {!string} portletNamespace
 * @param {!string} classNameId
 * @param {!string} classPK
 * @param {!object} settings
 * @private
 * @return {Promise}
 * @review
 */

function _updateSettings(
	updateFragmentEntryLinksURL,
	portletNamespace,
	classNameId,
	classPK,
	settings
) {
	const formData = new FormData();

	formData.append(`${portletNamespace}classNameId`, classNameId);
	formData.append(`${portletNamespace}classPK`, classPK);

	formData.append(
		`${portletNamespace}settings`,
		JSON.stringify(settings)
	);

	return fetch(
		updateFragmentEntryLinksURL,
		{
			body: formData,
			credentials: 'include',
			method: 'POST'
		}
	);
}

export {
	addFragmentEntryLinkReducer,
	removeFragmentEntryLinkReducer
};